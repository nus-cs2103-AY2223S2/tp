package seedu.vms.logic.commands.vaccination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableMap;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.vaccination.FilterVaxTypeParser;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.testutil.SampleVaxTypeData;

public class FindVaxTypeTest {
    private static final String VAX_NAME = "UNCHI () UNCHI";

    private VaxTypeModelStub model;


    @BeforeEach
    public void addVaxType() throws Exception {
        model = new VaxTypeModelStub();
        model.manager.add(new VaxType(
                new GroupName(VAX_NAME),
                SampleVaxTypeData.GROUPS_1,
                SampleVaxTypeData.MIN_AGE_1,
                SampleVaxTypeData.MAX_AGE_1,
                SampleVaxTypeData.INGREDIENTS_1,
                SampleVaxTypeData.HISTORY_REQS_1));
    }


    @Test
    public void execute_singlePattern() throws Exception {
        ObservableMap<String, VaxType> filteredMap = model.filteredMapView.asUnmodifiableObservableMap();

        attemptFind("UnchI");
        assertFalse(filteredMap.isEmpty());

        attemptFind("nch");
        assertFalse(filteredMap.isEmpty());

        attemptFind("BANANA");
        assertTrue(filteredMap.isEmpty());
    }


    @Test
    public void execute_multiplePattern() throws Exception {
        ObservableMap<String, VaxType> filteredMap = model.filteredMapView.asUnmodifiableObservableMap();

        attemptFind(VAX_NAME);
        assertFalse(filteredMap.isEmpty());

        attemptFind("Unchi unchi");
        assertFalse(filteredMap.isEmpty());

        attemptFind("() ()");
        assertTrue(filteredMap.isEmpty());
    }


    private void attemptFind(String command) throws Exception {
        ArgumentMultimap argsMap = ArgumentTokenizer.tokenize(command);
        new FilterVaxTypeParser().parse(argsMap).execute(model);
    }
}
