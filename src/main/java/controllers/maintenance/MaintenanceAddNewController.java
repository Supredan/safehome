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

public class MaintenanceAddNewController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfMaintenanceNo, tfProblemDescription, tfStartDate, tfResolutionDate, tfApartmentNo, tfType, tfBuildingExpense, tfResolution, tfTenantExpense;
    @FXML
    private AnchorPane apAddNewMaintenance;

    private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            Maintenance maintenance = new Maintenance();
            maintenance.setStartDate(tfStartDate.getText());
            maintenance.setType(tfType.getText());
            maintenance.setTenantExpense(tfTenantExpense.getText());
            maintenance.setResolutionDate(tfResolutionDate.getText());
            maintenance.setResolution(tfResolution.getText());
            maintenance.setProblemDescription(tfProblemDescription.getText());
            maintenance.setBuildingExpense(tfBuildingExpense.getText());
            maintenance.setApartmentNo(Integer.parseInt(tfApartmentNo.getText()));


                maintenanceDAO.save(maintenance);
            MaintenanceObservable.onMaintenanceAdded(maintenanceDAO.getLastAddedMaintenance());
                getStage().close();

        });
        btnCancel.setOnAction(event -> getStage().close());
    }

    private Stage getStage() {
        return ((Stage) apAddNewMaintenance.getScene().getWindow());
    }


}