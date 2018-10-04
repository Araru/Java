package uk.ac.cardiffmet.st20072041.Timeline;

/**
 * @author adam araru
 * this is historical figure class
 * used to create historical figures
 */
public class HistoricalFigure {
	
	
	/**
	 * public variable for name of hostorical figure
	 */
	private String name;

	
	/**
	 * constructor
	 * @param name
	 */
	public HistoricalFigure(String name) {
		this.name = name;
	}

	
	/**
	 * getter for name of historical figure
	 * @return name
	 */
	public String getName() {
		return name;
	}

	//setter is private because user does not create a figure. 
	//list of figures is already created and the user only has to pick from the combo box.
	private void setName(String name) {
		this.name = name;
	}

	
}
