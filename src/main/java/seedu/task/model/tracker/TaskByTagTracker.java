package seedu.task.model.tracker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import seedu.task.model.Model;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Task;

/**
 * Implementation of the stats feature to sort by occurrences of each tag.
 */
public class TaskByTagTracker {

    /**
     * Outputs in descending order the most frequent tag and its corresponding number of occurrence.
     * @param model interface to obtain data from
     * @return output to be printed
     */
    public static String sort(Model model) {
        List<Task> lastShownList = model.getFilteredTaskList();
        int size = lastShownList.size();
        HashMap<Tag, Integer> counter = new HashMap<>();

        for (int i = 0; i < size; i++) {
            Set<Tag> tagSet = lastShownList.get(i).getTags();
            Iterator<Tag> itr = tagSet.iterator();
            while (itr.hasNext()) {
                counter = editHashMap(counter, itr.next());
            }
        }

        String output = orderAndPrint(counter);
        return output;
    }

    private static String orderAndPrint(HashMap<Tag, Integer> counter) {
        PriorityQueue<TagComparator> ordering = createOrdering(counter);
        String output = "";
        int idx = 0;
        int tags = ordering.size();

        while (idx < 10 && tags > 0) {
            TagComparator tc = ordering.poll();
            output = appendToBack(output, idx, tc);
            tags--;
            idx++;
        }
        return output;
    }

    private static HashMap<Tag, Integer> editHashMap(HashMap<Tag, Integer> hm, Tag tag) {
        if (hm.containsKey(tag)) {
            int tagCount = hm.remove(tag);
            hm.put(tag, tagCount + 1);
        } else {
            hm.put(tag, 1);
        }
        return hm;
    }
    private static PriorityQueue<TagComparator> createOrdering(HashMap<Tag, Integer> counter) {
        PriorityQueue<TagComparator> ordering = new PriorityQueue<>();

        for (Map.Entry<Tag, Integer> entry : counter.entrySet()) {
            ordering.add(new TagComparator(entry.getKey(), entry.getValue()));
        }
        return ordering;
    }
    private static String appendToBack(String str, int idx, TagComparator tc) {
        return str + (idx + 1) + ". " + tc.getName() + " (count: " + tc.getOccurrences() + ")\n";
    }
}
