package seedu.address.model.entity.shop.exception;

// Thrown when Appointment is not found in the shop
public class AppointmentNotFoundException extends Exception {
    public AppointmentNotFoundException(int id) {
        super(String.format("Appointment %d does not exist", id));
    }
}
