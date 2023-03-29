package taa.model.alarm;

import javafx.collections.ObservableList;
import taa.assignment.Assignment;
import taa.assignment.Submission;
import taa.model.ReadOnlyAddressBook;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class AlarmList{

    private static List<Alarm> alarms = new ArrayList<Alarm>();

    //// alarm-level operations


    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    public static Alarm getFirstAlarm() {
        return alarms.get(0);
    }

    public static int getAlarmCount() {
        return alarms.size();
    }

    public List<Alarm> getAlarmList() {
        return alarms;
    }

    /**
     * @return A list of all assignments and submissions
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Alarm a : alarms) {
            sb.append(i + ". Alarm " + a + "\n");
            i++;
        }
        return sb.toString();
    }
}
