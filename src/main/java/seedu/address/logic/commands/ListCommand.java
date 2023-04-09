package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PARTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SERVICES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TECHNICIANS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VEHICLES;

import seedu.address.model.Model;

/**
 * Lists all entities in shop the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Currently listing everything";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        model.updateCustomerComparator((a, b) -> a.getId() - b.getId());

        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.updateAppointmentComparator((a, b) -> a.getDateStatus().compareTo(b.getDateStatus()));

        model.updateFilteredServiceList(PREDICATE_SHOW_ALL_SERVICES);
        model.updateServiceComparator((a, b) -> a.getId() - b.getId());

        model.updateFilteredVehicleList(PREDICATE_SHOW_ALL_VEHICLES);
        model.updateVehicleComparator((a, b) -> a.getId() - b.getId());

        model.updateFilteredTechnicianList(PREDICATE_SHOW_ALL_TECHNICIANS);
        model.updateTechnicianComparator((a, b) -> a.getId() - b.getId());

        model.updateFilteredPartMap(PREDICATE_SHOW_ALL_PARTS);
        return new CommandResult(MESSAGE_SUCCESS, Tab.ALL);
    }
}










