package vimification.storage;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.model.CommandStack;

public class JsonAdaptedCommandStack {

    private final List<Object> commands; // TODO:

    @JsonCreator
    public JsonAdaptedCommandStack(@JsonProperty("commands") List<Object> commands) {
        if (commands == null) {
            commands = List.of();
        }
        this.commands = commands;
    }

    public JsonAdaptedCommandStack(CommandStack source) {
        commands = source.stream().collect(Collectors.toList());
    }

    public CommandStack toModelType() {
        // TODO:
        return new CommandStack();
    }

}
