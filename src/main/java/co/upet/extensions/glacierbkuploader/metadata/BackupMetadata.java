/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader.metadata;

import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


/**
 *
 * @author Daniel
 */
@Entity("GlacierBackupMetadata")
public class BackupMetadata {
    
    public enum Status {UPLOADED, UPLOADING, DELETED}
    
    @Id
    private ObjectId id;
    private Date backupDate;
    private String archiveId;
    private String description;
    private Status status;
    private String localFilePath;
    private String vaultName;

    public BackupMetadata() {
        backupDate = new Date();
        status = Status.UPLOADING;
    }
    
    
    
    public Date getBackupDate() {
        return backupDate;
    }

    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getVaultName() {
        return vaultName;
    }

    public void setVaultName(String vaultName) {
        this.vaultName = vaultName;
    }
    
    
    
    
    
}
