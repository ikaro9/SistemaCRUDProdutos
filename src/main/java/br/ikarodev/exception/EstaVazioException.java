package br.ikarodev.exception;

public class EstaVazioException extends NegociosException {
    public EstaVazioException() {
        super("Não há produtos cadastrados.");
    }
}
