package lab1.zuulRefactored;
/*
 * Class Item - an item in an adventure game.
 *
 * This class is a modification of the "World of Zuul" application in
 * order to solve exercises 7.20 7.21 7.22 in Objects First With Java page 204. 
 * 
 * An  "Item" has a description and a weight.
 * 
 * @author  Poul Henriksen
 * @version 1.0 (February 2005)
 */
public class Item
{
    private String description;
    private double weight;
    
    /**
     * Create a new item with the given description and weight.
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
    
    public String getDescription() {
        return description;
    }
}
