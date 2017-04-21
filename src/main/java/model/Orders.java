package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**

 */
@Entity
@Table(name = "ORDERS", schema = "sr03_web_project")
public class Orders implements Serializable {

    public static final String ERROR_BINDING_TO_SHOPPING_BAG_MESSAGE = "Order needs to be linked to a ShoppingBag object";

    private int orderId;
    private Date orderDate = new Date();
    private BigDecimal orderAmount;

    private ShoppingBag shoppingBag;
    private Users user;
    private Address address;

    public Orders() {}

    public Orders(ShoppingBag thatShoppingBag, Address address) {
        initFromShoppingBag(thatShoppingBag);
        this.address = address;
    }

    public Orders(ShoppingBag thatShoppingBag, Address address, Date orderDate) {
        initFromShoppingBag(thatShoppingBag);
        this.address = address;
        this.orderDate = orderDate;
    }

    private void initFromShoppingBag(ShoppingBag thatShoppingBag) {
        this.orderAmount = thatShoppingBag.calculateAmount();
        this.shoppingBag = thatShoppingBag;
        this.user = thatShoppingBag.getUser();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "order_amount", nullable = false, precision = 2)
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_bag_id", nullable = false)
    public ShoppingBag getShoppingBag() {
        return shoppingBag;
    }

    public void setShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adr_id", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders that = (Orders) o;

        checkConsistency();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(shoppingBag, that.shoppingBag);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        checkConsistency();
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(shoppingBag);
        return hcb.toHashCode();
    }

    private void checkConsistency() {
        if (this.shoppingBag == null)
            throw new ModelException(ERROR_BINDING_TO_SHOPPING_BAG_MESSAGE);
    }
}
