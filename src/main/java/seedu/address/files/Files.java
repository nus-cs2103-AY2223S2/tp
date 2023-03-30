package seedu.address.files;

import java.nio.file.Path;
import java.util.List;

/**
 * Represents the interface for managing files associated with a specific person.
 */
public interface Files {
    /**
     * Initializes the files directory for the person.
     */
    void initFile();

    /**
     * Deletes all files in the person's files directory.
     */
    void deleteAll();

    /**
     * Deletes a file in the person's files directory by its file name.
     *
     * @param fileName The name of the file to be deleted.
     */
    void deleteFile(String fileName);

    /**
     * Adds a new file to the person's files directory.
     */
    void addFile();

    /**
     * Displays a file from the person's files directory by its file name.
     *
     * @param fileName The name of the file to be displayed.
     */
    void displayFile(String fileName);

    /**
     * Calculates and returns the next number to be used in the file naming format "number-mc".
     *
     * @param drc The directory path where the files are located.
     * @return The next number to be used in the file naming format "number-mc".
     */
    int nextMcNumber(Path drc);

    /**
     * Retrieves a list of file names in the person's files directory.
     *
     * @return A list of file names.
     */
    List<String> getFileNames();

    /**
     * Generates a medical certificate and saves it in the reports folder for the person associated with this
     * FilesManager instance. The generated file name will be the next available number for medical certificates
     * for this person.
     *
     * @param doctorName  the name of the doctor who issued the medical certificate
     * @param description a brief description of the reason for the medical certificate
     * @param days        the number of days for which the medical certificate is valid
     */
    void generateMc(String doctorName, String description, int days);

    /**
     * Reads and displays the nth file in the person's files directory.
     *
     * @param number The index of the file to be read.
     */
    void readNthFile(int number);

    /**
     * Deletes the nth file in the person's files directory.
     *
     * @param number The index of the file to be deleted.
     */
    void deleteNthFile(int number);

}

