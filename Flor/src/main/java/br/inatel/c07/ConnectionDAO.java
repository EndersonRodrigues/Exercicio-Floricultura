
package br.inatel.c07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Classe responsável pela conexão com o banco de dados.
public abstract class ConnectionDAO {

    Connection connection;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    // Dados de acesso ao MySQL.
    String host = env("DB_HOST", "localhost");
    String port = env("DB_PORT", "3306");
    String database = env("DB_NAME", "floricultura");
    String user = env("DB_USER", "root");
    String password = env("DB_PASSWORD", "root");

    String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

    // Abre a conexão com o banco.
    public Connection connectToDb() {
        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }

        return connection;
    }

    // Permite utilizar valores personalizados nas variáveis de ambiente.
    private static String env(String nome, String padrao) {
        String valor = System.getenv(nome);
        return valor != null ? valor : padrao;
    }
}
