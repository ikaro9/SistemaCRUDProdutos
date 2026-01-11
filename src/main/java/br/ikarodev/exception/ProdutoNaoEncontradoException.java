package br.ikarodev.exception;

public class ProdutoNaoEncontradoException extends NegociosException{
    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado.");
    }
}
