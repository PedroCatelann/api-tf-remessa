package br.com.autbank.workflow.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.exceptions.ResourceNotFoundException;
import br.com.autbank.repositories.RemessaRepository;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.DevolveRemessaResponse;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
@AllArgsConstructor
public class DevolveStatusRemessaFlowItem extends FlowItem<Integer, CotacaoContext, DevolveRemessaResponse> {

    private final RemessaRepository repository;
    @Override
    protected DevolveRemessaResponse doExecute(Integer id, CotacaoContext cotacaoContext) throws Exception {
        try {
            return repository.getRemessa(id);
        } catch (Exception e) {
            log.error("NÃO FOI POSSÍVEL ENCONTRAR O ID DA REMESSA");
            throw new ResourceNotFoundException("NÃO FOI POSSÍVEL ENCONTRAR O ID DA REMESSA");
        }
    }
}
