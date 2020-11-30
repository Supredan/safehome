package db;

public class PreparedQuery {
    static final String INSERT_BUILDING = "INSERT INTO Building(Street, City, State, Zipcode, Phone, EmpNo)" +
            "VALUES (?,?,?,?,?,?)";
    static final String UPDATE_BUILDING_BY_ID = "UPDATE Building SET Street=?,City=?,State=?,Zipcode=?,Phone=?,EmpNo=? WHERE id=?";
    static final String SELECT_ALL_BUILDING = "SELECT * FROM Building";
    static final String DELETE_BUILDING_BY_ID = "DELETE FROM Building WHERE id=?";
    static final String SELECT_BUILDING_BY_ID = "SELECT * FROM Building WHERE id=?";
    static final String GET_LAST_ADDED_BUILDING = "SELECT * from Building WHERE id = (SELECT MAX(id)  FROM Building)";

    static final String SELECT_ALL_APARTMENT = "SELECT * FROM Apartment";

    static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM Employee";

    static final String INSERT_CLIENT = "INSERT INTO Client(ClientName, Phone, StartDate, EndDate ,ApartmentNo,PaymentDate, AmountPaid,Late)" +
            "VALUES (?,?,?,?,?,?,?,?)";
    static final String UPDATE_CLIENT_BY_ID = "UPDATE Client SET ClientName=?, Phone=?, StartDate=?, EndDate=? ,ApartmentNo=?,PaymentDate=?, AmountPaid=?,Late=? WHERE id=?";
    static final String SELECT_ALL_CLIENT = "SELECT * FROM Client";
    static final String DELETE_CLIENT_BY_ID = "DELETE FROM Client WHERE id=?";
    static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id=?";
    static final String GET_LAST_ADDED_CLIENT = "SELECT * from Client WHERE id = (SELECT MAX(id)  FROM Client)";

    static final String INSERT_MAINTENANCE = "INSERT INTO Maintenance(ApartmentNo, StartDate, ProblemDescription, Type, Resolution, ResolutionDate, BuildingExpense, TenantExpense)" +
            "VALUES (?,?,?,?,?,?,?,?)";
    static final String UPDATE_MAINTENANCE_BY_ID = "UPDATE Maintenance SET ApartmentNo=?, StartDate=?, ProblemDescription=?, Type=?, Resolution=?, ResolutionDate=?, BuildingExpense=?, TenantExpense=? WHERE id=?";
    static final String SELECT_ALL_MAINTENANCE = "SELECT * FROM Maintenance";
    static final String DELETE_MAINTENANCE_BY_ID = "DELETE FROM Maintenance WHERE id=?";
    static final String SELECT_MAINTENANCE_BY_ID = "SELECT * FROM Maintenance WHERE id=?";
    static final String GET_LAST_ADDED_MAINTENANCE = "SELECT * from Maintenance WHERE id = (SELECT MAX(id)  FROM Maintenance)";

    static final String GET_APARTMENT_NUM_OF_BUILDING = "SELECT COUNT(*) from Apartment WHERE BuildingNo = ?";
    static final String GET_APARTMENT_OCCUPIED = "SELECT COUNT(DISTINCT ApartmentNo) from Client INNER JOIN Apartment WHERE BuildingNo = ?";
    static final String GET_CHANGING_TENANTS = "SELECT COUNT(*) from Client INNER JOIN Apartment WHERE BuildingNo = ? and (date(EndDate) BETWEEN ? AND ? or date(StartDate) BETWEEN ? AND ?)";
    static final String GET_TOTAL_REVENUE = "SELECT SUM(AmountPaid) FROM Client INNER JOIN Apartment WHERE BuildingNo = ? and PaymentDate BETWEEN ? AND ?";
    static final String GET_MAINTENANCE_EXPENSES = "SELECT SUM(BuildingExpense) FROM Maintenance INNER JOIN Apartment WHERE BuildingNo = ? and ResolutionDate BETWEEN ? AND ?";
    static final String GET_WAGES = "SELECT SUM(MonthlySalary) FROM Employee WHERE BuildingNo = ?";
}
