package model.query;

public class GameQH extends ModelQH {

    public GameQH() {
        super(EntityNames.GAME.getName(), EntityAlias.GAME.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return joinFetch("categories", EntityAlias.CATEGORY.getName()) +
                joinFetch("publisher", EntityAlias.PUBLISHER.getName()) +
                "";
    }

    public String fetchPhysicalGames() {
        return joinFetch("physicalGames", EntityAlias.PHYSICAL_GAME.getName());
    }
}
