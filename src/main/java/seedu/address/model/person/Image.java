package seedu.address.model.person;

/**
 * Represents a Person's image in the address book.
 */
public class Image {
    private static final String IMAGE_PATH = "";
    private static final String DEFAULT_IMAGE = "johndoe.png";
    public final String imageName;

    /**
     * Constructs an {@code Image}.
     *
     * @param imageName A valid image name.
     */
    public Image(String imageName) {
        this.imageName = imageName;
    }

    public Image() {
        this.imageName = DEFAULT_IMAGE;
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
