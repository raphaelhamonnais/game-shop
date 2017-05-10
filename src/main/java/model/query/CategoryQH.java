package model.query;

public class CategoryQH extends ModelQH {

    public CategoryQH() {
        super(EntityNames.CATEGORY.getName(), EntityAlias.CATEGORY.getName());
        this.idField = "catName";
    }

    @Override
    public String fetchRequiredProperties() {
        return "";
    }
}
