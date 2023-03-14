package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Vehicle;

/**
 * An Immutable AutoM8 Shop that is serializable to JSON format.
 */
@JsonRootName(value = "autom8")
class JsonSerializableShop {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customer list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_VEHICLE = "Vehicle list contains duplicate vehicle(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedVehicle> vehicles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableShop} with the given customers and vehicles.
     */
    @JsonCreator
    public JsonSerializableShop(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                @JsonProperty("vehicles") List<JsonAdaptedVehicle> vehicles) {
        this.customers.addAll(customers);
        this.vehicles.addAll(vehicles);
    }

    /**
     * Converts a given {@code ReadOnlyShop} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShop}.
     */
    public JsonSerializableShop(ReadOnlyShop source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        vehicles.addAll(source.getVehicleList().stream().map(JsonAdaptedVehicle::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Shop} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Shop toModelType() throws IllegalValueException {
        Shop shop = new Shop();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (shop.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            shop.addCustomer(customer);
        }

        for (JsonAdaptedVehicle jsonAdaptedVehicle : vehicles) {
            Vehicle vehicle = jsonAdaptedVehicle.toModelType();
            if (shop.hasVehicle(vehicle)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VEHICLE);
            }
            shop.addVehicle(vehicle);
        }
        return shop;
    }

}
