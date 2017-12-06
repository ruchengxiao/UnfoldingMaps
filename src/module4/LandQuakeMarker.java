package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = true;
		
	}
	/*public void draw(PGraphics pg, float x, float y) {
		// save previous styling
		pg.pushStyle();
			
		// determine color of marker from depth
		colorDetermine(pg);
		
		// call abstract method implemented in child class to draw marker shape
		drawEarthquake(pg, x, y);
		
		// OPTIONAL TODO: draw X over marker if within past day		
		
		// reset to previous styling
		pg.popStyle();
		
	}
	private void colorDetermine(PGraphics pg) {
		//TODO: Implement this method
		float depth= this.getDepth();
		if(depth<=THRESHOLD_INTERMEDIATE){
			pg.fill(254,254,65);
		}else if(depth>THRESHOLD_INTERMEDIATE||depth<=THRESHOLD_DEEP){
			pg.fill(0,0,255);
		}else{
			pg.fill(255,0,0);
		}
		
	}*/

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
						
		
		pg.ellipse(x-radius,y-radius,2*radius,2*radius);		
		String age = this.getAge();
	
		if(age.equals("Past Hour")||age.equals("Past Day")) {
			pg.fill(0,0,0);
			pg.line(x-2*radius,y-2*radius,x,y);
			pg.line(x,y-2*radius,x-2*radius,y);
		}
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}



		
}