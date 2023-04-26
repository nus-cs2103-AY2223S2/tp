package taa.model.alarm;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the list of alarms
 */
public class AlarmList {

    private static final List<Alarm> alarms = new ArrayList<Alarm>();

    //// alarm-level operation

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    /**
     * Delete the alarm specified from the alarm list, stop the counting down as well
     * @param alarm the alarm to be deleted
     */
    public static void deleteTheAlarm(Alarm alarm) {
        for (int i = 0; i < alarms.size(); i++) {
            if (alarms.get(i) == alarm) {
                alarms.remove(i);
            }
        }
    }

    /**
     * Delete the alarm specified in index by the user from the alarm list
     * @param i the index of the alarm to be deleted
     */
    public static void deleteTheAlarm(int i) {
        alarms.get(i - 1).stopTimeLine();
        alarms.remove(i - 1);
    }

    /**
     * Returns the alarm message of the specified alarm
     * @param alarm whose message is to be retrieved
     * @return the message of the specified alarm
     */
    public static String getAlarmAlert(Alarm alarm) {
        for (Alarm a : alarms) {
            if (a.equals(alarm)) {
                return a.getMessage();
            }
        }
        return null;
    }

    /**
     * Return the total number of alarms
     * @return the total number of alarms
     */
    public static int getAlarmCount() {
        return alarms.size();
    }

    /**
     * @return A list of all alarms, including the messages and remaining time
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Alarm a : alarms) {
            sb.append("Alarm").append(i).append(" >>>>> ").append(a).append("\n");
            i++;
        }
        return sb.toString();
    }
}
