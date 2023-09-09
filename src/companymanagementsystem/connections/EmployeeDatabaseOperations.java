
package companymanagementsystem.connections;

import companymanagementsystem.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseOperations extends Connections{
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try {
            String query = "SELECT employee_id, firstname, surname, position FROM employee";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("employee_id"));
                employee.setFirstname(resultSet.getString("firstname"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setPosition(resultSet.getString("position"));
                employees.add(employee);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return employees;
    }

    public boolean insertEmployee(Employee employeeDatas) {
        try {
            String query = "INSERT INTO employee (employee_id, firstname, surname, position) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, employeeDatas.getId());
            preparedStatement.setString(2, employeeDatas.getFirstname());
            preparedStatement.setString(3, employeeDatas.getSurname());
            preparedStatement.setString(4, employeeDatas.getPosition());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public boolean updateEmployee(Employee employee, String employee_id) {
        try {
            String query = "UPDATE employee SET employee_id=?, firstname=?, surname=?, position=? WHERE employee_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstname());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error in updateEmployee method due to " + ex);
            return false;
        }
        return true;
    }

    public boolean deleteEmployee(String employee_id) {
        try {
            String query = "DELETE FROM employee WHERE employee_id=? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error in deleteEmployee method due to " + ex);
            return false;
        }
        return true;
    }

    public Employee getAnSpesificEmployeeDatas(String id) throws Exception {
        Employee employee = new Employee();
        try {
            String query = "SELECT employee_id, firstname, surname, position FROM employee WHERE employee_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(); 

        if (resultSet.next()) {
            employee.setId(resultSet.getLong("employee_id"));
            employee.setFirstname(resultSet.getString("firstname"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setPosition(resultSet.getString("position"));
        }
        
        resultSet.close();


        } catch (SQLException ex) {
            System.out.println("Can't fetch a spesific row's data due to " + ex);
            throw ex;
            
        }
        return employee;
    }

    
}
