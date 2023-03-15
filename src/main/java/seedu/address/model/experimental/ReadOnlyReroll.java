package seedu.address.model.experimental;

import seedu.address.model.entity.Character;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

public interface ReadOnlyReroll {
    /**
     * Unmodifiable view
     * @return unmodifiable list
     */
    public ReadOnlyEntities<Item> getItems();

    /**
     * Unmodifiable view
     * @return unmodifiable list
     */
    public ReadOnlyEntities<Character> getCharacters();

    /**
     * Unmodifiable view
     * @return unmodifiable list
     */
    public ReadOnlyEntities<Mob> getMobs();
}
