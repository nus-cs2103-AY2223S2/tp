package vimification.internal.command.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class ComposedSearchCommand extends SearchCommand {

    public ComposedSearchCommand(SearchCommand... commands) {
        this(Arrays.asList(commands));
    }

    public ComposedSearchCommand(Collection<? extends SearchCommand> commands) {
        super(commands.stream()
                .map(SearchCommand::getPredicate)
                .reduce(Predicate::and)
                .orElse(ignore -> true));
    }

}
