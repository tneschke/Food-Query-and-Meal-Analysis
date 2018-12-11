package application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
 
public class TableViewSample {
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
       
        TextField fiberMinText = new TextField();
        fiberMinText.setPrefColumnCount(10);
        fiberMinText.setEditable(true);
        fiberMinText.setText("Min Fiber");
        
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
        //gridPane.add(calorieBox, 0, 1);
        gridPane.add(fiber, 0, 2);
        gridPane.add(fiberMinText, 0, 3);
        gridPane.add(fiberMaxText, 1, 3);
        //gridPane.add(fiberBox, 0, 3); //edited
        gridPane.add(fat, 0, 4);
        gridPane.add(fatMinText, 0, 5);
        gridPane.add(fatMaxText, 1, 5);
        //gridPane.add(fatMinBox,0 , 5);
        gridPane.add(protein, 0, 6);
        gridPane.add(proteinMinText, 0, 7);
        gridPane.add(proteinMaxText, 1, 7);
        gridPane.add(analyzeButton, 0,10 );
        //gridPane.add(proteinMinBox, 0, 7);
        
       //rules
        String calMinRule;
        String calMaxRule;
        String fiberMinRule;
        String fiberMaxRule;
        String fatMinRule;
        String fatMaxRule;
        
        String proteinRule;
        
//        if((caloriesMaxEvent == null && caloriesMinEvent != null) || minCal == maxCal) {
//            calMinRule = "calories == "+ minCal;
//            calMaxRule = calMinRule;
//        }
//        if(caloriesMaxEvent != null && caloriesMinEvent != null) {
//            calMinRule = "calories >= " + minCal;
//            calMaxRule = "calories <= " + maxCal;
//            
//        }
        
        
        //gridPane.add(fatText, 1, 5);
        
        gridPane.add(foodSearch,0,9);
        
        gridPane.setLayoutX(10);
        gridPane.setLayoutY(120);
        //calories fiber fat protein
        return gridPane;
    }
}