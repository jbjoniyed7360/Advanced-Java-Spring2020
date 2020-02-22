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
    private final ProductService productService;
    
    public Main() {
        productService = new ProductService();
        
        System.out.println("\nDiscontinued products: " + productService.findAllDiscontinuedProducts().size());
        // TODO add necessary lines to produce outputs for the other service methods
        System.out.println("\n\nPoduct need be reorder List..\n");
        productService.findProductsThatNeedToBeReordered().forEach(System.out::println);
        
        System.out.println("\n\nSorted by product unit Price..\n");
        productService.getProductListSortedByUnitPrice().forEach(System.out::println);
        
        System.out.println("\n\nSorted by product name..\n");
        productService.getProductListSortedByUnitProductName().forEach(System.out::println);
        
        System.out.println("\n\nTotal price of the product in stock = "+productService.getTotalPriceOfProductsInStock());

    }
    
    public static void main(String args[]) {
        Main main = new Main();
    }
}
