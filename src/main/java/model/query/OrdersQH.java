package model.query;

public class OrdersQH extends ModelQH {

    public OrdersQH() {
        super(EntityNames.ORDERS.getName(), EntityAlias.ORDERS.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return joinFetch("user", EntityAlias.USERS.getName()) +
                joinFetch("shoppingBag", EntityAlias.SHOPPING_BAG.getName()) +
                joinFetch("address", EntityAlias.ADDRESS.getName());
    }
}
