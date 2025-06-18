package dto;

import java.io.Serializable;

public class MasterTask implements Serializable {
	// フィールド定義
    private int masterTaskId;	// マスタータスクID
    private String title;		// マスタータスクタイトル
    private int timeSpan;		// 所要時間
    private int moodId;			// 気分ID
    private int categoryId;		// カテゴリID
    
    public MasterTask() {
    	
    }
    
    // コンストラクタ
    public MasterTask(int masterTaskId, String title, int timeSpan, int moodId, int categoryId) {
		super();
		this.masterTaskId = masterTaskId;
		this.title = title;
		this.timeSpan = timeSpan;
		this.moodId = moodId;
		this.categoryId = categoryId;
	}
	// セッタ・ゲッタ
	public int getMasterTaskId() {
		return masterTaskId;
	}
	public void setMasterTaskId(int masterTaskId) {
		this.masterTaskId = masterTaskId;
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


}
