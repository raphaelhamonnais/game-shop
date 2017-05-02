package model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLES", schema = "sr03_web_project")
public class Roles {

    private String roleName;
    private Set<Users> users = new HashSet<>();

    public Roles() {}

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    @Id
    @Column(name = "role_name", nullable = false, length = 50)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
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

        Roles that = (Roles) o;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(roleName, that.roleName);
        return eb.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(roleName);
        return hcb.toHashCode();
    }

    public void addUser(Users aUser) {
        if (users == null)
            users = new HashSet<>();
        users.add(aUser);
    }
}
