package fasttrack.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fasttrack.model.category.Category;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of expenses.
 */
public class SuggestionListPanel extends UiPart<Region> {
    private static final String FXML = "SuggestionListPanel.fxml";

    @FXML
    private ListView<Category> suggestionListView;

    private final FilteredList<Category> filteredCategoryList;
    private final CommandBox commandBox;


    /**
     * Creates a {@code SuggestionListPanel} with the given {@code ObservableList}.
     * @param categoryList the list of categories to use for the suggestions
     * @param commandBox the CommandBox to use for autocomplete
     */
    public SuggestionListPanel(ObservableList<Category> categoryList, CommandBox commandBox) {
        super(FXML);
        suggestionListView.setCellFactory(listView -> new SuggestionListViewCell());
        this.filteredCategoryList = new FilteredList<>(categoryList);
        suggestionListView.setItems(filteredCategoryList);
        this.commandBox = commandBox;
        this.suggestionListView.setVisible(false);
        initialiseAutocompleteHandlers();
    }

    /**
     * Convenience method to initialise the autocomplete handlers for the suggestion list.
     * Selects the last item in the suggestion list when it gains focus
     * Adds a key press listener to the suggestion list
     * Loads the autocomplete filter to show suggestions based on user input
     */
    private void initialiseAutocompleteHandlers() {
        selectLastItemOnFocus();
        addKeyPressListener();
        loadAutocompleteFilter();
    }

    /**
     * Sets the focus on the last item in the suggestion list when the suggestion list gains focus.
     */
    private void selectLastItemOnFocus() {
        suggestionListView.focusedProperty().addListener((observable, oldFocus, currentFocus) -> {
            if (currentFocus && suggestionListView.isVisible()) {
                suggestionListView.getSelectionModel().select(filteredCategoryList.size() - 1);
            }
        });
    }

    /**
     * Adds a key press listener to the suggestion list to handle ENTER and DOWN arrow key events.
     * On ENTER, the suggested text should be set to the command box
     * with focus returned to the command box.
     * On DOWN, check if the user is going to navigate out of the suggestion list and
     * return focus to the command box.
     */
    private void addKeyPressListener() {
        suggestionListView.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if (KeyCode.ENTER == event.getCode() || KeyCode.TAB == event.getCode()) {
                updateSuggestedText();
                event.consume();
            }
            if (KeyCode.DOWN == event.getCode()) {
                handleDownArrowKey(event);
            }
        });
    }

    /**
     * Sets the text in the CommandBox to the selected category in the suggestion list
     * and hides the suggestion list.
     */
    private void updateSuggestedText() {
        Category selectedCategory = suggestionListView.getSelectionModel().getSelectedItem();
        commandBox.updateCommandBoxText(selectedCategory.getCategoryName());
        suggestionListView.setVisible(false);
        commandBox.setFocus();
    }


    /**
     * Handles the DOWN arrow key event in the suggestion list.
     * Checks if the user is going to navigate out of the suggestion list and
     * return focus to the command box.
     * @param event the key event to handle
     */
    private void handleDownArrowKey(KeyEvent event) {
        int selectedIndex = suggestionListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == filteredCategoryList.size() - 1) {
            suggestionListView.getSelectionModel().clearSelection();
            commandBox.setFocus();
            event.consume();
        }
    }

    /**
     * Loads the autocomplete filter for the CommandBox.
     */
    private void loadAutocompleteFilter() {
        commandBox.getTextProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the user is currently typing a category e.g. "c/food"
            boolean isTypingCategory = newValue.matches(".*c/[^\\s]*$");
            if (newValue.contains("c/") && isTypingCategory) {
                getAutocompleteSuggestions(newValue);
                if (!filteredCategoryList.isEmpty()) {
                    suggestionListView.setVisible(true);
                    return;
                }
            }
            // Hide the suggestion list if it is empty or the user is not typing a category
            suggestionListView.getSelectionModel().clearSelection();
            suggestionListView.setVisible(false);
        });
    }

    /**
     * Gets the autocomplete suggestions for the input string.
     * @param input the input string to get autocomplete suggestions for
     */
    public void getAutocompleteSuggestions(String input) {
        String pattern = "c/(\\S+)";
        Pattern exp = Pattern.compile(pattern);
        Matcher matcher = exp.matcher(input);
        if (matcher.find()) {
            String categoryInput = matcher.group(1);
            filteredCategoryList.setPredicate(category -> inputMatchesCategoryName(category, categoryInput));
            return;
        }
        filteredCategoryList.setPredicate(category -> true);
        suggestionListView.scrollTo(filteredCategoryList.size() - 1);
    }

    /**
     * Checks if the input matches the category name.
     * @param category the category to check
     * @param input the input to check against the category name
     * @return true if the input matches the category name, false otherwise
     */
    private boolean inputMatchesCategoryName(Category category, String input) {
        return category.getCategoryName().toLowerCase().startsWith(input.toLowerCase());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Category} using a {@code SuggestionCard}.
     */
    class SuggestionListViewCell extends ListCell<Category> {
        @Override
        protected void updateItem(Category category, boolean empty) {
            super.updateItem(category, empty);
            if (empty || category == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SuggestionCard(category).getRoot());
            }
        }
    }

}
