package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.MatchNamePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonSerializableAddressBook;

/**
 * Views a person's contact details
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String FILEPATH = "export/ModCheck_contact_list.json";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": exports the contact details of the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private final List<Index> index;

    /**
     * Constructor for ExportCommand
     * @param index index of the person in the list to display their contacts
     */
    public ExportCommand(List<Index> index) {
        requireNonNull(index);
        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.resetPersonHiddenStatus();
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Index> maxIndex = this.index.stream().max(Comparator.naturalOrder());
        int max = maxIndex.get().getOneBased();

        if (max > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        List<Name> nameList = index.stream().filter(x -> validateIndex(x, lastShownList))
                .map(x -> lastShownList.get(x.getZeroBased()).getName()).collect(Collectors.toList());
        model.resetPersonHiddenStatus();
        MatchNamePredicate predicate = new MatchNamePredicate(nameList);
        model.updateFilteredPersonList(predicate);
        StringBuilder sb = new StringBuilder();
        nameList.stream().forEach(x -> sb.append(
                String.format(Messages.MESSAGE_EXPORT_PERSON_CONTACT_DETAILS, x) + "\n"));

        List<Person> updatedPersonList = model.getFilteredPersonList();
        try {
            writeToJsonFile(updatedPersonList);
        } catch (IOException e) {
            throw new CommandException(Messages.MESSAGE_IOEXCEPTION);
        }
        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        // state check
        ExportCommand v = (ExportCommand) other;
        return index.equals(v.index);
    }

    /**
     * Method to write person contact details to text file
     * @param personList list of person's contact details
     * @throws IOException read/write exception
     */
    @SuppressWarnings("checkstyle:CommentsIndentation")
    public void writeToJsonFile(List<Person> personList) throws IOException {
        File file = new File(FILEPATH);
        Path path = Path.of(FILEPATH);
        FileUtil.createParentDirsOfFile(path);
        FileUtil.createIfMissing(path);
        AddressBook addressBook = new AddressBook();
        for (Person p : personList) {
            addressBook.addPerson(p);
        }
        if (file.exists() && !file.isDirectory()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(JsonUtil.toJsonString(new JsonSerializableAddressBook(addressBook)));
            writer.newLine();
            writer.close();
        }
    }

    private boolean validateIndex(Index index, List<Person> lastShownList) {
        return index.getZeroBased() >= lastShownList.size() ? false : true;
    }
}
