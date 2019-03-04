package zwz.im.androidapp.model;

import java.util.List;

public class Fruit {

    private String name;

    private int imageId;

    private String type;

    private List<Fruit> childList;

    public Fruit(String name, int imageId, String type, List<Fruit> childList) {
        this.name = name;
        this.imageId = imageId;

        this.type = type;
        this.childList = childList;
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

    public List<Fruit> getChildList() {
        return childList;
    }

    public void setChildList(List<Fruit> childList) {
        this.childList = childList;
    }

}
