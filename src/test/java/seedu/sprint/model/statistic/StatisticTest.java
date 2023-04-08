package seedu.sprint.model.statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sprint.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.sprint.model.application.Application;
import seedu.sprint.testutil.TypicalApplications;


public class StatisticTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Statistic(null));
    }

    @Test
    public void getStatsMap_validStatsMapSize_success() {
        ObservableList<Application> dummyListEmpty = FXCollections.observableArrayList();
        Statistic test = new Statistic(dummyListEmpty);
        assertEquals(test.getStatsMap().size(), 4);
    }

    @Test
    public void getTotalNum_validTotalNumEmpty_success() {
        ObservableList<Application> dummyListEmpty = FXCollections.observableArrayList();
        Statistic test = new Statistic(dummyListEmpty);
        assertEquals(test.getTotalNum(), 0);
    }

    @Test
    public void getTotalNum_validTotalNum_success() {
        ObservableList<Application> dummyList = FXCollections.observableArrayList();
        dummyList.add(TypicalApplications.APPLE);
        dummyList.add(TypicalApplications.META);
        dummyList.add(TypicalApplications.GOOGLE);
        Statistic test = new Statistic(dummyList);
        assertEquals(test.getTotalNum(), 3);
    }
}
