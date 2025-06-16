package test;

import java.util.List;
import java.util.Scanner;

import dao.TasksDAO;
import dto.Task;
import dto.TaskList;

public class TasksDAOTest {
	public static void showAllData(List<Task> taskList) {
		for (Task task : taskList) {
			System.out.println("タスクID：" + task.getTaskId());
			System.out.println("アカウントID：" + task.getAccountId());
			System.out.println("タスクタイトル：" + task.getTitle());
			System.out.println("所要時間：" + task.getTimeSpan());
			System.out.println("気分ID：" + task.getMoodId());
			System.out.println("作業カテゴリID：" + task.getCategoryId());
			System.out.println("作成日時：" + task.getCreatedAt());
			System.out.println("公開設定：" + task.getIsPrivate());
			System.out.println();
		}
	}

	public static void showAll(List<TaskList> taskList) {
		for (TaskList task : taskList) {
			System.out.println("タスクID：" + task.getTaskId());
			System.out.println("アカウントID：" + task.getAccountId());
			System.out.println("タスクタイトル：" + task.getTitle());
			System.out.println("所要時間：" + task.getTimeSpan());
			System.out.println("気分名：" + task.getMoodTitle());
			System.out.println("作業カテゴリ名：" + task.getCategoryTitle());
			System.out.println("公開設定：" + task.getIsPrivate());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		TasksDAO tdao = new TasksDAO();
		Scanner scn = new Scanner(System.in);
		boolean judge = true;

		while (judge) {

			System.out.print(
					"行うメソッドを入力(s-selectAll(),g-suggestTask()2つ,r-registTask(),u-updateTask(),d-deleteTask(),end-テストを終了)");
			String test = scn.next();

			switch (test) {
			
			case "s":
				
				// selectAll()のテスト
				System.out.println("---------- selectAll()のテスト----------");

				// テスト情報を入力
				System.out.print("アカウントID:");
				int accountId = scn.nextInt();

				// 実行
				List<TaskList> TaskList = tdao.selectAll(accountId);
				TasksDAOTest.showAll(TaskList);

			case "g":
				
				// suggestTask()のテスト
				System.out.println("---------- suggestTask()のテスト ----------");

				// テスト情報を入力
				System.out.print("アカウントID:");
				accountId = scn.nextInt();
				System.out.print("所要時間(分):");
				int timeSpan = scn.nextInt();
				System.out.print("気分ID:");
				int moodId = scn.nextInt();
				System.out.print("作業ジャンルID:");
				int categoryId = scn.nextInt();

				// 実行
				List<Task> TaskList2 = tdao.suggestTask(accountId, timeSpan, moodId, categoryId);
				TasksDAOTest.showAllData(TaskList2);

				// suggestOtherTask()のテスト
				System.out.println("---------- suggestOtherTask()のテスト ----------");

				// 前項目で入力した内容で十個う
				List<Task> TaskList3 = tdao.suggestOtherTask(accountId, timeSpan, moodId, categoryId);
				TasksDAOTest.showAllData(TaskList3);

			case "r":
				
				// registTask()のテスト
				System.out.println("---------- registTask()のテスト ----------");

				// テスト情報を入力
				System.out.print("登録アカウントID:");
				accountId = scn.nextInt();
				System.out.print("タイトル:");
				String title = scn.next();
				System.out.print("所要時間(分):");
				timeSpan = scn.nextInt();
				System.out.print("気分ID:");
				moodId = scn.nextInt();
				System.out.print("作業ジャンルID:");
				categoryId = scn.nextInt();
				System.out.print("公開設定:");
				boolean isPrivate = scn.nextBoolean();

				// テスト情報をクラスに格納
				Task regRec = new Task();
				regRec.setAccountId(accountId);
				regRec.setTitle(title);
				regRec.setTimeSpan(timeSpan);
				regRec.setMoodId(moodId);
				regRec.setCategoryId(categoryId);
				regRec.setIsPrivate(isPrivate);

				// 実行
				if (tdao.registTask(regRec)) {
					System.out.println("登録成功！");
					TasksDAOTest.showAll(tdao.selectAll(accountId));
				} else {
					System.out.println("登録失敗！");
				}

			case "u":
				
				// updatetask
				System.out.println("---------- updateTask()のテスト ----------");

				// テスト情報を入力
				System.out.print("アカウントID:");
				accountId = scn.nextInt();

				// 実行
				List<TaskList> TaskList4 = tdao.selectAll(accountId);
				TasksDAOTest.showAll(TaskList4);

				// テスト情報を入力
				System.out.print("タスクID:");
				int taskId = scn.nextInt();
				System.out.print("タイトル:");
				title = scn.next();
				System.out.print("所要時間(分):");
				timeSpan = scn.nextInt();
				System.out.print("気分ID:");
				moodId = scn.nextInt();
				System.out.print("作業ジャンルID:");
				categoryId = scn.nextInt();
				System.out.print("公開設定:");
				isPrivate = scn.nextBoolean();

				// テスト情報をクラスに格納
				Task upRec = new Task();
				upRec.setTaskId(taskId);
				upRec.setTitle(title);
				upRec.setTimeSpan(timeSpan);
				upRec.setMoodId(moodId);
				upRec.setCategoryId(categoryId);
				upRec.setIsPrivate(isPrivate);

				// 実行
				if (tdao.updateTask(upRec)) {
					System.out.println("更新成功！");
					TasksDAOTest.showAll(tdao.selectAll(accountId));
				} else {
					System.out.println("更新失敗！");
				}

			case "d":
				
				// deleteTask()のテスト
				System.out.println("---------- deleteTask()のテスト ----------");

				// テスト情報を入力
				System.out.print("アカウントID:");
				accountId = scn.nextInt();

				// 実行
				List<TaskList> TaskList5 = tdao.selectAll(accountId);
				TasksDAOTest.showAll(TaskList5);

				// テスト情報を入力
				System.out.print("タスクID:");
				taskId = scn.nextInt();

				// 実行
				if (tdao.deleteTask(taskId)) {
					System.out.println("削除成功！");
					TasksDAOTest.showAll(tdao.selectAll(accountId));
				} else {
					System.out.println("削除失敗！");
				}

			default:
				judge = false;
			}
		}
		scn.close();

	}
}
