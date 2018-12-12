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
        

        // ComboBox<String> calorieBox = new ComboBox<String>();
        // calorieBox.getItems().add("<");
        // calorieBox.getItems().add(">");
        // calorieBox.getItems().add("=");
        //
        // ComboBox<String> fiberBox = new ComboBox<String>();
        // fiberBox.getItems().add("<");
        // fiberBox.getItems().add(">");
        // fiberBox.getItems().add("=");
        //
        // ComboBox<String> fatMinBox = new ComboBox<String>();
        // fatMinBox.getItems().add("<");
        // fatMinBox.getItems().add(">");
        // fatMinBox.getItems().add("=");
        //
        // ComboBox<String> proteinMinBox = new ComboBox<String>();
        // proteinMinBox.getItems().add("<");
        // proteinMinBox.getItems().add(">");
        // proteinMinBox.getItems().add("=");

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

        // gridPane.add(calorieBox, 0, 1);
        gridPane.add(fiber, 0, 2);
        gridPane.add(fiberMinText, 0, 3);
        gridPane.add(fiberMaxText, 1, 3);
        gridPane.add(fiberExactText, 2, 3);

        // gridPane.add(fiberBox, 0, 3); //edited
        gridPane.add(fat, 0, 4);
        gridPane.add(fatMinText, 0, 5);
        gridPane.add(fatMaxText, 1, 5);
        gridPane.add(fatExactText, 2, 5);

        gridPane.add(carbs, 0, 6);
        gridPane.add(carbsMinText, 0, 7);
        gridPane.add(carbsMaxText, 1, 7);
        gridPane.add(carbsExactText, 2, 7);

        // gridPane.add(fatMinBox,0 , 5);
        gridPane.add(protein, 0, 8);
        gridPane.add(proteinMinText, 0, 9);
        gridPane.add(proteinMaxText, 1, 9);
        gridPane.add(proteinExactText, 2, 9);
        gridPane.add(foodSearch, 0, 10);
        gridPane.add(analyzeButton, 1, 10);
        // gridPane.add(proteinMinBox, 0, 7);


        analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> rules = new ArrayList<String>();
                List<FoodItem> foods = new ArrayList<FoodItem>();
                double minCalories, maxCalories, exactCalories;
                double minFiber, maxFiber, exactFiber;
                double minFat, maxFat, exactFat;
                double minProtein, maxProtein, exactProtein;
                double minCarbs, maxCarbs, exactCarbs;
                // rules
                String calMinRule, calMaxRule, calExactRule;
                String fiberMinRule, fiberMaxRule, fiberExactRule;
                String fatMinRule, fatMaxRule, fatExactRule;
                String proteinMaxRule, proteinMinRule, proteinExactRule;
                String carbsMaxRule, carbsMinRule, carbsExactRule;

                try {
                    
                    clearCal(calorieMinText.getText(),calorieExactText.getText(),calorieMaxText.getText());
                    clearProtein(proteinMinText.getText(),proteinExactText.getText(),proteinMaxText.getText());
                    clearFiber(fiberMinText.getText(),fiberExactText.getText(),fiberMaxText.getText());
                    clearCarbs(carbsMinText.getText(),carbsExactText.getText(),carbsMaxText.getText());
                    clearFat(fatMinText.getText(),fatExactText.getText(),fatMaxText.getText());



//                    calorieMinText.clear();
//                    calorieMaxText.clear();
//                    calorieExactText.clear();
//                    fiberMinText.clear();
//                    fiberMaxText.clear();
//                    fiberExactText.clear();
//                    fatMinText.clear();
//                    fatMaxText.clear();
//                    fatExactText.clear();
//                    carbsMinText.clear();
//                    carbsMaxText.clear();
//                    carbsExactText.clear();
//                    proteinMinText.clear();
//                    proteinMaxText.clear();
//                    proteinExactText.clear();

                    if (calorieMinText != null) {
                        minCalories = Double.parseDouble(calorieMinText.getText());
                        calMinRule = "calories >= " + minCalories;
                        rules.add(calMinRule);
                    }
                    if (calorieMaxText != null) {
                        maxCalories = Double.parseDouble(calorieMinText.getText());
                        calMaxRule = "calories <= " + maxCalories;
                        rules.add(calMaxRule);
                    }
                    if (calorieExactText != null) {
                        exactCalories = Double.parseDouble(calorieMinText.getText());
                        calExactRule = "calories == " + exactCalories;
                        rules.add(calExactRule);
                    }
                    //fiber
                    if (fiberMinText != null) {
                        minFiber = Double.parseDouble(fiberMinText.getText());
                       fiberMinRule = "fiber >= " + minFiber;
                        rules.add(fiberMinRule);
                    }
                    if (fiberMaxText != null) {
                        maxFiber = Double.parseDouble(fiberMinText.getText());
                        fiberMaxRule = "fiber <= " + maxFiber;
                        rules.add(fiberMaxRule);
                    }
                    if (fiberExactText != null) {
                        exactFiber = Double.parseDouble(fiberMinText.getText());
                        fiberExactRule = "fiber == " + exactFiber;
                        rules.add(fiberExactRule);
                    }

                    if (fatMinText != null) {
                        minFat = Double.parseDouble(fatMinText.getText());
                       fatMinRule = "fat >= " + minFat;
                        rules.add(fatMinRule);
                    }
                    if (fatMaxText != null) {
                        maxFat = Double.parseDouble(fatMinText.getText());
                        fatMaxRule = "fat <= " + maxFat;
                        rules.add(fatMaxRule);
                    }
                    if (fatExactText != null) {
                        exactFat = Double.parseDouble(fatMinText.getText());
                        fatExactRule = "fat == " + exactFat;
                        rules.add(fatExactRule);
                    }
                    if (carbsMinText != null) {
                        minCarbs = Double.parseDouble(carbsMinText.getText());
                        carbsMinRule = "carbs >= " + minCarbs;
                        rules.add(carbsMinRule);
                    }
                    if (carbsMaxText != null) {
                        maxCarbs = Double.parseDouble(carbsMinText.getText());
                        carbsMaxRule = "carbs <= " + maxCarbs;
                        rules.add(carbsMaxRule);
                    }
                    if (carbsExactText != null) {
                        exactCarbs = Double.parseDouble(carbsMinText.getText());
                        carbsExactRule = "carbs == " + exactCarbs;
                        rules.add(carbsExactRule);
                    } 
                    if (proteinMinText != null) {
                        minProtein = Double.parseDouble(proteinMinText.getText());
                        proteinMinRule = "protein >= " + minProtein;
                        rules.add(proteinMinRule);
                    }
                    if (proteinMaxText != null) {
                        maxProtein = Double.parseDouble(proteinMinText.getText());
                        proteinMaxRule = "protein <= " + maxProtein;
                        rules.add(proteinMaxRule);
                    }
                    if (proteinExactText != null) {
                        exactProtein = Double.parseDouble(proteinMinText.getText());
                        proteinExactRule = "calories == " + exactProtein;
                        rules.add(proteinExactRule);
                    }

    
                    foods = data.filterByNutrients(rules);
                } catch (Exception e) {
                    //Alert alert = new Alert(AlertType.ERROR, "Please enter valid numbers only.");
                    //alert.showAndWait();
                }
            }
        });


        gridPane.setLayoutX(10);
        gridPane.setLayoutY(120);
        // calories fiber fat carbs protein
        return gridPane;
    }
    private void clearCal(String calorieMinText, String calorieExactText, String calorieMaxText) {
        if(calorieMaxText.equals("Max Calories")) {
            calorieMaxText = null;
        }
        if(calorieMinText.equals("Min Calories")) {
            calorieMinText = null;
        }
        if(calorieExactText.equals("ExactCalories")) {
            calorieExactText = null;
        }
    }
        private void clearFiber(String fiberMinText, String fiberExactText, String fiberMaxText) {
            if(fiberMaxText.equals("Max Fiber")) {
                fiberMaxText = null;
            }
            if(fiberMinText.equals("Min Fiber")) {
                fiberMinText = null;
            }
            if(fiberExactText.equals("Exact Fiber")) {
                fiberExactText = null;
            }
        }
        private void clearFat(String fatMinText, String fatExactText, String fatMaxText) {
            if(fatMaxText.equals("Max Fat")) {
                fatMaxText = null;
            }
            if(fatMinText.equals("Min Fat")) {
                fatMinText = null;
            }
            if(fatExactText.equals("Exact Fat")) {
                fatExactText = null;
            }
        }
        private void clearCarbs(String carbsMinText, String carbsExactText, String carbsMaxText) {
            if(carbsMaxText.equals("Max Carbs")) {
                carbsMaxText = null;
            }
            if(carbsMinText.equals("Min Carbs")) {
                carbsMinText = null;
            }
            if(carbsExactText.equals("Exact Carbs")) {
                carbsExactText = null;
            }
        }
        private void clearProtein(String proteinMinText, String proteinExactText, String proteinMaxText) {
            if(proteinMaxText.equals("Max Protein")) {
                proteinMaxText = null;
            }
            if(proteinMinText.equals("Min Protein")) {
                proteinMinText = null;
            }
            if(proteinExactText.equals("Exact Protein")) {
                proteinExactText = null;
            }
        }
        

}
