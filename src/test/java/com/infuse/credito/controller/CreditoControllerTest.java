package com.infuse.credito.controller;

import com.infuse.credito.model.Credito;
import com.infuse.credito.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class CreditoControllerTest {

    private CreditoService service;
    private CreditoController controller;

    @BeforeEach
    void setUp() {
        service = mock(CreditoService.class);
        controller = new CreditoController(service);
    }

    @Test
    void deveRetornarListaDeCreditosAoBuscarPorNfse() {
        String nfse = "123456";
        List<Credito> mockList = List.of(creditoMock());

        when(service.consultarPorNfse(nfse)).thenReturn(mockList);

        ResponseEntity<List<Credito>> response = controller.buscarPorNfse(nfse);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockList, response.getBody());
        verify(service, times(1)).consultarPorNfse(nfse);
    }

    @Test
    void deveRetornarCreditoAoBuscarPorNumeroCreditoExistente() {
        String numeroCredito = "ABC123";
        Credito mockCredito = creditoMock();

        when(service.consultarPorNumeroCredito(numeroCredito)).thenReturn(Optional.of(mockCredito));

        ResponseEntity<Credito> response = controller.buscarPorNumeroCredito(numeroCredito);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCredito, response.getBody());
        verify(service, times(1)).consultarPorNumeroCredito(numeroCredito);
    }

    @Test
    void deveRetornarNotFoundAoBuscarPorNumeroCreditoInexistente() {
        String numeroCredito = "NAO_EXISTE";

        when(service.consultarPorNumeroCredito(numeroCredito)).thenReturn(Optional.empty());

        ResponseEntity<Credito> response = controller.buscarPorNumeroCredito(numeroCredito);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(service, times(1)).consultarPorNumeroCredito(numeroCredito);
    }

    private Credito creditoMock() {
        Credito c = new Credito();
        c.setNumeroCredito("ABC123");
        c.setNumeroNfse("123456");
        c.setDataConstituicao(LocalDate.now());
        c.setValorIssqn(new BigDecimal("1000.00"));
        c.setTipoCredito("ISSQN");
        c.setSimplesNacional(true);
        c.setAliquota(new BigDecimal("5.0"));
        c.setValorFaturado(new BigDecimal("20000.00"));
        c.setValorDeducao(new BigDecimal("3000.00"));
        c.setBaseCalculo(new BigDecimal("17000.00"));
        return c;
    }
}
