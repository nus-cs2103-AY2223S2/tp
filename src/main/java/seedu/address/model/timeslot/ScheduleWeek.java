package seedu.address.model.timeslot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.DuplicateGroupException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class ScheduleWeek {
    private final ObservableList<ScheduleDay> internalList = FXCollections.observableArrayList();
    private final ObservableList<ScheduleDay> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


}
