package com.infuse.credito.service;

import com.infuse.credito.model.Credito;
import com.infuse.credito.repository.CreditoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditoService {

    private final CreditoRepository repository;
    private final ConsultaCreditoPublisher publisher;


    public CreditoService(CreditoRepository repository, ConsultaCreditoPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public List<Credito> consultarPorNfse(String numeroNfse) {
        List<Credito> creditos = repository.findByNumeroNfse(numeroNfse);
        // Publica uma mensagem para cada crédito retornado
        creditos.forEach(credito -> publisher.publicarConsulta(credito.getNumeroCredito()));
        return creditos;
    }

    public Optional<Credito> consultarPorNumeroCredito(String numeroCredito) {
        Optional<Credito> credito = repository.findByNumeroCredito(numeroCredito);
        // Publica se encontrar
        credito.ifPresent(c -> publisher.publicarConsulta(numeroCredito));

        return credito;
    }
}
