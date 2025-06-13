package test;

import java.util.List;
import java.util.Scanner;

import dao.TasksDAO;
import dto.Task;

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

	public static void main(String[] args) {
		TasksDAO tdao = new TasksDAO();
		Scanner scn = new Scanner(System.in);

		// selectAll()のテスト
		System.out.println("---------- selectAll()のテスト----------");
		System.out.print("アカウントID:");
		int accountId = scn.nextInt();

		List<Task> TaskList1 = tdao.selectAll(accountId);
		TasksDAOTest.showAllData(TaskList1);

		// suggestTask()のテスト
		System.out.println("---------- suggestTask()のテスト ----------");

		System.out.print("アカウントID:");
		accountId = scn.nextInt();
		System.out.print("所要時間(分):");
		int timeSpan = scn.nextInt();
		System.out.print("気分ID:");
		int moodId = scn.nextInt();
		System.out.print("作業ジャンルID:");
		int categoryId = scn.nextInt();

		List<Task> TaskList2 = tdao.suggestTask(accountId, timeSpan, moodId, categoryId);
		TasksDAOTest.showAllData(TaskList2);

		// suggestOtherTask()のテスト
		System.out.println("---------- suggestOtherTask()のテスト ----------");

		List<Task> TaskList3 = tdao.suggestOtherTask(accountId, timeSpan, moodId, categoryId);
		TasksDAOTest.showAllData(TaskList3);

		// registTask()のテスト
		System.out.println("---------- registTask()のテスト ----------");

		System.out.print("更新アカウントID:");
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

		Task regRec = new Task();
		regRec.setAccountId(accountId);
		regRec.setTitle(title);
		regRec.setTimeSpan(timeSpan);
		regRec.setMoodId(moodId);
		regRec.setCategoryId(categoryId);
		regRec.setIsPrivate(isPrivate);
		
		if (tdao.registTask(regRec)) {
			System.out.println("登録成功！");
			TasksDAOTest.showAllData(tdao.selectAll(accountId));
		} else {
			System.out.println("登録失敗！");
		}

		// updatetask
		System.out.println("---------- updateTask()のテスト ----------");

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

		Task upRec = new Task();
		upRec.setTaskId(taskId);
		upRec.setTitle(title);
		upRec.setTimeSpan(timeSpan);
		upRec.setMoodId(moodId);
		upRec.setCategoryId(categoryId);
		upRec.setIsPrivate(isPrivate);
		
		if (tdao.updateTask(upRec)) {
			System.out.println("更新成功！");
			TasksDAOTest.showAllData(tdao.selectAll(accountId));
		} else {
			System.out.println("更新失敗！");
		}

		// deleteTask()のテスト
		System.out.println("---------- deleteTask()のテスト ----------");
		
		System.out.print("タスクID:");
		taskId = scn.nextInt();
		
		Task delRec = new Task();
		delRec.setTaskId(taskId);
		
		if (tdao.deleteTask(delRec)) {
			System.out.println("削除成功！");
			TasksDAOTest.showAllData(tdao.selectAll(accountId));
		} else {
			System.out.println("削除失敗！");
		}
		
		scn.close();

	}
}
