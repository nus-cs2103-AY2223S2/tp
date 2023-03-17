package seedu.vms.logic.commands.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.logic.parser.CliSyntax;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.logic.parser.vaccination.AddVaxTypeParser;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.testutil.SampleVaxTypeData;


public class AddVaxTypeTest {
    private static final String INVALID_AGE_RANGE_CMD = String.format("UNCHI --%s %d --%s %d",
            CliSyntax.PREFIX_MIN_AGE.getPrefix(), 100,
            CliSyntax.PREFIX_MAX_AGE.getPrefix(), 99);
    private static final String INVALID_AGE_CMD = String.format("UNCHI --%s %d --%s %d",
            CliSyntax.PREFIX_MIN_AGE.getPrefix(), -100,
            CliSyntax.PREFIX_MAX_AGE.getPrefix(), 99);

    private static final String INVALID_GROUP_CMD = String.format("UNCHI --%s %s",
            CliSyntax.PREFIX_VAX_GROUPS.getPrefix(),
            "abc," + "123," + "unchi," + "i am invalid ( ^)o(^ )b," + "banana");

    private static final String INVALID_REQ_TYPE_CMD = String.format("UNCHI --%s %s",
            CliSyntax.PREFIX_INGREDIENTS.getPrefix(),
            "rUbb15h::UNCHI");
    private static final String INVALID_REQ_CMD_EMPTY = String.format("UNCHI --%s %s",
            CliSyntax.PREFIX_INGREDIENTS.getPrefix(),
            "NONE::" + ParserUtil.KEYWORD_EMPTY_LIST);
    private static final String INVALID_REQ_CMD_SYNTAX = String.format("UNCHI --%s %s",
            CliSyntax.PREFIX_INGREDIENTS.getPrefix(),
            "NONE::UNCHI, i am invalid ( ^)o(^ )b, BANANA");
    private static final String INVALID_REQ_CMD_TOO_FEW_PARTS = String.format("UNCHI --%s %s",
            CliSyntax.PREFIX_HISTORY_REQ.getPrefix(),
            "NONE");
    private static final String INVALID_REQ_CMD_TOO_MANY_PARTS = String.format("UNCHI --%s %s",
            CliSyntax.PREFIX_HISTORY_REQ.getPrefix(),
            "NONE::UNCHI, BANANA::NONE::pear");


    @Test
    public void execute_validBasic() throws Exception {
        checkExecution(SampleVaxTypeData.CMD_NAME_1,
                SampleVaxTypeData.NAME_1,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_INGREDIENTS,
                VaxType.DEFAULT_HISTORY_REQS);
    }


    @Test
    public void execute_validPartial() throws Exception {
        checkExecution(SampleVaxTypeData.CMD_NAME_1 + SampleVaxTypeData.CMD_GROUPS_1,
                SampleVaxTypeData.NAME_1,
                SampleVaxTypeData.GROUPS_1,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_INGREDIENTS,
                VaxType.DEFAULT_HISTORY_REQS);
        checkExecution(SampleVaxTypeData.CMD_NAME_1 + SampleVaxTypeData.CMD_MIN_AGE_1,
                SampleVaxTypeData.NAME_1,
                VaxType.DEFAULT_GROUP_SET,
                SampleVaxTypeData.MIN_AGE_1,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_INGREDIENTS,
                VaxType.DEFAULT_HISTORY_REQS);
        checkExecution(SampleVaxTypeData.CMD_NAME_1 + SampleVaxTypeData.CMD_MIN_AGE_1,
                SampleVaxTypeData.NAME_1,
                VaxType.DEFAULT_GROUP_SET,
                SampleVaxTypeData.MIN_AGE_1,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_INGREDIENTS,
                VaxType.DEFAULT_HISTORY_REQS);
        checkExecution(SampleVaxTypeData.CMD_NAME_1 + SampleVaxTypeData.CMD_MAX_AGE_1,
                SampleVaxTypeData.NAME_1,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                SampleVaxTypeData.MAX_AGE_1,
                VaxType.DEFAULT_INGREDIENTS,
                VaxType.DEFAULT_HISTORY_REQS);
        checkExecution(SampleVaxTypeData.CMD_NAME_1 + SampleVaxTypeData.CMD_INGREDIENTS_1,
                SampleVaxTypeData.NAME_1,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                SampleVaxTypeData.INGREDIENTS_1,
                VaxType.DEFAULT_HISTORY_REQS);
        checkExecution(SampleVaxTypeData.CMD_NAME_1 + SampleVaxTypeData.CMD_HISTORY_REQS_1,
                SampleVaxTypeData.NAME_1,
                VaxType.DEFAULT_GROUP_SET,
                VaxType.DEFAULT_MIN_AGE,
                VaxType.DEFAULT_MAX_AGE,
                VaxType.DEFAULT_INGREDIENTS,
                SampleVaxTypeData.HISTORY_REQS_1);
    }


    @Test
    public void execute_validFull() throws Exception {
        String command = String.join(" ",
                SampleVaxTypeData.CMD_NAME_REAL,
                SampleVaxTypeData.CMD_INGREDIENTS_REAL,
                SampleVaxTypeData.CMD_MIN_AGE_REAL,
                SampleVaxTypeData.CMD_HISTORY_REQS_REAL,
                SampleVaxTypeData.CMD_MAX_AGE_REAL,
                SampleVaxTypeData.CMD_GROUPS_REAL);
        checkExecution(command,
                SampleVaxTypeData.NAME_REAL,
                SampleVaxTypeData.GROUPS_REAL,
                SampleVaxTypeData.MIN_AGE_REAL,
                SampleVaxTypeData.MAX_AGE_REAL,
                SampleVaxTypeData.INGREDIENTS_REAL,
                SampleVaxTypeData.HISTORY_REQS_REAL);
    }


    @Test
    public void execute_invalidName_exceptionThrown() throws Exception {
        // empty
        checkExecutionEx("", ParseException.class);
        // blank
        checkExecutionEx(" ", ParseException.class);
        // illegal characters
        checkExecutionEx("( ^)o(^ )b ", ParseException.class);
        // too long
        checkExecutionEx("<15 characters><15 characters>a", ParseException.class);
        // duplicate
        VaxTypeModelStub model = new VaxTypeModelStub();
        new AddVaxTypeParser().parse(SampleVaxTypeData.CMD_NAME_1).execute(model);
        assertThrows(CommandException.class,
                () -> new AddVaxTypeParser().parse(SampleVaxTypeData.CMD_NAME_1).execute(model));
    }


    @Test
    public void execute_invalidAgeRange_exceptionThrown() {
        // min > max
        checkExecutionEx(INVALID_AGE_RANGE_CMD, CommandException.class);
        // age < 0
        checkExecutionEx(INVALID_AGE_CMD, ParseException.class);
    }


    @Test
    public void execute_invalidGroup_exceptionThrown() {
        checkExecutionEx(INVALID_GROUP_CMD, ParseException.class);
    }


    @Test
    public void execute_invalidReq_exceptionThrown() {
        checkExecutionEx(INVALID_REQ_TYPE_CMD, ParseException.class);
        checkExecutionEx(INVALID_REQ_CMD_EMPTY, ParseException.class);
        checkExecutionEx(INVALID_REQ_CMD_SYNTAX, ParseException.class);
        checkExecutionEx(INVALID_REQ_CMD_TOO_FEW_PARTS, ParseException.class);
        checkExecutionEx(INVALID_REQ_CMD_TOO_MANY_PARTS, ParseException.class);
    }


    private void checkExecution(String command, GroupName name, HashSet<GroupName> groups,
                Age minAge, Age maxAge,
                HashSet<GroupName> allergyReq, List<Requirement> historyReq) throws Exception {
        VaxType expected = new VaxType(name, groups, minAge, maxAge, allergyReq, historyReq);

        VaxTypeModelStub model = new VaxTypeModelStub();
        new AddVaxTypeParser().parse(command).execute(model);
        VaxType actual = model.manager.get(name.getName()).get();

        assertEquals(expected, actual);
    }


    private <T extends Throwable> void checkExecutionEx(String command, Class<T> exception) {
        assertThrows(exception, () -> new AddVaxTypeParser().parse(command).execute(new VaxTypeModelStub()));
    }
}
