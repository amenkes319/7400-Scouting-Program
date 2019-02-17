package application;

import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InputController
{
	@FXML Button btnCargoCargoshipAdd, btnCargoRocketAdd, btnHatchCargoshipAdd, btnHatchRocketAdd, btnPenaltyAdd, btnPiecesDroppedAdd;
	@FXML Button btnCargoCargoshipSubtract, btnCargoRocketSubtract, btnHatchCargoshipSubtract, btnHatchRocketSubtract, btnPenaltySubtract, btnPiecesDroppedSubtract;
	@FXML Button btnSave, btnBack;
	@FXML Label lblName, lblTeamNumber, lblMatchNumber;
	@FXML Label lblCargoCargoshipCounter, lblCargoRocketCounter, lblHatchCargoshipCounter, lblHatchRocketCounter, lblPenaltyCounter, lblPiecesDropped;
	@FXML CheckBox chkBoxLevelThree, chkBoxHAB;
	@FXML Slider startLevelSlider, endLevelSlider, defenseSlider;
	@FXML TextArea txtAreaComments;

	Stage stgInput;

	String name;
	int    teamNumber, matchNumber;
	int    cargoInCargoship, cargoInRocket, hatchInCargoship, hatchInRocket, penalties, piecesDropped;

	boolean b_levelThree, b_HAB;

	public InputController(String n, int t, int m)
	{
		name = n;
		teamNumber = t;
		matchNumber = m;

		cargoInCargoship = 0;
		cargoInRocket = 0;
		hatchInCargoship = 0;
		hatchInRocket = 0;
		penalties = 0;

		b_levelThree = false;
		b_HAB = false;

		stgInput = new Stage();
		//stgInput.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Input.fxml"));

			loader.setController(this);
			stgInput.setScene(new Scene(loader.load()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		stgInput.setTitle("Input Data");
	}

	public void initialize()
	{
		btnCargoCargoshipAdd.setOnAction(e ->
		{
			if(cargoInCargoship >= 0)
			{
				cargoInCargoship++;
				updateCargoInCargoship();
			}
		});

		btnCargoCargoshipSubtract.setOnAction(e ->
		{
			if(cargoInCargoship > 0)
			{
				cargoInCargoship--;
				updateCargoInCargoship();
			}
		});

		btnCargoRocketAdd.setOnAction(e ->
		{
			if(cargoInRocket >= 0)
			{
				cargoInRocket++;
				updateCargoInRocket();
			}
		});

		btnCargoRocketSubtract.setOnAction(e ->
		{
			if(cargoInRocket > 0)
			{
				cargoInRocket--;
				updateCargoInRocket();
			}
		});

		btnHatchCargoshipAdd.setOnAction(e ->
		{
			if(hatchInCargoship >= 0)
			{
				hatchInCargoship++;
				updateHatchInCargoship();
			}
		});

		btnHatchCargoshipSubtract.setOnAction(e ->
		{
			if(hatchInCargoship > 0)
			{
				hatchInCargoship--;
				updateHatchInCargoship();
			}
		});

		btnHatchRocketAdd.setOnAction(e ->
		{
			if(hatchInRocket >= 0)
			{
				hatchInRocket++;
				updateHatchInRocket();
			}
		});

		btnHatchRocketSubtract.setOnAction(e ->
		{
			if(hatchInRocket > 0)
			{
				hatchInRocket--;
				updateHatchInRocket();
			}
		});

		btnPenaltyAdd.setOnAction(e ->
		{
			if(penalties >= 0)
			{
				penalties++;
				updatePenalty();
			}
		});

		btnPenaltySubtract.setOnAction(e ->
		{
			if(penalties > 0)
			{
				penalties--;
				updatePenalty();
			}
		});

		btnPiecesDroppedAdd.setOnAction(e ->
		{
			if(piecesDropped >= 0)
			{
				piecesDropped++;
				updatePiecesDropped();
			}
		});

		btnPiecesDroppedSubtract.setOnAction(e ->
		{
			if(piecesDropped > 0)
			{
				piecesDropped--;
				updatePiecesDropped();
			}
		});

		btnSave.setOnAction(e -> saveFile());
		btnBack.setOnAction(e -> loadBack());

		setInputLabels();
	}

	public void showStage()
	{
		stgInput.show();
	}

	public void loadBack()
	{
		MainMenuController ctrlMainMenu = new MainMenuController();
		ctrlMainMenu.showStage();
		stgInput.close();
	}

	public void saveFile()
	{
		b_levelThree = chkBoxLevelThree.isSelected();
		b_HAB = chkBoxHAB.isSelected();

		try
		{
			FileWriter fw = new FileWriter(new File("src\\application\\data.csv"), true);
			fw.write(String.valueOf(teamNumber) + "," + String.valueOf(matchNumber) + "," + String.valueOf(cargoInCargoship) + "," + String.valueOf(cargoInRocket) +
					 "," + String.valueOf(hatchInCargoship) + "," + String.valueOf(hatchInRocket) + "," + String.valueOf(penalties) + "," + String.valueOf(piecesDropped) +
					 "," + String.valueOf((int) startLevelSlider.getValue()) + "," + String.valueOf((int) endLevelSlider.getValue()) + "," + String.valueOf(defenseSlider.getValue()) +
					 "," + String.valueOf(b_HAB) + "," + String.valueOf(b_levelThree) + "," + String.valueOf(txtAreaComments.getText().replaceAll(",", "-")) + "\n");

			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		MainMenuController ctrlMainMenu = new MainMenuController();
		ctrlMainMenu.showStage();
		stgInput.close();
	}

	public void setInputLabels()
	{
		lblName.setText(name);
		lblTeamNumber.setText(String.valueOf(teamNumber));
		lblMatchNumber.setText(String.valueOf(matchNumber));
	}

	public void updateCargoInCargoship()
	{
		lblCargoCargoshipCounter.setText(String.valueOf(cargoInCargoship));
	}

	public void updateCargoInRocket()
	{
		lblCargoRocketCounter.setText(String.valueOf(cargoInRocket));
	}

	public void updateHatchInCargoship()
	{
		lblHatchCargoshipCounter.setText(String.valueOf(hatchInCargoship));
	}

	public void updateHatchInRocket()
	{
		lblHatchRocketCounter.setText(String.valueOf(hatchInRocket));
	}

	public void updatePenalty()
	{
		lblPenaltyCounter.setText(String.valueOf(penalties));
	}


	public void updatePiecesDropped()
	{
		lblPiecesDropped.setText(String.valueOf(piecesDropped));
	}
}
