package model.entity;

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
@Table(name = "ADDRESS", schema = "sr03_web_project")
public class Address implements Serializable {

    private int adrId;
    private String adrName;
    private String adrStreet;
    private String adrCity;
    private String adrCountry;
    private String adrZipCode;

    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();
    @JsonIgnore
    private Set<Users> users = new HashSet<>();

    public Address() {}

    public Address(String adrName, String adrStreet, String adrCity, String adrCountry, String adrZipCode) {
        this.adrName = adrName;
        this.adrStreet = adrStreet;
        this.adrCity = adrCity;
        this.adrCountry = adrCountry;
        this.adrZipCode = adrZipCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adr_id", nullable = false)
    public int getAdrId() {
        return adrId;
    }

    public void setAdrId(int adrId) {
        this.adrId = adrId;
    }

    @Basic
    @Column(name = "adr_name", nullable = false, length = 50)
    public String getAdrName() {
        return adrName;
    }

    public void setAdrName(String adrName) {
        this.adrName = adrName;
    }

    @Basic
    @Column(name = "adr_street", nullable = false, length = -1)
    public String getAdrStreet() {
        return adrStreet;
    }

    public void setAdrStreet(String adrStreet) {
        this.adrStreet = adrStreet;
    }

    @Basic
    @Column(name = "adr_city", nullable = false, length = 30)
    public String getAdrCity() {
        return adrCity;
    }

    public void setAdrCity(String adrCity) {
        this.adrCity = adrCity;
    }

    @Basic
    @Column(name = "adr_country", nullable = false, length = 30)
    public String getAdrCountry() {
        return adrCountry;
    }

    public void setAdrCountry(String adrCountry) {
        this.adrCountry = adrCountry;
    }

    @Basic
    @Column(name = "adr_zip_code", nullable = false, length = 30)
    public String getAdrZipCode() {
        return adrZipCode;
    }

    public void setAdrZipCode(String adrZipCode) {
        this.adrZipCode = adrZipCode;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address") // no cascade
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "addresses")
    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address that = (Address) o;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(adrName, that.adrName);
        eb.append(adrStreet, that.adrStreet);
        eb.append(adrCity, that.adrCity);
        eb.append(adrCountry, that.adrCountry);
        eb.append(adrZipCode, that.adrZipCode);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(adrName);
        hcb.append(adrStreet);
        hcb.append(adrCity);
        hcb.append(adrCountry);
        hcb.append(adrZipCode);
        return hcb.toHashCode();
    }

    public void addUser(Users user) {
        if (users == null)
            users = new HashSet<>();
        users.add(user);
    }

    public void addOrder(Orders order) {
        if (orders == null)
            orders = new HashSet<>();
        orders.add(order);
    }
}
