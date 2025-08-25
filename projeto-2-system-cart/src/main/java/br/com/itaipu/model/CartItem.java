package br.com.itaipu.model;

import java.math.BigDecimal;

public record CartItem(
    Long productId,
    String productName,
    Integer quantity,
    BigDecimal unitPrice,
    BigDecimal totalPrice,
    String currency
) {} 