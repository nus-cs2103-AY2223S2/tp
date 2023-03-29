package seedu.address.logic.injector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOT;

import java.util.regex.Matcher;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
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
    public void inject_noArgPresent_lectureContext() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String command = TEST_COMMAND_NAME;
        String injectedCommand = injector.inject(command, model);

        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(injectedCommand.trim());
        assertTrue(matcher.matches());
        assertEquals(TEST_COMMAND_NAME, matcher.group("commandWord"));

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(matcher.group("arguments"), PREFIX_MODULE, PREFIX_LECTURE);

        String expectedModCode = TypicalModules.getCs2040s().getCode().toString();
        String expectedLecCode = TypicalLectures.getCs2040sWeek1().getName().toString();
        assertEquals(expectedModCode, argumentMultimap.getValue(PREFIX_MODULE).get());
        assertEquals(expectedLecCode, argumentMultimap.getValue(PREFIX_LECTURE).get());
    }

    @Test
    public void inject_noArgPresent_rootContext() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.ROOT);
        String command = TEST_COMMAND_NAME;
        String injectedCommand = injector.inject(command, model);

        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(injectedCommand.trim());
        assertTrue(matcher.matches());
        assertEquals(TEST_COMMAND_NAME, matcher.group("commandWord"));

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(matcher.group("arguments"), PREFIX_MODULE, PREFIX_LECTURE);

        assertFalse(argumentMultimap.getValue(PREFIX_MODULE).isPresent());
        assertFalse(argumentMultimap.getValue(PREFIX_LECTURE).isPresent());
    }


    @Test
    public void inject_modPresent_lecContext() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String command = TEST_COMMAND_NAME + " " + PREFIX_MODULE + " " + modCode;
        String injectedCommand = injector.inject(command, model);

        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(injectedCommand.trim());
        assertTrue(matcher.matches());
        assertEquals(TEST_COMMAND_NAME, matcher.group("commandWord"));

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(matcher.group("arguments"), PREFIX_MODULE, PREFIX_LECTURE);

        assertEquals(modCode, argumentMultimap.getValue(PREFIX_MODULE).get());
        assertFalse(argumentMultimap.getValue(PREFIX_LECTURE).isPresent());
    }

    @Test
    public void inject_lecPresent_lecContext() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String lecName = TypicalLectures.getCs2040sWeek1().getName().toString();
        String modCode = TypicalModules.getCs2040s().getCode().toString();
        String command = TEST_COMMAND_NAME + " " + PREFIX_LECTURE + " " + lecName;
        String injectedCommand = injector.inject(command, model);

        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(injectedCommand.trim());
        assertTrue(matcher.matches());
        assertEquals(TEST_COMMAND_NAME, matcher.group("commandWord"));

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(matcher.group("arguments"), PREFIX_MODULE, PREFIX_LECTURE);

        assertEquals(modCode, argumentMultimap.getValue(PREFIX_MODULE).get());
        assertEquals(lecName, argumentMultimap.getValue(PREFIX_LECTURE).get());
    }

    @Test
    public void inject_rootContext_lecContext() {
        final Model model = new ModelStubWithNavContext(TypicalNavigationContexts.LECTURE_CS2040S_WEEK_1);
        String command = TEST_COMMAND_NAME + " " + PREFIX_ROOT;
        String injectedCommand = injector.inject(command, model);

        final Matcher matcher = TrackerParser.BASIC_COMMAND_FORMAT.matcher(injectedCommand.trim());
        assertTrue(matcher.matches());
        assertEquals(TEST_COMMAND_NAME, matcher.group("commandWord"));

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(matcher.group("arguments"), PREFIX_MODULE, PREFIX_LECTURE);

        assertFalse(argumentMultimap.getValue(PREFIX_MODULE).isPresent());
        assertFalse(argumentMultimap.getValue(PREFIX_LECTURE).isPresent());
        assertFalse(argumentMultimap.getValue(PREFIX_ROOT).isPresent());
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
