package uk.ac.cardiffmet.st20072041.Timeline;

/**
 * @author adam araru
 * event class for basic event object
 */
public class Event {
	
	/**
	 * variable for year
	 */
	private String eventYear;
	/**
	 * variable for title
	 */
	private String eventTitle;
	/**
	 * variable for description
	 */
	private String eventDescription;
	
	
	/**
	 * @param initYear
	 * @param initEventTitle
	 * @param initEventDescription
	 */
	public Event (
		String initYear,
		String initEventTitle,
		String initEventDescription)
	{
		eventYear=initYear;
		eventTitle=initEventTitle;
		eventDescription=initEventDescription;
	}
	
	
/**
 * getter for event description
 * @return event description
 */
public String getEventDescription() {
		
		return eventDescription;
	}

/**
 * setter for event description
 * @param newValue
 */
public void setEventDescription(String newValue) {
	
	{
		eventDescription = newValue;
	}
}

public String getEventTitle() {
	
		return eventTitle;
}
public void setEventTitle(String newValue) {

		{
		eventTitle = newValue;
		}
}

public String getEventYear() {
	
		return eventYear;
}

public void setEventYear(String newValue) {

		{
		eventYear = newValue;
		}
}

}
