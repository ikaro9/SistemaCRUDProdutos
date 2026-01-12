package br.ikarodev;

import br.ikarodev.dao.ProdutoDAO;
import br.ikarodev.exception.PersistenciaException;
import br.ikarodev.menu.Menu;

public class Main {
    public static void main(String[] args){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtoDAO.criarTabela();
        Menu menu = new Menu();
        menu.exibirMenu();
        }catch (PersistenciaException e){
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
