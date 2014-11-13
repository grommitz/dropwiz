package com.grommitz.dropwiz.resources;

import com.google.common.base.Optional;
import com.grommitz.dropwiz.NameService;
import com.grommitz.dropwiz.ProducedByMe;
import com.grommitz.dropwiz.Saying;
import com.codahale.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;
	private NameService service;

	@Inject 
	public HelloWorldResource(@ProducedByMe String defaultName, NameService service) {
		this.template = "oi %s";
		this.defaultName = defaultName; // "default man";
		this.service = service;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		//final String value = String.format(template, name.or(defaultName));
		String value = "template = " + template;
		value += ", defaultName = " + defaultName;
		value += ", service = " + (service == null ? "null" : service.getName());
		return new Saying(counter.incrementAndGet(), value);
	}
}

