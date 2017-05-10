package model.query;

public class AddressQH extends ModelQH {

    public AddressQH() {
        super(EntityNames.ADDRESS.getName(), EntityAlias.ADDRESS.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return "";
    }
}
