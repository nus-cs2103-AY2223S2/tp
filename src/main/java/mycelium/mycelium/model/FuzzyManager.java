package mycelium.mycelium.model;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import mycelium.mycelium.commons.core.LogsCenter;
import mycelium.mycelium.model.util.FuzzyComparable;

/**
 * This class is responsible for handling fuzzy matching of clients and projects.
 */
public class FuzzyManager {
    private static final Logger logger = LogsCenter.getLogger(FuzzyManager.class);

    /**
     * Given an {@code ObservableList} of {@code FuzzyComparable} items and a query, returns a new list of items
     * ranked by their fuzzy match score with the query.
     */
    public static <T extends FuzzyComparable<String>> ObservableList<T> rankItems(ObservableList<T> items,
                                                                                  String query) {
        if (query.isEmpty()) {
            return items;
        }
        logger.fine("Received fuzzy match request for items with query = " + query);
        return items.filtered(item -> item.fuzzyCompareTo(query) > 0.0)
            .sorted((item1, item2) -> Double.compare(item2.fuzzyCompareTo(query), item1.fuzzyCompareTo(query)));
    }
}

