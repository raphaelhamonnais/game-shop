package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**

 */
@Entity
@Table(name = "PHYSICAL_GAME", schema = "sr03_web_project")
public class PhysicalGame implements Serializable {

    public static final String ERROR_BINDING_GAME_MESSAGE = "PhysicalGame needs to be linked to a Game object";
    public static final String ERROR_BINDING_CONSOLE_MESSAGE = "PhysicalGame needs to be linked to a Console object";

    private int physicalGameId;
    private int gameStock;
    private BigDecimal gamePrice;
    @JsonIgnore
    private Game game;
    @JsonIgnore
    private Console console;
    @JsonIgnore
    private Set<ShoppingBagRow> shoppingBagRows = new HashSet<>();

    public PhysicalGame() {}

    public PhysicalGame(Game game, Console console, int gameStock, BigDecimal gamePrice) {
        this.gameStock = gameStock;
        this.gamePrice = gamePrice;
        this.game = game;
        this.console = console;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "physical_game_id", nullable = false)
    public int getPhysicalGameId() {
        return physicalGameId;
    }

    public void setPhysicalGameId(int physicalGameId) {
        this.physicalGameId = physicalGameId;
    }

    @Basic
    @Column(name = "game_stock", nullable = false)
    public int getGameStock() {
        return gameStock;
    }

    public void setGameStock(int gameStock) {
        this.gameStock = gameStock;
    }

    @Basic
    @Column(name = "game_price", nullable = false, precision = 2)
    public BigDecimal getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(BigDecimal gamePrice) {
        this.gamePrice = gamePrice;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "console_id", nullable = false)
    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "physicalGame")
    public Set<ShoppingBagRow> getShoppingBagRows() {
        return shoppingBagRows;
    }

    public void setShoppingBagRows(Set<ShoppingBagRow> shoppingBagRows) {
        this.shoppingBagRows = shoppingBagRows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicalGame that = (PhysicalGame) o;

        checkConsistency();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(game, that.game);
        eb.append(console, that.console);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        checkConsistency();
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(game);
        hcb.append(console);
        return hcb.toHashCode();
    }


    private void checkConsistency() {
        if (this.game == null)
            throw new ModelException(ERROR_BINDING_GAME_MESSAGE);

        if (this.console == null)
            throw new ModelException(ERROR_BINDING_CONSOLE_MESSAGE);
    }

    public void addShoppingBagRow(ShoppingBagRow aShoppingBagRow) {
        if (shoppingBagRows == null)
            shoppingBagRows = new HashSet<>();
        shoppingBagRows.add(aShoppingBagRow);
    }
}
