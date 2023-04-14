package seedu.library.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.library.storage.JsonAdaptedBookmark.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBookmarks.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Rating;
import seedu.library.model.bookmark.Title;

public class JsonAdaptedBookmarkTest {
    private static final String INVALID_TITLE = " ";
    private static final JsonAdaptedProgress INVALID_PROGRESS = new JsonAdaptedProgress("a", "b", "c");
    private static final String INVALID_AUTHOR = " ";
    private static final String INVALID_GENRE = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_RATING = "-1";

    private static final String VALID_TITLE = BENSON.getTitle().toString();
    private static final JsonAdaptedProgress VALID_PROGRESS = new JsonAdaptedProgress(BENSON.getProgress());
    private static final String VALID_GENRE = BENSON.getGenre().toString();
    private static final String VALID_AUTHOR = BENSON.getAuthor().toString();
    private static final String VALID_URL = BENSON.getUrl().toString();
    private static final String VALID_RATING = "5";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validBookmarkDetails_returnsBookmark() throws Exception {
        JsonAdaptedBookmark bookmark = new JsonAdaptedBookmark(BENSON);
        assertEquals(BENSON, bookmark.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark =
                new JsonAdaptedBookmark(INVALID_TITLE, VALID_PROGRESS,
                        VALID_GENRE, VALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark = new JsonAdaptedBookmark(null,
                VALID_PROGRESS, VALID_GENRE, VALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

    @Test
    public void toModelType_invalidProgress_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark =
                new JsonAdaptedBookmark(VALID_TITLE, INVALID_PROGRESS,
                        VALID_GENRE, VALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = Progress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

    //    @Test
    //    public void toModelType_nullProgress_throwsIllegalValueException() {
    //        JsonAdaptedBookmark bookmark = new JsonAdaptedBookmark(VALID_TITLE, null,
    //                VALID_GENRE, VALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Progress.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    //    }

    @Test
    public void toModelType_invalidGenre_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark =
                new JsonAdaptedBookmark(VALID_TITLE, VALID_PROGRESS,
                        INVALID_GENRE, VALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = Genre.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

    @Test
    public void toModelType_nullGenre_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark = new JsonAdaptedBookmark(VALID_TITLE,
                VALID_PROGRESS, null, VALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Genre.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

    @Test
    public void toModelType_invalidAuthor_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark =
                new JsonAdaptedBookmark(VALID_TITLE, VALID_PROGRESS,
                        VALID_GENRE, INVALID_AUTHOR, VALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = Author.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

    //    @Test
    //    public void toModelType_nullAuthor_throwsIllegalValueException() {
    //        JsonAdaptedBookmark bookmark = new JsonAdaptedBookmark(VALID_TITLE,
    //                VALID_PROGRESS, VALID_GENRE, null, VALID_RATING, VALID_URL, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    //    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedBookmark bookmark =
                new JsonAdaptedBookmark(VALID_TITLE, VALID_PROGRESS,
                        VALID_GENRE, VALID_AUTHOR, VALID_RATING, VALID_URL, invalidTags);
        assertThrows(IllegalValueException.class, bookmark::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedBookmark bookmark =
                new JsonAdaptedBookmark(VALID_TITLE, VALID_PROGRESS,
                        VALID_GENRE, VALID_AUTHOR, INVALID_RATING, VALID_URL, VALID_TAGS);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bookmark::toModelType);
    }

}
