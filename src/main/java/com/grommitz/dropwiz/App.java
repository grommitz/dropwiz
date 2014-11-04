package com.grommitz.dropwiz;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.grommitz.dropwiz.resources.HelloWorldResource;
import com.grommitz.dropwiz.health.TemplateHealthCheck;

public class App extends Application<AppConfig> {

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<AppConfig> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(AppConfig configuration,
			Environment environment) {
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(),
				configuration.getDefaultName());
		environment.jersey().register(resource);

		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

	}

}
