package br.ikarodev.service;

import br.ikarodev.dao.ProdutoDAO;
import br.ikarodev.exception.NomeInvalidoException;
import br.ikarodev.exception.PrecoInvalidoException;
import br.ikarodev.exception.ProdutoJaExisteException;
import br.ikarodev.model.Produto;

import java.math.BigDecimal;
import java.text.ParseException;

public class ProdutoService {
    private ProdutoDAO produtoDao = new ProdutoDAO();
    public void cadastrarProduto(String nome, String categoria, BigDecimal preco){
        if(nome == null || nome.isBlank()){
            throw new NomeInvalidoException();
        }
        if(preco.compareTo(BigDecimal.ZERO)<=0){
           throw new PrecoInvalidoException();
        }
        if(produtoDao.jaExisteNome(nome)){
            throw new ProdutoJaExisteException();
        }
        Produto produto = new Produto(nome, categoria, preco);
        produtoDao.inserir(produto);
    }
}
