/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader.metadata;

import co.upet.extensions.glacierbkuploader.Configuration;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author Daniel
 */
public class BackupMetadataStore {

    private final MongoClient mongoClient;
    private final Morphia morphia = new Morphia();
    private final Datastore dataStore;

    @Inject
    public BackupMetadataStore(Configuration conf) {
        try {
            mongoClient = new MongoClient(conf.mongoHost(), conf.mongoPort());
            morphia.map(BackupMetadata.class);
            dataStore = morphia.createDatastore(mongoClient, conf.dataStore());
            dataStore.ensureIndexes();
        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        }
    }

    Datastore getDataStore() {
        return dataStore;
    }

    public void save(BackupMetadata item) {
        getDataStore().save(item);
    }

    public void update(BackupMetadata item) {
        getDataStore().merge(item);
    }

    public List<BackupMetadata> findOldBackupsToDelete(Date fechaLimite) {
        return getDataStore().createQuery(BackupMetadata.class)
                .field("backupDate").lessThan(fechaLimite)
                .field("status").equal(BackupMetadata.Status.UPLOADED)
                .asList();
    }
}
