package com.abnamro.assessment;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

/**
 * MyApplicationBean
 */
@Singleton
public class MyApplicationBean {

    @Inject
    @RestClient
    PersonResource personResource;

    public void printHello(@Observes ContainerInitialized event, @Parameters List<String> parameters) {
        if (parameters.isEmpty()) {
            return;
        }

        switch (parameters.get(0)) {
            case "add":
                    personResource.addPerson(Json.createObjectBuilder()
                        .add("name", parameters.get(1))
                        .add("birthDate", parameters.get(2)).build());
        }

        String response = personResource.getPersons();
        System.out.println("persons: " + response);
    }

    @Path("/")
    @RegisterRestClient(baseUri = "http://localhost:8080")
    public interface PersonResource {

        @GET
        public String getPersons();

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public String addPerson(JsonObject p);
    }

}
