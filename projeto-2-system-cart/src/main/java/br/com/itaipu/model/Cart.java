package br.com.itaipu.model;

import java.math.BigDecimal;
import java.util.List;

public record Cart(
    String id,
    List<CartItem> items,
    BigDecimal totalPrice,
    String defaultCurrency,
    List<CartTotal> totalsInOtherCurrencies
) {} 