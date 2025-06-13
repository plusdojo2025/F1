package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Mood;

public class MoodDAO {
	
	public Mood getMood(int mood_id){
		
		Connection conn = null;
		// リストを作成
		Mood mood = new Mood();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
		
			// 実行SQL
			String sql = "SELECT * FROM mood WHERE mood_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mood_id);
			ResultSet rs = ps.executeQuery();
		
			// SQLの結果をdtoにセット
			rs.next();
			mood.setMoodTitle(rs.getString("mood_title"));
			mood.setMoodId(rs.getInt("mood_id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			mood = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			mood = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					mood = null;
				}
			}
		}
		
	return mood;
		
	}
	
	// Moodテーブルから全件取得
	public List<Mood> getMoodList() {
		
		Connection conn = null;
		// リストを作成
		List<Mood> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
		
		
			// 実行SQL
			String sql = "SELECT * FROM mood;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			// SQLの結果をdtoにセット
			while (rs.next()) {
				// Moodオブジェクト生成
				Mood mood = new Mood();
				mood.setMoodTitle(rs.getString("mood_title"));
				mood.setMoodId(rs.getInt("mood_id"));
				list.add(mood);
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
