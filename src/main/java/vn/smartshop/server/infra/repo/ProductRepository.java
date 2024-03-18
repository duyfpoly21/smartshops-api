package vn.smartshop.server.infra.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.smartshop.server.model.entity.MasterData;
import vn.smartshop.server.model.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
    Page<Product> findByBrand(String code, Pageable pageable);

    Page<Product> findByCategory(String code, Pageable pageable);

    @Query("select p from Product p where " +
            "(:key = null or p.brand = :key)" +
             "or (:key = null or p.category = :key)" +
            "or (:key = null or p.name = :key)" +
            "or (:key = null or p.code = :key)" +
            "or (:key = null or p.price = :key)"
    )
    Page<Product> findByKey(@Param("key") String key, Pageable pageable);
}
