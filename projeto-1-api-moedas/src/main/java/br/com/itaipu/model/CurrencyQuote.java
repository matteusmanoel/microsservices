package br.com.itaipu.model;

import java.math.BigDecimal;

public record CurrencyQuote(
    String code,        // Código da moeda base (ex: USD, EUR, BRL)
    String codein,      // Código da moeda de destino/conversão
    String name,        // Nome descritivo do par de moedas
    BigDecimal high,    // Maior valor da cotação no período
    BigDecimal low,     // Menor valor da cotação no período
    BigDecimal varBid,  // Variação do valor de compra
    BigDecimal pctChange, // Variação percentual da cotação
    BigDecimal bid,     // Valor de compra da moeda
    BigDecimal ask,     // Valor de venda da moeda
    Long timestamp,     // Momento da cotação em Unix timestamp
    Long createDate     // Data de criação do registro (pode ser null)
) {} 