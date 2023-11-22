import java.util.Random;
public abstract class Item {
    private String name;
    private double price;
    private int amount;

    public Item(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount(){
        return amount;
    }

    //To access the field amount.
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public static Item[] generateRandomItems() {
        Random random = new Random();
        Item[] items = new Item[5]; // Assuming 5 different items

        for (int i = 0; i < items.length; i++) {
            if (i == 0) {
                items[i] = new Hat("Hat_", random.nextDouble(1,3) * 2, random.nextInt(0,50) + 1);
            }
            else if (i == 1) {
                items[i] = new Vest("Vest_", random.nextDouble(1,3) * 4, random.nextInt(0,50) + 1);
            }
            else if (i == 2) {
                items[i] = new Potion("Potion_", random.nextDouble(1,3) * 1, random.nextInt(0,50) + 1);
            }
            else if (i == 3) {
                items[i] = new Weapon("Weapon_", random.nextDouble(1,3) * 5, random.nextInt(0,50) + 1);
            }
            else {
                items[i] = new Shoes("Shoes_", random.nextDouble(1,3) * 3, random.nextInt(0,50) + 1);
            }
        }

        return items;
    }
}
