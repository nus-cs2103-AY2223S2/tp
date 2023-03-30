package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BRAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATE_NUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Customer;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditVehicleCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "editvehicle";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the customer identified "
            + "by the id number displayed by listvehicle. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PLATE_NUM + "PLATE NUMBER] "
            + "[" + PREFIX_BRAND + "VEHICLE BRAND] "
            + "[" + PREFIX_CUSTOMER_ID + "OWNER ID] "
            + "[" + PREFIX_VEHICLE_COLOR + "COLOR]"
            + "[" + PREFIX_VEHICLE_TYPE + "VEHICLE TYPE]"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PLATE_NUM + "SBA1234A "
            + PREFIX_VEHICLE_COLOR + "blue";

    public static final String MESSAGE_EDIT_VEHICLE_SUCCESS = "Edited vehicle: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_VEHICLE = "This vehicle already registered";
    public static final String MESSAGE_CUSTOMER_NOT_FOUND = "This customer does not exist.";
    private static final Vehicle VEHICLE_DOES_NOT_EXIST = null;

    private static final Customer CUSTOMER_DOES_NOT_EXIST = null;
    private final EditVehicleDescriptor editVehicleDescriptor;

    /**
     * @param editPersonDescriptor details to edit the person with
     */
    public EditVehicleCommand(EditVehicleDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        this.editVehicleDescriptor = new EditVehicleDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        requireNonNull(model);
        List<Vehicle> lastShownList = model.getFilteredVehicleList();

        // Locate entry containing id. By right each ID is unique.
        Vehicle vehicleToEdit = lastShownList.stream().filter(vehicle ->
                        editVehicleDescriptor.getId() == vehicle.getId()).findAny()
                .orElse(VEHICLE_DOES_NOT_EXIST);

        Vehicle editedVehicle = createEditedVehicle(vehicleToEdit, editVehicleDescriptor);

        if (!vehicleToEdit.isSameVehicle(editedVehicle) && model.hasVehicle(editedVehicle.getId())) {
            throw new CommandException(MESSAGE_DUPLICATE_VEHICLE);
        }

        Customer customer = model.getFilteredCustomerList().stream().filter(person ->
                editedVehicle.getOwnerId() == vehicleToEdit.getOwnerId()).findAny().orElse(CUSTOMER_DOES_NOT_EXIST);

        if (customer == null) {
            throw new CommandException(MESSAGE_CUSTOMER_NOT_FOUND);
        }

        model.setVehicle(vehicleToEdit, editedVehicle);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_VEHICLE_SUCCESS, editedVehicle));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Vehicle createEditedVehicle(Vehicle vehicleToEdit, EditVehicleDescriptor editVehicleDescriptor) {
        assert vehicleToEdit != null;

        int id = vehicleToEdit.getId();

        String plateNumber = editVehicleDescriptor.getPlateNumber().orElse(vehicleToEdit.getPlateNumber());
        String brand = editVehicleDescriptor.getBrand().orElse(vehicleToEdit.getBrand());
        int ownerId = editVehicleDescriptor.getOwnerId().orElse(vehicleToEdit.getOwnerId());
        VehicleType vehicleType = editVehicleDescriptor.getType().orElse(vehicleToEdit.getType());
        String vehicleColor = editVehicleDescriptor.getColor().orElse(vehicleToEdit.getColor());
        Set<Integer> serviceIds = editVehicleDescriptor.getServiceIdsSet().orElse(vehicleToEdit.getServiceIdsSet());

        return new Vehicle(id, ownerId, plateNumber, vehicleColor, brand, vehicleType, serviceIds);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVehicleCommand)) {
            return false;
        }

        // state check
        EditVehicleCommand e = (EditVehicleCommand) other;
        return editVehicleDescriptor.equals(e.editVehicleDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditVehicleDescriptor {

        private int id;
        private int ownerId;
        private String plateNumber;
        private String color;
        private String brand;
        private VehicleType type;
        private Set<Integer> serviceIds = new HashSet<>();

        public EditVehicleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVehicleDescriptor(EditVehicleDescriptor toCopy) {
            setId(toCopy.id);
            setOwnerId(toCopy.ownerId);
            setPlateNumber(toCopy.plateNumber);
            setColor(toCopy.color);
            setBrand(toCopy.brand);
            setType(toCopy.type);
            setServiceIds(toCopy.serviceIds);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return
                    CollectionUtil.isAnyNonNull(ownerId, plateNumber, color, brand, type, serviceIds);
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public Optional<Integer> getOwnerId() {
            return Optional.ofNullable(id);
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public Optional<String> getPlateNumber() {
            return Optional.ofNullable(plateNumber);
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Optional<String> getColor() {
            return Optional.ofNullable(color);
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Optional<String> getBrand() {
            return Optional.ofNullable(brand);
        }

        public void setType(VehicleType vehicleType) {
            this.type = vehicleType;
        }

        public Optional<VehicleType> getType() {
            return Optional.ofNullable(type);
        }


        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setServiceIds(Set<Integer> ints) {
            this.serviceIds = (ints != null) ? new HashSet<>(ints) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Integer>> getServiceIdsSet() {
            return (serviceIds != null)
                    ? Optional.of(Collections.unmodifiableSet(serviceIds)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditVehicleDescriptor)) {
                return false;
            }

            // state check
            EditVehicleDescriptor e = (EditVehicleDescriptor) other;

            return getId() == e.getId() //not sure if need this id checking
                    && getOwnerId().equals(e.getOwnerId())
                    && getPlateNumber().equals(e.getPlateNumber())
                    && getColor().equals(e.getColor())
                    && getBrand().equals(e.getBrand())
                    && getType().equals(e.getType())
                    && getServiceIdsSet().equals(e.getServiceIdsSet());
        }
    }
}
