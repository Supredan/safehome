package controllers.report;

import db.ReportDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ReportSubmitController implements Initializable {

    @FXML
    private Button btnGenerate;

    @FXML
    private TextField tfBuilding, tfAddress, tfQuarter, tfYear, tfTotalApartments, tfCurrentlyOccupied, tfPercent,
            tfChangingTenants, tfTotalRentRevenue, tfUtilities,
            tfMaintenance, tfRepairs, tfInsurance, tfNewTenantCleaning,
            tfWages, tfTotalExpenses, tfUnrecoveredRents;

    TextField[] textFields = new TextField[]{tfAddress, tfTotalApartments, tfCurrentlyOccupied, tfPercent,
            tfChangingTenants, tfTotalRentRevenue, tfUtilities,
            tfMaintenance, tfRepairs, tfInsurance, tfNewTenantCleaning,
            tfWages, tfTotalExpenses, tfUnrecoveredRents};

    ReportDAO reportDAO = new ReportDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGenerate.setOnAction(event -> {
            String year = tfYear.getText();
            String quarter = tfQuarter.getText();
            int buildingID = Integer.parseInt(tfBuilding.getText());
            List<String> result = reportDAO.getReportSubmit(buildingID, year, quarter);
//            for (int i = 0; i < result.size(); i++) {
//                textFields[i].setText(result.get(i));
//            }
            tfAddress.setText(result.get(0));
            tfTotalApartments.setText(result.get(1));
            tfCurrentlyOccupied.setText(result.get(2));
            tfPercent.setText(result.get(3));
            tfChangingTenants.setText(result.get(4));
            tfTotalRentRevenue.setText(result.get(5));
            tfUtilities.setText(result.get(6));
            tfMaintenance.setText(result.get(7));
            tfRepairs.setText(result.get(8));
            tfInsurance.setText(result.get(9));
            tfNewTenantCleaning.setText(result.get(10));
            tfWages.setText(result.get(11));
            tfTotalExpenses.setText(result.get(12));
            tfUnrecoveredRents.setText(result.get(13));
        });
    }
}
