package controllers.observers;

import model.Building;

public interface IBuildingObserver {
    void onBuildingAdded(Building building);
    void onBuildingDeleted(Building building);
    void onBuildingEdited(Building building);
}
