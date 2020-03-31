/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1.service;

import bd.edu.seu.ajlab1.model.Product;
import bd.edu.seu.ajlab1.repository.ProductDao;
import bd.edu.seu.ajlab1.repository.ProductDaoCsvImplementation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleConsumer;
import java.util.stream.Collectors;

/**
 *
 * @author kmhasan
 */
public class ProductService {

    private ProductDao productDao;

    public ProductService() {
        // we can switch out the implementation just by changing one line here
        // that's the beauty of using DAO pattern here
        productDao = new ProductDaoCsvImplementation();
    }

    // a sample implementation
    public List<Product> findAllDiscontinuedProducts() {
        List<Product> productList = productDao.readAll()
                .stream()
                .filter(Product::isDiscontinued)
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> findProductsThatNeedToBeReordered() {
        List<Product> productList = productDao.readAll()
                .stream()
                .filter((product) -> product.getUnitsInStock() < product.getReorderLevel())
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> getProductListSortedByUnitPrice() {
        List<Product> productList = productDao.readAll()
                .stream()
                .sorted(Comparator.comparing(Product::getUnitPrice))
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public List<Product> getProductListSortedByUnitProductName() {
                List<Product> productList = productDao.readAll()
                .stream()
                .sorted(Comparator.comparing(Product::getProductName).thenComparing(Product::getUnitPrice))
                .collect(Collectors.toList());
        return productList;
    }

    // TODO write your code here
    public double getTotalPriceOfProductsInStock() {
          return productDao.readAll()
                .stream()
                 .mapToDouble(p -> p.getUnitPrice()*p.getUnitsInStock())
                  .sum();
    }



    //Todo get max value product
    public double getPriceProduct(){
        return productDao.readAll().
                stream()
                .mapToDouble(Product::getUnitPrice)
                .max()
                .orElse(0);
    }

    //Todo get min unit price product
    public double getMinValueProduct(){
        return productDao.readAll()
                .stream()
                .mapToDouble(Product::getUnitPrice)
                .min()
                .orElse(0);
    }

    //todo get average of all unit price
    public double getAverageUnitPrice(){
        return productDao.readAll()
                .stream()
                .parallel()
                .mapToDouble(Product::getUnitPrice)
                .average()
                .orElse(0);
    }

    //todo use map
    public List reorderLevel(){
        return productDao.readAll()
                .stream()
                .map(product -> product.getUnitPrice()*10)
                .collect(Collectors.toList());
    }


}

