package com.CryptoTracker.CryptoTracker.controllers;

import com.CryptoTracker.CryptoTracker.models.CryptoCurrencies;
import com.CryptoTracker.CryptoTracker.services.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class CryptoController {
    @Autowired
    private CryptoService service;


    @GetMapping("/findAll")
    public List<CryptoCurrencies> getAllPrices() {
        return service.findAllCrypto();
    }

    @GetMapping("/findAll/{name}")
    public List<CryptoCurrencies> getAllByName(@PathVariable String name) {
        return service.getFileCoin(name);
    }

    @GetMapping("/update")
    public void update() {
        service.updatePrices();

    }
}
