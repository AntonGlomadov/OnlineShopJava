package com.glomadovanton.shop.goods;

import com.glomadovanton.shop.exception.CakeNotFoundException;
import com.glomadovanton.shop.rest.dto.cake.Cake;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.cake.Cakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakesServiceImpl implements CakesService {

    private final CakeRepository cakeRepository;

    @Autowired
    public CakesServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public Cakes getCakes(){
        List<CakeEntity> cakeEntityList = cakeRepository.findAll();
        List<Cake> cakeList = cakeEntityList.stream().map(c -> {
            Cake cake = new Cake();
            cake.setId(c.getId());
            cake.setCalories(c.getCalories());
            cake.setName(c.getName());
            cake.setImage(c.getImage());
            cake.setPrice(c.getPrice());
            cake.setWeight(c.getWeight());
            return cake;
        }).collect(Collectors.toList());
        Cakes cakes = new Cakes();
        cakes.setCakeList(cakeList);
        return cakes;
    }

    @Override
    public CakeFullInf getCake(Long id) {
        return  cakeRepository.findById(id)
                .map(cakeEntity -> {
                    CakeFullInf cake = new CakeFullInf();
                    cake.setId(cakeEntity.getId());
                    cake.setCalories(cakeEntity.getCalories());
                    cake.setName(cakeEntity.getName());
                    cake.setImage(cakeEntity.getImage());
                    cake.setPrice(cakeEntity.getPrice());
                    cake.setWeight(cakeEntity.getWeight());
                    cake.setStorageConditions(cakeEntity.getStorageConditions());
                    cake.setCompositions(cakeEntity.getCompositions());
                    return cake;
                })
                .orElseThrow(() -> new CakeNotFoundException("No such cake"));
    }

    @Override
    public CakeEntity getCakeEntity(Long id) {
        return cakeRepository.findById(id).get();
    }

    @Override
    public void addCake(CakeFullInf cake){
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setCalories(cake.getCalories());
        cakeEntity.setImage(cake.getImage());
        cakeEntity.setCompositions(cake.getCompositions());
        cakeEntity.setName(cake.getName());
        cakeEntity.setPrice(cake.getPrice());
        cakeEntity.setWeight(cake.getWeight());
        cakeEntity.setStorageConditions(cake.getStorageConditions());
        cakeRepository.save(cakeEntity);
    }

    @Override
    public void deleteCake(Long id) {
        cakeRepository.deleteById(id);
    }
}
