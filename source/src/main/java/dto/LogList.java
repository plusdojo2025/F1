package dto;

import java.io.Serializable;

public class LogList implements Serializable {
	// フィールド定義
    private int logId;					// ログID
    private String title;				// タスクタイトル
    private int duration;				// タスク実行時間
    private String moodTitle;			// 気分タイトル
    private String categoryTitle;		// カテゴリータイトル
    private Integer satisfactionLevel; 	// 満足度（NULL許容のためラッパークラス）
    
    // コンストラクタ定義
	public LogList() {}

	// ゲッタ・セッタ定義
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public Integer getSatisfactionLevel() {
		return satisfactionLevel;
	}

	public void setSatisfactionLevel(Integer satisfactionLevel) {
		this.satisfactionLevel = satisfactionLevel;
	}    
    
}
