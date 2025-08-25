package br.com.itaipu.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends PanacheEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    public CartEntity cart;
    
    @Column(nullable = false)
    public Long productId;
    
    @Column(nullable = false)
    public String productName;
    
    @Column(nullable = false)
    public Integer quantity;
    
    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal unitPrice;
    
    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal totalPrice;
    
    @Column(nullable = false)
    public String currency = "BRL";
} 