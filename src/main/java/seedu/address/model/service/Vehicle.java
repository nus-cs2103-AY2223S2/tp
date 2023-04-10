package seedu.address.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.DeepCopy;
import seedu.address.model.Findable;

/**
 * The vehicle class represents a vehicle object in the repair shop.
 */
public class Vehicle implements Findable, DeepCopy<Vehicle> {
    private int id;
    private int ownerId;
    private String plateNumber;
    private String color;
    private String brand;
    private VehicleType type;
    private final Set<Integer> serviceIds = new HashSet<>();

    /**
     * This method is the constructor for the Vehicle class.
     *
     * @param ownerId     Id of customer that owns the vehicle
     * @param plateNumber The Vehicle Registration Number (VRN) of the vehicle.
     * @param color       This is the color of the vehicle.
     * @param brand       This is the brand of the vehicle (i.e. KIA).
     * @param type        What vehicle type this vehicle is (i.e. Bike)
     */
    public Vehicle(int id, int ownerId, String plateNumber, String color, String brand, VehicleType type) {
        this.id = id;
        this.ownerId = ownerId;
        this.plateNumber = plateNumber;
        this.color = color;
        this.brand = brand;
        this.type = type;
    }

    /**
     * This method is the constructor for the Vehicle class.
     * @param plateNumber The Vehicle Registration Number (VRN) of the vehicle.
     * @param color This is the color of the vehicle.
     * @param brand This is the brand of the vehicle (i.e. KIA).
     * @param type What vehicle type this vehicle is (i.e. Bike)
     * @param serviceIds The list of service id this vehicle had/has
     */
    public Vehicle(int id, int ownerId, String plateNumber, String color, String brand,
                   VehicleType type, Set<Integer> serviceIds) {
        this.id = id;
        this.ownerId = ownerId;
        this.plateNumber = plateNumber;
        this.color = color;
        this.brand = brand;
        this.type = type;
        this.serviceIds.addAll(serviceIds);
    }

    /**
     * This method is the constructor for the Vehicle class.
     * @param plateNumber The Vehicle Registration Number (VRN) of the vehicle.
     * @param color This is the color of the vehicle.
     * @param brand This is the brand of the vehicle (i.e. KIA).
     * @param type What vehicle type this vehicle is (i.e. Bike)
     * @param serviceIds The list of service id this vehicle had/has
     */
    public Vehicle(int id, String plateNumber, String color, String brand, VehicleType type, Set<Integer> serviceIds) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.color = color;
        this.brand = brand;
        this.type = type;
        this.serviceIds.addAll(serviceIds);
    }

    /**
     * This method returns the id of this vehicle.
     * This is not the VRN number.
     *
     * @return The id of this vehicle.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Id of customer that owns the vehicle
     */
    public int getOwnerId() {
        return this.ownerId;
    }

    /**
     * This method returns the VRN number of this vehicle.
     *
     * @return the plate number of this vehicle.
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * This method sets the VRN number of this vehicle.
     * Primarily used of the vehicle's VRN has been changed.
     *
     * @param plateNumber the new plate number of this vehicle.
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * This method returns the current color of this vehicle.
     *
     * @return the color of this vehicle.
     */
    public String getColor() {
        return color;
    }

    /**
     * This method changes the color of this vehicle.
     *
     * @param color the new color of this vehicle.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method returns the brand of this vehicle.
     *
     * @return the brand of this vehicle.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method sets the brand of this vehicle.
     * Only to be used if errors has been made.
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This returns the VehicleType of this vehicle.
     * i.e. returns that it is a car or a motorbike.
     *
     * @return the type of this vehicle.
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * This method changes the VehicleType of this vehicle.
     *
     * @param type the VehicleType to be changed to.
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * This method returns the services that is needed to be done to this vehicle.
     * In a List.
     *
     * @return List of services
     */
    public List<Integer> getServiceIds() {
        return new ArrayList<>(this.serviceIds);
    }

    /**
     * This method returns the services that is needed to be done to this vehicle.
     * In a Set.
     *
     * @return Set of services
     */
    public Set<Integer> getServiceIdsSet() {
        return this.serviceIds;
    }

    /**
     * This method adds the service that is needed to be done to this vehicle.
     *
     * @param service the service to be added.
     */
    public void addService(Service service) {
        this.serviceIds.add(service.getId());
    }

    public void addService(int serviceId) {
        this.serviceIds.add(serviceId);
    }

    /**
     * This method removes a service that was added to this vehicle...
     *
     * @param service the service to be removed.
     */
    public void removeService(Service service) {
        this.serviceIds.remove(service.getId());
    }

    /**
     * Returns true if both vehicles have the same plate number.
     */
    public boolean isSameVehicle(Vehicle otherVehicle) {
        if (otherVehicle == this) {
            return true;
        }

        return otherVehicle != null
                && otherVehicle.getPlateNumber().equalsIgnoreCase(getPlateNumber());
    }

    /**
     * Returns true if both vehicles have the same car plate number.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Vehicle) {
            Vehicle otherVehicle = (Vehicle) other;
            return this.isSameVehicle(otherVehicle);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append(String.format("ID: %s", this.getId()))
                .append(String.format("%nOwner: %s", this.getOwnerId()))
                .append(String.format("%nPlate: %s", this.getPlateNumber()))
                .append(String.format("%nColor: %s", this.getColor()))
                .append(String.format("%nBrand: %s", this.getBrand()))
                .append(String.format("%nType: %s", this.getType().getValue()))
                .append(String.format("%nServices: %n%s",
                        StringUtil.indent(this.getServiceIds().toString(), 2)))
                .toString();
    }

    @Override
    public boolean hasKeyword(String keyword) {
        return this.brand.toLowerCase().contains(keyword)
            || this.color.toLowerCase().contains(keyword)
            || this.plateNumber.toLowerCase().contains(keyword)
            || this.type.toString().toLowerCase().contains(keyword);
    }

    @Override
    public Vehicle copy() {
        return new Vehicle(this.id, this.ownerId, this.plateNumber, this.color, this.brand, this.type, this.serviceIds);
    }
}
