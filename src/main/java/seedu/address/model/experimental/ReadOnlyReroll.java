package seedu.address.model.experimental;

import seedu.address.model.entity.Character;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

public interface ReadOnlyReroll {
    public ReadOnlyEntities<Item> getItems();
    public ReadOnlyEntities<Character> getCharacters();
    public ReadOnlyEntities<Mob> getMobs();
}
