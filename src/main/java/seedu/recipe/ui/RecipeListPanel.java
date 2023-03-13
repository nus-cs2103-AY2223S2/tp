package seedu.recipe.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.model.recipe.Recipe;

/**
 * Panel containing the list of recipes.
 */
public class RecipeListPanel extends UiPart<Region> {
    private static final String FXML = "RecipeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecipeListPanel.class);
    private RecipeDetailsPanel recipeDetailsPanel = new RecipeDetailsPanel();

    @FXML
    private ListView<Recipe> recipeListView;

    @FXML
    private AnchorPane recipeDetailsPanelPlaceholder;


    /**
     * Creates a {@code RecipeListPanel} with the given {@code ObservableList}.
     */
    public RecipeListPanel(ObservableList<Recipe> recipeList) {
        super(FXML);
        if (!recipeList.isEmpty()) {
            recipeDetailsPanel.setRecipeDetails(recipeList.get(0));
        } else {
            recipeDetailsPanel.setEmptyRecipeDetails();
        }
        recipeDetailsPanelPlaceholder.getChildren().add(recipeDetailsPanel.getRoot());
        updateDetailsIfChanged(recipeList);
        recipeListView.setItems(recipeList);
        recipeListView.setCellFactory(listView -> new RecipeListViewCell());
    }


    private void updateDetailsIfChanged(ObservableList<Recipe> recipeList) {
        recipeList.addListener(new ListChangeListener<Recipe>() {
            @Override
            public void onChanged(Change<? extends Recipe> change) {
                while (change.next()) {
                    if (change.wasAdded() || change.wasUpdated()) {
                        int indexToChange = change.getFrom();
                        Recipe recipeToDisplay = change.getList().get(indexToChange);
                        recipeListView.scrollTo(indexToChange);
                        recipeListView.getSelectionModel().select(indexToChange);
                        recipeListView.getFocusModel().focus(indexToChange);
                        recipeDetailsPanel.setRecipeDetails(recipeToDisplay);
                    } else if (change.wasRemoved()) {
                        if (recipeList.size() > 0) {
                            recipeListView.scrollTo(0);
                            recipeListView.getSelectionModel().select(0);
                            recipeListView.getFocusModel().focus(0);
                        }
                    }
                }
            }
        });
    }

    /**
     * Handles mouse click event on the patient card.
     *
     * @param mouseEvent created by the user
     */
    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        Recipe recipeToDisplay = recipeListView.getSelectionModel().getSelectedItem();
        recipeDetailsPanel.setRecipeDetails(recipeToDisplay);
        logger.log(Level.INFO, "Patient mouse click handled.");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Recipe} using a {@code RecipeCard}.
     */
    class RecipeListViewCell extends ListCell<Recipe> {
        @Override
        protected void updateItem(Recipe recipe, boolean empty) {
            super.updateItem(recipe, empty);

            if (empty || recipe == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RecipeCard(recipe, getIndex() + 1).getRoot());
            }
        }
    }

}
