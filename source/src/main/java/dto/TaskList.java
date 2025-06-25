package dto;

import java.io.Serializable;

public class TaskList implements Serializable {
	// フィールド定義
    private int taskId;				// タスクID
    private int accountId;			// アカウントID
    private String title;			// タスクタイトル
    private int timeSpan;			// 所要時間
    private String moodTitle;		// 気分名
    private int moodId;
    private String categoryTitle;	//　作業カテゴリ名
    private int categoryId;
	private boolean isPrivate;		// 公開設定
	
	// コンストラクタ
	public TaskList() {
		super();
	}
	
	// ゲッタ・セッタ
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

	public String getMoodTitle() {
		return moodTitle;
	}

	public void setMoodTitle(String moodTitle) {
		this.moodTitle = moodTitle;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
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

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
}
