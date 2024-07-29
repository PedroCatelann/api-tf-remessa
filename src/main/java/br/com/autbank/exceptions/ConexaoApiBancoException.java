package br.com.autbank.exceptions;

public class ConexaoApiBancoException extends RuntimeException {

    public ConexaoApiBancoException(String message) {
        super(message);
    }

    public ConexaoApiBancoException(String message, Throwable cause) {
        super(message, cause);
    }
}
