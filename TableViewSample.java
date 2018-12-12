package application;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class TableViewSample {
	private FoodData data = new FoodData();
	public GridPane start() {
		final Label calorie = new Label("Calories");
		calorie.setFont(new Font("Comic Sans", 16));

		final Label fiber = new Label("Fiber");
		fiber.setFont(new Font("Comic Sans", 12));

		final Label fat = new Label("Fat");
		fat.setFont(new Font("Comic Sans", 12));

		final Label protein = new Label("Protein");
		protein.setFont(new Font("Comic Sans", 12));

		final Label search = new Label("Search By Name");
		search.setFont(new Font("Comic Sans", 12));

		final Label carbs = new Label("Carbs");
		carbs.setFont(new Font("Comic Sans", 16));

		GridPane gridPane = new GridPane();

		TextField foodSearch = new TextField();
		foodSearch.setPrefColumnCount(10);
		foodSearch.setEditable(true);
		foodSearch.setText("Search");

		Button analyzeButton = new Button("Analyze");


		//        ComboBox<String> calorieBox = new ComboBox<String>();
		//        calorieBox.getItems().add("<");
		//        calorieBox.getItems().add(">");
		//        calorieBox.getItems().add("=");
		//
		//        ComboBox<String> fiberBox = new ComboBox<String>();
		//        fiberBox.getItems().add("<");
		//        fiberBox.getItems().add(">");
		//        fiberBox.getItems().add("=");
		//
		//        ComboBox<String> fatMinBox = new ComboBox<String>();
		//        fatMinBox.getItems().add("<");
		//        fatMinBox.getItems().add(">");
		//        fatMinBox.getItems().add("=");
		//        
		//        ComboBox<String> proteinMinBox = new ComboBox<String>();
		//        proteinMinBox.getItems().add("<");
		//        proteinMinBox.getItems().add(">");
		//        proteinMinBox.getItems().add("=");

		TextField calorieMinText = new TextField();
		calorieMinText.setPrefColumnCount(10);
		calorieMinText.setEditable(true);

		calorieMinText.setText("Min Calories");

		TextField calorieMaxText = new TextField();
		calorieMaxText.setPrefColumnCount(10);
		calorieMaxText.setEditable(true);
		calorieMaxText.setText("Max Calories");
		TextField calorieExactText = new TextField();
		calorieExactText.setPrefColumnCount(10);
		calorieExactText.setEditable(true);
		calorieExactText.setText("Exact Calories");

		TextField carbsMinText = new TextField();
		carbsMinText.setPrefColumnCount(10);
		carbsMinText.setEditable(true);

		carbsMinText.setText("Min Carbs");

		TextField carbsMaxText = new TextField();
		carbsMaxText.setPrefColumnCount(10);
		carbsMaxText.setEditable(true);
		carbsMaxText.setText("Max Carbs");

		TextField carbsExactText = new TextField();
		carbsExactText.setPrefColumnCount(10);
		carbsExactText.setEditable(true);
		carbsExactText.setText("Exact Carbs");

		TextField fiberMinText = new TextField();
		fiberMinText.setPrefColumnCount(10);
		fiberMinText.setEditable(true);
		fiberMinText.setText("Min Fiber");

		TextField fiberExactText = new TextField();
		fiberExactText.setPrefColumnCount(10);
		fiberExactText.setEditable(true);
		fiberExactText.setText("Exact Fiber");
		TextField fiberMaxText = new TextField();
		fiberMaxText.setPrefColumnCount(10);
		fiberMaxText.setEditable(true);
		fiberMaxText.setText("Max Fiber");

		TextField fatMinText = new TextField();
		fatMinText.setPrefColumnCount(10);
		fatMinText.setEditable(true);
		fatMinText.setText("Min Fat");

		TextField fatMaxText = new TextField();
		fatMaxText.setPrefColumnCount(10);
		fatMaxText.setEditable(true);
		fatMaxText.setText("Max Fat");

		TextField proteinMinText = new TextField();
		proteinMinText.setPrefColumnCount(10);
		proteinMinText.setEditable(true);
		proteinMinText.setText("Min Protein");

		TextField proteinMaxText = new TextField();
		proteinMaxText.setPrefColumnCount(10);
		proteinMaxText.setEditable(true);
		proteinMaxText.setText("Max Protein");

		gridPane.add(calorie, 0, 0);
		gridPane.add(calorieMinText, 0, 1);
		gridPane.add(calorieMaxText, 1, 1);
		gridPane.add(calorieExactText, 2, 1);

		//gridPane.add(calorieBox, 0, 1);
		gridPane.add(fiber, 0, 2);
		gridPane.add(fiberMinText, 0, 3);
		gridPane.add(fiberMaxText, 1, 3);
		gridPane.add(fiberExactText, 2, 3);

		//gridPane.add(fiberBox, 0, 3); //edited
		gridPane.add(fat, 0, 4);
		gridPane.add(fatMinText, 0, 5);
		gridPane.add(fatMaxText, 1, 5);

		gridPane.add(carbs, 0, 6);
		gridPane.add(carbsMinText, 0, 7);
		gridPane.add(carbsMaxText, 1, 7);
		gridPane.add(carbsExactText, 2, 7);

		//gridPane.add(fatMinBox,0 , 5);
		gridPane.add(protein, 0, 8);
		gridPane.add(proteinMinText, 0, 9);
		gridPane.add(proteinMaxText, 1, 9);

		gridPane.add(analyzeButton, 0,10 );
		//gridPane.add(proteinMinBox, 0, 7);

		//rules
		String calMinRule;
		String calMaxRule;
		String fiberMinRule;
		String fiberMaxRule;
		String fatMinRule;
		String fatMaxRule;
		String proteinMaxRule;
		String proteinMinRule;
		String proteinRule;
		String proteinExactRule;
		String fatExactRule;
		String fiberExactRule;
		String calExactRule;


		analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				List<String> rules = new ArrayList<String>();
				List<FoodItem> foods = new ArrayList<FoodItem>();
				String minCalories;
				String maxCalories; 
				String minFiber; 
				String maxFiber;
				String minFat;
				String maxFat;
				String minProtein;
				String maxProtein;
				String minCarbs;
				String maxCarbs;

				try {
					minCalories = calorieMinText.getText();
					maxCalories = calorieMaxText.getText();
					minFiber = fiberMinText.getText();
					maxFiber = fiberMaxText.getText();
					minFat = fatMinText.getText();
					maxFat = fatMaxText.getText();
					minProtein = proteinMinText.getText();
					maxProtein = proteinMaxText.getText();
					minCarbs = carbsMinText.getText();
					maxCarbs = carbsMaxText.getText();

					rules.add(minCalories);
					rules.add(maxCalories);
					rules.add(minFiber);
					rules.add(maxFiber);
					rules.add(minFat);
					rules.add(maxFat);
					rules.add(minProtein);
					rules.add(maxProtein);
					rules.add(minCarbs);
					rules.add(maxCarbs);

					foods = data.filterByNutrients(rules);

				} catch(Exception e) {
					Alert alert = new Alert(AlertType.ERROR, "Enter a valid number.");
					alert.showAndWait();
				}
			} 
		});

		gridPane.add(foodSearch,0,9);


		//        if(exactCal != null) {
		//            
		//            calExactRule = "calories == "+ exactCal;
		//            
		//        }
		//        if(minCal != null && maxCal != null) {
		//            calMinRule = "calories >= " + minCal;
		//            calMaxRule = "calories <= " + maxCal;
		//            
		//        }
		//        if(minCal != null && maxCal == null) {
		//            calMinRule = "calories >= " + minCal;
		//        }
		//        if(maxCal != null && minCal == null) {
		//            calMaxRule = "calories <= " + maxCal;
		//        }
		//        
		//        //Fiber rules
		//        if(exactFiber != null) {
		//            
		//            fiberExactRule = "fiber == "+ exactFiber;
		//            
		//        }
		//        if(minFiber != null && maxFiber != null) {
		//            fiberMinRule = "fiber >= " + minFiber;
		//            fiberMaxRule = "fiber <= " + maxFiber;
		//            
		//        }
		//        if(minFiber != null && maxFiber == null) {
		//            fiberMinRule = "fiber >= " + minFiber;
		//        }
		//        if(maxFiber != null && minFiber == null) {
		//            fiberMaxRule = "fiber <= " + maxFiber;
		//        }
		//        //fat rules
		//        if(exactFat != null) {
		//            
		//            fatExactRule = "fiber == "+ exactFat;
		//            
		//        }
		//        if(minFat != null && maxFat != null) {
		//            fatMinRule = "fat >= " + minFat;
		//            fatMaxRule = "fat <= " + maxFat;
		//            
		//        }
		//        if(minfat != null && maxFiber == null) {
		//            fatMinRule = "fat >= " + minFat;
		//            }
		//        if(maxFat != null && minFat == null) {
		//            fatMaxRule = "fat <= " + maxFat;
		//        }
		//        // protein rules
		//        if(exactProtein != null) {
		//            
		//            proteinExactRule = "protein == "+ exactProtein;
		//            
		//        }
		//        if(minProtein != null && maxProtein != null) {
		//            proteinMinRule = "protein >= " + minProtein;
		//            proteinMaxRule = "protein <= " + maxProtein;
		//            
		//        }
		//        if(minProtein != null && maxProtein == null) {
		//            proteinMinRule = "protein >= " + minProtein;
		//            }
		//        if(maxProtein != null && minProtein == null) {
		//            proteinMaxRule = "protein <= " + maxProtein;
		//        }


		gridPane.setLayoutX(10);
		gridPane.setLayoutY(120);
		//calories fiber fat carbs protein
		return gridPane;
	}
}
