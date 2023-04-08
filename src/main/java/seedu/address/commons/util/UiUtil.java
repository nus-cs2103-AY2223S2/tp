package seedu.address.commons.util;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seedu.address.model.service.ServiceStatus;
import seedu.address.model.service.appointment.Appointment;

/**
 * UI Utility class for commonly shared functions that help set UI elements.
 *
 */
public class UiUtil {
    private static final Color toRepair = Color.rgb(61, 167, 201);
    private static final Color inProgress = Color.rgb(252, 159, 39);
    private static final Color complete = Color.rgb(89, 201, 61);
    private static final Color cancelled = Color.rgb(236, 46, 46);
    private static final Color onHold = Color.rgb(255, 230, 0);

    /**
     * Sets the text and style class of a label based on an appointment date
     * status.
     *
     * @param dateStatus the label to modify
     * @param status the appointment date status to set the label to
     */
    public static void setDateStatus(Label dateStatus, Appointment.DateStatus status) {
        switch (status) {
        case PASSED:
            dateStatus.setText(status.name());
            dateStatus.getStyleClass().add("passed-tag");
            break;
        case UPCOMING:
            dateStatus.setText(status.name());
            dateStatus.getStyleClass().add("upcoming-tag");
            break;
        default:
            dateStatus.setText(status.name());
            dateStatus.getStyleClass().add("today-tag");
        }
    }

    /**
     * Sets the fill color of a circle based on a service status.
     *
     * @param statusCircle the circle to modify
     * @param status the service status to set the circle to
     */
    public static void setStatusCircle(Circle statusCircle, ServiceStatus status) {
        switch (status) {
        case TO_REPAIR:
            statusCircle.setFill(toRepair);
            break;
        case IN_PROGRESS:
            statusCircle.setFill(inProgress);
            break;
        case COMPLETE:
            statusCircle.setFill(complete);
            break;
        case CANCELLED:
            statusCircle.setFill(cancelled);
            break;
        default:
            statusCircle.setFill(onHold);
        }
    }
}
