package br.com.autbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BancoApiInsereRequestResponse {
    private Integer idRemessa;
    private BigDecimal valor;
    private String status;
}
