package codoc.model.person;


import java.io.InputStream;

/**
 * The type Profile picture.
 */
public class ProfilePicture {
    public final String profilePicturePath;
    public final InputStream profilePictureInputStream;

    /**
     * Get picture.
     * @param profilePicturePath string path
     */
    public ProfilePicture(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
        this.profilePictureInputStream = this.getClass().getResourceAsStream(profilePicturePath);
    }

    @Override
    public String toString() {
        return profilePicturePath;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfilePicture // instanceof handles nulls
                && profilePicturePath.equals(((ProfilePicture) other).profilePicturePath)); // state check
    }

    @Override
    public int hashCode() {
        return profilePicturePath.hashCode();
    }
}
