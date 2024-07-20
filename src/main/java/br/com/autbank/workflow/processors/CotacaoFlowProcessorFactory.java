package br.com.autbank.workflow.processors;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import arch.pattern.workflow2.flow.FlowBuilder;
import arch.pattern.workflow2.flow.FlowProcessor;
import br.com.autbank.workflow.contexts.CotacaoContext;
import br.com.autbank.workflow.items.DevolveStatusRemessaFlowItem;
import br.com.autbank.workflow.items.InsereRemessaFlowItem;
import br.com.autbank.workflow.items.SimulaCotacaoFlowItem;
import core.autogen.models.CriarRemessaResponse;
import core.autogen.models.DevolveRemessaResponse;
import core.autogen.models.SimulacaoRemessaResponse;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Factory
@AllArgsConstructor
public class CotacaoFlowProcessorFactory {

    private final SimulaCotacaoFlowItem simulaCotacaoFlowItem;
    private final InsereRemessaFlowItem insereRemessaFlowItem;
    private final DevolveStatusRemessaFlowItem devolveStatusRemessaFlowItem;

    @Bean
    @Singleton
    public FlowProcessor<BigDecimal, CotacaoContext, SimulacaoRemessaResponse> simularCotacaoFlowProcessor() {

        return new FlowBuilder<CotacaoContext>()
                .step(simulaCotacaoFlowItem)
                .build();
    }

    @Bean
    @Singleton
    public FlowProcessor<BigDecimal, CotacaoContext, CriarRemessaResponse> criarCotacaoFlowProcessor() {

        return new FlowBuilder<CotacaoContext>()
                .step(insereRemessaFlowItem)
                .build();
    }

    @Bean
    @Singleton
    public FlowProcessor<Integer, CotacaoContext, DevolveRemessaResponse> devolveRemessaFlowProcessor() {

        return new FlowBuilder<CotacaoContext>()
                .step(devolveStatusRemessaFlowItem)
                .build();
    }
}

