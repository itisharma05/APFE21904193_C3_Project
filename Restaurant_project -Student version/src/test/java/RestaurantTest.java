import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void commonTask(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant spyPlayer = Mockito.spy(restaurant);

        LocalTime currentTime = LocalTime.parse("19:00:00");

        Mockito.when(spyPlayer.getCurrentTime()).thenReturn(currentTime);
        boolean isOpen = spyPlayer.isRestaurantOpen();

        assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant spyPlayer = Mockito.spy(restaurant);

        LocalTime currentTime = LocalTime.parse("23:00:00");

        Mockito.when(spyPlayer.getCurrentTime()).thenReturn(currentTime);
        boolean isOpen = spyPlayer.isRestaurantOpen();

        assertFalse(isOpen);


    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<TotalOrderPrice>>>>>>>>>>>>>>>>>>>
    @Test
    public void when_no_item_are_selected_from_menu_then_total_price_should_be_returned_as_0(){
        List<String> selectedItemsName = new ArrayList<>();
        int expectedTotalOrderPrice = restaurant.totalOrderPrice(selectedItemsName);

        assertEquals(0,expectedTotalOrderPrice);
    }

    @Test
    public void when_items_are_selected_from_menu_then_total_price_of_selected_items_should_be_returned(){
        restaurant.addToMenu("Red Sauce Pasta", 250);
        List<String> selectedItemsName = new ArrayList<>();
        selectedItemsName.add("Red Sauce Pasta");//250
        selectedItemsName.add("Sweet corn soup");//119
        selectedItemsName.add("Vegetable lasagne");//269

        int expectedTotalOrderPrice = restaurant.totalOrderPrice(selectedItemsName);

        assertEquals(638,expectedTotalOrderPrice);

    }
    //<<<<<<<<<<<<<<<<<<<<<<TotalOrderPrice>>>>>>>>>>>>>>>>>>>
}