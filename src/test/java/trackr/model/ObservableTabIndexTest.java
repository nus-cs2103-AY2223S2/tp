package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;

//@@author arkarsg
public class ObservableTabIndexTest {

    @Test
    public void updateToTab_updatesTabIndex_success() {
        ObservableTabIndex.updateToTab(1);
        int initialInteger = 1;
        int dupInteger = 1;
        int integerToObserve = initialInteger + dupInteger;

        SimpleIntegerProperty initialObservable = (SimpleIntegerProperty) ObservableTabIndex.valueProperty();
        SimpleIntegerProperty dupObservable = (SimpleIntegerProperty) ObservableTabIndex.valueProperty();
        NumberBinding observableToObserve = initialObservable.add(dupObservable);

        assertEquals(integerToObserve, 2);
        assertEquals(observableToObserve.getValue(), 2);

        initialInteger = 2;
        dupInteger = 2;
        ObservableTabIndex.updateToTab(2);

        assertFalse(integerToObserve == 4);
        assertEquals(observableToObserve.getValue(), 4);
    }
}
