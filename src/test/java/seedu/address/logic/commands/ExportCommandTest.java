package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class ExportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    @Test
    void execute_exportCommandFailure() {
        List<Index> indexList = new ArrayList<>();
        Index i = Index.fromOneBased(Integer.MAX_VALUE);
        indexList.add(i);
        assertCommandFailure(new ExportCommand(indexList), model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    void execute_exportCommandSuccess() {
        List<Person> personList = model.getFilteredPersonList();
        model.showPersonContact(personList);
        List<Index> indexList = new ArrayList<>();

        for (int i = 1; i <= personList.size(); i++) {
            indexList.add(Index.fromOneBased(i));
        }

        List<Name> nameList = indexList.stream()
                .map(x -> personList.get(x.getZeroBased()).getName())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        nameList.stream().forEach(x -> sb.append(
                String.format(Messages.MESSAGE_EXPORT_PERSON_CONTACT_DETAILS, x) + "\n"));
        assertCommandSuccess(new ExportCommand(indexList), model, sb.toString(), model);
    }
}
