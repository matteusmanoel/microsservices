package br.com.itaipu.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity extends PanacheEntity {
    
    @Column(unique = true, nullable = false)
    public String cartId;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<CartItemEntity> items = new ArrayList<>();
    
    @Column(nullable = false)
    public BigDecimal totalPrice = BigDecimal.ZERO;
    
    @Column(nullable = false)
    public String defaultCurrency = "BRL";
    
    @Column(nullable = false)
    public LocalDateTime createdAt;
    
    @Column(nullable = false)
    public LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 