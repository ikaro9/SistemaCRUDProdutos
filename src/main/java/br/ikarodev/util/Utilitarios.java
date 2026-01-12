package br.ikarodev.util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Utilitarios {
    Scanner input = new Scanner(System.in);
    public int lerInteiro(String mensagem){
        while(true){
            try{
                System.out.println(mensagem);
                String entrada = input.nextLine();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite uma entrada válida.");;
            }
        }
    }
    public Double lerDouble(String mensagem){
        while(true){
            try{
                System.out.println(mensagem);
                String entrada = input.nextLine().replace(",",".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite uma entrada válida.");;
            }
        }
    }
}
