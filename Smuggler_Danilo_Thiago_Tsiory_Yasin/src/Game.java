public class Game {
    public City[] getCities()
    {
        //Initialize cities with random item prices and amount.
        City city1 = new City("City_1");
        City city2 = new City("City_2");
        City city3 = new City("City_3");
        City city4 = new City("City_4");

        City[] cities = {city1, city2, city3, city4};

        return cities;
    }

}
