package zwz.im.androidapp.model;

public class Msg {

    public static final int TYPE_RECEIVED = 0;

    public static final int TYPE_SENT = 1;

    private String content;

    private int type, imageId;

    public Msg(String content, int type, int imageId) {
        this.content = content;
        this.type = type;
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
