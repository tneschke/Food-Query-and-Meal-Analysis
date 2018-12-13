

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {

	// List of all the food items.
	private List<FoodItem> foodItemList;

	// Map of nutrients and their corresponding index
	private HashMap<String, BPTree<Double, FoodItem>> indexes;


	/**
	 * Public constructor
	 */
	public FoodData() {
		indexes = new HashMap<String, BPTree<Double, FoodItem>>();
		foodItemList = new LinkedList<FoodItem>();
		BPTree<Double, FoodItem> calories = new BPTree<Double, FoodItem>(3);
        BPTree<Double, FoodItem> fat = new BPTree<Double, FoodItem>(3);
        BPTree<Double, FoodItem> fiber = new BPTree<Double, FoodItem>(3);
        BPTree<Double, FoodItem> protein = new BPTree<Double, FoodItem>(3);
        BPTree<Double, FoodItem> carbs = new BPTree<Double, FoodItem>(3);
        
        
        indexes.put("calories", calories);
        indexes.put("fat", fat);
        indexes.put("fiber", fiber);
        indexes.put("protein", protein);
        indexes.put("carbohydrate", carbs);
	}


	/*
	 * (non-Javadoc)
	 * @see skeleton.FoodDataADT#loadFoodItems(java.lang.String)
	 */
	@Override
	public void loadFoodItems(String filePath) {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null && line.length() > 14) {
				String[] data = line.split(",");
				FoodItem newFood = new FoodItem(data[0],data[1]);

				for(int i = 2; i < 12; i+=2 ) {
					newFood.addNutrient(data[i], Double.parseDouble(data[i+1]));
					indexes.get(data[i]).insert(Double.parseDouble(data[i+1]), newFood);
				}
				foodItemList.add(newFood);
			}
		} 
		catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR, "Please enter a valid file.");
			alert.showAndWait();
		} 
		catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, "An error occurred within this file.");
			alert.showAndWait();         
		} 
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see skeleton.FoodDataADT#filterByName(java.lang.String)
	 */
	@Override
	public List<FoodItem> filterByName(String substring) {
		List<FoodItem> newList = new LinkedList<FoodItem>();
		for(FoodItem food : foodItemList) {
			if(food.getName().contains(substring)) newList.add(food);
		}
		return newList;
	}

	/*
	 * (non-Javadoc)
	 * @see skeleton.FoodDataADT#filterByNutrients(java.util.List)
	 */
	@Override
	public List<FoodItem> filterByNutrients(List<String> rules) {
		
    	List<FoodItem> newList = new LinkedList<FoodItem>();
    	for(FoodItem food : foodItemList) {
    		newList.add(food); 
    	}
    	List<FoodItem> tempList = new LinkedList<FoodItem>();
        for (String rule : rules) {
        	String[] ruleArray = rule.split("\\s+");
        	System.out.println(ruleArray[0]);
        	System.out.println(ruleArray[1]);
        	System.out.println(ruleArray[2]);
        	tempList = indexes.get(ruleArray[0]).rangeSearch(Double.parseDouble(ruleArray[2]), ruleArray[1]);
//	        	for(FoodItem food : foodItemList) {
//	        		newList = indexes.get(key)
//	            	if(food.getNutrientValue(ruleArray[0]) < (Double.parseDouble(ruleArray[1]))
//	            			&& food.getNutrientValue(ruleArray[0]) > (Double.parseDouble(ruleArray[2]))) {
//	            		newList.remove(food);
//	            	}
//	            }
        	System.out.println(newList.size());

        	System.out.println(tempList.size());
        	
        	FoodItem[] foodArray = newList.toArray(new FoodItem[newList.size()]);
        	
        	for (FoodItem food : foodArray) {
        		if(!tempList.contains(food)) {
        			newList.remove(food);
        		}
        	}
        	
        }
        return newList;
    }


	/*
	 * (non-Javadoc)
	 * @see skeleton.FoodDataADT#addFoodItem(skeleton.FoodItem)
	 */
	@Override
	public void addFoodItem(FoodItem foodItem) {
		foodItemList.add(foodItem);
		indexes.get("calories").insert(foodItem.getNutrientValue("calories"), foodItem);
        indexes.get("fat").insert(foodItem.getNutrientValue("fat"), foodItem);
        indexes.get("protein").insert(foodItem.getNutrientValue("protein"), foodItem);
        indexes.get("carbohydrate").insert(foodItem.getNutrientValue("carbohydrate"), foodItem);
        indexes.get("fiber").insert(foodItem.getNutrientValue("fiber"), foodItem);
	}

	/*
	 * (non-Javadoc)
	 * @see skeleton.FoodDataADT#getAllFoodItems()
	 */
	@Override
	public List<FoodItem> getAllFoodItems() {
		return foodItemList;

	}


	@Override
	public void saveFoodItems(String filename) {
		//creates new identical list
		List<FoodItem> newList = new LinkedList<FoodItem>();
		for(FoodItem food : foodItemList) {
			newList.add(food);
		}

		//sorts list by name of foods
		Collections.sort(newList, new Comparator<FoodItem>() {
			@Override
			public int compare(FoodItem one, FoodItem two) {
				return one.getName().compareTo(two.getName());
			}
		});

		//saves list to csv file
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filename);
			for (FoodItem food : newList) {
				fileWriter.append((food.getID()));
				fileWriter.append(",");
				fileWriter.append(food.getName());

				HashMap<String,Double> nutrients = food.getNutrients();
				for (Map.Entry<String, Double> entry : nutrients.entrySet()) {
					fileWriter.append(",");
					fileWriter.append(entry.getKey());
					fileWriter.append(",");
					fileWriter.append(String.valueOf(entry.getValue()));			    
				}
				fileWriter.append("\n");
			}

		} 
		catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "An error occurred saving this file. "
					+ "Make sure it is a valid file path!");
			alert.showAndWait(); 
		} 
		finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR, "An error occurred closing or finding this file. "
						+ "Make sure it is a valid file path!");
				alert.showAndWait(); 
			}
		}
	}
}
