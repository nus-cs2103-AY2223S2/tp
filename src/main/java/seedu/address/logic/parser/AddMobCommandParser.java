package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCharacterCommand;
import seedu.address.logic.commands.AddMobCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Stats;
import seedu.address.model.entity.Character;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddMobCommandParser implements Parser<AddMobCommand> {
    public AddMobCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME,
                PREFIX_STRENGTH,
                PREFIX_DEXTERITY,
                PREFIX_INTELLIGENCE,
                PREFIX_CHALLENGE_RATING,
                PREFIX_LEGENDARY
        );

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        int str = ParserUtil.parseInt(argMultimap.getValue(PREFIX_STRENGTH).get());
        int dex = ParserUtil.parseInt(argMultimap.getValue(PREFIX_DEXTERITY).get());
        int intel = ParserUtil.parseInt(argMultimap.getValue(PREFIX_INTELLIGENCE).get());
        int cr = ParserUtil.parseInt(argMultimap.getValue(PREFIX_CHALLENGE_RATING).get());
        boolean isLegendary = Boolean.parseBoolean(argMultimap.getValue(PREFIX_LEGENDARY).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Stats stats = new Stats(str, dex, intel);

        Mob newMob = new Mob(name, stats, cr, isLegendary, tagList);

        return new AddMobCommand(newMob);
    }
}
