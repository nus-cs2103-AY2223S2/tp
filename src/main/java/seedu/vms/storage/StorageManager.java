package seedu.vms.storage;

import java.io.IOException;
import java.util.logging.Logger;

import seedu.vms.commons.core.LogsCenter;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.model.keyword.KeywordManager;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.vaccination.ReadOnlyVaxTypeManage;
import seedu.vms.model.vaccination.VaxTypeManager;
import seedu.vms.storage.appointment.AppointmentStorage;
import seedu.vms.storage.keyword.KeywordStorage;
import seedu.vms.storage.patient.PatientManagerStorage;
import seedu.vms.storage.vaccination.VaxTypeStorage;

/**
 * Manages storage of PatientManager data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PatientManagerStorage patientManagerStorage;
    private VaxTypeStorage vaxTypeStorage;
    private AppointmentStorage appointmentStorage;
    private UserPrefsStorage userPrefsStorage;
    private KeywordStorage keywordStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PatientManagerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(
            PatientManagerStorage patientManagerStorage,
            VaxTypeStorage vaxTypeStorage,
            AppointmentStorage appointmentStorage,
            UserPrefsStorage userPrefsStorage,
            KeywordStorage keywordStorage) {
        this.patientManagerStorage = patientManagerStorage;
        this.vaxTypeStorage = vaxTypeStorage;
        this.appointmentStorage = appointmentStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.keywordStorage = keywordStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public UserPrefs readUserPrefs() throws IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ PatientManager methods ==============================

    @Override
    public ReadOnlyPatientManager readPatientManager() throws IOException {
        return patientManagerStorage.readPatientManager();
    }

    @Override
    public void savePatientManager(ReadOnlyPatientManager patientManager) throws IOException {
        patientManagerStorage.savePatientManager(patientManager);
    }

    // ================ Vax Type methods ==============================

    @Override
    public VaxTypeManager loadDefaultVaxTypes() throws RuntimeException {
        logger.fine("Attempting to load default vaccination types");
        return vaxTypeStorage.loadDefaultVaxTypes();
    }

    @Override
    public VaxTypeManager loadUserVaxTypes() throws IOException {
        return vaxTypeStorage.loadUserVaxTypes();
    }

    @Override
    public void saveVaxTypes(ReadOnlyVaxTypeManage manager) throws IOException {
        vaxTypeStorage.saveVaxTypes(manager);
    }

    // ================ Appointment methods ===========================

    @Override
    public AppointmentManager loadAppointments() throws IOException {
        return appointmentStorage.loadAppointments();
    }

    @Override
    public void saveAppointments(AppointmentManager manager) throws IOException {
        appointmentStorage.saveAppointments(manager);
    }

    // ================ Keyword methods ===============================
    @Override
    public KeywordManager loadKeywords() throws IOException {
        return keywordStorage.loadKeywords();
    }

    @Override
    public KeywordManager loadEmptyKeywords() throws RuntimeException {
        return keywordStorage.loadEmptyKeywords();
    }

    @Override
    public void saveKeywords(KeywordManager manager) throws IOException {
        keywordStorage.saveKeywords(manager);
    }

}
