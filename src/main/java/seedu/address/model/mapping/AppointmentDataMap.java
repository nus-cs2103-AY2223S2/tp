package seedu.address.model.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.UniqueTechnicianList;
import seedu.address.model.service.appointment.Appointment;

/**
 * Represents a mapping between appointments and their assigned staff
 */
public class AppointmentDataMap {
    private final Map<Integer, Appointment> idToAppointmentMap = new HashMap<>();
    private final Map<Integer, Technician> idToTechnicianMap = new HashMap<>();
    private final Map<Integer, Customer> idToCustomerMap = new HashMap<>();
    private final Map<Appointment, UniqueTechnicianList> appointmentToTechniciansMap = new HashMap<>();
    private final Map<Appointment, Customer> appointmentToCustomerMap = new HashMap<>();

    /**
     * Initialises the id mappings based on the provided lists.
     *
     * @param appointments The list of appointments.
     * @param technicians The list of technicians.
     * @param customers The list of customers.
     */
    public void initialiseIdMappings(List<Appointment> appointments, List<Technician> technicians,
                                     List<Customer> customers) {
        for (Appointment appointment : appointments) {
            idToAppointmentMap.put(appointment.getId(), appointment);
        }
        for (Technician technician : technicians) {
            idToTechnicianMap.put(technician.getId(), technician);
        }
        for (Customer customer : customers) {
            idToCustomerMap.put(customer.getId(), customer);
        }
    }

    /**
     * Clears all mappings.
     */
    public void clearAll() {
        idToAppointmentMap.clear();
        idToTechnicianMap.clear();
        idToCustomerMap.clear();
        appointmentToTechniciansMap.clear();
        appointmentToCustomerMap.clear();
    }

    /**
     * Process the mappings of appointments to their associated technician
     * and customer based on the current id maps
     *
     */
    public void processMappings() {
        for (int key : idToAppointmentMap.keySet()) {
            Appointment appointment = idToAppointmentMap.get(key);
            UniqueTechnicianList appointmentTechnicianList = new UniqueTechnicianList();
            Customer appointmentCustomer = idToCustomerMap.get(appointment.getCustomerId());

            if (appointmentCustomer != null) {
                this.appointmentToCustomerMap.put(appointment, appointmentCustomer);
            }

            for (int technicianId : appointment.getStaffIds()) {
                Technician technician = idToTechnicianMap.get(technicianId);
                if (technician != null) {
                    appointmentTechnicianList.add(technician);
                }
            }
            this.appointmentToTechniciansMap.put(appointment, appointmentTechnicianList);
        }
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
     * Modifies the provided customer and updates the mappings.
     *
     * @param customer The customer to add or update.
     * @param isRemove A flag to indicate whether to remove given customer.
     */
    public void modifyCustomer(Customer customer, boolean isRemove) {
        if (isRemove) {
            idToCustomerMap.remove(customer.getId());
        } else {
            idToCustomerMap.put(customer.getId(), customer);
        }
        processMappings();
    }

    /**
     * Resets the mappings based on the lists provided and process the technician
     * associated mappings again
     *
     * @param appointments The list of appointments.
     * @param technicians The list of technicians.
     * @param customers The list of customers.
     */
    public void reset(List<Appointment> appointments, List<Technician> technicians, List<Customer> customers) {
        clearAll();
        initialiseIdMappings(appointments, technicians, customers);
        processMappings();
    }

    /**
     * Returns an unmodifiable observable list of technicians associated with the
     * specified appointment.
     *
     * @param appointment The appointment whose technicians to retrieve.
     * @return An unmodifiable observable list of technicians associated with the
     *         specified appointment.
     */
    public ObservableList<Technician> getAppointmentTechnicians(Appointment appointment) {
        return this.appointmentToTechniciansMap.get(appointment).asUnmodifiableObservableList();
    }

    /**
     * Returns the Customer associated with the specified appointment.
     *
     * @param appointment The appointment whose customer to retrieve.
     * @return The customer with the appointment
     */
    public Customer getAppointmentCustomer(Appointment appointment) {
        return this.appointmentToCustomerMap.get(appointment);
    }
}
