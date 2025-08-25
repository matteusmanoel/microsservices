package br.com.itaipu.model;

import java.math.BigDecimal;

public record CartTotal(
    String currency,
    BigDecimal total
) {} 