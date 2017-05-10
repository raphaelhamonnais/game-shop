package model.query;

public class PhysicalGameQH extends ModelQH {

    public PhysicalGameQH() {
        super(EntityNames.PHYSICAL_GAME.getName(), EntityAlias.PHYSICAL_GAME.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return joinFetch("console", EntityAlias.CONSOLE.getName()) +
                joinFetch("game", EntityAlias.GAME.getName()) +
                "";
    }
}
