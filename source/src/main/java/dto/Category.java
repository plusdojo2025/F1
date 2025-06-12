package dto;

public class Category {
	// フィールド定義
    private int categoryId;			// カテゴリID
    private String categoryTitle;	// カテゴリタイトル
    
    // セッタ・ゲッタ
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

    
}
