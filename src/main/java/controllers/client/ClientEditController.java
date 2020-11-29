package controllers.client;

import controllers.observers.ClientObservable;
import db.ClientDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientEditController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfClientNo, tfClientName, tfStartDate, tfEndDate, tfApartmentNo, tfPhone, tfPaymentDate, tfAmountPaid, tfLate;
    @FXML
    private AnchorPane apEditClient;

    private Client client;
    private ClientDAO clientDAO = new ClientDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
                client.setStartDate(tfStartDate.getText());
                client.setLate(tfLate.getText());
                client.setPhone(tfPhone.getText());
                client.setPaymentDate(tfPaymentDate.getText());
                client.setEndDate(tfEndDate.getText());
                client.setClientName(tfClientName.getText());
                client.setAmountPaid(tfAmountPaid.getText());
                client.setApartmentNo(Integer.parseInt(tfApartmentNo.getText()));

                clientDAO.update(client);
                ClientObservable.onClientEdit(clientDAO.getClientById(client.getId()));
                getStage().close();
        });
        btnCancel.setOnAction(event -> {
            getStage().close();
        });
    }

    public void initClientInfo(Client client) {
        this.client = client;
        tfAmountPaid.setText(client.getAmountPaid());
        tfClientName.setText(client.getClientName());
        tfPhone.setText(client.getPhone());
        tfEndDate.setText(client.getEndDate());
        tfStartDate.setText(client.getStartDate());
        tfPaymentDate.setText(client.getPaymentDate());
        tfLate.setText(client.getLate());
        tfApartmentNo.setText(String.valueOf(client.getApartmentNo()));
    }

    private Stage getStage() {
        return ((Stage) apEditClient.getScene().getWindow());
    }
}
