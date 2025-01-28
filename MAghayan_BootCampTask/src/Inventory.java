import java.io.*;
import java.util.*;

public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;  // Added for serialization compatibility

    private final Map<Rarity, List<Item>> items;

    public Inventory() {
        items = new HashMap<>();
        for (Rarity rarity : Rarity.values()) {
            items.put(rarity, new ArrayList<>());
        }
    }

    // Adding items to Inventory
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item.");
        }
        items.get(item.getRarity()).add(item);
    }

    //Removing Items from Inventory
    public boolean removeItems(String name, Rarity rarity, int count) throws ItemNotFoundException {
        if (name == null || rarity == null || count <= 0) {
            throw new IllegalArgumentException("Invalid arguments for removing items.");
        }

        List<Item> itemList = items.get(rarity);
        int removed = 0;

        if (itemList != null) {
            List<Item> toRemove = new ArrayList<>();
            boolean itemFound = false;  // Track if we find items matching the name

            for (Item item : itemList) {
                if (item.getName().equals(name) && removed < count) {
                    itemFound = true;
                    toRemove.add(item);
                    removed++;
                }
            }

            if (!itemFound) {
                throw new ItemNotFoundException("No items with the name " + name + " and rarity " + rarity + " found.");
            }

            if (removed == count){
                itemList.removeAll(toRemove);
            }
        }

        return removed == count;
    }

    // Upgrades
    public void upgradeItem(Item item) throws ItemNotFoundException {
        Rarity rarityNow = item.getRarity();
        switch (rarityNow){
            case EPIC:
                upgradeEpicItem(item, item.getName(), rarityNow);
            case LEGENDARY:
                System.out.println("Cannot upgrade a Legendary Item.");
            default:
                upgradeOtherItems(item, item.getName(), rarityNow);

        }

    }

    // Upgrade for Basic Items
    public void upgradeOtherItems(Item item, String name, Rarity rarity) throws ItemNotFoundException {
        if (removeItems(name, rarity, 3)) {
            item.setRarity(Rarity.values()[rarity.ordinal() + 1]);
            addItem(item);
            System.out.println(name + " upgraded to " + item.getRarity());
        }
        else {
            System.out.println("Not enough items to upgrade " + name + ".");
        }
    }

    // Different Upgrade logic for Epic Items
    public void upgradeEpicItem(Item item, String name, Rarity rarity) throws ItemNotFoundException {
        if (item.getUpgradeCount() < 2) {
            if (removeItems(name, rarity, 3)) {
                item.updateUpgradeCount();
                addItem(item);
                System.out.println(name + " upgraded to Epic " + item.getUpgradeCount());
            }
            else {
                System.out.println("Not enough Epic items to upgrade " + name);
            }
        }
        else {
            if (removeItems(name, rarity, 2)) {
                item.setRarity(Rarity.LEGENDARY);
                item.resetUpgradeCount();
                addItem(item);
                System.out.println(name + " upgraded to Legendary");
            }
            else {
                System.out.println("Not enough Epic 2 items to get a Legendary " + name);
            }
        }
    }

    // For retrieving specific Items
    public List<Item> getItemsByRarityAndName(Rarity rarity, String name) throws ItemNotFoundException {
        if (name == null || rarity == null) {
            throw new IllegalArgumentException("Name and Rarity cannot be null");
        }

        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items.get(rarity)) {
            if (item.getName().equals(name)) {
                filteredItems.add(item);
            }
        }
        if (filteredItems.isEmpty()) {
            throw new ItemNotFoundException("No items found with name " + name + " and rarity " + rarity);
        }
        return filteredItems;
    }

    //Display
    public void displayInventory() {
        System.out.println("\nInventory:");
        for (Rarity rarity : Rarity.values()) {
            System.out.println(rarity + ":");
            for (Item item : items.get(rarity)) {
                System.out.println(item);
            }
        }
    }

    // Save inventory to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Inventory saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Load inventory from a file
    public static Inventory loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("Inventory loaded from file: " + filename);
            return (Inventory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
            return new Inventory();
        }
    }
}

