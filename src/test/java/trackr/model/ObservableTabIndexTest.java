package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;

public class ObservableTabIndexTest {

    @Test
    public void updateToTab_updatesTabIndex_success() {
        ObservableTabIndex.updateToTab(1);
        int initialInteger = 1;
        int dupInteger = 1;
        int sumInteger = initialInteger + dupInteger;

        SimpleIntegerProperty initialObservable = (SimpleIntegerProperty) ObservableTabIndex.valueProperty();
        SimpleIntegerProperty dupObservable = (SimpleIntegerProperty) ObservableTabIndex.valueProperty();
        NumberBinding sumObservable = initialObservable.add(dupObservable);

        assertEquals(sumInteger, 2);
        assertEquals(sumObservable.getValue(), 2);
 
        initialInteger = 2;
        dupInteger = 2;
        ObservableTabIndex.updateToTab(2);
        assertFalse(sumInteger == 4);
        assertEquals(sumObservable.getValue(), 4);

   } 
}
