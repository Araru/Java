package uk.ac.cardiffmet.st20072041.Timeline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

/**
 * @author adam araru
 * this is the controller with my methods
 */
public class methods {
	
	/**
	 * create historical figure and add to arraylist called figureList
	 */
	public static void createFigure() {
		TLWindow.getFigureList().add(new HistoricalFigure("N/A"));
		TLWindow.getFigureList().add(new HistoricalFigure("Hitler"));//add person
		TLWindow.getFigureList().add(new HistoricalFigure("Archduke Frank"));
	}
	
	/**
	 * loads the events into the txtArea
	 * @param event
	 */
	public static void setEventInTextArea(EventWithFigure event) {
	TLWindow.txtArea.setText(event.getEventYear()+"\n" + "\n" +
	event.getEventTitle()+"\n" + "\n" + event.getEventDescription()+"\n"+"\n"+
	event.getFigure().getName()+"\n" +"\n"+ event.getInvolvment());}
	
	/**
	 * method scrolls through array to get next event and display in txtArea
	 */
	public static void perviouseEvent(){
		TLWindow.currentEvent--;
		if(TLWindow.currentEvent <0 ) {
			TLWindow.currentEvent = TLWindow.eventList.size() - 1;
		}setEventInTextArea(TLWindow.eventList.get(TLWindow.currentEvent));}
	
	/**
	 * method scrolls through array to get next event and display in txtArea
	 */
	public static void nextEvent() {
		TLWindow.currentEvent++;
		if(TLWindow.eventList.size() == TLWindow.currentEvent) {
			TLWindow.currentEvent =0 ;
		}
		setEventInTextArea(TLWindow.eventList.get(TLWindow.currentEvent));
	}
	
	/**
	 * save method to save events into json file
	 */
	public static void saveToJson() {
		String result = "";//empty string
		JSONArray array = new  JSONArray();//json array
		
		for (EventWithFigure e : TLWindow.eventList) {
			JSONObject eventObject = new JSONObject();//create json object
			eventObject.put("description", e.getEventDescription());//create key value pair
			JSONObject personJSON = new JSONObject();//create json object for person/ figure
			personJSON.put("name", e.getFigure().getName());//create key value pair 
			eventObject.put("figure", personJSON);//create key value pair 
												 //personJson is the value which is taken from jsonObject
			eventObject.put("involvment", e.getInvolvment());
			eventObject.put("date", e.getEventYear());
			eventObject.put("title", e.getEventTitle());
			array.add(eventObject);//add the objects to the json array
		}
		result = array.toJSONString();//convert json array to string and stors it in string result
		//System.out.println(result);
		
		//try catch block
		try {
			File file = new File("info.json");//create new json file
			FileWriter writer = new FileWriter(file);//create file writer
			writer.write(result);//write the string result to info.json file
			writer.flush();
			writer.close();//closes stream
		} catch (Exception e2) {
			//cath exception and display error message
			JOptionPane.showMessageDialog(null, "Something went wrong with saving the events.");;
		}
	}
	
	/**
	 * load the json file method
	 */
	public static void loadJson() {
		JSONParser parser = new JSONParser();//create new json parser
		JSONArray array = null;
		ArrayList<EventWithFigure> inputArray = new ArrayList<EventWithFigure>();//create new arraylist of events
		try {
			array = (JSONArray) parser.parse(new FileReader("info.json"));//file reader reads json array
		} catch (IOException | ParseException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong uploading the file.");
		}
		for (int i = 0; i < array.size(); i++) {
			JSONObject eventObj = (JSONObject) array.get(i);//each object in array becomes json object
			JSONObject personObj =  (JSONObject)eventObj.get("figure");
			//create instance of event with figure for each json object
			EventWithFigure event = new EventWithFigure(new HistoricalFigure( (String)  personObj.get("name")), (String) eventObj.get("involvment"),(String) eventObj.get("date"), (String) eventObj.get("title"), (String) eventObj.get("description"));
			inputArray.add(event);//add objects to array
		}
		TLWindow.eventList = inputArray;//copy inputarray to eventlist	
	}
	
	/**
	 * add event method
	 */
	public static void addEvent() {
		boolean isvalid = true;//set boolean variable to true
		String inv = TLWindow.txtInvolvment.getText();//set string variable value to what is in txtInvolvment
		String date = TLWindow.txtEventYear.getText();
		String title = TLWindow.txtEventTitle.getText();
		String description = TLWindow.txtEventDescription.getText();
		//validation to check if fields are empty and display error message
		if(description.trim().isEmpty() | date.trim().isEmpty() | title.trim().isEmpty()&&  isvalid)
			{
			JOptionPane.showMessageDialog(null, "You can not leave description, year or title blank. They are manditory fields");
			isvalid = false;//change boolean to false
		}
		if(isvalid) {//if the fields are valid and have been filled in add to array
			EventWithFigure newEvent = new  EventWithFigure(TLWindow.getFigureList().get(TLWindow.cmbFigure.getSelectedIndex()), inv, date, title, description);
			TLWindow.eventList.add(newEvent);
			saveToJson();
		}
	}
	
	/**
	 * delete current event method
	 */
	public static void deleteEvent() {
		//show message
		JOptionPane.showMessageDialog(null, "You deleted:" + TLWindow.eventList.get(TLWindow.currentEvent).getEventTitle());
		//delete that current event
		TLWindow.eventList.remove(TLWindow.currentEvent);
		TLWindow.currentEvent =0 ;
		//clear text area
		setEventInTextArea(TLWindow.eventList.get(TLWindow.currentEvent));
		saveToJson();
		
	}
}
