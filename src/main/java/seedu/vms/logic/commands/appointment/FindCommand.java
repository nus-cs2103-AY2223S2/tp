package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.util.CollectionUtil;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.model.GroupName;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.appointment.predicates.EndTimePredicate;
import seedu.vms.model.appointment.predicates.IndexPredicate;
import seedu.vms.model.appointment.predicates.StartTimePredicate;
import seedu.vms.model.appointment.predicates.VaccineContainsKeywordsPredicate;

/**
 * Finds and lists all appointments in appointment manager where any of the argument keywords match.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_GROUP = "appointment";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Finds all appointments that matches the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Either a keyword or flag must be provided.\n"
            + "Syntax: " + COMMAND_GROUP + " " + COMMAND_WORD
            + " [...KEYWORDS...] "
            + "[" + DELIMITER + PREFIX_STARTTIME + " START_TIME] "
            + "[" + DELIMITER + PREFIX_ENDTIME + " END_TIME] "
            + "[" + DELIMITER + PREFIX_VACCINATION + " VAX_GROUP]\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " Dose 1\n"
            + "(List all appointments that uses \"Dose 1')\"";

    private final Optional<IndexPredicate> indexPredicate;
    private final Optional<StartTimePredicate> startTimePredicate;
    private final Optional<EndTimePredicate> endTimePredicate;
    private final Optional<VaccineContainsKeywordsPredicate> vaccinePredicate;
    private final Optional<Boolean> isCompletedPredicate;

    /**
     * FindCommand that contains more appointment information that is given by the user.
     * Accepts different descriptors when applicable
     *
     * @param findAppointmentDescriptor
     */
    public FindCommand(FindAppointmentDescriptor findAppointmentDescriptor) {
        if (findAppointmentDescriptor.getPatient().isPresent()) {
            this.indexPredicate = Optional.of(new IndexPredicate(findAppointmentDescriptor.getPatient().get()));
        } else {
            this.indexPredicate = Optional.empty();
        }

        if (findAppointmentDescriptor.getAppointmentTime().isPresent()) {
            this.startTimePredicate = Optional
                    .of(new StartTimePredicate(findAppointmentDescriptor.getAppointmentTime().get()));
        } else {
            this.startTimePredicate = Optional.empty();
        }

        if (findAppointmentDescriptor.getAppointmentEndTime().isPresent()) {
            this.endTimePredicate = Optional
                    .of(new EndTimePredicate(findAppointmentDescriptor.getAppointmentEndTime().get()));
        } else {
            this.endTimePredicate = Optional.empty();
        }

        if (findAppointmentDescriptor.getVaccination().isPresent()) {
            this.vaccinePredicate = Optional
                    .of(new VaccineContainsKeywordsPredicate(findAppointmentDescriptor.getVaccination().get()));
        } else {
            this.vaccinePredicate = Optional.empty();
        }

        if (findAppointmentDescriptor.getStatus().isPresent()) {
            this.isCompletedPredicate = Optional.of(findAppointmentDescriptor.getStatus().get());
        } else {
            this.isCompletedPredicate = Optional.empty();
        }
    }

    @Override
    public CommandMessage execute(Model model) {
        requireNonNull(model);
        List<Optional<? extends Predicate<Appointment>>> optionalFilters = List.of(indexPredicate, vaccinePredicate);
        List<Predicate<Appointment>> filters = optionalFilters.stream()
                .filter(Objects::nonNull)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        model.setAppointmentFilters(filters);
        return new CommandMessage(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, model.getFilteredAppointmentMap().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles Optional.empty()s
                        && indexPredicate.equals(((FindCommand) other).indexPredicate) // state check
                        && vaccinePredicate.equals(((FindCommand) other).vaccinePredicate)); // state check
    }

    /**
     * Stores the details to find the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class FindAppointmentDescriptor {
        private Index patientId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Set<GroupName> vaccines;
        private boolean isCompleted;

        public FindAppointmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public FindAppointmentDescriptor(FindAppointmentDescriptor toCopy) {
            this.patientId = toCopy.patientId;
            this.startTime = toCopy.startTime;
            this.endTime = toCopy.endTime;
            this.vaccines = toCopy.vaccines;
            this.isCompleted = toCopy.isCompleted;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientId, startTime, endTime, vaccines, isCompleted);
        }

        public Optional<Index> getPatient() {
            return Optional.ofNullable(patientId);
        }
        public void setPatient(Index patientId) {
            this.patientId = patientId;
        }

        public Optional<LocalDateTime> getAppointmentTime() {
            return Optional.ofNullable(startTime);
        }

        public void setAppointmentTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }
        public Optional<LocalDateTime> getAppointmentEndTime() {
            return Optional.ofNullable(endTime);
        }
        public void setAppointmentEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public Optional<Set<GroupName>> getVaccination() {
            return (vaccines != null) ? Optional.of(Collections.unmodifiableSet(vaccines)) : Optional.empty();
        }
        public void setVaccination(Set<GroupName> vaccines) {
            this.vaccines = vaccines;
        }

        public Optional<Boolean> getStatus() {
            return Optional.of(isCompleted);
        }
        public void setStatus(boolean isCompleted) {
            this.isCompleted = isCompleted;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindAppointmentDescriptor)) {
                return false;
            }

            // state check
            FindAppointmentDescriptor e = (FindAppointmentDescriptor) other;

            return getPatient().equals(e.getPatient())
                    && getAppointmentTime().equals(e.getAppointmentTime())
                    && getAppointmentEndTime().equals(e.getAppointmentEndTime())
                    && getVaccination().equals(e.getVaccination())
                    && getStatus().equals(e.getStatus());
        }
    }
}
