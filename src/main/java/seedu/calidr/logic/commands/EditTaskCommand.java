package seedu.calidr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.calidr.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.calidr.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import net.fortuna.ical4j.filter.FilterExpression;
import seedu.calidr.commons.core.Messages;
import seedu.calidr.commons.core.index.Index;
import seedu.calidr.commons.util.CollectionUtil;
import seedu.calidr.logic.commands.exceptions.CommandException;
import seedu.calidr.model.Model;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.ToDo;
import seedu.calidr.model.task.params.Description;
import seedu.calidr.model.task.params.EventDateTimes;
import seedu.calidr.model.task.params.Location;
import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.params.Title;
import seedu.calidr.model.task.params.TodoDateTime;

/**
 * Edits the details of an existing task in the task list.
 */
public class EditTaskCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_BY + "BY] (only for todos)"
            + "[" + PREFIX_FROM + "FROM] (only for events)"
            + "[" + PREFIX_TO + "TO] (only for events)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Science Homework "
            + PREFIX_BY + "03-11-2024 2359";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the read-only task-list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> readOnlyTaskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= readOnlyTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = readOnlyTaskList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.equals(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }


    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor)
            throws CommandException {

        assert taskToEdit != null;

        if (taskToEdit instanceof ToDo) {
            ToDo todoToEdit = (ToDo) taskToEdit;

            Title updatedTitle = editTaskDescriptor.getTitle().orElse(todoToEdit.getTitle());
            Description oldDescription = todoToEdit.getDescription().orElse(null);
            Description updatedDescription = editTaskDescriptor.getDescription().orElse(oldDescription);
            Location oldLocation = todoToEdit.getLocation().orElse(null);
            Location updatedLocation = editTaskDescriptor.getLocation().orElse(oldLocation);
            Priority updatedPriority = editTaskDescriptor.getPriority().orElse(todoToEdit.getPriority());
            TodoDateTime updatedBy = editTaskDescriptor.getByDateTime().orElse(todoToEdit.getBy());

            ToDo updatedTodo = new ToDo(updatedTitle, updatedBy);
            updatedTodo.setDescription(updatedDescription);
            updatedTodo.setLocation(updatedLocation);
            updatedTodo.setPriority(updatedPriority);

            return updatedTodo;

        } else if (taskToEdit instanceof Event) {
            Event eventToEdit = (Event) taskToEdit;

            Title updatedTitle = editTaskDescriptor.getTitle().orElse(eventToEdit.getTitle());
            Description oldDescription = eventToEdit.getDescription().orElse(null);
            Description updatedDescription = editTaskDescriptor.getDescription().orElse(oldDescription);
            Location oldLocation = eventToEdit.getLocation().orElse(null);
            Location updatedLocation = editTaskDescriptor.getLocation().orElse(oldLocation);
            Priority updatedPriority = editTaskDescriptor.getPriority().orElse(eventToEdit.getPriority());

            LocalDateTime updatedFromDateTime = editTaskDescriptor.getFromDateTime()
                    .orElse(eventToEdit.getEventDateTimes().from);
            LocalDateTime updatedToDateTime = editTaskDescriptor.getToDateTime()
                    .orElse(eventToEdit.getEventDateTimes().to);

            if (!EventDateTimes.isValidEventDateTimes(updatedFromDateTime, updatedToDateTime)) {
                throw new CommandException(EventDateTimes.MESSAGE_CONSTRAINTS);
            }
            EventDateTimes updatedEventTimes = new EventDateTimes(updatedFromDateTime, updatedToDateTime);

            Event updatedEvent = new Event(updatedTitle, updatedEventTimes);
            updatedEvent.setDescription(updatedDescription);
            updatedEvent.setLocation(updatedLocation);
            updatedEvent.setPriority(updatedPriority);

            return updatedEvent;

        } else {
            // error
            // todo store messages as constant in messages
            throw new CommandException("Invalid task type!");
        }
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Title title;
        private Description description;
        private Location location;
        private TodoDateTime byDateTime;
        private LocalDateTime fromDateTime;
        private LocalDateTime toDateTime;
        private Priority priority;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setLocation(toCopy.location);
            setByDateTime(toCopy.byDateTime);
            setFromDateTime(toCopy.fromDateTime);
            setToDateTime(toCopy.toDateTime);
            setPriority(toCopy.priority);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, location, byDateTime,
                    fromDateTime, toDateTime, priority);
        }

        public void setTitle(Title title) {
            this.title = title;
        }
        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);

        }
        public void setLocation(Location location) {
            this.location = location;
        }

        public void setByDateTime(TodoDateTime todoDateTime) {
            this.byDateTime = todoDateTime;
        }
        public Optional<TodoDateTime> getByDateTime() {
            return Optional.ofNullable(byDateTime);
        }

        public void setFromDateTime(LocalDateTime localDateTime) {
            this.fromDateTime = localDateTime;
        }
        public Optional<LocalDateTime> getFromDateTime() {
            return Optional.ofNullable(fromDateTime);
        }

        public void setToDateTime(LocalDateTime localDateTime) {
            this.toDateTime = localDateTime;
        }
        public Optional<LocalDateTime> getToDateTime() {
            return Optional.ofNullable(toDateTime);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }
        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;
            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getLocation().equals(e.getLocation())
                    && getByDateTime().equals(e.getByDateTime())
                    && getFromDateTime().equals(e.getFromDateTime())
                    && getToDateTime().equals(e.getToDateTime())
                    && getPriority().equals(e.getPriority());
        }
    }
}
