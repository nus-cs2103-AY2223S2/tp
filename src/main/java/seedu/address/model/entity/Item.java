package seedu.address.model.entity;

import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an item, which is a child class of Entity
 */
public class Item extends Entity {
    public Item(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
