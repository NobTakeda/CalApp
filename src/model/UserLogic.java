package model;

public class UserLogic {
	public void execute(User user) {
		Double bmi=(user.getWeight()/((user.getHeight()/100)*(user.getHeight()/100)));
		bmi= ((double)Math.round(bmi * 10))/10;
		user.setBmi(bmi);
	}
}
