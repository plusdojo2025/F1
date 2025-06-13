package test;

import java.util.List;
import java.util.Scanner;

import dao.CategoryDAO;
import dto.Category;

public class CategoryDAOTest {
	public static void showAllData(List<Category> categoryList) {
		for (Category category : categoryList) {
			System.out.println("作業ジャンルID：" + category.getCategoryId());
			System.out.println("作業ジャンル名：" + category.getCategoryTitle());
			System.out.println();
		}
	}
	
	public static void showAllData(Category category) {
			System.out.println("作業ジャンルID：" + category.getCategoryId());
			System.out.println("作業ジャンル名：" + category.getCategoryTitle());
			System.out.println();
	}


	public static void main(String[] args) {
		CategoryDAO cdao = new CategoryDAO();
		Scanner scn = new Scanner(System.in);
		
		// getCategoryList()のテスト
		System.out.println("---------- getCategoryList()のテスト----------");
		CategoryDAOTest.showAllData(cdao.getCategoryList());

		// getCategory()のテスト
		System.out.println("---------- getCategory()のテスト----------");
		
		//テスト情報を入力
		System.out.print("作業ジャンルID:");
		int categoryId = scn.nextInt();
		
		//実行
		Category category = cdao.getCategory(categoryId);
		CategoryDAOTest.showAllData(category);

		scn.close();
	}
}
