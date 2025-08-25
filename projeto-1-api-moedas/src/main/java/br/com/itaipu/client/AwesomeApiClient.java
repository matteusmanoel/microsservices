package br.com.itaipu.client;

import br.com.itaipu.model.CurrencyQuote;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/json")
@RegisterRestClient(configKey = "awesome-api")
public interface AwesomeApiClient {

    @GET
    @Path("/{from}-{to}")
    @Produces(MediaType.APPLICATION_JSON)
    List<CurrencyQuote> getQuote(@PathParam("from") String from, @PathParam("to") String to);
} 