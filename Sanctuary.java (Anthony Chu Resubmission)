
//file implements sanctuary
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Implementation of Sanctuary
 */
public class Sanctuary {
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;

    /**
     * get max animals
     * 
     * @return maxAnimals
     */
    public int getMaxAnimals() {
        return maxAnimals;
    }

    private final int maxSpecies;

    /**
     * get max species
     * 
     * @return maxSpecies
     */
    public int getMaxSpecies() {
        return maxSpecies;
    }

    /**
     * Sanctuary constructor
     * 
     * @param maxAnimals max animals
     * @param maxSpecies max species
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
     * count total animals
     * 
     * @return total animals
     */
    public int getTotalAnimals() {
        int result = 0;
        for (String key : sanctuary.keySet()) {
            result += sanctuary.get(key);
        }
        return result;
    }

    /**
     * get total species
     * 
     * @return total species
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * rescue species given number
     * 
     * @param species given species
     * @param num     given number
     * @return number of animals not rescued
     */
    public int rescue(String species, int num) {
        if (species == null || num <= 0) {
            throw new IllegalArgumentException();
        }
        if (!sanctuary.containsKey(species)) {
            if (getTotalSpecies() == getMaxSpecies() || getTotalAnimals() == getMaxAnimals()) {
                return num;
            }
            int diff = getMaxAnimals() - getTotalAnimals();
            if (num > diff) {
                sanctuary.put(species, diff);
                return num - diff;
            }
            sanctuary.put(species, num);
            return 0;
        }
        int diff = getMaxAnimals() - getTotalAnimals();
        if (num > diff) {
            sanctuary.put(species, countForSpecies(species) + diff);
            return num - diff;
        }
        sanctuary.put(species, countForSpecies(species) + num);
        return 0;
    }

    /**
     * release species given number
     * 
     * @param species given species
     * @param num     given number
     */
    public void release(String species, int num) {
        if (num <= 0 || num > countForSpecies(species)
                || species == null || !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        if (num == sanctuary.get(species)) {
            sanctuary.remove(species);
        } else {
            sanctuary.put(species, sanctuary.get(species) - num);
        }
    }

    /**
     * count number of animals in given species
     * 
     * @param species given speicies
     * @return number of animals given species
     */
    public int countForSpecies(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        }
        if (!sanctuary.containsKey(species)) {
            return 0;
        }
        return sanctuary.get(species);
    }
}
