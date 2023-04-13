package mycelium.mycelium.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycelium.mycelium.model.util.Fuzzy;
import mycelium.mycelium.model.util.FuzzyComparable;

/**
 * This class is responsible for handling fuzzy matching of clients and projects.
 */
public class FuzzyManager {
    /**
     * Given an {@code ObservableList} of {@code FuzzyComparable} items and a query, returns a new list of items
     * ranked by their fuzzy match score with the query.
     */
    public static <T extends FuzzyComparable<String>> ObservableList<T> rankItems(ObservableList<T> items,
                                                                                  String query) {
        query = query.strip().toLowerCase();
        if (query.isEmpty()) {
            return items;
        }

        HashMap<T, Double> simpleScores = new HashMap<>();
        for (T item : items) {
            simpleScores.put(item, Fuzzy.delta(query, item.getFuzzyField().toLowerCase()));
        }

        Map<Boolean, List<T>> partitioned =
            items.stream().collect(Collectors.partitioningBy(item -> simpleScores.get(item) > 0.0));
        List<T> results = partitioned.get(true);
        List<T> remaining = partitioned.get(false);

        HashMap<T, Double> distances = new HashMap<>();
        for (T item : remaining) {
            distances.put(item, Fuzzy.levenshtein(query, item.getFuzzyField().toLowerCase()));
        }

        results.sort((item1, item2) -> Double.compare(simpleScores.get(item2), simpleScores.get(item1)));

        remaining.stream()
            .filter(item -> distances.get(item) > 0.0)
            .sorted((item1, item2) -> Double.compare(distances.get(item2), distances.get(item1)))
            .forEach(results::add);

        return FXCollections.observableArrayList(results);
    }
}

