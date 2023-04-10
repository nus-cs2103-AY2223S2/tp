package expresslibrary.storage;

import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalBooks.A_GAME_OF_THRONES;
import static expresslibrary.testutil.TypicalBooks.BELOVED;
import static expresslibrary.testutil.TypicalPersons.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.commons.util.JsonUtil;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import expresslibrary.testutil.ExpressLibraryBuilder;
import expresslibrary.testutil.TypicalExpressLibrary;

public class JsonSerializableExpressLibraryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableExpressLibraryTest");
    private static final Path TYPICAL_EXPRESSLIBRARY_FILE = TEST_DATA_FOLDER.resolve("typicalExpressLibrary.json");
    private static final Path TYPICAL_PERSON_WITH_BORROWED_BOOKS = TEST_DATA_FOLDER
            .resolve("typicalPersonWithBorrowedBooks.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonExpressLibrary.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonExpressLibrary.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPRESSLIBRARY_FILE,
                JsonSerializableExpressLibrary.class).get();
        ExpressLibrary expressLibraryFromFile = dataFromFile.toModelType();
        ExpressLibrary typicalExpressLibrary = TypicalExpressLibrary.getTypicalExpressLibrary();
        assertEquals(expressLibraryFromFile, typicalExpressLibrary);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableExpressLibrary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableExpressLibrary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableExpressLibrary.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_personHasBorrowedBooks_success() throws Exception {
        // Load test data from JSON file
        JsonSerializableExpressLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSON_WITH_BORROWED_BOOKS,
                JsonSerializableExpressLibrary.class).get();

        // Convert JSON data to ExpressLibrary object
        ExpressLibrary expressLibraryFromFile = dataFromFile.toModelType();

        // Get person from the resulting ExpressLibrary object
        Person personFromFile = expressLibraryFromFile.getPerson(ALICE);

        // Assert that the person has borrowed the correct book(s) with the correct loan dates
        Set<Book> expectedBooks = new HashSet<>(Arrays.asList(A_GAME_OF_THRONES, BELOVED));
        for (Book book : expectedBooks) {
            book.loanBookTo(personFromFile,
                LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 15));
            personFromFile.borrowBook(book);
        }
        Set<Book> actualBooks = personFromFile.getBooks();
        assertEquals(expectedBooks, actualBooks);

        //simulate building the ExpressLibrary from the JSON file
        ExpressLibraryBuilder expectedLibraryBuilder = new ExpressLibraryBuilder();

        for (Book book : actualBooks) {
            expectedLibraryBuilder.withBook(book);
        }

        ExpressLibrary expectedLibrary = expectedLibraryBuilder.withPerson(personFromFile).build();
        assertEquals(expectedLibrary, expressLibraryFromFile);
    }

}
