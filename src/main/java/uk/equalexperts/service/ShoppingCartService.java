package uk.equalexperts.service;

import static java.math.BigDecimal.ROUND_CEILING;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import uk.equalexperts.model.Product;

public class ShoppingCartService {
	private final Map<Product,Integer>  shoppingCart;
	private double  saleTaxRate;
	public ShoppingCartService()
	{
    	shoppingCart = new HashMap<>() ;
	}
	
	public void addProduct(final Product product,final int quantity)
	{
		int existingQuantity=0;
		existingQuantity=(shoppingCart.containsKey(product))?existingQuantity+shoppingCart.get(product):0;		
		shoppingCart.put(product, quantity+existingQuantity);
	}
	
	public Map<Product,Integer> getProducts()
	{
		return  shoppingCart;
	}
	
	public double calculateBill()
	{  double finalBill=0.0;
	   for(Map.Entry<Product,Integer> entry: shoppingCart.entrySet())
	   {
		   final Product product=entry.getKey();
		   final Integer quantity=entry.getValue();
		   finalBill=finalBill+product.getUnitPrice()*quantity;
	   }
		return Math.round(finalBill *100.0)/100.0;
	}

	public double calculateBillAfterTax()
	{
	  double billBeforeTax= calculateBill();
	  double saleTaxAmount=calculateSaleTaxAmount(billBeforeTax);
	  billBeforeTax=billBeforeTax+saleTaxAmount;
	  return Math.round(billBeforeTax *100.0)/100.0;
	}
	public void setSaleTaxRate(final double saleTaxRate)
	{
	    this.saleTaxRate=saleTaxRate;
	}
	public double getSaleTaxAmount()
	{
		double billBeforeTax= calculateBill();
		return calculateSaleTaxAmount(billBeforeTax);
	}
	
	private double calculateSaleTaxAmount(double billAmount) {
	 double saleTaxAmount=saleTaxRate*billAmount/100;
     saleTaxAmount=new BigDecimal(saleTaxAmount).setScale(2, ROUND_CEILING).doubleValue();
     return saleTaxAmount;
	}
	

}
