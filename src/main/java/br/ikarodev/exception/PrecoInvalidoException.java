package br.ikarodev.exception;

public class PrecoInvalidoException extends NegociosException{
    public PrecoInvalidoException() {
        super("Preço inválido");
    }
    public PrecoInvalidoException(String string) {
        super(string);
    }
}
