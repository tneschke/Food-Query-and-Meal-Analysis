/**
 * Filename:   TableViewSample.java
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

/**
 * This class displays the food filtering options. This class allows the user to
 * apply nutrient filters to view foods with certain nutrients values based on
 * rules.
 * 
 * @author Kiara Mutschler, Teague Neschke, Wes Koerner, Nathan Frank, Sneha
 *         Polishetty
 */
public class TableViewSample {
	private FoodData data = new FoodData();

    public GridPane start() {
        //Initialize the nutrient labels
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

        Button analyzeButton = new Button("Filter List");
        
        //the following code below are the text fields for all possible filters that can be applied
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

        
        //add text fields to the gridPane
        gridPane.add(calorie, 0, 0);
        gridPane.add(calorieMinText, 0, 1);
        gridPane.add(calorieMaxText, 1, 1);
        gridPane.add(calorieExactText, 2, 1);

        gridPane.add(fiber, 0, 2);
        gridPane.add(fiberMinText, 0, 3);
        gridPane.add(fiberMaxText, 1, 3);
        gridPane.add(fiberExactText, 2, 3);

        gridPane.add(fat, 0, 4);
        gridPane.add(fatMinText, 0, 5);
        gridPane.add(fatMaxText, 1, 5);
        gridPane.add(fatExactText, 2, 5);

        gridPane.add(carbs, 0, 6);
        gridPane.add(carbsMinText, 0, 7);
        gridPane.add(carbsMaxText, 1, 7);
        gridPane.add(carbsExactText, 2, 7);

        gridPane.add(protein, 0, 8);
        gridPane.add(proteinMinText, 0, 9);
        gridPane.add(proteinMaxText, 1, 9);
        gridPane.add(proteinExactText, 2, 9);
        gridPane.add(foodSearch, 0, 10);
        gridPane.add(analyzeButton, 1, 10);

        analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> rules = new ArrayList<String>();
                List<FoodItem> foods = new ArrayList<FoodItem>();
                //the following are user inputs which will be converted to double values
                double minCalories, maxCalories, exactCalories;
                double minFiber, maxFiber, exactFiber;
                double minFat, maxFat, exactFat;
                double minProtein, maxProtein, exactProtein;
                double minCarbs, maxCarbs, exactCarbs;
                // the following are filter rules names
                String calMinRule, calMaxRule, calExactRule;
                String fiberMinRule, fiberMaxRule, fiberExactRule;
                String fatMinRule, fatMaxRule, fatExactRule;
                String proteinMaxRule, proteinMinRule, proteinExactRule;
                String carbsMaxRule, carbsMinRule, carbsExactRule;

                try {
                    //calls clear methods to check if user has entered a numeric value in the field 
                    String minCalVal = clearMinCal(calorieMinText.getText());
                    String maxCalVal = clearMaxCal(calorieMaxText.getText());
                    String exactCalVal = clearExactCal(calorieExactText.getText());
                    
                    String minCarbVal = clearMinCarbs(carbsMinText.getText());
                    String maxCarbVal = clearMaxCarbs(carbsMaxText.getText());
                    String exactCarbVal = clearExactCarbs(carbsExactText.getText());

                    String minFatVal = clearMinFat(fatMinText.getText());
                    String maxFatVal = clearMaxFat(fatMaxText.getText());
                    String exactFatVal = clearExactFat(fatExactText.getText());
                    
                    String minFiberVal = clearMinFiber(fiberMinText.getText());
                    String maxFiberVal = clearMaxFiber(fiberMaxText.getText());
                    String exactFiberVal = clearExactFiber(fiberExactText.getText());
                    
                    String minProteinVal = clearMinProtein(proteinMinText.getText());
                    String maxProteinVal = clearMaxProtein(proteinMaxText.getText());
                    String exactProteinVal = clearExactProtein(proteinExactText.getText());

                    // if user has enter value, create rule and add rule to list
                    if (minCalVal != null) {
                        minCalories = Double.parseDouble(minCalVal);
                        calMinRule = "calories >= " + minCalories;
                        rules.add(calMinRule);
                    }
                    if (maxCalVal != null) {
                        maxCalories = Double.parseDouble(maxCalVal);
                        calMaxRule = "calories <= " + maxCalories;
                        rules.add(calMaxRule);
                    }
                    if (exactCalVal != null) {
                        exactCalories = Double.parseDouble(exactCalVal);
                        calExactRule = "calories == " + exactCalories;
                        rules.add(calExactRule);
                    }
                    if (minFiberVal != null) {
                        minFiber = Double.parseDouble(minFiberVal);
                       fiberMinRule = "fiber >= " + minFiber;
                        rules.add(fiberMinRule);
                    }
                    if (maxFiberVal != null) {
                        maxFiber = Double.parseDouble(maxFiberVal);
                        fiberMaxRule = "fiber <= " + maxFiber;
                        rules.add(fiberMaxRule);
                    }
                    if (exactFiberVal != null) {
                        exactFiber = Double.parseDouble(exactFiberVal);
                        fiberExactRule = "fiber == " + exactFiber;
                        rules.add(fiberExactRule);
                    }

                    if (minFatVal != null) {
                        minFat = Double.parseDouble(minFatVal);
                       fatMinRule = "fat >= " + minFat;
                        rules.add(fatMinRule);
                    }
                    if (maxFatVal != null) {
                        maxFat = Double.parseDouble(maxFatVal);
                        fatMaxRule = "fat <= " + maxFat;
                        rules.add(fatMaxRule);
                    }
                    if (exactFatVal != null) {
                        exactFat = Double.parseDouble(exactFatVal);
                        fatExactRule = "fat == " + exactFat;
                        rules.add(fatExactRule);
                    }
                    if (minCarbVal != null) {
                        minCarbs = Double.parseDouble(minCarbVal);
                        carbsMinRule = "carbs >= " + minCarbs;
                        rules.add(carbsMinRule);
                    }
                    if (maxCarbVal != null) {
                        maxCarbs = Double.parseDouble(minCarbVal);
                        carbsMaxRule = "carbs <= " + maxCarbs;
                        rules.add(carbsMaxRule);
                    }
                    if (exactCarbVal != null) {
                        exactCarbs = Double.parseDouble(exactCarbVal);
                        carbsExactRule = "carbs == " + exactCarbs;
                        rules.add(carbsExactRule);
                    } 
                    if (minProteinVal != null) {
                        minProtein = Double.parseDouble(minProteinVal);
                        proteinMinRule = "protein >= " + minProtein;
                        rules.add(proteinMinRule);
                    }
                    if (maxProteinVal != null) {
                        maxProtein = Double.parseDouble(maxProteinVal);
                        proteinMaxRule = "protein <= " + maxProtein;
                        rules.add(proteinMaxRule);
                    }
                    if (exactProteinVal != null) {
                        exactProtein = Double.parseDouble(exactProteinVal);
                        proteinExactRule = "protein == " + exactProtein;
                        rules.add(proteinExactRule);
                    }

    
                    foods = data.filterByNutrients(rules);
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR, "Please enter valid numbers only.");
                    alert.showAndWait();
                }
            }
        });

        gridPane.setLayoutX(10);
        gridPane.setLayoutY(120);
        return gridPane;
    }
   
    		//the following methods check if the user has entered a value in the text field
        private String clearMinCal(String calorieMinText) {
        if(calorieMinText.equals("Min Calories")) {
            calorieMinText = null;
        }
        return calorieMinText;
        }
        private String clearMaxCal(String calorieMaxText) {
            if(calorieMaxText.equals("Max Calories")) {
                calorieMaxText = null;
                return calorieMaxText;
            }
            return calorieMaxText;
            }
        
        private String clearMaxCarbs(String carbsMaxText) {
            if(carbsMaxText.equals("Max Carbs")) {
                carbsMaxText = null;
                
            }
            return carbsMaxText;
            }
        private String clearMaxFiber(String fiberMaxText) {
            if(fiberMaxText.equals("Max Fiber")) {
                fiberMaxText = null;
                
            }
            return fiberMaxText;
            }
        private String clearMaxFat(String fatMaxText) {
            if(fatMaxText.equals("Max Fat")) {
                fatMaxText = null;
                
            }
            return fatMaxText;
            }
        private String clearMaxProtein(String proteinMaxText) {
            if(proteinMaxText.equals("Max Protein")) {
                proteinMaxText = null;
                
            }
            return proteinMaxText;
            }
        
        private String clearMinFiber(String fiberMinText) {
            if(fiberMinText.equals("Min Fiber")) {
               fiberMinText = null;
            }
            return fiberMinText;
            }
        private String clearMinCarbs(String carbsMinText) {
            if(carbsMinText.equals("Min Carbs")) {
                carbsMinText = null;
            }
            return carbsMinText;
            }
        private String clearMinFat(String fatMinText) {
            if(fatMinText.equals("Min Fat")) {
                fatMinText = null;
            }
            return fatMinText;
            }
        private String clearMinProtein(String proteinMinText) {
            if(proteinMinText.equals("Min Protein")) {
                proteinMinText = null;
            }
            return proteinMinText;
            }
        
        private String clearExactCal(String calMinText) {
            if(calMinText.equals("Exact Calories")) {
                calMinText = null;
            }
            return calMinText;
            }
        private String clearExactCarbs(String carbsExactText) {
            if(carbsExactText.equals("Exact Carbs")) {
                carbsExactText = null;
            }
            return carbsExactText;
            }
        private String clearExactFat(String fatExactText) {
            if(fatExactText.equals("Exact Fat")) {
                fatExactText = null;
            }
            return fatExactText;
            }
        private String clearExactFiber(String fiberExactText) {
            if(fiberExactText.equals("Exact Fiber")) {
                fiberExactText = null;
            }
            return fiberExactText;
            }
        
        private String clearExactProtein(String proteinExactText) {
            if(proteinExactText.equals("Exact Protein")) {
                proteinExactText = null;
            }
            return proteinExactText;
            }  

}