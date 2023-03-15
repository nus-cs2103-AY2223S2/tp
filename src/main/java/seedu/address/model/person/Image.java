package seedu.address.model.person;

/**
 * Represents a Person's image in the address book.
 */
public class Image {

    public static final String IMAGE_PATH = "data/images/";
    public static final String DEFAULT_IMAGE = "default_image.png";
    public final String imageName;

    /**
     * Constructs an {@code Image}.
     *
     * @param imageName A valid image name.
     */
    public Image(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Overloaded constructor for a default image.
     */
    public Image() {
        this.imageName = DEFAULT_IMAGE;
    }

    public String getFullString() {
        return IMAGE_PATH + imageName;
    }

    @Override
    public String toString() {
        return imageName;
    }

    @Override
    public int hashCode() {
        return imageName.hashCode();
    }
}
