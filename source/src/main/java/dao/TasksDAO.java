package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Task;
import dto.TaskList;

public class TasksDAO {
	
	// アカウントIDをもとに、タスクを全件取得
	public List<TaskList> selectAll(int account_id){
		Connection conn = null;
		List<TaskList> taskList = new ArrayList<TaskList>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//タスク全件取得SQL文を準備
			String sqlSelect = "SELECT task_id,account_id,title,time_span,mood_title,category_title,is_private "
					+ "FROM tasks AS t "
					+ "INNER JOIN mood As m ON t.mood_id = m.mood_id "
					+ "INNER JOIN category As c ON t.category_id = c.category_id "
					+ "WHERE account_id = ?;";
			PreparedStatement pStmtSelect = conn.prepareStatement(sqlSelect);
			
			//タスク全件取得SQlに挿入するプリペアードステートメント
			pStmtSelect.setInt(1, account_id);
			
			//タスク全件取得SQLを実行
			ResultSet rsSelect = pStmtSelect.executeQuery();
			
			//SQL結果をdtoにセット
			while(rsSelect.next()) {
				TaskList task = new TaskList();
				task.setTaskId(rsSelect.getInt("task_id"));
				task.setAccountId(rsSelect.getInt("account_id"));
				task.setTitle(rsSelect.getString("title"));
				task.setTimeSpan(rsSelect.getInt("time_span"));
				task.setMoodTitle(rsSelect.getString("mood_title"));
				task.setCategoryTitle(rsSelect.getString("category_title"));
				task.setIsPrivate(rsSelect.getBoolean("is_private"));
				taskList.add(task);
			}
		//エラー処理
		} catch (SQLException e) {
			e.printStackTrace();
			taskList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			taskList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					taskList = null;
				}
			}
		}

		// 結果を返す
		return taskList;
		
	}
	
	// taskIDをもとに、1件のタスクを取得
	public Task getTask(int task_id){
		Connection conn = null;
		
		// DAOの生成
		MoodDAO mdao = new MoodDAO();
		CategoryDAO cdao = new CategoryDAO();
		
		// taskインスタンスの生成
		Task task = new Task();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//1件のタスクを取得するSQLを準備
			String sqlSelect = "SELECT * FROM tasks WHERE task_id = ?;";
			PreparedStatement pStmtSelect = conn.prepareStatement(sqlSelect);
			pStmtSelect.setInt(1, task_id);
			
			ResultSet rsSelect = pStmtSelect.executeQuery();
			
			if (rsSelect.next()) {
				task.setTaskId(rsSelect.getInt("task_id"));
				task.setAccountId(rsSelect.getInt("account_id"));
				task.setTitle(rsSelect.getString("title"));
				task.setTimeSpan(rsSelect.getInt("time_span"));
				task.setMoodId(rsSelect.getInt("mood_id"));
				task.setCategoryId(rsSelect.getInt("category_id"));
				task.setCreatedAt(rsSelect.getTimestamp("created_at"));
				task.setIsPrivate(rsSelect.getBoolean("is_private"));
				task.setMood(mdao.getMood(rsSelect.getInt("mood_id")));
				task.setCategory(cdao.getCategory(rsSelect.getInt("category_id")));
			}
            
			rsSelect.close();

		} catch (SQLException e) {
			e.printStackTrace();
			task = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			task = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					task = null;
				}
			}
		}

		// 結果を返す
		return task;
	}
	
	// 提案時のフィルター（自分のタスク）
	public List<Task> suggestTask(int account_id,int time_span,int mood_id,int category_id){
		Connection conn = null;
		List<Task> tasks = new ArrayList<Task>();
		MoodDAO mdao = new MoodDAO();
		CategoryDAO cdao = new CategoryDAO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//自タスク提案(気分一致)SQLの準備
			String sqlSuggest1 = "SELECT * FROM tasks "
					+ "WHERE account_id = ? "
					+ "AND mood_id = ? "
					+ "AND category_id = ? "
					+ "AND time_span BETWEEN (? - 5) AND ? "
					+ "ORDER BY RAND();";
			PreparedStatement pStmtSuggest1 = conn.prepareStatement(sqlSuggest1);
			
			//自タスク提案(気分一致)SQLに挿入するプリペアードステートメント
			pStmtSuggest1.setInt(1, account_id);
			pStmtSuggest1.setInt(2, mood_id);
			pStmtSuggest1.setInt(3, category_id);
			pStmtSuggest1.setInt(4, time_span);
			pStmtSuggest1.setInt(5, time_span);
			
			//自タスク提案(気分一致)SQLを実行
			ResultSet rsSuggest1 = pStmtSuggest1.executeQuery();
			
			//SQLの結果をdtoに格納
			while(rsSuggest1.next()) {
				Task task = new Task();
				task.setTaskId(rsSuggest1.getInt("task_id"));
				task.setAccountId(rsSuggest1.getInt("account_id"));
				task.setTitle(rsSuggest1.getString("title"));
				task.setTimeSpan(rsSuggest1.getInt("time_span"));
				task.setMoodId(rsSuggest1.getInt("mood_id"));
				task.setCategoryId(rsSuggest1.getInt("category_id"));
				task.setCreatedAt(rsSuggest1.getTimestamp("created_at"));
				task.setIsPrivate(rsSuggest1.getBoolean("is_private"));
				task.setMood(mdao.getMood(rsSuggest1.getInt("mood_id")));
				task.setCategory(cdao.getCategory(rsSuggest1.getInt("category_id")));
				tasks.add(task);
			}
			
			//自タスク提案(気分不一致)SQLの準備
			String sqlSuggest2 = "SELECT * FROM tasks "
					+ "WHERE account_id = ? "
					+ "AND mood_id <> ? "
					+ "AND category_id = ? "
					+ "AND time_span BETWEEN (? - 5) AND ? "
					+ "ORDER BY RAND();";
			PreparedStatement pStmtSuggest2 = conn.prepareStatement(sqlSuggest2);
			
			//自タスク提案(気分不一致)SQLに挿入するプリペアードステートメント
			pStmtSuggest2.setInt(1, account_id);
			pStmtSuggest2.setInt(2, mood_id);
			pStmtSuggest2.setInt(3, category_id);
			pStmtSuggest2.setInt(4, time_span);
			pStmtSuggest2.setInt(5, time_span);
			
			//自タスク提案(気分不一致)SQLを実行
			ResultSet rsSuggest2 = pStmtSuggest2.executeQuery();
			
			//SQLの結果をdtoに格納
			while(rsSuggest2.next()) {
				Task task = new Task();
				task.setTaskId(rsSuggest2.getInt("task_id"));
				task.setAccountId(rsSuggest2.getInt("account_id"));
				task.setTitle(rsSuggest2.getString("title"));
				task.setTimeSpan(rsSuggest2.getInt("time_span"));
				task.setMoodId(rsSuggest2.getInt("mood_id"));
				task.setCategoryId(rsSuggest2.getInt("category_id"));
				task.setCreatedAt(rsSuggest2.getTimestamp("created_at"));
				task.setIsPrivate(rsSuggest2.getBoolean("is_private"));
				task.setMood(mdao.getMood(rsSuggest2.getInt("mood_id")));
				task.setCategory(cdao.getCategory(rsSuggest2.getInt("category_id")));
				tasks.add(task);
			}
		
		//エラー処理
		} catch (SQLException e) {
			e.printStackTrace();
			tasks = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			tasks = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					tasks = null;
				}
			}
		}

		// 結果を返す
		return tasks;
	}
	
	// 提案時のフィルター(他の人のタスク)
	public List<Task> suggestOtherTask(int account_id,int time_span,int mood_id,int category_id){
		
		Connection conn = null;
		List<Task> tasks = new ArrayList<Task>();
		MoodDAO mdao = new MoodDAO();
		CategoryDAO cdao = new CategoryDAO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//他タスク提案(気分一致)SQLの準備
			String sqlSuggest1 = "SELECT * FROM tasks "
					+ "WHERE account_id <> ? "
					+ "AND mood_id = ? "
					+ "AND category_id = ? "
					+ "AND time_span BETWEEN (? - 5) AND ? "
					+ "ORDER BY RAND();";
			PreparedStatement pStmtSuggest1 = conn.prepareStatement(sqlSuggest1);
			
			//他タスク提案(気分一致)SQLに挿入するプリペアードステートメント
			pStmtSuggest1.setInt(1, account_id);
			pStmtSuggest1.setInt(2, mood_id);
			pStmtSuggest1.setInt(3, category_id);
			pStmtSuggest1.setInt(4, time_span);
			pStmtSuggest1.setInt(5, time_span);
			
			//他タスク提案(気分一致)SQLの実行
			ResultSet rsSuggest1 = pStmtSuggest1.executeQuery();
			
			//SQLの結果をdtoに格納
			while(rsSuggest1.next()) {
				Task task = new Task();
				task.setTaskId(rsSuggest1.getInt("task_id"));
				task.setAccountId(rsSuggest1.getInt("account_id"));
				task.setTitle(rsSuggest1.getString("title"));
				task.setTimeSpan(rsSuggest1.getInt("time_span"));
				task.setMoodId(rsSuggest1.getInt("mood_id"));
				task.setCategoryId(rsSuggest1.getInt("category_id"));
				task.setCreatedAt(rsSuggest1.getTimestamp("created_at"));
				task.setIsPrivate(rsSuggest1.getBoolean("is_private"));
				task.setMood(mdao.getMood(rsSuggest1.getInt("mood_id")));
				task.setCategory(cdao.getCategory(rsSuggest1.getInt("category_id")));
				tasks.add(task);
			}
			
			//他タスク提案(気分不一致)SQLの準備
			String sqlSuggest2 = "SELECT * FROM tasks "
					+ "WHERE account_id <> ? "
					+ "AND mood_id <> ? "
					+ "AND category_id = ? "
					+ "AND time_span BETWEEN (? - 5) AND ? "
					+ "ORDER BY RAND();";
			PreparedStatement pStmtSuggest2 = conn.prepareStatement(sqlSuggest2);
			
			//他タスク提案(気分不一致)SQLに挿入するプリペアードステートメント
			pStmtSuggest2.setInt(1, account_id);
			pStmtSuggest2.setInt(2, mood_id);
			pStmtSuggest2.setInt(3, category_id);
			pStmtSuggest2.setInt(4, time_span);
			pStmtSuggest2.setInt(5, time_span);
			
			//他タスク提案(気分不一致)SQLを実行
			ResultSet rsSuggest2 = pStmtSuggest2.executeQuery();
			
			//SQLの結果をdtoに格納
			while(rsSuggest2.next()) {
				Task task = new Task();
				task.setTaskId(rsSuggest2.getInt("task_id"));
				task.setAccountId(rsSuggest2.getInt("account_id"));
				task.setTitle(rsSuggest2.getString("title"));
				task.setTimeSpan(rsSuggest2.getInt("time_span"));
				task.setMoodId(rsSuggest2.getInt("mood_id"));
				task.setCategoryId(rsSuggest2.getInt("category_id"));
				task.setCreatedAt(rsSuggest2.getTimestamp("created_at"));
				task.setIsPrivate(rsSuggest2.getBoolean("is_private"));
				task.setMood(mdao.getMood(rsSuggest2.getInt("mood_id")));
				task.setCategory(cdao.getCategory(rsSuggest2.getInt("category_id")));
				tasks.add(task);
			}
			
		//エラー処理
		} catch (SQLException e) {
			e.printStackTrace();
			tasks = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			tasks = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					tasks = null;
				}
			}
		}

		// 結果を返す
		return tasks;
	}
	
	// 新規登録
	public boolean registTask(Task task){
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//タスク新規登録SQLの準備
			String sqlRegist = "INSERT INTO tasks (task_id,account_id,title,time_span,mood_id,category_id,is_private) VALUES (0,?,?,?,?,?,?);";
			PreparedStatement pStmtRegist = conn.prepareStatement(sqlRegist);
				
			//タスク新規登録SQLに挿入するプリペアードステートメント
			pStmtRegist.setInt(1,task.getAccountId());
			pStmtRegist.setString(2,task.getTitle());
			pStmtRegist.setInt(3,task.getTimeSpan());
			pStmtRegist.setInt(4,task.getMoodId());
			pStmtRegist.setInt(5,task.getCategoryId());
			pStmtRegist.setBoolean(6,task.getIsPrivate());
			
			//タスク新規登録SQLを実行
			if (pStmtRegist.executeUpdate() == 1) {
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
	
	// 更新
	public boolean updateTask(Task task){
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//タスク情報更新SQLの準備
			String sqlUpdate = "UPDATE tasks SET "
					+ "    title = ?,"
					+ "    time_span = ?,"
					+ "    mood_id = ?,"
					+ "    category_id = ?,"
					+ "    is_private = ? "
					+ "    WHERE task_id = ?;";
			PreparedStatement pStmtUpdate = conn.prepareStatement(sqlUpdate);
			
			//タスク情報更新SQLに挿入するプリペアードステートメント
			pStmtUpdate.setString(1,task.getTitle());
			pStmtUpdate.setInt(2,task.getTimeSpan());
			pStmtUpdate.setInt(3,task.getMoodId());
			pStmtUpdate.setInt(4,task.getCategoryId());
			pStmtUpdate.setBoolean(5,task.getIsPrivate());
			pStmtUpdate.setInt(6,task.getTaskId());
			
			//タスク情報更新SQLを実行
			if (pStmtUpdate.executeUpdate() == 1) {
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
	
	// 削除
	public boolean deleteTask(int task_id){
		Connection conn = null;
		boolean result = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/f1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//タスク削除SQLの準備
			String sqlDelete = "DELETE FROM tasks WHERE task_id = ?;";
			PreparedStatement pStmtDelete = conn.prepareStatement(sqlDelete);
				
			//タスク削除SQLに挿入するプリペアードステートメント
			pStmtDelete.setInt(1,task_id);
			
			//タスク削除SQLを実行
			if (pStmtDelete.executeUpdate() == 1) {
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
	
}
