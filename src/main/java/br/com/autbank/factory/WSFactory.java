package br.com.autbank.factory;

import arch.context.annotation.Bean;
import arch.context.annotation.Factory;
import cotacao.client.CotacoesCambioPort;
import cotacao.client.CotacoesCambioPortService;
import jakarta.inject.Singleton;

@Factory
public class WSFactory {

    @Bean
    @Singleton
    public CotacoesCambioPort provideCotacaoWSService() {
        return new CotacoesCambioPortService().getCotacoesCambioPortSoap11();
    }
}
