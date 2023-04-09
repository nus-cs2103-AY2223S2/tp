package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.TxtUtil;
import seedu.address.model.history.InputHistory;

/**
 * This class implements {@code HistoryStorage}, represent as a
 * manager for the .txt file that storing executed commands.
 */
public class TxtInputHistoryStorage implements InputHistoryStorage {

    private Path filePath = Paths.get("data", "inputHistory.txt");

    public TxtInputHistoryStorage() {}

    public TxtInputHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getHistoryStoragePath() {
        return filePath;
    }

    @Override
    public Optional<InputHistory> readInputHistory() throws IOException {
        return readInputHistory(filePath);
    }

    @Override
    public Optional<InputHistory> readInputHistory(Path filePath) throws IOException {
        requireNonNull(filePath);

        Optional<String> historyString = TxtUtil.readTxtFile(filePath);
        if (!historyString.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(InputHistory.fromDataString(historyString.get()));
    }

    @Override
    public void saveInputHistory(InputHistory history) throws IOException {
        saveInputHistory(history, filePath);
    }

    @Override
    public void saveInputHistory(InputHistory history, Path filePath) throws IOException {
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        TxtUtil.saveTxtFile(history.toDataString(), filePath);
    }
}
