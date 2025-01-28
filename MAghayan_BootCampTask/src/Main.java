import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        Inventory inventory = new Inventory();

        // Adding items to test functionality
        inventory.addItem(generateRandomItem("Gold Sword")); // Generating the rarity randomly
        inventory.addItem(generateRandomItem("Gold Sword"));
        inventory.addItem(generateRandomItem("Diamond Axe"));
        inventory.addItem(new Item("Diamond Shield", Rarity.COMMON)); // Creating Manually
        inventory.addItem(new Item("Iron Sword", Rarity.COMMON));
        inventory.addItem(new Item("Iron Sword", Rarity.COMMON));
        inventory.addItem(new Item("Stone Sword", Rarity.GREAT));
        inventory.addItem(new Item("Wool Armor", Rarity.EPIC));
        inventory.addItem(new Item("Iron Sword", Rarity.RARE));
        inventory.addItem(new Item("Iron Sword", Rarity.EPIC));
        inventory.addItem(new Item("Leather Armor", Rarity.LEGENDARY));

        // To see initial Inventory
        inventory.displayInventory();

        // Save Inventory
        // Save to a specific location
        inventory.saveToFile("inventoryy.dat");




        try {
            // Upgrading an item from Common
            Item sword = new Item("Iron Sword", Rarity.COMMON);
            inventory.addItem(sword);
            System.out.println("\nUpgrade Iron Sword to Great:");
            inventory.upgradeItem(sword);  // Should succeed

            // Adding enough items for upgrading
            inventory.addItem(new Item("Iron Sword", Rarity.GREAT));
            System.out.println("\nUpgrade Iron Sword to Rare...");
            inventory.upgradeItem(sword);  // Should fail as we don't have enough items

            // Adding more RARE items to upgrade to Epic
            Item swordRare = new Item("Iron Sword", Rarity.RARE);
            inventory.addItem(swordRare);
            inventory.addItem(new Item("Iron Sword", Rarity.RARE));
            System.out.println("\nUpgrade Iron Sword to Epic...");
            inventory.upgradeItem(swordRare);  // Should upgrade to Epic

            // Upgrade from Epic
            Item swordEpic = new Item("Iron Sword", Rarity.EPIC);
            System.out.println("\nUpgrade Iron Sword to Epic1...");
            inventory.upgradeItem(swordEpic); // Should fail as we only have 2 Epics

            // Upgrade
            Item armour = new Item("Wool Armor", Rarity.COMMON);
            inventory.addItem(armour);
            inventory.upgradeItem(armour); // Should fail as we don't have enough

            // Removing some items
            System.out.println("\nRemoving Iron Sword...");
            inventory.removeItems("Iron Sword", Rarity.COMMON, 2);  // Remove 2 Iron Sword items

            // Removing a non-existent item (should throw an error)
            System.out.println("\nRemoving a non-existent item...");
            inventory.removeItems("Nonexistent Sword", Rarity.COMMON, 1);

            // Display updated inventory
            inventory.displayInventory();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Save inventory
        inventory.saveToFile("finalInventoryy.dat");

        // Load initial Inventory
        Inventory loadedInventory = Inventory.loadFromFile("finalInventoryy.dat");
        loadedInventory.displayInventory();
    }

    // Random item generation with weighted probabilities
    public static Item generateRandomItem(String name) {
        double random = Math.random();
        Rarity rarity;

        if (random < 0.5) {
            rarity = Rarity.COMMON;
        } else if (random < 0.75) {
            rarity = Rarity.GREAT;
        } else if (random < 0.9) {
            rarity = Rarity.RARE;
        } else if (random < 0.98) {
            rarity = Rarity.EPIC;
        } else {
            rarity = Rarity.LEGENDARY;
        }

        return new Item(name, rarity);
    }
}