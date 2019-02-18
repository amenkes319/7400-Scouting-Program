package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenuController
{
	Stage stgMainMenu;

	@FXML TextField txtFldTeamNumber, txtFldMatchNumber;
	@FXML Button btnEnterData, btnDisplayData;

	public MainMenuController()
	{
		stgMainMenu = new Stage();
		//stgMainMenu.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));

		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main Menu.fxml"));

			loader.setController(this);
			stgMainMenu.setScene(new Scene(loader.load()));
			stgMainMenu.setTitle("Main Menu");
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
		btnEnterData.setOnAction(e -> loadInput());
		btnDisplayData.setOnAction(e -> loadDisplay());
	}

	public void showStage()
	{
		stgMainMenu.show();
	}

	public void loadInput()
	{
		InputController ctrlInput = new InputController(Integer.valueOf(txtFldTeamNumber.getText()), Integer.valueOf(txtFldMatchNumber.getText()));
		ctrlInput.showStage();
		stgMainMenu.close();
	}

	public void loadDisplay()
	{
		DisplayController ctrlDisplay = new DisplayController();
		ctrlDisplay.showStage();
		stgMainMenu.close();
	}
}
