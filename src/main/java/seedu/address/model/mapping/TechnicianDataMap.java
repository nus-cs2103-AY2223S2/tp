package seedu.address.model.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceList;
import seedu.address.model.service.appointment.Appointment;
import seedu.address.model.service.appointment.UniqueAppointmentList;

/**
 * Represents a mapping between technicians with their associated services and appointments.
 */
public class TechnicianDataMap {
    private final Map<Integer, Technician> idToTechnicianMap = new HashMap<>();
    private final Map<Integer, Service> idToServiceMap = new HashMap<>();
    private final Map<Integer, Appointment> idToAppointmentMap = new HashMap<>();
    private final Map<Technician, ServiceList> technicianToServiceMap = new HashMap<>();
    private final Map<Technician, UniqueAppointmentList> technicianToAppointmentsMap = new HashMap<>();

    /**
     * Initialises the id mappings based on the provided lists.
     *
     * @param technicians The list of technicians.
     * @param services The list of services.
     * @param appointments The list of appointments.
     */
    public void initialiseIdMappings(List<Technician> technicians, List<Service> services,
                                     List<Appointment> appointments) {
        for (Technician technician : technicians) {
            idToTechnicianMap.put(technician.getId(), technician);
        }
        for (Service service : services) {
            idToServiceMap.put(service.getId(), service);
        }
        for (Appointment appointment : appointments) {
            idToAppointmentMap.put(appointment.getId(), appointment);
        }
    }

    /**
     * Clears all mappings.
     */
    public void clearAll() {
        idToTechnicianMap.clear();
        idToServiceMap.clear();
        idToAppointmentMap.clear();
        technicianToServiceMap.clear();
        technicianToAppointmentsMap.clear();
    }

    /**
     * Process the mappings of technicians to their associated appointments
     * and services based on the current id maps
     *
     */
    public void processMappings() {
        for (int key : idToTechnicianMap.keySet()) {
            Technician technician = idToTechnicianMap.get(key);
            ServiceList technicianServices = new ServiceList();
            UniqueAppointmentList technicianAppointments = new UniqueAppointmentList();
            for (int serviceId : technician.getServiceIds()) {
                Service service = idToServiceMap.get(serviceId);
                if (service != null) {
                    technicianServices.add(service);
                }
            }
            for (int appointmentId : technician.getAppointmentIds()) {
                Appointment appointment = idToAppointmentMap.get(appointmentId);
                if (appointment != null) {
                    technicianAppointments.add(appointment);
                }
            }
            this.technicianToServiceMap.put(technician, technicianServices);
            this.technicianToAppointmentsMap.put(technician, technicianAppointments);
        }
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
     * Modifies the provided appointment and updates the mappings.
     *
     * @param appointment The appointment to add or update.
     * @param isRemove A flag to indicate whether to remove given appointment.
     */
    public void modifyAppointment(Appointment appointment, boolean isRemove) {
        if (isRemove) {
            idToAppointmentMap.remove(appointment.getId());
        } else {
            idToAppointmentMap.put(appointment.getId(), appointment);
        }
        processMappings();
    }

    /**
     * Resets the mappings based on the lists provided and process the technician
     * associated mappings again
     *
     * @param technicians The list of technicians.
     * @param services The list of services.
     * @param appointments The list of appointments.
     */
    public void reset(List<Technician> technicians, List<Service> services, List<Appointment> appointments) {
        clearAll();
        initialiseIdMappings(technicians, services, appointments);
        processMappings();
    }

    /**
     * Returns an unmodifiable observable list of services associated with the
     * specified technician.
     *
     * @param technician The technician whose services to retrieve.
     * @return An unmodifiable observable list of services associated with the
     *         specified technician.
     */
    public ObservableList<Service> getTechnicianServices(Technician technician) {
        return this.technicianToServiceMap.get(technician).asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable observable list of appointments associated with the
     * specified technician.
     *
     * @param technician The technician whose appointments to retrieve.
     * @return An unmodifiable observable list of appointments associated with the
     *         specified technician.
     */
    public ObservableList<Appointment> getTechnicianAppointments(Technician technician) {
        return this.technicianToAppointmentsMap.get(technician).asUnmodifiableObservableList();
    }
}
