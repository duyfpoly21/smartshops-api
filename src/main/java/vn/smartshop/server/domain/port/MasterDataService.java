package vn.smartshop.server.domain.port;

import vn.smartshop.server.model.entity.MasterData;

public interface MasterDataService {
    void save(Object masterData, String key);

    <T> T getByCode(String code,Class<T> clazz) throws Exception;
}
