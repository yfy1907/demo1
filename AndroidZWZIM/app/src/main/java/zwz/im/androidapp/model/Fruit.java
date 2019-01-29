package zwz.im.androidapp.model;

public class Fruit {

    private String name;

    private int imageId;

    private String type;

    public Fruit(String name, int imageId, String type) {
        this.name = name;
        this.imageId = imageId;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
