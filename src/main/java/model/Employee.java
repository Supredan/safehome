package model;

public class Employee extends LibraryModel {
    private String EmpName;
    private String Position;
    private String Phone;
    private String MonthlySalary;
    private int BuildingNo;

    public Employee() {
    }

    public Employee(String empName, String position, String phone, String monthlySalary, int buildingNo) {
        EmpName = empName;
        Position = position;
        Phone = phone;
        MonthlySalary = monthlySalary;
        BuildingNo = buildingNo;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMonthlySalary() {
        return MonthlySalary;
    }

    public void setMonthlySalary(String monthlySalary) {
        MonthlySalary = monthlySalary;
    }

    public int getBuildingNo() {
        return BuildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        BuildingNo = buildingNo;
    }
}
