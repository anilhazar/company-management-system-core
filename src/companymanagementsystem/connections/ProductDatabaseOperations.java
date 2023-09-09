
package companymanagementsystem.connections;

import companymanagementsystem.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDatabaseOperations extends Connections{
      

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            String query = "SELECT catalog_number, category, price, quantity FROM product";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product();
                product.setCatalogNumber(resultSet.getLong("catalog_number"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getLong("price"));
                product.setQuantity(resultSet.getLong("quantity"));
                products.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return products;
    }

    public boolean insertProduct(Product productDatas) {
        try {
            String query = "INSERT INTO product (catalog_number, category, price, quantity) VALUES( ?,  ?,  ?,  ?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, productDatas.getCatalogNumber());
            preparedStatement.setString(2, productDatas.getCategory());
            preparedStatement.setLong(3, productDatas.getPrice());
            preparedStatement.setLong(4, productDatas.getQuantity());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public boolean updateProduct(Product product, String catalog_number
    ) {
        try {
            String query = "UPDATE product SET catalog_number=?, category =  ?, price =  ?, quantity =  ? WHERE catalog_number= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, product.getCatalogNumber());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setLong(3, product.getPrice());
            preparedStatement.setLong(4, product.getQuantity());
            preparedStatement.setString(5, catalog_number);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(
                    "Error in updateProduct method due to " + ex);
            return false;
        }
        return true;
    }

    public boolean deleteProduct(String catalog_number) {
        try {
            String query = "DELETE FROM product WHERE catalog_number=? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, catalog_number);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error in deleteProduct method due to " + ex);
            return false;
        }
        return true;
    }
    
    public Product getAnSpesificProductDatas(String catalogNumber) throws Exception {
        Product product = new Product();
        try {
            String query = "SELECT catalog_number, category, price, quantity FROM product WHERE catalog_number=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, catalogNumber);
            ResultSet resultSet = preparedStatement.executeQuery(); 

        if (resultSet.next()) {
            product.setCatalogNumber(resultSet.getLong("catalog_number"));
            product.setCategory(resultSet.getString("category"));
            product.setPrice(resultSet.getLong("price"));
            product.setQuantity(resultSet.getLong("quantity"));
        }
        
        resultSet.close();


        } catch (SQLException ex) {
            System.out.println("Can't fetch a spesific row's data due to " + ex);
            throw ex;
            
        }
        return product;
    }


}
