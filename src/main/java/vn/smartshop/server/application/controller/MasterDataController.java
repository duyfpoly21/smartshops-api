package vn.smartshop.server.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.smartshop.server.domain.port.MasterDataService;
import vn.smartshop.server.infra.util.ResponseUtil;
import vn.smartshop.server.model.dto.response.Response;

@RestController
@RequestMapping("/api/master")
@AllArgsConstructor
public class MasterDataController {
    private final MasterDataService masterDataService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveProduct(@RequestBody Object data, @RequestParam String key){
        masterDataService.save(data,key);
        return ResponseUtil.success(null);
    }

    @GetMapping("/get-by")
    public ResponseEntity<Response> getByKey(@RequestParam String key) throws Exception {
        Object data = masterDataService.getByCode(key,Object.class);
        return ResponseUtil.success(data);
    }
}
