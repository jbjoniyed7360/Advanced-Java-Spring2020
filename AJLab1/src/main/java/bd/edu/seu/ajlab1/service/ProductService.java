/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1.service;

import bd.edu.seu.ajlab1.model.Product;
import bd.edu.seu.ajlab1.repository.ProductDAOmySQLImplementation;
import bd.edu.seu.ajlab1.repository.ProductDao;
import bd.edu.seu.ajlab1.repository.ProductDaoCsvImplementation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kmhasan
 */
public class ProductService {

    private final ProductDao productDao;
    private List<Product> productListGlobal = new ArrayList<>();

    public ProductService() {
        // we can switch out the implementation just by changing one line here
        // that's the beauty of using DAO pattern here
        //productDao = new ProductDaoCsvImplementation();
        productDao = new ProductDAOmySQLImplementation();
        productListGlobal = productDao.readAll();
    }

    // a sample implementation
    public List<Product> findAllDiscontinuedProducts() {
        List<Product> productList = productListGlobal;
        return productList
                .stream()
                .filter((product) -> product.isDiscontinued())
                .collect(Collectors.toList());
    }

    // TODO write your code here
    public List<Product> findProductsThatNeedToBeReordered() {
        List<Product> productList = productListGlobal;
        return productList
                .stream()
                .filter((product) -> product.getUnitsInStock() < product.getReorderLevel())
                .collect(Collectors.toList());
    }

    // TODO write your code here
    public List<Product> getProductListSortedByUnitPrice() {
        List<Product> productList = productListGlobal;
        return productList
                .stream()
                .sorted((a, b) -> (int) a.getUnitPrice() * 1000 - (int) b.getUnitPrice() * 1000)
                .collect(Collectors.toList());
    }

    // TODO write your code here
    public List<Product> getProductListSortedByUnitProductName() {
        List<Product> productList = productListGlobal;
        return productList
                .stream()
                .sorted((a, b) -> a.getProductName().compareTo(b.getProductName()))
                .collect(Collectors.toList());
    }

    // TODO write your code here
    public double getTotalPriceOfProductsInStock() {
        List<Product> productList = productListGlobal;
        return productList.stream()
                .mapToDouble(p -> p.getUnitPrice() * p.getUnitsInStock())
                .sum();
    }
}
