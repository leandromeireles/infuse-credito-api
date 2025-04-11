package com.infuse.credito.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsultaCreditoPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ConsultaCreditoPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarConsulta(String numeroCredito) {
        String mensagem = "Consulta realizada para crédito: " + numeroCredito;
        kafkaTemplate.send("auditoria-creditos", mensagem);
        System.out.println("Evento Kafka publicado: " + mensagem);
    }
}
