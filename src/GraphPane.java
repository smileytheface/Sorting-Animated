// Carl Mastny
// ITPRG247
// Lab 6 - 23.15 p640
// This class creates a pane to hold the histogram of the numbers that are being sorted
// Sources:
// https://liveexample-ppe.pearsoncmg.com/intro11e/evennumberedexercisehtml/Exercise23_16.html

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GraphPane extends Pane {
	private int[] numbers;
	private int coloredIndex = -1;
	
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
		repaint();
	}
	
	public void setColoredIndex(int index) {
      coloredIndex = index;
      repaint();
    }
	
	public void repaint() {
		int max = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (max < numbers[i]) {
				max = numbers[i];
			}
		}
		
		this.getChildren().clear();
	    
		double height = getHeight() * 0.88 - 40;
	    double width = getWidth() - 20;
	    double unitWidth = width * 1.0 / numbers.length;
	
	    for (int i = 0; i < numbers.length; i++) {
	    	// Creating the row at the top
	    	Rectangle square =  new Rectangle(i * unitWidth + 10, 0, unitWidth, unitWidth);
	        square.setFill(Color.TRANSPARENT);
	        square.setStroke(Color.BLACK);
	        this.getChildren().add(square);
	        
	        // Creating the graph at the bottom
	        // Algorithm for setting the coordinates of the rectangles dimensions was taken from the companion 
	        // website's solution for exercise 16
	        Rectangle bar =  new Rectangle(i * unitWidth + 10, getHeight() 
	        		- (numbers[i] * 1.0 / max) * height, unitWidth, (numbers[i] * 1.0 / max) * height);
	        bar.setFill(Color.web("#c8beb7"));
	        bar.setStroke(Color.WHITE);
	        bar.setStrokeWidth(5);
	        this.getChildren().add(bar);
        }
	    	
	    if (coloredIndex >= 0) {
	        int i = coloredIndex;
	        Rectangle filledSquare = new Rectangle(i * unitWidth + 10, 0, unitWidth, unitWidth);
	        filledSquare.setFill(Color.web("#ff8080"));
	        filledSquare.setStroke(Color.BLACK);
	        this.getChildren().add(filledSquare);
	        
	        Rectangle filledBar = new Rectangle(i * unitWidth + 10, getHeight() 
	        		- (numbers[i] * 1.0 / max) * height, unitWidth, (numbers[i] * 1.0 / max) * height);
	        filledBar.setFill(Color.web("#ff8080"));
	        filledBar.setStroke(Color.WHITE);
	        filledBar.setStrokeWidth(5);
	        this.getChildren().add(filledBar);
        }
	    
	    for (int i = 0; i < numbers.length; i++) {
	    	this.getChildren().add(new Text(i * unitWidth + 10 + (unitWidth / 2) - 3, unitWidth / 2, numbers[i] + ""));
	    }
	    
	    
	}
}