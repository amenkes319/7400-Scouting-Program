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

	public DisplayController()
	{

		stgDisplay = new Stage();
		stgDisplay.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));

		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Display.fxml"));

			loader.setController(this);
			stgDisplay.setScene(new Scene(loader.load()));
			stgDisplay.setTitle("7400 Scouting Program");
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

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

		System.out.println();
	}

	public void showStage()
	{
		stgDisplay.show();
	}

	public void loadBack()
	{
		InputController ctrlInput = new InputController();
		ctrlInput.showStage();
		stgDisplay.close();
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
		String teamNumber, matchNumber, cargoInCargoship, cargoInRocket, hatchInCargoship, hatchInRocket,
			   penalties, piecesDropped, startHABLevel, endHABLevel, defense, bHAB, bLevelThree, comments;

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

				teamNumber = data[0];
				matchNumber = data[1];
				cargoInCargoship = data[2];
				cargoInRocket = data[3];
				hatchInCargoship = data[4];
				hatchInRocket = data[5];
				penalties = data[6];
				piecesDropped = data[7];
				startHABLevel = data[8];
				endHABLevel = data[9];
				defense = data[10];
				bHAB = Boolean.valueOf(data[11]) ? "Yes" : "No";
				bLevelThree = Boolean.valueOf(data[12]) ? "Yes" : "No";
				comments = data[13];

				if(!line.equals(selectedTeam))
				{
					pw.println(teamNumber + "," + matchNumber + "," + cargoInCargoship + "," + cargoInRocket + "," + hatchInCargoship + "," + hatchInRocket + "," +
					penalties + "," + piecesDropped + "," + startHABLevel + "," + endHABLevel + "," + defense + "," + bHAB + "," + bLevelThree + "," + comments + ",");
					System.out.println(line + "   does not equal    " + selectedTeam);
				}
				else if(b_deleted)
				{
					pw.println(teamNumber + "," + matchNumber + "," + cargoInCargoship + "," + cargoInRocket + "," + hatchInCargoship + "," + hatchInRocket + "," +
					penalties + "," + piecesDropped + "," + startHABLevel + "," + endHABLevel + "," + defense + "," + bHAB + "," + bLevelThree + "," + comments + ",");
					System.out.println(line + "   equals    " + selectedTeam + "   but is already deleted");
				}
				else
				{
					b_deleted = true;
					System.out.println(line + "   equals    " + selectedTeam + "   and has not been deleted");
				}
			}
			scanner.close();
			pw.flush();
			pw.close();
			oldFile.delete();

			File dump = new File(filepath);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public ObservableList<RobotData> getRobotData()
	{
		int dataTeamNumber = 0;
		int dataMatchNumber = 0;
		int dataCargoInCargoship = 0;
		int dataCargoInRocket = 0;
		int dataHatchInCargoship = 0;
		int dataHatchInRocket = 0;
		int dataPenalties = 0;
		int dataPiecesDropped = 0;
		int dataStartHABLevel = 0;
		int dataEndHABLevel = 0;
		double dataDefense = 0.0;
		boolean b_dataHAB = false;
		boolean b_dataLevelThree = false;
		String dataComments = "";

		ObservableList<RobotData> robotDataList = FXCollections.observableArrayList();

		try
		{
			Scanner scanner = new Scanner(new File("src\\application\\data.csv"));

			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				String[] data = line.split(",");

				dataTeamNumber = Integer.valueOf(data[0]);
				dataMatchNumber = Integer.valueOf(data[1]);
				dataCargoInCargoship = Integer.valueOf(data[2]);
				dataCargoInRocket = Integer.valueOf(data[3]);
				dataHatchInCargoship = Integer.valueOf(data[4]);
				dataHatchInRocket = Integer.valueOf(data[5]);
				dataPenalties = Integer.valueOf(data[6]);
				dataPiecesDropped = Integer.valueOf(data[7]);
				dataStartHABLevel = Integer.valueOf(data[8]);
				dataEndHABLevel = Integer.valueOf(data[9]);
				dataDefense = Double.valueOf(data[10]);
				b_dataHAB = Boolean.valueOf(data[11]);
				b_dataLevelThree = Boolean.valueOf(data[12]);
				dataComments = data[13];

				robotDataList.add(new RobotData(dataTeamNumber, dataMatchNumber, dataCargoInCargoship, dataCargoInRocket, dataHatchInCargoship, dataHatchInRocket,
						dataPenalties, dataPiecesDropped, dataStartHABLevel, dataEndHABLevel, dataDefense, b_dataHAB, b_dataLevelThree, dataComments));
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
