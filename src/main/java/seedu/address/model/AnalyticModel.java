package seedu.address.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import seedu.address.model.util.AnalyticsType;
/**
 * The API of the AnalyticModel component.
 */
public interface AnalyticModel {
    DoubleProperty getAnalyticsData(AnalyticsType type);
}
