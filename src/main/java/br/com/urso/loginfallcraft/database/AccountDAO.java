package br.com.urso.loginfallcraft.database;

import br.com.urso.loginfallcraft.utils.UUIDResolver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountDAO {
    private final Connection connection;

    public AccountDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void register(UUID uuid, String password) {
        try {
            String query = "insert into accounts (uuid , password) values (? , ?)";
            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(query);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void changePassword(UUID uuid, String newPassword) {
        try {
            String query = "update accounts set password = ? where uuid = ?";
            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void unregister(String userName) {
        try {
            String query = "delete from accounts where uuid = ?";
            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean login(UUID uniqueId, String password) {
        String query = "select * from accounts where uuid = ? and password = ?";
        try {
            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(query);
            preparedStatement.setString(1, uniqueId.toString());
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return false;
    }

    public boolean isPlayer(String uniqueId) {
        String query = "select * from accounts where uuid = ?";
        try {
            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(query);
            preparedStatement.setString(1, uniqueId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return false;
    }
}
