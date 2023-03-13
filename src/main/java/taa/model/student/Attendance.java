package taa.model.student;

/**
 * Attendance class
 */
public class Attendance {
    public static final String errorMsg = "Week number out of range, should be between 1-12";
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
            return false;
        }
        return true;
    }

    public int getNumWeeksPresent() {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (this.attendanceList[i]) {
                count++;
            }
        }
        return count;
    }

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

    public Attendance getCopy() {
        Attendance copy = new Attendance();
        for (int i = 0; i < 12; i++) {
            copy.attendanceList[i] = this.attendanceList[i];
        }
        return copy;
    }

    /**
     * Function to mark attendance
     * @param index index to mark
     */
    public void mark(int index) {
        if (index <= 0 || index >= 12) {
            System.out.println("Error, attendance index out of range");
        }
        this.attendanceList[index - 1] = true;
    }

    /**
     * Function to unmark attendance
     *
     * @param index index to unmark
     */
    public void unmark(int index) {
        if (index <= 0 || index >= 12) {
            System.out.println("Error, attendance index out of range");
        }
        this.attendanceList[index - 1] = false;
    }
}
