package br.ikarodev.exception;

public class ProdutoJaExisteException extends NegociosException {
    public ProdutoJaExisteException() {
        super("Produto já existe.");
    }
}
