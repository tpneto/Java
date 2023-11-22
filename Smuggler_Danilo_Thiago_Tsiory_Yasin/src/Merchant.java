import java.util.Random;
import java.text.DecimalFormat;
public class Merchant {
    String currentCity;
    double money;
    Item[] inventory;
    int caughtCount;
    double fine;
    int amountOfItems;
    private City[] cities;
    private Game travelTo;

    public Merchant(String startingCity, double money, Game travelTo) {
        this.currentCity = startingCity;
        this.money = money;
        this.inventory = new Item[5]; // Maximum 5 items.
        this.caughtCount = 0;
        this.fine = 100;
        this.amountOfItems = 0;
        this.cities = travelTo.getCities();
        this.travelTo = travelTo;



        inventory[0] = new Hat("Hat_",2,1);
        inventory[1] = new Vest("Vest_",4,1);
        inventory[2] = new Potion("Potion_",1,5);
        inventory[3] = new Weapon("Weapon_",5,1);
        inventory[4] = new Shoes("Shoes_",3,1);

    }
    public double getMoney(){
        return money;
    }
    public Item[] getInventory(){ return inventory;}
    public void travelToCity(City city) {
        City[] cities = travelTo.getCities();
        Random random = new Random();
        int randomCityIndex = random.nextInt(cities.length);

        // Update the inventory.
        int amountOfItems = 0;
        for (int i = 0; i < inventory.length; i++) {
            amountOfItems += inventory[i].getAmount();
        }

        if (amountOfItems < 10) {
            currentCity = city.getName();
            System.out.println("You travelled to: " + currentCity + "\n");
        } else {
            int chanceToGetCaught = random.nextInt(10) + 1; // Used to calculate the chance to get caught.
            int randomItemIndex = random.nextInt(inventory.length);
            Item randomItem = inventory[randomItemIndex];

            if (amountOfItems >= 10 && amountOfItems < 20 && chanceToGetCaught == 1) {
                // Chance to get caught: 10%.
                System.out.println("You got CAUGHT!");
                caughtCount += 1;

                applyPenalty(randomItem);
                moveToRandomCity(cities[randomCityIndex], "You were sent to: ");

            } else if (amountOfItems >= 20 && amountOfItems < 30 && (chanceToGetCaught == 1 || chanceToGetCaught == 2)) {
                // Chance to get caught: 20%.
                System.out.println("You got CAUGHT!");
                caughtCount += 1;

                applyPenalty(randomItem);
                moveToRandomCity(cities[randomCityIndex], "You were sent to: ");

            } else if (amountOfItems >= 30 && amountOfItems < 40 && (chanceToGetCaught == 1 || chanceToGetCaught == 2 || chanceToGetCaught == 3)) {
                // Chance to get caught: 30%.
                System.out.println("You got CAUGHT!");
                caughtCount += 1;

                applyPenalty(randomItem);
                moveToRandomCity(cities[randomCityIndex], "You were sent to: ");

            } else if (amountOfItems >= 40 && (chanceToGetCaught >= 1 && chanceToGetCaught <= 4)) {
                // Chance to get caught 40%.
                System.out.println("You got CAUGHT!");
                caughtCount += 1;

                applyPenalty(randomItem);
                moveToRandomCity(cities[randomCityIndex], "You were sent to: ");

            } else {
                // If nothing happened during the trip.
                currentCity = city.getName();
                System.out.println("You travelled to: " + currentCity + "\n");
            }
        }
    } //Move the merchant from city to city.
    public void sellItem(Item item, int quantityToSell, City city) {

        double totalPrice = quantityToSell * item.getPrice();

        money += totalPrice; // Uptade the merchant's money.

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getName().equals(item.getName())) {
                int currentAmount = inventory[i].getAmount();
                if (currentAmount >= quantityToSell) {
                    inventory[i].setAmount(currentAmount - quantityToSell); // Update the merchan's inventory.
                    city.updateItemStock(item, quantityToSell); // Update the city's stock.
                    System.out.println("Sale successful!");
                } else {
                    System.out.println("Not enough of this item in your inventory.");
                }
                return;
            }
        }
        System.out.println("Item not found in your inventory.");

    } // Update the inventory and money after selling something.
    public void buyItem(Item item, int quantityToBuy, City city) {
        double totalPrice = item.getPrice() * quantityToBuy;

        if (money >= totalPrice && city.sellItem(item, quantityToBuy)) {
            money -= totalPrice;
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] != null && inventory[i].getName().equals(item.getName())) {
                    inventory[i].setAmount(inventory[i].getAmount() + quantityToBuy);
                    break;
                }
            }
        }
    } // Update the inventory and money after the purchase.
    public void displayInventory() {
        // Implement the logic for displaying the merchant's inventory.
        System.out.println("\nInventory:\n");
        for (int i = 0; i < inventory.length; i++) {
            System.out.println("Item; " + inventory[i].getName());
            System.out.println("Price: " + inventory[i].getPrice());
            System.out.println("Amount: " + inventory[i].getAmount());
        }
    } // Display the merchant's inventory.
    private void moveToRandomCity(City cities, String x) {
        currentCity = cities.getName();
        System.out.println(x + currentCity);
        System.out.println("");
    } // Move to random city if he gets caught.
    private void applyPenalty(Item randomItem) {
        randomItem.setAmount(0);
        System.out.println("Item: " + randomItem.getName() + " was confiscated.");
        System.out.println("Fine paid: $ " + fine);
        money = money - fine;
        fine += 50;
        System.out.println("Updated fine: $ " + fine);
        System.out.println("");
    } // Apply penalty if he gets caught.
    public void displayStatus() {
        // Implement the logic for displaying the merchant's current status (money, city, etc.).
        DecimalFormat df = new DecimalFormat("#.##"); //To decimal;
        System.out.println("\n" + "Current city: " + currentCity);
        System.out.println("Money: $ " + df.format(money));
        System.out.println("Fine amount: " + fine);
        System.out.println("Caught count: " + caughtCount + "\n");
    } // Display the merchant's status.
    public boolean isGameOver() {
//        System.out.println("You lost!");
        return money <= 0;
    }   // If the Merchant's money goes under 0, the game is over.



}



