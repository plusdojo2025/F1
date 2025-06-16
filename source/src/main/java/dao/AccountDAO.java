package dao;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Account;
		
public class AccountDAO {
	

	// メールアドレスとパスワードをもとに、1件のアカウントを取得
	public Account getAccount(String email, String password){
		Connection conn = null;
		Account account = new Account();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//パスワードをハッシュ化
			password = hashPassword(password);
			
			//アカウント取得SQL文を準備
			String sql = "SELECT * FROM account WHERE email = ? AND password = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//アカウント取得SQLに挿入するプリペアードステートメント
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			
			//アカウント取得SQLを実行
			ResultSet rs = pStmt.executeQuery();
			
			//accountにSQL実行結果を挿入
			while(rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt("account_id"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setNickname(rs.getString("nickname"));
				account.setCategoryId(rs.getInt("category_id"));
				account.setGoalDetail(rs.getString("goal_detail"));
				account.setCreatedAt(rs.getTimestamp("created_at"));
				account.setLoginAt(rs.getTimestamp("login_at"));
				account.setConsecutiveLogins(rs.getInt("consecutive_logins"));
			}
		
		//エラー処理
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
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//入力されたメールアドレスが存在しなければ新規登録
			if(existsAccount(account.getEmail())) {
				
				//パスワードのハッシュ化
				String password = hashPassword(account.getPassword());	
				
				//新規登録SQLの準備
				String sqlRegist = "INSERT INTO account (account_id,email,password,nickname,category_id,goal_detail,login_at,consecutive_logins) VALUES(0,?,?,?,?,?,null,1);";
				PreparedStatement pStmtRegist = conn.prepareStatement(sqlRegist);
				
				//新規登録SQLに挿入するプリペアードステートメント
				pStmtRegist.setString(1,account.getEmail());
				pStmtRegist.setString(2,password);
				pStmtRegist.setString(3,account.getNickname());
				pStmtRegist.setInt(4,account.getCategoryId());
				pStmtRegist.setString(5,account.getGoalDetail());
				
				//新規登録SQLの実行
				if (pStmtRegist.executeUpdate() == 1) {
				result = true;
				}
				
			}
			
		//エラー処理
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
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//入力されたメールアドレスが存在しなければ情報更新
			if(existsAccount(account.getEmail())) {
				
				//パスワードのハッシュ化
				String password = hashPassword(account.getPassword());
				
				//アカウント情報更新SQLを準備
				String sqlUpdate = "UPDATE account SET "
						+ "email = ?,"
						+ "password = ?,"
						+ "nickname = ?,"
						+ "category_id = ?,"
						+ "goal_detail = ? "
						+ "login_at = ? "
						+ "consecutive_logins = ? "
						+ "WHERE account_id = ?;";
				PreparedStatement pStmtUpdate = conn.prepareStatement(sqlUpdate);
				
				//アカウント情報更新SQLに挿入するプリペアードステートメント
				pStmtUpdate.setString(1,account.getEmail());
				pStmtUpdate.setString(2,password);
				pStmtUpdate.setString(3,account.getNickname());
				pStmtUpdate.setInt(4,account.getCategoryId());
				pStmtUpdate.setString(5,account.getGoalDetail());
				pStmtUpdate.setTimestamp(6,account.getLoginAt());
				pStmtUpdate.setInt(7,account.getConsecutiveLogins());
				pStmtUpdate.setInt(8,account.getAccountId());
				
				//アカウント情報更新SQLを実行
				if (pStmtUpdate.executeUpdate() == 1) {
				result = true;
				}
				
			}
		//エラー処理
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
	//入力されたメールアドレスがテーブル内に存在しないならtrue,存在するならfalseを返すメソッド
	public boolean existsAccount(String email){
		
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//メールアドレス重複確認SQLを準備
			String sqlExists = "SELECT COUNT(*) FROM account WHERE email = ?;";
			PreparedStatement pStmtExists = conn.prepareStatement(sqlExists);
			
			//メールアドレス重複確認SQLに挿入するプリペアードステートメント
			pStmtExists.setString(1,email);
			
			//重複確認SQLを実行
			ResultSet rsExists = pStmtExists.executeQuery();
			rsExists.next();
			if(rsExists.getInt("count(*)") == 0) {
				result = true;
			}
		
		//エラー処理
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
	
	//テスト用メソッド
	public List<Account> showAll(){
		Connection conn = null;
		List<Account> accountList = new ArrayList<Account>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sukima?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//アカウント取得SQL文を準備
			String sql = "SELECT * FROM account";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("account_id"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setNickname(rs.getString("nickname"));
				account.setCategoryId(rs.getInt("category_id"));
				account.setGoalDetail(rs.getString("goal_detail"));
				account.setCreatedAt(rs.getTimestamp("created_at"));
				account.setLoginAt(rs.getTimestamp("login_at"));
				account.setConsecutiveLogins(rs.getInt("consecutive_Logins"));
				accountList.add(account);
			}
		
		//エラー処理
		} catch (SQLException e) {
			e.printStackTrace();
			accountList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			accountList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					accountList = null;
				}
			}
		}

		// 結果を返す
		return accountList;
	}
	
	// パスワードをSHA-256でハッシュ化するメソッド
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // SHA-256アルゴリズムを取得
            byte[] hash = digest.digest(password.getBytes()); // パスワードをハッシュ化
            StringBuilder hexString = new StringBuilder(); // ハッシュ値を16進数に変換するためのStringBuilder
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b); // バイトを16進数に変換
                if (hex.length() == 1) {
                    hexString.append('0'); // 1桁の場合は0を追加
                }
                hexString.append(hex); // 16進数を追加
            }
            return hexString.toString(); // ハッシュ値を返す
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // アルゴリズムが見つからない場合の例外処理
        }
    }
	
	


}
