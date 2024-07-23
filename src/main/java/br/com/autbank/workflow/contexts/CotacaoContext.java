package br.com.autbank.workflow.contexts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class CotacaoContext {
    BigDecimal valor;
    BigDecimal taxaCambio;
    BigDecimal valorDolar;
    Integer idRemessa;
    String status;
    String status_envio;
}
