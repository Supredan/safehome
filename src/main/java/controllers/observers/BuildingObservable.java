package controllers.observers;

import model.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingObservable {
    private static List<IBuildingObserver> buildingObservers = new ArrayList<>();

    public static void registerBuildingObserver(IBuildingObserver iBuildingObserver){
        buildingObservers.add(iBuildingObserver);
    }

    public static void onBuildingAdded(Building building){
        for (IBuildingObserver iBuildingObserver : buildingObservers) {
            iBuildingObserver.onBuildingAdded(building);
        }
    }

    public static void onBuildingDeleted(Building building){
        for (IBuildingObserver iBuildingObserver : buildingObservers) {
            iBuildingObserver.onBuildingDeleted(building);
        }
    }

    public static void onBuildingEdit(Building building){
        for (IBuildingObserver iBuildingObserver : buildingObservers) {
            iBuildingObserver.onBuildingEdited(building);
        }
    }
}
