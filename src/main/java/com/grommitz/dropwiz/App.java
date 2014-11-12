package com.grommitz.dropwiz;

import javax.inject.Inject;

//import org.jboss.weld.environment.se.Weld;
//import org.jboss.weld.environment.se.WeldContainer;



import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.grommitz.dropwiz.resources.HelloWorldResource;
import com.grommitz.dropwiz.health.TemplateHealthCheck;

public class App extends Application<AppConfig> {

	@Inject NameService service;
	@Inject @ProducedByMe HelloWorldResource resource;

	public static void main(String[] args) throws Exception {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		App app = container.instance().select(App.class).get();	    
		//App app = new App();
		app.run(args);
		weld.shutdown();
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<AppConfig> bootstrap) {
		System.out.println("Initialised app for " + service.getName());
	}
	
	@Override
	public void run(AppConfig configuration, Environment environment) {
//		final HelloWorldResource resource = new HelloWorldResource(
//				configuration.getTemplate(),
//				configuration.getDefaultName());
		environment.jersey().register(resource);

		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
	}

}
