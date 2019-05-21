package /*src.*/application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBox
{
    public static void displaySaveSuccess()
    {
        Alert alert = new Alert(AlertType.INFORMATION); //Alert box class (added in Java 8) makes a pop up window object
        alert.setTitle("7400 Scouting Program");
        alert.setHeaderText("Save Successful");
        alert.setContentText("File Successfully Saved!");

        //Shows the alert window and does not allow the user to interact with the other windows until the alert is closed
        alert.showAndWait();
    }

    public static void displayTeamNumberError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("7400 Scouting Program");
        alert.setHeaderText("ERROR!");
        alert.setContentText("Please enter a valid team number!");

        alert.showAndWait();
    }

    public static void displayMatchNumberError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("7400 Scouting Program");
        alert.setHeaderText("ERROR!");
        alert.setContentText("Please enter a valid match number!");

        alert.showAndWait();
    }
}
