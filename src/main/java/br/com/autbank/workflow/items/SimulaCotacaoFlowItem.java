package br.com.autbank.workflow.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.SimulacaoRemessaResponse;
import cotacao.client.GetTaxaCambioRequest;
import cotacao.client.Moedas;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Named
@Slf4j
@RequiredArgsConstructor
public class SimulaCotacaoFlowItem extends FlowItem<BigDecimal, CotacaoContext, SimulacaoRemessaResponse> {

    @Override
    protected SimulacaoRemessaResponse doExecute(BigDecimal valor, CotacaoContext cotacaoContext) throws Exception {
        var model = new GetTaxaCambioRequest();
        model.setMoeda(Moedas.USDBRL);

        log.info("A MOEDA EH {}", model.getMoeda().value());

        SimulacaoRemessaResponse simulacaoRemessaResponse = new SimulacaoRemessaResponse();
        simulacaoRemessaResponse.setTaxaCambio(BigDecimal.valueOf(10.2));
        simulacaoRemessaResponse.setValor(valor);
        simulacaoRemessaResponse.setValor(BigDecimal.TEN);
        return simulacaoRemessaResponse;

    }
}
