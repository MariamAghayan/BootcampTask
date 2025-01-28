# BootcampTask Inventory Management System
This project is a Java-based Inventory Management System that simulates item storage, upgrades, and management for a role-playing game. The system features serialization to save and load inventory data. Below is a detailed explanation of the project's classes, methods, and functionalities.
Features

•	Add, remove, and retrieve items based on rarity and name.

•	Upgrade items through predefined rules.

•	Save and load inventory data using serialization.

•	Display the inventory in a structured format.

How to Compile and Run
1.	Make sure you have a Java IDE (like IntelliJ IDEA or Eclipse) installed on your computer.
2.	Open the project folder in your IDE.
3.	Build the project using your IDE's build option.
4.	Run the Main class to execute the program.

Classes and Methods

1. Main
   
This is the entry point of the program.

•	main(String[] args): Initializes the inventory, adds test data, performs upgrades, removes items, saves the inventory, and demonstrates the functionality.

•	generateRandomItem(String name): Generates a random item with a specified name and weighted rarity distribution.

3. Inventory
   
Manages the collection of items and provides methods for inventory operations.

Fields:

•	Map<Rarity, List<Item>> items: A map storing lists of items, categorized by their rarity.

Methods:

•	Inventory(): Initializes the inventory with empty lists for each rarity.

•	addItem(Item item): Adds an item to the inventory based on its rarity.

•	removeItems(String name, Rarity rarity, int count): Removes a specified number of items with the given name and rarity. Throws ItemNotFoundException if items are not found.

•	upgradeItem(Item item): Upgrades an item based on its rarity and predefined rules.

•	upgradeOtherItems(Item item, String name, Rarity rarity): Handles upgrades for items that are not Epic or Legendary.

•	upgradeEpicItem(Item item, String name, Rarity rarity): Handles upgrades for Epic items, including progression to Legendary rarity.

•	getItemsByRarityAndName(Rarity rarity, String name): Retrieves items with a specific name and rarity. Throws ItemNotFoundException if no items are found.

•	displayInventory(): Prints the inventory, categorized by rarity.

•	saveToFile(String filename): Saves the inventory to a specified file using serialization.

•	loadFromFile(String filename): Loads inventory data from a specified file.

5. Item
   
Represents an individual item in the inventory.

Fields:

•	String name: The name of the item.

•	Rarity rarity: The rarity of the item.

•	int upgradeCount: Tracks the number of upgrades for Epic items.

Methods:

•	Item(String name, Rarity rarity): Constructs an item with the given name and rarity.

•	getName(): Returns the item's name.

•	getRarity(): Returns the item's rarity.

•	getUpgradeCount(): Returns the item's upgrade count (for Epic items).

•	setName(String name): Sets the item's name.

•	setRarity(Rarity rarity): Sets the item's rarity.

•	updateUpgradeCount(): Increments the item's upgrade count.

•	resetUpgradeCount(): Resets the upgrade count to zero.

•	toString(): Returns a string representation of the item, including upgrade count for Epic items.

7. Rarity

An enumeration representing the possible rarities of items:

•	COMMON

•	GREAT

•	RARE

•	EPIC

•	LEGENDARY

8. ItemNotFoundException

A custom exception for handling cases where requested items are not found in the inventory.
Constructor:

•	ItemNotFoundException(String message): Constructs an exception with a specified error message.
Design Choices and Assumptions

•	Items are categorized strictly by rarity.

•	Upgrades follow specific rules:

o	Common to Rare: Requires 3 items of the same rarity.

o	Epic: Requires multiple stages, with 3 items for the first two upgrades and 2 items for the final upgrade to Legendary.

o	Legendary: Cannot be upgraded further.

•	Serialization is used to save and load the inventory for persistence.

Files Saved

•	inventoryy.dat: Saves the inventory after initial operations.

•	finalInventoryy.dat: Saves the inventory after all operations.

