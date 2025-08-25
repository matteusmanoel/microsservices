package br.com.itaipu.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends PanacheEntity {
    
    @Column(nullable = false)
    public String name;
    
    @Column(columnDefinition = "TEXT")
    public String description;
    
    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal price;
    
    @Column(nullable = false)
    public String category;
    
    @Column(nullable = false)
    public Integer stock;
    
    @Column(nullable = false)
    public String currency = "BRL";
} 