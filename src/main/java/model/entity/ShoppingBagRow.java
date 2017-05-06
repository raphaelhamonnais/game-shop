package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.ModelException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**

 */
@Entity
@Table(name = "SHOPPING_BAG_ROW", schema = "sr03_web_project")
public class ShoppingBagRow implements Serializable {

    public static final String ERROR_BINDING_TO_PHYSICAL_GAME_MESSAGE = "ShoppingBagRow needs to be linked to a PhysicalGame object";
    public static final String ERROR_BINDING_TO_SHOPPING_BAG_MESSAGE = "ShoppingBagRow needs to be linked to a ShoppingBag object";

    private int shopBagRowId;
    private int nbUnits = 1;
    private PhysicalGame physicalGame;
    @JsonIgnore
    private ShoppingBag shoppingBag;

    public ShoppingBagRow() {}

    public ShoppingBagRow(PhysicalGame physicalGame, ShoppingBag shoppingBag, int nbUnits) {
        this.nbUnits = nbUnits;
        this.physicalGame = physicalGame;
        this.shoppingBag = shoppingBag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_bag_row_id", nullable = false)
    public int getShopBagRowId() {
        return shopBagRowId;
    }

    public void setShopBagRowId(int shopBagRowId) {
        this.shopBagRowId = shopBagRowId;
    }

    @Basic
    @Column(name = "nb_units", nullable = false)
    public int getNbUnits() {
        return nbUnits;
    }

    public void setNbUnits(int nbUnits) {
        this.nbUnits = nbUnits;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physical_game_id")
    public PhysicalGame getPhysicalGame() {
        return physicalGame;
    }

    public void setPhysicalGame(PhysicalGame physicalGame) {
        this.physicalGame = physicalGame;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_bag_id", nullable = false)
    public ShoppingBag getShoppingBag() {
        return shoppingBag;
    }

    public void setShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingBagRow that = (ShoppingBagRow) o;
        checkConsistency();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(physicalGame, that.physicalGame);
        eb.append(shoppingBag, that.shoppingBag);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        checkConsistency();
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(physicalGame);
        hcb.append(shoppingBag);
        return hcb.toHashCode();
    }

    private void checkConsistency() {
        if (this.shoppingBag == null)
            throw new ModelException(ERROR_BINDING_TO_SHOPPING_BAG_MESSAGE);

        if (this.physicalGame == null)
            throw new ModelException(ERROR_BINDING_TO_PHYSICAL_GAME_MESSAGE);
    }
}
