package taa.model.alarm;

import java.util.ArrayList;
import java.util.List;


public class AlarmList {

    private static final List<Alarm> alarms = new ArrayList<Alarm>();

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

    public static void deleteTheAlarm(int i) {
        alarms.get(i - 1).stopTimeLine();
        alarms.remove(i - 1);
    }

    public static Alarm getSoonestAlarm() {
        int i = 0;
        double maxRemainingTime = Double.MAX_VALUE;
        int maxIndex = -1;
        for (Alarm a : alarms) {
            if (a.getRemainingTime() < maxRemainingTime) {
                maxIndex = i;
            }
            i++;
        }
        return alarms.get(maxIndex);
    }

    public static String getAlarmAlert(Alarm alarm) {
        for (Alarm a : alarms) {
            if (a.equals(alarm)) {
                return a.getMessage();
            }
        }
        return null;
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
        int i = 1;
        for (Alarm a : alarms) {
            sb.append("Alarm " + i + ">>>>> " + a + "\n");
            i++;
        }
        return sb.toString();
    }
}

