package br.ikarodev.exception;

public class IdInvalidoException extends RuntimeException {
    public IdInvalidoException() {
        super("Id inválido.");
    }
}
