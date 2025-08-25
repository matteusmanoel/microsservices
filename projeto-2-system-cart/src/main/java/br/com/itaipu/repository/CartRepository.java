package br.com.itaipu.repository;

import br.com.itaipu.entity.CartEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartRepository implements PanacheRepository<CartEntity> {
    
    public CartEntity findByCartId(String cartId) {
        return find("cartId", cartId).firstResult();
    }
    
    public boolean existsByCartId(String cartId) {
        return count("cartId", cartId) > 0;
    }
} 