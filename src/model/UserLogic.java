package model;

public class UserLogic {
	public void execute(User user) {
		Double bmi=(user.getWeight()/((user.getHeight()/100)*(user.getHeight()/100)));
		bmi= ((double)Math.round(bmi * 10))/10;
		user.setBmi(bmi);
	}
	public String hiddenUserpass(User user) {
		String hiddenWords=user.getUserpass();
		if(hiddenWords.length()<2) {
			hiddenWords="*";
		}else {
			char[] charArray=hiddenWords.toCharArray();
			for(int i=1;i<hiddenWords.length();i++) {
				charArray[i]='*';
			}
			hiddenWords=String.valueOf(charArray);
		}
		return hiddenWords;
	}
}
