package br.com.pag.service.order.service;

import br.com.pag.service.order.exception.ProductNotFoundByIdException;
import br.com.pag.service.order.model.Produto;
import br.com.pag.service.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = { "product, products" })
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @CacheEvict(value ="products", allEntries = true)
    @Transactional
    public Produto create(Produto product) {
        return repository.save(product);
    }

    @Transactional
    @CachePut(value="product", key = "#product.id")
    @CacheEvict(value ="products", allEntries = true)
    public Produto update(Produto product) {
        return repository.findById(product.getId())
            .map(c -> {
                c.setNome(product.getNome())
                    .setPrecoSugerido(product.getPrecoSugerido());
                return repository.save(c);
            })
            .orElseThrow(() -> new ProductNotFoundByIdException(product.getId()));
    }

    @Cacheable("products")
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Cacheable(value ="product", key = "#productId", unless="#result == null")
    public Optional<Produto> findById(Long productId) {
        return repository.findById(productId);
    }
}
