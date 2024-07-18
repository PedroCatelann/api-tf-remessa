package br.com.autbank.controllers;

import arch.common.http.HttpResponse;
import arch.pattern.workflow2.flow.FlowProcessor;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.controllers.RemessasController;
import core.autogen.models.SimulacaoRemessaResponse;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Named
@Slf4j
@RequiredArgsConstructor
public class RemessaControllerImpl implements RemessasController {

    private final FlowProcessor<BigDecimal, CotacaoContext, SimulacaoRemessaResponse> simulacaoRemessaResponseFlowProcessor;

    @Override
    public HttpResponse<SimulacaoRemessaResponse> simularRemessas(SimularRemessasRequest request) throws Exception {
        log.info("O valor é de {}", request.getValor());
        var context = CotacaoContext.builder().valor(request.getValor()).build();

        return HttpResponse.ok(simulacaoRemessaResponseFlowProcessor.execute(request.getValor(), context));
    }
}
