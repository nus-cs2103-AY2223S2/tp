package seedu.address.model;

import java.nio.file.Path;

import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Part;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;

/**
 * Extension of model functionality for Shop
 */
public interface ShopModel extends Model {
    Path getShopFilePath();

    /**
     * Sets the user prefs' shop file path.
     */
    void setShopFilePath(Path shopFilePath);

    /**
     * Replaces shop book data with the data in {@code shop}.
     */
    void setShop(Shop shop); //TODO ReadOnlySHop

    /**
     * Returns the Shop
     */
    Shop getShop(); //TODO ReadOnlyShop

    /**
     * Adds customer to the shop
     *
     * @param customer Customer to be added
     */
    void addCustomer(Customer customer);

    /**
     * Checks whether Shop already has customer
     *
     * @param customerId Customer ID to be checked
     */
    boolean hasCustomer(int customerId);

    /**
     * Adds vehicle to the shop
     *
     * @param vehicle Vehicle to be added
     */
    void addVehicle(Vehicle vehicle);

    /**
     * Checks if shop already has vehicle
     *
     * @param vehicleId Vehicle ID to check against
     */
    boolean hasVehicle(int vehicleId);

    /**
     * Adds service
     *
     * @param service Service to add
     */
    void addService(Service service);

    /**
     * @param serviceId ID of service
     * @return Whether service already in the system
     */
    boolean hasService(int serviceId);

    /**
     * Adds appointment
     *
     * @param appointment Appointment to add
     */
    void addAppointment(Appointment appointment);

    /**
     * Adds part
     *
     * @param part Part to add
     */
    void addPart(Part part);

    /**
     * Checks if part already exists
     *
     * @param part Part to check against
     */
    boolean hasPart(Part part);


}
