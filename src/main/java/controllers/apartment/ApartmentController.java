package controllers.apartment;


import controllers.BaseTableController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Apartment;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class ApartmentController extends BaseTableController<Apartment> implements Initializable {
    @FXML
    private TableView<Apartment> tvApartment;
    @FXML
    private TableColumn<Apartment, String> tfApartmentNo, tfBuildingNo, tfMonthlyRate;

    @Override
    public TableView<Apartment> getTableView() {
        return tvApartment;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return null;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(apartmentDAO.getListAllApartment());
        tfApartmentNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfMonthlyRate.setCellValueFactory(new PropertyValueFactory<>("MonthlyRate"));
        tfBuildingNo.setCellValueFactory(new PropertyValueFactory<>("BuildingNo"));
        tvApartment.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableData();
    }

}
