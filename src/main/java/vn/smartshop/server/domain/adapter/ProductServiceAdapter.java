package vn.smartshop.server.domain.adapter;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.smartshop.server.domain.port.ProductService;
import vn.smartshop.server.infra.repo.ProductRepository;
import vn.smartshop.server.infra.util.CommonUtil;
import vn.smartshop.server.model.dto.response.BusinessException;
import vn.smartshop.server.model.entity.Product;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceAdapter implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new BusinessException("product "+id +" notfound"));
    }

    @Override
    public Page<Product> findByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category,pageable);
    }

    @Override
    public Page<Product> findByBrand(String brand, Pageable pageable) {
        return productRepository.findByBrand(brand,pageable);
    }

    @Override
    public Page<Product> findByKey(String key, Pageable pageable) {
        if (CommonUtil.isNull(key)) return productRepository.findAll(pageable);
        return productRepository.findByKey(key,pageable);
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(()->new BusinessException("product "+code +" notfound"));
    }
}
