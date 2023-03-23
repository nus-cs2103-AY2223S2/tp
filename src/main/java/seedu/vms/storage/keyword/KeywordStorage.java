package seedu.vms.storage.keyword;

import java.io.IOException;

import seedu.vms.model.keyword.KeywordManager;

/**
 * Represents the storage for {@link KeywordManager}.
 */
public interface KeywordStorage {
    /**
     * Loads the {@code KeywordManager} as defined by the user.
     *
     * @throws IOException if an I/O error occurs.
     */
    public KeywordManager loadKeywords() throws IOException;


    /**
     * Saves the specified {@code KeywordManager} to hard disk.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void saveKeywords(KeywordManager manager) throws IOException;
}
