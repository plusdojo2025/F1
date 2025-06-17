package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Category;

public class CategoryDAO {
	
	public Category getCategory(int category_id){
		Connection conn = null;
		// リストを作成
		Category category = new Category();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
			// 実行SQL
			String sql = "SELECT * FROM category WHERE category_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, category_id);
			ResultSet rs = ps.executeQuery();
			
			// SQLの結果をdtoにセット
			while(rs.next()) {
			category.setCategoryTitle(rs.getString("category_title"));
			category.setCategoryId(rs.getInt("category_id"));
			}
			
		//エラー処理
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
	
	// Categoryテーブルから全件取得
	public List<Category> getCategoryList(){
		
		Connection conn = null;
		// リストを作成
		List<Category> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
		
			// 実行SQL
			String sql = "SELECT * FROM category;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			// SQLの結果をdtoにセット
			while (rs.next()) {
				// Categoryオブジェクト生成
				Category category = new Category();
				category.setCategoryTitle(rs.getString("category_title"));
				category.setCategoryId(rs.getInt("category_id"));
				list.add(category);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			list= null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}
		}
		
	return list;
		
	}


}

