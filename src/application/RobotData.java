package /*src.*/application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * Stores the robot data to be displayed in the table
 *
 * @version 5/10/2019
 * @author Andrew Menkes
 */
public class RobotData
{
    //SimpleIntegerProperty- Value used in a TableColumn to read the value
    private SimpleIntegerProperty teamNumber, matchNumber, cargoInCargoship, cargoInRocket, hatchInCargoship, hatchInRocket, penalties, piecesDropped, startHABLevel, endHABLevel;
    private SimpleDoubleProperty defense;
    private SimpleStringProperty HAB, levelThree, comments;

    public RobotData(int teamNumber, int matchNumber, int cargoInCargoship, int cargoInRocket,
            int hatchInCargoship, int hatchInRocket, int penalties, int piecesDropped, int startHABLevel,
            int endHABLevel, double defense, String HAB, String levelThree, String comments)
    {
        this.teamNumber = new SimpleIntegerProperty(teamNumber);
        this.matchNumber = new SimpleIntegerProperty(matchNumber);
        this.cargoInCargoship = new SimpleIntegerProperty(cargoInCargoship);
        this.cargoInRocket = new SimpleIntegerProperty(cargoInRocket);
        this.hatchInCargoship = new SimpleIntegerProperty(hatchInCargoship);
        this.hatchInRocket = new SimpleIntegerProperty(hatchInRocket);
        this.penalties = new SimpleIntegerProperty(penalties);
        this.piecesDropped = new SimpleIntegerProperty(piecesDropped);
        this.startHABLevel = new SimpleIntegerProperty(startHABLevel);
        this.endHABLevel = new SimpleIntegerProperty(endHABLevel);
        this.defense = new SimpleDoubleProperty(defense);
        this.HAB = new SimpleStringProperty(HAB);
        this.levelThree = new SimpleStringProperty(levelThree);
        this.comments = new SimpleStringProperty(comments);
    }

    public int getTeamNumber()
    {
        return teamNumber.get();
    }

    public int getMatchNumber()
    {
        return matchNumber.get();
    }

    public int getCargoInCargoship()
    {
        return cargoInCargoship.get();
    }

    public int getCargoInRocket()
    {
        return cargoInRocket.get();
    }

    public int getHatchInCargoship()
    {
        return hatchInCargoship.get();
    }

    public int getHatchInRocket()
    {
        return hatchInRocket.get();
    }

    public int getPenalties()
    {
        return penalties.get();
    }

    public int getPiecesDropped()
    {
        return piecesDropped.get();
    }

    public int getStartHABLevel()
    {
        return startHABLevel.get();
    }

    public int getEndHABLevel()
    {
        return endHABLevel.get();
    }

    public double getDefense()
    {
        return defense.get();
    }

    public String getHAB()
    {
        return HAB.get();
    }

    public String getLevelThree()
    {
        return levelThree.get();
    }

    public String getComments()
    {
        return comments.get();
    }

    public String getAllData()
    {
        return getTeamNumber() + "," + getMatchNumber() + "," + getCargoInCargoship() + "," + getCargoInRocket() + "," + getHatchInCargoship() + "," + getHatchInRocket() + "," +
               getPenalties() + "," + getPiecesDropped() + "," + getStartHABLevel() + "," + getEndHABLevel() + "," + getDefense() + "," + getHAB() + "," + getLevelThree() + "," + getComments() + ",";
    }
}
