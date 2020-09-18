import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCExample {

    public static void main(String[] args) {
    List<Employee> employeeList = new ArrayList<Employee>();
    String sql_select = "Select * from Users";
    String sql_insert = "Insert into Users values (3," + "' Ekaterina'," + "' Stepanova'," + "'woman'," +  "'1990.12.03')";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/shop", "postgres", "82469173");
        Statement statement1 = conn.createStatement();

        PreparedStatement statement = conn.prepareStatement(sql_select)) {
            statement1.executeUpdate(sql_insert);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("UserID");
                String firstName = resultSet.getString("FirstName");
                String secondName = resultSet.getString("SecondName");
                String sex = resultSet.getString("Sex");
                Date birthDate = resultSet.getDate("BirthDate");

                Employee obj = new Employee();
                obj.setId(id);
                obj.setFirstName(firstName);
                obj.setSecondName(secondName);
                obj.setSex(sex);
                obj.setBirthDate(birthDate);
                employeeList.add(obj);

            }
            employeeList.forEach(x -> System.out.println(x.toString()));

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}