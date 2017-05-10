package model.query;

public class ShoppingBagQH extends ModelQH {

    public ShoppingBagQH() {
        super(EntityNames.SHOPPING_BAG.getName(), EntityAlias.SHOPPING_BAG.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return joinFetch("user", EntityAlias.USERS.getName()) +
                joinFetch("shoppingBagRows", EntityAlias.SHOPPING_BAG_ROW.getName()) +
                "";
    }
}
