package db;

import model.Client;
import service.ConnectionService;
import utils.converter.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void save(Client client) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_CLIENT)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getPhone());
            preparedStatement.setString(3, client.getStartDate());
            preparedStatement.setString(4, client.getEndDate());
            preparedStatement.setInt(5, client.getApartmentNo());
            preparedStatement.setString(6, client.getPaymentDate());
            preparedStatement.setString(7, client.getAmountPaid());
            preparedStatement.setString(8, client.getLate());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Client client) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.UPDATE_CLIENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getPhone());
            preparedStatement.setString(3, client.getStartDate());
            preparedStatement.setString(4, client.getEndDate());
            preparedStatement.setInt(5, client.getApartmentNo());
            preparedStatement.setString(6, client.getPaymentDate());
            preparedStatement.setString(7, client.getAmountPaid());
            preparedStatement.setString(8, client.getLate());
            preparedStatement.setInt(9, client.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getListAllClient() {
        List<Client> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_ALL_CLIENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(Client client) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_CLIENT_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientById(int id) {
        Client client = new Client();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            client = ResultSetConverter.getClient(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public Client getLastAddedClient() {
        Client client = new Client();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_CLIENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            client = ResultSetConverter.getClient(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
}
