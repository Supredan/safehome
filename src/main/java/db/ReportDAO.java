package db;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.Building;
import model.Maintenance;
import service.ConnectionService;
import utils.converter.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportDAO {

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

    public List<String> getReportSubmit(int buildingID, String year, String quarter) {
        List<String> result = new ArrayList<>();

        Building building = getBuildingById(buildingID);
        result.add(building.getStreet() + ", " + building.getState() + ", " + building.getPhone());

        String startDate = "", endDate = "";
        if (quarter.equalsIgnoreCase("spring")) {
            startDate = "-01-01";
            endDate = "-03-30";
        } else if (quarter.equalsIgnoreCase("summer")) {
            startDate = "-04-01";
            endDate = "-06-30";
        } else if (quarter.equalsIgnoreCase("autumn")) {
            startDate = "-07-01";
            endDate = "-09-30";
        } else if (quarter.equalsIgnoreCase("winter")) {
            startDate = "-10-01";
            endDate = "-12-31";
        }
        startDate = year + startDate;
        endDate = year + endDate;


        String totalApartments = "", currentlyOccupied, noChanging, Maintenance = "", wages = "";

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_APARTMENT_NUM_OF_BUILDING)) {
            preparedStatement.setInt(1, buildingID);
            totalApartments = preparedStatement.executeQuery().getString("COUNT(*)");
            result.add(totalApartments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_APARTMENT_OCCUPIED)) {
            preparedStatement.setInt(1, buildingID);
            currentlyOccupied = preparedStatement.executeQuery().getString("COUNT(DISTINCT ApartmentNo)");
            result.add(currentlyOccupied);
            double percent = 100.0 * Double.parseDouble(currentlyOccupied) / Double.parseDouble(totalApartments);
            result.add(Math.round(percent) + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_CHANGING_TENANTS)) {
            preparedStatement.setInt(1, buildingID);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setString(4, startDate);
            preparedStatement.setString(5, endDate);
            noChanging = preparedStatement.executeQuery().getString("COUNT(*)");
            result.add(noChanging);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_TOTAL_REVENUE)) {
            preparedStatement.setInt(1, buildingID);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            result.add(preparedStatement.executeQuery().getString("SUM(AmountPaid)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result.add("");

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_MAINTENANCE_EXPENSES)) {
            preparedStatement.setInt(1, buildingID);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            Maintenance = preparedStatement.executeQuery().getString("SUM(BuildingExpense)");
            result.add(Maintenance);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result.add("");
        result.add("");
        result.add("");

        try (Connection connection = ConnectionService.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery.GET_WAGES)) {
            preparedStatement.setInt(1, buildingID);
            wages = preparedStatement.executeQuery().getString("SUM(MonthlySalary)");
            result.add(wages);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result.add(String.valueOf(Integer.parseInt(Maintenance) + Integer.parseInt(wages)));

        result.add("");

        return result;
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

    public XYChart.Series getSeries(String year, String quarter) {
        XYChart.Series series = new XYChart.Series();

        List<Maintenance> maintenances = getListAllMaintenance();
        String startDate = "", endDate = "";
        if (quarter.equalsIgnoreCase("spring")) {
            startDate = "-01-01";
            endDate = "-03-30";
        } else if (quarter.equalsIgnoreCase("summer")) {
            startDate = "-04-01";
            endDate = "-06-30";
        } else if (quarter.equalsIgnoreCase("autumn")) {
            startDate = "-07-01";
            endDate = "-09-30";
        } else if (quarter.equalsIgnoreCase("winter")) {
            startDate = "-10-01";
            endDate = "-12-31";
        }
        startDate = year + startDate;
        endDate = year + endDate;

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date start = ft.parse(startDate);
            Date end = ft.parse(endDate);
            while (start.before(end)) {
                int count = 0;
                for (Maintenance maintenance : maintenances) {
                    if (ft.parse(maintenance.getStartDate()).before(start)
                            && (maintenance.getResolutionDate().isEmpty() || ft.parse(maintenance.getResolutionDate()).after(start))) {
                        count++;
                    }
                }
                series.getData().add(new XYChart.Data(ft.format(start), count));
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(start);
                calendar.add(Calendar.DATE,1);
                start = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return series;
    }
}
