package com.infuse.credito.service;

import com.infuse.credito.model.Credito;
import com.infuse.credito.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditoServiceTest {

    private CreditoRepository repository;
    private ConsultaCreditoPublisher publisher;
    private CreditoService service;

    @BeforeEach
    void setUp() {
        repository = mock(CreditoRepository.class);
        publisher = mock(ConsultaCreditoPublisher.class);
        service = new CreditoService(repository, publisher);
    }

    @Test
    void deveConsultarPorNfseEPublicarCadaCredito() {
        String nfse = "123456";
        List<Credito> creditos = List.of(creditoMock("CRED001"), creditoMock("CRED002"));

        when(repository.findByNumeroNfse(nfse)).thenReturn(creditos);

        List<Credito> result = service.consultarPorNfse(nfse);

        assertEquals(2, result.size());
        verify(repository).findByNumeroNfse(nfse);
        verify(publisher).publicarConsulta("CRED001");
        verify(publisher).publicarConsulta("CRED002");
    }

    @Test
    void deveConsultarPorNumeroCreditoEPublicarSeEncontrado() {
        String numeroCredito = "CRED123";
        Credito credito = creditoMock(numeroCredito);

        when(repository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito));

        Optional<Credito> result = service.consultarPorNumeroCredito(numeroCredito);

        assertTrue(result.isPresent());
        verify(repository).findByNumeroCredito(numeroCredito);
        verify(publisher).publicarConsulta(numeroCredito);
    }

    @Test
    void naoDevePublicarSeCreditoNaoEncontrado() {
        String numeroCredito = "INEXISTENTE";

        when(repository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.empty());

        Optional<Credito> result = service.consultarPorNumeroCredito(numeroCredito);

        assertFalse(result.isPresent());
        verify(repository).findByNumeroCredito(numeroCredito);
        verify(publisher, never()).publicarConsulta(anyString());
    }

    private Credito creditoMock(String numeroCredito) {
        Credito c = new Credito();
        c.setNumeroCredito(numeroCredito);
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
