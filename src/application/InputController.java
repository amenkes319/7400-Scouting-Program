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
	@FXML Button btnSave, btnDisplayAllData;
	@FXML Label lblCargoCargoshipCounter, lblCargoRocketCounter, lblHatchCargoshipCounter, lblHatchRocketCounter, lblPenaltyCounter, lblPiecesDropped;
	@FXML CheckBox chkBoxLevelThree, chkBoxHAB;
	@FXML Slider startLevelSlider, endLevelSlider, defenseSlider;
	@FXML TextArea txtAreaComments;
	@FXML TextField txtFldTeamNumber, txtFldMatchNumber;

	Stage stgInput;

	int teamNumber, matchNumber, cargoInCargoship, cargoInRocket, hatchInCargoship, hatchInRocket, penalties, piecesDropped;
	boolean b_levelThree, b_HAB;

	public InputController()
	{
		stgInput = new Stage();
		stgInput.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));

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

		stgInput.setTitle("7400 Scouting Program");
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
		btnDisplayAllData.setOnAction(e -> loadDisplay());
	}

	public void showStage()
	{
		stgInput.show();
	}

	public void saveFile()
	{
		try
		{
			teamNumber = Integer.valueOf(txtFldTeamNumber.getText().trim());
			matchNumber = Integer.valueOf(txtFldMatchNumber.getText().trim());

			String bHAB = chkBoxHAB.isSelected() ? "Yes" : "No";
			String bLevelThree = chkBoxLevelThree.isSelected() ? "Yes" : "No";
			String comments = txtAreaComments.getText().isEmpty() ? "No comments" : txtAreaComments.getText().replaceAll(",", "-").replaceAll("\n", "--");

			FileWriter fw = new FileWriter(new File("src\\application\\data.csv"), true);

			fw.write(String.valueOf(teamNumber) + "," + String.valueOf(matchNumber) + "," + String.valueOf(cargoInCargoship) + "," + String.valueOf(cargoInRocket) +
					 "," + String.valueOf(hatchInCargoship) + "," + String.valueOf(hatchInRocket) + "," + String.valueOf(penalties) + "," + String.valueOf(piecesDropped) +
					 "," + String.valueOf((int) startLevelSlider.getValue()) + "," + String.valueOf((int) endLevelSlider.getValue()) + "," + String.valueOf(defenseSlider.getValue()) +
					 "," + bHAB + "," + bLevelThree + "," + comments + ",\n");

			fw.close();

			reset();

			AlertBox.displaySaveSuccess();
		}
		catch(Exception e)
		{
			try
			{
				Integer.valueOf(txtFldTeamNumber.getText());
			}
			catch(NumberFormatException nfe)
			{
				AlertBox.displayTeamNumberError();
			}

			try
			{
				Integer.valueOf(txtFldMatchNumber.getText());
			}
			catch(NumberFormatException nfe)
			{
				AlertBox.displayMatchNumberError();
			}
		}
	}

	private void reset()
	{
		txtFldTeamNumber.clear();
		txtFldMatchNumber.clear();
		cargoInCargoship = 0;
		cargoInRocket = 0;
		hatchInCargoship = 0;
		hatchInRocket = 0;
		penalties = 0;
		piecesDropped = 0;
		startLevelSlider.setValue(0);
		endLevelSlider.setValue(0);
		defenseSlider.setValue(0);
		txtAreaComments.clear();

		updateCargoInCargoship();
		updateCargoInRocket();
		updateHatchInCargoship();
		updateHatchInRocket();
		updatePenalty();
		updatePiecesDropped();
	}

	public void loadDisplay()
	{
		DisplayController ctrlDisplay = new DisplayController();
		ctrlDisplay.showStage();
		stgInput.close();
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
