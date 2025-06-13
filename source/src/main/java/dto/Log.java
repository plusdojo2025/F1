package dto;

import java.sql.Timestamp;

public class Log {
	// フィールド定義
    private int logId;					// ログID
    private int accountId;				// アカウントID
    private int taskId;					// タスクID
    private Timestamp logTime;			// タスク開始日時
    private int duration;				// タスク実行時間
    private Integer satisfactionLevel; 	// 満足度（NULL許容のためラッパークラス）
    
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
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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

}
