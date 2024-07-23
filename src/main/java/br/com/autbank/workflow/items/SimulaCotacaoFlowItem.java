package br.com.autbank.workflow.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.utilities.SimulacaoCotacaoUtility;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.SimulacaoRemessaResponse;
import cotacao.client.CotacoesCambioPort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Named
@Slf4j
@RequiredArgsConstructor
public class SimulaCotacaoFlowItem extends FlowItem<BigDecimal, CotacaoContext, SimulacaoRemessaResponse> {

    private final CotacoesCambioPort cambioPort;

    private final SimulacaoCotacaoUtility simulacaoCotacaoUtility;


    @Override
    protected SimulacaoRemessaResponse doExecute(BigDecimal valor, CotacaoContext cotacaoContext) throws Exception {
//        var modelRequest = new GetTaxaCambioRequest();
//        modelRequest.setMoeda(Moedas.USDBRL);
//
//        var modelResponse = new GetTaxaCambioResponse();
//        modelResponse.setTaxaCambio(modelRequest.getMoeda().value());

        var serverResponse = simulacaoCotacaoUtility.retornaTaxaCambio(cambioPort);
        var taxaCambio = new BigDecimal(serverResponse);
        cotacaoContext.setTaxaCambio(taxaCambio);
        cotacaoContext.setValorDolar(valor.multiply(taxaCambio));
        SimulacaoRemessaResponse simulacaoRemessaResponse = new SimulacaoRemessaResponse();
        simulacaoRemessaResponse.setTaxaCambio(taxaCambio);
        simulacaoRemessaResponse.setValor(valor);
        simulacaoRemessaResponse.setValorEnvio(valor.multiply(taxaCambio));

        return simulacaoRemessaResponse;

    }
}
