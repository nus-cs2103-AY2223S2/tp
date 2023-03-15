package seedu.address.model.experimental;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;

public interface ReadOnlyEntities<T extends Entity> {
    ObservableList<T> getEntityList();
}
