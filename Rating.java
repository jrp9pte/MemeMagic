 
public class Rating {
	
	//Variables
	int score;
	User user;
	// Methods
	public Rating() {
		score = 0;
		user = new User();
	}
	public Rating(User user, int score) {
		this.user = user;
		if(this.score == -1 || this.score == 1 ||this.score == 0) {
			this.score = score;
		}
		else {
			score = 0;
		}
	}
	@Override public String toString() {
		String option1 = user.getUserName() + " rated as an upvote";
		String option2 = user.getUserName() + " rated as a downvote";
		String option3= user.getUserName() + " rated as a pass";
		if( getScore() == 1) {
			return option1;
		}
		else if( getScore()== 0) {
			return option3;
			
		}
		else if (getScore()== -1) {
			return option2;
		}
		else {
			return "There was an error getting score";
		}
	}
	@Override public boolean equals(Object object) {
		if( object instanceof Rating == false) {
			return false;
		}
		Rating ratingObject = (Rating) object;
		if( ratingObject.getUser() != null) {
			boolean scorecheck = (ratingObject.getScore() == score);
			boolean usercheck = (ratingObject.getUser()).equals(user);
			if ( scorecheck == true && usercheck == true) {
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
	public int getScore() {
		return score;
	}
	public boolean setScore(int scores) {
		if ( scores==1 || scores == 0 || scores == -1) {
			score = scores;
			return true;
		}
		else {
			return false;
		}
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
