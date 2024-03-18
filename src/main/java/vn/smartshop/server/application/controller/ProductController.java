package vn.smartshop.server.application.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.smartshop.server.domain.port.ProductService;
import vn.smartshop.server.infra.util.ResponseUtil;
import vn.smartshop.server.model.dto.response.Response;
import vn.smartshop.server.model.entity.Product;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveProduct(@RequestBody Product product){
        productService.save(product);
        return ResponseUtil.success(null);
    }

    @GetMapping("/get-by")
    public ResponseEntity<Response> getByKey(@RequestParam String key, Pageable pageable){
        return ResponseUtil.success(productService.findByKey(key,pageable));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Response> getByKey(@RequestParam Long id){
        return ResponseUtil.success(productService.findById(id));
    }
}
