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
        // TODO : Complete
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
            while ((line = br.readLine()) != null || line != ",,,,,,,,,,,") {

                String[] data = line.split(",");
                FoodItem newFood = new FoodItem(data[0],data[1]);
                for(int i = 2; i < 12; i+=2 ) {
                	newFood.addNutrient(data[i], Double.parseDouble(data[i+1]));
                }
                foodItemList.add(newFood);

            }

        } 
        catch (FileNotFoundException e) {
            System.err.print("File does not exist!");
        } 
        catch (IOException e) {
            e.printStackTrace();         
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
    	
    	newList = foodItemList;
        for (String rule : rules) {
        	String[] ruleArray = rule.split("\\s+");
        	for(FoodItem food : foodItemList) {
            	if(food.getNutrientValue(ruleArray[0]) < (Double.parseDouble(ruleArray[1]))
            			&& food.getNutrientValue(ruleArray[0]) > (Double.parseDouble(ruleArray[2]))) {
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
			e.printStackTrace();
		} 
		finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
