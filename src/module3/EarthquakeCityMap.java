package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;
import processing.core.PFont;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();
	    
	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	   
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how o get one property and use it
	    for(int count=0 ; count< earthquakes.size(); count++)
	    //use the loop method to attribute all the properties to their corresponding locations
	    {
	    	if (earthquakes.size() > 0) 
	    	{
	    		PointFeature f = earthquakes.get(count);
	    		System.out.println(f.getProperties());
	    		//to monitor whether the propertied has been all gotten.
	    		markers.add(createMarker(f));
	    		Object magObj = f.getProperty("magnitude");
	    		Float mag = Float.parseFloat(magObj.toString());
	    		int color = getColor(mag);
	    		Marker currentMarker = markers.get(count);
	    		currentMarker.setColor(color);
	    		switch(getStrColor(mag))
	    		{
	    			case "yellow": ((SimplePointMarker) currentMarker).setRadius(10); break;
	    			case "red" : ((SimplePointMarker) currentMarker).setRadius(12); break;
	    			case "blue" :((SimplePointMarker) currentMarker).setRadius(8); break;
	    		}
	    		
	    		map.addMarker(markers.get(count)); 
	    	}else{
	    		System.out.println("Error happened: We can not get the information of the earthquakes");
	    	}
	    // PointFeatures also have a getLocation method	    	}
	    }
	}
	    private int getColor(Float mag)
	    {	    	
	    	int yellow = color(255, 255, 0);
	    	int blue = color(0, 0, 255);
	    	int red = color(255, 0, 0);
	    	if (mag< 4.0){
	    		return blue;
	    	}else if (mag>=4.0 && mag<= 4.9){
	    		return yellow;
	    	}else {
	    		return red;
	    	}
	    }
	  private String getStrColor(Float mag)
	  {
		  //help to set the radius of the marker according to different magnitude
		
		  if (mag< 4.0){
	    		return "blue";
	    	}else if (mag>=4.0 && mag<= 4.9){
	    		return "yellow";
	    	}else {
	    		return "red";
	    	}
	  }
	
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// associate the location with its own properties
		return new SimplePointMarker(feature.getLocation(), feature.getProperties());
	}
	private Location getLocation(PointFeature feature)
	{
		return new Location(feature.getLocation());
	}
	
	
	public void draw() {
	    background(25);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		int yellow = color(255, 255, 0);
    	int blue = color(0, 0, 255);
    	int red = color(255, 0, 0);
    	int textHeight = 125;
		fill(255, 250, 240);
		rect(15, 50, 170, 250);
		fill(50);
		textSize(15);
		text("Earthquake Key", 40,75 ); 
		fill(red);
		ellipse(42, 120, 24, 24);
		fill(yellow);
		ellipse(42, 170, 20, 20);
		fill(blue);
		ellipse(42, 220, 16, 16);
		fill(50);
		textSize(15);
		text("5.0+ Magnitude", 62,textHeight ); 
		textSize(15);
		text("4.0+ Magnitude", 62,textHeight+50 ); 
		textSize(15);
		text("Below 4.0", 62,textHeight+100 ); 

	  
	    // Remember you can use Processing's graphics methods here
	
	}
}
