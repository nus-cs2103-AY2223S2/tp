package seedu.address.storage;

import java.io.File;

/**
 * The type Image storage.
 */
public class ImageStorage {

    private String username;
    /**
     * Instantiates a new Image storage.
     *
     * @param username the username
     */
    public ImageStorage(String username) {
        this.username = username;
        String path = "src/main/resources/images/" + username;
        createDrc(path);
    }

    private void createDrc(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Delete drc.
     *
     * @param path the path
     */
    public static void deleteDrc(String path) {
        String path1 = "src/main/resources/images/" + path;
        File directory = new File(path1);
        if (directory.exists()) {
            directory.delete();
        }
    }
}
