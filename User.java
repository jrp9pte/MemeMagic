import java.util.ArrayList;
import java.util.TreeSet;
public class User implements Comparable<User> {
		// Variables
		String username;
		ArrayList<Meme> memesCreated;
		TreeSet<Meme> memesViewed;
		// Methods
		public User() {
			username = "";
			ArrayList<Meme> memesCreated1 = new ArrayList<Meme>();
			memesCreated = memesCreated1;
			TreeSet<Meme> memesViewed1 = new TreeSet<Meme>();
			memesViewed = memesViewed1;
		}
		public User(String username) {
			this.username = username;
			ArrayList<Meme> memesCreated1 = new ArrayList<Meme>();
			memesCreated = memesCreated1;
			TreeSet<Meme> memesViewed1 = new TreeSet<Meme>();
			memesViewed = memesViewed1;
		}
		public void rateMeme(Meme meme, int rating) {
			
			if ( meme != null) {
				memesViewed.add(meme);
			}
			Rating ratingObject = new Rating(this, rating);
			meme.addRating(ratingObject);
		}

		public Meme createMeme(BackgroundImage backgroundImage, String caption) {
			User user1 = new User(username);
			Meme memeobject = new Meme( backgroundImage, caption, user1);
			memesCreated.add(memeobject);
			return memeobject;
		}
		public boolean deleteMeme(Meme meme) {
			if( meme != null) {
					for ( int t= 0 ; t < memesCreated.size(); t++) {
						if(memesCreated.get(t) != null) {
							if( (memesCreated.get(t)).getShared() == false) {
								if( (memesCreated.get(t) != null) && (memesCreated.get(t)).equals(meme)) {
							memesCreated.remove(meme);
							return true;
								}
							}
						}
					}
			}
			return false;
		}
		public void shareMeme(Meme meme, Feed feed) {
			meme.setShared(true);
			(feed.getMemes()).add(meme);
		}
		public boolean rateNextMemeFromFeed(Feed feed, int ratingScore) {
			Meme newMeme = feed.getNewMeme(this);
			if( newMeme != null) {
				memesViewed.add(newMeme);
				Rating newRating = new Rating( this, ratingScore );
				newMeme.addRating(newRating);
				return true;
			}
			return false;
		}
		public double calculateReputation() {
			if( memesCreated.size() == 0) {
				return 0.0;
			}
			double average = 0.0;
			for ( int v = 0 ; v < memesCreated.size(); v++) {
				if(memesCreated.get(v) != null) {
					average = (average + (double) (memesCreated.get(v)).calculateOverallRating());
				}
			}
			return average/ memesCreated.size();
		}
		@Override
		public String toString() {
			return username + " has rated (" + memesViewed.size() + ") " + "memes, (" + calculateReputation() + ")";
		}
		@Override
		public boolean equals(Object object) {
			if( object != null) {
				if ( object instanceof User && this.getUserName() == ((User) object).getUserName()) {
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
	
		public String getUserName() {
			return username;
		}
		public void setUserName( String name) {
			username = name;
		}
		public ArrayList<Meme> getMemesCreated() {
			return memesCreated;
		}
		public void setMemesCreated(ArrayList<Meme> memesCreated) {
			this.memesCreated = memesCreated;
		}
		public ArrayList<Meme> getMemesViewed() {
			ArrayList<Meme> memesViewedList = new ArrayList<Meme>(memesViewed);
			return memesViewedList;
		}
		public void setMemesViewed(ArrayList<Meme> memesViewed) {
			this.memesViewed = new TreeSet<Meme>(memesViewed);
		}
		@Override
		public int compareTo(User o) {
			int firstCompare = (this.getUserName()).compareTo(o.getUserName());
			if( firstCompare != 0) {
				return firstCompare;
			}
			int secondCompare = Integer.compare((o.getMemesCreated().size()),(this.getMemesCreated().size()));
			return secondCompare;
		}
		
		

}
		
