package com.compute.config;

import com.compute.crypto.CryptoProvider;
import com.compute.service.ComputeService;
import com.compute.service.ComputeServiceImpl;
import com.google.inject.AbstractModule;

public class CryptoModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(ComputeService.class).to(ComputeServiceImpl.class);
	}

}
