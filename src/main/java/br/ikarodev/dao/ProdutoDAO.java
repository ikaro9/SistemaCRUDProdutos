package br.ikarodev.dao;

import br.ikarodev.config.Conexao;
import br.ikarodev.exception.PersistenciaException;
import br.ikarodev.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void criarTabela()  {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nome TEXT UNIQUE NOT NULL, Categoria TEXT NOT NULL, Preço NUMERIC NOT NULL) ";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }catch (SQLException e){
            throw new PersistenciaException("Erro ao criar tabela",e);
        }
    }

    public void inserir(Produto produto)  {
        String sql = "INSERT INTO produtos (Nome,Categoria,Preço) VALUES (?,?,?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new PersistenciaException("Erro ao inserir produto",e);
        }
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("id"),
                        rs.getString("Nome"),
                        rs.getString("Categoria"),
                        rs.getBigDecimal("Preço"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao listar produtos",e);
        }
            return produtos;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET Nome = ?, Categoria = ?, Preço = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao atualizar produto",e);
        }
    }

    public Produto buscar(Integer id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(rs.getInt("id"),
                            rs.getString("Nome"),
                            rs.getString("Categoria"),
                            rs.getBigDecimal("Preço"));
                }
        }
        }catch(SQLException e){
                throw new PersistenciaException("Erro ao buscar produto",e);
            }
        return null;
    }
    public void remover (Integer id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try(Connection conn = Conexao.conectar();PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao remover produto",e);
        }
    }

    public boolean estaVazio(){
        String sql = "SELECT COUNT(*) FROM produtos";
        try(Connection conn = Conexao.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            if(rs.next()){
                return rs.getInt(1)==0;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao acessar banco",e);
        }
        return true;
    }

    public void limpar() {
        String limparTabela = "DELETE FROM produtos";
        String resetarTabela = "DELETE FROM sqlite_sequence WHERE name = 'produtos'";
        try(Connection conn = Conexao.conectar();Statement stmt = conn.createStatement()){
            stmt.execute(limparTabela);
            stmt.execute(resetarTabela);
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao limpar registros",e);
        }

    }

    public boolean jaExisteNome(String nome){
      String sql = "SELECT 1 FROM produtos WHERE Nome = ?";
      try(Connection conn = Conexao.conectar();PreparedStatement stmt = conn.prepareStatement(sql)){
          stmt.setString(1,nome);
          try(ResultSet rs = stmt.executeQuery()) {
              return rs.next();
          }
      } catch (SQLException e) {
          throw new PersistenciaException("Erro ao acessar banco",e);
      }
    }
}
