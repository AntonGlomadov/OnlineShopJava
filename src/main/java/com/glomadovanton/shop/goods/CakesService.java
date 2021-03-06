package com.glomadovanton.shop.goods;

import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.cake.Cakes;

public interface CakesService {
     Cakes getCakes();
     CakeFullInf getCake(Long id);
     CakeEntity getCakeEntity(Long id);
     Long addCake(CakeFullInf cake);
     void deleteCake(Long id);
}
