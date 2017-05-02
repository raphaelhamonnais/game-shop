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
@Table(name = "USERS", schema = "sr03_web_project")
public class Users implements Serializable {

    private int userId;
    private String userLogin;
    private String userEmail;
    private String userPasswd;
    private String userLastName;
    private String userFirstName;
    private String userTel = "";
    private boolean userActive = true;

    private Set<Address> addresses = new HashSet<>();
    private Set<Orders> orders = new HashSet<>();
    private Set<ShoppingBag> shoppingBags = new HashSet<>();
    private Set<Roles> roles = new HashSet<>();

    public Users() {}

    public Users(String userLogin, String userEmail, String userPasswd, String userLastName, String userFirstName) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.userPasswd = userPasswd;
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
    }

    public Users(String userLogin, String userEmail, String userPasswd, String userLastName, String userFirstName, String userTel) {
        this(userLogin, userEmail, userPasswd, userLastName, userFirstName);
        this.userTel = userTel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_login", nullable = false, length = 50)
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Basic
    @Column(name = "user_email", nullable = false, length = 50)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_passwd", nullable = false, length = 50)
    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    @Basic
    @Column(name = "user_last_name", nullable = false, length = 20)
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Basic
    @Column(name = "user_first_name", nullable = false, length = 20)
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Basic
    @Column(name = "user_tel", nullable = true, length = 15)
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Basic
    @Column(name = "user_active", nullable = true)
    public boolean isUserActive() {
        return userActive;
    }

    public void setUserActive(boolean userActive) {
        this.userActive = userActive;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ADRESSES", schema = "sr03_web_project",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "adr_id", nullable = false, updatable = false)}
    )
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<ShoppingBag> getShoppingBags() {
        return shoppingBags;
    }

    public void setShoppingBags(Set<ShoppingBag> shoppingBags) {
        this.shoppingBags = shoppingBags;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES", schema = "sr03_web_project",
            joinColumns = {@JoinColumn(name = "user_login", referencedColumnName = "user_login", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name", nullable = false, updatable = false)}
    )
    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users that = (Users) o;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(userLogin, that.userLogin);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(userLogin);
        return hcb.toHashCode();
    }

    public void addAddress(Address address) {
        if (addresses == null)
            addresses = new HashSet<>();
        addresses.add(address);
    }

    public void addShoppingBag(ShoppingBag aShoppingBag) {
        if (shoppingBags == null)
            shoppingBags = new HashSet<>();
        shoppingBags.add(aShoppingBag);
    }

    public void addOrder(Orders anOrder) {
        if (orders == null)
            orders = new HashSet<>();
        orders.add(anOrder);
    }

    public void addRole(Roles aRole) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(aRole);
    }
}
