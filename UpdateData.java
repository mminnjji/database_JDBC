
import java.sql.*;
import java.util.Scanner;

public class UpdateData {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/burger_restaurant";
    private static final String USER = "restaurant_user";
    private static final String PASS = "password";

    public void updateCustomer(int customerId, String newName, String newPhone) {
        String sql = "UPDATE Customers SET name = ?, phone = ? WHERE customer_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setString(2, newPhone);
            stmt.setInt(3, customerId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
            } else {
                System.out.println("정보 수정 실패 - 사용자 ID 존재하지 않음");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
