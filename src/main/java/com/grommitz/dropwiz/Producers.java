package com.grommitz.dropwiz;

import javax.enterprise.inject.Produces;

import com.grommitz.dropwiz.resources.HelloWorldResource;

public class Producers {

	@Produces
	public HelloWorldResource produceResource() {
		HelloWorldResource resource = new HelloWorldResource(
				"Hi %s",
				"wierdo");
		return resource;
	}
	
}
