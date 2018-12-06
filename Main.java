package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
    @Override
    public void start(Stage applicationStage) {
        TableViewSample tableView = new TableViewSample();
        FoodTableSample foodTable1 = new FoodTableSample(applicationStage,220,475);
        FoodTableSample foodTable2 = new FoodTableSample(applicationStage,220, 70);

        Button importFoodList = new Button("Import Food List");
        Button saveFoodList = new Button("Save Food List");
        Button addFoodTitle = new Button("Add New Food");
        Button analyzeFood = new Button("Analyze Food");
        
        analyzeFood.setLayoutX(10);
        analyzeFood.setLayoutY(500);
        importFoodList.setLayoutX(10);
        importFoodList.setLayoutY(80);
        addFoodTitle.setLayoutX(10);
        addFoodTitle.setLayoutY(420);
        saveFoodList.setLayoutX(140);
        saveFoodList.setLayoutY(80);

        Text mainTitle = new Text(10, 25, "Meal Planner");
        Text foodItems = new Text(400, 50, "Food Items");
        Text yourMeal = new Text(400, 460, "Your Meal");

        mainTitle.setFont(Font.font("ComicSans", FontWeight.BOLD, 25));
        mainTitle.setFill(Color.DARKSLATEGREY);
        foodItems.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
        foodItems.setFill(Color.DARKSLATEGREY);
        yourMeal.setFont(Font.font("ComicSans", FontWeight.BOLD, 18));
        yourMeal.setFill(Color.DARKSLATEGREY);
        
        TextField  foodName = new TextField("Food Name");
        foodName.setMaxWidth(80);
        foodName.setLayoutX(10);
        foodName.setLayoutY(380);
        
        TextField calories = new TextField("Enter Calories");
        calories.setMaxWidth(80);
        calories.setLayoutX(110);
        calories.setLayoutY(380);
        
        TextField fiber = new TextField("Enter Fiber");
        fiber.setMaxWidth(80);
        fiber.setLayoutX(210);
        fiber.setLayoutY(380);
        
        TextField fat = new TextField("Enter Fat");
        fat.setMaxWidth(80);
        fat.setLayoutX(310);
        fat.setLayoutY(380);
        
        TextField protein = new TextField("Enter Protein");
        protein.setMaxWidth(80);
        protein.setLayoutX(410);
        protein.setLayoutY(380);
        
        Text mealCalories = new Text(10, 550, "Calories");
        Text calorieCount = new Text(13, 565, "300");
        Text mealFiber = new Text(10, 600, "Fiber");
        Text fiberCount = new Text(13, 615, "18 g");
        Text mealFat = new Text(10, 650, "Fat");
        Text fatCount = new Text(13, 665, "18 g");
        Text mealProtein = new Text(10, 700, "Protein");
        Text proteinCount = new Text(13, 715, "1 g");
        
        Group group = new Group(mainTitle, foodItems,importFoodList,saveFoodList,addFoodTitle,
                foodName, calories, fiber, fat, protein, yourMeal, mealCalories,
                mealFiber, mealFat, mealProtein, calorieCount, fiberCount, fatCount, proteinCount,
                analyzeFood, tableView.start(), foodTable1.start(), foodTable2.start());
        Scene scene = new Scene(group, 900, 900);           // Add canvas to panes
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