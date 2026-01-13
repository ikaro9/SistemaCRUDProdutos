package br.ikarodev.menu;
import br.ikarodev.exception.*;
import br.ikarodev.model.Produto;
import br.ikarodev.service.ProdutoService;
import br.ikarodev.util.Utilitarios;

import java.math.BigDecimal;
import java.util.Scanner;
public class Menu {
    private final Scanner input = new Scanner(System.in);
    private final ProdutoService produtoService = new ProdutoService();
    private final Utilitarios utilitarios = new Utilitarios();

    public void exibirMenu(){
     int opcao;
      do {
          System.out.println("""
                  1. Cadastrar Produto
                  2. Listar Produtos
                  3. Buscar Produto
                  4. Atualizar Produto
                  5. Remover Produto
                  6. Limpar Registros
                  0. Sair""");
           opcao = utilitarios.lerInteiro("Digite a opção desejada: ");
          switch (opcao) {
              case 1:
                  cadastrarProduto();
                  break;
              case 2:
                  listarProduto();
                  break;
              case 3:
                  buscarProduto();
                  break;
              case 4:
                  atualizarProduto();
                  break;
              case 5:
                  remover();
                  break;
              case 6:
                  limpar();
                  break;
              case 0:
                  System.out.println("Saindo...");
                  break;
              default:
                  System.out.println("Digite uma opção válida");
          }
      }while (opcao != 0);
    }
    public void cadastrarProduto(){
        System.out.print("Digite o nome do produto: ");
        String nome = input.nextLine();
        System.out.print("Digite a categoria do produto: ");
        String Categoria = input.nextLine();
        BigDecimal preco = utilitarios.lerBigDecimal("Digite o preço do produto: ");
        try {
            produtoService.cadastrarProduto(nome, Categoria, preco);
        }catch(NomeInvalidoException | PrecoInvalidoException | ProdutoJaExisteException e){
            System.out.println("[ERRO]: " + e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao cadastrar produto. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }

    public void listarProduto(){
        try {
            for (Produto i : produtoService.listarProdutos()) {
                System.out.println(i);
            }
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
    public void buscarProduto(){
       Integer id = utilitarios.lerInteiro("Digite o id: ");
        try {
            System.out.println(produtoService.buscarProduto(id));
        }catch (IdInvalidoException | ProdutoNaoEncontradoException e){
            System.out.println("[ERRO]: " + e.getMessage());
        } catch (PersistenciaException e) {
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
    public void atualizarProduto(){
        Integer id = utilitarios.lerInteiro("Digite o id: ");
        try {
            Produto produto = produtoService.buscarProduto(id);
            System.out.println("Você está editando " + produto.getNome());
            System.out.print("Digite o nome do produto: ");
            String nome = input.nextLine();
            System.out.print("Digite a categoria do produto:");
            String Categoria = input.nextLine();
            BigDecimal preco = utilitarios.lerBigDecimal("Digite o preço: ");
            produtoService.atualizarProduto(id,nome, Categoria, preco);
        }catch (IdInvalidoException | ProdutoNaoEncontradoException | NomeInvalidoException | PrecoInvalidoException | ProdutoJaExisteException e) {
            System.out.println("[ERRO]: " + e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
    public void remover(){
        Integer id = utilitarios.lerInteiro("Digite o id: ");
        try {
            produtoService.removerProduto(id);
        }catch (IdInvalidoException | ProdutoNaoEncontradoException e){
            System.out.println("[ERRO]: " + e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
   public void limpar(){
        try{
            produtoService.limparProdutos();
        }catch (EstaVazioException e){
            System.out.println(e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
   }
}
