package _08_California_Weather;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */

public class CaliforniaWeather implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton button1 = new JButton("Find Weather of City");
	JButton button2 = new JButton("Find city with weather condition");
	JButton button3 = new JButton("Find city with Min: __ and Max:__ temp in F");
	
    void start() {
    	panel.add(button1);
    	panel.add(button2);
    	panel.add(button3);
    	frame.add(panel);
    	frame.setSize(300, 300);
    	frame.setVisible(true);
    	frame.pack();
    	button1.addActionListener(this);
    	button2.addActionListener(this);
    	button3.addActionListener(this);
       
        
        
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
		if (e.getSource()== button1) {
			String inputCity = JOptionPane.showInputDialog("What city's weather would you like to search for?");
	        
	        // All city keys have the first letter capitalized of each word
	        String cityName = Utilities.capitalizeWords( inputCity);
	        WeatherData datum = weatherData.get(cityName);
	        
	        if( datum == null ) {
	        	JOptionPane.showMessageDialog(null, "Unable to find weather data for: " + cityName);
	            System.out.println();
	        } else {
	        	JOptionPane.showMessageDialog(null,cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
	           
	        }
	        
		}
	if(e.getSource()==button2) {
		ArrayList <String> cities = new ArrayList<String>(); 
		String weatherCondition = JOptionPane.showInputDialog("What weather condition would you like to search for? ");
        for(String i : weatherData.keySet()) {
        	if(weatherData.get(i).weatherSummary.equals(weatherCondition)) {
        		System.out.println(i);
        		cities.add(i);
        	}
        	
}
        JOptionPane.showMessageDialog(null, "These cities have the weather condition you are looking for: " + cities);
        }
	if(e.getSource() == button3) {
		ArrayList <String> cities = new ArrayList<String>(); 
		String maxTemp = JOptionPane.showInputDialog("What max temperature: ");
		String minTemp = JOptionPane.showInputDialog("What minimum temperature: ");
		Double max = Double.parseDouble(maxTemp);
		Double min = Double.parseDouble(minTemp);
		
		for (String i : weatherData.keySet()) {
			if(weatherData.get(i).temperatureF<=max&& weatherData.get(i).temperatureF >= min) {
				cities.add(i);
				
			}
		}
		JOptionPane.showMessageDialog(null, "These cities have the temperature you are looking for: "+ cities);
	}
	}
		
	}

