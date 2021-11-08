package com.glomadovanton.shop.goods;

import com.glomadovanton.shop.rest.dto.Cake;
import com.glomadovanton.shop.rest.dto.CakeFullInf;
import com.glomadovanton.shop.rest.dto.Cakes;

public interface CakesService {
     Cakes getCakes();
     CakeFullInf getCake(Long id);
}
