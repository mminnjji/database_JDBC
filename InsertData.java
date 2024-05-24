
import java.sql.*;
import java.util.Scanner;

public class InsertData {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/burger_restaurant";
    private static final String USER = "restaurant_user";
    private static final String PASS = "password";

    public void insertOrder(int customerId, String orderDate, int[] productIds, int[] quantities) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            connection.setAutoCommit(false); // 트랜잭션 시작

            // Orders 테이블에 새 주문 삽입
            String orderSql = "INSERT INTO Orders (customer_id, order_date) VALUES (?, ?)";
            try (PreparedStatement orderStmt = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, customerId);
                orderStmt.setString(2, orderDate);
                orderStmt.executeUpdate();

                // 생성된 주문 ID 가져오기
                ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);

                    // 주문 항목 삽입
                    String orderItemSql = "INSERT INTO OrderItems (order_id, product_id, quantity, item_price) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement orderItemStmt = connection.prepareStatement(orderItemSql)) {
                        for (int i = 0; i < productIds.length; i++) {
                            int productId = productIds[i];
                            int quantity = quantities[i];
                            double itemPrice = getProductPrice(connection, productId);

                            orderItemStmt.setInt(1, orderId);
                            orderItemStmt.setInt(2, productId);
                            orderItemStmt.setInt(3, quantity);
                            orderItemStmt.setDouble(4, itemPrice * quantity);
                            orderItemStmt.addBatch();
                        }
                        orderItemStmt.executeBatch();
                    }

                    // 트랜잭션 커밋
                    connection.commit();
                    System.out.println("주문과 주문 항목이 성공적으로 삽입되었습니다.");
                } else {
                    connection.rollback();
                    System.out.println("주문 ID를 가져오는 데 실패했습니다. 트랜잭션이 롤백되었습니다.");
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println("오류가 발생하여 트랜잭션이 롤백되었습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double getProductPrice(Connection connection, int productId) throws SQLException {
        String productSql = "SELECT price FROM Products WHERE product_id = ?";
        try (PreparedStatement productStmt = connection.prepareStatement(productSql)) {
            productStmt.setInt(1, productId);
            ResultSet resultSet = productStmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("price");
            } else {
                throw new SQLException("제품 ID를 찾을 수 없습니다: " + productId);
            }
        }
    }
}