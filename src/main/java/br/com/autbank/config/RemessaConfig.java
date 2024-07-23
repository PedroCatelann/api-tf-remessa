package br.com.autbank.config;

import arch.common.config.Config;
import jakarta.inject.Named;
import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;

import static br.com.autbank.RemessaConstants.DEFAULT_URI_BANCO;
import static br.com.autbank.RemessaConstants.URI_BANCO;

@Named
@Getter
public class RemessaConfig {
    private final URI uriBanco;

    public RemessaConfig(Config config) throws URISyntaxException {
        this.uriBanco = config.getOptionalValue(URI_BANCO, URI.class).orElse(new URI(DEFAULT_URI_BANCO));
    }
}
