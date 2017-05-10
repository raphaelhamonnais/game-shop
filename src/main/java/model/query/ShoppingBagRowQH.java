package model.query;

public class ShoppingBagRowQH extends ModelQH {

    public ShoppingBagRowQH() {
        super(EntityNames.SHOPPING_BAG_ROW.getName(), EntityAlias.SHOPPING_BAG_ROW.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return joinFetch("physicalGame", EntityAlias.PHYSICAL_GAME.getName()) +
                "";
    }
}
