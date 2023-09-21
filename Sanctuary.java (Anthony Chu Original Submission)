/*
 * Name: Anthony Chu
 * Email: abc004@ucsd.edu
 * PID: A17496679
 * Sources Used: Non
 *
 * The file gathered the methods to 
 * implement the Sanctuary class
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A class with Sanctuary object's method
 */
public class Sanctuary {
    HashMap<String, Integer> sanctuary;
    // Container to store all the animal species in the sanctuary. 
    private final int maxAnimals;
    // /The maximum number of animals that the sanctuary can support.
    private final int maxSpecies;
    //The maximum number of species that the sanctuary can support.

    /**
     * Constructor initialize the HashMap with no elements. 
     *  
     * @param maxAnimals the max animals
     * @param maxSpecies the max species
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals <= 0 || maxSpecies <= 0 || maxAnimals < maxSpecies) {
            throw new IllegalArgumentException();
        }
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
        sanctuary = new HashMap<>(maxSpecies);
    }

    /**
     * Return the number of animals in the sanctuary 
     * that are of the given species. 
     * 
     * @param species the speicies
     * @return number of animals in the sanctuary 
     * that are of the given species
     */
    public int countForSpecies(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        }
        if (sanctuary.containsKey(species)==false) {
            return 0;
        }
        return sanctuary.get(species);
    }

    /**
     * Return the total number of animals in the sanctuary.
     * 
     * @return total number of animals in the sanctuary.
     */
    public int getTotalAnimals() {
        int result = 0;
        for (String key : sanctuary.keySet()) {
            result += sanctuary.get(key);
        }
        return result;
    }

    /**
     * Return the total number of species in the sanctuary
     * 
     * @return total number of species in the sanctuary
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * Return the maximum allowed number of animals in the sanctuary.
     * 
     * @return maximum allowed number of animals in the sanctuary.
     */
    public int getMaxAnimals() {
        return maxAnimals;
    }
    
    /**
     * Return the maximum allowed number of species in the sanctuary.	
     * 
     * @return maximum allowed number of species in the sanctuary.	
     */
    public int getMaxSpecies() {
        return maxSpecies;
    }

    /**
     * If it does not exceed the maxAnimals nor maxSpecies of the sanctuary, 
     * add num animals of species to the sanctuary. 
     * If adding num animals exceeds the maximum limit, 
     * add as many animals as permitted. 
     * 
     * @param species the species
     * @param num number of animals
     * @return the number of animals that could not be rescued.
     */
    public int rescue(String species, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        if (species == null){
            throw new IllegalArgumentException();
        }
        if (getTotalAnimals() < this.getMaxAnimals() && getTotalSpecies() < this.getMaxSpecies()) {
            if (num > (this.getMaxAnimals() - this.getTotalAnimals())) {
                int output = num - this.getMaxAnimals() + this.getTotalAnimals();
                sanctuary.put(species, this.getMaxAnimals());
                return output;
            }
            if (sanctuary.containsKey(species)==false) {
                sanctuary.put(species, num);
                return 0;
            }
            sanctuary.put(species, sanctuary.get(species) + num );
            return 0;
        }
        return num;
    }

    /**
     * Remove num animals of species from the sanctuary. 
     * Remove the species from the sanctuary if its remaining count reaches 0.
     * 
     * @param species the species
     * @param num number of animals
     */
    public void release(String species, int num) {
        if (num <= 0 || num > sanctuary.get(species) ||
                species == null || sanctuary.containsKey(species)==false) {
            throw new IllegalArgumentException();
        }
        if (num == sanctuary.get(species)) {
            sanctuary.remove(species);
        } else {
            sanctuary.put(species, sanctuary.get(species) - num);
        }
    }

    
}
