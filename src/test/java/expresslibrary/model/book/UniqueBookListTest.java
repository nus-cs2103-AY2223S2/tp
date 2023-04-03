package expresslibrary.model.book;

import static expresslibrary.logic.commands.CommandTestUtil.VALID_AUTHOR_ALEX;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_AUTHOR_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TITLE_ANIMAL;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TITLE_HARRY;
import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalBooks.A_GAME_OF_THRONES;
import static expresslibrary.testutil.TypicalBooks.BELOVED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import expresslibrary.model.book.exceptions.BookNotFoundException;
import expresslibrary.model.book.exceptions.DuplicateBookException;
import expresslibrary.testutil.BookBuilder;

public class UniqueBookListTest {

    private final UniqueBookList uniqueBookList = new UniqueBookList();

    @Test
    public void contains_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.contains(null));
    }

    @Test
    public void contains_bookNotInList_returnsFalse() {
        assertFalse(uniqueBookList.contains(A_GAME_OF_THRONES));
    }

    @Test
    public void contains_bookInList_returnsTrue() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        assertTrue(uniqueBookList.contains(A_GAME_OF_THRONES));
    }

    @Test
    public void contains_bookWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        Book editedAGameOfThrones = new BookBuilder(A_GAME_OF_THRONES).withTitle(VALID_TITLE_ANIMAL)
                .withAuthor(VALID_AUTHOR_ALEX).build();
        assertTrue(uniqueBookList.contains(editedAGameOfThrones));
    }

    @Test
    public void get_correctBook() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        assertEquals(A_GAME_OF_THRONES, uniqueBookList.get(A_GAME_OF_THRONES));
    }

    @Test
    public void get_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.get(null));
    }

    @Test
    public void get_bookNotInList_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.get(A_GAME_OF_THRONES));
    }

    @Test
    public void add_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.add(null));
    }

    @Test
    public void add_duplicateBook_throwsDuplicateBookException() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.add(A_GAME_OF_THRONES));
    }

    @Test
    public void setBook_nullTargetBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBook(null, A_GAME_OF_THRONES));
    }

    @Test
    public void setBook_nullEditedBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBook(A_GAME_OF_THRONES, null));
    }

    @Test
    public void setBook_targetBookNotInList_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.setBook(A_GAME_OF_THRONES, A_GAME_OF_THRONES));
    }

    @Test
    public void setBook_editedBookIsSameBook_success() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        uniqueBookList.setBook(A_GAME_OF_THRONES, A_GAME_OF_THRONES);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(A_GAME_OF_THRONES);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasSameIdentity_success() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        Book editedAGameOfThrones = new BookBuilder(A_GAME_OF_THRONES).withTitle(VALID_TITLE_HARRY)
                .withAuthor(VALID_AUTHOR_ROWLING).build();
        uniqueBookList.setBook(A_GAME_OF_THRONES, editedAGameOfThrones);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(editedAGameOfThrones);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasDifferentIdentity_success() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        uniqueBookList.setBook(A_GAME_OF_THRONES, BELOVED);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BELOVED);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBook_editedBookHasNonUniqueIdentity_throwsDuplicateBookException() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        uniqueBookList.add(BELOVED);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.setBook(A_GAME_OF_THRONES, BELOVED));
    }

    @Test
    public void remove_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.remove(null));
    }

    @Test
    public void remove_bookDoesNotExist_throwsBookNotFoundException() {
        assertThrows(BookNotFoundException.class, () -> uniqueBookList.remove(A_GAME_OF_THRONES));
    }

    @Test
    public void remove_existingBook_removesBook() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        uniqueBookList.remove(A_GAME_OF_THRONES);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_nullUniqueBookList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBooks((UniqueBookList) null));
    }

    @Test
    public void setBooks_uniqueBookList_replacesOwnListWithProvidedUniqueBookList() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BELOVED);
        uniqueBookList.setBooks(expectedUniqueBookList);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setBooks((List<Book>) null));
    }

    @Test
    public void setBooks_list_replacesOwnListWithProvidedList() {
        uniqueBookList.add(A_GAME_OF_THRONES);
        List<Book> bookList = Collections.singletonList(BELOVED);
        uniqueBookList.setBooks(bookList);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BELOVED);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setBooks_listWithDuplicateBooks_throwsDuplicateBookException() {
        List<Book> listWithDuplicateBooks = Arrays.asList(A_GAME_OF_THRONES, A_GAME_OF_THRONES);
        assertThrows(DuplicateBookException.class, () -> uniqueBookList.setBooks(listWithDuplicateBooks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookList.asUnmodifiableObservableList().remove(0));
    }
}
