package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


/**
 * Creates a Session Pie chart
 */
public class SessionPieChart {

    /**
     * Generates a pie chart based on the attendance map of a session.
     * @param attendanceMap The attendance map of a session.
     * @return A PieChart object representing the attendance of a session.
     */
    public static ObservableList<PieChart.Data> generateAttendancePieChart(HashMap<String, Boolean> attendanceMap) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Count the number of students who are present and absent.
        int presentCount = 0;
        int absentCount = 0;
        for (Map.Entry<String, Boolean> entry : attendanceMap.entrySet()) {
            if (entry.getValue()) {
                presentCount++;
            } else {
                absentCount++;
            }
        }

        // Add the attendance data to the pie chart.
        if (presentCount > 0) {
            pieChartData.add(new PieChart.Data("Present (" + presentCount + ")", presentCount));
        }
        if (absentCount > 0) {
            pieChartData.add(new PieChart.Data("Absent (" + absentCount + ")", absentCount));
        }


        return pieChartData;

    }
}
