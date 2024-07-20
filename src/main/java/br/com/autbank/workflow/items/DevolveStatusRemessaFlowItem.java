package br.com.autbank.workflow.items;

import arch.pattern.workflow2.flow.FlowItem;
import br.com.autbank.repositories.RemessaRepository;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.DevolveRemessaResponse;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class DevolveStatusRemessaFlowItem extends FlowItem<Integer, CotacaoContext, DevolveRemessaResponse> {

    private final RemessaRepository repository;
    @Override
    protected DevolveRemessaResponse doExecute(Integer id, CotacaoContext cotacaoContext) throws Exception {
        return repository.getRemessa(id);
    }
}
