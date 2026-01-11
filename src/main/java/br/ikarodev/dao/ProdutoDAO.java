package br.ikarodev.dao;

import br.ikarodev.config.Conexao;
import br.ikarodev.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public static void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nome TEXT UNIQUE NOT NULL, Categoria TEXT NOT NULL, Preço NUMERIC NOT NULL) ";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (Nome,Categoria,Preço) VALUES (?,?,?)";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("id"),
                        rs.getString("Nome"),
                        rs.getString("Categoria"),
                        rs.getBigDecimal("Preço"));
                produtos.add(produto);
            }
        }
            return produtos;
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET Nome = ?, Categoria = ?, Preço = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCategoria());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
        }
    }

    public Produto buscar(Integer id) throws SQLException {
        String sql = "SELECT FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return new Produto(rs.getInt("id"),
                            rs.getString("Nome"),
                            rs.getString("Categoria"),
                            rs.getBigDecimal("Preço"));
                }
            }
        }
        return null;
    }
    public void remover (Integer id) throws SQLException{
        String sql = "DELETE FROM produtos WHERE id = ?";
        try(Connection conn = Conexao.conectar();PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }

    public boolean estaVazio()throws SQLException{
        String sql = "SELECT COUNT(*) FROM produtos";
        try(Connection conn = Conexao.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            if(rs.next()){
                return rs.getInt(1)==0;
            }
        }
        return true;
    }

    public void limpar() throws SQLException{
        String limparTabela = "DELETE FROM produtos";
        String resetarTabela = "DELETE FROM sqlite_sequence WHERE name = 'produtos";
        try(Connection conn = Conexao.conectar();Statement stmt = conn.createStatement()){
            stmt.execute(limparTabela);
            stmt.execute(resetarTabela);
        }

    }
}
