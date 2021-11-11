package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ResultDAO;
import model.Result;

@WebServlet("/MakeResult")
public class MakeResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr=request.getParameter("date");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String msg="";
		String userid=request.getParameter("userid");
		try {
			Date date=dateFormat.parse(dateStr);
			//formから取得したdateStrをcalendarに詰める
			Calendar cl=Calendar.getInstance();
			cl.setTime(date);
			//30日前の日付をcalcStrに詰める
			cl.add(Calendar.DATE, -30);
			String calcDate=dateFormat.format(cl.getTime());
			//System.out.println(calcStr);

			ResultDAO dao=new ResultDAO();
			//データの登録されているupdate、件数をlistに格納
			List<Result> list=dao.findCount(dateStr,calcDate,userid);
			List<Result> toJsonList=new ArrayList<>();
			if(list.size()<1) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.print("データがありませんでした");
			}else {
				//listの回数だけ回し、日毎のデータを取り出す
				for(int i=0;i<list.size();i++) {
					//resultListに日毎のデータを全て詰める
					List<Result> resultList=dao.findData(list.get(i).getUpdated(),userid);
					for(int j=0;j<list.get(i).getCount();j++) {
						toJsonList.add(resultList.get(j));
					}
				}
				Gson gson = new Gson();
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.print("{\"result\": " + gson.toJson(toJsonList)+"}");
			}
			/*
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/showresult.jsp");
			rd.forward(request, response);
			*/

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
