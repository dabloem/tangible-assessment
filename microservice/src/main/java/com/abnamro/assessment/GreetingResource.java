package com.abnamro.assessment;

import java.util.Random;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;

@Path("/hello")
public class GreetingResource {


    @GET
    @Retry(maxRetries = 4)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Random r = new Random();
        if (r.nextBoolean()) {
            System.out.println("error");
            Logger.getLogger("greeting").info("error");
            throw new RuntimeException("random error");
        }
        return "hello";
    }
}