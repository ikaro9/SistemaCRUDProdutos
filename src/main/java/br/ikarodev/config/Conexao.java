package br.ikarodev.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:sqlite:produtos.db";
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
