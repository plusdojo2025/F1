package test;

import java.util.List;
import java.util.Scanner;

import dao.MoodDAO;
import dto.Mood;

public class MoodDAOTest {
	public static void showAllData(List<Mood> moodList) {
		for (Mood mood : moodList) {
			System.out.println("気分ID：" + mood.getMoodId());
			System.out.println("気分名：" + mood.getMoodTitle());
			System.out.println();
		}
	}
	
	public static void showAllData(Mood mood) {
			System.out.println("気分：" + mood.getMoodId());
			System.out.println("気分：" + mood.getMoodTitle());
			System.out.println();
	}


	public static void main(String[] args) {
		MoodDAO mdao = new MoodDAO();
		Scanner scn = new Scanner(System.in);
		
		// getMoodList()のテスト
		System.out.println("---------- getMoodList()のテスト----------");
		MoodDAOTest.showAllData(mdao.getMoodList());

		// getMood()のテスト
		System.out.println("---------- getMood()のテスト----------");
		System.out.print("気分ID:");
		int moodId = scn.nextInt();

		Mood mood = mdao.getMood(moodId);
		MoodDAOTest.showAllData(mood);

		scn.close();
	}
}
