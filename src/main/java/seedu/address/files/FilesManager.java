package seedu.address.files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.person.Person;


/**
 * The type Files manager.
 */
public class FilesManager implements seedu.address.files.Files {

    private static final Logger logger = Logger.getLogger(FilesManager.class.getName());

    private Person person;
    private FileStorage store;
    private PdfGenerator create;
    private String path;
    private List<Path> files;
    private List<String> fileNames;

    /**
     * Instantiates a new Files manager.
     *
     * @param person the person
     */
    public FilesManager(Person person) {
        this.person = person;
        store = new FileStorage(person.getName().fullName);
        path = "reports/" + person.getName().fullName;
        updateList();
    }

    public void initFile() {
        FileStorage.createDrc(path);
    }

    public void deleteAll() {
        FileStorage.deleteDrc(path);
    }

    /**
     * Delete file.
     *
     * @param fileName the file name
     */
    public void deleteFile(String fileName) {
        String uri = path + "/" + fileName;
        FileStorage.deleteFile(uri);
        updateList();
    }

    /**
     * Add file.
     */
    public void addFile() {
        try {
            store.uploadFile();
            setAllFiles();
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Display file.
     *
     * @param fileName the file name
     */
    public void displayFile(String fileName) {
        String filePath = this.path + "/" + fileName;
        Path path1 = Paths.get(filePath);
        FileReaderManager fileReaderManager = new FileReaderManager(path1);
        fileReaderManager.displayFile();
    }

    /**
     * Calculates and returns the next number to be used in the file naming format "number-mc".
     *
     * @param drc The directory path where the files are located.
     * @return The next number to be used in the file naming format "number-mc".
     */
    public int nextMcNumber(Path drc) {
        int highestNumber = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(drc, "*-mc.pdf")) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    String fileName = entry.getFileName().toString();
                    String[] tokens = fileName.split("-mc\\.pdf");
                    if (tokens.length > 0) {
                        int number = Integer.parseInt(tokens[0]);
                        highestNumber = Math.max(highestNumber, number);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highestNumber + 1;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    /**
     * Generates a medical certificate and saves it in the reports folder for the person associated with this
     * FilesManager instance. The generated file name will be the next available number for medical certificates
     * for this person.
     * @param doctorName the name of the doctor who issued the medical certificate
     * @param description a brief description of the reason for the medical certificate
     * @param days the number of days for which the medical certificate is valid
     */
    public void generateMc(String doctorName, String description, int days) {
        updateList();
        Path path2 = Paths.get(path);
        FileStorage.createDrc(path);
        create = new PdfGenerator(person,
                doctorName, description, days);
        try {
            create.generate(Integer.toString(nextMcNumber(path2)));
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        updateList();
    }

    /**
     * Read nth file.
     *
     * @param  number the number of the file to read
     */
    public void readNthFile(int number) {
        //Check if the files list is empty or the input number is invalid
        updateList();
        if (!isValidFileNumber(number)) {
            logger.log(Level.WARNING, "Invalid file number or no files exist.");
            return;
        }
        Path nthFilePath = files.get(number - 1);
        String fileName = nthFilePath.getFileName().toString();
        displayFile(fileName);
    }

    /**
     * Delete nth file.
     *
     * @param number the number of file to delete
     */
    public void deleteNthFile(int number) {
        //Check if the files list is empty or the input number is invalid
        updateList();
        if (!isValidFileNumber(number)) {
            logger.log(Level.WARNING, "Invalid file number or no files exist.");
            return;
        }
        Path nthFilePath = files.get(number - 1);
        String fileName = nthFilePath.getFileName().toString();
        deleteFile(fileName);
        updateList();
    }

    /**
     * Checks if the given file number is valid.
     *
     * @param number the number of the file to check
     * @return true if the file number is valid, false otherwise
     */
    private boolean isValidFileNumber(int number) {
        return !files.isEmpty() && number > 0 && number <= files.size();
    }

    /**
     * Sets the list of all files in the directory.
     */
    private void setAllFiles() {
        Path directory = Paths.get(path);
        files = new ArrayList<>();
        if (!isEmptyDirectory() && Files.exists(directory) && Files.isDirectory(directory)) {
            try (Stream<Path> stream = Files.walk(directory)) {
                stream.filter(Files::isRegularFile)
                        .forEach(files::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the list of file names based on the list of files in the specified directory.
     * If there are no files or the files list is null, sets an empty list for the file names.
     */
    private void setFileNames() {
        if (files != null) {
            fileNames = files.stream()
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } else {
            fileNames = Collections.emptyList();
        }
    }

    /**
     * Checks if the directory is empty.
     * @return true if the directory is empty, false otherwise
     */
    private boolean isEmptyDirectory() {
        Path directory = Paths.get(path);
        if (Files.isDirectory(directory)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                return !directoryStream.iterator().hasNext();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void updateList() {
        setAllFiles();
        setFileNames();
    }
}
