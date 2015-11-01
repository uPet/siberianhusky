/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.upet.extensions.glacierbkuploader;

import com.google.inject.AbstractModule;

/**
 *
 * @author Daniel
 */
public class AppModule extends AbstractModule{

    private final Configuration conf;
    
    public AppModule(Configuration conf) {
        this.conf = conf;
    }

    @Override
    protected void configure() {
        bind(Configuration.class).toInstance(conf);
    }
    
}
