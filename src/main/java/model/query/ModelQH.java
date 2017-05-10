package model.query;

public abstract class ModelQH {


    protected static final String PARAM_ID_FILTER = "idFilter";
    protected static final String SPACE = " ";
    protected String name;
    protected String alias;
    protected String idField;


    public ModelQH(String name, String alias) {
        this.name = name;
        this.alias = alias;
        this.idField = "id";

    }

    public String from() {
        return new StringBuilder().append(" from ")
                .append(name).append(SPACE)
                .append(alias).append(SPACE)
                .toString();
//        return " from " + name + " " + alias + " ";

    }


    public String count() {
        return new StringBuilder().append(" select count ")
                .append(" ( ")
                .append(alias).append(".").append(idField)
                .append(" ) ")
                .toString();
    }

    public String filterById() {
        return new StringBuilder()
                .append(" where ").append(alias)
                .append(".").append(idField).append("=:")
                .append(PARAM_ID_FILTER).append(SPACE)
                .toString();
//        return " where " + alias + ".id =:" + PARAM_ID_FILTER;
    }

    public String joinFetch(String fieldName, String aliasTo) {
        return new StringBuilder().append(" join fetch ")
                .append(alias).append(".").append(fieldName).append(SPACE)
                .append(aliasTo).append(SPACE)
                .toString();
//        return " join fetch " + aliasFrom + "." + fieldName + " " + aliasTo + " ";
    }

    public String getParamIdFilter() {
        return PARAM_ID_FILTER;
    }

    abstract public String fetchRequiredProperties();

    public enum EntityNames {
        ADDRESS("Address"),
        CATEGORY("Category"),
        CONSOLE("Console"),
        GAME("Game"),
        ORDERS("Orders"),
        PHYSICAL_GAME("PhysicalGame"),
        SHOPPING_BAG("ShoppingBag"),
        SHOPPING_BAG_ROW("ShoppingBagRow"),
        USERS("Users"),
        ROLES("Roles"),
        PUBLISHER("Publisher");
        private String name;
        EntityNames(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            return name;
        }
    }

    public enum EntityAlias {
        ADDRESS("adr"),
        CATEGORY("cat"),
        CONSOLE("con"),
        GAME("ga"),
        ORDERS("ord"),
        PHYSICAL_GAME("pg"),
        SHOPPING_BAG("sb"),
        SHOPPING_BAG_ROW("sbr"),
        USERS("usr"),
        ROLES("rol"),
        PUBLISHER("pub");
        private String name;
        EntityAlias(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            return name;
        }
    }
}
