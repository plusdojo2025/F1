package dto;

import java.io.Serializable;

public class Mood implements Serializable {
	// フィールド定義
    private int moodId;			// 気分ID
    private String moodTitle;	// 気分タイトル
    
    // コンストラクタ
    public Mood(int moodId, String moodTitle) {
		super();
		this.moodId = moodId;
		this.moodTitle = moodTitle;
	}
	public Mood() {
	}
	// セッタ・ゲッタ
	public int getMoodId() {
		return moodId;
	}
	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}
	public String getMoodTitle() {
		return moodTitle;
	}
	public void setMoodTitle(String moodTitle) {
		this.moodTitle = moodTitle;
	}


}
