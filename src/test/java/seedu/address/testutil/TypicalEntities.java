package seedu.address.testutil;

import seedu.address.experimental.model.Reroll;
import seedu.address.model.entity.*;
import seedu.address.model.entity.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TypicalEntities {

    public static final Entity ITEM1 = new Item(new Name("Item1"), 3, 3, new HashSet<>());
    public static final Entity DUKE = new Character(new Name("Duke"), new Stats(2,2,2), 3, 3, new HashSet<>());

    public static Reroll getTypicalReroll() {
        Reroll rr = new Reroll();
        for (Entity entity : getTypicalEntities()) {
            rr.addEntity(entity);
        }
        return rr;
    }

    public static List<Entity> getTypicalEntities() {
        return new ArrayList<>(Arrays.asList(ITEM1, DUKE));
    }
}
