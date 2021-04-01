package model;

import java.util.List;

public class MealsLogic {
	public void execute(List<Food> list,Meals meals,String date) {
		int bCal=0;
		int lCal=0;
		int sCal=0;
		int tCal=0;
		for(int i=0;i<list.size();i++) {
			switch(list.get(i).getTime()) {
			case 0:
				bCal+=list.get(i).getCal();
				break;
			case 1:
				lCal+=list.get(i).getCal();
				break;
			case 2:
				sCal+=list.get(i).getCal();
				break;
			}
			tCal+=list.get(i).getCal();
		}
		meals.setBreakfastCal(bCal);
		meals.setLunchCal(lCal);
		meals.setSupperCal(sCal);
		meals.setTotalCal(tCal);
		meals.setDate(date);
	}
}
