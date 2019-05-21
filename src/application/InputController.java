package /*src.*/application;

import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 * Every FXML file needs a controller class
 * Controls Input.fxml
 *
 * @version 5/10/2019
 * @author Andrew Menkes
 */
public class InputController
{
    //Label - A string displayed as basic text
    @FXML private Label lblCargoCargoshipCounter, lblCargoRocketCounter, lblHatchCargoshipCounter, lblHatchRocketCounter, lblPenaltyCounter, lblPiecesDropped;
    @FXML private CheckBox chkBoxLevelThree, chkBoxHAB; //CheckBox- contains a boolean value whether the box is check or not
    @FXML private Slider startLevelSlider, endLevelSlider, defenseSlider; //Slider- contains an int or double value
    @FXML private TextArea txtAreaComments; //TextArea- Allows the user to enter a large String
    @FXML private TextField txtFldTeamNumber, txtFldMatchNumber; //TextField- Allows the user to tenter a short String
    @FXML private Button btnCargoCargoshipAdd;
    @FXML private Button btnCargoRocketAdd;
    @FXML private Button btnHatchCargoshipAdd;
    @FXML private Button btnHatchRocketAdd;
    @FXML private Button btnPenaltyAdd;
    @FXML private Button btnPiecesDroppedAdd;
    @FXML private Button btnCargoCargoshipSubtract;
    @FXML private Button btnCargoRocketSubtract;
    @FXML private Button btnHatchCargoshipSubtract;
    @FXML private Button btnHatchRocketSubtract;
    @FXML private Button btnPenaltySubtract;
    @FXML private Button btnPiecesDroppedSubtract;
    @FXML private Button btnSave;
    @FXML private Button btnDisplayAllData;
    @FXML private Button btnClear;
    @FXML private Button btnRandom;

    private Stage stgInput;

    private int teamNumber, matchNumber, cargoInCargoship, cargoInRocket, hatchInCargoship, hatchInRocket, penalties, piecesDropped;

    public InputController(Stage stage)
    {
        stgInput = stage;
    }

    /*
     * Method called automatically by Application class
     * Initializes button actions
     */
    public void initialize()
    {

        //button.setOnAction sets the action of the specified button when pressed
        //e -> method() is a lambda function which takes the user action, e, and performs the action on the right side of the arrow operator
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
        btnClear.setOnAction(e -> reset());
        btnRandom.setOnAction(e -> generateRandomTeam());
    }

    public void show()
    {
        stgInput.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));

        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Input.fxml")); //Loads the FXML file

            loader.setController(this); //Sets InputController.java as the Input.FXML controller

            //Scene- the contents set inside of the stage
            stgInput.setScene(new Scene(loader.load())); //Sets the scene of the stage to the fxml loaded file
            stgInput.centerOnScreen();
        }
        catch(Exception e)
        {
            System.out.println("File not found");
        }
    }

    public void saveFile()
    {
        try
        {
            teamNumber = Integer.valueOf(txtFldTeamNumber.getText().trim()); //get String text of team number text field
            matchNumber = Integer.valueOf(txtFldMatchNumber.getText().trim()); //get String text of match number text field

            String hab = chkBoxHAB.isSelected() ? "Yes" : "No";
            String levelThree = chkBoxLevelThree.isSelected() ? "Yes" : "No";
            String comments = txtAreaComments.getText().isEmpty() ? "No comments" : txtAreaComments.getText().replaceAll(",", "-").replaceAll("\n", "  ");

            FileWriter fw = new FileWriter(new File("src\\application\\data.csv"), true); //Write to specified file in first parameter, boolean value whether the writer appends the current file

            fw.write(String.valueOf(teamNumber) + "," + String.valueOf(matchNumber) + "," + String.valueOf(cargoInCargoship) + "," + String.valueOf(cargoInRocket) +
                     "," + String.valueOf(hatchInCargoship) + "," + String.valueOf(hatchInRocket) + "," + String.valueOf(penalties) + "," + String.valueOf(piecesDropped) +
                     "," + String.valueOf((int) startLevelSlider.getValue()) + "," + String.valueOf((int) endLevelSlider.getValue()) + "," + String.valueOf(defenseSlider.getValue()) +
                     "," + hab + "," + levelThree + "," + comments + ",\n"); //Write data to file

            fw.close();

            reset();

            AlertBox.displaySaveSuccess();
        }
        catch(NumberFormatException e)
        {
            try
            {
                Integer.valueOf(txtFldTeamNumber.getText().trim());
            }
            catch(NumberFormatException ex)
            {
                AlertBox.displayTeamNumberError();
            }

            try
            {
                Integer.valueOf(txtFldMatchNumber.getText().trim());
            }
            catch(NumberFormatException ex)
            {
                AlertBox.displayMatchNumberError();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Reset all values in input.FXML
     */
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
        chkBoxHAB.setSelected(false);
        chkBoxLevelThree.setSelected(false);
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

    /*
     * Generate and save random team data
     */
    private void generateRandomTeam()
    {
        try
        {
            FileWriter fw = new FileWriter(new File("src\\application\\data.csv"), true);

            fw.write(Math.round((Math.random()*7915 + 1)) + "," + Math.round(Math.random()*115 + 1) + "," + Math.round(Math.random()*20) + "," + Math.round(Math.random()*20) +
                     "," + Math.round(Math.random()*20) + "," + Math.round(Math.random()*20) + "," + Math.round(Math.random()*10) + "," + Math.round(Math.random()*20) +
                     "," + (Math.round(Math.random()*2 + 1)) + "," + Math.round(Math.random()*3) + "," + (double) Math.round(Math.random()*5 * 2)/2 +
                     "," + (Math.round(Math.random()) == 1 ? "Yes" : "No") + "," + (Math.round(Math.random()) == 1 ? "Yes" : "No") + "," + "Random Team" + ",\n");

            fw.close();

            AlertBox.displaySaveSuccess();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadDisplay()
    {
        DisplayController ctrlDisplay = new DisplayController(stgInput);
        ctrlDisplay.show();
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
