/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1.repository;

import bd.edu.seu.ajlab1.model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Joniyed
 */
public class ProductDAOmySQLImplementation implements ProductDao {
    private List<Product> products = new ArrayList<>();
    
    @Override
    public Product getSingleProduct(int id){
        Connection con = ConnectionSingleton.getConnection();
        try {
            Statement st = con.createStatement();
            String query = "select * from product_details WHERE productID="+id;
            
            
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               Product product = new Product(
                       Integer.parseInt(rs.getString("productID")),
                       rs.getString("productName"),
                       rs.getString("quantityPerUnit"),
                       Double.parseDouble(rs.getString("unitPrice")),
                       Double.parseDouble(rs.getString("unitsInStock")),
                       Double.parseDouble(rs.getString("unitsOnOrder")),
                       Double.parseDouble(rs.getString("reorderLevel")),
                       rs.getString("discontinued").contains("true")
               );
               return product; 
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOmySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    

    @Override
    public List<Product> readAll() {
        try {
            Connection con = ConnectionSingleton.getConnection();
            System.out.println("");
            String q = "select * from product_details";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);

            List<String> productList_temp = new ArrayList<>();

            while (rs.next()) {
                String value = "";
                for (int i = 1; i < 11; i++) {
                    value += rs.getString(i) + ",";
                }
                productList_temp.add(value);
            }

            if (!productList_temp.isEmpty()) {
                products = productList_temp.stream()
                        .map(this::getProduct)
                        .filter(p -> p != null)
                        .collect(Collectors.toList());
            }
            return products;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOmySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Product getProduct(String line) {
        if (line.startsWith("p")) {
            return null;
        }

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
        Connection con = ConnectionSingleton.getConnection();
        try {
            Statement st = con.createStatement();
            String query = "insert into product_details values("+
                    product.getProductID()+",'"+
                    product.getProductName()+"','"+"','"+"','"+
                    product.getQuantityPerUnit()+"','"+
                    product.getUnitPrice()+"','"+
                    product.getUnitsInStock()+"','"+
                    product.getUnitsOnOrder()+"','"+
                    product.getReorderLevel()+"','"+
                    product.isDiscontinued()  
                    +"')";
            
            System.out.println(st.executeUpdate(query));  
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOmySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void deleteProduct(int id) {
        Connection con = ConnectionSingleton.getConnection();
        try {
            Statement st = con.createStatement();
            String query = "delete from product_details where productID="+id;
            
            st.executeUpdate(query);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOmySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateProduct(Product product, int id) {
        Connection con = ConnectionSingleton.getConnection();
        try {
            Statement st = con.createStatement();
            
            String query = "UPDATE product_details set "+
                   "productName='"+product.getProductName()+
                    "',quantityPerUnit='"+product.getQuantityPerUnit()+
                    "',unitPrice='"+product.getUnitPrice()+
                    "',unitsInStock='"+product.getUnitsInStock()+
                    "',unitsOnOrder='"+product.getUnitsOnOrder()+
                    "',reorderLevel='"+product.getReorderLevel()+
                    "',discontinued='"+product.isDiscontinued()+
                    "' where productID ='"+id+"'";
            
            st.executeUpdate(query);
                      
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOmySQLImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

}
