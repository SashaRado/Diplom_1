package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;

    @Mock
    Ingredient firstIngredient, secondIngredient, thirdIngredient;

    @Test
    public void setBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void removeIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        burger.moveIngredient(1, 0);
        assertEquals(new ArrayList<>(Arrays.asList(secondIngredient, firstIngredient, thirdIngredient)), burger.ingredients);
    }

    @Test
    public void getPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.ingredients.add(firstIngredient);
        when(bun.getPrice()).thenReturn(10f);
        when(firstIngredient.getPrice()).thenReturn(20f);
        float actual = burger.getPrice();
        assertThat(actual, equalTo(40f));
    }

    @Test
    public void getReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.ingredients.add(firstIngredient);
        when(bun.getName()).thenReturn("Сладкая булочка");
        when(bun.getPrice()).thenReturn(100F);
        when(firstIngredient.getType()).thenReturn(FILLING);
        when(firstIngredient.getName()).thenReturn("Котлета");
        when(firstIngredient.getPrice()).thenReturn(500F);
        String expected = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", firstIngredient.getType().toString().toLowerCase(), firstIngredient.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", burger.getPrice());
        assertEquals(expected, burger.getReceipt());
    }
}