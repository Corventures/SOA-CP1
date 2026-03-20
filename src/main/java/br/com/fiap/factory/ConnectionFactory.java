package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.FileInputStream;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(".env"));

            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    prop.getProperty("db.user"),
                    prop.getProperty("db.pass"));
        } catch (Exception e) {
            throw new SQLException("Erro ao carregar configurações do banco.");
        }
    }
}