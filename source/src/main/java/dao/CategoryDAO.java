package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Category;

public class CategoryDAO {
	
	public Category getCategory(int category_id){
		Connection conn = null;
		// リストを作成
		Category category = new Category();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/output?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
			// 実行SQL
			String sql = "SELECT * FROM mood WHERE category_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, category_id);
			ResultSet rs = ps.executeQuery();
			
			// SQLの結果をdtoにセット
			rs.next();
			category.setCategoryTitle(rs.getString("category_title"));
			category.setCategoryId(rs.getInt("category_id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			category = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			category = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					category = null;
				}
			}
		}
		
	return category;
	}
	
}

