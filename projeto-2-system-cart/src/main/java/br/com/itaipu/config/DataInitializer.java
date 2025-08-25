package br.com.itaipu.config;

import br.com.itaipu.entity.ProductEntity;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;

@ApplicationScoped
public class DataInitializer {

    @Inject
    EntityManager entityManager;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        // Verificar se já existem produtos
        Long count = (Long) entityManager.createQuery("SELECT COUNT(p) FROM ProductEntity p").getSingleResult();
        
        if (count == 0) {
            // Criar produtos de exemplo
            createSampleProducts();
        }
    }

    private void createSampleProducts() {
        // Produtos de Eletrônicos
        createProduct("iPhone 15", "Smartphone Apple com 128GB", new BigDecimal("5999.99"), "Eletrônicos", 50);
        createProduct("Samsung Galaxy S24", "Smartphone Samsung com 256GB", new BigDecimal("4999.99"), "Eletrônicos", 30);
        createProduct("MacBook Air M2", "Notebook Apple com chip M2", new BigDecimal("8999.99"), "Eletrônicos", 20);
        createProduct("Dell Inspiron 15", "Notebook Dell com Intel i7", new BigDecimal("3999.99"), "Eletrônicos", 25);
        
        // Produtos de Livros
        createProduct("Clean Code", "Livro sobre boas práticas de programação", new BigDecimal("89.90"), "Livros", 100);
        createProduct("Design Patterns", "Padrões de projeto em Java", new BigDecimal("79.90"), "Livros", 80);
        createProduct("Domain-Driven Design", "DDD na prática", new BigDecimal("99.90"), "Livros", 60);
        
        // Produtos de Casa
        createProduct("Smart TV 55\"", "TV Samsung 4K Smart", new BigDecimal("2999.99"), "Casa", 15);
        createProduct("Aspirador Robô", "Aspirador automático Xiaomi", new BigDecimal("899.99"), "Casa", 40);
        createProduct("Cafeteira Expresso", "Cafeteira automática", new BigDecimal("599.99"), "Casa", 35);
        
        // Produtos de Esporte
        createProduct("Tênis Nike Air Max", "Tênis esportivo confortável", new BigDecimal("399.99"), "Esporte", 70);
        createProduct("Bicicleta Mountain Bike", "Bicicleta para trilhas", new BigDecimal("1299.99"), "Esporte", 10);
        createProduct("Esteira Elétrica", "Esteira para exercícios em casa", new BigDecimal("2499.99"), "Esporte", 8);
    }

    private void createProduct(String name, String description, BigDecimal price, String category, Integer stock) {
        ProductEntity product = new ProductEntity();
        product.name = name;
        product.description = description;
        product.price = price;
        product.category = category;
        product.stock = stock;
        product.currency = "BRL";
        
        entityManager.persist(product);
    }
} 