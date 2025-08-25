package br.com.itaipu.model;

import java.math.BigDecimal;

public record Product(
    Long id,
    String name,
    String description,
    BigDecimal price,
    String category,
    Integer stock
) {} 