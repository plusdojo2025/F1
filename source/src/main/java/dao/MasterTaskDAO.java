package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MasterTask;

public class MasterTaskDAO {
	
	public List<MasterTask> selectAll(){
		
		Connection conn = null;
		// リストを作成
		List<MasterTask> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
		
			// 実行SQL
			String sql = "SELECT * FROM master_tasks";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			// SQLの結果をbeanにセット
			while (rs.next()) {
				MasterTask mt = new MasterTask();
				mt.setTitle(rs.getString("title"));
				mt.setTimeSpan(rs.getInt("time_span"));
				mt.setMoodId(rs.getInt("mood_id"));
				mt.setCategoryId(rs.getInt("category_id"));
				// リストに追加
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
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
