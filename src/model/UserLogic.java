package model;

public class UserLogic {
	public void execute(User user) {
		Double bmi=(user.getWeight()/((user.getHeight()/100)*(user.getHeight()/100)));
		bmi= ((double)Math.round(bmi * 10))/10;
		user.setBmi(bmi);
	}

	//文字列を先頭以外伏せ字にする
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

	//入力された文字列が全角か半角かを判別する
	public boolean isOneByte(String s) {
		boolean isOneByte=false;
		char[] chars=s.toCharArray();
		for(int i=0;i<chars.length;i++){
			if(String.valueOf(chars[i]).getBytes().length<2) {
				isOneByte=true;
			}else {
				isOneByte=false;
				break;
			}
		}
		return isOneByte;
	}
}
