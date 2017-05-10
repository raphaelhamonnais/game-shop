package model.query;


public class UsersQH extends ModelQH {

    public UsersQH() {
        super(EntityNames.USERS.getName(), EntityAlias.USERS.getName());
        this.idField = "userLogin";
    }

    @Override
    public String fetchRequiredProperties() {
        return joinFetch("addresses", EntityAlias.ADDRESS.getName()) +
                joinFetch("roles", EntityAlias.ROLES.getName());
    }

    public String removeAddressAlias(String s) {
        return s.replace(
                alias + ".addresses " + EntityAlias.ADDRESS.getName(),
                alias + ".addresses "
        );
    }
}
