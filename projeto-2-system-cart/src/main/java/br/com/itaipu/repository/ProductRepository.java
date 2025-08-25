package br.com.itaipu.repository;

import br.com.itaipu.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductEntity> {
    
    public List<ProductEntity> findByCategory(String category) {
        return find("category", category).list();
    }
    
    public List<ProductEntity> findByNameContaining(String name) {
        return find("name like ?1", "%" + name + "%").list();
    }
} 