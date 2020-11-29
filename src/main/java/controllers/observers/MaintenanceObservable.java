package controllers.observers;

import model.Maintenance;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceObservable {
    private static List<IMaintenanceObserver> maintenanceObservers = new ArrayList<>();

    public static void registerMaintenanceObserver(IMaintenanceObserver iMaintenanceObserver){
        maintenanceObservers.add(iMaintenanceObserver);
    }

    public static void onMaintenanceAdded(Maintenance maintenance){
        for (IMaintenanceObserver iMaintenanceObserver : maintenanceObservers) {
            iMaintenanceObserver.onMaintenanceAdded(maintenance);
        }
    }

    public static void onMaintenanceDeleted(Maintenance maintenance){
        for (IMaintenanceObserver iMaintenanceObserver : maintenanceObservers) {
            iMaintenanceObserver.onMaintenanceDeleted(maintenance);
        }
    }

    public static void onMaintenanceEdit(Maintenance maintenance){
        for (IMaintenanceObserver iMaintenanceObserver : maintenanceObservers) {
            iMaintenanceObserver.onMaintenanceEdited(maintenance);
        }
    }
}
