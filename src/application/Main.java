package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.show();
		primaryStage.setTitle("7400 Scouting");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        InputController ctrlInput = new InputController(primaryStage);
        ctrlInput.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}