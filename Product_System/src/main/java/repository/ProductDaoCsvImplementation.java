/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import model.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author kmhasan
 */
public class ProductDaoCsvImplementation implements ProductDao {

    private final String CSV_FILENAME = "D:\\Advancded Java\\Codes\\Product_System\\src\\main\\resources\\products.csv";

    @Override
    public List<Product> readAll() {
        try {
            List<Product> productList = Files.lines(Paths.get(CSV_FILENAME))
                    .map(this::getProduct)
                    .filter(product -> product != null)
                    .collect(Collectors.toList());
            return productList;
        } catch (IOException ex) {
            Logger.getLogger(ProductDaoCsvImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Product getProduct(String line) {
        if (line.startsWith("p") || line.startsWith(" "))
            return null;
        
        String[] tokens = line.split("\\,");
        int productId = Integer.parseInt(tokens[0]);
        String productName = tokens[1];
        String quantityPerUnit = tokens[4];
        double unitPrice = Double.parseDouble(tokens[5]);
        double unitsInStock = Double.parseDouble(tokens[6]);
        double unitsOnOrder = Double.parseDouble(tokens[7]);
        double reorderLevel = Double.parseDouble(tokens[8]);
        boolean discontinued = tokens[9].charAt(0) == '1';
        
        Product product = new Product(productId, 
                productName, 
                quantityPerUnit, 
                unitPrice, 
                unitsInStock, 
                unitsOnOrder, 
                reorderLevel, 
                discontinued);
        return product;
    }

    @Override
    public void createProduct(Product product) {
        int discontinued = 0;
        if( product.getDiscontinued()) {
            discontinued = 1;
        }

        if(readAll().stream().filter(s -> s.getProductId() == product.getProductId()).collect(Collectors.toList()).size()==0) {
            String newProduct =
                    product.getProductId() + "," +
                            product.getProductName() + "," +"0,"+"0,"+
                            product.getQuantityPerUnit() + "," +
                            product.getUnitPrice() + "," +
                            product.getUnitsInStock() + "," +
                            product.getUnitsOnOrder() + "," +
                            product.getReorderLevel() + "," +
                            discontinued;
            try {
                List<String> oldProductList = Files.readAllLines(Paths.get(CSV_FILENAME));
                StringBuilder stringBuilder = new StringBuilder();
                oldProductList.forEach(p -> stringBuilder.append(p).append("\n"));
                PrintWriter wr = new PrintWriter(CSV_FILENAME);
                stringBuilder.append(newProduct);
                wr.println(stringBuilder);
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(product.getProductId()+" no product is created...");
        }else {
            System.out.println("ProductId is duplicate...");
        }
    }

    @Override
    public void deleteProduct(int id) {
        List<Product> products = readAll().stream().filter(p -> p.getProductId()!=id).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<products.size();i++) {
            int discontinued = 0;
            if (products.get(i).getDiscontinued()) {
                discontinued = 1;
            }
            String newProduct = products.get(i).getProductId() + "," +
                    products.get(i).getProductName() + "," + "0," + "0," +
                    products.get(i).getQuantityPerUnit() + "," +
                    products.get(i).getUnitPrice() + "," +
                    products.get(i).getUnitsInStock() + "," +
                    products.get(i).getUnitsOnOrder() + "," +
                    products.get(i).getReorderLevel() + "," +
                    discontinued;
            stringBuilder.append(newProduct);
            if (i < products.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        PrintWriter wr = null;
        try {
            wr = new PrintWriter(CSV_FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // stringBuilder.append(products);
        assert wr != null;
        wr.println(stringBuilder);
        wr.close();

        System.out.println(id+" no product is deleted..");
        
    }

    @Override
    public void updateProduct(Product product, int id) {
        int discontinued = 0;
        if( product.getDiscontinued()) {
            discontinued = 1;
        }


        List<Product> products = readAll();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getProductId() == id){
                products.set(i,product);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<products.size();i++){
            String newProduct = products.get(i).getProductId() + "," +
                    products.get(i).getProductName() + "," +"0,"+"0,"+
                    products.get(i).getQuantityPerUnit() + "," +
                    products.get(i).getUnitPrice() + "," +
                    products.get(i).getUnitsInStock() + "," +
                    products.get(i).getUnitsOnOrder() + "," +
                    products.get(i).getReorderLevel() + "," +
                    discontinued;
            stringBuilder.append(newProduct);
            if(i<products.size()-1){
                stringBuilder.append("\n");
            }
        }
        PrintWriter wr = null;
        try {
            wr = new PrintWriter(CSV_FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       // stringBuilder.append(products);
        assert wr != null;
        wr.println(stringBuilder);
        wr.close();



        System.out.println(id+" no product is updated....");
    }
}
