package application;

import java.io.*;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class DisplayController
{
	@FXML Button btnBack;
	@FXML Button btnDelete;
	@FXML private TableView<RobotData> tableView;
	@FXML private TableColumn<RobotData, Integer> teamNumberColumn;
	@FXML private TableColumn<RobotData, Integer> matchNumberColumn;
	@FXML private TableColumn<RobotData, Integer> cargoInCargoshipColumn;
	@FXML private TableColumn<RobotData, Integer> cargoInRocketColumn;
	@FXML private TableColumn<RobotData, Integer> hatchInCargoshipColumn;
	@FXML private TableColumn<RobotData, Integer> hatchInRocketColumn;
	@FXML private TableColumn<RobotData, Integer> penaltiesColumn;
	@FXML private TableColumn<RobotData, Integer> piecesDroppedColumn;
	@FXML private TableColumn<RobotData, Integer> startHABLevelColumn;
	@FXML private TableColumn<RobotData, Integer> endHABLevelColumn;
	@FXML private TableColumn<RobotData, Double> defenseColumn;
	@FXML private TableColumn<RobotData, String> bHABColumn;
	@FXML private TableColumn<RobotData, String> bLevelThreeColumn;
	@FXML private TableColumn<RobotData, String> commentsColumn;

	Stage stgDisplay;

	public DisplayController(Stage stage)
	{

		stgDisplay = stage;
	}

	/*
	 * Method called automatically by Application class
	 * Initializes button actions and cell values
	 */
	public void initialize()
	{
		btnBack.setOnAction(e -> loadBack());
		btnDelete.setOnAction(e -> loadDelete());

		teamNumberColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("teamNumber"));
		matchNumberColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("matchNumber"));
		cargoInCargoshipColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("cargoInCargoship"));
		cargoInRocketColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("cargoInRocket"));
		hatchInCargoshipColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("hatchInCargoship"));
		hatchInRocketColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("hatchInRocket"));
		penaltiesColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("penalties"));
		piecesDroppedColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("piecesDropped"));
		startHABLevelColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("startHABLevel"));
		endHABLevelColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Integer>("endHABLevel"));
		defenseColumn.setCellValueFactory(new PropertyValueFactory<RobotData, Double>("defense"));
		bHABColumn.setCellValueFactory(new PropertyValueFactory<RobotData, String>("HAB"));
		bLevelThreeColumn.setCellValueFactory(new PropertyValueFactory<RobotData, String>("levelThree"));
		commentsColumn.setCellValueFactory(new PropertyValueFactory<RobotData, String>("comments"));

		tableView.setItems(getRobotData());
	}

	public void show()
	{
		stgDisplay.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));

		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Display.fxml"));

			loader.setController(this);
			stgDisplay.setScene(new Scene(loader.load()));
			stgDisplay.centerOnScreen();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void loadBack()
	{
		InputController ctrlInput = new InputController(stgDisplay);
		ctrlInput.show();
	}

	public void loadDelete()
	{
		String filepath = "src\\application\\data.csv";
		String selectedTeam = tableView.getSelectionModel().getSelectedItem().getAllData();
		removeTeam(filepath, selectedTeam);

		tableView.setItems(getRobotData());
	}

	private void removeTeam(String filepath, String selectedTeam)
	{
		String tempFile = "src\\application\\temp.txt";

		File oldFile = new File(filepath);
		File newFile = new File(tempFile);

		boolean b_deleted = false;

		try
		{
			Scanner scanner = new Scanner(oldFile);

			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();

				String[] data = line.split(",");

				if(!line.equals(selectedTeam) || b_deleted)
				{
					for(String d : data)
						pw.print(d + ",");
					pw.println();
				}
				else
					b_deleted = true;
			}
			scanner.close();
			pw.flush();
			pw.close();
			oldFile.delete();

			File temp = new File(filepath);
			newFile.renameTo(temp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public ObservableList<RobotData> getRobotData()
	{
		int data_TeamNumber = 0;
		int data_MatchNumber = 0;
		int data_CargoInCargoship = 0;
		int data_CargoInRocket = 0;
		int data_HatchInCargoship = 0;
		int data_HatchInRocket = 0;
		int data_Penalties = 0;
		int data_PiecesDropped = 0;
		int data_StartHABLevel = 0;
		int data_EndHABLevel = 0;
		double data_Defense = 0.0;
		String data_HAB = "";
		String data_LevelThree = "";
		String data_Comments = "";

		ObservableList<RobotData> robotDataList = FXCollections.observableArrayList();

		try
		{
			Scanner scanner = new Scanner(new File("src\\application\\data.csv"));

			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				String[] data = line.split(",");

				data_TeamNumber = Integer.valueOf(data[0]);
				data_MatchNumber = Integer.valueOf(data[1]);
				data_CargoInCargoship = Integer.valueOf(data[2]);
				data_CargoInRocket = Integer.valueOf(data[3]);
				data_HatchInCargoship = Integer.valueOf(data[4]);
				data_HatchInRocket = Integer.valueOf(data[5]);
				data_Penalties = Integer.valueOf(data[6]);
				data_PiecesDropped = Integer.valueOf(data[7]);
				data_StartHABLevel = Integer.valueOf(data[8]);
				data_EndHABLevel = Integer.valueOf(data[9]);
				data_Defense = Double.valueOf(data[10]);
				data_HAB = data[11];
				data_LevelThree = data[12];
				data_Comments = data[13];

				robotDataList.add(new RobotData(data_TeamNumber, data_MatchNumber, data_CargoInCargoship, data_CargoInRocket, data_HatchInCargoship, data_HatchInRocket,
						data_Penalties, data_PiecesDropped, data_StartHABLevel, data_EndHABLevel, data_Defense, data_HAB, data_LevelThree, data_Comments));
			}

			scanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return robotDataList;
	}
}