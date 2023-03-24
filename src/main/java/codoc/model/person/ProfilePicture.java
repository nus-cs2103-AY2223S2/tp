package codoc.model.person;


/**
 * The type Profile picture.
 */
public class ProfilePicture {
    public final String profilePicturePath;

    public ProfilePicture(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
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
