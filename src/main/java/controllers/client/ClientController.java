package controllers.client;


import controllers.BaseTableController;
import controllers.observers.ClientObservable;
import controllers.observers.IClientObserver;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import utils.ui.UiConstants;
import utils.ui.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController extends BaseTableController<Client> implements Initializable, IClientObserver {
    @FXML
    private TableView<Client> tvClient;
    @FXML
    private TableColumn<Client, String> tfClientNo, tfClientName, tfStartDate, tfEndDate, tfApartmentNo, tfPhone, tfPaymentDate, tfAmountPaid, tfLate;
    @FXML
    private Button btnDeleteClient, btnEditClient, btnAddNewClient;

    @Override
    public TableView<Client> getTableView() {
        return tvClient;
    }

    @Override
    protected TextField getTextFieldSearch() {
        return null;
    }

    @Override
    public void initTableData() {
        observableList = FXCollections.observableArrayList(clientDAO.getListAllClient());
        tfClientNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfAmountPaid.setCellValueFactory(new PropertyValueFactory<>("AmountPaid"));
        tfApartmentNo.setCellValueFactory(new PropertyValueFactory<>("ApartmentNo"));
        tfClientName.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        tfEndDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        tfPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        tfLate.setCellValueFactory(new PropertyValueFactory<>("Late"));
        tfPaymentDate.setCellValueFactory(new PropertyValueFactory<>("PaymentDate"));
        tfStartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        tvClient.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ClientObservable.registerClientObserver(this);
        initTableData();
        initListeners();
    }


    @Override
    public void onClientAdded(Client Client) {
        observableList.add(Client);
    }

    @Override
    public void onClientDeleted(Client Client) {
        for (int i = 0; i < observableList.size(); i++) {
            Client currentClient = observableList.get(i);
            if (currentClient.getId() == Client.getId())
                observableList.remove(i);
        }
    }

    @Override
    public void onClientEdited(Client client) {
        for (int i = 0; i < observableList.size(); i++) {
            Client currentClient = observableList.get(i);
            if (currentClient.getId() == client.getId())
                observableList.set(i, client);
        }
    }

    private void initListeners() {
        btnDeleteClient.setOnAction(event -> {
            if (getSelectionItem() != null) {
                if (ViewUtil.showConfirmation()) {
                    clientDAO.delete(getSelectionItem());
                    ClientObservable.onClientDeleted(getSelectionItem());
                }
            }
        });

        btnEditClient.setOnAction(event -> {
            if (getSelectionItem() != null) {
                ViewUtil.showClientEditView(this);
            }
        });

        btnAddNewClient.setOnAction(event -> {
            ViewUtil.showView(UiConstants.Path.CLIENT_ADD_NEW_PATH, UiConstants.Tittle.CLIENT_ADD_NEW_TITTLE, false);
        });
    }
}
