package vimification.internal.command.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class OrComposedSearchCommand extends SearchCommand {

    public OrComposedSearchCommand(SearchCommand... commands) {
        this(Arrays.asList(commands));
    }

    public OrComposedSearchCommand(Collection<? extends SearchCommand> commands) {
        super(commands.stream()
                .map(SearchCommand::getPredicate)
                .reduce(Predicate::and)
                .orElse(ignore -> true));
    }
}
