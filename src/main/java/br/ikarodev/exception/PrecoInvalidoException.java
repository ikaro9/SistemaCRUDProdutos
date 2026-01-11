package br.ikarodev.exception;

public class PrecoInvalidoException extends NegociosException{
    public PrecoInvalidoException() {
        super("Preço inválido");
    }
}
