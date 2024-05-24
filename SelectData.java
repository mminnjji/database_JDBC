
import java.sql.*;
import java.util.Scanner;

public class SelectData {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/burger_restaurant";
    private static final String USER = "restaurant_user";
    private static final String PASS = "password";

    public void queryWithJoinAndView(String customerName) {
        String sql = "SELECT os.order_id, os.customer_name, os.order_date, os.total_amount, os.product_list, c.phone " +
                     "FROM OrderSummary os " +
                     "JOIN Customers c ON os.customer_name = c.name " +
                     "WHERE os.customer_name = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer Name: " + rs.getString("customer_name"));
                System.out.println("Order Date: " + rs.getTimestamp("order_date"));
                System.out.println("Total Amount: " + rs.getDouble("total_amount"));
                System.out.println("Product List: " + rs.getString("product_list"));
                System.out.println("Customer Phone: " + rs.getString("phone"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void queryWithAggregationAndGroupBy(String productName) {
        String sql = "SELECT p.product_name, SUM(oi.quantity) AS total_quantity_sold " +
                     "FROM OrderItems oi " +
                     "JOIN Products p ON oi.product_id = p.product_id " +
                     "WHERE p.product_name = ? " +
                     "GROUP BY p.product_name";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Product Name: " + rs.getString("product_name"));
                System.out.println("Total Quantity Sold: " + rs.getInt("total_quantity_sold"));
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
