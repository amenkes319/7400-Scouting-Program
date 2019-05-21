package /*src.*/application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 * Starts the JavaFX application and creates the primary stage
 *
 * @version 5/10/2019
 * @author Andrew Menkes
 */
public class Main extends Application
{
    /*
     * JavaFX Application method used to start the application
     *
     * @param primaryStage A stage is the window in which the scene will take place
     */
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.show();
        primaryStage.setTitle("7400 Scouting");
	    primaryStage.setResizable(false);
	    primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png"))); //Set icon
        InputController ctrlInput = new InputController(primaryStage);
        ctrlInput.show();
    }

    public static void main(String[] args)
    {
        launch(args); //JavaFX static method used to call the start() method
    }
}