package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public static int CIR_SIZE = 5; //The size of the circle
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(11);
		pg.ellipse(x, y, 5, 5);
		
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title		
		String country = "Country: " + getCountry();
		String altitude = "Altitude: " + getAltitude();
		String city = "City: " + getCity();
		String name = "Name: " + getName();
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-CIR_SIZE-20, pg.textWidth(country) +pg.textWidth(altitude)+pg.textWidth(city)+pg.textWidth(name)+ 20, 20);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		//pg.text(name, x+3, y-CIR_SIZE-33);
		float c = x+3;
		pg.text(country, c, y - CIR_SIZE -18);
		c = c+pg.textWidth(country)+5;
		pg.text(altitude, c, y - CIR_SIZE -18);
		c = c+pg.textWidth(altitude)+5;
		pg.text(city, c, y - CIR_SIZE -18);
		c = c+pg.textWidth(city)+5;
		pg.text(name, c, y - CIR_SIZE -18);
		
		
		pg.popStyle();
		// show routes
		}
	
	private String getCountry()
	{
		return getStringProperty("country");
	}
	
	private String getAltitude()
	{
		return getStringProperty("altitude");
	}
	
	private String getCity()
	{
		return getStringProperty("city");
	}
	
	private String getName()
	{
		return getStringProperty("name");
	}
	
	private String getRoute()
	{
		
		return AirportMap.routeList.get(0).getProperties().toString();
	}
}
