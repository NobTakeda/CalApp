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
import model.UserLogic;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		String userpass=request.getParameter("userpass");
		//DAOでidとpassがデータベースにあるか確認。
		UserDAO dao=new UserDAO();
		User user=new User();
		UserLogic ul=new UserLogic();
		user=dao.findOne(userid);
		if(user != null) {
			System.out.println("Login.java内情報確認用　id="+user.getUserid()+",pass="+user.getUserpass());
		}else {
			System.out.println("Login.java内情報確認用　userはnullです");
		}

		//登録ボタンが押されているかの判定。nullなら送信ボタン、値があれば登録ボタンと判定する。
		String pushedRegisterButton=request.getParameter("registerButton");
		String registerMessage="OK"; //Login.jspに返すエラーメッセージ

		if(pushedRegisterButton == null) {
			if(user == null || user.getUserid() == null) {
				registerMessage="noUser";
				System.out.println("例外場所発見用1");
				request.setAttribute("result",registerMessage);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				rd.forward(request,response);
			}else if(userpass.equals(user.getUserpass()) == false ){
				registerMessage="diffPass";
				request.setAttribute("result",registerMessage);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				rd.forward(request,response);
			}else {
			//ID,pass共に合っている場合
				HttpSession session=request.getSession();
				session.setAttribute("user",user);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/main.jsp");
				rd.forward(request,response);
			}
		}else{
			//id,passの登録処理。まず全角半角を判定、もし全角だった場合login.jspに戻す。
			if(ul.isOneByte(userid) && ul.isOneByte(userpass)) {
				//半角なので、main.jspでデータ登録ができるようにmakeUser.jspへ飛ばす。
				request.setAttribute("userid",userid);
				request.setAttribute("userpass",userpass);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/makeUser.jsp");
				rd.forward(request,response);
			}else {
				registerMessage="noOneByte";
				request.setAttribute("result",registerMessage);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				rd.forward(request,response);
			}
		}
	}
}
