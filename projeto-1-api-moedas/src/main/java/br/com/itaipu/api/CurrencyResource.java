package br.com.itaipu.api;

import br.com.itaipu.service.CurrencyService;
import br.com.itaipu.model.CurrencyQuote;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/api/currency")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Currency API", description = "API para cotação de moedas")
public class CurrencyResource {

    @Inject
    CurrencyService currencyService;

    @GET
    @Path("/quote/{from}/{to}")
    @Operation(summary = "Obter cotação de moeda", description = "Retorna a cotação atual entre duas moedas")
    public Response getCurrencyQuote(@PathParam("from") String from, @PathParam("to") String to) {
        try {
            CurrencyQuote quote = currencyService.getQuote(from.toUpperCase(), to.toUpperCase());
            return Response.ok(quote).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao obter cotação: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/quotes/{base}")
    @Operation(summary = "Obter cotações de uma moeda base", description = "Retorna cotações para múltiplas moedas")
    public Response getMultipleQuotes(@PathParam("base") String base, @QueryParam("currencies") String currencies) {
        try {
            List<CurrencyQuote> quotes = currencyService.getMultipleQuotes(base.toUpperCase(), currencies);
            return Response.ok(quotes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao obter cotações: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/available")
    @Operation(summary = "Listar moedas disponíveis", description = "Retorna lista de moedas suportadas")
    public Response getAvailableCurrencies() {
        try {
            List<String> currencies = currencyService.getAvailableCurrencies();
            return Response.ok(currencies).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar moedas: " + e.getMessage())
                    .build();
        }
    }
} 