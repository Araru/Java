package uk.ac.cardiffmet.st20072041.Timeline;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.color.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.security.PermissionCollection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import java.awt.TextArea;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * @author adam araru
 * view class to set up the GUI
 */
public class TLWindow {

	/**
	 * create frame variable
	 */
	private JFrame frame;
	private JPanel pnlTimeline;
	private JPanel pnlAddEvent;
	public static JTextField txtEventYear;
	public static JTextField txtEventTitle;
	public static JTextArea txtEventDescription;
	public static JTextArea txtInvolvment;
	public static TextArea txtArea;
	public static JComboBox cmbFigure;
	/**
	 * create public arraylist for events 
	 */
	public static ArrayList<EventWithFigure> eventList = new ArrayList<EventWithFigure>();
	/**
	 * create arraylist for figures
	 */
	private static ArrayList<HistoricalFigure> figureList = new ArrayList<HistoricalFigure>();
	/**
	 * declare variable with type int and value 0
	 */
	public static int currentEvent = 0;
	private JButton btnDoneAdding;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TLWindow window = new TLWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});
	}

	/**
	 * Create the application.
	 */
	public TLWindow() {
		methods.createFigure();
		initialize();
		methods.setEventInTextArea(eventList.get(currentEvent));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//run method to load json file
		methods.loadJson();
		frame = new JFrame();
		frame.setBounds(100, 100, 810, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		//create jpanel
		pnlTimeline = new JPanel();
		pnlTimeline.setBackground(new Color(51, 204, 0));
		frame.getContentPane().add(pnlTimeline, "name_452855020230537");
		pnlTimeline.setLayout(null);
		pnlTimeline.setVisible(true);
		
		pnlAddEvent = new JPanel();
		pnlAddEvent.setBackground(new Color(51, 204, 0));
		frame.getContentPane().add(pnlAddEvent, "name_452860503633825");
		pnlAddEvent.setLayout(null);
		//this panel is not visible at first
		pnlAddEvent.setVisible(false);
		
		//button display add event form and hide pnlTimeline
		JButton btnAE = new JButton("Add Event");
		btnAE.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlAddEvent.setVisible(true);
				pnlTimeline.setVisible(false);
			}
		});
		btnAE.setBounds(337, 289, 123, 23);
		pnlTimeline.add(btnAE);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				methods.deleteEvent();//when button is clicked delete event method called
			}
		});
		btnDeleteEvent.setBounds(370, 67, 134, 23);
		pnlTimeline.add(btnDeleteEvent);
		
		txtArea = new TextArea();
		txtArea.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtArea.setBounds(182, 67, 182, 200);
		pnlTimeline.add(txtArea);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			methods.nextEvent();//when button clicked nextEvent method called
				}});
		btnNext.setBounds(169, 289, 89, 23);
		pnlTimeline.add(btnNext);
		
		cmbFigure = new JComboBox();
		cmbFigure.setBounds(51, 195, 86, 20);
		for (HistoricalFigure person : getFigureList()) {
			cmbFigure.addItem(person.getName());//add the persons names to the coboBox
		}
		pnlAddEvent.add(cmbFigure);
		
		JButton btnPrev = new JButton("Previouse");
		btnPrev.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPrev.setForeground(new Color(0, 0, 0));
		btnPrev.setBackground(new Color(204, 204, 204));
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				methods.perviouseEvent();//Previous method called
			}
		});
		btnPrev.setBounds(37, 289, 105, 23);
		pnlTimeline.add(btnPrev);
		
		JLabel lbl2Year = new JLabel("Year");
		lbl2Year.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2Year.setForeground(new Color(51, 0, 51));
		lbl2Year.setBounds(53, 71, 59, 14);
		pnlTimeline.add(lbl2Year);
		
		JLabel lbl2Title = new JLabel("Title");
		lbl2Title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2Title.setBounds(53, 106, 59, 14);
		pnlTimeline.add(lbl2Title);
		
		JLabel lbl2Description = new JLabel("Description");
		lbl2Description.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2Description.setBounds(53, 141, 89, 14);
		pnlTimeline.add(lbl2Description);
		
		JLabel lbl2HistoryFigure = new JLabel("Historical Figure");
		lbl2HistoryFigure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2HistoryFigure.setBounds(53, 180, 123, 14);
		pnlTimeline.add(lbl2HistoryFigure);
		
		JLabel lbl2Involvment = new JLabel("Involvment");
		lbl2Involvment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl2Involvment.setBounds(53, 219, 89, 14);
		pnlTimeline.add(lbl2Involvment);
		
		JLabel lblWorldWar = new JLabel("World War 1 Timeline");
		lblWorldWar.setForeground(new Color(204, 0, 0));
		lblWorldWar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWorldWar.setBounds(156, 11, 249, 31);
		pnlTimeline.add(lblWorldWar);
		
		btnDoneAdding = new JButton ("Done");
		btnDoneAdding.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDoneAdding.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			methods.addEvent();//add event method called
		}
		});
		btnDoneAdding.setBounds(416, 202, 89, 23);
		pnlAddEvent.add(btnDoneAdding);
		
		txtEventYear = new JTextField();
		txtEventYear.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				//validation for only numbers allowed
				//other keys on keyboard disabled
              char caracter = e.getKeyChar();
              if (((caracter < '0') || (caracter > '9'))
                      && (caracter != '\b')) {
                  e.consume();}}});
		txtEventYear.setColumns(10);
		txtEventYear.setBounds(51, 25, 86, 20);
		pnlAddEvent.add(txtEventYear);
		
		txtEventTitle = new JTextField();
		txtEventTitle.setColumns(10);
		txtEventTitle.setBounds(160, 25, 229, 20);
		pnlAddEvent.add(txtEventTitle);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				methods.saveToJson();}});//call method to save to json
		btnSave.setBounds(527, 202, 89, 23);
		pnlAddEvent.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlTimeline.setVisible(true);
				pnlAddEvent.setVisible(false);}});
		btnBack.setBounds(639, 202, 89, 23);
		pnlAddEvent.add(btnBack);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYear.setBounds(51, 11, 54, 14);
		pnlAddEvent.add(lblYear);
		
		JLabel lblTitle = new JLabel("Event Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(160, 11, 89, 14);
		pnlAddEvent.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description of Event");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(51, 68, 161, 14);
		pnlAddEvent.add(lblDescription);
		
		JLabel lblHistoricalFigure = new JLabel("Historical Figure");
		lblHistoricalFigure.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHistoricalFigure.setBounds(51, 170, 124, 14);
		pnlAddEvent.add(lblHistoricalFigure);
		
		JLabel lblInvolvment = new JLabel("Involvement of Historical Figure");
		lblInvolvment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInvolvment.setBounds(180, 170, 242, 14);
		pnlAddEvent.add(lblInvolvment);
		
		JLabel lblOnceReadyPlease = new JLabel("Once ready please click done then save. \r\n");
		lblOnceReadyPlease.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOnceReadyPlease.setForeground(Color.RED);
		lblOnceReadyPlease.setBounds(416, 168, 263, 23);
		pnlAddEvent.add(lblOnceReadyPlease);
		
		txtEventDescription = new JTextArea();
		txtEventDescription.setBounds(51, 84, 397, 79);
		pnlAddEvent.add(txtEventDescription);
		
		txtInvolvment = new JTextArea();
		txtInvolvment.setBounds(180, 193, 226, 119);
		pnlAddEvent.add(txtInvolvment);
		
	}

	public static ArrayList<HistoricalFigure> getFigureList() {
		return figureList;
	}

	public static void setFigureList(ArrayList<HistoricalFigure> figureList) {
		TLWindow.figureList = figureList;
	}
}
