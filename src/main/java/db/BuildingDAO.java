package db;

import model.Building;
import service.ConnectionService;
import utils.converter.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDAO {

    public void save(Building building) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.INSERT_BUILDING)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, building.getId());
            preparedStatement.setString(2, building.getStreet());
            preparedStatement.setString(3, building.getCity());
            preparedStatement.setString(4, building.getState());
            preparedStatement.setString(5, building.getZipcode());
            preparedStatement.setString(6, building.getPhone());
            preparedStatement.setInt(7, building.getManagerNo());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Building building) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.UPDATE_BUILDING_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(7, building.getId());
            preparedStatement.setString(1, building.getStreet());
            preparedStatement.setString(2, building.getCity());
            preparedStatement.setString(3, building.getState());
            preparedStatement.setString(4, building.getZipcode());
            preparedStatement.setString(5, building.getPhone());
            preparedStatement.setInt(6, building.getManagerNo());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Building> getListAllBuilding() {
        List<Building> list = new ArrayList<>();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_ALL_BUILDING)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(ResultSetConverter.getBuilding(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(Building building) {
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.DELETE_BUILDING_BY_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, building.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Building getBuildingById(int id) {
        Building building = new Building();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.SELECT_BUILDING_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            building = ResultSetConverter.getBuilding(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return building;
    }

    public Building getLastAddedBuilding() {
        Building building = new Building();
        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_LAST_ADDED_BUILDING)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            building = ResultSetConverter.getBuilding(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return building;
    }
}
