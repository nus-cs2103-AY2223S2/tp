package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays the different Organisation helps available
 */
public class HelpOrganisationCommand extends HelpCommand {

    public static final String COMMAND_WORD = "organisation";

    public static final String HEADER_1 = "----- Tab-leport Commands -----\n";

    public static final String HEADER_2 = "\n----- Filtering Commands ----- (METRIC: performance/urgency)\n";

    public static final String HEADER_3 = "\n----- Sorting Commands ----- (EVENT_TYPE: all/tutoria/lab/consultation)"
            + " (METRIC: name/performance/email/remark)"
            + " (ORDER: reverse/nonreverse)\n";

    public static final String CD_STUDENT = "Go to Students tab: "
            + "         cd_student\n";

    public static final String CD_EVENT = "Go to Events tab: "
            + "            cd_event\n";

    public static final String FILTER = "Filter event student list:     filter METRIC THRESHOLD\n";

    public static final String SORT = "Sort event student list:     sort-student EVENT_TYPE METRIC ORDER\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HEADER_1
                + CD_STUDENT
                + CD_EVENT
                + HEADER_2
                + FILTER
                + HEADER_3
                + SORT);
    }
}
