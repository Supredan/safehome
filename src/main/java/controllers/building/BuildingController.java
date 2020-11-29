package controllers.building;


import controllers.BaseTableController;
import controllers.observers.BuildingObservable;
import controllers.observers.IBuildingObserver;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Building;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class BuildingController extends BaseTableController<Building> implements Initializable, IBuildingObserver {
    @FXML
    private TableView<Building> tvBuilding;
    @FXML
    private TableColumn<Building, String> tfBuildingNo, tfStreet, tfCity, tfState, tfZipcode, tfPhone, tfEmpNo;
    @FXML
    private Button btnDeleteBuilding, btnEditBuilding, btnAddNewBuilding;

    @Override
    public TableView<Building> getTableView() {
        return tvBuilding;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return null;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(buildingDAO.getListAllBuilding());
        tfBuildingNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfStreet.setCellValueFactory(new PropertyValueFactory<>("Street"));
        tfCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        tfState.setCellValueFactory(new PropertyValueFactory<>("State"));
        tfZipcode.setCellValueFactory(new PropertyValueFactory<>("Zipcode"));
        tfPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tfEmpNo.setCellValueFactory(new PropertyValueFactory<>("EmpNo"));
        tvBuilding.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BuildingObservable.registerBuildingObserver(this);
        initTableData();
        initListeners();
    }


    @Override
    public void onBuildingAdded(Building Building) {
        observableList.add(Building);
    }

    @Override
    public void onBuildingDeleted(Building Building) {
        for (int i = 0; i < observableList.size(); i++) {
            Building currentBuilding = observableList.get(i);
            if (currentBuilding.getId() == Building.getId())
                observableList.remove(i);
        }
    }

    @Override
    public void onBuildingEdited(Building building) {
        for (int i = 0; i < observableList.size(); i++) {
            Building currentBuilding = observableList.get(i);
            if (currentBuilding.getId() == building.getId())
                observableList.set(i, building);
        }
    }

    private void initListeners() {
        btnDeleteBuilding.setOnAction(event -> {
            if (getSelectionItem() != null) {
                if (ViewUtil.showConfirmation()) {
                    buildingDAO.delete(getSelectionItem());
                    BuildingObservable.onBuildingDeleted(getSelectionItem());
                }
            }
        });

        btnEditBuilding.setOnAction(event -> {
            if (getSelectionItem() != null) {
                ViewUtil.showBuildingEditView(this);
            }
        });

        btnAddNewBuilding.setOnAction(event -> {
            ViewUtil.showView(UiConstants.Path.BUILDING_ADD_NEW_PATH, UiConstants.Tittle.BUILDING_ADD_NEW_TITTLE, false);
        });
    }
}
