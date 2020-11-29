package controllers.maintenance;

import controllers.observers.MaintenanceObservable;
import db.MaintenanceDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Maintenance;

import java.net.URL;
import java.util.ResourceBundle;

public class MaintenanceEditController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfProblemDescription, tfStartDate, tfResolutionDate, tfApartmentNo, tfType, tfBuildingExpense, tfResolution, tfTenantExpense;
    @FXML
    private AnchorPane apEditMaintenance;

    private Maintenance maintenance;
    private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            maintenance.setStartDate(tfStartDate.getText());
            maintenance.setType(tfType.getText());
            maintenance.setTenantExpense(tfTenantExpense.getText());
            maintenance.setResolutionDate(tfResolutionDate.getText());
            maintenance.setResolution(tfResolution.getText());
            maintenance.setProblemDescription(tfProblemDescription.getText());
            maintenance.setBuildingExpense(tfBuildingExpense.getText());
            maintenance.setApartmentNo(Integer.parseInt(tfApartmentNo.getText()));


                maintenanceDAO.update(maintenance);
                MaintenanceObservable.onMaintenanceEdit(maintenanceDAO.getMaintenanceById(maintenance.getId()));
                getStage().close();
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    public void initMaintenanceInfo(Maintenance maintenance) {
        this.maintenance = maintenance;
        tfBuildingExpense.setText(maintenance.getBuildingExpense());
        tfProblemDescription.setText(maintenance.getProblemDescription());
        tfResolution.setText(maintenance.getResolution());
        tfResolutionDate.setText(maintenance.getResolutionDate());
        tfStartDate.setText(maintenance.getStartDate());
        tfType.setText(maintenance.getType());
        tfTenantExpense.setText(maintenance.getTenantExpense());
        tfApartmentNo.setText(String.valueOf(maintenance.getApartmentNo()));
    }

    private Stage getStage() {
        return ((Stage) apEditMaintenance.getScene().getWindow());
    }
}
