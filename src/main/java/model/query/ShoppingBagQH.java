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

    public String getOpenedShoppingBag(boolean and) {
        StringBuilder stringBuilder;

        stringBuilder = (and)
                ? new StringBuilder(" and ")
                : new StringBuilder(" where ");
        return stringBuilder
                .append(EntityAlias.SHOPPING_BAG.getName())
                .append(".").append("bought").append("=false")
                .append(SPACE)
                .toString();
    }
}
