package expresslibrary.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_BOOK_DISPLAYED_INDEX = "The book index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_DELETE_PERSON = "Please return all books before deleting the person!"
            + "\nAlternatively, use 'deletePerson PERSON_INDEX -f' to return all books while deleting the person.";
    public static final String MESSAGE_INVALID_DELETE_BOOK = "Please return the book before deleting it!"
            + "\nAlternatively, use 'deleteBook BOOK_INDEX -f' to return it while deleting the book.";
    public static final String MESSAGE_BOOK_BORROWED = "The book is already borrowed";
    public static final String MESSAGE_BOOK_BORROWED_NOT_FOUND = "A book borrowed by this person is"
            + " not found in the book list. \nPlease use 'listBook' first to list all books.";
    public static final String MESSAGE_BORROWER_NOT_FOUND = "The borrower of this book is not found in the person list,"
            + " please use listPerson to list all persons.";
    public static final String MESSAGE_BOOK_NOT_BORROWED = "The book is not borrowed";
    public static final String MESSAGE_BOOK_INVALID_BORROWER = "The book is not borrowed by this person";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person(s) listed!";
    public static final String MESSAGE_BOOK_FOUND_OVERVIEW = "%1$d book(s) found!";
    public static final String MESSAGE_BORROW_DATE_AFTER_CURRENT_DATE = "Borrowed date cannot be past today's date!";
    public static final String MESSAGE_BORROW_DATE_AFTER_DUE_DATE = "Borrowed date cannot be past due date!";
    public static final String MESSAGE_INVALID_DATE = "Date(s) must be valid and provided in the form dd/mm/yyyy!";
    public static final String MESSAGE_EARLY_DATE = "Due date must be after today's date!";
    public static final String MESSAGE_INVALID_EDIT_BORROW_DATE = "Book is not borrowed!";

}
