package com.employee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	
	
	
	@Autowired
	public DataSource dataSource;
	
	public boolean addEmployee(Employee e) throws SQLException {
	Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"insert into imcs.users (number,name,salary,age,employeeid,company_name) values (?, ?, ?, ?,?,?) ");
		statement.setInt(1, e.getNumber());
		statement.setString(2, e.getName());
		statement.setDouble(3, e.getSalary());
		statement.setInt(4, e.getAge());
		statement.setInt(5, e.getEmployeeid());
		statement.setString(6, e.getCompany_name());

		return statement.executeUpdate() > 0;
	}

	public boolean deleteEmployee(int e) throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from imcs.users where employeeid = ? ");
		statement.setInt(1, e);
		return statement.executeUpdate() > 0;
	}

	public boolean updateEmployee(Employee e) throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"update imcs.users set number=?,name=?,salary = ?,age=?,employeeid=?,company_name=? where employeeid = ? or name=?");
		statement.setInt(1, e.getNumber());
		statement.setString(2, e.getName());
		statement.setDouble(3, e.getSalary());
		statement.setInt(4, e.getAge());
		statement.setInt(5, e.getEmployeeid());
		statement.setString(6, e.getCompany_name());
		statement.setInt(7, e.getEmployeeid());
		statement.setString(8, e.getName());
		return statement.executeUpdate() > 0;
	}

	public StringBuilder buildConstruct(ResultSet rs) {

		try {
			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append(rs.getInt(1)).append(",").append(rs.getString(2)).append(",").append(rs.getDouble(3))
						.append(",").append(rs.getInt(4)).append(",").append(rs.getInt(5));
				//System.out.println(sb);
				return sb;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void selectEmployee(Employee e) throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"select number, name, salary,age,employeeid,company_name from imcs.users where employeeid = ? ");
		statement.setInt(1, e.getEmployeeid());
		ResultSet resultSet = statement.executeQuery();
		// use resultset
		buildConstruct(resultSet);

	}

	public void selectAllEmployee() throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from imcs.users ");
		ResultSet resultSet = statement.executeQuery();
		buildConstruct(resultSet);

	}

	public void sortBySalary() throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from imcs.users order by salary asc");
		ResultSet resultSet = statement.executeQuery();

	}

	public void sortBySalaryAndName() throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from imcs.users order by salary,name ");
		ResultSet resultSet = statement.executeQuery();
		buildConstruct(resultSet);
	}

	public void sortByDepartment() throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from imcs.users order by number");
		ResultSet resultSet = statement.executeQuery();
		buildConstruct(resultSet);

	}

	public void sortByID() throws SQLException {
		Connection	connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from imcs.users order by employeeid");
		ResultSet resultSet = statement.executeQuery();
		buildConstruct(resultSet);
	}

/*	private Connection createConnection() throws ClassNotFoundException, SQLException {
		// register driver
		Class.forName("com.mysql.jdbc.Driver");
		// establish connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imcs", "root", "root");
		return connection;
	}*/

}
