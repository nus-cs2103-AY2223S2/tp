package codoc.model.person;


/**
 * The type Profile picture.
 */
public class ProfilePicture {
    public final String imagePath;

    public ProfilePicture(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return imagePath;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfilePicture // instanceof handles nulls
                && imagePath.equals(((ProfilePicture) other).imagePath)); // state check
    }

    @Override
    public int hashCode() {
        return imagePath.hashCode();
    }
}
