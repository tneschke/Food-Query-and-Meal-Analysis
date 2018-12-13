/**
 * Filename:   Main.java
 * Project:    Meal Analysis M3
 * Authors:    Kiara Mutschler, Teague Neschke, Wes Koerner, Nathan Frank, Sneha Polishetty 
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002 (Sneha, Wes) & 001 (Kiara, Teague, Nathan) 
 * 
 * Due Date:   Before 10pm on December 12, 2018
 * Version:    1.0
 * 
 * Credits:    none
 * 
 * Bugs:       no known bugs, but not complete either
 */
package application;

import java.util.List;
import javafx.application.Application;
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

/**
 * This class is the main program driver than launches the meal analysis
 * program. The class allows the scene, stage, controls, buttons, etc., visible.
 * 
 * @author Kiara Mutschler, Teague Neschke, Wes Koerner, Nathan Frank, Sneha
 *         Polishetty
 */
public class Main extends Application {
	private int count = 0;
	private FoodTableSample foodTable2;

	@Override
	/**
	 * This method implements the stage, multiple buttons, both the food list and
	 * meal analysis tables, text fields for adding a new fooditem, BPtrees for all
	 * the nutrients, etc.
	 * 
	 * @param Stage
	 *            applicationStage
	 * @return none
	 */
	public void start(Stage applicationStage) {
		FoodData foodList = new FoodData();
		FoodData myFood = new FoodData();

		BPTree proteinTree = new BPTree(4);
		BPTree fatTree = new BPTree(4);
		BPTree fiberTree = new BPTree(4);
		BPTree calorieTree = new BPTree(4);
		BPTree carbsTree = new BPTree(4); 

		TableViewSample tableView = new TableViewSample();

		foodTable2 = new FoodTableSample(applicationStage, 220, 475, foodList);
		FoodTableSample foodTable1 = new FoodTableSample(applicationStage, 220, 70, foodList);

		//implementation of buttons that will be used in this program
		Button importFoodList = new Button("Import Food List");
		Button saveFoodList = new Button("Save Food List");
		Button addFoodTitle = new Button("Add New Food"); // button done
		Button analyzeFood = new Button("Analyze Food");
		Button addToMyList = new Button("Add Food to My Meal");
		Button delfromMyList = new Button("Delete Food From my List"); 

		analyzeFood.setLayoutX(10);
		analyzeFood.setLayoutY(500);
		importFoodList.setLayoutX(10);
		importFoodList.setLayoutY(50);
		addFoodTitle.setLayoutX(10);
		addFoodTitle.setLayoutY(420);
		saveFoodList.setLayoutX(120);
		saveFoodList.setLayoutY(50);
		addToMyList.setLayoutX(800);
		addToMyList.setLayoutY(430);
		delfromMyList.setLayoutX(320);
		delfromMyList.setLayoutY(50);
		
		Text mainTitle = new Text(10, 25, "Meal Planner");
		Text foodItems = new Text(400, 50, "Food Items");
		Text yourMeal = new Text(400, 460, "Your Meal");

		// text characteristics for the text headers
		mainTitle.setFont(Font.font("ComicSans", FontWeight.BOLD, 25));
		mainTitle.setFill(Color.DARKSLATEGREY);
		foodItems.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
		foodItems.setFill(Color.DARKSLATEGREY);
		yourMeal.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
		yourMeal.setFill(Color.DARKSLATEGREY);

		// the following code creates textfields where a user can input in order to
		// create a new food item
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

		TextField fat = new TextField();
		fat.setMaxWidth(80);
		fat.setLayoutX(210);
		fat.setLayoutY(380);
		fat.setText("Enter Fat");

		TextField carbs = new TextField();
		carbs.setMaxWidth(80);
		carbs.setLayoutX(310);
		carbs.setLayoutY(380);
		carbs.setText("Enter Carbs");

		TextField fiber = new TextField();
		fiber.setMaxWidth(80);
		fiber.setLayoutX(410);
		fiber.setLayoutY(380);
		fiber.setText("Enter Fiber");

		TextField protein = new TextField();
		protein.setMaxWidth(80);
		protein.setLayoutX(510);
		protein.setLayoutY(380);
		protein.setText("Enter Protein");

		TextField file = new TextField();
		file.setMaxWidth(200);
		file.setLayoutX(10);
		file.setLayoutY(85);
		file.setText("Enter File Name/Path"); //foodItems.csv for this program 

		TextField addFood = new TextField();
		addFood.setPrefWidth(200);
		addFood.setLayoutX(800);
		addFood.setLayoutY(390);
		addFood.setText("Food Name");
		
		TextField removeFood = new TextField();
		removeFood.setPrefWidth(200);
		removeFood.setLayoutX(320);
		removeFood.setLayoutY(500);
		removeFood.setText("Food Name");

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
					//gets the user input and then converts to a double value
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
					carb = Double.parseDouble(carbsNum);

					//checks that input values are not negative
					if (prot >= 0 && fa >= 0 && fib >= 0 && cal >= 0) {
						newFood = new FoodItem(Integer.toString(count), food);
						newFood.addNutrient("calories", cal);
						newFood.addNutrient("fiber", fib);
						newFood.addNutrient("carbohydrate", carb);
						newFood.addNutrient("fat", fa);
						newFood.addNutrient("protein", prot);
						foodList.addFoodItem(newFood);
						foodTable1.update(foodList);
						count++;
					} else {
						Alert alert = new Alert(AlertType.ERROR, "Enter a positive number.");
						alert.showAndWait();
					}
					return;
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR, "Enter all information for a new food.");
					alert.showAndWait();
				}
			}
		});

		//this button allows the inputed file to be presented onto the food table
		importFoodList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				foodList.loadFoodItems(file.getText());
				foodTable1.update(foodList);
			}
		});

		//this button allows user to save their imported food list
		saveFoodList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				foodList.saveFoodItems(file.getText());
			}
		});

		//this button allows user to add a new food item onto the meal analysis table
		addToMyList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (FoodItem food : foodList.filterByName(addFood.getText())) {
					myFood.addFoodItem(food);
				}
				foodTable2.update(myFood);
			}
		});
		
		//this button allows user to remove a food item from the meal analysis table
		delfromMyList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (FoodItem food : foodList.filterByName(removeFood.getText())) {
					myFood.removeFoodItem(food);
				}
				foodTable2.update(myFood);
			}
		});
		
		//this button sums up the total nutrient values present in the meal analysis table 
		analyzeFood.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// maybe change to new food list they add food to -teague
				List<FoodItem> foods = foodList.getAllFoodItems();
				int cal = 0;
				int fib = 0;
				int fat = 0;
				int prot = 0;
				for (FoodItem food : foods) {
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

		Group group = new Group(mainTitle, foodItems, importFoodList, saveFoodList, addFoodTitle, foodName, addFood,
				addToMyList, delfromMyList, calories, fiber, fat, protein, file, yourMeal, mealCalories, mealFiber, mealFat,
				mealProtein, calorieCount, fiberCount, fatCount, proteinCount, carbs, analyzeFood, tableView.start(),
				foodTable1.start(), foodTable2.start());
		Scene scene = new Scene(group, 2000, 900); // Add canvas to panes
		applicationStage.setTitle("Meal Planner"); // Set window's title
		applicationStage.setScene(scene); // Set window's scene
		applicationStage.show(); // Display window
		return;
	}

	public static void main(String[] args) {
		launch(args); // Launch application
		return;
	}
}
