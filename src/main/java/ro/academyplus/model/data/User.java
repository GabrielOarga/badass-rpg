package ro.academyplus.model.data;


import ro.academyplus.model.data.characters.BaseHero;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id = 0;
    @Column(name = "username", nullable = false)
    private String name;
    @Column(name = "userEmail", nullable = false)
    private String email;
    @Column(name = "userPassword", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BaseHero> heroes = new ArrayList<>();

    public void addHero(BaseHero hero) {
        this.heroes.add(hero);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BaseHero> getHeroes() {
        return heroes;
    }

    public boolean removeHero(BaseHero hero) {
        return heroes.remove(hero);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", heroCount= '" + heroes.size() +
                '}';
    }
}
