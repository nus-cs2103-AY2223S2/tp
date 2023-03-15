package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCharacterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Stats;
import seedu.address.model.entity.Character;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddCharacterCommandParser implements Parser<AddCharacterCommand> {
    public AddCharacterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME,
                PREFIX_STRENGTH,
                PREFIX_DEXTERITY,
                PREFIX_INTELLIGENCE,
                PREFIX_LEVEL,
                PREFIX_XP
        );

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        int str = ParserUtil.parseInt(argMultimap.getValue(PREFIX_STRENGTH).get());
        int dex = ParserUtil.parseInt(argMultimap.getValue(PREFIX_DEXTERITY).get());
        int intel = ParserUtil.parseInt(argMultimap.getValue(PREFIX_INTELLIGENCE).get());
        int level = ParserUtil.parseInt(argMultimap.getValue(PREFIX_LEVEL).get());
        int xp = ParserUtil.parseInt(argMultimap.getValue(PREFIX_XP).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Stats stats = new Stats(str, dex, intel);

        Character newChar = new Character(name, stats, level, xp, tagList);

        return new AddCharacterCommand(newChar);
    }
}
