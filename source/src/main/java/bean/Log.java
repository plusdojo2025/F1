package bean;

import java.sql.Timestamp;

public class Log {
	// フィールド定義
    private int logId;
    private int accountId;
    private int taskId;
    private Timestamp logTime;
    private int duration;
    private Integer satisfactionLevel; // NULL許容のためラッパークラス
	
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
