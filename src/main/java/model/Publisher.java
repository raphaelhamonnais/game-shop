package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PUBLISHER", schema = "sr03_web_project")
public class Publisher {

    private int publisherId;
    private String publisherName;
    private Set<Game> games = new HashSet<>();

    public Publisher() {}

    public Publisher(String name) {
        this.publisherName = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false)
    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "publisher_name", nullable = true, length = 100)
    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        if (this.games == null)
            this.games = new HashSet<>();
        this.games.add(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher that = (Publisher) o;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(publisherName, that.publisherName);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(publisherName);
        return hcb.toHashCode();
    }
}
