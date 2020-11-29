package controllers.building;

import controllers.observers.BuildingObservable;
import db.BuildingDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Building;

import java.net.URL;
import java.util.ResourceBundle;

public class BuildingEditController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfStreet, tfCity, tfState, tfZipcode, tfPhone, tfEmpNo;
    @FXML
    private AnchorPane apEditBuilding;

    private Building building;
    private BuildingDAO buildingDAO = new BuildingDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            if (!(tfStreet.getText().isEmpty()
                    || tfCity.getText().isEmpty()
                    || tfState.getText().isEmpty())
                    || tfZipcode.getText().isEmpty()
                    || tfPhone.getText().isEmpty()
                    || tfEmpNo.getText().isEmpty()) {
                building.setCity(tfCity.getText());
                building.setManagerNo(Integer.parseInt(tfEmpNo.getText()));
                building.setPhone(tfPhone.getText());
                building.setState(tfState.getText());
                building.setZipcode(tfZipcode.getText());
                building.setStreet(tfStreet.getText());

                buildingDAO.update(building);
                BuildingObservable.onBuildingEdit(buildingDAO.getBuildingById(building.getId()));
                getStage().close();

            }
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    public void initBuildingInfo(Building building) {
        this.building = building;
        tfCity.setText(building.getCity());
        tfEmpNo.setText(String.valueOf(building.getManagerNo()));
        tfPhone.setText(building.getPhone());
        tfState.setText(building.getState());
        tfZipcode.setText(building.getZipcode());
        tfStreet.setText(building.getStreet());
    }

    private Stage getStage() {
        return ((Stage) apEditBuilding.getScene().getWindow());
    }
}
