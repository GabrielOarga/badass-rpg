package ro.academyplus.model.dto;

import javax.validation.constraints.Size;
import java.lang.reflect.Array;

public class BaseHeroDTO {
    @Size(min = 1, max = 30, message ="Character name must consist of one or more alphanumerical")
    private String heroName;
    private String heroType;

    public BaseHeroDTO() {
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }

    @Override
    public String toString() {
        return "BaseHeroDTO{" +
                "heroName='" + heroName + '\'' +
                ", heroType='" + heroType + '\'' +
                '}';
    }
}
