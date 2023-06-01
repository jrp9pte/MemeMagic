import java.util.Comparator;

public class CompareMemeByRating implements Comparator<Meme> {

	@Override
	public int compare(Meme o1, Meme o2) {
		int firstCompare = Double.compare(o2.calculateOverallRating(),o1.calculateOverallRating());
		if(firstCompare != 0) {
			return firstCompare;
		}
		int secondCompare = o1.getCaption().compareTo(o2.getCaption());
		if(secondCompare != 0) {
			return secondCompare;
		}
		int thirdCompare = o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
		if(thirdCompare != 0) {
			return thirdCompare;
		}
		int fourthCompare = o1.getCreator().compareTo(o2.getCreator());
			return fourthCompare;

	}


	
	

	

}
	
