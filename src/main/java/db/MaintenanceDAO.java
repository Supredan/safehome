package db;

import model.Maintenance;
import service.ConnectionService;
import utils.converter.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {

    public void save(Maintenance maintenance) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_MAINTENANCE)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, maintenance.getApartmentNo());
            preparedStatement.setString(2, maintenance.getStartDate());
            preparedStatement.setString(3, maintenance.getProblemDescription());
            preparedStatement.setString(4, maintenance.getType());
            preparedStatement.setString(5, maintenance.getResolution());
            preparedStatement.setString(6, maintenance.getResolutionDate());
            preparedStatement.setString(7, maintenance.getBuildingExpense());
            preparedStatement.setString(8, maintenance.getTenantExpense());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Maintenance maintenance) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.UPDATE_MAINTENANCE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, maintenance.getApartmentNo());
            preparedStatement.setString(2, maintenance.getStartDate());
            preparedStatement.setString(3, maintenance.getProblemDescription());
            preparedStatement.setString(4, maintenance.getType());
            preparedStatement.setString(5, maintenance.getResolution());
            preparedStatement.setString(6, maintenance.getResolutionDate());
            preparedStatement.setString(7, maintenance.getBuildingExpense());
            preparedStatement.setString(8, maintenance.getTenantExpense());
            preparedStatement.setInt(9, maintenance.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Maintenance> getListAllMaintenance() {
        List<Maintenance> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_ALL_MAINTENANCE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getMaintenance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(Maintenance maintenance) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_MAINTENANCE_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, maintenance.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Maintenance getMaintenanceById(int id) {
        Maintenance maintenance = new Maintenance();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_MAINTENANCE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            maintenance = ResultSetConverter.getMaintenance(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenance;
    }

    public Maintenance getLastAddedMaintenance() {
        Maintenance maintenance = new Maintenance();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_MAINTENANCE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            maintenance = ResultSetConverter.getMaintenance(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenance;
    }
}
