package vn.smartshop.server.domain.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.smartshop.server.model.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);

    Product findById(Long id);

    Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findByBrand(String category, Pageable pageable);

    Page<Product> findByKey(String key,Pageable pageable);

    Product findByCode(String code);
}
