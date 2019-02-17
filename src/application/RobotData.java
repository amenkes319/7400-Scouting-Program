package application;

import javafx.beans.property.SimpleStringProperty;

public class RobotData
{
	private SimpleStringProperty teamNumber, matchNumber, cargoInCargoship, cargoInRocket, hatchInCargoship, hatchInRocket, penalties, piecesDropped, startHABLevel, endHABLevel, defense, b_HAB, b_levelThree, comments;

	public RobotData()
	{
	}
	
	public RobotData(int teamNumber, int matchNumber, int cargoInCargoship, int cargoInRocket,
			int hatchInCargoship, int hatchInRocket, int penalties,	int piecesDropped,
			int startHABLevel, int endHABLevel, double defense, boolean b_HAB, boolean b_levelThree,
			String comments)
	{
		this.teamNumber = new SimpleStringProperty(String.valueOf(teamNumber));
		this.matchNumber = new SimpleStringProperty(String.valueOf(matchNumber));
		this.cargoInCargoship = new SimpleStringProperty(String.valueOf(cargoInCargoship));
		this.cargoInRocket = new SimpleStringProperty(String.valueOf(cargoInRocket));
		this.hatchInCargoship = new SimpleStringProperty(String.valueOf(hatchInCargoship));
		this.hatchInRocket = new SimpleStringProperty(String.valueOf(hatchInRocket));
		this.penalties = new SimpleStringProperty(String.valueOf(penalties));
		this.piecesDropped = new SimpleStringProperty(String.valueOf(piecesDropped));
		this.startHABLevel = new SimpleStringProperty(String.valueOf(startHABLevel));
		this.endHABLevel = new SimpleStringProperty(String.valueOf(endHABLevel));
		this.defense = new SimpleStringProperty(String.valueOf(defense));
		this.b_HAB = new SimpleStringProperty(String.valueOf(b_HAB));
		this.b_levelThree = new SimpleStringProperty(String.valueOf(b_levelThree));
		this.comments = new SimpleStringProperty(comments);
	}

	public String getTeamNumber()
	{
		return teamNumber.get();
	}

	public void setTeamNumber(SimpleStringProperty teamNumber)
	{
		this.teamNumber = teamNumber;
	}

	public String getMatchNumber()
	{
		return matchNumber.get();
	}

	public void setMatchNumber(SimpleStringProperty matchNumber)
	{
		this.matchNumber = matchNumber;
	}

	public String getCargoInCargoship()
	{
		return cargoInCargoship.get();
	}

	public void setCargoInCargoship(SimpleStringProperty cargoInCargoship)
	{
		this.cargoInCargoship = cargoInCargoship;
	}

	public String getCargoInRocket()
	{
		return cargoInRocket.get();
	}

	public void setCargoInRocket(SimpleStringProperty cargoInRocket)
	{
		this.cargoInRocket = cargoInRocket;
	}

	public String getHatchInCargoship()
	{
		return hatchInCargoship.get();
	}

	public void setHatchInCargoship(SimpleStringProperty hatchInCargoship)
	{
		this.hatchInCargoship = hatchInCargoship;
	}

	public String getHatchInRocket()
	{
		return hatchInRocket.get();
	}

	public void setHatchInRocket(SimpleStringProperty hatchInRocket)
	{
		this.hatchInRocket = hatchInRocket;
	}

	public String getPenalties()
	{
		return penalties.get();
	}

	public void setPenalties(SimpleStringProperty penalties)
	{
		this.penalties = penalties;
	}

	public String getPiecesDropped()
	{
		return piecesDropped.get();
	}

	public void setPiecesDropped(SimpleStringProperty piecesDropped)
	{
		this.piecesDropped = piecesDropped;
	}

	public String getStartHABLevel()
	{
		return startHABLevel.get();
	}

	public void setStartHABLevel(SimpleStringProperty startHABLevel)
	{
		this.startHABLevel = startHABLevel;
	}

	public String getEndHABLevel()
	{
		return endHABLevel.get();
	}

	public void setEndHABLevel(SimpleStringProperty endHABLevel)
	{
		this.endHABLevel = endHABLevel;
	}

	public String getDefense()
	{
		return defense.get();
	}

	public void setDefense(SimpleStringProperty defense)
	{
		this.defense = defense;
	}

	public String getB_HAB()
	{
		return b_HAB.get();
	}

	public void setB_HAB(SimpleStringProperty b_HAB)
	{
		this.b_HAB = b_HAB;
	}

	public String getB_levelThree()
	{
		return b_levelThree.get();
	}

	public void setB_levelThree(SimpleStringProperty b_levelThree)
	{
		this.b_levelThree = b_levelThree;
	}

	public String getComments()
	{
		return comments.get();
	}

	public void setComments(SimpleStringProperty comments)
	{
		this.comments = comments;
	}


}
