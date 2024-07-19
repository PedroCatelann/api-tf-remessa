package br.com.autbank.utilities;

import arch.cache.aop.CacheKey;
import arch.cache.aop.CacheLocal;
import cotacao.client.CotacoesCambioPort;
import cotacao.client.GetTaxaCambioRequest;
import cotacao.client.GetTaxaCambioResponse;
import cotacao.client.Moedas;
import jakarta.inject.Named;

@Named
public class SimulacaoCotacaoUtility {
    @CacheKey("DM_TF_SIMULA_COTACAO")
    @CacheLocal(ttl = "5m")
    public String retornaTaxaCambio(CotacoesCambioPort cambioPort) {
        var modelRequest = new GetTaxaCambioRequest();
        modelRequest.setMoeda(Moedas.USDBRL);

        var modelResponse = new GetTaxaCambioResponse();
        modelResponse.setTaxaCambio(modelRequest.getMoeda().value());

        return cambioPort.getTaxaCambio(modelRequest).getTaxaCambio().replace(",", ".");

    }
}
