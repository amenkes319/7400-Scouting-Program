package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		//primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        MainMenuController ctrlMainMenu = new MainMenuController();
        ctrlMainMenu.showStage();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
