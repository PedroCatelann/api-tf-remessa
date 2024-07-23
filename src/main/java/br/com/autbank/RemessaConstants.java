package br.com.autbank;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RemessaConstants {
    public final static String STATUS_REMESSA = "CRIADA";

    public final static String STATUS_ENVIO = "PENDENTE";
    public final static String URI_BANCO = "URL_BANCO";
    public final static String DEFAULT_URI_BANCO = "http://localhost:8080/secured/envios-exterior";

}
