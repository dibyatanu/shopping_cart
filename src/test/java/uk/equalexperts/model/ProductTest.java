package uk.equalexperts.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProductTest {
	private Product product;
	
	@Test
	public void shouldGetProductNameAndPrice() {
		product = new Product("Dave Soap",39.99);
		assertThat(product.getName(),is(equalTo("Dave Soap")));
		assertThat(product.getUnitPrice(),is(equalTo(39.99)));
	}
    @Test
    public void indenticalProductsShouldHaveSameHashCode()
    {
    	final Product daveSoap1= new Product("Dave Soap",39.99);
    	final Product daveSoap2= new Product("Dave Soap",39.99);
    	assertEquals(daveSoap1.hashCode(), daveSoap2.hashCode());
    }
    @Test
    public void indenticalProductsShouldBeEqualToEachOther()
    {
    	final Product daveSoap1= new Product("Dave Soap",39.99);
    	final Product daveSoap2= new Product("Dave Soap",39.99);
    	assertTrue(daveSoap1.equals(daveSoap2));
    }
}
