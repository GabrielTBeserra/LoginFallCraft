package br.com.urso.loginfallcraft.database;

import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {
    public TableCreator() {
        String accountsTable = "create table if not exists accounts (\n" +
                "    uuid varchar(50) primary key ,\n" +
                "    password varchar(100) not null\n" +
                ")";


        try {
            Statement statement = ConnectionFactory.getConnection().createStatement();
            statement.execute(accountsTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
