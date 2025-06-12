package dao;

import java.util.List;
import bean.Task;

public class TasksDAO {
	
	// アカウントIDをもとに、タスクを全件取得
	public List<Task> selectAll(int account_id) {
		
	}
	
	// 提案時のフィルター（自分のタスク）
	public List<Task> suggestTask(int account_id, time_span, mood_id, category_id) {
	
	}
	
	// 提案時のフィルター(他の人のタスク)
	public List<Task> suggestOtherTask(int account_id, time_span, mood_id, category_id) {
	
	}
	
	// 新規登録
	public boolean registTask(Task task) {
		
	}
	
	// 更新
	public boolean updateTask(Task task) {
		
	}
	
	// 削除
	public boolean deleteTask(Task task) {
		
	}
	
}
