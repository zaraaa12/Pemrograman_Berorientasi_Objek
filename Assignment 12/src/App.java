import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException  {
        String url = "jdbc:mysql://localhost:3306/elearning";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            if (conn.isValid(5)) {
                createData(conn);
                updateData(conn);
                deleteData(conn);
                readData(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readData(Connection conn) throws SQLException {
        String query = "SELECT * FROM products";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("+------------------+----------------+----------------+");
            System.out.println("| Name             | SKU            | Price          |");
            System.out.println("+------------------+----------------+----------------+");
            
            while (rs.next()) {
                String name = rs.getString("name");
                String sku = rs.getString("sku");
                String price = rs.getString("price");
                
                System.out.printf("| %-16s | %-14s | %-14s | \n", 
                    name, sku, price);
            }
            
            System.out.println("+----------------------+------------+----------------+");
        }
    }

    private static void createData(Connection conn) throws SQLException {
        String query = "INSERT INTO products (name, sku, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "School Bag");
            pstmt.setString(2, "SKU00012");
            pstmt.setDouble(3, 1000000.0);
            pstmt.executeUpdate();
            System.out.println("Data created successfully");
        }
    }

    private static void updateData(Connection conn) throws SQLException {
        String query = "UPDATE products SET sku = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "SKUACR00011");
            pstmt.setString(2, "1");
            pstmt.executeUpdate();
            System.out.println("Data updated successfully");
        }
    }

    private static void deleteData(Connection conn) throws SQLException {
        String query = "DELETE FROM products WHERE sku = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "SKU00012");
            pstmt.executeUpdate();
            System.out.println("Data deleted successfully");
        }
    }
}