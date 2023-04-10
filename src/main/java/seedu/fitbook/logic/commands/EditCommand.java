package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_CALORIE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_GOAL;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.fitbook.model.FitBookModel.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.commons.util.CollectionUtil;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Goal;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;
import seedu.fitbook.model.client.WeightHistory;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;
import seedu.fitbook.model.tag.Tag;


/**
 * Edits the details of an existing client in the FitBook.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the index number used in the displayed client list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_CALORIE + "CALORIE] "
            + "[" + PREFIX_APPOINTMENT + "APPOINTMENT_TIME]..."
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_GOAL + "GOAL] "
            + "[" + PREFIX_ROUTINE + "ROUTINE]..."
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This client already exists in the FitBook.";
    public static final String MESSAGE_ROUTINE_NAME_INVALID =
            "Invalid routine used for editing. Please use a valid routine from the routine list.";

    private final Index index;
    private final EditClientDescriptor editClientDescriptor;

    /**
     * @param index of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditCommand(Index index, EditClientDescriptor editClientDescriptor) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor, model);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedClient));
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor,
        FitBookModel model) throws CommandException {
        assert clientToEdit != null;
        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Phone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Email updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        Calorie updatedCalorie = editClientDescriptor.getCalorie().orElse(clientToEdit.getCalorie());
        Weight updatedWeight = editClientDescriptor.getWeight().orElse(clientToEdit.getWeight());
        Gender updatedGender = editClientDescriptor.getGender().orElse(clientToEdit.getGender());
        Goal updatedGoal = editClientDescriptor.getGoal().orElse(clientToEdit.getGoal());
        Set<Tag> updatedTags = editClientDescriptor.getTags().orElse(clientToEdit.getTags());
        Set<Appointment> updatedAppointment =
                editClientDescriptor.getAppointments().orElse(clientToEdit.getAppointments());
        Set<Routine> updatedRoutine =
                getRoutines(clientToEdit, editClientDescriptor, model);
        return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedAppointment, updatedWeight,
                updatedGender, updatedCalorie, updatedGoal, updatedTags, updatedRoutine);
    }

    private static Set<Routine> getRoutines(Client clientToEdit, EditClientDescriptor editClientDescriptor,
                                    FitBookModel model) throws CommandException {
        Optional<Set<Routine>> optionalRoutines = editClientDescriptor.getRoutines();
        List<Routine> routinesModel = model.getFitBookExerciseRoutine().getRoutineList();
        Set<Routine> updatedRoutine = new HashSet<>();
        if (optionalRoutines.isPresent()) {
            Set<Routine> routines = optionalRoutines.get();
            List<RoutineName> routineNamesToAdd = new ArrayList<>();
            routines.forEach(routine -> routineNamesToAdd.add(routine.getRoutineName()));
            Set<Routine> finalUpdatedRoutine = updatedRoutine;
            routineNamesToAdd.forEach(routineName -> routinesModel.forEach(routine -> {
                if (routineName.equals(routine.getRoutineName())) {
                    finalUpdatedRoutine.add(routine);
                }
            }));
            if (routineNamesToAdd.size() != updatedRoutine.size()) {
                throw new CommandException(MESSAGE_ROUTINE_NAME_INVALID);
            }
            return finalUpdatedRoutine;
        } else {
            updatedRoutine = clientToEdit.getRoutines();
            return updatedRoutine;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editClientDescriptor.equals(e.editClientDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Calorie calorie;
        private Weight weight;
        private Gender gender;
        private Goal goal;
        private Set<Tag> tags;
        private Set<Appointment> appointments;
        private WeightHistory weightHistory;
        private Set<Routine> routines;

        public EditClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setCalorie(toCopy.calorie);
            setWeight(toCopy.weight);
            setGender(toCopy.gender);
            setGoal(toCopy.goal);
            setAppointments(toCopy.appointments);
            setTags(toCopy.tags);
            setWeightHistory(toCopy.weightHistory);
            setRoutines(toCopy.routines);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, appointments,
                    gender, weight, calorie, goal, tags, routines);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }
        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }
        public void setGoal(Goal goal) {
            this.goal = goal;
        }
        public Optional<Goal> getGoal() {
            return Optional.ofNullable(goal);
        }
        public void setWeightHistory(WeightHistory weightHistory) {
            this.weightHistory = weightHistory;
        }
        public Optional<WeightHistory> getWeightHistory() {
            return Optional.ofNullable(weightHistory);
        }

        /**
         * Sets {@code appointments} to this object's {@code appointments}.
         * A defensive copy of {@code appointments} is used internally.
         */
        public void setAppointments(Set<Appointment> appointments) {
            this.appointments = (appointments != null) ? new HashSet<>(appointments) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code appointments} is null.
         */
        public Optional<Set<Appointment>> getAppointments() {
            return (appointments != null) ? Optional.of(Collections.unmodifiableSet(appointments)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setCalorie(Calorie calorie) {
            this.calorie = calorie;
        }

        public Optional<Calorie> getCalorie() {
            return Optional.ofNullable(calorie);
        }

        /**
         * Sets {@code routines} to this object's {@code routines}.
         * A defensive copy of {@code routines} is used internally.
         */
        public void setRoutines(Set<Routine> routines) {
            this.routines = (routines != null) ? new HashSet<>(routines) : null;
        }

        /**
         * Returns an unmodifiable routine set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code routines} is null.
         */
        public Optional<Set<Routine>> getRoutines() {
            return (routines != null) ? Optional.of(Collections.unmodifiableSet(routines)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getWeight().equals(e.getWeight())
                    && getGender().equals(e.getGender())
                    && getGoal().equals(e.getGoal())
                    && getCalorie().equals(e.getCalorie())
                    && getAppointments().equals(e.getAppointments())
                    && getTags().equals(e.getTags())
                    && getRoutines().equals(e.getRoutines());
        }
    }
}
