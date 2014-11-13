package com.grommitz.dropwiz;

import javax.enterprise.inject.Produces;

import com.grommitz.dropwiz.resources.HelloWorldResource;

public class Producers {

	@Produces
	@ProducedByMe
	public HelloWorldResource produceResource() {
		HelloWorldResource resource = new HelloWorldResource("poohead", new NameService());
		return resource;
	}
	
	@Produces
	@ProducedByMe
	String produceDefaultName() {
		return "BORIS";
	}
}
