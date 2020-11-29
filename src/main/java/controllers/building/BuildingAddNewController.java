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

public class BuildingAddNewController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfBuildingNo, tfStreet, tfCity, tfState, tfZipcode, tfPhone, tfEmpNo;
    @FXML
    private AnchorPane apAddNewBuilding;

    private BuildingDAO buildingDAO = new BuildingDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            Building building = new Building(tfStreet.getText(), tfCity.getText(),
                    tfState.getText(), tfZipcode.getText(), tfPhone.getText(), Integer.parseInt(tfEmpNo.getText()));

                buildingDAO.save(building);
            BuildingObservable.onBuildingAdded(buildingDAO.getLastAddedBuilding());
                getStage().close();

        });
        btnCancel.setOnAction(event -> getStage().close());
    }

    private Stage getStage() {
        return ((Stage) apAddNewBuilding.getScene().getWindow());
    }


}