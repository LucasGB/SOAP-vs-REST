package com.dao;

import java.sql.*;

public class MysqlConn {

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soap-vs-rest?useTimezone=true&serverTimezone=UTC", "root", "root");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado, adicione a biblioteca Mysql Jdbc Driver no projeto.");
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
}