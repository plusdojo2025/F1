package test;

import java.util.List;
import java.util.Scanner;

import dao.AccountDAO;
import dto.Account;

public class AccountDAOTest {

	public static void showAllData(List<Account> accountList) {
		for (Account account : accountList) {
			System.out.println("アカウントID：" + account.getAccountId());
			System.out.println("メールアドレス：" + account.getEmail());
			System.out.println("パスワード：" + account.getPassword());
			System.out.println("ニックネーム：" + account.getNickname());
			System.out.println("目標作業ジャンル：" + account.getCategoryId());
			System.out.println("目標詳細：" + account.getGoalDetail());
			System.out.println("登録日時：" + account.getCreatedAt());
			System.out.println("ログイン日時：" + account.getLoginAt());
			System.out.println("連続ログイン：" + account.getConsecutiveLogins());
			System.out.println();
		}
	}

	public static void showAllData(Account account) {
		System.out.println("アカウントID：" + account.getAccountId());
		System.out.println("メールアドレス：" + account.getEmail());
		System.out.println("パスワード：" + account.getPassword());
		System.out.println("ニックネーム：" + account.getNickname());
		System.out.println("目標作業ジャンル：" + account.getCategoryId());
		System.out.println("目標詳細：" + account.getGoalDetail());
		System.out.println("登録日時：" + account.getCreatedAt());
		System.out.println("ログイン日時：" + account.getLoginAt());
		System.out.println("連続ログイン：" + account.getConsecutiveLogins());
		System.out.println();
	}

	public static void main(String[] args) {
		AccountDAO adao = new AccountDAO();
		Scanner scn = new Scanner(System.in);
		boolean judge = true;

		while (judge) {
			AccountDAOTest.showAllData(adao.showAll());

			System.out.println("行うメソッドを入力(g-getAccount(),r-registAccount(),u-updateAccount(),end-テストを終了)");
			String test = scn.next();

			switch (test) {
			
			case "g":
				// getaccount()のテスト1
				System.out.println("---------- getAccount()のテスト----------");

				// テスト情報入力
				System.out.print("メールアドレス:");
				String email = scn.next();
				System.out.print("パスワード:");
				String password = scn.next();
				
				//実行
				Account getAccount = adao.getAccount(email, password);
				AccountDAOTest.showAllData(getAccount);

			case "r":
				// registAccount()のテスト
				System.out.println("---------- registAccount()のテスト ----------");

				// テスト情報入力
				System.out.print("メールアドレス:");
				email = scn.next();
				System.out.print("パスワード:");
				password = scn.next();
				System.out.print("ニックネーム:");
				String nickname = scn.next();
				System.out.print("目標作業ジャンル:");
				int categoryId = scn.nextInt();
				System.out.print("目標詳細:");
				String goalDetail = scn.next();

				// テスト情報をクラスにセット
				Account regRec = new Account();
				regRec.setEmail(email);
				regRec.setPassword(password);
				regRec.setNickname(nickname);
				regRec.setCategoryId(categoryId);
				regRec.setGoalDetail(goalDetail);
				
				// 実行
				if (adao.registAccount(regRec) > 0) {
					System.out.println("登録成功！");
					AccountDAOTest.showAllData(adao.showAll());
				} else {
					System.out.println("登録失敗！");
				}

			case "u":
				// updateAccount()のテスト
				System.out.println("---------- updateAccount()のテスト ----------");

				// テスト情報入力
				System.out.print("更新アカウントID:");
				int accountId = scn.nextInt();
				System.out.print("メールアドレス:");
				email = scn.next();
				System.out.print("パスワード:");
				password = scn.next();
				System.out.print("ニックネーム:");
				nickname = scn.next();
				System.out.print("目標作業ジャンル:");
				categoryId = scn.nextInt();
				System.out.print("目標詳細:");
				goalDetail = scn.next();

				// テスト情報をクラスにセット
				Account upRec = new Account();
				upRec.setAccountId(accountId);
				upRec.setEmail(email);
				upRec.setPassword(password);
				upRec.setNickname(nickname);
				upRec.setCategoryId(categoryId);
				upRec.setGoalDetail(goalDetail);

				// 第二引数用（emailCheckフラグ）
				Boolean flg = false;
				// 実行
				if (adao.updateAccount(upRec, flg, flg)) {
					System.out.println("更新成功！");
					AccountDAOTest.showAllData(adao.showAll());
				} else {
					System.out.println("更新失敗！");
				}

			default:
				judge = false;
			}
		}

		scn.close();
	}
}
