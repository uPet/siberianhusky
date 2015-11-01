/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader;

import co.upet.extensions.glacierbkuploader.glacier.GlacierManager;
import co.upet.extensions.glacierbkuploader.metadata.BackupMetadata;
import co.upet.extensions.glacierbkuploader.metadata.BackupMetadataStore;
import com.amazonaws.services.glacier.transfer.UploadResult;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Daniel
 */
public class Uploader {

    @Inject
    private BackupMetadataStore store;
    
    @Inject
    private GlacierManager glacierManager;
    
    @Inject
    private Configuration configuration;
    
    public void executeBackup(){
        sendActualBackupToGlacier();
        deleteOldBackups();
    }
    
    private void sendActualBackupToGlacier(){
        BackupMetadata backupMetadata = new BackupMetadata();
        backupMetadata.setLocalFilePath(configuration.filePath());
        backupMetadata.setVaultName(configuration.vaultName());
        backupMetadata.setDescription(configuration.fileDescription());
        
        store.save(backupMetadata);
        UploadResult uploadFile = glacierManager.uploadFile(backupMetadata);
        
        backupMetadata.setArchiveId(uploadFile.getArchiveId());
        backupMetadata.setStatus(BackupMetadata.Status.UPLOADED);
        store.update(backupMetadata);
        
        System.out.println("Archive ID: " + backupMetadata.getArchiveId());
    }

    private void deleteOldBackups() {
        Date fechaLimite = calcularFechaLimiteBackups();
        List<BackupMetadata> backupsToDelete = store.findOldBackupsToDelete(fechaLimite);
        backupsToDelete.forEach(b -> {
            glacierManager.deleteFile(b);
            b.setStatus(BackupMetadata.Status.DELETED);
            store.update(b);
        });
    }

    private Date calcularFechaLimiteBackups() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,  -configuration.daysToDeleteBk());
        return c.getTime();
    }

}
