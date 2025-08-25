package br.com.itaipu.service;

import br.com.itaipu.client.CurrencyApiClient;
import br.com.itaipu.entity.CartEntity;
import br.com.itaipu.entity.CartItemEntity;
import br.com.itaipu.entity.ProductEntity;
import br.com.itaipu.model.Cart;
import br.com.itaipu.model.CartItem;
import br.com.itaipu.model.CartTotal;
import br.com.itaipu.model.CurrencyQuote;
import br.com.itaipu.repository.CartRepository;
import br.com.itaipu.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CartService {

    @Inject
    CartRepository cartRepository;
    
    @Inject
    ProductRepository productRepository;
    
    @Inject
    @RestClient
    CurrencyApiClient currencyApiClient;

    @Transactional
    public Cart createCart() {
        String cartId = UUID.randomUUID().toString();
        CartEntity cartEntity = new CartEntity();
        cartEntity.cartId = cartId;
        cartEntity.totalPrice = BigDecimal.ZERO;
        cartEntity.defaultCurrency = "BRL";
        
        cartRepository.persist(cartEntity);
        
        return convertToCart(cartEntity);
    }

    public Cart getCart(String cartId) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if (cartEntity == null) {
            throw new WebApplicationException("Carrinho não encontrado", 404);
        }
        
        return convertToCart(cartEntity);
    }

    @Transactional
    public Cart addItem(String cartId, Long productId, Integer quantity) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if (cartEntity == null) {
            throw new WebApplicationException("Carrinho não encontrado", 404);
        }
        
        ProductEntity product = productRepository.findById(productId);
        if (product == null) {
            throw new WebApplicationException("Produto não encontrado", 404);
        }
        
        if (product.stock < quantity) {
            throw new WebApplicationException("Estoque insuficiente", 400);
        }
        
        // Verificar se o item já existe no carrinho
        CartItemEntity existingItem = cartEntity.items.stream()
                .filter(item -> item.productId.equals(productId))
                .findFirst()
                .orElse(null);
        
        if (existingItem != null) {
            existingItem.quantity += quantity;
            existingItem.totalPrice = existingItem.unitPrice.multiply(BigDecimal.valueOf(existingItem.quantity));
        } else {
            CartItemEntity newItem = new CartItemEntity();
            newItem.cart = cartEntity;
            newItem.productId = productId;
            newItem.productName = product.name;
            newItem.quantity = quantity;
            newItem.unitPrice = product.price;
            newItem.totalPrice = product.price.multiply(BigDecimal.valueOf(quantity));
            newItem.currency = product.currency;
            
            cartEntity.items.add(newItem);
        }
        
        // Recalcular total
        recalculateTotal(cartEntity);
        
        return convertToCart(cartEntity);
    }

    @Transactional
    public Cart removeItem(String cartId, Long productId) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if (cartEntity == null) {
            throw new WebApplicationException("Carrinho não encontrado", 404);
        }
        
        cartEntity.items.removeIf(item -> item.productId.equals(productId));
        recalculateTotal(cartEntity);
        
        return convertToCart(cartEntity);
    }

    @Transactional
    public Cart updateItemQuantity(String cartId, Long productId, Integer quantity) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if (cartEntity == null) {
            throw new WebApplicationException("Carrinho não encontrado", 404);
        }
        
        CartItemEntity item = cartEntity.items.stream()
                .filter(i -> i.productId.equals(productId))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Item não encontrado no carrinho", 404));
        
        if (quantity <= 0) {
            cartEntity.items.remove(item);
        } else {
            ProductEntity product = productRepository.findById(productId);
            if (product.stock < quantity) {
                throw new WebApplicationException("Estoque insuficiente", 400);
            }
            
            item.quantity = quantity;
            item.totalPrice = item.unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
        
        recalculateTotal(cartEntity);
        
        return convertToCart(cartEntity);
    }

    @Transactional
    public Cart clearCart(String cartId) {
        CartEntity cartEntity = cartRepository.findByCartId(cartId);
        if (cartEntity == null) {
            throw new WebApplicationException("Carrinho não encontrado", 404);
        }
        
        cartEntity.items.clear();
        cartEntity.totalPrice = BigDecimal.ZERO;
        
        return convertToCart(cartEntity);
    }

    private void recalculateTotal(CartEntity cartEntity) {
        BigDecimal total = cartEntity.items.stream()
                .map(item -> item.totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        cartEntity.totalPrice = total;
    }

    private Cart convertToCart(CartEntity cartEntity) {
        List<CartItem> items = cartEntity.items.stream()
                .map(this::convertToCartItem)
                .collect(Collectors.toList());
        
        List<CartTotal> totalsInOtherCurrencies = getTotalsInOtherCurrencies(cartEntity);
        
        return new Cart(
                cartEntity.cartId,
                items,
                cartEntity.totalPrice,
                cartEntity.defaultCurrency,
                totalsInOtherCurrencies
        );
    }

    private CartItem convertToCartItem(CartItemEntity itemEntity) {
        return new CartItem(
                itemEntity.productId,
                itemEntity.productName,
                itemEntity.quantity,
                itemEntity.unitPrice,
                itemEntity.totalPrice,
                itemEntity.currency
        );
    }

    private List<CartTotal> getTotalsInOtherCurrencies(CartEntity cartEntity) {
        List<CartTotal> totals = new java.util.ArrayList<>();
        
        // Adicionar conversões para USD e EUR
        String[] currencies = {"USD", "EUR"};
        
        for (String currency : currencies) {
            if (!currency.equals(cartEntity.defaultCurrency)) {
                try {
                    CurrencyQuote quote = currencyApiClient.getQuote(cartEntity.defaultCurrency, currency);
                    BigDecimal convertedTotal = cartEntity.totalPrice.multiply(quote.bid());
                    totals.add(new CartTotal(currency, convertedTotal));
                } catch (Exception e) {
                    // Se a conversão falhar, não adicionar essa moeda
                }
            }
        }
        
        return totals;
    }
} 