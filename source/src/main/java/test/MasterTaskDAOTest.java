package test;

import java.util.List;
import java.util.Scanner;

import dao.MasterTaskDAO;
import dto.MasterTask;

public class MasterTaskDAOTest {
	
	public static void showAllData(List<MasterTask> masterTaskList) {
		for (MasterTask masterTask : masterTaskList) {
			System.out.println("タイトル：" + masterTask.getTitle());
			System.out.println("所要時間：" + masterTask.getTimeSpan());
			System.out.println("気分ID" + masterTask.getMoodId());
			System.out.println("作業ジャンルID" + masterTask.getCategoryId());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MasterTaskDAO mtdao = new MasterTaskDAO();
		Scanner scn = new Scanner(System.in);
		
		// selectAll()のテスト
		System.out.println("---------- selectAll()のテスト----------");
		MasterTaskDAOTest.showAllData(mtdao.selectAll());
		
		scn.close();
	}
}
