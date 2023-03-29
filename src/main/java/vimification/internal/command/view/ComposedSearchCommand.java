package vimification.internal.command.view;

import java.util.Arrays;
import java.util.function.Predicate;

public class ComposedSearchCommand extends SearchCommand {

    public ComposedSearchCommand(SearchCommand... commands) {
        super(Arrays.stream(commands)
                .map(SearchCommand::getPredicate)
                .reduce(Predicate::and)
                .orElse(ignore -> true));
    }

}
