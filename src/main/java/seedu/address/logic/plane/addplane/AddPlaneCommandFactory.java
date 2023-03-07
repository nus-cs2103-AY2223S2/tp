package seedu.address.logic.plane.addplane;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.plane.Plane;

/**
 * The factory that's responsible for creating a {@code AddPlaneCommand}.
 */
public class AddPlaneCommandFactory implements CommandFactory<AddPlaneCommand> {
    public static final String COMMAND_WORD = "add";
    public static final String PREFIX_MODEL = "/model";
    public static final String PREFIX_AGE = "/age";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_MODEL, PREFIX_AGE));
    }

    @Override
    public AddPlaneCommand createCommand(CommandParam param) throws ParseException {
        String model = param.getNamedValuesOrThrow(PREFIX_MODEL);
        int age = param.getNamedIntOrThrow(PREFIX_AGE);

        final Plane plane = new Plane(model, age);
        return new AddPlaneCommand(plane);
    }
}
