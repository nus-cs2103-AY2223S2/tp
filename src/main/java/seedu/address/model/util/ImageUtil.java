package seedu.address.model.util;

import static seedu.address.model.person.Image.IMAGE_PATH;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.imageio.ImageIO;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utility methods for {@code Image} file operations.
 */
public class ImageUtil {
    /**
     * Copies the image provided for use in BookFace.
     * @param stringPath Absolute path to image.
     * @return String file name of the new file.
     * @throws IOException when file I/O is unsuccessful.
     */
    public static String importImage(String stringPath) throws IOException, CommandException, InvalidPathException {
        try {
            checkDirectory();
        } catch (SecurityException se) {
            throw new CommandException("Unable to import due to permissions.");
        }
        if (stringPath == null || stringPath.isEmpty()) {
            throw new CommandException("Path to image has not been included.");
        }
        Path path = Paths.get(stringPath);
        if (!Files.exists(path)) {
            throw new CommandException("Referenced file does not exist.");
        }
        assert Files.exists(path) : "File path invalid";
        String type = Files.probeContentType(path);

        if (type == null) {
            throw new CommandException("File at path is not an image");
        }
        boolean validImageType = type.equals("image/png") || type.equals("image/jpeg");
        if (!validImageType) {
            throw new CommandException("Please only upload png or jpeg images.");
        }
        if (ImageIO.read(new File(stringPath)) == null) {
            throw new CommandException("Referenced file is not an image");
        }


        String newName = UUID.randomUUID().toString();
        String extension = ".png";
        if (type.contains("jpeg")) {
            extension = ".jpg";
        }
        Path destination = Paths.get(IMAGE_PATH + newName + extension);

        Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);

        return newName + extension;

    }

    /**
     * Delete image given file name.
     * @param fileName Name of file to be deleted.
     * @return false if image does not exist, true if deletion is successful.
     * @throws IOException when file I/O is unsuccessful.
     */
    public static boolean deleteImage(String fileName) throws IOException, ParseException {
        try {
            checkDirectory();
        } catch (SecurityException se) {
            throw new ParseException("Unable to delete due to permissions.");
        }
        Path path = Paths.get(IMAGE_PATH + fileName);
        if (!Files.exists(path)) {
            return false;
        }
        Files.delete(path);
        return true;
    }

    /**
     * Check if image exist and is valid.
     * @param filePath name of the image file/
     * @return True if image can exist and is valid, False otherwise.
     * @throws IOException when file I/O is unsuccessful.
     */
    public static boolean isValidImage(String filePath) throws IOException {
        try {
            checkDirectory();
        } catch (SecurityException se) {
            return false;
        }

        if (filePath == null || filePath.isEmpty()) {

            return false;
        }
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return false;
        }
        String type = Files.probeContentType(path);
        if (type == null) {

            return false;
        }
        boolean validImageType = type.equals("image/png") || type.equals("image/jpeg");
        if (!validImageType) {
            return false;
        }

        if (ImageIO.read(new File(filePath)) == null) {
            return false;
        }

        return true;
    }

    /**
     * Ensures directory for image exists.
     * @throws SecurityException when there is no permission to access the directory.
     */
    public static void checkDirectory() throws SecurityException {
        String directoryPath = IMAGE_PATH;
        File profilePictureDirectory = new File(directoryPath);
        if (!profilePictureDirectory.exists()) {
            profilePictureDirectory.mkdir();
        }
    }
}
