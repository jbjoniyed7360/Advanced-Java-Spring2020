package bd.edu.seu.repository;import bd.edu.seu.model.Product;import java.util.List;public interface ProductDao {    public List<Product> readAll();    public Product findProductByid(long id);    public Product save(Product product);    public Product updateProduct(Product product);}