package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		String userpass=request.getParameter("userpass");
		//DAOでidとpassがデータベースにあるか確認。
		UserDAO dao=new UserDAO();
		User user=new User();
		dao.registerCheck(user,userid);

		//登録ボタンが押されているかの判定。nullなら送信ボタン、値があれば登録ボタンと判定する。
		String pushedRegisterButton=request.getParameter("registerButton");
		if(pushedRegisterButton==null) {
			//処理なし
		}else{
			//main.jspでデータ登録ができるようにmakeUser.jspのpostへ飛ばす。
			request.setAttribute("userid",userid);
			request.setAttribute("userpass",userpass);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/makeUser.jsp");
			rd.forward(request,response);
			
		}



		String registerMassage=null;
		//ユーザーIDとパスがnullだった場合
		if(user.getUserpass() == null) {
			registerMassage="noUser";

		//ユーザーIDは存在するがパスが違っていた場合
		}else if(user.getUserpass() != userpass){
			registerMassage="diffPass";

		//その他(ID,pass共に合っている場合)
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("user",user);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/main.jsp");
			rd.forward(request,response);
		}
		request.setAttribute("result",registerMassage);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request,response);
	}

}
