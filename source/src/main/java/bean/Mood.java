package bean;

public class Mood {
	// フィールド定義
    private int moodId;			// 気分ID
    private String moodTitle;	// 気分タイトル
    
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
