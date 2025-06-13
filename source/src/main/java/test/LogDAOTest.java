package test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import dao.LogDAO;
import dto.Category;
import dto.Log;
import dto.Mood;

public class LogDAOTest {
	public static void showAllData(List<Log> logList) {
		for (Log log : logList) {
			System.out.println("ログID：" + log.getLogId());
			System.out.println("アカウントID：" + log.getAccountId());
			System.out.println("タスクID：" + log.getTaskId());
			System.out.println("タスク開始日時：" + log.getLogTime());
			System.out.println("タスク実行日時：" + log.getDuration());
			System.out.println("満足度：" + log.getSatisfactionLevel());
		}
	}

	public static void main(String[] args) {
		LogDAO ldao = new LogDAO();
		Scanner scn = new Scanner(System.in);

		// selectLogs()のテスト
		System.out.println("---------- selectLogs()のテスト----------");
		System.out.print("アカウントID");
		int accountId = scn.nextInt();

		List<Log> selectLogs = ldao.selectLogs(accountId);
		LogDAOTest.showAllData(selectLogs);

		// registLog()のテスト
		System.out.println("---------- registLog()のテスト ----------");

		System.out.print("アカウントID:");
		accountId = scn.nextInt();
		System.out.print("タスクID:");
		int taskId = scn.nextInt();
		Timestamp LogTime = new Timestamp(System.currentTimeMillis());
		System.out.print("所要時間:");
		int duration = scn.nextInt();
		System.out.print("満足度:");
		Integer satisfactionLevel = scn.nextInt();

		Log regRec = new Log();
		regRec.setAccountId(accountId);
		regRec.setTaskId(taskId);
		regRec.setLogTime(LogTime);
		regRec.setDuration(duration);
		regRec.setSatisfactionLevel(satisfactionLevel);
		
		
		if (ldao.registLog(regRec)) {
			System.out.println("登録成功！");
			LogDAOTest.showAllData(ldao.selectLogs(accountId));
		} else {
			System.out.println("登録失敗！");
		}

		System.out.print("アカウントID:");
		accountId = scn.nextInt();

		// sumDuration()のテスト
		System.out.println("---------- sumDuration()のテスト ----------");
		int sumDuration = ldao.sumDuration(accountId);
		System.out.println("総合計所要時間:" + sumDuration);

		// getMaxMood()のテスト
		System.out.println("---------- getMaxMood()のテスト ----------");
		Mood mood = ldao.getMaxMood(accountId);
		System.out.println("最頻気分ID:" + mood.getMoodId());
		System.out.println("最頻気分名:" + mood.getMoodTitle());

		// getMaxCategory()のテスト
		System.out.println("---------- getMaxCategory()のテスト ----------");
		Category category = ldao.getMaxCategory(accountId);
		System.out.println("最頻作業ジャンルID:" + category.getCategoryId());
		System.out.println("最頻作業ジャンル名:" + category.getCategoryTitle());

		scn.close();
	}

}
