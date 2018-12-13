package application;

import java.util.List;

import application.FoodTableSample.TableItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	private int count = 0;
	private FoodTableSample foodTable2;
	@Override
	public void start(Stage applicationStage) {
		FoodData foodList = new FoodData();
		FoodData myFood = new FoodData();

		BPTree proteinTree = new BPTree(4);
		BPTree fatTree = new BPTree(4);
		BPTree fiberTree = new BPTree(4);
		BPTree calorieTree = new BPTree(4);
		
		TableViewSample tableView = new TableViewSample();
		
		
		foodTable2 = new FoodTableSample(applicationStage,220,475, foodList);
		FoodTableSample foodTable1 = new FoodTableSample(applicationStage,220, 70, foodList);

		Button importFoodList = new Button("Import Food List");
		Button saveFoodList = new Button("Save Food List");
		Button addFoodTitle = new Button("Add New Food"); //button done
		Button analyzeFood = new Button("Analyze Food");

		analyzeFood.setLayoutX(10);
		analyzeFood.setLayoutY(500);
		importFoodList.setLayoutX(10);
		importFoodList.setLayoutY(50);
		addFoodTitle.setLayoutX(10);
		addFoodTitle.setLayoutY(420);
		saveFoodList.setLayoutX(120);
		saveFoodList.setLayoutY(50);

		Text mainTitle = new Text(10, 25, "Meal Planner");
		Text foodItems = new Text(400, 50, "Food Items");
		Text yourMeal = new Text(400, 460, "Your Meal");

		mainTitle.setFont(Font.font("ComicSans", FontWeight.BOLD, 25));
		mainTitle.setFill(Color.DARKSLATEGREY);
		foodItems.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
		foodItems.setFill(Color.DARKSLATEGREY);
		yourMeal.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
		yourMeal.setFill(Color.DARKSLATEGREY);

		TextField foodName = new TextField();
		foodName.setMaxWidth(80);
		foodName.setLayoutX(10);
		foodName.setLayoutY(380);
		foodName.setText("Food Name");

		TextField calories = new TextField();
		calories.setMaxWidth(80);
		calories.setLayoutX(110);
		calories.setLayoutY(380);
		calories.setText("Enter Calories");

		TextField fiber = new TextField();
		fiber.setMaxWidth(80);
		fiber.setLayoutX(210);
		fiber.setLayoutY(380);
		fiber.setText("Enter Fiber");

		TextField fat = new TextField();
		fat.setMaxWidth(80);
		fat.setLayoutX(310);
		fat.setLayoutY(380);
		fat.setText("Enter Fat");

		TextField protein = new TextField();
		protein.setMaxWidth(80);
		protein.setLayoutX(410);
		protein.setLayoutY(380);
		protein.setText("Enter Protein");

		TextField carbs = new TextField();
		carbs.setMaxWidth(80);
		carbs.setLayoutX(510);
		carbs.setLayoutY(380);
		carbs.setText("Enter Carbs");

		TextField file = new TextField();
		file.setMaxWidth(200);
		file.setLayoutX(10);
		file.setLayoutY(85);
		file.setText("Enter File Name/Path");

		Text mealCalories = new Text(10, 550, "Calories");
		Text calorieCount = new Text(13, 565, "300");
		Text mealFiber = new Text(10, 600, "Fiber");
		Text fiberCount = new Text(13, 615, "18 g");
		Text mealFat = new Text(10, 650, "Fat");
		Text fatCount = new Text(13, 665, "18 g");
		Text mealProtein = new Text(10, 700, "Protein");
		Text proteinCount = new Text(13, 715, "1 g");
	    
		addFoodTitle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FoodItem newFood;// = new FoodItem(count, null);
				String food = "";
				String calNum = ""; 
				String protNum = ""; 
				String fibNum = "";
				String faNum = "";
				String carbsNum = "";
				Double cal = 0.0;   
				Double fib = 0.0;   
				Double fa = 0.0;
				Double prot = 0.0;
				Double carb = 0.0;

				try {
					food = foodName.getText();
					calNum = calories.getText();
					cal = Double.parseDouble(calNum);
					fibNum = fiber.getText();
					fib = Double.parseDouble(fibNum);
					faNum = fat.getText();
					fa = Double.parseDouble(faNum);
					protNum = protein.getText();
					prot = Double.parseDouble(protNum);
					carbsNum = carbs.getText();
					carb = Double.parseDouble(protNum);
				} catch(Exception e){
					Alert alert = new Alert(AlertType.ERROR, "Enter all information for a new food.");
					alert.showAndWait();
				}

				if(prot >= 0 && fa >= 0 && fib >= 0 && cal >= 0) {
					newFood = new FoodItem(Integer.toString(count), food);
					newFood.addNutrient("calories", cal);
					newFood.addNutrient("fiber", fib);
					newFood.addNutrient("fat", fa);
					newFood.addNutrient("protein", prot);
					newFood.addNutrient("carbohydrate", carb);
					foodList.addFoodItem(newFood);
					foodTable1.update(foodList);
					count++;
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Enter a positive number.");
					alert.showAndWait();
				}
				return;
			} 
		});

		importFoodList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				foodList.loadFoodItems(file.getText());
				foodTable1.update(foodList);
			} 
		});

		saveFoodList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {    	
				foodList.saveFoodItems(file.getText());
			} 
		});

		analyzeFood.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//maybe change to new food list they add food to -teague
				List<FoodItem> foods = foodList.getAllFoodItems();
				int cal = 0;   
				int fib = 0;   
				int fat = 0;
				int prot = 0;
				for(FoodItem food : foods) {
					cal += food.getNutrientValue("calories");
					fib += food.getNutrientValue("fiber");
					fat += food.getNutrientValue("fat");
					prot += food.getNutrientValue("prot");
				}
				calorieCount.setText(Integer.toString(cal));
				fiberCount.setText(Integer.toString(fib));
				proteinCount.setText(Integer.toString(prot));
				fatCount.setText(Integer.toString(fat));
			} 
		});

		Group group = new Group(mainTitle, foodItems,importFoodList,saveFoodList,addFoodTitle,
				foodName, calories, fiber, fat, protein, file, yourMeal, mealCalories,
				mealFiber, mealFat, mealProtein, calorieCount, fiberCount, fatCount, proteinCount, carbs,
				analyzeFood, tableView.start(), foodTable1.start(), foodTable2.start());
		Scene scene = new Scene(group, 1000, 900);           // Add canvas to panes
		applicationStage.setTitle("Meal Planner"); // Set window's title
		applicationStage.setScene(scene);          // Set window's scene
		applicationStage.show();                   // Display window
		return;
	}

	public static void main(String [] args) {
		launch(args); // Launch application
		return;
	}
}
