package uk.equalexperts.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import uk.equalexperts.model.Product;

public class ShoppingCartServiceTest {

	@Test
	public void shoppingCartShouldHaveFiveDoveSoapsWithRightPrice() {
	 //arrange
	 final ShoppingCartService shoppingCart=new ShoppingCartService();
	 final Product doveSoap= new Product("Dove Soap",39.99);
	 //act
	 shoppingCart.addProduct(doveSoap,5);
	 //assert
	 final Map<Product,Integer> actualProduct=shoppingCart.getProducts();
	 // assert contains the expected product in the basket
	 assertTrue(actualProduct.containsKey(doveSoap));
	 //assert contains the expected quantity 
	 assertTrue(actualProduct.containsValue(5));
	 assertThat(shoppingCart.calculateBill(),is(equalTo(199.95)));
	 
	}
	@Test
	public void shoppingCartShouldHaveEightDoveSoapsWithRightPrice()
	{
	//arrange
     final ShoppingCartService shoppingCart=new ShoppingCartService();
	 final Product doveSoap= new Product("Dove Soap",39.99);
	 //act
	 shoppingCart.addProduct(doveSoap,5);
	 shoppingCart.addProduct(doveSoap,3);
	//assert
	 final Map<Product,Integer> actualProduct=shoppingCart.getProducts();
	 // assert contains the expected product in the basket
	 assertTrue(actualProduct.containsKey(doveSoap));
	 //assert contains the expected quantity 
	 assertTrue(actualProduct.containsValue(8));
	 assertThat(shoppingCart.calculateBill(),is(equalTo(319.92)));
	}
	
	@Test
	public void shoppingCartShouldHaveTenDoveSoapsWithRightPrice()
	{
	//arrange
     final ShoppingCartService shoppingCart=new ShoppingCartService();
	 final Product doveSoap= new Product("Dove Soap",39.99);
	 //act
	 shoppingCart.addProduct(doveSoap,5);
	 shoppingCart.addProduct(doveSoap,3);
	 shoppingCart.addProduct(doveSoap,2);
	//assert
	 final Map<Product,Integer> actualProduct=shoppingCart.getProducts();
	 // assert contains the expected product in the basket
	 assertTrue(actualProduct.containsKey(doveSoap));
	 //assert contains the expected quantity 
	 assertTrue(actualProduct.containsValue(10));
	 assertThat(shoppingCart.calculateBill(),is(equalTo(399.9)));
	}
	
	@Test
	public void shoppingCartShouldMultipleItemAndPriceIncludesSalesTax() {
	//arrange
	 final ShoppingCartService shoppingCart=new ShoppingCartService();
	 final Product doveSoap= new Product("Dove Soap",39.99);
	 final Product axeDeo= new Product("Axe Deo",99.99);
	 shoppingCart.setSaleTaxRate(12.5);
	//act
	 shoppingCart.addProduct(doveSoap,2);
	 shoppingCart.addProduct(axeDeo,2);
	//assert
	 final Map<Product,Integer> actualProduct=shoppingCart.getProducts();
	 // assert contains the expected product in the basket
	 assertTrue(actualProduct.containsKey(doveSoap));
	 assertTrue(actualProduct.containsKey(axeDeo));
	 //assert contains the expected quantity 
	 assertThat(actualProduct.get(doveSoap),is(equalTo(2)));
	 assertThat(actualProduct.get(axeDeo),is(equalTo(2)));
	 assertThat(shoppingCart.getSaleTaxAmount(),is(equalTo(35.00)));
	 assertThat(shoppingCart.calculateBillAfterTax(),is(equalTo(314.96)));
	}

}
