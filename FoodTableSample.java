/**
 * Filename:   FoodTableSample.java
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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * This class creates the tables for presenting the food list and meal analysis
 * table.
 * 
 * @author Kiara Mutschler, Teague Neschke, Wes Koerner, Nathan Frank, Sneha
 *         Polishetty
 */
public class FoodTableSample {
	private final TableView<TableItem> table; // table object which is used for 2 tables in the program
	private ObservableList<TableItem> data; // will contain all food items from the fooditems list
	private Stage stage;
	private int x;
	private int y;
	final HBox hb;

	/**
	 * Constructor for FoodTableSample object.
	 */
	public FoodTableSample(Stage stage, int x, int y, FoodData foods) {
		 this.x = x;
	        this.y = y; 
	        table = new TableView<>();
	        data = FXCollections.observableArrayList();
	        List<FoodItem> foodList = foods.getAllFoodItems();
	        for(FoodItem food : foodList) {
	        	TableItem tableItem = new TableItem(food.getName(), String.valueOf(food.getNutrientValue("calories")),
	        			String.valueOf(food.getNutrientValue("fat")), String.valueOf(food.getNutrientValue("carbohydrate")), 
	        			String.valueOf(food.getNutrientValue("fiber")),
	        			String.valueOf(food.getNutrientValue("protein")));
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
        			String.valueOf(food.getNutrientValue("fat")),String.valueOf(food.getNutrientValue("carbohydrate")),
        			String.valueOf(food.getNutrientValue("fiber")),
        			String.valueOf(food.getNutrientValue("protein")));
        	data.add(tableItem);	
        }
        table.setItems(data);
    }
	
	/**
	 * This method creates the table dimensions, characteristics, and column headers
	 * in the table, which are the food item nutrients.
	 * 
	 * @param none
	 * @return TableView<TableItem>
	 */
	public TableView<TableItem> start() {
		Scene scene = new Scene(new Group());
		final Label label = new Label("Food Items");
		label.setFont(new Font("Arial", 20));
		table.setEditable(true);
		table.setMaxHeight(300);

		// the code below creates the coloumn headers of the table
		TableColumn<TableItem, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<TableItem, String> caloriesCol = new TableColumn<>("Calories");
		caloriesCol.setMinWidth(100);
		caloriesCol.setCellValueFactory(new PropertyValueFactory<>("calories"));

		TableColumn<TableItem, String> fatCol = new TableColumn<>("Fat");
		fatCol.setMinWidth(100);
		fatCol.setCellValueFactory(new PropertyValueFactory<>("fat"));

		TableColumn<TableItem, String> carbsCol = new TableColumn<>("Carbs");
		carbsCol.setMinWidth(100);
		carbsCol.setCellValueFactory(new PropertyValueFactory<>("carbs"));

		TableColumn<TableItem, String> fiberCol = new TableColumn<>("Fiber");
		fiberCol.setMinWidth(100);
		fiberCol.setCellValueFactory(new PropertyValueFactory<>("fiber"));

		TableColumn<TableItem, String> proteinCol = new TableColumn<>("Protein");
		proteinCol.setMinWidth(100);
		proteinCol.setCellValueFactory(new PropertyValueFactory<>("protein"));

		TableColumn<TableItem, String> addItemCol = new TableColumn<>("Add Item");
		addItemCol.setMinWidth(100);
		addItemCol.setCellValueFactory(new PropertyValueFactory<>("add item"));

		// creates a column where user can remove or add fooditem in the selected row
		TableColumn<TableItem, Boolean> actionCol = new TableColumn<>("Add/Delete");
		actionCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<TableItem, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<TableItem, Boolean> features) {
						return new SimpleBooleanProperty(features.getValue() != null);
					}
				});

		actionCol.setCellFactory(new Callback<TableColumn<TableItem, Boolean>, TableCell<TableItem, Boolean>>() {
			@Override
			public TableCell<TableItem, Boolean> call(TableColumn<TableItem, Boolean> foodItemBoolTableCol) {
				return new AddItemCell(stage, table);
			}
		});

		table.setItems(data);
		table.getColumns().addAll(nameCol, caloriesCol, fatCol, carbsCol, fiberCol, proteinCol, actionCol);

		final VBox vbox = new VBox();
		vbox.setSpacing(3);
		vbox.setPadding(new Insets(7, 0, 0, 7));
		vbox.getChildren().addAll(label, table, hb);

		((Group) scene.getRoot()).getChildren().addAll(vbox);
		table.setLayoutX(x + 150);
		table.setLayoutY(y);
		return table;
	}

	// creates the "+/-" button contained in each row of the food item
	private class AddItemCell extends TableCell<TableItem, Boolean> {
		final Button addButton = new Button("+/-");
		final StackPane paddedButton = new StackPane();
		final DoubleProperty buttonY = new SimpleDoubleProperty();

		AddItemCell(final Stage stage, final TableView table) {
			paddedButton.setPadding(new Insets(3));
			paddedButton.getChildren().add(addButton);
			addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					buttonY.set(mouseEvent.getScreenY());
					;
				}
			});
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			setGraphic(paddedButton);
		}
	}

	/**
	 * This class represents a Table Item with all of its properties.
	 */
	public static class TableItem {
		private String name;
		private String calories;
		private String fiber;
		private String fat;
		private String carbs;
		private String protein;

		private TableItem(String name, String calories, String fat, String carbs, String fiber, String protein) {
			this.name = name;
			this.calories = calories;
			this.fat = fat;
			this.fiber = fiber;
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

		public void setCarbs(String carbs) {
			this.carbs = carbs;
		}
	}
}
