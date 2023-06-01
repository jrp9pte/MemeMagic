public class Meme implements Comparable<Meme>{

	// Variables
	User creator;
	BackgroundImage backgroundImage;
	Rating[] ratings;
	String caption;
	String captionVerticalAlign;
	boolean shared;
	// Methods
	public Meme() {
		creator = new User();
		backgroundImage = new BackgroundImage();
		ratings = new Rating[10];
		caption = "";
		captionVerticalAlign = "bottom";
		shared = false;
		
		
	}
	public Meme( BackgroundImage backgroundImage, String caption, User creator) {
		this.backgroundImage = backgroundImage;
		this.caption = caption;
		this.creator = creator;
		ratings = new Rating[10];
		captionVerticalAlign = "bottom";
	}
	public boolean addRating(Rating rating) {
		for( int v = 0 ; v < ratings.length; v++ ) {
			if(ratings[v] == null) {
				if(rating != null) {
					ratings[v] = rating;
					return true;
				}
				else {
					return false;
				}
			}
		}
		for (int v = 0 ; v < ratings.length - 1; v++ ) {
				if(rating != null) {	
					ratings[v] = ratings[v+1];
					ratings[ratings.length-1] = rating;
					return true;
				}
		}
			
		
		return false;
	}
	public double calculateOverallRating() {
		double total = 0.0;
		if( ratings != null) {
			for( int i = 0; i < ratings.length -1 ; i++) {
				if( ratings[i] != null) {
					total = total + ratings[i].getScore();
				}
			}	
		}
		return total;
	}
	@Override public String toString() {
		return backgroundImage +" '" + caption + "' " + calculateOverallRating() + " [+1: " + countOneRatings() + ", -1: " +countNegativeOneRatings() + "]" + " - created by " + creator.getUserName() ;
	}
	private int countOneRatings() {
		int numberOneRatings = 0;
		if( ratings != null) {
			for( int j = 0 ; j < ratings.length  ; j++) {
				if( ratings[j] != null) {
					if(ratings[j].getScore() == 1 ) {
					numberOneRatings++;
					}
				}
			}
		}
		return  numberOneRatings;
	}
	private int countNegativeOneRatings() {
		int numberNegativeOneRatings = 0;
		if( ratings != null) {
			for( int j = 0 ; j < ratings.length - 1 ; j++) {
				if( ratings[j] != null) {
					if(ratings[j].getScore() == -1 ) {
					numberNegativeOneRatings++;
					}
				}
			}
		}
		return  numberNegativeOneRatings;
	}
	@Override public boolean equals(Object object) {
			if( this == null || object == null){
				return false;
			}
			if ( this == object) {
				return true;
			}
			if( object instanceof Meme == false) {
				return false;
			}
			Meme memeobject = (Meme) object;
			if( memeobject.getCreator() != null && memeobject.getCaption() != null && memeobject.getBackgroundImage()!= null) {
				if( (memeobject.getCreator()).equals(creator) && (memeobject.getCaption()).equals(caption) && (memeobject.getBackgroundImage()).equals(backgroundImage)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
		
	}
	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(BackgroundImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public Rating[] getRatings() {
		return ratings;
	}
	public void setRatings(Rating[] ratings) {
		this.ratings = ratings;
		
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}
	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		if( captionVerticalAlign == "top"|| captionVerticalAlign == "bottom"|| captionVerticalAlign == "middle") {
		this.captionVerticalAlign = captionVerticalAlign;
		return true;
		
		}	
		return false;
	}
	public boolean getShared() {
		return shared;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	@Override
	public int compareTo(Meme o) {
		int firstCompare= (this.getCaption()).compareTo(o.getCaption());
		if ( firstCompare != 0) {
			return firstCompare;
		}
		int secondCompare = (this.getBackgroundImage()).compareTo(o.getBackgroundImage());
		if ( secondCompare != 0) {
			return secondCompare;
		}
		//int thirdCompare = ((int)this.calculateOverallRating())-((int)o.calculateOverallRating());
		int thirdCompare = ((int)o.calculateOverallRating() - (int) this.calculateOverallRating());
		if(thirdCompare != 0) {
			return thirdCompare;
		}
		int fourthCompare = Boolean.compare( o.getShared(), this.getShared());
		return fourthCompare;
	}
	
	
	
}
