package cleo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Cleo cleo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image cleoImage = new Image(this.getClass().getResourceAsStream("/images/cleo.png"));

    /**
     * Sets greetings for Cleo and dimensions of scroll pane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Good day! I’m Cleo, your personal assistant,"
                        + " what’s on the agenda today?\nType 'commands' for the list of commands!";

        dialogContainer.getChildren().add(
                DialogBox.getCleoDialog(greeting, cleoImage)
        );
    }

    /**
     * Injects the Cleo instance.
     */
    public void setCleo(Cleo c) {
        cleo = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cleo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cleo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCleoDialog(response, cleoImage)
        );
        userInput.clear();
    }
}
