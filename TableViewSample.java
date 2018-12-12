package application;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class TableViewSample {
	private FoodData data = new FoodData();
	public GridPane start() {
		Font comicSans = new Font("Comic Sans", 12);
		final Label calorie = new Label("Calories");
		calorie.setFont(comicSans);
		final Label fiber = new Label("Fiber");
		fiber.setFont(comicSans);
		final Label fat = new Label("Fat");
		fat.setFont(comicSans);
		final Label protein = new Label("Protein");
		protein.setFont(comicSans);
		final Label search = new Label("Search By Name");
		search.setFont(comicSans);
		final Label carbs = new Label("Carbs");
		carbs.setFont(comicSans);

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
		
		TextField fatExactText = new TextField();
		fatExactText.setPrefColumnCount(10);
		fatExactText.setEditable(true);
		fatExactText.setText("Exact Fat");

		TextField proteinMinText = new TextField();
		proteinMinText.setPrefColumnCount(10);
		proteinMinText.setEditable(true);
		proteinMinText.setText("Min Protein");

		TextField proteinMaxText = new TextField();
		proteinMaxText.setPrefColumnCount(10);
		proteinMaxText.setEditable(true);
		proteinMaxText.setText("Max Protein");

		TextField proteinExactText = new TextField();
		proteinExactText.setPrefColumnCount(10);
		proteinExactText.setEditable(true);
		proteinExactText.setText("Exact Protein");

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
		gridPane.add(fatExactText, 2, 5);

		gridPane.add(carbs, 0, 6);
		gridPane.add(carbsMinText, 0, 7);
		gridPane.add(carbsMaxText, 1, 7);
		gridPane.add(carbsExactText, 2, 7);

		//gridPane.add(fatMinBox,0 , 5);
		gridPane.add(protein, 0, 8);
		gridPane.add(proteinMinText, 0, 9);
		gridPane.add(proteinMaxText, 1, 9);
		gridPane.add(proteinExactText, 2, 9);
		gridPane.add(foodSearch,0,10);
		gridPane.add(analyzeButton,1 ,10);
		//gridPane.add(proteinMinBox, 0, 7);


		analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				List<String> rules = new ArrayList<String>();
				List<FoodItem> foods = new ArrayList<FoodItem>();
				String minCalories, maxCalories, exactCalories;
				String minFiber, maxFiber, exactFiber; 
				String minFat, maxFat, exactFat;
				String minProtein, maxProtein, exactProtein;
				String minCarbs, maxCarbs, exactCarbs;
				//rules
				String calMinRule, calMaxRule, calExactRule;
				String fiberMinRule, fiberMaxRule, fiberExactRule;
				String fatMinRule, fatMaxRule, fatExactRule;
				String proteinMaxRule, proteinMinRule, proteinExactRule;
				String carbMaxRule, carbMinRule, carbExactRule;

				try {
				    calorieMinText.clear();
	                   calorieMaxText.clear();
	                   calorieExactText.clear();
	                   fiberMinText.clear();
                       fiberMaxText.clear();
                       fiberExactText.clear();
                       fatMinText.clear();
                       fatMaxText.clear();
                       fatExactText.clear();
                       carbsMinText.clear();
                       carbsMaxText.clear();
                       carbsExactText.clear();
					minCalories = calorieMinText.getText();
					maxCalories = calorieMaxText.getText();
					exactCalories = calorieExactText.getText();
					Double.parseDouble(minCalories);
					Double.parseDouble(maxCalories);
					if(minCalories != null) {
						calMinRule = "calories >= " + minCalories;
						rules.add(calMinRule);
					} if(maxCalories != null) {
						calMaxRule = "calories <= " + maxCalories;
						rules.add(calMaxRule);
					} if(exactCalories != null) {
						calExactRule = "calories == "+ exactCalories;
						rules.add(calExactRule);
					}
					
					minFiber = fiberMinText.getText();
					maxFiber = fiberMaxText.getText();
					exactFiber = fiberExactText.getText();
					if(minFiber != null) {
			            fiberMinRule = "fiber >= " + minFiber;
			            rules.add(fiberMinRule);
					} if(maxFiber != null) {
			            fiberMaxRule = "fiber <= " + maxFiber;
			            rules.add(fiberMaxRule);
					} if(exactFiber != null) {
						fiberExactRule = "fiber == "+ exactFiber;
						rules.add(fiberExactRule);
					}
					
					minFat = fatMinText.getText();
					maxFat = fatMaxText.getText();
					exactFat = fatExactText.getText();
					if(minFat != null) {
						fatMinRule = "fat >= " + minFat;
						rules.add(fatMinRule);
					} if(maxFat != null) {
						fatMaxRule = "fat >= " + maxFat;
						rules.add(fatMaxRule);
					} if(exactFat != null) {
						fatExactRule = "fat == "+ exactFat;
						rules.add(fatExactRule);
					}
					
					minProtein = proteinMinText.getText();
					maxProtein = proteinMaxText.getText();
					exactProtein = proteinExactText.getText();
					if(minProtein != null) {
			            proteinMinRule = "protein >= " + minProtein;
			            rules.add(proteinMinRule);
					} if(maxProtein != null) {
			            proteinMaxRule = "protein <= " + maxProtein;
			            rules.add(proteinMaxRule);
					} if(proteinExactText != null) {
						proteinExactRule = "protein == "+ exactProtein;
						rules.add(proteinExactRule);
					}
					
					minCarbs = carbsMinText.getText();
					maxCarbs = carbsMaxText.getText();
					exactCarbs = carbsExactText.getText();
					if(minCarbs != null) {
			            carbMinRule = "carbs >= " + minCarbs;
			            rules.add(carbMinRule);
					} if(maxCarbs != null) {
			            carbMaxRule = "carbs >= " + maxCarbs;
			            rules.add(carbMaxRule);
					} if(carbsExactText != null) {
						carbExactRule = "carbs == "+ exactCarbs;
						rules.add(carbExactRule);
					}
					foods = data.filterByNutrients(rules);
				} catch(Exception e) {
					Alert alert = new Alert(AlertType.ERROR, "Please enter valid numbers only.");
					alert.showAndWait();
				}
			} 
		});

		
		gridPane.setLayoutX(10);
		gridPane.setLayoutY(120);
		//calories fiber fat carbs protein
		return gridPane;
	}
}