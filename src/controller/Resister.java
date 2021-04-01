package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDAO;
import dao.MealsDAO;
import model.Food;
import model.Meals;
import model.MealsLogic;

@WebServlet("/Resister")
public class Resister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=request.getParameter("date");
		FoodDAO dao=new FoodDAO();
		if(date != null) {
			//List<Food>に取得した日付の献立を詰める
			List<Food> list=dao.findToday(date);
			MealsDAO mealsdao=new MealsDAO();
			//mealsテーブルに取得した日付のデータがあるか調べる
			Meals meals=mealsdao.findOneMeals(date);
			MealsLogic ml=new MealsLogic();

			//mealsテーブルに既にデータがあるかをdateで判定し、あれば更新処理
			if(meals.getDate() != null) {
				ml.execute(list,meals,date);
				mealsdao.updateMeals(meals);
			//なければ新規挿入
			}else {
				ml.execute(list, meals,date);
				mealsdao.insertMeals(meals);
			}
		}else {}
		/*
		if(meals != null) {
			ml.execute(list,meals);
			mealsdao.updateMeals(meals);
		}else {
			ml.execute(list, meals);
			mealsdao.insertMeals(meals);
		}
		*/

		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
		rd.forward(request, response);
	}

}
