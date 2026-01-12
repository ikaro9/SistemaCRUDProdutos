package br.ikarodev.service;

import br.ikarodev.dao.ProdutoDAO;
import br.ikarodev.exception.*;
import br.ikarodev.model.Produto;

import java.math.BigDecimal;
import java.util.List;

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
    public List<Produto> listarProdutos(){
       return produtoDao.listar();
    }

    public Produto buscarProduto(Integer id){
        if(id == null || id <= 0){
         throw new IdInvalidoException();
        }
        Produto produto = produtoDao.buscar(id);
        if(produto == null){
            throw new ProdutoNaoEncontradoException();
        }
        return produto;
    }
    public void removerProduto(Integer id){
        if(id == null || id <= 0){
            throw new IdInvalidoException();
        }
        Produto produto = produtoDao.buscar(id);
        if(produto == null){
            throw new ProdutoNaoEncontradoException();
        }
        produtoDao.remover(id);
    }

    public void limparProdutos(){
        if(produtoDao.estaVazio()){
            throw new EstaVazioException();
        }
        produtoDao.limpar();
    }

    public void atualizarProduto(String nome, String categoria,BigDecimal preco){
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
        produtoDao.atualizar(produto);
    }



}
