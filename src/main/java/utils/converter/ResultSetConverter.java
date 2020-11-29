package utils.converter;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {

    public static Building getBuilding(ResultSet resultSet) throws SQLException {
        Building building = new Building();

        building.setId(resultSet.getInt("id"));
        building.setStreet(resultSet.getString("Street"));
        building.setZipcode(resultSet.getString("Zipcode"));
        building.setState(resultSet.getString("State"));
        building.setPhone(resultSet.getString("Phone"));
        building.setManagerNo(resultSet.getInt("EmpNo"));
        building.setCity(resultSet.getString("City"));

        return building;
    }

    public static Apartment getApartment(ResultSet resultSet) throws SQLException {
        Apartment apartment = new Apartment();

        apartment.setId(resultSet.getInt("id"));
        apartment.setBuildingNo(resultSet.getInt("BuildingNo"));
        apartment.setMonthlyRate(resultSet.getString("MonthlyRate"));

        return apartment;
    }

    public static Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setBuildingNo(resultSet.getInt("BuildingNo"));
        employee.setEmpName(resultSet.getString("EmpName"));
        employee.setMonthlySalary(resultSet.getString("MonthlySalary"));
        employee.setPhone(resultSet.getString("Phone"));
        employee.setPosition(resultSet.getString("Position"));

        return employee;
    }

    public static Client getClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();

        client.setId(resultSet.getInt("id"));
        client.setAmountPaid(resultSet.getString("AmountPaid"));
        client.setApartmentNo(resultSet.getInt("ApartmentNo"));
        client.setClientName(resultSet.getString("ClientName"));
        client.setPhone(resultSet.getString("Phone"));
        client.setEndDate(resultSet.getString("EndDate"));
        client.setPaymentDate(resultSet.getString("PaymentDate"));
        client.setLate(resultSet.getString("Late"));
        client.setStartDate(resultSet.getString("StartDate"));

        return client;
    }

    public static Maintenance getMaintenance(ResultSet resultSet) throws SQLException {
        Maintenance maintenance = new Maintenance();

        maintenance.setId(resultSet.getInt("id"));
        maintenance.setBuildingExpense(resultSet.getString("BuildingExpense"));
        maintenance.setApartmentNo(resultSet.getInt("ApartmentNo"));
        maintenance.setProblemDescription(resultSet.getString("ProblemDescription"));
        maintenance.setResolution(resultSet.getString("Resolution"));
        maintenance.setResolutionDate(resultSet.getString("ResolutionDate"));
        maintenance.setTenantExpense(resultSet.getString("TenantExpense"));
        maintenance.setType(resultSet.getString("Type"));
        maintenance.setStartDate(resultSet.getString("StartDate"));

        return maintenance;
    }
}
