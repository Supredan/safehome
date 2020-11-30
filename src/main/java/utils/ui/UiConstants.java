package utils.ui;

public interface UiConstants {

    interface Tittle {
        String START_TITTLE = "SafeHome";
        String ERROR = "error!";
        String CONFIRMATION = "Are you sure?";

        String BUILDING_EDIT_TITTLE = "Edit building";
        String BUILDING_ADD_NEW_TITTLE = "Add building";

        String CLIENT_EDIT_TITTLE = "Edit client";
        String CLIENT_ADD_NEW_TITTLE = "Add client";

        String MAINTENANCE_EDIT_TITTLE = "Edit maintenance";
        String MAINTENANCE_ADD_NEW_TITTLE = "Add maintenance";

        String REPORT_SUBMIT_TITTLE = "report to submit";
    }

    interface Dialogs {
        String CONFIRMATION_QUESTION = "Delete?";

    }

    interface Path {
        String VADIMKSN = "png/safehome.png";
        String START_PATH = "fxml/start_view.fxml";

        String BUILDING_EDIT_PATH = "fxml/building/building_edit_view.fxml";
        String BUILDING_ADD_NEW_PATH = "fxml/building/building_add_new_view.fxml";

        String CLIENT_EDIT_PATH = "fxml/client/client_edit_view.fxml";
        String CLIENT_ADD_NEW_PATH = "fxml/client/client_add_new_view.fxml";

        String MAINTENANCE_EDIT_PATH = "fxml/maintenance/maintenance_edit_view.fxml";
        String MAINTENANCE_ADD_NEW_PATH = "fxml/maintenance/maintenance_add_new_view.fxml";

        String REPORT_SUBMIT_PATH = "fxml/report/report_submit.fxml";
    }
}
