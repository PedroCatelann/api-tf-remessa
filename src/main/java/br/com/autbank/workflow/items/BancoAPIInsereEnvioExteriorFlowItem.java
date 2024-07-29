package br.com.autbank.workflow.items;

import arch.common.exception.InternalErrorException;
import arch.common.http.body.HttpBodyHandlers;
import arch.common.http.body.HttpBodyPublishers;
import arch.common.oauth2.OAuth2Client;
import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.config.RemessaConfig;
import br.com.autbank.exceptions.ConexaoApiBancoException;
import br.com.autbank.model.BancoApiInsereRequestResponse;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.CriarRemessaResponse;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.ConnectException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Named
@Slf4j
@AllArgsConstructor
public class BancoAPIInsereEnvioExteriorFlowItem extends FlowItem<CriarRemessaResponse, CotacaoContext, CriarRemessaResponse> {

    private final RemessaConfig remessaConfig;

    private HttpClient httpClient;

    private final OAuth2Client oAuth2Client;

    @Override
    protected CriarRemessaResponse doExecute(CriarRemessaResponse criarRemessaResponse, CotacaoContext cotacaoContext) throws Exception {
        var requestObj = new BancoApiInsereRequestResponse(cotacaoContext.getIdRemessa(), cotacaoContext.getValor(), cotacaoContext.getStatus_envio());

        var request = HttpRequest.newBuilder()
                .uri(remessaConfig.getUriBanco())
                .header("content-type", "application/json")
                .header("Authorization", String.format("Bearer %s", oAuth2Client.getAccessToken()))
                .POST(HttpBodyPublishers.ofJson(requestObj))
                .build();
        try {
            var response = httpClient.send(request, HttpBodyHandlers.ofCodec());
            if(response.statusCode() == 200)
                return criarRemessaResponse;
            log.error("Ocorreu um erro durante a requisição, código http: {}", response.statusCode());
            throw new InternalErrorException("Falha na requisição");
        } catch (ConnectException e) {
            throw new ConexaoApiBancoException("Não foi possível se conectar a API de banco", e.getCause());
        }

    }
}
