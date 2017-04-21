package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**

 */
@Entity
@Table(name = "CONSOLE", schema = "sr03_web_project")
public class Console implements Serializable {

    private int consoleId;
    private String consoleName;

    private Set<PhysicalGame> physicalGames = new HashSet<>();

    public Console() {}

    public Console(String consoleName) {
        this.consoleName = consoleName;
    }

    public Console(String consoleName, Set<PhysicalGame> physicalGames) {
        this.consoleName = consoleName;
        this.physicalGames = physicalGames;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "console_id", nullable = false)
    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    @Basic
    @Column(name = "console_name", nullable = true, length = 20)
    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "console")
    public Set<PhysicalGame> getPhysicalGames() {
        return physicalGames;
    }

    public void setPhysicalGames(Set<PhysicalGame> physicalGames) {
        this.physicalGames = physicalGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Console that = (Console) o;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(consoleName, that.consoleName);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(consoleName);
        return hcb.toHashCode();
    }


    public void addPhysicalGame(PhysicalGame physicalGame) {
        if (physicalGames == null)
            physicalGames = new HashSet<>();
        physicalGames.add(physicalGame);
    }
}
