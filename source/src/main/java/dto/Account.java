package dto;

import java.sql.Timestamp;

public class Account {
	// フィールド定義
    private int accountId;			// アカウントID
    private String email;			// メールアドレス
    private String password;		// パスワード
    private String nickname;		// ニックネーム
    private int categoryId;			// 目標ジャンル（作業ジャンル）
    private String goalDetail;		// 目標内容
    private Timestamp createdAt;	// 登録日時
    private Timestamp loginAt;		// ログイン日時
    private int consecutiveLogins;	// 連続ログイン

    // コンストラクタ
	public Account() {
	}
	
	// セッタ・ゲッタ
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getGoalDetail() {
		return goalDetail;
	}
	public void setGoalDetail(String goalDetail) {
		this.goalDetail = goalDetail;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(Timestamp loginAt) {
		this.loginAt = loginAt;
	}
	
	public int getConsecutiveLogins() {
		return consecutiveLogins;
	}
	public void setConsecutiveLogins(int consecutiveLogins) {
		this.consecutiveLogins = consecutiveLogins;
	}

}
