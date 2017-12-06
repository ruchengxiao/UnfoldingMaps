package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
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
		// Drawing a centered square for Ocean earthquakes
		// DO NOT set the fill color.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered square.
		
						
		pg.rect(x-radius,y-radius,2*radius,2*radius);		
		String age = getAge();
		if(age.equals("Past Hour")||age.equals("Past Day")){
			pg.fill(0,0,0);
			pg.line(x-radius,y-radius,x+radius,y+radius);
			pg.line(x+radius,y-radius,x-radius,y+radius);
		}
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		
	}
	


	

}
