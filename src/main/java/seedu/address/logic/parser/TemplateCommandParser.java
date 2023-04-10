package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.TemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Name;

/**
 * Parses given {@code String} of arguments in the context of TemplateCommand and returns
 * a TemplateCommand for execution
 */
public class TemplateCommandParser implements Parser<TemplateCommand> {
    // Arguments should have the format: TEMPLATE NAME
    // Example: orc John Cena
    private static final Pattern COMMAND_FORMAT = Pattern.compile(
            "^(?<template>\\w+)\\s+(?<name>.+)$"
    );
    @Override
    public TemplateCommand parse(String args) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(args.trim());

        // Arguments have the wrong format
        boolean hasInvalidFormat = !matcher.matches();
        if (hasInvalidFormat) {
            String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TemplateCommand.MESSAGE_USAGE);
            throw new ParseException(errorMessage);
        }

        final String templateName = matcher.group("template");
        final String nameString = matcher.group("name");
        final Name newEntityName = ParserUtil.parseName(nameString);

        assert templateName != null;
        assert newEntityName != null;

        return new TemplateCommand(templateName, newEntityName);
    }
}
