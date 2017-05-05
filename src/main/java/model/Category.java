package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**

 */
@Entity
@Table(name = "CATEGORY", schema = "sr03_web_project")
public class Category implements Serializable {

    private int catId;
    private String catName;
    @JsonIgnore
    private Set<Game> games = new HashSet<>();

    public Category() {}

    public Category(String catName) {
        this.catName = catName;
    }

    public Category(String catName, Set<Game> games) {
        this.catName = catName;
        this.games = games;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false, unique = true)
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "cat_name", nullable = false, unique = true, length = 20)
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category that = (Category) o;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(catName, that.catName);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(catName);
        return hcb.toHashCode();
    }

    public void addGame(Game aGame) {
        if (games == null)
            games = new HashSet<>();
        games.add(aGame);
    }
}
