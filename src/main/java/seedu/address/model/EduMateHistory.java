package seedu.address.model;

import java.util.ArrayList;

public class EduMateHistory {

    private final ArrayList<String> eduMateHistory;
    private int index;
    private final int minIndex = 0;
    private final int maxIndex = 100;

    public EduMateHistory(ArrayList<String> eduMateHistory) {
        this.eduMateHistory = eduMateHistory;
    }

    public String getPreviousCommand(boolean isUp) {
        if (isUp) {
            String previousCommand = eduMateHistory.get(index);
            if (previousCommand != null && index < maxIndex) {
                index++;
            }
            return previousCommand;
        } else {
            if (index > minIndex) {
                index--;
            }
            return eduMateHistory.get(index);
        }
    }
}
