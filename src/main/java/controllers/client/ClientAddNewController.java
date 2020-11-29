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

public class ClientAddNewController implements Initializable {
    @FXML
    private Button btnOk, btnCancel;
    @FXML
    private TextField tfClientNo, tfClientName, tfStartDate, tfEndDate, tfApartmentNo, tfPhone, tfPaymentDate, tfAmountPaid, tfLate;
    @FXML
    private AnchorPane apAddNewClient;

    private ClientDAO clientDAO = new ClientDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
    }

    private void initListeners() {
        btnOk.setOnAction(event -> {
            Client client = new Client();
            client.setStartDate(tfStartDate.getText());
            client.setLate(tfLate.getText());
            client.setPhone(tfPhone.getText());
            client.setPaymentDate(tfPaymentDate.getText());
            client.setEndDate(tfEndDate.getText());
            client.setClientName(tfClientName.getText());
            client.setAmountPaid(tfAmountPaid.getText());
            client.setApartmentNo(Integer.parseInt(tfApartmentNo.getText()));


                clientDAO.save(client);
            ClientObservable.onClientAdded(clientDAO.getLastAddedClient());
                getStage().close();

        });
        btnCancel.setOnAction(event -> getStage().close());
    }

    private Stage getStage() {
        return ((Stage) apAddNewClient.getScene().getWindow());
    }


}