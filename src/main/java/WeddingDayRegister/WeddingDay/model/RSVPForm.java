package WeddingDayRegister.WeddingDay.model;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
// Сутність
@Entity
@Table(name = "wedding_list")
public class RSVPForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String secondName;
    @Column
    private String presence;
    @Column
    private List<String> guest;
    @Column
    private List<String> beverage;

    public RSVPForm() {
    }

    public RSVPForm(long id, String name, String secondName, String presence, List<String> guest, List<String> beverage) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.presence = presence;
        this.guest = guest;
        this.beverage = beverage;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPresence() {
        return presence;
    }

    public List<String> getGuest() {
        return guest;
    }

    public List<String> getBeverage() {
        return beverage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public void setGuest(List<String> guest) {
        this.guest = guest;
    }

    public void setBeverage(List<String> beverage) {
        this.beverage = beverage;
    }
}
