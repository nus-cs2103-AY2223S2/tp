package seedu.fitbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.client.Client;

/**
 * An Immutable FitBook that is serializable to JSON format.
 */
@JsonRootName(value = "fitbook")
class JsonSerializableFitBook {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFitBook} with the given clients.
     */
    @JsonCreator
    public JsonSerializableFitBook(@JsonProperty("clients") List<JsonAdaptedClient> clients) {
        this.clients.addAll(clients);
    }

    /**
     * Converts a given {@code ReadOnlyFitBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFitBook}.
     */
    public JsonSerializableFitBook(ReadOnlyFitBook source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code FitBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FitBook toFitBookModelType() throws IllegalValueException {
        FitBook addressBook = new FitBook();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toFitBookModelType();
            if (addressBook.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            addressBook.addClient(client);
        }
        return addressBook;
    }

}
