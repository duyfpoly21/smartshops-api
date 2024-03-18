package vn.smartshop.server.domain.adapter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.smartshop.server.domain.port.MasterDataService;
import vn.smartshop.server.infra.repo.MasterDataRepository;
import vn.smartshop.server.infra.util.JsonUtil;
import vn.smartshop.server.model.dto.response.BusinessException;
import vn.smartshop.server.model.entity.MasterData;

@Service
@AllArgsConstructor
public class MasterDataServiceAdapter implements MasterDataService {
    private final MasterDataRepository masterDataRepository;

    @Override
    public void save(Object masterData,String key) {
        MasterData currentData = masterDataRepository.findByCode(key).orElse(new MasterData());
        currentData.setData(JsonUtil.writeValueAsString(masterData));
        currentData.setCode(key);
        masterDataRepository.save(currentData);
    }

    @Override
    public <T> T getByCode(String code,Class<T> clazz) throws Exception {
        MasterData masterData = masterDataRepository.findByCode(code).orElseThrow(()-> new BusinessException("masterData not found"));
        return JsonUtil.readValue(clazz,masterData.getData());
    }
}
