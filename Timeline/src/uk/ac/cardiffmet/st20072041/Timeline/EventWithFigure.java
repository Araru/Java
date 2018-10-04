package uk.ac.cardiffmet.st20072041.Timeline;

import java.util.ArrayList;


/**
 * @author Adam Araru
 * this class extends event class (it is an instance of event class)
 *
 */
public class EventWithFigure extends Event{
	
	
	/**
	 * 
	 * create an instance of historical figure class which is put inside this class as a variable
	 * this variable then holds the historical figure
	 */
	private static HistoricalFigure figure;
	/**
	 * variable with type string for the involvement of the figure
	 */
	private String involvment;

	/**
	 * constructor
	 * event with figure inherits attributes of superclass
	 * @param initFigure
	 * @param initInvolvment
	 * @param initYear
	 * @param initEventTitle
	 * @param initEventDescription
	 */
	public EventWithFigure(
			HistoricalFigure initFigure,
			String initInvolvment,
			String initYear,
			String initEventTitle,
			String initEventDescription)
	{
		super (initYear,initEventTitle,initEventDescription);
		
		setInvolvment(initInvolvment);
		setFigure(initFigure);
	}
	
	/**
	 * setter for figure
	 * @param person
	 */
	public void setFigure(HistoricalFigure person) {
		this.figure = person;
	}
	
	/**
	 * getter for figure
	 * @return figure
	 */
	public static HistoricalFigure getFigure() {
		return figure;
	}
	
	/**
	 * setter for involvement of figure
	 * @param newValue
	 */
	public void setInvolvment(String newValue) {
		involvment = newValue;
	}
	
	/**
	 * @return involvement
	 */
	public String getInvolvment() {
		return involvment;
	}
	
	/* 
	 * converts objects to string
	 */
	public String toString() {
		  return getClass().getSimpleName() +  getFigure() + getInvolvment() + getEventYear() + getEventTitle() + getEventDescription();
		}

}
