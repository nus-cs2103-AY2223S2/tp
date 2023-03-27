package seedu.address.logic.parser;

import seedu.address.logic.commands.OrganiseCommand;
import seedu.address.model.location.Location;
import seedu.address.model.scheduler.time.Day;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactIndex;
import org.joda.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class OrganiseCommandParser implements Parser<OrganiseCommand> {

    //need ot settle organise type. is it a custom or suggestion
    //probably can only settle custom for now, by index will have to wait for integration

    public OrganiseCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, Prefix.DAY, Prefix.TIME, Prefix.LOCATION);

        if (argumentMultimap.getValue(Prefix.DAY).isPresent()) {
            List<String> indexArray = Arrays.stream(argumentMultimap.getPreamble().split(" ")).filter(x -> !x.isEmpty()).collect(Collectors.toList());
            Set<ContactIndex> indices = ParserUtil.parseIndices(indexArray);
            System.out.println(indices.toString()); //todo remove

            if (argumentMultimap.getValue(Prefix.DAY).isEmpty()) {
                throw new ParseException("NO DATE GIVEN"); //todo make this a constant
            }

            Day day;
            try {
                day = Day.valueOf(argumentMultimap.getValue(Prefix.DAY).get());
                System.out.println(day); //todo handle when day is invalid
            } catch (IllegalArgumentException e) {
                throw new ParseException("WRONG DAY");
            }

            //todo might need to create new constructor for location with no lat long
            if (argumentMultimap.getValue(Prefix.LOCATION).isEmpty()) {
                throw new ParseException("NO LOCATION GIVEN"); //todo make this constant
            }
            Location location = new Location(argumentMultimap.getValue(Prefix.LOCATION).toString(), 1.3, 103.7); //todo, ask about this
            System.out.println(location);

            if (argumentMultimap.getValue(Prefix.TIME).isEmpty()) {
                throw new ParseException("NO TIME GIVEN"); //todo make this constant
            }

            List<String> time = List.of(argumentMultimap.getValue(Prefix.TIME).get().split(" "));

            LocalTime startHour, endHour;
            try { //todo might need to add minutes later
                startHour = new LocalTime(Integer.parseInt(time.get(0)), 0);
                endHour = new LocalTime(Integer.parseInt(time.get(1)), 0);
            } catch (NumberFormatException nfe) {
                throw new ParseException("WRONG HOUR FORMAT"); //todo make this a constant
            }
            System.out.println(startHour);
            System.out.println(endHour);

            return new OrganiseCommand(day, startHour, endHour, location, indices);
        }

        try {
            ContactIndex contactIndex = new ContactIndex(Integer.parseInt(args.trim()));
            System.out.println(contactIndex.toString()); //todo remove
            return new OrganiseCommand(contactIndex);
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
