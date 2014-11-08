package com.grommitz.dropwiz;

import io.dropwizard.setup.Environment;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

import javax.inject.Named;

public class TheGuice implements Module {

	//@Override
	public void configure(Binder binder) {
	}

	@Provides
	@Named("defaultName")
	public String provideDefaultName(AppConfig config) {
		return config.getDefaultName();
	}
	
	@Provides
	@Named("template")
	public String provideTemplate(AppConfig config) {
		return config.getTemplate();
	}
	
	@Provides
	public NameService provideNameService(Environment e) {
		return new NameService();
	}
	
}
