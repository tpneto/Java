import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String playerName;
        String cityToTravel;
        String answer;
        int choice;
        Game map = new Game();
        Merchant merchant = new Merchant("City_1",500, map);
        City[] cities = map.getCities();
        City chosenCity = null;
        City currentCity = null;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Smuggler Game.");
        System.out.println("Please insert your name.");
        playerName = scanner.nextLine();
        System.out.println("Greetings, Mr(s) " + playerName + ".");

        while (!merchant.isGameOver())  {
            // Implement the game loop here, including user interaction.

            System.out.println("Select one option:");
            System.out.println("1. Check inventory;");
            System.out.println("2. Merchant status;");
            System.out.println("3. Travel to new city;");
            System.out.println("4. Buy an item;");
            System.out.println("5. Sell an item;");
            System.out.println("6. Quit game.");

            choice = scanner.nextInt();

            switch (choice) {
                case 1: //Merchant status.
                {
                    System.out.println("Merchant status: ");
                    merchant.displayInventory();
                    break;
                }
                case 2: //Display Inventory.
                {
                    System.out.println("Merchant status: ");
                    merchant.displayStatus();
                    break;
                }
                case 3: // Travel.
                {
                    boolean travelCompleted = false;

                    System.out.println("Please select the city to travel.");
                    System.out.println("List: ");
                    for (int i = 0; i < cities.length; i++) {
                        System.out.println(cities[i].getName());
                    }

                    scanner.nextLine();
                    cityToTravel = scanner.nextLine();

                    for (City city : cities) {
                        if (city.getName().equals(cityToTravel)) {
                            System.out.println("Traveling to: " + cityToTravel + "\n");
                            merchant.travelToCity(city);
                            travelCompleted = true; // Trip completed.
                            break; // Stop after travel.
                        }
                    }
                    if (!travelCompleted) {
                        System.out.println("Invalid city name.\n");
                    }
                    break;
                }
                case 4: //Buy.
                {
                    for (City city : cities) {
                        if (city.getName().equals(merchant.currentCity)) {
                            currentCity = city;
                            break;
                        }
                    }

                    if (currentCity != null) {
                        currentCity.viewCityItems();
                        System.out.println("Do you want to buy something?\nY: Yes N: no\n");
                        scanner.nextLine();
                        answer = scanner.nextLine();

                        if (answer.equalsIgnoreCase("N")) {
                            System.out.println("Ok. Bye!");
                        } else if (answer.equalsIgnoreCase("Y")) {

                            System.out.println("Select the number of the item:\n" + "(0) Hat_\n" +
                                    "(1) Vest_\n" + "(2) Potion_\n" + "(3) Weapon_\n" + "(4) Shoes_\n");
                            int chosenItem = scanner.nextInt();

                            if (chosenItem >= 0 && chosenItem < currentCity.getItems().length) {
                                Item selectedItem = currentCity.getItems()[chosenItem]; // Use the index numbers to access the items.
                                int amount = selectedItem.getAmount(); // Get the amount of items.


                                System.out.println("Selected item: " + selectedItem.getName());
                                if (amount > 0) {
                                    System.out.println("Enter the quantity to buy:");
                                    int quantityToBuy = scanner.nextInt();

                                    if (quantityToBuy <= amount) {
                                        double totalPrice = quantityToBuy * selectedItem.getPrice();

                                        if (totalPrice <= merchant.getMoney()) {
                                            // Buy the item.
                                            merchant.buyItem(selectedItem, quantityToBuy, currentCity);
                                            System.out.println("Purchase successful!");
                                        } else {
                                            System.out.println("Not enough money to buy this quantity.");
                                        }
                                    } else {
                                        System.out.println("Not enough of this item in the city.");
                                    }
                                } else {
                                    System.out.println("This item is out of stock.");
                                }
                            } else {
                                System.out.println("Invalid item number.");
                            }

                        } else {
                            System.out.println("Invalid option.");
                        }
                    } else {
                        System.out.println("Invalid city.");
                    }
                    break;
                }
                case 5: // Sell.
                {
                    for (City city : cities) {
                        if (city.getName().equals(merchant.currentCity)) {
                            currentCity = city;
                            break;
                        }
                    }

                    if (currentCity != null) {
                        merchant.displayInventory();
                        System.out.println("Select the number of the item to sell:\n" + "(0) Hat_\n" +
                                "(1) Vest_\n" + "(2) Potion_\n" + "(3) Weapon_\n" + "(4) Shoes_\n");

                        int chosenItem = scanner.nextInt();

                        if (chosenItem >= 0 && chosenItem < merchant.getInventory().length) {
                            Item selectedItem = merchant.getInventory()[chosenItem];
                            int amount = selectedItem.getAmount();

                            System.out.println("Selected item: " + selectedItem.getName());
                            if (amount > 0) {
                                System.out.println("Enter the quantity to sell:");
                                int quantityToSell = scanner.nextInt();

                                if (quantityToSell <= amount) {
                                    double totalPrice = quantityToSell * selectedItem.getPrice();

                                    // Sell the item.
                                    merchant.sellItem(selectedItem, quantityToSell, currentCity);
                                } else {
                                    System.out.println("Not enough of this item in your inventory.");
                                }
                            } else {
                                System.out.println("You don't have this item in your inventory.");
                            }
                        } else {
                            System.out.println("Invalid item number.");
                        }
                    } else {
                        System.out.println("Invalid city.");
                    }
                    break;
                }
                case 6: //Exit game.
                {
                    System.out.println("Quitting the game...");
                    System.exit(0);
                    break;
                }
                default: //Error handling.
                {
                    System.out.println("Invalid option. Please try again.");
                    break;
                }
            }
        }
        System.out.println("You lost!");
    }
}

