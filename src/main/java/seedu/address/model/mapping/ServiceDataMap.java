package seedu.address.model.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.UniqueTechnicianList;
import seedu.address.model.service.Service;
import seedu.address.model.service.Vehicle;

/**
 * Represents a mapping between services and their associated technicians
 */
public class ServiceDataMap {
    private final Map<Integer, Service> idToServiceMap = new HashMap<>();
    private final Map<Integer, Technician> idToTechnicianMap = new HashMap<>();
    private final Map<Integer, Vehicle> idToVehicleMap = new HashMap<>();
    private final Map<Service, UniqueTechnicianList> serviceToTechniciansMap = new HashMap<>();
    private final Map<Service, Vehicle> serviceToVehicleMap = new HashMap<>();


    /**
     * Initialises the id mappings based on the provided lists.
     *
     * @param services The list of services.
     * @param technicians The list of technicians.
     * @param vehicles The list of appointments.
     */
    public void initialiseIdMappings(List<Service> services, List<Technician> technicians, List<Vehicle> vehicles) {
        for (Service service : services) {
            idToServiceMap.put(service.getId(), service);
        }
        for (Technician technician : technicians) {
            idToTechnicianMap.put(technician.getId(), technician);
        }
        for (Vehicle vehicle : vehicles) {
            idToVehicleMap.put(vehicle.getId(), vehicle);
        }
    }

    /**
     * Clears all mappings.
     */
    public void clearAll() {
        idToServiceMap.clear();
        idToTechnicianMap.clear();
        idToVehicleMap.clear();
        serviceToTechniciansMap.clear();
        serviceToVehicleMap.clear();
    }

    /**
     * Process the mappings of services to their associated technicians and vehicle
     * based on current id maps
     *
     */
    public void processMappings() {
        for (int key : idToServiceMap.keySet()) {
            Service service = idToServiceMap.get(key);
            UniqueTechnicianList serviceTechnicianList = new UniqueTechnicianList();
            Vehicle serviceVehicle = idToVehicleMap.get(service.getVehicleId());

            if (serviceVehicle != null) {
                this.serviceToVehicleMap.put(service, serviceVehicle);
            }

            for (int technicianId : service.getAssignedToIds()) {
                Technician technician = idToTechnicianMap.get(technicianId);
                if (technician != null) {
                    serviceTechnicianList.add(technician);
                }
            }
            this.serviceToTechniciansMap.put(service, serviceTechnicianList);
        }
    }

    /**
     * Modifies the provided service and updates the mappings.
     *
     * @param service The service to add or update.
     * @param isRemove A flag to indicate whether to remove given service.
     */
    public void modifyService(Service service, boolean isRemove) {
        if (isRemove) {
            idToServiceMap.remove(service.getId());
        } else {
            idToServiceMap.put(service.getId(), service);
        }
        processMappings();
    }

    /**
     * Modifies the provided technician and updates the mappings.
     *
     * @param technician The technician to add or update.
     * @param isRemove A flag to indicate whether to remove given technician.
     */
    public void modifyTechnician(Technician technician, boolean isRemove) {
        if (isRemove) {
            idToTechnicianMap.remove(technician.getId());
        } else {
            idToTechnicianMap.put(technician.getId(), technician);
        }
        processMappings();
    }

    /**
     * Modifies the provided vehicle and updates the mappings.
     *
     * @param vehicle The vehicle to add or update.
     * @param isRemove A flag to indicate whether to remove given vehicle.
     */
    public void modifyVehicle(Vehicle vehicle, boolean isRemove) {
        if (isRemove) {
            idToVehicleMap.remove(vehicle.getId());
        } else {
            idToVehicleMap.put(vehicle.getId(), vehicle);
        }
        processMappings();
    }

    /**
     * Resets the mappings based on the lists provided and process the service
     * associated mappings again
     *
     * @param services The list of services.
     * @param technicians The list of technicians.
     * @param vehicles The list of vehicles.
     */
    public void reset(List<Service> services, List<Technician> technicians, List<Vehicle> vehicles) {
        clearAll();
        initialiseIdMappings(services, technicians, vehicles);
        processMappings();
    }

    /**
     * Returns an unmodifiable observable list of technicians associated with the
     * specified service.
     *
     * @param service The service whose technicians to retrieve.
     * @return An unmodifiable observable list of technicians associated with the
     *         specified service.
     */
    public ObservableList<Technician> getServiceTechnicians(Service service) {
        return this.serviceToTechniciansMap.get(service).asUnmodifiableObservableList();
    }

    /**
     * Returns the Vehicle associated with the specified service.
     *
     * @param service The service whose owner to vehicle.
     * @return The Vehicle to service
     */
    public Vehicle getServiceVehicle(Service service) {
        return this.serviceToVehicleMap.get(service);
    }
}
