package taa.model.alarm;

import java.util.ArrayList;
import java.util.List;


public class AlarmList {

    private static List<Alarm> alarms = new ArrayList<Alarm>();

    //// alarm-level operation

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    public static void deleteTheAlarm(Alarm alarm) {
        for (int i = 0; i < alarms.size(); i++) {
            if(alarms.get(i) == alarm) {
                alarms.remove(i);
            }
        }
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
