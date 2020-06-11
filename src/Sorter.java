// Carl Mastny
// ITPRG247
// Lab 6 - 23.15 p640
// This program displays a graphic animation of different sorts
// Sources:
// https://www.tutorialspoint.com/How-to-convert-string-to-array-of-integers-in-java
// https://stackoverflow.com/questions/15016826/set-javafx-combobox-font
// https://stackoverflow.com/questions/22190370/how-to-set-width-of-drop-down-of-combobox-in-java-fx
// https://stackoverflow.com/questions/30572918/how-to-get-the-selected-item-from-a-combobox-in-javafx
// https://www.geeksforgeeks.org/javafx-borderpane-class
// https://liveexample-ppe.pearsoncmg.com/intro11e/evennumberedexercisehtml/Exercise23_16.html
// Sorting algorithims were derived from the book
// ("Introduction to Java Programming and Data Structures, Comprehensive Version, Eleventh Edition" by Y. Daniel Yang)

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.HBox;


public class Sorter extends Application {
	int[] values = new int[10];
	Timeline animation = null;
	@Override
	public void start(Stage primaryStage) {
		GraphPane graphPane = new GraphPane();
		
		GridPane grid = new GridPane();
		grid.setHgap(40);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		/*
		 *  vvv top controls vvv
		 */
		Button start = new Button("START");
		start.setStyle("-fx-border-color: null; -fx-background-color: #ff8080;");
		start.setFont(Font.font("Amatic SC", 20));
		grid.add(start, 0, 0);
		
		Label selectSort = new Label("Select Sort: ");
		selectSort.setFont(Font.font("Amatic SC", 20));
		grid.add(selectSort, 1, 0);
		
		ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Selection Sort",
		        "Bubble Sort",
		        "Insertion Sort"
		    );
		ComboBox<String> comboBox = new ComboBox<String>(options);
		//https://stackoverflow.com/questions/15016826/set-javafx-combobox-font
		//https://stackoverflow.com/questions/22190370/how-to-set-width-of-drop-down-of-combobox-in-java-fx
		comboBox.setStyle("-fx-font: 15px \"Amatic SC\";");
		
		
		ObservableList<String> intervalOptions = 
		    FXCollections.observableArrayList(
		    	"10",
		    	"25",
		    	"50",
		    	"100",
		        "250",
		        "500"
		    );
		ComboBox<String> intervalComboBox = new ComboBox<String>(intervalOptions);
		//https://stackoverflow.com/questions/15016826/set-javafx-combobox-font
		//https://stackoverflow.com/questions/22190370/how-to-set-width-of-drop-down-of-combobox-in-java-fx
		intervalComboBox.setStyle("-fx-font: 15px \"Amatic SC\";");
		
		HBox dropDowns = new HBox();
		dropDowns.setSpacing(40);
		dropDowns.getChildren().addAll(comboBox, intervalComboBox);
		
		grid.add(dropDowns, 2, 0);
		
		Label enterValues = new Label("Enter 10 values to be sorted: ");
		enterValues.setFont(Font.font("Amatic SC", 20));
		grid.add(enterValues, 1, 1);
		
		TextField valuesEntered = new TextField();
		valuesEntered.setStyle("-fx-font: 15px; -fx-pref-width: 300;");
		grid.add(valuesEntered, 2, 1);
		/*
		 * ^^^ top controls ^^^
		 */
		
		//https://stackoverflow.com/questions/30572918/how-to-get-the-selected-item-from-a-combobox-in-javafx
		
		//Event handlers for my Timeline object
		EventHandler<ActionEvent> selectionSortStep = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		//if numbers are sorted, stop the animation, reset the class, and unlock the controls
    			if (SelectionSortStepper.isSorted()) {
    				graphPane.setNumbers(values);
    				start.setDisable(false);
    				comboBox.setDisable(false);
    				animation.stop();
    			} else {
    				values = SelectionSortStepper.step(values);
    				graphPane.setNumbers(values);
    				graphPane.setColoredIndex(SelectionSortStepper.getCurrentIndex());
    			}
        	}
        };
        
        EventHandler<ActionEvent> bubbleSortStep = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		//if numbers are sorted, stop the animation, reset the class, and unlock the controls
        		if (BubbleStepper.isSorted()) {
    				graphPane.setNumbers(values);
    				start.setDisable(false);
    				comboBox.setDisable	(false);
    				animation.stop();
    			} else {
    				values = BubbleStepper.step(values);
        			graphPane.setNumbers(values);
        			graphPane.setColoredIndex(BubbleStepper.getCurrentIndex());
    			}
        	}
        };
        
        EventHandler<ActionEvent> insertionSortStep = new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		//if numbers are sorted, stop the animation, reset the class, and unlock the controls
        		if (InsertionSortStepper.isSorted()) {
    				graphPane.setColoredIndex(InsertionSortStepper.getCurrentIndex());
    				graphPane.setNumbers(values);
    				start.setDisable(false);
    				comboBox.setDisable	(false);
    				animation.stop();
    			} else {
    				values = InsertionSortStepper.step(values);
        			graphPane.setNumbers(values);
        			graphPane.setColoredIndex(InsertionSortStepper.getCurrentIndex());
    			}
        	}
        };
        
        //event handler for when the start button is clicked
		EventHandler<ActionEvent> startSort = new EventHandler<ActionEvent>() { 
			
			public void handle(ActionEvent e) { 
				
				//get values from text box
				// https://www.tutorialspoint.com/How-to-convert-string-to-array-of-integers-in-java
				String[] strValues = valuesEntered.getText().split(" ");
				//input validation
				if (comboBox.getValue() == null || strValues.length != 10
						|| valuesEntered.getText() == null || intervalComboBox.getValue() == null) {
					Alert alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Whoops");
        			alert.setHeaderText(null);
        			alert.setContentText("Please make sure all fields are full and 10 values are entered");

        			alert.showAndWait();
				} else {
					int intervalDuration = Integer.parseInt(intervalComboBox.getValue());
					
					//if the array of values is already sorted reset the sort objects
					if (SelectionSortStepper.isSorted()) {
						SelectionSortStepper.reset();
					} else if (BubbleStepper.isSorted()) {
						BubbleStepper.reset();
					} else if (InsertionSortStepper.isSorted()) {
						InsertionSortStepper.reset();
					}
					
					// set array to integers from text box
					for (int i = 0; i < strValues.length; i++) {
						values[i] = Integer.parseInt(strValues[i]);
					}
					
					//while the sort is running, you can't restart the sort or change the sort type
					start.setDisable(true);
					comboBox.setDisable(true);
					
					// create timeline object to create an animation of the sorting steps according to which sort 
					// is chosen
					if (comboBox.getValue() == "Selection Sort" && !(SelectionSortStepper.isSorted())) {
						animation = new Timeline(new KeyFrame(Duration.millis(intervalDuration), selectionSortStep));
						animation.setCycleCount(Timeline.INDEFINITE);
					} else if (comboBox.getValue() == "Bubble Sort" && !(BubbleStepper.isSorted())) {
						animation = new Timeline(new KeyFrame(Duration.millis(intervalDuration), bubbleSortStep));
						animation.setCycleCount(Timeline.INDEFINITE);
					} else if (comboBox.getValue() == "Insertion Sort" && !(InsertionSortStepper.isSorted())) {
						animation = new Timeline(new KeyFrame(Duration.millis(intervalDuration), insertionSortStep));
						animation.setCycleCount(Timeline.INDEFINITE);
					}
					
					animation.play();
				}
				
        	}
			
		};
        
        start.setOnAction(startSort);
        
		//https://www.geeksforgeeks.org/javafx-borderpane-class
        BorderPane borderPane = new BorderPane();
		borderPane.setTop(grid);
		borderPane.setCenter(graphPane);
		borderPane.setStyle("-fx-background-color: #fff;");
		
		Scene scene = new Scene(borderPane, 750, 500);
		primaryStage.setTitle("Sorter");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
