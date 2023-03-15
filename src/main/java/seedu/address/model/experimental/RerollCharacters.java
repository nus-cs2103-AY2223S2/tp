package seedu.address.model.experimental;

import seedu.address.model.entity.Character;

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
