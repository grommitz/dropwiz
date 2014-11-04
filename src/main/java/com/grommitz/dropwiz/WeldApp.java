package com.grommitz.dropwiz;

import javax.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class WeldApp {
	
	@Inject NameService service;

	public static void main(String[] args) {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		WeldApp app = container.instance().select(WeldApp.class).get();
		app.run();
		weld.shutdown();
	}

	public void run() {
		System.out.println("HELLO " + service.getName());
	}
	
}
