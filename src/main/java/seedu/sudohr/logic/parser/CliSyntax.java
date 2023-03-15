package seedu.sudohr.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    // Employee level definitions
    public static final Prefix PREFIX_ID = new Prefix("id/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    /* Prefix definitions for leave*/
    public static final Prefix PREFIX_DATE = new Prefix("g/");
    public static final Prefix PREFIX_EMPLOYEE_INDEX = new Prefix("p/");
    /* Department level definitions */

    public static final Prefix PREFIX_DEPARTMENT_NAME = new Prefix("n/");
}
