package controllers.observers;

import model.Maintenance;

public interface IMaintenanceObserver {
    void onMaintenanceAdded(Maintenance maintenance);
    void onMaintenanceDeleted(Maintenance maintenance);
    void onMaintenanceEdited(Maintenance maintenance);
}
