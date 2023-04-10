package expresslibrary.model.book;

import static expresslibrary.testutil.TypicalBooks.BELOVED;
import static expresslibrary.testutil.TypicalBooks.DUNE;
import static expresslibrary.testutil.TypicalPersons.ALICE;
import static expresslibrary.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import expresslibrary.testutil.BookBuilder;

public class BookTest {

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(BELOVED.isSameBook(BELOVED));

        // null -> returns false
        assertFalse(BELOVED.isSameBook(null));

        // same isbn, all other attributes different -> returns true
        // add fields later
        Book editedBeloved = new BookBuilder(BELOVED).build();
        assertTrue(BELOVED.isSameBook(editedBeloved));

        // different isbn, all other attributes same -> returns false
        editedBeloved = new BookBuilder(BELOVED).withIsbn("0813240123409").build();
        assertFalse(BELOVED.isSameBook(editedBeloved));

        // title differs in case, all other attributes same -> returns true
        Book editedDune = new BookBuilder(DUNE).withTitle(DUNE.getTitle().title.toLowerCase()).build();
        assertTrue(DUNE.isSameBook(editedDune));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Book belovedCopy = new BookBuilder(BELOVED).build();
        assertEquals(belovedCopy.getAuthor(), BELOVED.getAuthor());
        assertEquals(belovedCopy.getTitle(), BELOVED.getTitle());
        assertEquals(belovedCopy.getIsbn(), BELOVED.getIsbn());
        assertEquals(belovedCopy.getBorrower(), BELOVED.getBorrower());
        assertEquals(belovedCopy.getBorrowDate(), BELOVED.getBorrowDate());
        assertEquals(belovedCopy.getDueDate(), BELOVED.getDueDate());
        assertEquals(BELOVED, belovedCopy);

        // same object -> returns true
        assertEquals(BELOVED, BELOVED);

        // null -> returns false
        assertNotEquals(BELOVED, null);

        // different type -> returns false
        assertNotEquals(BELOVED, 5);

        // different person -> returns false
        assertNotEquals(BELOVED, DUNE);

        // different title -> returns false
        Book editedBeloved = new BookBuilder(BELOVED).withTitle(DUNE.getTitle().title).build();
        assertNotEquals(BELOVED, editedBeloved);

        // different author -> returns false
        editedBeloved = new BookBuilder(BELOVED).withAuthor(DUNE.getAuthor().name).build();
        assertNotEquals(BELOVED, editedBeloved);

        // different isbn -> returns false
        editedBeloved = new BookBuilder(BELOVED).withIsbn(DUNE.getIsbn().isbn).build();
        assertNotEquals(BELOVED, editedBeloved);
    }

    @Test
    public void loanBookTo_validInput_loanSuccessful() {
        Book belovedCopy = new BookBuilder(BELOVED).build();
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);

        belovedCopy.loanBookTo(ALICE, borrowDate, dueDate);

        assertEquals(ALICE, belovedCopy.getBorrower());
        assertEquals(borrowDate, belovedCopy.getBorrowDate());
        assertEquals(dueDate, belovedCopy.getDueDate());
    }

    // Test case for returnBook method
    @Test
    public void returnBook_bookWasLoaned_bookReturnedSuccessfully() {
        Book book = new BookBuilder(DUNE).build();
        LocalDate borrowDate = LocalDate.now().minusDays(7);
        LocalDate dueDate = borrowDate.plusDays(14);

        book.loanBookTo(BOB, borrowDate, dueDate);

        book.returnBook();

        assertNull(book.getBorrower());
        assertNull(book.getBorrowDate());
        assertNull(book.getDueDate());
    }

    @Test
    public void testGetIsBorrowedReturnsFalseWhenBookNotBorrowed() {
        assertFalse(BELOVED.getIsBorrowed());
    }

    @Test
    public void testGetIsBorrowedReturnsTrueWhenBookBorrowed() {
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusWeeks(3);
        Book belovedCopy = new BookBuilder(BELOVED).build();
        belovedCopy.loanBookTo(ALICE, borrowDate, dueDate);
        assertTrue(belovedCopy.getIsBorrowed());
    }

    @Test
    public void testGetIsBorrowedReturnsFalseWhenBookReturned() {
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusWeeks(3);
        Book duneCopy = new BookBuilder(DUNE).build();
        duneCopy.loanBookTo(BOB, borrowDate, dueDate);
        duneCopy.returnBook();
        assertFalse(duneCopy.getIsBorrowed());
    }


    @Test
    public void testHashCode() {
        assertEquals(BELOVED.hashCode(), BELOVED.hashCode());
        assertNotEquals(BELOVED.hashCode(), DUNE.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Beloved | Toni Morrison", BELOVED.toString());
        assertEquals("Dune | Frank Herbert", DUNE.toString());
    }
}
