package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Task implements Serializable {
	// フィールド定義
    private int taskId;				// タスクID
    private int accountId;			// アカウントID
    private String title;			// タスクタイトル
    private int timeSpan;			// 所要時間
    private int moodId;				// 気分ID
    private int categoryId;			// 作業カテゴリID
    private Timestamp createdAt;	// 作成日
    private boolean isPrivate;		// 公開設定
    private Mood mood;				// 気分オブジェクト
    private Category category;		// 作業カテゴリオブジェクト

    // コンストラクタ
	public Task() {
	}

	// セッタ・ゲッタ
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getTimeSpan() {
		return timeSpan;
	}
	public void setTimeSpan(int timeSpan) {
		this.timeSpan = timeSpan;
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
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public Mood getMood() {
		return mood;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

}
