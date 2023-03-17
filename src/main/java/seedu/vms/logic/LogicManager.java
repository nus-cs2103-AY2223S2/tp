package seedu.vms.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.GuiSettings;
import seedu.vms.commons.core.LogsCenter;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.logic.parser.VmsParser;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.util.SampleDataUtil;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeManager;
import seedu.vms.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private static final String LOAD_SUCCESS_FORMAT = "Save data for %s found";
    private static final String LOAD_DEFAULT_FORMAT = "Default data for %s will be loaded";
    private static final String LOAD_EMPTY_FORMAT = "Empty data for %s will be loaded";
    private static final String LOAD_ERROR_FORMAT = "Unable to load %s: %s";
    private static final String LOAD_DEATH_FORMAT = "Died loading %s: %s";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final VmsParser vmsParser;

    private Consumer<List<CommandResult>> onExecutionComplete = results -> {};

    private final LinkedBlockingDeque<String> commandQueue = new LinkedBlockingDeque<>();
    private volatile boolean isExecuting = true;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        vmsParser = new VmsParser();
    }


    @Override
    public void queue(String commandText) {
        commandQueue.add(commandText);
        startNext();
    }

    private synchronized void startNext() {
        if (isExecuting || commandQueue.isEmpty()) {
            return;
        }
        isExecuting = true;
        String commandText = commandQueue.poll();
        new Thread(() -> attemptProcess(
                () -> parseCommand(commandText))).start();
    }


    private void attemptProcess(Runnable process) {
        try {
            process.run();
        } catch (Throwable deathEx) {
            completeExecution(List.of(new CommandResult(
                    deathEx.toString(),
                    CommandResult.State.DEATH)));
        }
    }


    private void parseCommand(String commandText) {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            execute(vmsParser.parseCommand(commandText));
        } catch (ParseException parseEx) {
            completeExecution(List.of(new CommandResult(
                    parseEx.getMessage(),
                    CommandResult.State.ERROR)));
        }
    }


    private void execute(Command command) {
        ArrayList<CommandResult> results = new ArrayList<>();

        try {
            results.add(command.execute(model));
        } catch (CommandException ex) {
            results.add(new CommandResult(ex.getMessage(), CommandResult.State.ERROR));
            completeExecution(results);
            return;
        }

        try {
            storage.savePatientManager(model.getPatientManager());
        } catch (IOException ioe) {
            results.add(new CommandResult(FILE_OPS_ERROR_MESSAGE + ioe, CommandResult.State.WARNING));
        }

        try {
            storage.saveVaxTypes(model.getVaxTypeManager());
        } catch (IOException ioe) {
            results.add(new CommandResult(FILE_OPS_ERROR_MESSAGE + ioe, CommandResult.State.WARNING));
        }

        try {
            storage.saveAppointments(model.getAppointmentManager());
        } catch (IOException ioe) {
            results.add(new CommandResult(FILE_OPS_ERROR_MESSAGE + ioe, CommandResult.State.WARNING));
        }

        completeExecution(results, command.getFollowUp());
    }


    private void completeExecution(List<CommandResult> results) {
        completeExecution(results, Optional.empty());
    }


    private synchronized void completeExecution(List<CommandResult> results, Optional<Command> followUp) {
        onExecutionComplete.accept(results);
        if (followUp.isPresent()) {
            new Thread(() -> attemptProcess(
                    () -> execute(followUp.get()))).start();
            return;
        }
        isExecuting = false;
        startNext();
    }


    @Override
    public void loadManagers() {
        new Thread(this::performLoadSequence).start();
    }


    private void performLoadSequence() {
        ReadOnlyPatientManager patientManager = new PatientManager();
        try {
            patientManager = storage.readPatientManager();
            sendLoadInfo(String.format(LOAD_SUCCESS_FORMAT, "patients"));
        } catch (IOException ioEx) {
            sendLoadWarning(String.format(LOAD_ERROR_FORMAT,
                    "patients", ioEx.getMessage()));
            patientManager = SampleDataUtil.getSamplePatientManager();
            sendLoadInfo(String.format(LOAD_DEFAULT_FORMAT, "patients"));
        } catch (Throwable deathEx) {
            sendLoadDeath(String.format(LOAD_DEATH_FORMAT,
                    "patients", deathEx.getMessage()));
            sendLoadInfo(String.format(LOAD_EMPTY_FORMAT, "patients"));
        }
        model.setPatientManager(patientManager);

        VaxTypeManager vaxTypeManager = new VaxTypeManager();
        try {
            vaxTypeManager = storage.loadUserVaxTypes();
            sendLoadInfo(String.format(LOAD_SUCCESS_FORMAT, "vaccinations"));
        } catch (IOException ioEx) {
            sendLoadWarning(String.format(LOAD_ERROR_FORMAT,
                    "vaccinations", ioEx.getMessage()));
            vaxTypeManager = storage.loadDefaultVaxTypes();
            sendLoadInfo(String.format(LOAD_DEFAULT_FORMAT, "vaccinations"));
        } catch (Throwable deathEx) {
            sendLoadDeath(String.format(LOAD_DEATH_FORMAT,
                    "vaccinations", deathEx.getMessage()));
            sendLoadInfo(String.format(LOAD_EMPTY_FORMAT, "vaccinations"));
        }
        model.setVaxTypeManager(vaxTypeManager);


        AppointmentManager appointmentManager = new AppointmentManager();
        try {
            appointmentManager = storage.loadAppointments();
            sendLoadInfo(String.format(LOAD_SUCCESS_FORMAT, "appointments"));
        } catch (IOException ioEx) {
            sendLoadWarning(String.format(LOAD_ERROR_FORMAT,
                    "appointments", ioEx.getMessage()));
            sendLoadInfo(String.format(LOAD_EMPTY_FORMAT, "appointments"));
        } catch (Throwable deathEx) {
            sendLoadDeath(String.format(LOAD_DEATH_FORMAT,
                    "appointments", deathEx.getMessage()));
            sendLoadInfo(String.format(LOAD_EMPTY_FORMAT, "appointments"));
        }
        model.setAppointmentManager(appointmentManager);

        isExecuting = false;
    }


    private void sendLoadInfo(String message) {
        logger.info(message);
        onExecutionComplete.accept(List.of(new CommandResult(message)));
    }


    private void sendLoadWarning(String message) {
        logger.warning(message);
        onExecutionComplete.accept(List.of(new CommandResult(
                message, CommandResult.State.WARNING)));
    }


    private void sendLoadDeath(String message) {
        logger.severe(message);
        onExecutionComplete.accept(List.of(new CommandResult(
                message, CommandResult.State.DEATH)));
    }


    @Override
    public void setOnExecutionCompletion(Consumer<List<CommandResult>> onExecutionComplete) {
        this.onExecutionComplete = onExecutionComplete;
    }


    @Override
    public ReadOnlyPatientManager getPatientManager() {
        return model.getPatientManager();
    }

    @Override
    public ObservableMap<Integer, IdData<Patient>> getFilteredPatientMap() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableMap<String, VaxType> getFilteredVaxTypeMap() {
        return model.getFilteredVaxTypeMap();
    }

    @Override
    public ObservableMap<Integer, IdData<Appointment>> getFilteredAppointmentMap() {
        return model.getFilteredAppointmentMap();
    }

    @Override
    public Path getPatientManagerFilePath() {
        return model.getPatientManagerFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
