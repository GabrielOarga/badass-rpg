package ro.academyplus.model.data.map.storeable;
import ro.academyplus.model.data.plebs.BasePleb;

import javax.persistence.*;

//Todo: create a map builder yeah that would require security groups and shit, ain't nobody got time for that
@Entity
@Table(name = "mapElements")
public class StructureElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private BasePleb content;
    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;

    public StructureElement() {
        x = 0;
        y = 0;
        content = null;
    }

    public StructureElement(int x, int y, BasePleb content) {

        this.x = x;
        this.y = y;
        if (content != null)
            this.content = content;
        else
            this.content = null;
    }



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Object getContent() {

        return content;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
