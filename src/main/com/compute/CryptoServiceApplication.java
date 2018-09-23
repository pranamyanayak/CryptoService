package com.compute;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compute.config.CryptoModule;
import com.compute.controller.ComputeServiceController;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;
 
public class CryptoServiceApplication extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoServiceApplication.class);
 
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    	GuiceBundle<Configuration> guiceBundle = GuiceBundle.<Configuration>newBuilder()
				.addModule(new CryptoModule())
				.enableAutoConfig(getClass().getPackage().getName())
				.setConfigClass(Configuration.class)
				.build();

		b.addBundle(guiceBundle);
    }
 
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Starting CryptoService application");
        e.jersey().register(new ComputeServiceController());
    }
 
    public static void main(String[] args) throws Exception {
    	new CryptoServiceApplication().run(args);
    }
}
