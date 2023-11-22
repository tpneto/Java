import java.text.DecimalFormat;

    public class City {
        private String name;
        private Item[] items;


        public City(String name) {
            this.name = name;
            this.items = Item.generateRandomItems();

        }

        public String getName() {
            return name;
        }

        public Item[] getItems() {

            return items;
        }

        public void viewCityItems() {
            DecimalFormat df = new DecimalFormat("#.##"); //To decimal;

            System.out.println("City: " + name);

            for (int i = 0; i < items.length; i++) {
                System.out.println("Item: " + items[i].getName());
                System.out.println("Price: $ " + df.format(items[i].getPrice()));
                System.out.println("Amount: " + items[i].getAmount() + "\n");
            }
        }

        public boolean sellItem(Item item, int quantityToSell) {
            for (Item cityItem : items) {
                if (cityItem.getName().equals(item.getName())) {
                    if (cityItem.getAmount() >= quantityToSell) {
                        cityItem.setAmount(cityItem.getAmount() - quantityToSell);
                        return true; // Item sold.
                    }
                }
            }
            return false; // Not enough in stock.
        }

        public void updateItemStock(Item item, int quantity) {

            for (int i = 0; i < items.length; i++) {
                if (items[i] != null && items[i].getName().equals(item.getName())) {
                    int currentAmount = items[i].getAmount();
                    items[i].setAmount(currentAmount + quantity); // Increase the amount of items.
                    return;
                }
            }
        }

    }

