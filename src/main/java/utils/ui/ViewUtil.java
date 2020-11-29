package utils.ui;

import controllers.BaseTableController;
import controllers.building.BuildingEditController;
import controllers.client.ClientEditController;
import controllers.maintenance.MaintenanceEditController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Building;
import model.Client;
import model.Maintenance;
import utils.media.GandalfPlayer;

import java.io.IOException;
import java.util.Optional;

public class ViewUtil {

    public static FXMLLoader showView(String viewPath, String viewTitle, boolean resizable) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(ViewUtil.class.getClassLoader().getResource(viewPath));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.getIcons().add(new Image(UiConstants.Path.VADIMKSN));
        stage.setTitle(viewTitle);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(resizable);
        stage.show();
        return loader;
    }

    public static void showError(String dialog) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(UiConstants.Tittle.ERROR);
        alert.setContentText(dialog);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static boolean showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(UiConstants.Tittle.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(UiConstants.Dialogs.CONFIRMATION_QUESTION);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void showBuildingEditView(BaseTableController<Building> baseTableController) {
        FXMLLoader loader = showView
                (UiConstants.Path.BUILDING_EDIT_PATH, UiConstants.Tittle.BUILDING_EDIT_TITTLE, false);
        BuildingEditController buildingEditController = loader.getController();
        buildingEditController.initBuildingInfo(baseTableController.getSelectionItem());
    }
    
    public static void showClientEditView(BaseTableController<Client> baseTableController) {
        FXMLLoader loader = showView
                (UiConstants.Path.CLIENT_EDIT_PATH, UiConstants.Tittle.CLIENT_EDIT_TITTLE, false);
        ClientEditController clientEditController = loader.getController();
        clientEditController.initClientInfo(baseTableController.getSelectionItem());
    }

    public static void showMaintenanceEditView(BaseTableController<Maintenance> baseTableController) {
        FXMLLoader loader = showView
                (UiConstants.Path.MAINTENANCE_EDIT_PATH, UiConstants.Tittle.MAINTENANCE_EDIT_TITTLE, false);
        MaintenanceEditController maintenanceEditController = loader.getController();
        maintenanceEditController.initMaintenanceInfo(baseTableController.getSelectionItem());
    }

}
