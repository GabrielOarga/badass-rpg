package ro.academyplus.model.dto;

public class ActionDTO {
    private String selectedHeroName;
    private String selectedAction;

    public ActionDTO() {
    }

    public String getSelectedHeroName() {
        return selectedHeroName;
    }

    public void setSelectedHeroName(String selectedHeroName) {
        this.selectedHeroName = selectedHeroName;
    }

    public String getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(String selectedAction) {
        this.selectedAction = selectedAction;
    }
}
