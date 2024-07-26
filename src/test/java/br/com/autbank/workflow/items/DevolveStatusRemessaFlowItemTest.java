package br.com.autbank.workflow.items;

import br.com.autbank.exceptions.ResourceNotFoundException;
import br.com.autbank.repositories.RemessaRepository;
import br.com.autbank.workflow.contexts.CotacaoContext;
import core.autogen.models.DevolveRemessaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
class DevolveStatusRemessaFlowItemTest {
    @Mock
    private CotacaoContext cotacaoContext;
    @Mock
    private RemessaRepository repository;

    @Mock
    private DevolveRemessaResponse devolveRemessaResponse;

    @Test
    void devolveRemessaSeOIdExistir() throws Exception {
        var valorEsperado = devolveRemessaResponse;

        Mockito.when(repository.getRemessa(20)).thenReturn(devolveRemessaResponse);
        var unit = new DevolveStatusRemessaFlowItem(repository);
        var retorno = unit.doExecute(20, cotacaoContext);
        Assertions.assertEquals(retorno, valorEsperado);
    }

    @Test
    void devolveNadaSeOIdNaoExistir() throws Exception {

        DevolveStatusRemessaFlowItem unit = new DevolveStatusRemessaFlowItem(repository);

        Mockito.when(repository.getRemessa(60)).thenThrow(new EmptyResultDataAccessException(1));

        // Act & Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            unit.doExecute(60, cotacaoContext);
        });
    }
}
