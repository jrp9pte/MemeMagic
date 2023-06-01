import java.util.Comparator;

public class CompareMemeByCreator implements Comparator<Meme> {

	@Override
	public int compare(Meme o1, Meme o2) {
		int firstCompare = o1.getCreator().compareTo(o2.getCreator());
		if(firstCompare != 0) {
			return firstCompare;
		}
		int secondCompare = Double.compare(o2.calculateOverallRating(),o1.calculateOverallRating());
		if(secondCompare != 0) {
			return secondCompare;
		}
		int thirdCompare = o1.getCaption().compareTo(o2.getCaption());
		if(thirdCompare != 0) {
			return thirdCompare;
		}
		int fourthCompare = o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
		if(fourthCompare != 0) {
			return fourthCompare;
		}
		int fifthCompare = Boolean.compare(o2.getShared(), o1.getShared());
		return fifthCompare;
	}

	

}
