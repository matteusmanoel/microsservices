package br.com.itaipu.service;

import br.com.itaipu.client.AwesomeApiClient;
import br.com.itaipu.model.CurrencyQuote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CurrencyService {

    @Inject
    @RestClient
    AwesomeApiClient awesomeApiClient;

    public CurrencyQuote getQuote(String from, String to) {
        try {
            List<CurrencyQuote> quotes = awesomeApiClient.getQuote(from, to);
            if (quotes.isEmpty()) {
                throw new WebApplicationException("Cotação não encontrada", 404);
            }
            return quotes.get(0);
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao obter cotação: " + e.getMessage(), 500);
        }
    }

    public List<CurrencyQuote> getMultipleQuotes(String base, String currencies) {
        try {
            List<String> currencyList = Arrays.asList(currencies.split(","));
            return currencyList.stream()
                    .map(currency -> getQuote(base, currency.trim()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao obter múltiplas cotações: " + e.getMessage(), 500);
        }
    }

    public List<String> getAvailableCurrencies() {
        return List.of("USD", "EUR", "BRL", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "BTC", "ETH");
    }
} 