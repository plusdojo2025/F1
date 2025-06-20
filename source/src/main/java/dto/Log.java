package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Log implements Serializable {
	// フィールド定義
    private int logId;					// ログID
    private int accountId;				// アカウントID
    private String taskTitle;			// タスクタイトル
    private int moodId;					// 気分ID
	private int categoryId;				// 作業カテゴリID
    private Timestamp logTime;			// タスク開始日時
    private int duration;				// タスク実行時間
    private Integer satisfactionLevel; 	// 満足度（NULL許容のためラッパークラス）
    private Task task;					// taskオブジェクト
    
    // コンストラクタ
	public Log() {
	}

	// セッタ・ゲッタ
    public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
    public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public int getMoodId() {
		return moodId;
	}
	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Integer getSatisfactionLevel() {
		return satisfactionLevel;
	}
	public void setSatisfactionLevel(Integer satisfactionLevel) {
		this.satisfactionLevel = satisfactionLevel;
	}

	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
}
