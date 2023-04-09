package seedu.address.model.entity.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.DeepCopy;
import seedu.address.model.tag.Tag;

/**
 * A technician is a special type of staff, in which they handle the tasks related to the vehicle,
 * such tasks include services and appointments
 */
public class Technician extends Staff implements DeepCopy<Technician> {
    private final Set<Integer> serviceIds = new HashSet<>();
    private final Set<Integer> appointmentIds = new HashSet<>();
    /**
     * {@inheritDoc}
     */
    public Technician(int id, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(id, name, phone, email, address, tags);
    }

    /**
     * {@inheritDoc}
     */
    public Technician(int id, Name name, Phone phone, Email email, Address address,
                      Set<Tag> tags, Set<Integer> serviceIds, Set<Integer> appointmentIds) {
        super(id, name, phone, email, address, tags);
        this.serviceIds.addAll(serviceIds);
        this.appointmentIds.addAll(appointmentIds);
    }

    /**
     * This method returns a list of service ids this technician is assigned to.
     *
     * @return a list of services this technician is assigned to.
     */
    public List<Integer> getServiceIds() {
        return new ArrayList<>(this.serviceIds);
    }

    /**
     * This method returns a list of appointment ids this technician is assigned to.
     *
     * @return a list of appointment this technician is assigned to.
     */
    public List<Integer> getAppointmentIds() {
        return new ArrayList<>(this.appointmentIds);
    }

    public void removeAppointmentIds(Predicate<? super Integer> pred) {
        this.appointmentIds.removeIf(pred);
    }

    public void removeServiceIds(Predicate<? super Integer> pred) {
        this.serviceIds.removeIf(pred);
    }

    /**
     * This method adds a service id to the list of service ids this technician is assigned to.
     *
     * @param serviceId service id to be added.
     */
    public void addServiceId(int serviceId) {
        this.serviceIds.add(serviceId);
    }

    /**
     * This method adds an appointment id to the list of appointment ids this technician is assigned to.
     *
     * @param appointmentId appointment id to be added.
     */
    public void addAppointmentId(int appointmentId) {
        this.appointmentIds.add(appointmentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Technician) {
            Technician other = (Technician) obj;
            return this.getId() == other.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public Technician copy() {
        return new Technician(this.getId(), this.getName(), this.getPhone(), this.getEmail(),
                this.getAddress(), this.getTags(), this.serviceIds, this.appointmentIds);
    }
}
