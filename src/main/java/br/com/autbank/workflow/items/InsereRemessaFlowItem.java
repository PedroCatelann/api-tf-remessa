package br.com.autbank.workflow.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.RemessaConstants;
import br.com.autbank.repositories.RemessaRepository;
import br.com.autbank.utilities.SimulacaoCotacaoUtility;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.CriarRemessaResponse;
import core.autogen.models.DadosPagamento;
import core.autogen.models.DadosRemessa;
import cotacao.client.CotacoesCambioPort;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
@Named
@AllArgsConstructor
@Slf4j
public class InsereRemessaFlowItem extends FlowItem<BigDecimal, CotacaoContext, CriarRemessaResponse> {
    private final RemessaRepository repository;
    private final CotacoesCambioPort cambioPort;
    private final SimulacaoCotacaoUtility simulacaoCotacaoUtility;
    @Override
    protected CriarRemessaResponse doExecute(BigDecimal valor, CotacaoContext cotacaoContext) throws Exception {
        var serverResponse = simulacaoCotacaoUtility.retornaTaxaCambio(cambioPort);
        var taxaCambio = new BigDecimal(serverResponse);
        cotacaoContext.setStatus_envio(RemessaConstants.STATUS_ENVIO);
        var id = repository.registrarRemessa(valor.multiply(taxaCambio), valor, taxaCambio);
        cotacaoContext.setIdRemessa(id);
        log.info("ID DA REMESSA REGISTRADA {}", id);

        CriarRemessaResponse criarRemessaResponse = new CriarRemessaResponse();
        DadosRemessa dadosRemessa = new DadosRemessa();
        dadosRemessa.setIdRemessa(id);
        dadosRemessa.setTaxaCambio(taxaCambio);
        dadosRemessa.setValorEnvio(valor.multiply(taxaCambio));
        dadosRemessa.setValorPago(valor);

        DadosPagamento dadosPagamento = new DadosPagamento();
        dadosPagamento.setNomeBanco("INTER");
        dadosPagamento.setNroContaBanco("1234");
        dadosPagamento.setValor(valor);

        criarRemessaResponse.setDadosRemessa(dadosRemessa);
        criarRemessaResponse.setDadosPagamento(dadosPagamento);

        return criarRemessaResponse;

    }
}
