package arb.logic.commands.project;

import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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

    private static final String MAIN_COMMAND_WORD = "edit-project";
    private static final String ALIAS_COMMAND_WORD = "ep";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Edits the details of the project identified "
            + "by the index number used in the displayed project list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: <index> (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_CLIENT + "CLIENT]* "
            + "[" + PREFIX_TAG + "TAG]*\n"
            + "Example: " + MAIN_COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Sunset painting "
            + PREFIX_DEADLINE + "2023-07-05";

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

        if (currentListBeingShown != ListType.PROJECT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_PROJECT);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = createEditedProject(projectToEdit, editProjectDescriptor);

        if (!projectToEdit.isSameProject(editedProject) && model.hasProject(editedProject)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_PROJECT);
        }

        String message = String.format(MESSAGE_EDIT_PROJECT_SUCCESS, editedProject);
        ListType toBeShown = ListType.PROJECT;

        Optional<Optional<NameContainsKeywordsPredicate>> optionalClientNamePredicate =
                editProjectDescriptor.getClientNamePredicate();
        boolean shouldResetLinkedClient = optionalClientNamePredicate.map(o -> !o.isPresent()).orElse(false);
        boolean shouldChangeLinkedClient = optionalClientNamePredicate.map(o -> o.isPresent()).orElse(false);
        if (shouldResetLinkedClient) {
            model.unlinkClientFromProject(projectToEdit);
        } else if (shouldChangeLinkedClient) {
            if (model.numberOfClientsMatchingPredicate(optionalClientNamePredicate.get().get()) == 0) {
                throw new CommandException(String.format(Messages.MESSAGE_CANNOT_FIND_CLIENT_WITH_KEYWORDS,
                        optionalClientNamePredicate.get().get().keywordsToString()));
            }
            model.setProjectToLink(editedProject);
            model.updateFilteredClientList(optionalClientNamePredicate.get().get());
            message += "\n" + LinkProjectToClientCommand.MESSAGE_USAGE;
            toBeShown = ListType.CLIENT;
        }

        model.setProject(projectToEdit, editedProject);
        model.resetFilteredAndSortedProjectList();

        return new CommandResult(message, shouldChangeLinkedClient, toBeShown);
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

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }

    /**
     * Stores the details to edit the project with. Each non-empty field value will replace the
     * corresponding field value of the project.
     */
    public static class EditProjectDescriptor {
        private Title title;
        private Optional<Deadline> deadline;
        private Optional<Price> price;
        private Optional<NameContainsKeywordsPredicate> clientNamePredicate;
        private Set<Tag> tags;

        public EditProjectDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditProjectDescriptor(EditProjectDescriptor toCopy) {
            setTitle(toCopy.title);
            this.deadline = toCopy.deadline;
            this.price = toCopy.price;
            this.clientNamePredicate = toCopy.clientNamePredicate;
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, deadline, price, tags, clientNamePredicate);
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

        public void setClientNamePredicate(NameContainsKeywordsPredicate clientNamePredicate) {
            this.clientNamePredicate = Optional.ofNullable(clientNamePredicate);
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

        public Optional<Optional<NameContainsKeywordsPredicate>> getClientNamePredicate() {
            return Optional.ofNullable(clientNamePredicate);
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
                    && getClientNamePredicate().equals(e.getClientNamePredicate());
        }
    }

}
