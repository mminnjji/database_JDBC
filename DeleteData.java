
import java.sql.*;

public class DeleteData {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/burger_restaurant";
    private static final String USER = "restaurant_user";
    private static final String PASS = "password";

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("주문이 성공적으로 삭제되었습니다");
            } else {
                System.out.println("삭제에 실패하였습니다 - 주문 번호 존재하지 않음.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
