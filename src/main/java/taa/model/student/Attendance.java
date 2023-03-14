package taa.model.student;

/**
 * Attendance class
 */
public class Attendance {
    public static final String ERROR_MSG = "Week number out of range, should be between 1-12";
    private final boolean[] attendanceList = new boolean[12];

    /**
     * constructor for attendance class
     */
    public Attendance() {
    }

    /**
     * Checks if the week is valid
     * @param week String value of week
     * @return true if week is valid and otherwise
     */
    public static boolean isValidWeek(String week) {
        int intWeek = Integer.parseInt(week);
        if (intWeek <= 0 || intWeek > 12) {
            System.out.println(week);
            return false;
        }
        return true;
    }

    /**
     * @return number of weeks marked present
     */
    public int getNumWeeksPresent() {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (this.attendanceList[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Marks attendance as true
     * @param week week that is to be marked
     */
    public void markAttendance(int week) {
        this.attendanceList[week] = true;
    }

    /**
     * Checks if the week have been marked
     * @param week week to be checked
     * @return true if week is marked else false
     */
    public boolean isMarkedWeek(int week) {
        return this.attendanceList[week];
    }

    /**
     * Convert string value of week into int, called after ensuring {@code week}
     * is valid
     * @param week String value of week
     * @return int value of week
     */
    public static int convertToIntegerWeek(String week) {
        return Integer.parseInt(week);
    }

    /**
     * Function to unmark attendance
     *
     * @param index index to unmark
     */
    public void unmarkAttendance(int index) {
        this.attendanceList[index] = false;
    }
}
