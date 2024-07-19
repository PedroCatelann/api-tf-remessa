package br.com.autbank.repositories;

import arch.common.config.Config;

import br.com.autbank.RemessaConstants;
import jakarta.inject.Named;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Named
public class RemessaRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String insertRemessaSQL;

    public RemessaRepository(JdbcTemplate jdbcTemplate, Config config) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertRemessaSQL = config.getValue("classpath:SQL/DML/INSERT_REMESSA.sql");
    }

    public int registrarRemessa(BigDecimal valor_reais, BigDecimal valor_dolar, BigDecimal cotacao_dolar) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertRemessaSQL, new String[]{"ID"});
                ps.setBigDecimal(1, valor_reais);
                ps.setBigDecimal(2, valor_dolar);
                ps.setBigDecimal(3, cotacao_dolar);
                ps.setString(4, RemessaConstants.STATUS_REMESSA);
                return ps;
            }
        }, keyHolder);

        Number key = keyHolder.getKey();

        return key != null ? key.intValue() : null;
        //var a = jdbcTemplate.update(insertRemessaSQL, valor_reais, valor_dolar, cotacao_dolar, RemessaConstants.STATUS_REMESSA);
    }
}
