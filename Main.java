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
		
		
		foodTable2 = new FoodTableSample(applicationStage,370,550, foodList);
		FoodTableSample foodTable1 = new FoodTableSample(applicationStage,370, 70, foodList);
		Button delfromMyList = new Button("Delete Food from Meal");
		Button importFoodList = new Button("Import Food List");
		Button saveFoodList = new Button("Save Food List");
		Button addFoodTitle = new Button("Add New Food"); //button done
		Button analyzeFood = new Button("Analyze Meal");
		Button addToMyList = new Button("Add Food to Meal");

		analyzeFood.setLayoutX(10);
		analyzeFood.setLayoutY(520);
		importFoodList.setLayoutX(10);
		importFoodList.setLayoutY(50);
		addFoodTitle.setLayoutX(610);
		addFoodTitle.setLayoutY(420);
		saveFoodList.setLayoutX(140);
		saveFoodList.setLayoutY(50);
		addToMyList.setLayoutX(150);
		addToMyList.setLayoutY(620);
		delfromMyList.setLayoutX(300);
        delfromMyList.setLayoutY(620);
        
		Text mainTitle = new Text(10, 25, "Meal Planner");
		Text foodItems = new Text(550, 50, "Food Items");
		Text yourMeal = new Text(550, 530, "Your Meal");

		mainTitle.setFont(Font.font("ComicSans", FontWeight.BOLD, 25));
		mainTitle.setFill(Color.DARKSLATEGREY);
		foodItems.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
		foodItems.setFill(Color.DARKSLATEGREY);
		yourMeal.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
		yourMeal.setFill(Color.DARKSLATEGREY);

		TextField foodName = new TextField();
		foodName.setMaxWidth(80);
		foodName.setLayoutX(10);
		foodName.setLayoutY(420);
		foodName.setText("Food Name");

		TextField calories = new TextField();
		calories.setMaxWidth(80);
		calories.setLayoutX(110);
		calories.setLayoutY(420);
		calories.setText("Enter Calories");

		TextField fat = new TextField();
		fat.setMaxWidth(80);
		fat.setLayoutX(210);
		fat.setLayoutY(420);
		fat.setText("Enter Fat");

		TextField carbs = new TextField();
		carbs.setMaxWidth(80);
		carbs.setLayoutX(310);
		carbs.setLayoutY(420);
		carbs.setText("Enter Carbs");
		
		TextField fiber = new TextField();
		fiber.setMaxWidth(80);
		fiber.setLayoutX(410);
		fiber.setLayoutY(420);
		fiber.setText("Enter Fiber");

		TextField protein = new TextField();
		protein.setMaxWidth(80);
		protein.setLayoutX(510);
		protein.setLayoutY(420);
		protein.setText("Enter Protein");

		TextField file = new TextField();
		file.setMaxWidth(200);
		file.setLayoutX(10);
		file.setLayoutY(85);
		file.setText("Enter File Name/Path");
		
		TextField addFood = new TextField();
		addFood.setPrefWidth(100);
		addFood.setLayoutX(170);
		addFood.setLayoutY(580);
		addFood.setText("Food Name");
		
		TextField removeFood = new TextField();
        removeFood.setPrefWidth(100);
        removeFood.setLayoutX(330);
        removeFood.setLayoutY(580);
        removeFood.setText("Food Name");

		Text mealCalories = new Text(10, 580, "Total Calories");
		Text calorieCount = new Text(13, 595, "0");
		
		Text mealFat = new Text(10, 620, "Total Fats");
		Text fatCount = new Text(13, 635, "0 g");
		
		Text mealCarb = new Text(10, 660, "Total Carbs");
		Text carbCount = new Text(13, 675, "0 g");
		
		Text mealProtein = new Text(10, 740, "Total Protein");
		Text proteinCount = new Text(13, 755, "0 g");

		
		Text mealFiber = new Text(10, 700, "Total Fiber");
		Text fiberCount = new Text(13, 715, "0 g");

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
					carb = Double.parseDouble(carbsNum);
					
					if(prot >= 0 && fa >= 0 && fib >= 0 && cal >= 0) {
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
				} catch(Exception e){
					Alert alert = new Alert(AlertType.ERROR, "Enter all information for a new food.");
					alert.showAndWait();
				}
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
		
		addToMyList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				for(FoodItem food: foodList.filterByName(addFood.getText())){
					myFood.addFoodItem(food);
				}
				foodTable2.update(myFood);
			} 
		});
		delfromMyList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (FoodItem food : foodList.filterByName(removeFood.getText())) {
                    myFood.removeFoodItem(food);
                }
                foodTable2.update(myFood);
            }
        });

		analyzeFood.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//maybe change to new food list they add food to -teague
				List<FoodItem> eles = myFood.getAllFoodItems();
				double cal = 0;   
				double fib = 0;   
				double fat = 0;
				double prot = 0;
				double carb = 0;
				for(FoodItem food : eles) {
					cal += food.getNutrientValue("calories");
					fib += food.getNutrientValue("fiber");
					fat += food.getNutrientValue("fat");
					prot += food.getNutrientValue("protein");
					carb += food.getNutrientValue("carbohydrate");
				}
				calorieCount.setText(Double.toString(cal));
				fatCount.setText(Double.toString(fat) + " g");
				fiberCount.setText(Double.toString(fib) + " g");
				proteinCount.setText(Double.toString(prot) +" g");
				carbCount.setText(Double.toString(carb)+ " g");
			} 
		});

		Group group = new Group(mainTitle, foodItems,importFoodList,saveFoodList,addFoodTitle,
				foodName, addFood, removeFood, addToMyList, delfromMyList, calories, fiber, fat, protein, file, yourMeal, mealCalories,
				mealFiber, mealFat, mealProtein, calorieCount, fiberCount, fatCount, proteinCount, carbs,
				analyzeFood, carbCount, mealCarb, tableView.start(), foodTable1.start(), foodTable2.start());
		Scene scene = new Scene(group, 1900, 900);           // Add canvas to panes
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