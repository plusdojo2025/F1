package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Category;
import dto.Log;
import dto.LogList;
import dto.Mood;

public class LogDAO {
	
	// 取得
	public List<LogList> selectLogs(int account_id) {
		
		Connection conn = null;
		List<LogList> logs = new ArrayList<LogList>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//ログ取得SQL文を準備
			String sqlSelect = "SELECT "
					+ "log_id, l.task_title, l.duration, m.mood_title, c.category_title, l.satisfaction_level "
					+ "FROM log AS l "
                    + "JOIN mood AS m ON m.mood_id = l.mood_id "
                    + "JOIN category AS c ON c.category_id = l.category_id "
					+ "WHERE l.account_id = ? "
					+ "ORDER BY log_time DESC;";

			PreparedStatement pStmtSelect = conn.prepareStatement(sqlSelect);
			pStmtSelect.setInt(1, account_id);
			
			ResultSet rsSelect = pStmtSelect.executeQuery();
			
			while(rsSelect.next()) {
				LogList log = new LogList();
				log.setLogId(rsSelect.getInt("log_id"));
				log.setTitle(rsSelect.getString("title"));
				log.setDuration(rsSelect.getInt("duration"));
		        log.setMoodTitle(rsSelect.getString("mood_title"));
		        log.setCategoryTitle(rsSelect.getString("category_title"));
		        log.setSatisfactionLevel((Integer)rsSelect.getObject("satisfaction_level"));
		        logs.add(log);
			}
			
			rsSelect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			logs = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logs = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					logs = null;
				}
			}
		}

		// 結果を返す
		return logs;
		
	}
	
	// 登録
	public boolean registLog(Log log){
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			String sqlRegist = "INSERT INTO log VALUES (0,?,?,?,?,?,?,?);";
				
			PreparedStatement pStmtRegist = conn.prepareStatement(sqlRegist);
				
			pStmtRegist.setInt(1,log.getAccountId());
			pStmtRegist.setString(2,log.getTaskTitle());
			pStmtRegist.setInt(3,log.getMoodId());
			pStmtRegist.setInt(4,log.getCategoryId());
			pStmtRegist.setTimestamp(5,log.getLogTime());
			pStmtRegist.setInt(6,log.getDuration());
			pStmtRegist.setInt(7,log.getSatisfactionLevel());
				
			if (pStmtRegist.executeUpdate() == 1) {
			result = true;
			}
				

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 結果を返す
		return result;
	}
	
	
	// 満足度の登録
	public boolean registSatisfactionlevel(int accountId,int satisfactionLevel){
		Connection conn = null;
		boolean result = false;
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			String sqlUpdate = "UPDATE log SET satisfaction_level = ? "
					+ "WHERE account_id = ?;";
					
			PreparedStatement pStmtUpdate = conn.prepareStatement(sqlUpdate);
					
			pStmtUpdate.setInt(1,accountId);
			pStmtUpdate.setInt(2,satisfactionLevel);
					
			if (pStmtUpdate.executeUpdate() == 1) {
			result = true;
			}
					

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 結果を返す
		return result;
	}
	
	
	// 総合計
	public int sumDuration(int account_id){
		
		Connection conn = null;
		int sumDuration=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//アカウント取得SQL文を準備
			String sqlSum = "SELECT SUM(duration) AS sum_duration FROM log "
					+ "WHERE account_id = ?;";
			
			PreparedStatement pStmtSum = conn.prepareStatement(sqlSum);
			pStmtSum.setInt(1, account_id);
			
			ResultSet rsSum = pStmtSum.executeQuery();
			
			rsSum.next();
			sumDuration = rsSum.getInt("sum_duration");
			
		} catch (SQLException e) {
			e.printStackTrace();
			sumDuration = 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sumDuration = 0;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					sumDuration = 0;
				}
			}
		}
		// 結果を返す
		return sumDuration;
	}
	
	// 1日の総合計
		public int sumDayDuration(int account_id){
			
			Connection conn = null;
			int sumDayDuration=0;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				
				//アカウント取得SQL文を準備
				String sqlSum = "SELECT SUM(duration) AS sum_day_duration FROM log "
						+ "WHERE account_id = ? "
						+ "AND DATE(log_time) = CURDATE();";
				
				PreparedStatement pStmtSum = conn.prepareStatement(sqlSum);
				pStmtSum.setInt(1, account_id);
				
				ResultSet rsSum = pStmtSum.executeQuery();
				
				rsSum.next();
				sumDayDuration = rsSum.getInt("sum_day_duration");
				
			} catch (SQLException e) {
				e.printStackTrace();
				sumDayDuration = 0;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				sumDayDuration = 0;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						sumDayDuration = 0;
					}
				}
			}
			// 結果を返す
			return sumDayDuration;
		}
	
	// よく行う気分
	public Mood getMaxMood(int account_id){
		
		Connection conn = null;
		Mood mood;
		MoodDAO mdao = new MoodDAO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//最頻気分ID取得SQL文を準備
			String sqlSum = "SELECT mood_id FROM log "
					+ "WHERE account_id = ? "
					+ "GROUP BY mood_id "
					+ "HAVING count(*) = (SELECT MAX(cnt) "
					+ "                   FROM (SELECT count(*) AS cnt FROM log "
					+ "                         WHERE account_id = ? "
					+ "                         GROUP BY mood_id) AS a) "
					+ "ORDER BY mood_id "
					+ "LIMIT 1;";
			
			PreparedStatement pStmtSum = conn.prepareStatement(sqlSum);
			pStmtSum.setInt(1, account_id);
			pStmtSum.setInt(2, account_id);
			
			ResultSet rsSum = pStmtSum.executeQuery();
			
			rsSum.next();
			mood = mdao.getMood(rsSum.getInt("mood_id"));
			
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

		// 結果を返す
		return mood;
	}

	// よく行う作業ジャンル
	public Category getMaxCategory(int account_id){
		
		Connection conn = null;
		Category category;
		CategoryDAO cdao = new CategoryDAO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//最頻作業カテゴリID取得SQL文を準備
			String sqlSum = "SELECT category_id FROM log "
					+ "WHERE account_id = ? "
					+ "GROUP BY category_id "
					+ "HAVING count(*) = (SELECT MAX(cnt) "
					+ "                   FROM (SELECT count(*) AS cnt FROM log "
					+ "                         WHERE account_id = ? "
					+ "                         GROUP BY category_id) AS a) "
					+ "ORDER BY category_id "
					+ "LIMIT 1;";
			
			PreparedStatement pStmtSum = conn.prepareStatement(sqlSum);
			pStmtSum.setInt(1, account_id);
			pStmtSum.setInt(2, account_id);
			
			ResultSet rsSum = pStmtSum.executeQuery();
			
			rsSum.next();
			category = cdao.getCategory(rsSum.getInt("category_id"));
			
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

		// 結果を返す
		return category;
		
	}

}