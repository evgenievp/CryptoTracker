package com.CryptoTracker.CryptoTracker.cryptoRepo;

import com.CryptoTracker.CryptoTracker.models.CryptoCurrencies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoRepository extends JpaRepository<CryptoCurrencies, Long> {
    List<CryptoCurrencies> findByName(String name);
}
