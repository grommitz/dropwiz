package com.grommitz.dropwiz;

import javax.inject.Inject;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.grommitz.dropwiz.resources.HelloWorldResource;
import com.grommitz.dropwiz.health.TemplateHealthCheck;
import com.hubspot.dropwizard.guice.GuiceBundle;

public class App extends Application<AppConfig> {

	//	@Inject NameService service;
	//	@Inject HelloWorldResource resource;

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<AppConfig> bootstrap) {
		System.out.println("Initialised app for x"); // + service.getName());
		GuiceBundle<AppConfig> guiceBundle = GuiceBundle.<AppConfig>newBuilder()
				.addModule(new TheGuice())
				.setConfigClass(AppConfig.class)
				.enableAutoConfig(getClass().getPackage().getName())
				.build();
		bootstrap.addBundle(guiceBundle);
	}

	@Override
	public void run(AppConfig configuration, Environment environment) {
		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
	}

}
