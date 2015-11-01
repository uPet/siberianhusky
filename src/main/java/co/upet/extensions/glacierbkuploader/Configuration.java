/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader;
/**
 *
 * @author Daniel
 */
public class Configuration {
    
    private String filePath;
    private String fileDescription;
    private String vaultName;
    private int daysToDeleteBk;
    private String mongoHost;
    private int mongoPort;
    private String dataStore;
    private String awsAccessKey;
    private String awsSecretKey;
    private String glacierEndPoint;
    
    
    
    public String filePath(){
//        return "/data_ext/s3Backup/imagenes_upet.tar";
        return filePath;
    }
    
    public String fileDescription(){
        return fileDescription;
    }
    
    public String vaultName(){
        return vaultName;
    }

    public int daysToDeleteBk() {
        return daysToDeleteBk;
    }

    public String mongoHost() {
        return mongoHost;
    }

    public int mongoPort() {
        return mongoPort;
    }

    public String dataStore() {
        return dataStore;
    }

    public String awsAccessKey() {
        return awsAccessKey;
    }

    public String awsSecretKey() {
        return awsSecretKey;
    }

    public String glacierEndPoint() {
        return glacierEndPoint;
    }
    
}
