package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtId;
import static seedu.address.logic.commands.CommandTestUtil.showCustomerAtId;
import static seedu.address.logic.commands.CommandTestUtil.showServiceAtId;
import static seedu.address.logic.commands.CommandTestUtil.showTechnicianAtId;
import static seedu.address.logic.commands.CommandTestUtil.showVehicleAtId;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalShop;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandsTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TypicalShop().getTypicalShop());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getShop());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listCustomersIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCustomersCommand(), model, ListCustomersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listVehiclesIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListVehiclesCommand(), model, ListVehiclesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listServicesIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListServicesCommand(), model, ListServicesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listAppointmentsIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentsCommand(), model,
                ListAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listTechniciansIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTechniciansCommand(), model,
                ListTechniciansCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listPartsIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPartsCommand(), model,
                ListPartsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listCustomerIsFiltered_showsEverything() {
        showCustomerAtId(model, 1);
        assertCommandSuccess(new ListCustomersCommand(), model,
                ListCustomersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listVehicleIsFiltered_showsEverything() {
        showVehicleAtId(model, 1);
        assertCommandSuccess(new ListVehiclesCommand(), model,
                ListVehiclesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listServiceIsFiltered_showsEverything() {
        showServiceAtId(model, 1);
        assertCommandSuccess(new ListServicesCommand(), model,
                ListServicesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listAppointmentIsFiltered_showsEverything() {
        showAppointmentAtId(model, 1);
        assertCommandSuccess(new ListAppointmentsCommand(), model,
                ListAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listTechnicianIsFiltered_showsEverything() {
        showTechnicianAtId(model, 1);
        assertCommandSuccess(new ListTechniciansCommand(), model,
                ListTechniciansCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
