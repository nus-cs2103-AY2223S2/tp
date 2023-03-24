package seedu.sudohr.logic.commands.leave;


import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;
import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.BENSON;
import static seedu.sudohr.testutil.TypicalEmployees.CARL;
import static seedu.sudohr.testutil.TypicalEmployees.DANIEL;
import static seedu.sudohr.testutil.TypicalEmployees.ELLE;
import static seedu.sudohr.testutil.TypicalEmployees.FIONA;
import static seedu.sudohr.testutil.TypicalEmployees.GEORGE;
import static seedu.sudohr.testutil.TypicalEmployees.HOON;
import static seedu.sudohr.testutil.TypicalEmployees.IDA;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;





public class ListLeavesByEmployeeCommandTest {
    private Model model;

    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSudoHr(), new UserPrefs());

    }



}
