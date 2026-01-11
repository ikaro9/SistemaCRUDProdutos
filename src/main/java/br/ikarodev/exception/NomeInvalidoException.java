package br.ikarodev.exception;

public class NomeInvalidoException extends NegociosException{
    public NomeInvalidoException() {
        super("Nome inválido");
    }
}
