import java.io.Serializable;

enum Rarity {
    COMMON, GREAT, RARE, EPIC, LEGENDARY
}
class Item implements Serializable {
    private static final long serialVersionUID = 1L;  // Added for serialization compatibility

    private String name;
    private Rarity rarity;
    private int upgradeCount;

    public Item(String name, Rarity rarity) {
        if (name == null || rarity == null) {
            throw new IllegalArgumentException("Item name and rarity cannot be null.");
        }
        this.name = name;
        this.rarity = rarity;
        this.upgradeCount = 0;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getUpgradeCount(){
        return upgradeCount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRarity(Rarity rarity){
        this.rarity = rarity;
    }

    public void updateUpgradeCount(){
        this.upgradeCount++;
    }

    public void resetUpgradeCount(){
        this.upgradeCount = 0;
    }

    @Override
    public String toString(){
        if (rarity == Rarity.EPIC) {
            return name +  " " + rarity + " " + upgradeCount;
        }
        return name + " " + rarity;
    }
  }
