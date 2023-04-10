package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.FXCollections;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.idgen.IdGenerator;
import seedu.address.model.ReadOnlyShop;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.shop.CaseInsensitiveHashMap;
import seedu.address.model.entity.shop.Shop;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.appointment.Appointment;

/**
 * An Immutable AutoM8 Shop that is serializable to JSON format.
 */
@JsonRootName(value = "autom8")
class JsonSerializableShop {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customer list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_VEHICLE = "Vehicle list contains duplicate vehicle(s).";
    public static final String MESSAGE_DUPLICATE_SERVICE = "Service list contains duplicate service(s).";
    public static final String MESSAGE_DUPLICATE_PART = "Part list contains duplicate part(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";
    public static final String MESSAGE_DUPLICATE_TECHNICIAN = "Technician list contains duplicate appointment(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedVehicle> vehicles = new ArrayList<>();
    private final List<JsonAdaptedService> services = new ArrayList<>();
    private final List<JsonAdaptedPart> parts = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedTechnician> technicians = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableShop} with the given customers and vehicles.
     */
    @JsonCreator
    public JsonSerializableShop(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                @JsonProperty("vehicles") List<JsonAdaptedVehicle> vehicles,
                                @JsonProperty("services") List<JsonAdaptedService> services,
                                @JsonProperty("parts") List<JsonAdaptedPart> parts,
                                @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
                                @JsonProperty("technicians") List<JsonAdaptedTechnician> technicians) {
        this.customers.addAll(customers);
        this.vehicles.addAll(vehicles);
        this.services.addAll(services);
        this.parts.addAll(parts);
        this.appointments.addAll(appointments);
        this.technicians.addAll(technicians);
    }

    /**
     * Converts a given {@code ReadOnlyShop} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShop}.
     */
    public JsonSerializableShop(ReadOnlyShop source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        vehicles.addAll(source.getVehicleList().stream().map(JsonAdaptedVehicle::new).collect(Collectors.toList()));
        services.addAll(source.getServiceList().stream().map(JsonAdaptedService::new).collect(Collectors.toList()));
        source.getPartMap()
            .forEach(entry -> parts.add(new JsonAdaptedPart(entry.getKey(), entry.getValue())));
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
            .collect(Collectors.toList()));
        technicians.addAll(source.getTechnicianList().stream().map(JsonAdaptedTechnician::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Shop} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Shop toModelType() throws IllegalValueException {
        Shop shop = new Shop();
        IdGenerator idGenerator = new IdGenerator();
        List<Customer> customerList = new ArrayList<>();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (shop.hasCustomer(customer.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            idGenerator.setCustomerIdUsed(customer.getId());
            customerList.add(customer);
        }

        List<Vehicle> vehicleList = new ArrayList<>();
        for (JsonAdaptedVehicle jsonAdaptedVehicle : vehicles) {
            Vehicle vehicle = jsonAdaptedVehicle.toModelType();
            if (shop.hasVehicle(vehicle.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VEHICLE);
            }
            idGenerator.setVehicleIdUsed(vehicle.getId());
            vehicleList.add(vehicle);
        }

        List<Service> serviceList = new ArrayList<>();
        for (JsonAdaptedService jsonAdaptedService : services) {
            Service service = jsonAdaptedService.toModelType();
            if (shop.hasService(service.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SERVICE);
            }
            idGenerator.setServiceIdUsed(service.getId());
            serviceList.add(service);
        }

        Map<String, Integer> partMap = new CaseInsensitiveHashMap<>();
        for (JsonAdaptedPart jsonAdaptedPart : parts) {
            Map.Entry<String, Integer> partEntry = jsonAdaptedPart.toModelType();
            if (shop.hasPart(partEntry.getKey())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PART);
            }
            partMap.put(partEntry.getKey(), partEntry.getValue());
        }

        List<Appointment> appointmentList = new ArrayList<>();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (shop.hasAppointment(appointment.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            idGenerator.setAppointmentIdUsed(appointment.getId());
            appointmentList.add(appointment);
        }

        List<Technician> technicianList = new ArrayList<>();
        for (JsonAdaptedTechnician jsonAdaptedTechnician : technicians) {
            Technician technician = jsonAdaptedTechnician.toModelType();
            if (shop.hasTechnician(technician.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TECHNICIAN);
            }
            idGenerator.setStaffIdUsed(technician.getId());
            technicianList.add(technician);
        }
        shop.initializeData(
            idGenerator,
            FXCollections.observableArrayList(customerList),
            FXCollections.observableArrayList(vehicleList),
            FXCollections.observableMap(partMap),
            FXCollections.observableArrayList(serviceList),
            FXCollections.observableArrayList(technicianList),
            FXCollections.observableArrayList(appointmentList)
        );
        return shop;
    }

}
