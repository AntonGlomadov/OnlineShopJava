package com.glomadovanton.shop.rest.advice;

import com.glomadovanton.shop.goods.CakesService;
import com.glomadovanton.shop.rest.controller.CakeController;
import com.glomadovanton.shop.rest.dto.CakeFullInf;
import com.glomadovanton.shop.rest.dto.Cakes;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

public class CakeControllerTest {

    @Test
    void testCakes (){
        Cakes cakes = new Cakes();
        CakesService cakesService = Mockito.mock(CakesService.class);
        Mockito.doReturn(cakes).when(cakesService).getCakes();
        CakeController cakeController = new CakeController(cakesService);
        Cakes cakesTest = cakeController.cakes();
        AssertionErrors.assertEquals("",cakesTest,cakes);
        Mockito.verify(cakesService,Mockito.times(1)).getCakes();
    }

    @Test
    void testByIdCakes (){
        CakeFullInf cakeFullInf = new CakeFullInf();
        CakesService cakesService = Mockito.mock(CakesService.class);
        Mockito.doReturn(cakeFullInf).when(cakesService).getCake(any());
        CakeController cakeController = new CakeController(cakesService);
        CakeFullInf cakeTest = cakeController.getCakeById(1L);
        AssertionErrors.assertEquals("",cakeTest,cakeFullInf);
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(cakesService,Mockito.times(1)).getCake(argumentCaptor.capture());
        AssertionErrors.assertEquals("",argumentCaptor.getValue(),1L);
    }
}
