package br.ikarodev.menu;
import br.ikarodev.model.Produto;
import br.ikarodev.service.ProdutoService;
import br.ikarodev.util.Utilitarios;

import java.math.BigDecimal;
import java.util.Scanner;
public class Menu {
    private Scanner input = new Scanner(System.in);
    private ProdutoService produtoService = new ProdutoService();
    private Utilitarios utilitarios = new Utilitarios();

    public void exibirMenu(){
        System.out.println("""
                           1. Cadastrar Produto
                           2. Listar Produtos
                           3. Buscar Produto
                           4. Atualizar Produto
                           5. Remover Produto
                           6. Limpar Registros
                           0. Sair""");
        System.out.print("Digite a opção desejada: ");
        int opcao = input.nextInt();
        switch (opcao){
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
                break;
            case 0:
                break;
            default:
                System.out.println("Digite uma opção válida");
        }
    }
    public void cadastrarProduto(){
        System.out.print("Digite o nome do produto: ");
        String nome = input.nextLine();
        System.out.print("Digite a categoria do produto:");
        String Categoria = input.nextLine();
        System.out.print("Digite o preço do produto:");
        BigDecimal preco = input.nextBigDecimal();
        produtoService.cadastrarProduto(nome,Categoria,preco);
    }

    public void listarProduto(){
       for(Produto i : produtoService.listarProdutos()){
           System.out.println(i);
       }
    }
    public void buscarProduto(){
        System.out.print("Digite o id: ");
        Integer id = input.nextInt();
        System.out.println(produtoService.buscarProduto(id));
    }
    public void atualizarProduto(){
        System.out.print("Digite o nome do produto: ");
        String nome = input.nextLine();
        System.out.print("Digite a categoria do produto:");
        String Categoria = input.nextLine();
        System.out.print("Digite o preço do produto:");
        BigDecimal preco = input.nextBigDecimal();
        produtoService.atualizarProduto(nome,Categoria,preco);
    }
    public void remover(){
        System.out.print("Digite o id: ");
        Integer id = input.nextInt();
        System.out.println(produtoService.buscarProduto(id));
        produtoService.removerProduto(id);
    }

}
