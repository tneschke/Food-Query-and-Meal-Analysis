import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
 
public class TableViewSample {
    public GridPane start() {
        final Label calorie = new Label("Calories");
        calorie.setFont(new Font("Comic Sans", 12));
        final Label fiber = new Label("Fiber");
        fiber.setFont(new Font("Comic Sans", 12));
        final Label fat = new Label("Fat");
        fat.setFont(new Font("Comic Sans", 12));
        final Label protein = new Label("Protein");
        protein.setFont(new Font("Comic Sans", 12));
        
        GridPane gridPane = new GridPane();

        ComboBox<String> calorieBox = new ComboBox<String>();
        calorieBox.getItems().add("<");
        calorieBox.getItems().add(">");
        calorieBox.getItems().add("=");

        ComboBox<String> fiberBox = new ComboBox<String>();
        fiberBox.getItems().add("<");
        fiberBox.getItems().add(">");
        fiberBox.getItems().add("=");

        ComboBox<String> fatBox = new ComboBox<String>();
        fatBox.getItems().add("<");
        fatBox.getItems().add(">");
        fatBox.getItems().add("=");
        
        ComboBox<String> proteinBox = new ComboBox<String>();
        proteinBox.getItems().add("<");
        proteinBox.getItems().add(">");
        proteinBox.getItems().add("=");
        
        TextField calorieText = new TextField();
        calorieText.setPrefColumnCount(10);
        calorieText.setEditable(true);
        calorieText.setText("Enter Calories");
       
        TextField fiberText = new TextField();
        fiberText.setPrefColumnCount(10);
        fiberText.setEditable(true);
        fiberText.setText("Enter Fiber");
        
        TextField fatText = new TextField();
        fatText.setPrefColumnCount(10);
        fatText.setEditable(true);
        fatText.setText("Enter Protein");
        
        TextField proteinText = new TextField();
        proteinText.setPrefColumnCount(10);
        proteinText.setEditable(true);
        proteinText.setText("Enter Protein");
        
        gridPane.add(calorie, 0, 0);
        gridPane.add(calorieBox, 0, 1);
        gridPane.add(fiber, 0, 2);
        gridPane.add(fiberBox, 0, 3); //edited
        gridPane.add(fat, 0, 4);
        gridPane.add(fatBox,0 , 5);
        gridPane.add(protein, 0, 6);
        gridPane.add(proteinBox, 0, 7);
        gridPane.add(calorieText, 1, 1);
        gridPane.add(fiberText, 1, 3);
        gridPane.add(fatText, 1, 5);
        gridPane.add(proteinText, 1, 7);
        
        gridPane.setLayoutX(10);
        gridPane.setLayoutY(120);
        //calories fiber fat protein
        return gridPane;
    }
}