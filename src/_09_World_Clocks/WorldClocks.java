package _09_World_Clocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * You task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
    ClockUtilities clockUtil;
    Timer timer;
    TimeZone timeZone;

    JFrame frame;
    JPanel panel;
    JPanel mainPanel;
    JButton button1;
    JTextArea textArea;
    
    String city;
    String dateStr;
    String timeStr;
    
    HashMap<String, TimeZone> cityTimes = new HashMap<String, TimeZone>();
    
    public WorldClocks() {
        clockUtil = new ClockUtilities();
        
        // The format for the city must be: city, country (all caps)
        city = "Los Angeles, US";
        
        timeZone = clockUtil.getTimeZoneFromCityName(city);
        cityTimes.put(city, timeZone);
      
        System.out.println(dateStr);

        // Sample starter program
        frame = new JFrame();
        panel = new JPanel();
        mainPanel = new JPanel();
        button1 = new JButton("Add City Time");
        textArea = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100, 100);
        frame.add(mainPanel);
        mainPanel.add(panel);
        panel.add(textArea);
        mainPanel.add(button1);
        button1.addActionListener(this);
        textArea.setText(city + "\n" + dateStr);
        
        // This Timer object is set to call the actionPerformed() method every
        // 1000 milliseconds
        timer = new Timer(1000, this);
        timer.start();
        for(String i : cityTimes.keySet()) {
        	Calendar calendar = Calendar.getInstance(cityTimes.get(i));
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
            dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR);
            timeZone = clockUtil.getTimeZoneFromCityName(i);
        }
   	 	

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    	if(arg0.getSource() == button1) {
    		city = JOptionPane.showInputDialog("What city time would you like to see?");
    		
    		timeZone = clockUtil.getTimeZoneFromCityName(city);
    		cityTimes.put(city, timeZone);
    	}
    	
    	mainPanel.removeAll();
    	
        for(String i : cityTimes.keySet()) {
        	JPanel panel1 = new JPanel();
    		JTextArea textArea  = new JTextArea();
    		panel1.add(textArea);
    		mainPanel.add(panel1);
    		TimeZone timeZone = cityTimes.get(i);
    		
        	Calendar calendar = Calendar.getInstance(cityTimes.get(i));
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
            dateStr = dayOfWeek + " " + month + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR);
            
            Calendar c = Calendar.getInstance(timeZone);
            String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
            String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
            timeStr = militaryTime + twelveHourTime;
            System.out.println(timeStr);
            textArea.setText(i + "\n" + dateStr + "\n" + timeStr);
        }
    	mainPanel.add(button1);
    
         frame.pack();
    }
}
