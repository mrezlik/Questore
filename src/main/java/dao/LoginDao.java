package dao;

import model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao extends ManipulationDao {

    public int findStatusIdByName(String name) throws SQLException {
        ResultSet result = selectDataFromTable("status", "id_status", "name='" + name + "'");
        return getIntFromResult(result, "id_status");
    }

    public String getUserCategory(int userID) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(
                "SELECT user_category.name FROM user_category " +
                "  INNER JOIN user ON user_category.id = user.id " +
                "    WHERE user.id = ?;");
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        return rs.getString("user_category.name");
    }

    public int getUserId(String login, String password) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(
                "SELECT id FROM user " +
                        "    WHERE login = ? AND password = ?;");
        ps.setString(1, login);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.getInt("id");
    }

}
