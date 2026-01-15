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
        System.out.print("Digite o nome do produto ('0' para cancelar): ");
        String nome = input.nextLine();
       if(utilitarios.cancelarAcao(nome)){
           return;
       }
        System.out.print("Digite a categoria do produto ('0' para cancelar): ");
        String Categoria = input.nextLine();
        if(utilitarios.cancelarAcao(Categoria)){
            return;
        }
        BigDecimal preco = utilitarios.lerBigDecimal("Digite o preço do produto ('0' para cancelar): ");
        try {
            produtoService.cadastrarProduto(nome, Categoria, preco);
            System.out.println("Produto cadastrado com sucesso!");
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
        }catch(EstaVazioException e){
            System.out.println(e.getMessage());
        } catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
    public void buscarProduto(){
       Integer id = utilitarios.lerInteiro("Digite o id ('0' para cancelar): ");
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
        Integer id = utilitarios.lerInteiro("Digite o id ('0' para cancelar): ");
        try {
            Produto produto = produtoService.buscarProduto(id);
            System.out.println("Tem certeza que deseja atualizar" + produto.getNome() + "(S/N)  ('0' para cancelar) ?");
       String conf;
            do{
                conf = input.nextLine();
                if(!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("n")){
                    System.out.println("Digite S ou N para confirmação ('0' para cancelar): ");
                }
                if(conf.equalsIgnoreCase("n") || conf.equalsIgnoreCase("0")){
                    System.out.println("Operação cancelada.");
                    return;
                }
            }while(!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("n"));
            System.out.print("Digite o nome do produto ('0' para cancelar): ");
            String nome = input.nextLine();
            System.out.print("Digite a categoria do produto ('0' para cancelar): ");
            String Categoria = input.nextLine();
            BigDecimal preco = utilitarios.lerBigDecimal("Digite o preço ('0' para cancelar): ");
            produtoService.atualizarProduto(id, nome, Categoria, preco);
            System.out.println("Produto atualizado com sucesso!");
        }catch (IdInvalidoException | ProdutoNaoEncontradoException | NomeInvalidoException | PrecoInvalidoException | ProdutoJaExisteException e) {
            System.out.println("[ERRO]: " + e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
    public void remover(){
        Integer id = utilitarios.lerInteiro("Digite o id ('0' para cancelar): ");
        try {
            Produto produto = produtoService.buscarProduto(id);
            System.out.println("Tem certeza que deseja remover" + produto.getNome() + "(S/N) ('0' para cancelar)?");
            String conf;
            do{
             conf = input.nextLine();
             if(!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("n")){
                 System.out.println("Digite S ou N para confirmação ('0' para cancelar) :");
             }
             if(conf.equalsIgnoreCase("n") || conf.equalsIgnoreCase("0")){
                 System.out.println("Operação cancelada.");
                 return;
             }
            }while (!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("n"));
            produtoService.removerProduto(id);
            System.out.println("Produto removido com sucesso!");
        }catch (IdInvalidoException | ProdutoNaoEncontradoException e){
            System.out.println("[ERRO]: " + e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
    }
   public void limpar(){
        try{
            System.out.println("Tem certeza que deseja limpar os registros (S/N) ('0' para cancelar) ?.\n ATENÇÃO: ESSA OPERAÇÃO NÃO PODERÁ SER DESFEITA.");
            String conf;
            do{
              conf = input.nextLine();
                if(!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("n")){
                    System.out.println("Digite S ou N para confirmação ('0' para cancelar): ");
                }
                if(conf.equalsIgnoreCase("n") || conf.equalsIgnoreCase("0")){
                    System.out.println("Operação cancelada.");
                    return;
                }
            }while (!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("n"));
            produtoService.limparProdutos();
            System.out.println("Operação realizada com sucesso.");
        }catch (EstaVazioException e){
            System.out.println(e.getMessage());
        }catch (PersistenciaException e){
            System.out.println("Erro ao listar produtos. Tente novamente mais tarde.");
            System.err.println("[ERRO]: " + e.getMessage());
        }
   }
}
