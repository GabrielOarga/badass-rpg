package ro.academyplus.model.dto;

public class MapDTO {
    //TODO VALIDATION
    private String actionType; //TODO movement combat
    private String actionValue;//TODO w a s d fight flight

    public MapDTO() {
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    @Override
    public String toString() {
        return "MapDTO{" +
                "actionType='" + actionType + '\'' +
                ", actionValue='" + actionValue + '\'' +
                '}';
    }
}