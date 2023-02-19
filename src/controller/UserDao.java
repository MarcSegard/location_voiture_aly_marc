package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import myConnection.Connect;

public class UserDao implements IDao<User> {

	Connection connect = Connect.getConnection();
	PreparedStatement sql;
	ResultSet rs;

	public static User currentUser;

	@Override
	public boolean create(User user) {
		try {
			sql = connect.prepareStatement(
					"insert into client (nom_client,prenom_client,email_client,password_client, permis_client) values (?,?,?,PASSWORD(?),?)");
			sql.setString(1, user.getNom());
			sql.setString(2, user.getPrenom());
			sql.setString(3, user.getEmail());
			sql.setString(4, user.getPassword());
			sql.setString(5, user.getPermis());
			sql.execute();

			sql = connect.prepareStatement("select distinct LAST_INSERT_ID() as id from client");
			rs = sql.executeQuery();

			if (rs.next()) {
				user.setId(rs.getInt("id"));
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean checkEmailIsExist(String email) {
		try {
			sql = connect.prepareStatement("select email_client from client where email_client=?");
			sql.setString(1, email);
			rs = sql.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User userLogin(String email, String password) {
		try {
			sql = connect
					.prepareStatement("select * from client where email_client=? and password_client = PASSWORD(?)");
			sql.setString(1, email);
			sql.setString(2, password);
			rs = sql.executeQuery();

			if (rs.next()) {
				currentUser = new User(rs.getString("nom_client"), rs.getString("prenom_client"),
						rs.getString("email_client"), rs.getString("permis_client"), rs.getInt("id_client"));
				return currentUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(User user) {
		try {
			sql = connect.prepareStatement(
					"UPDATE client SET email_client = ?, password_client = PASSWORD(?) WHERE id_client = ?");
			sql.setString(1, user.getEmail());
			sql.setString(2, user.getPassword());
			sql.setInt(3, user.getId());
			sql.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	

	public boolean delete(User user) {
		try {
			sql = connect.prepareStatement("delete from client where id_client=?");
			sql.setInt(1, user.getId());
			int userDelet = sql.executeUpdate();
			return userDelet > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
