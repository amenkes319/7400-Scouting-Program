package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        InputController ctrlInput = new InputController();
        ctrlInput.showStage();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
