package controllers.employee;


import controllers.BaseTableController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController extends BaseTableController<Employee> implements Initializable {
    @FXML
    private TableView<Employee> tvEmployee;
    @FXML
    private TableColumn<Employee, String> tfId, tfBuildingNo, tfEmpName, tfPosition, tfPhone, tfMonthlySalary;

    @Override
    public TableView<Employee> getTableView() {
        return tvEmployee;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return null;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(employeeDAO.getListAllEmployee());
        tfId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfEmpName.setCellValueFactory(new PropertyValueFactory<>("EmpName"));
        tfBuildingNo.setCellValueFactory(new PropertyValueFactory<>("BuildingNo"));
        tfMonthlySalary.setCellValueFactory(new PropertyValueFactory<>("MonthlySalary"));
        tfPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tfPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));

        tvEmployee.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableData();
    }

}
