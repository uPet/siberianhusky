/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader.glacier;

import co.upet.extensions.glacierbkuploader.Configuration;
import co.upet.extensions.glacierbkuploader.metadata.BackupMetadata;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.DeliveryMode;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.model.DeleteArchiveRequest;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.UploadResult;
import java.io.File;
import javax.inject.Inject;

/**
 *
 * @author Daniel
 */
public class GlacierManager {
    
    private final AmazonGlacierClient glacierClient;
    private final ArchiveTransferManager transferManager;

    @Inject
    public GlacierManager(Configuration conf) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(conf.awsAccessKey(), conf.awsSecretKey());
        glacierClient = new AmazonGlacierClient(awsCreds);
        glacierClient.setEndpoint(conf.glacierEndPoint());
        transferManager = new ArchiveTransferManager(glacierClient, awsCreds);
    }
    
    public UploadResult uploadFile(BackupMetadata metadata){
        return transferManager.upload("-", metadata.getVaultName(), metadata.getDescription(), new File(metadata.getLocalFilePath()), new Listener());
    }    

    public void deleteFile(BackupMetadata metadata) {
        final DeleteArchiveRequest request = new DeleteArchiveRequest("-", metadata.getVaultName(), metadata.getArchiveId());
        glacierClient.deleteArchive(request);
    }
    
    class Listener implements ProgressListener, DeliveryMode {

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            if(progressEvent.getEventType() != ProgressEventType.REQUEST_BYTE_TRANSFER_EVENT)
                System.out.println("Progress: " + progressEvent.toString());
        }

        @Override
        public boolean isSyncCallSafe() {
            return true;
        }
        
    }
}
