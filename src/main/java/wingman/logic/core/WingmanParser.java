package wingman.logic.core;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.logic.crew.linkflight.CrewFlightLinkCommandFactory;
import wingman.logic.crew.linklocation.CrewLocationLinkCommandFactory;
import wingman.logic.flight.linklocation.FlightLocationLinkCommandFactory;
import wingman.logic.pilot.linkflight.PilotFlightLinkCommandFactory;
import wingman.logic.pilot.linklocation.PilotLocationLinkCommandFactory;
import wingman.logic.plane.linkflight.PlaneFlightLinkCommandFactory;
import wingman.logic.plane.linklocation.PlaneLocationLinkCommandFactory;
import wingman.logic.toplevel.add.AddCommandFactory;
import wingman.logic.toplevel.changemode.ChangeModeCommandFactory;
import wingman.logic.toplevel.delete.DeleteCommandFactory;
import wingman.logic.toplevel.syntax.CrewSyntax;
import wingman.logic.toplevel.syntax.FlightSyntax;
import wingman.logic.toplevel.syntax.LocationSyntax;
import wingman.logic.toplevel.syntax.PilotSyntax;
import wingman.logic.toplevel.syntax.PlaneSyntax;
import wingman.model.OperationMode;

/**
 * The parser that's responsible for parsing the user input and handling the
 * commands.
 */
public class WingmanParser extends FactoryParser {
    /**
     * The command groups that are available in the application.
     */
    private static final List<CommandGroup> COMMAND_GROUPS = List.of(
            new CommandGroup(
                    OperationMode.PILOT,
                    List.of(
                            new AddCommandFactory<>(
                                    "pilot",
                                    Optional.of(PilotSyntax.PREFIXES),
                                    PilotSyntax::add,
                                    PilotSyntax::factory
                            ),
                            new DeleteCommandFactory<>(
                                    PilotSyntax::getManager,
                                    PilotSyntax::delete
                            ),
                            PilotFlightLinkCommandFactory.linkFactory(),
                            PilotFlightLinkCommandFactory.unlinkFactory(),
                            PilotLocationLinkCommandFactory.linkFactory(),
                            PilotLocationLinkCommandFactory.unlinkFactory()
                    )
            ),
            new CommandGroup(
                    OperationMode.CREW,
                    List.of(
                            new AddCommandFactory<>(
                                    "crew",
                                    Optional.of(CrewSyntax.PREFIXES),
                                    CrewSyntax::add,
                                    CrewSyntax::factory
                            ),
                            new DeleteCommandFactory<>(
                                    CrewSyntax::getManager,
                                    CrewSyntax::delete
                            ),
                            CrewFlightLinkCommandFactory.linkFactory(),
                            CrewFlightLinkCommandFactory.unlinkFactory(),
                            CrewLocationLinkCommandFactory.linkFactory(),
                            CrewLocationLinkCommandFactory.unlinkFactory()
                    )
            ),
            new CommandGroup(
                    OperationMode.PLANE,
                    List.of(
                            new AddCommandFactory<>(
                                    "plane",
                                    Optional.of(PlaneSyntax.PREFIXES),
                                    PlaneSyntax::add,
                                    PlaneSyntax::factory
                            ),
                            new DeleteCommandFactory<>(
                                    PlaneSyntax::getManager,
                                    PlaneSyntax::delete
                            ),
                            PlaneFlightLinkCommandFactory.linkFactory(),
                            PlaneFlightLinkCommandFactory.unlinkFactory(),
                            PlaneLocationLinkCommandFactory.linkFactory(),
                            PlaneLocationLinkCommandFactory.unlinkFactory()
                    )
            ),
            new CommandGroup(
                    OperationMode.LOCATION,
                    List.of(
                            new AddCommandFactory<>(
                                    "location",
                                    Optional.of(LocationSyntax.PREFIXES),
                                    LocationSyntax::add,
                                    LocationSyntax::factory
                            ),
                            new DeleteCommandFactory<>(
                                    LocationSyntax::getManager,
                                    LocationSyntax::delete
                            )
                    )
            ),
            new CommandGroup(
                    OperationMode.FLIGHT,
                    List.of(
                            new AddCommandFactory<>(
                                    "flight",
                                    Optional.of(FlightSyntax.PREFIXES),
                                    FlightSyntax::add,
                                    FlightSyntax::factory
                            ),
                            new DeleteCommandFactory<>(
                                    FlightSyntax::getManager,
                                    FlightSyntax::delete
                            ),
                            FlightLocationLinkCommandFactory.linkFactory(),
                            FlightLocationLinkCommandFactory.unlinkFactory()
                    )
            )
    );
    /**
     * The top level command factories that are available in the application.
     */
    private static final List<CommandFactory<?>> COMMAND_FACTORIES = List.of(
            new ChangeModeCommandFactory()
    );

    /**
     * The command groups that are available in the application.
     */
    private final List<CommandGroup> groups;

    /**
     * The list of top-level factories that produces commands.
     */
    private final List<CommandFactory<?>> factories;

    /**
     * Constructs a {@code WingmanParser} with the command groups given.
     *
     * @param commandGroups the command groups that are available in the
     *                      application.
     * @param factories     the top-level factories that are responsible
     *                      for parsing of top-level features.
     */
    public WingmanParser(
            List<CommandGroup> commandGroups,
            List<CommandFactory<?>> factories
    ) {
        this.groups = commandGroups;
        this.factories = factories;
    }

    /**
     * Constructs a {@code WingmanParser} with the default {@code
     * COMMAND_GROUPS} and {@code  COMMAND_FACTORIES}
     */
    public WingmanParser() {
        this(COMMAND_GROUPS, COMMAND_FACTORIES);
    }

    /**
     * Tokenizes the user input into a {@code Deque} of {@code String}s. It
     * splits the user input by whitespace. This may result in the token
     * being unable to recover back to the original user input, i.e. if the
     * user used different whitespace characters other than spaces. However,
     * for the purpose of this application, this is acceptable.
     *
     * @param userInput the user input to tokenize.
     * @return a {@code Deque} of {@code String}s that contains the tokens.
     */
    private static Deque<String> tokenize(String userInput) {
        return new ArrayDeque<>(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param operationMode the operation mode at which the command is
     *                      executed.
     * @param userInput     the user input to parse.
     * @return the command that corresponds to the user input.
     * @throws ParseException if a parsing error exists, or if no command is
     *                        found in the parser.
     */
    public Command parse(
            OperationMode operationMode,
            String userInput
    ) throws ParseException, CommandException {
        final Deque<String> tokens = tokenize(userInput);

        final Optional<Command> topCommand = this.parseFactory(tokens);

        if (topCommand.isPresent()) {
            return topCommand.get();
        }

        final Optional<Command> groupedCommand =
                this.parseGroup(operationMode, tokens);

        if (groupedCommand.isPresent()) {
            return groupedCommand.get();
        }

        throw new ParseException("No command group found for operation mode "
                                         + operationMode);
    }

    /**
     * Helps to delegate the parsing to each command group. This is
     * abstracted out so that we can have single level of abstraction in
     * the {@code parse} method.
     *
     * @param operationMode the operation mode at which the command is
     * @param tokens        the tokens to parse.
     * @return the command that corresponds to the user input.
     * @throws ParseException if a parsing error exists. Note that if no
     *                        group is found, this method will simply return an
     *                        empty {@code Optional}.
     */
    private Optional<Command> parseGroup(
            OperationMode operationMode,
            Deque<String> tokens
    ) throws ParseException, CommandException {
        for (CommandGroup commandGroup : this.groups) {
            if (!commandGroup.getOperationMode().equals(operationMode)) {
                continue;
            }
            return Optional.of(commandGroup.parse(tokens));
        }
        return Optional.empty();
    }

    @Override
    protected List<CommandFactory<?>> getFactories() {
        return this.factories;
    }
}
