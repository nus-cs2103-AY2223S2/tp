package seedu.task.model.tracker;

import java.util.*;

import seedu.task.model.Model;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Task;

public class TaskByTagTracker {

    public static String sort(Model model) {
        List<Task> lastShownList = model.getFilteredTaskList();
        int size = lastShownList.size();
        HashMap<String, Integer> counter = new HashMap<>();

        for (int i = 0; i < size; i++) {
            Set<Tag> tagSet = lastShownList.get(i).getTags();
            Iterator itr = tagSet.iterator();
            while (itr.hasNext()) {
                Tag tagEntry = (Tag) itr.next();
                String tag = tagEntry.toString();
                if (counter.containsKey(tag)) {
                    int tagCount = counter.remove(tag);
                    counter.put(tag, tagCount + 1);
                }
                else {
                    counter.put(tag, 1);
                }
            }
        }

        TreeMap<String, Integer> counted = new TreeMap<>(counter);
        PriorityQueue<TagComparator> ordering = new PriorityQueue<>();

        for (Map.Entry<String, Integer> entry : counted.entrySet()) {
            ordering.add(new TagComparator(entry.getKey(), entry.getValue()));
        }

        String output = "";
        int idx = 0;
        int tags = ordering.size();

        while (idx < 10 && tags > 0) {
            TagComparator tc = ordering.poll();
            output += (idx + 1) + ". " + tc.getName() + " (count: " + tc.getOccurrences() + ")\n";
            tags--;
            idx++;
        }

        return output;
    }
}
