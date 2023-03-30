package arb.logic.commands.project;

import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static arb.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static arb.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static arb.model.Model.PROJECT_NO_COMPARATOR;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.commons.util.CollectionUtil;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Status;
import arb.model.project.Title;
import arb.model.tag.Tag;

/**
 * Edits the details of an existing project in the address book.
 */
public class EditProjectCommand extends Command {

    public static final String MESSAGE_EDIT_PROJECT_SUCCESS = "Edited Project: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the address book.";
    public static final String MESSAGE_CANNOT_FIND_CLIENT_WITH_KEYWORDS =
            "Cannot find any client with given keywords: %1$s";

    private static final String MAIN_COMMAND_WORD = "edit-project";
    private static final String ALIAS_COMMAND_WORD = "ep";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Edits the details of the project identified "
            + "by the index number used in the displayed project list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "TITLE] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + MAIN_COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Sunset painting "
            + PREFIX_DEADLINE + "2023-07-05 "
            + PREFIX_PRICE + "2.07";

    public final Index index;
    public final EditProjectDescriptor editProjectDescriptor;

    /**
     * @param index of the project in the filtered project list to edit
     * @param editProjectDescriptor details to edit the project with
     */
    public EditProjectCommand(Index index, EditProjectDescriptor editProjectDescriptor) {
        requireNonNull(index);
        requireNonNull(editProjectDescriptor);

        this.index = index;
        this.editProjectDescriptor = new EditProjectDescriptor(editProjectDescriptor);
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getSortedProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (currentListBeingShown != ListType.PROJECT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_PROJECT);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = createEditedProject(projectToEdit, editProjectDescriptor);

        if (!projectToEdit.isSameProject(editedProject) && model.hasProject(editedProject)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_PROJECT);
        }

        Optional<List<String>> optionalClientNameKeywords = editProjectDescriptor.getClientNameKeywords();

        if (optionalClientNameKeywords.isPresent() && optionalClientNameKeywords.get().isEmpty()) {
            model.unlinkClientFromProject(projectToEdit);
        } else if (optionalClientNameKeywords.isPresent()) {
            model.updateFilteredClientList(new NameContainsKeywordsPredicate(optionalClientNameKeywords.get()));
            if (model.getFilteredClientList().size() == 0) {
                model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
                throw new CommandException(String.format(MESSAGE_CANNOT_FIND_CLIENT_WITH_KEYWORDS,
                        keywordsToString(optionalClientNameKeywords.get())));
            }
            model.setProjectToLink(editedProject);
        }

        model.setProject(projectToEdit, editedProject);

        String message = String.format(MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);
        ListType toBeShown = ListType.PROJECT;
        if (optionalClientNameKeywords.isPresent() && !optionalClientNameKeywords.get().isEmpty()) {
            model.updateFilteredClientList(new NameContainsKeywordsPredicate(optionalClientNameKeywords.get()));
            message += "\n" + LinkProjectToClientCommand.MESSAGE_USAGE;
            toBeShown = ListType.CLIENT;
        } else {
            model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
            model.updateSortedProjectList(PROJECT_NO_COMPARATOR);
        }

        return new CommandResult(message, optionalClientNameKeywords.isPresent()
                && !optionalClientNameKeywords.get().isEmpty(), toBeShown);
    }

    /**
     * Creates and returns a {@code Project} with the details of {@code projectToEdit}
     * edited with {@code editProjectDescriptor}.
     */
    private static Project createEditedProject(Project projectToEdit,
                                               EditProjectDescriptor editProjectDescriptor) {
        assert projectToEdit != null;

        Title updatedTitle = editProjectDescriptor.getTitle().orElse(projectToEdit.getTitle());
        Optional<Optional<Deadline>> optionalUpdatedDeadline = editProjectDescriptor.getDeadline();
        Deadline updatedDeadline = projectToEdit.getDeadline();
        if (optionalUpdatedDeadline.isPresent()) {
            updatedDeadline = optionalUpdatedDeadline.get().orElse(null);
        }
        Status status = projectToEdit.getStatus();

        Optional<Optional<Price>> optionalUpdatedPrice = editProjectDescriptor.getPrice();
        Price updatedPrice = projectToEdit.getPrice();
        if (optionalUpdatedPrice.isPresent()) {
            updatedPrice = optionalUpdatedPrice.get().orElse(null);
        }

        Set<Tag> updatedTags = editProjectDescriptor.getTags().orElse(projectToEdit.getTags());

        return new Project(updatedTitle, status, updatedDeadline, updatedPrice, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProjectCommand)) {
            return false;
        }

        // state check
        EditProjectCommand e = (EditProjectCommand) other;
        return index.equals(e.index)
                && editProjectDescriptor.equals(e.editProjectDescriptor);
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }

    private String keywordsToString(List<String> keywords) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> keywordsIterator = keywords.iterator();
        keywordsIterator.forEachRemaining(s -> sb.append(s + ", "));
        return sb.delete(sb.length() - 2, sb.length()).toString();
    }

    /**
     * Stores the details to edit the project with. Each non-empty field value will replace the
     * corresponding field value of the project.
     */
    public static class EditProjectDescriptor {
        private Title title;
        private Optional<Deadline> deadline;
        private Optional<Price> price;
        private List<String> clientNameKeywords;

        private Set<Tag> tags;

        public EditProjectDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditProjectDescriptor(EditProjectDescriptor toCopy) {
            setTitle(toCopy.title);
            this.deadline = toCopy.deadline;
            this.price = toCopy.price;
            this.clientNameKeywords = toCopy.clientNameKeywords;
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, deadline, price, tags, clientNameKeywords);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = Optional.ofNullable(deadline);
        }

        public void setPrice(Price price) {
            this.price = Optional.ofNullable(price);
        }

        public void setClientNameKeywords(List<String> clientNameKeywords) {
            this.clientNameKeywords = clientNameKeywords;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }


        public Optional<Optional<Deadline>> getDeadline() {
            return Optional.ofNullable(this.deadline);
        }

        public Optional<Optional<Price>> getPrice() {
            return Optional.ofNullable(price);
        }

        public Optional<List<String>> getClientNameKeywords() {
            return Optional.ofNullable(clientNameKeywords);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProjectDescriptor)) {
                return false;
            }

            // state check
            EditProjectDescriptor e = (EditProjectDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDeadline().equals(e.getDeadline())
                    && getPrice().equals(e.getPrice())
                    && getTags().equals(e.getTags())
                    && getClientNameKeywords().equals(e.getClientNameKeywords());
        }
    }

}
