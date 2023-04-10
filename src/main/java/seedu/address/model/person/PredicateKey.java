package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.parser.Prefix;

/**
 * Keys for the PersonContainsKeyPredicate hashmap.
 */
public enum PredicateKey {
    NAME(PREFIX_NAME),
    ADDRESS(PREFIX_ADDRESS),
    COMMS(PREFIX_COMMS),
    EMAIL(PREFIX_EMAIL),
    GENDER(PREFIX_GENDER),
    MAJOR(PREFIX_MAJOR),
    MODULES(PREFIX_MODULES),
    PHONE(PREFIX_PHONE),
    RACE(PREFIX_RACE),
    TAG(PREFIX_TAG),
    FACULTY(PREFIX_FACULTY);

    public final Prefix prefix;

    PredicateKey(Prefix prefixName) {
        this.prefix = prefixName;
    }
}
