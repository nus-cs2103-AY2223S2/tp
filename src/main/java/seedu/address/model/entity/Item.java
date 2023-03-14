package seedu.address.model.entity;

import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;

public class Item extends Entity {
    public Item(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
