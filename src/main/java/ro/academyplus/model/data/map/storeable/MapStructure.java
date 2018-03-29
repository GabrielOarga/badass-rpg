package ro.academyplus.model.data.map.storeable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maps")
public class MapStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column
    protected int intendedLevel = 1;

    @OneToMany(cascade = CascadeType.ALL)
    public List<StructureElement> mapElements = new ArrayList<>();

    private final int size = 21;

    public MapStructure() {

    }

    public List<StructureElement> getMapElements() {
        return mapElements;
    }

    public int getSize() {
        return size;
    }

    public void setIntendedLevel(int intendedLevel) {
        this.intendedLevel = intendedLevel;
    }

    public int getIntendedLevel() {

        return intendedLevel;
    }

    public void add(StructureElement el) {
        mapElements.add(el);
    }
}