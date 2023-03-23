package seedu.vms.model.keyword;

import seedu.vms.model.StorageModel;

/**
 * A manager to handle {@code Keyword}.
 */
public class KeywordManager extends StorageModel<Keyword> implements ReadOnlyKeywordManager {
    /**
     * Constructs an empty {@code KeywordManager}.
     */
    public KeywordManager() {

    }

    /**
     * Creates an AppointmentManager using the keywords in the {@code toBeCopied}
     */
    public KeywordManager(ReadOnlyKeywordManager toBeCopied) {
        super(toBeCopied);
    }
}
