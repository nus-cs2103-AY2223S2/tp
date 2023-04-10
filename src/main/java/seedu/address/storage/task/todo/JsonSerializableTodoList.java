package seedu.address.storage.task.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.model.task.InternshipTodo;

/**
 * An Immutable TodoList that is serializable to JSON format.
 */
@JsonRootName(value = "todolist")
class JsonSerializableTodoList {

    public static final String MESSAGE_DUPLICATE_TODO = "Internship todo list contains duplicate InternshipTodo(s).";

    private final List<JsonAdaptedTodo> todos = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTodoList} with the given todo tasks.
     */
    @JsonCreator
    public JsonSerializableTodoList(@JsonProperty("todos")
                                            List<JsonAdaptedTodo> todos) {
        this.todos.addAll(todos);
    }

    /**
     * Converts a given {@code ReadOnlyTodoList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTodoList}.
     */
    public JsonSerializableTodoList(ReadOnlyTodoList source) {
        todos.addAll(source.getTodoList().stream().map(
                                                JsonAdaptedTodo::new).collect(Collectors.toList()));
    }

    /**
     * Converts this todo list into the model's {@code TodoList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TodoList toModelType() throws IllegalValueException {
        TodoList todoList = new TodoList();
        for (JsonAdaptedTodo jsonAdaptedTodo : todos) {
            InternshipTodo todo = jsonAdaptedTodo.toModelType();
            if (todoList.hasTodo(todo)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TODO);
            }
            todoList.addTodo(todo);
        }
        return todoList;
    }

}
