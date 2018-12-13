

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FoodTableSample {
    private  TableView<TableItem> table;
    private ObservableList<TableItem> data;
    private Stage stage;
    private int x;
    private int y;
    final HBox hb;
    private TableColumn<TableItem, ?> addItemcCol;
    
    public FoodTableSample(Stage stage, int x, int y,FoodData foods) {
    	
        this.x = x;
        this.y = y; 
        table = new TableView<>();
//        data = 
//        		FXCollections.observableArrayList(new TableItem("Turnip", "20", "3", "2", "5"), new TableItem("Carrot", "20", "7", "8", "9")
//        		,new TableItem("Turnip", "20", "3", "2", "5"), new TableItem("Turnip", "20", "3", "2", "5"), new TableItem("Turnip", "20", "3", "2", "5"),
//        		new TableItem("Turnip", "20", "3", "2", "5"), new TableItem("Turnip", "20", "3", "2", "5"), new TableItem("Turnip", "20", "3", "2", "5"),
//        		new TableItem("Turnip", "20", "3", "2", "5"));
        data = FXCollections.observableArrayList();
        List<FoodItem> foodList = foods.getAllFoodItems();
    	data.add(new TableItem("Turnip", "20", "20", "20", "20" ,"20"));
    	System.out.print(foodList.size());
        for(FoodItem food : foodList) {
        	TableItem tableItem = new TableItem(food.getName(), String.valueOf(food.getNutrientValue("calories")),
        			String.valueOf(food.getNutrientValue("fat")),String.valueOf(food.getNutrientValue("fiber")),
        			String.valueOf(food.getNutrientValue("protein")),String.valueOf(food.getNutrientValue("carbohydrate")));
        	data.add(tableItem);	
        }
        hb = new HBox();
        this.stage = stage;
    }
    
    
    public void update(FoodData foods) {
    	data = FXCollections.observableArrayList();
        List<FoodItem> foodList = foods.getAllFoodItems();
    	
        for(FoodItem food : foodList) {
        	TableItem tableItem = new TableItem(food.getName(), String.valueOf(food.getNutrientValue("calories")),
        			String.valueOf(food.getNutrientValue("fat")),String.valueOf(food.getNutrientValue("fiber")),
        			String.valueOf(food.getNutrientValue("protein")),String.valueOf(food.getNutrientValue("carbohydrate")));
        	data.add(tableItem);	
        }
        table.setItems(data);
    }
    

    public TableView<TableItem> start() {
        Scene scene = new Scene(new Group());
        final Label label = new Label("Food Items");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        table.setMaxHeight(300);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
        new PropertyValueFactory<>("name"));

        TableColumn caloriesCol = new TableColumn("Calories");
        caloriesCol.setMinWidth(100);
         caloriesCol.setCellValueFactory(
         new PropertyValueFactory<>("calories"));

        TableColumn fiberCol = new TableColumn("Fiber");
        fiberCol.setMinWidth(100);
         fiberCol.setCellValueFactory(
         new PropertyValueFactory<>("fiber"));

        TableColumn fatCol = new TableColumn("Fat");
        fatCol.setMinWidth(100);
         fatCol.setCellValueFactory(
         new PropertyValueFactory<>("fat"));

        TableColumn proteinCol = new TableColumn("Protein");
        proteinCol.setMinWidth(100);
         proteinCol.setCellValueFactory(
         new PropertyValueFactory<>("protein"));
         
         TableColumn carbsCol = new TableColumn("Carbs");
         carbsCol.setMinWidth(100);
          carbsCol.setCellValueFactory(
          new PropertyValueFactory<>("carbs"));
        
        TableColumn<TableItem, Boolean> actionCol = new TableColumn<>("Add/Delete");
        actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TableItem, Boolean>, ObservableValue<Boolean>>(){
            @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TableItem, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        
    actionCol.setCellFactory(new Callback<TableColumn<TableItem, Boolean>, TableCell<TableItem, Boolean>>() {
            @Override public TableCell<TableItem, Boolean> call(TableColumn<TableItem, Boolean> foodItemBoolTableCol) {
                return new AddItemCell(stage, table);
        }
        });

         TableColumn addItemCol = new TableColumn("Add Item");
         addItemCol.setMinWidth(100);

        table.setItems(data);
        table.getColumns().addAll(nameCol, caloriesCol, fiberCol, fatCol, proteinCol, carbsCol, 
        		actionCol);

        final TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(nameCol.getPrefWidth());
        final TextField addCalories = new TextField();
        addCalories.setMaxWidth(caloriesCol.getPrefWidth());
        addCalories.setPromptText("Calories");
        final TextField addFiber = new TextField();
        addFiber.setMaxWidth(fiberCol.getPrefWidth());
        addFiber.setPromptText("Fiber");
        final TextField addFat = new TextField();
        addFat.setMaxWidth(fatCol.getPrefWidth());
        addFat.setPromptText("Fat");
        final TextField addProtein = new TextField();
        addProtein.setMaxWidth(proteinCol.getPrefWidth());
        addProtein.setPromptText("Protein");
        final TextField addCarbs = new TextField();
        addCarbs.setMaxWidth(carbsCol.getPrefWidth());
        addCarbs.setPromptText("Carbs");

        final Button addButton = new Button("Add New Food");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new TableItem(addName.getText(), addCalories.getText(), addFiber.getText(),
                        addFat.getText(), addProtein.getText(), addCarbs.getText()));
                addName.clear();
                addCalories.clear();
                addFiber.clear(); 
                addFat.clear();
                addProtein.clear(); 
                addCarbs.clear(); 
            }
        });

        hb.getChildren().addAll(addName, addCalories, addFiber, addFat, addProtein, addCarbs, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(3);
        vbox.setPadding(new Insets(7, 0, 0, 7));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        table.setLayoutX(x+150);
        table.setLayoutY(y);
        return table;
    }
    
    private class AddItemCell extends TableCell<TableItem, Boolean> {
        final Button addButton = new Button("+/-");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        
        AddItemCell(final Stage stage, final TableView table) {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(addButton);
            addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    buttonY.set(mouseEvent.getScreenY());;
                }
            });
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            }
    }

    public static class TableItem {
        private String name;
        private String calories;
        private String fiber;
        private String fat;
        private String carbs;
        private String protein;

        private TableItem(String name, String calories, String fiber, String fat, String protein, String carbs) {
            this.name = name;
            this.calories = calories;
            this.fiber = fiber;
            this.fat = fat;
            this.protein = protein;
            this.carbs = carbs;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCalories() {
            return this.calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public String getFiber() {
            return this.fiber;
        }

        public void setFiber(String fiber) {
            this.fiber = fiber;
        }

        public String getFat() {
            return this.fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getProtein() {
            return this.protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }
        
        public String getCarbs() {
            return this.carbs;
        }
    }
}
