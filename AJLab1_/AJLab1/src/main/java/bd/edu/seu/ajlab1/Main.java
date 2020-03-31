/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1;

import bd.edu.seu.ajlab1.service.ProductService;

/**
 *
 * @author kmhasan
 */
public class Main {
    private ProductService productService;
    
    public Main() {
        productService = new ProductService();
        
//        System.out.println("Discontinued products: " + productService.findAllDiscontinuedProducts().size()+"\n");
//        // TODO add necessary lines to produce outputs for the other service methods
//        System.out.println(productService.findProductsThatNeedToBeReordered().size()+" product need to be reorder...");
//        System.out.println("Sorted by product id..");
//        productService.getProductListSortedByUnitPrice().forEach(System.out::println);
//        System.out.println("Sorted by product name..");
//        productService.getProductListSortedByUnitProductName().forEach(System.out::println);
//        System.out.println("");
//        System.out.println("Total price of the product in stock = "+productService.getTotalPriceOfProductsInStock());
        System.out.println(productService.getPriceProduct());
        System.out.println(productService.getMinValueProduct());
        System.out.println(productService.getAverageUnitPrice());
    }
    
    public static void main(String args[]) {
        new Main();
    }
}
