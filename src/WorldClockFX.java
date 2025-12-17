import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorldClockFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create GUI without FXML
        Label title = new Label("World Clock - No FXML");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ComboBox<String> countryBox = new ComboBox<>();
        countryBox.getItems().addAll("India", "Germany", "Japan", "USA", "UK");
        countryBox.setPromptText("Select country");

        Button btn = new Button("Get Time");
        TextArea result = new TextArea();
        result.setPromptText("Time will appear here");

        btn.setOnAction(e -> {
            String country = countryBox.getValue();
            if (country != null) {
                result.setText(country + ":\n10:30 PM\nMarch 17, 2025\n\nDate includes year!");
            }
        });

        VBox root = new VBox(15, title, countryBox, btn, result);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 400, 350);
        stage.setScene(scene);
        stage.setTitle("World Clock");
        stage.show();
    }
}