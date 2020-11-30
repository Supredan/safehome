package controllers.report;

import db.ReportDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class ReportUndoMaintenanceController implements Initializable {

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private LineChart<String, Number> linechart;
    @FXML
    private TextField quarter, year;

    @FXML
    private Button btnPlot;

    private ReportDAO reportDAO = new ReportDAO();

    public void initGraph(){
        xAxis.setAutoRanging(true);

        yAxis.setAutoRanging(true);
        linechart.setAnimated(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGraph();

        btnPlot.setOnAction(event -> {
            //Graph Series
            XYChart.Series series = reportDAO.getSeries(year.getText(), quarter.getText());
            linechart.getData().addAll(series);
        });
    }
}
