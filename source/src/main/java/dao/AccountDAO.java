package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Account;
		
public class AccountDAO {
	

	// 1件のアカウントを取得
	public Account getAccount(String email, String password){
		Connection conn = null;
		Account account;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/output?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//アカウント取得SQL文を準備
			String sql = "SELECT * FROM account WHERE email = ? AND password = ?;";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			
			ResultSet rs = pStmt.executeQuery();
			
			rs.next();
			account = new Account();
			account.setAccountId(rs.getInt("account_id"));
			account.setEmail(rs.getString("email"));
			account.setPassword(rs.getString("password"));
			account.setNickname(rs.getString("nickname"));
			account.setCategoryId(rs.getInt("category_id"));
			account.setGoalDetail(rs.getString("goal_detail"));
			account.setCreatedAt(rs.getTimestamp("created_at"));
			account.setLoginAt(rs.getTimestamp("login_at"));
			account.setConsecutiveLogins(rs.getInt("consecutive_Logins"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			account = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			account = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					account = null;
				}
			}
		}

		// 結果を返す
		return account;
	}
		
	// 1件のアカウントを新規登録
	public boolean registAccount(Account account){
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/output?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			if(existsAccount(account.getEmail())) {
				
				String sqlRegist = "INSERT INTO account (account_id,email,password,nickname,category_id,goal_detail,login_at,consecutive_logins) VALUES(0,?,?,?,?,null,1);";
				
				PreparedStatement pStmtRegist = conn.prepareStatement(sqlRegist);
				
				pStmtRegist.setString(1,account.getEmail());
				pStmtRegist.setString(2,account.getPassword());
				pStmtRegist.setString(3,account.getNickname());
				pStmtRegist.setInt(4,account.getCategoryId());
				pStmtRegist.setString(5,account.getGoalDetail());
				
				if (pStmtRegist.executeUpdate() == 1) {
				result = true;
				}
				
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
	// 重複があった場合：return false;
	}
		
	// アカウント情報の更新
	public boolean updateAccount(Account account){
		
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/output?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			if(existsAccount(account.getEmail())) {
				
				String sqlUpdate = "UPDATE account SET "
						+ "email = ?,"
						+ "password = ?,"
						+ "nickname = ?,"
						+ "category_id = ?,"
						+ "goal_detail = ? "
						+ "WHERE account_id = ?;";
				
				PreparedStatement pStmtUpdate = conn.prepareStatement(sqlUpdate);
				
				pStmtUpdate.setString(1,account.getEmail());
				pStmtUpdate.setString(2,account.getPassword());
				pStmtUpdate.setString(3,account.getNickname());
				pStmtUpdate.setInt(4,account.getCategoryId());
				pStmtUpdate.setString(5,account.getGoalDetail());
				pStmtUpdate.setInt(6,account.getAccountId());
				
				if (pStmtUpdate.executeUpdate() == 1) {
				result = true;
				}
				
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
	
	public boolean existsAccount(String email){
		
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/output?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			String sqlExists = "SELECT COUNT(*) FROM account WHERE email = ?;";
			
			PreparedStatement pStmtExists = conn.prepareStatement(sqlExists);
			pStmtExists.setString(1,email);
			
			ResultSet rsExists = pStmtExists.executeQuery();
			
			rsExists.next();
			if(rsExists.getInt("count(*)") == 0) {
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
	
	
	


}
