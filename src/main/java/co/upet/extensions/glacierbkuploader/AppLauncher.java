/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Daniel
 */
public class AppLauncher {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Configuration file Path is required!");
        } else {
            Uploader uploader = uploaderWithConf(readConfigurationFile(args[0]));
            uploader.executeBackup();
        }
    }

    private static Uploader uploaderWithConf(Configuration conf) {
        Injector injector = Guice.createInjector(new AppModule(conf));
        return injector.getInstance(Uploader.class);
    }

    private static Configuration readConfigurationFile(String path) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(path), Configuration.class);
    }
}
