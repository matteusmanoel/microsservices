package br.com.itaipu.service;

import br.com.itaipu.entity.ProductEntity;
import br.com.itaipu.model.Product;
import br.com.itaipu.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.listAll().stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id);
        if (productEntity == null) {
            throw new WebApplicationException("Produto não encontrado", 404);
        }
        return convertToProduct(productEntity);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContaining(name).stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.name = product.name();
        productEntity.description = product.description();
        productEntity.price = product.price();
        productEntity.category = product.category();
        productEntity.stock = product.stock();
        productEntity.currency = "BRL";
        
        productRepository.persist(productEntity);
        
        return convertToProduct(productEntity);
    }

    public Product updateProduct(Long id, Product product) {
        ProductEntity productEntity = productRepository.findById(id);
        if (productEntity == null) {
            throw new WebApplicationException("Produto não encontrado", 404);
        }
        
        productEntity.name = product.name();
        productEntity.description = product.description();
        productEntity.price = product.price();
        productEntity.category = product.category();
        productEntity.stock = product.stock();
        
        return convertToProduct(productEntity);
    }

    public void deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id);
        if (productEntity == null) {
            throw new WebApplicationException("Produto não encontrado", 404);
        }
        
        productRepository.delete(productEntity);
    }

    public void updateStock(Long productId, Integer quantity) {
        ProductEntity productEntity = productRepository.findById(productId);
        if (productEntity == null) {
            throw new WebApplicationException("Produto não encontrado", 404);
        }
        
        productEntity.stock -= quantity;
        if (productEntity.stock < 0) {
            throw new WebApplicationException("Estoque insuficiente", 400);
        }
    }

    private Product convertToProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.id,
                productEntity.name,
                productEntity.description,
                productEntity.price,
                productEntity.category,
                productEntity.stock
        );
    }
} 