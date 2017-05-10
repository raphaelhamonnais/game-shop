package model.query;

public class RolesQH extends ModelQH {

    public RolesQH() {
        super(EntityNames.ROLES.getName(), EntityAlias.ROLES.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return "";
    }
}
