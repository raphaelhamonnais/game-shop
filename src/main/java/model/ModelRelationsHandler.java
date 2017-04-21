package model;

/**

 */
public class ModelRelationsHandler {

    public static void mapRelations(Game aGame, Console aConsole, PhysicalGame aPhysicalGame) {
        // Setting up the physical game (setGame and setConsole MUST be
        //      done before adding the PhysicalGame to the Game and Console
        //      collections, see PhysicalGame.checkConsistency() method)
        aPhysicalGame.setGame(aGame);
        aPhysicalGame.setConsole(aConsole);
        aGame.addPhysicalGame(aPhysicalGame);
        aConsole.addPhysicalGame(aPhysicalGame);
    }

    public static void mapRelations(Game aGame, Category aCategory) {
        aCategory.addGame(aGame);
        aGame.addCategory(aCategory);
    }

    public static void mapRelations(Users aUser, ShoppingBag aShoppingBag) {
        aShoppingBag.setUser(aUser);
        aUser.addShoppingBag(aShoppingBag);
    }

    public static void mapRelations(Users aUser, Address anAddress) {
        aUser.addAddress(anAddress);
        anAddress.addUser(aUser);
    }

    public static void mapRelations(Orders anOrder, ShoppingBag aShoppingBag) {
        anOrder.setOrderAmount(aShoppingBag.calculateAmount());
        anOrder.setShoppingBag(aShoppingBag);
        aShoppingBag.setOrder(anOrder);
//        anOrder.setUser(aShoppingBag.getUser());
    }

    public static void mapRelations(Orders anOrder, Users aUser) {
        anOrder.setUser(aUser);
        aUser.addOrder(anOrder);
    }

    public static void mapRelations(Orders anOrder, Address anAddress) {
        anOrder.setAddress(anAddress);
        anAddress.addOrder(anOrder);
    }

    public static void mapRelations(ShoppingBagRow aShoppingBagRow, ShoppingBag aShoppingBag, PhysicalGame aPhysicalGame) {
        aShoppingBagRow.setShoppingBag(aShoppingBag);
        aShoppingBagRow.setPhysicalGame(aPhysicalGame);
        aShoppingBag.addShoppingBagRow(aShoppingBagRow);
        aPhysicalGame.addShoppingBagRow(aShoppingBagRow);
    }
}
