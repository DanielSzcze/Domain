package site.danielszczesny.backend.model;

import org.springframework.stereotype.Component;
import site.danielszczesny.backend.model.lolapp.ChestArray;
import site.danielszczesny.backend.model.timofinance.Record;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "chestArray", length = 1000)
    private String chestArray;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Record> records = new HashSet<>();

    public Account(){
        this.chestArray = createChestArray("");
    }

    public Account(Long id, String username, String chestArray) {
        this.id = id;
        this.username = username;
        this.chestArray = createChestArray(chestArray);
    }

    private String createChestArray(String chestArray) {
        if (chestArray.isEmpty() || chestArray.isBlank()) {
            return new ChestArray().toString();
        } else {
            return chestArray;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChestArray() {
        return chestArray;
    }

    public void setChestArray(String chestArray) {
        this.chestArray = chestArray;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}
