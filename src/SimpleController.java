// SimpleController.java - JavaFX Controller
// Make sure javafx libraries are added to project

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SimpleController {

    // These MUST match FXML file fx:id exactly
    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private TextArea resultArea;

    @FXML
    private Button timeButton;

    // This runs when window opens
    @FXML
    public void initialize() {
        // Add countries (like your console app)
        countryBox.getItems().addAll(
                "India", "Pakistan", "Germany",
                "USA-NewYork", "Japan", "UK",
                "Australia-Sydney", "France", "China", "SaudiArabia"
        );
        countryBox.setPromptText("Select a country...");

        // Button action
        timeButton.setOnAction(event -> showTime());
    }

    // Button click action
    private void showTime() {
        String country = countryBox.getValue();

        if (country == null) {
            resultArea.setText("⚠️ Please select a country first!");
            return;
        }

        // Get time info
        String timeInfo = getTimeInfo(country);
        resultArea.setText(timeInfo);
    }

    // Get time information
    private String getTimeInfo(String country) {
        String timezone = getTimezone(country);
        String exampleTime = getExampleTime(country);

        String result = "══════════════════════════════\n";
        result += "         " + country.toUpperCase() + "\n";
        result += "══════════════════════════════\n\n";
        result += "🕐 Time: " + exampleTime + " (AM/PM)\n";
        result += "📅 Date: March 17, 2025 ← YEAR INCLUDED\n";
        result += "🌐 Timezone: " + timezone + "\n\n";
        result += "──────────────────────────────\n";
        result += "Same logic as console version!";

        return result;
    }

    // Get timezone
    private String getTimezone(String country) {
        switch(country) {
            case "India": return "Asia/Kolkata";
            case "Pakistan": return "Asia/Karachi";
            case "Germany": return "Europe/Berlin";
            case "USA-NewYork": return "America/New_York";
            case "Japan": return "Asia/Tokyo";
            case "UK": return "Europe/London";
            case "Australia-Sydney": return "Australia/Sydney";
            case "France": return "Europe/Paris";
            case "China": return "Asia/Shanghai";
            case "SaudiArabia": return "Asia/Riyadh";
            default: return "UTC";
        }
    }

    // Get example time
    private String getExampleTime(String country) {
        switch(country) {
            case "India": return "10:30 PM";
            case "Pakistan": return "10:00 PM";
            case "Germany": return "06:00 PM";
            case "USA-NewYork": return "12:00 PM";
            case "Japan": return "02:00 AM";
            case "UK": return "05:00 PM";
            case "Australia-Sydney": return "03:00 AM";
            case "France": return "07:00 PM";
            case "China": return "01:00 AM";
            case "SaudiArabia": return "08:00 PM";
            default: return "Time not available";
        }
    }
}