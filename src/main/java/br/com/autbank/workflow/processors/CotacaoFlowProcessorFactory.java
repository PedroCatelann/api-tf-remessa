package br.com.autbank.workflow.processors;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import br.com.autbank.workflow.contexts.CotacaoContext;
import br.com.autbank.workflow.items.SimulaCotacaoFlowItem;
import core.autogen.models.SimulacaoRemessaResponse;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Factory
@AllArgsConstructor
public class CotacaoFlowProcessorFactory {

    private final SimulaCotacaoFlowItem simulaCotacaoFlowItem;

    @Bean
    @Singleton
    public FlowProcessor<BigDecimal, CotacaoContext, SimulacaoRemessaResponse> simularCotacaoFlowProcessor() {

        return new FlowBuilder<CotacaoContext>()
                .step(simulaCotacaoFlowItem)
                .build();
    }

}
