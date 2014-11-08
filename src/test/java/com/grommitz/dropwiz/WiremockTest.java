package com.grommitz.dropwiz;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class WiremockTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080

	@Test
	public void exampleTest() {
		stubFor(get(urlEqualTo("/my/resource"))
				.withHeader("Accept", equalTo("text/xml"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "text/xml")
						.withBody("<response>noodles burger cakes strudles</response>")));

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8089").path("/my/resource");

		Response response = target.request(MediaType.TEXT_XML)
				.get();
		
		String r = response.getEntity().toString();
		assertThat(r, is("noodles etc"));

	}

}
