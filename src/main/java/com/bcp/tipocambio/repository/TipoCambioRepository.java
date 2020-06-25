package com.bcp.tipocambio.repository;

import com.bcp.tipocambio.models.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {

    public Optional<TipoCambio> findByOrigenAndDestino(String origen,String destino);
}
