package com.techelevator;

import com.techelevator.util.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTests {

    @Test
    public void testGetMessage() {
        Product gum = new Gum();
        Assert.assertEquals("Chew Chew, Yum!", gum.getMessage());

        Product drink = new Drink();
        Assert.assertEquals("Glug Glug, Yum!", drink.getMessage());

        Product candy = new Candy();
        Assert.assertEquals("Munch Munch, Yum!", candy.getMessage());

        Product chip = new Chip();
        Assert.assertEquals("Crunch Crunch, Yum!", chip.getMessage());

    }

    @Test
    public void testGettersAndSetters() {
        Product product = new Gum();
        product.setName("Test Product");
        product.setPrice(new BigDecimal("9.99"));
        product.setQuantity(5);

        Assert.assertEquals("Test Product", product.getName());
        Assert.assertEquals(new BigDecimal("9.99"), product.getPrice());
        Assert.assertEquals(5, product.getQuantity());
    }
}