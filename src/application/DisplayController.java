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
	@FXML private Button btnBack;
	@FXML private Button btnDelete;
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

	private Stage stgDisplay;

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

		boolean bDeleted = false;

		try
		{
			Scanner scanner = new Scanner(oldFile);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(tempFile, true)));

			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();

				String[] data = line.split(",");

				System.out.println(line);

				if(!line.equals(selectedTeam) || bDeleted)
				{
					for(String d : data)
						pw.print(d + ",");
					pw.println();
				}
				else
					bDeleted = true;
			}
			scanner.close();
			pw.flush();
			pw.close();
			oldFile.delete();

			newFile.renameTo(new File(filepath));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public ObservableList<RobotData> getRobotData()
	{

		ObservableList<RobotData> robotDataList = FXCollections.observableArrayList();

		try
		{
			Scanner scanner = new Scanner(new File("src\\application\\data.csv"));

			while(scanner.hasNextLine())
			{
				String[] data = scanner.nextLine().split(",");

				robotDataList.add(new RobotData(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4]), Integer.valueOf(data[5]),
						Integer.valueOf(data[6]), Integer.valueOf(data[7]), Integer.valueOf(data[8]), Integer.valueOf(data[9]), Double.valueOf(data[10]), data[11], data[12], data[13]));
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
