package seedu.address.logic.injector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_NAME_DESC_L1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.TrackerParser;
import seedu.address.model.Model;
import seedu.address.model.navigation.NavigationContext;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalNavigationContexts;

public class NavigationInjectorTest {

    private static final String TEST_COMMAND_NAME = "test";

    private final NavigationInjector injector = new NavigationInjector();

    @Test
    public void inject_blankInput_noChange() {
        assertNoChange("");
    }

    @Test
    public void inject_inputOnlyRootPrefix_noChange() {
        assertNoChange(PREFIX_ROOT.toString());
    }

    @Test
    public void inject_inputWithWhitelistCommandWord_noChange() {
        assertNoChange(NavigationInjector.WHITELIST[0]);
    }

    @Test
    public void inject_inputWithWhitelistCommandWordAndArg_noChange() {
        assertNoChange(NavigationInjector.WHITELIST[0] + LECTURE_NAME_DESC_L1);
    }

    private void assertNoChange(String input) {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String injectedInput = injector.inject(input, model);
        assertEquals(input, injectedInput);
    }

    @Test
    public void inject_fromRootContextWithNoArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.ROOT);
        String input = TEST_COMMAND_NAME;

        String injectedInput = injector.inject(input, model);

        assertPrefixesNotPresentInInjectedInput(injectedInput, PREFIX_MODULE, PREFIX_LECTURE);
    }

    @Test
    public void inject_fromLecContextWithNoArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String input = TEST_COMMAND_NAME;
        String injectedInput = injector.inject(input, model);

        ArgumentMultimap argumentMultimap = tokenizeNavCommandInput(injectedInput);

        String expectedModCode = TypicalModules.getCs2040s().getCode().toString();
        String expectedLecCode = TypicalLectures.getCs2040sWeek1().getName().toString();
        assertEquals(expectedModCode, argumentMultimap.getValue(PREFIX_MODULE).get());
        assertEquals(expectedLecCode, argumentMultimap.getValue(PREFIX_LECTURE).get());
    }

    @Test
    public void inject_fromLecContextWithModArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String input = String.join(" ", TEST_COMMAND_NAME, PREFIX_MODULE.toString(), modCode);

        String injectedInput = injector.inject(input, model);

        ArgumentMultimap argumentMultimap = tokenizeNavCommandInput(injectedInput);

        assertEquals(modCode, argumentMultimap.getValue(PREFIX_MODULE).get());
        assertFalse(argumentMultimap.getValue(PREFIX_LECTURE).isPresent());
    }

    @Test
    public void inject_fromLecContextWithLecArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String lecName = TypicalLectures.getCs2040sWeek1().getName().toString();
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String input = String.join(" ", TEST_COMMAND_NAME, PREFIX_LECTURE.toString(), lecName);

        String injectedInput = injector.inject(input, model);

        ArgumentMultimap argumentMultimap = tokenizeNavCommandInput(injectedInput);

        assertPrefixMatchInInjectedInput(argumentMultimap, PREFIX_MODULE, modCode);
        assertPrefixMatchInInjectedInput(argumentMultimap, PREFIX_LECTURE, lecName);
    }

    @Test
    public void inject_fromLecContextWithRootArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String input = String.join(" ", TEST_COMMAND_NAME, PREFIX_ROOT.toString());
        String injectedInput = injector.inject(input, model);

        assertPrefixesNotPresentInInjectedInput(injectedInput, PREFIX_MODULE, PREFIX_LECTURE, PREFIX_ROOT);
    }

    @Test
    public void inject_fromLecContextWithRootArgAndLecArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String lecName = TypicalLectures.getCs2040sWeek1().getName().toString();
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String input = String.join(" ", TEST_COMMAND_NAME, PREFIX_ROOT.toString(), PREFIX_LECTURE.toString(), lecName);

        String injectedInput = injector.inject(input, model);
        ArgumentMultimap argumentMultimap = tokenizeNavCommandInput(injectedInput);

        assertPrefixMatchInInjectedInput(argumentMultimap, PREFIX_MODULE, modCode);
        assertPrefixMatchInInjectedInput(argumentMultimap, PREFIX_LECTURE, lecName);
    }

    @Test
    public void inject_fromLecContextWithRootArgAndModArg_success() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String input = String.join(" ", TEST_COMMAND_NAME, PREFIX_ROOT.toString(), PREFIX_MODULE.toString(), modCode);

        String injectedInput = injector.inject(input, model);
        ArgumentMultimap argumentMultimap = tokenizeNavCommandInput(injectedInput);

        assertPrefixMatchInInjectedInput(argumentMultimap, PREFIX_MODULE, modCode);
        assertPrefixesNotPresentInInjectedInput(injectedInput, PREFIX_LECTURE);
    }


    private ArgumentMultimap tokenizeNavCommandInput(String injectedInput) {
        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(injectedInput.trim());
        assertTrue(matcher.matches());
        assertEquals(TEST_COMMAND_NAME, matcher.group("commandWord"));
        return ArgumentTokenizer.tokenize(matcher.group("arguments"), PREFIX_MODULE, PREFIX_LECTURE);
    }

    private void assertPrefixMatchInInjectedInput(ArgumentMultimap argMultimap, Prefix prefix, String expected) {
        assertEquals(expected, argMultimap.getValue(prefix).get());
    }

    private void assertPrefixesNotPresentInInjectedInput(String injectedInput, Prefix... prefixes) {
        ArgumentMultimap argumentMultimap = tokenizeNavCommandInput(injectedInput);

        for (var prefix : prefixes) {
            assertFalse(argumentMultimap.getValue(prefix).isPresent());
        }
    }

    private class ModelStubWithNavContext extends ModelStub {

        private final NavigationContext navContext;

        private ModelStubWithNavContext(NavigationContext navContext) {
            this.navContext = navContext;
        }

        @Override
        public NavigationContext getCurrentNavContext() {
            return this.navContext;
        }
    }
}
