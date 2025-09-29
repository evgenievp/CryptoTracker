package com.CryptoTracker.CryptoTracker.services;

import com.CryptoTracker.CryptoTracker.cryptoRepo.CryptoRepository;
import com.CryptoTracker.CryptoTracker.models.CryptoCurrencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CryptoService {
    @Autowired
    private RestTemplate restTemplate;
    private CryptoRepository repo;
    private final EmailService emailService;


    @Autowired
    public CryptoService(CryptoRepository repo, RestTemplate restTemplate, EmailService emailService) {
        this.repo = repo;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    public List<CryptoCurrencies> findAllCrypto() {
        return repo.findAll();
    }

    public List<CryptoCurrencies> getFileCoin(String name) {
        return repo.findByName(name);
    }

    public void updatePrices() {
        String filecoinUrl = "https://api.coingecko.com/api/v3/simple/price?ids=filecoin&vs_currencies=usd";

        ResponseEntity<Map<String, Map<String, Double>>> response =
                restTemplate.exchange(
                        filecoinUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {}
                );

        Map<String, Map<String, Double>> filecoinResponse = response.getBody();

        if (filecoinResponse != null && filecoinResponse.containsKey("filecoin")) {
            double price = filecoinResponse.get("filecoin").get("usd");

            CryptoCurrencies crypto = new CryptoCurrencies();
            crypto.setName("filecoin");
            crypto.setPrice(price);
            crypto.setTimestamp(LocalDateTime.now());

            repo.save(crypto);

            double threshold = 0.1;
            if (price > threshold) {
                emailService.sendAlert(
                        "мой.мейл@gmail.com",
                        "Filecoin Alert 🚨",
                        "Цената на Filecoin е " + price + " USD (над прага " + threshold + ")"
                );
            }
        } else {
            System.out.println("⚠️ API не върна данни за filecoin!");
        }
    }



}
