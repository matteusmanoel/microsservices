package br.com.itaipu.client;

import br.com.itaipu.model.CurrencyQuote;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/currency")
@RegisterRestClient(configKey = "currency-api")
public interface CurrencyApiClient {

    @GET
    @Path("/quote/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    CurrencyQuote getQuote(@PathParam("from") String from, @PathParam("to") String to);
} 