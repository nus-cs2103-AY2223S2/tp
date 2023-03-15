package seedu.address.model.experimental;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Character;

import java.util.List;

import static java.util.Objects.requireNonNull;

// Abstraction of all operations on characters
public class RerollCharacters extends RerollEntities<Character> {

    // For convenience
    private final UniqueEntityList<Character> characters = entities;

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollCharacters
                && characters.equals(((RerollCharacters) other).characters));
    }

}
