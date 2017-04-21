package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**

 */
@Entity
@Table(name = "SHOPPING_BAG", schema = "sr03_web_project")
public class ShoppingBag implements Serializable {

    public static final String ERROR_BINDING_USER_MESSAGE = "ShoppingBag needs to be linked to a User object";

    private int shopBagId;
    private Date creationDate = new Date();
    private Date lastUpdate = new Date();
    private boolean isBought = false;

    private Set<ShoppingBagRow> shoppingBagRows = new HashSet<>();
    private Users user;
    private Orders order;

    public ShoppingBag() {}


    public ShoppingBag(Users user) {
        this.user = user;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_bag_id", nullable = false)
    public int getShopBagId() {
        return shopBagId;
    }

    public void setShopBagId(int shopBagId) {
        this.shopBagId = shopBagId;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "last_update", nullable = false)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Basic
    @Column(name = "is_bought", nullable = true)
    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingBag")
    public Set<ShoppingBagRow> getShoppingBagRows() {
        return shoppingBagRows;
    }

    public void setShoppingBagRows(Set<ShoppingBagRow> shoppingBagRows) {
        this.shoppingBagRows = shoppingBagRows;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shoppingBag")
    @JoinColumn(name = "order_id", nullable = true)
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingBag that = (ShoppingBag) o;

        checkConsistency();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(user, that.user);
        eb.append(creationDate, that.creationDate);

        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        checkConsistency();
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(user);
        hcb.append(creationDate);
        return hcb.toHashCode();
    }

    public BigDecimal calculateAmount() {
        if (shoppingBagRows == null || shoppingBagRows.isEmpty())
            return new BigDecimal(0);

        BigDecimal result = new BigDecimal(0);

        for (ShoppingBagRow spr : shoppingBagRows) {
            BigDecimal gamePrice = spr.getPhysicalGame().getGamePrice();
            BigDecimal quantity = new BigDecimal(spr.getNbUnits());
            result = result.add(gamePrice.multiply(quantity));
        }
        return result;
    }


    private void checkConsistency() {
        if (this.user == null)
            throw new ModelException(ERROR_BINDING_USER_MESSAGE);
    }

    public void addShoppingBagRow(ShoppingBagRow aShoppingBagRow) {
        if (shoppingBagRows == null)
            shoppingBagRows = new HashSet<>();
        shoppingBagRows.add(aShoppingBagRow);
    }

}
