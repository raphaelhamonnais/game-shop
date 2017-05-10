package model.query;

public class ConsoleQH extends ModelQH {

    public ConsoleQH() {
        super(EntityNames.CONSOLE.getName(), EntityAlias.CONSOLE.getName());
        this.idField = "consoleName";
    }

    @Override
    public String fetchRequiredProperties() {
        return "";
    }

    public String fetchPhysicalGames() {
        return joinFetch("physicalGames", EntityAlias.PHYSICAL_GAME.getName());
    }
}
