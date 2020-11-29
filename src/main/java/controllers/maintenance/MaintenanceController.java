package controllers.maintenance;


import controllers.BaseTableController;
import controllers.observers.MaintenanceObservable;
import controllers.observers.IMaintenanceObserver;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Maintenance;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class MaintenanceController extends BaseTableController<Maintenance> implements Initializable, IMaintenanceObserver {
    @FXML
    private TableView<Maintenance> tvMaintenance;
    @FXML
    private TableColumn<Maintenance, String> tfMaintenanceNo, tfProblemDescription, tfStartDate, tfResolutionDate, tfApartmentNo, tfType, tfBuildingExpense, tfResolution, tfTenantExpense;
    @FXML
    private Button btnDeleteMaintenance, btnEditMaintenance, btnAddNewMaintenance;

    @Override
    public TableView<Maintenance> getTableView() {
        return tvMaintenance;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return null;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(maintenanceDAO.getListAllMaintenance());
        tfMaintenanceNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfBuildingExpense.setCellValueFactory(new PropertyValueFactory<>("BuildingExpense"));
        tfApartmentNo.setCellValueFactory(new PropertyValueFactory<>("ApartmentNo"));
        tfProblemDescription.setCellValueFactory(new PropertyValueFactory<>("ProblemDescription"));
        tfResolution.setCellValueFactory(new PropertyValueFactory<>("Resolution"));
        tfResolutionDate.setCellValueFactory(new PropertyValueFactory<>("ResolutionDate"));
        tfType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tfTenantExpense.setCellValueFactory(new PropertyValueFactory<>("TenantExpense"));
        tfStartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        tvMaintenance.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MaintenanceObservable.registerMaintenanceObserver(this);
        initTableData();
        initListeners();
    }


    @Override
    public void onMaintenanceAdded(Maintenance Maintenance) {
        observableList.add(Maintenance);
    }

    @Override
    public void onMaintenanceDeleted(Maintenance Maintenance) {
        for (int i = 0; i < observableList.size(); i++) {
            Maintenance currentMaintenance = observableList.get(i);
            if (currentMaintenance.getId() == Maintenance.getId())
                observableList.remove(i);
        }
    }

    @Override
    public void onMaintenanceEdited(Maintenance maintenance) {
        for (int i = 0; i < observableList.size(); i++) {
            Maintenance currentMaintenance = observableList.get(i);
            if (currentMaintenance.getId() == maintenance.getId())
                observableList.set(i, maintenance);
        }
    }

    private void initListeners() {
        btnDeleteMaintenance.setOnAction(event -> {
            if (getSelectionItem() != null) {
                if (ViewUtil.showConfirmation()) {
                    maintenanceDAO.delete(getSelectionItem());
                    MaintenanceObservable.onMaintenanceDeleted(getSelectionItem());
                }
            }
        });

        btnEditMaintenance.setOnAction(event -> {
            if (getSelectionItem() != null) {
                ViewUtil.showMaintenanceEditView(this);
            }
        });

        btnAddNewMaintenance.setOnAction(event -> {
            ViewUtil.showView(UiConstants.Path.MAINTENANCE_ADD_NEW_PATH, UiConstants.Tittle.MAINTENANCE_ADD_NEW_TITTLE, false);
        });
    }
}
