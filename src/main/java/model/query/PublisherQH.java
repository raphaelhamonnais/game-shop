package model.query;

public class PublisherQH extends ModelQH {

    public PublisherQH() {
        super(EntityNames.PUBLISHER.getName(), EntityAlias.PUBLISHER.getName());
    }

    @Override
    public String fetchRequiredProperties() {
        return "";
    }
}
