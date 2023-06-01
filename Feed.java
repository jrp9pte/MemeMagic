import java.util.ArrayList;
public class Feed {
	//Variables
	ArrayList<Meme> memes = new ArrayList<Meme>();
	//Methods
	public Feed() {}
	public Feed(ArrayList<Meme> memes) {
		this.memes = memes;
	}
	public Meme getNewMeme(User user) {
		for( int g = 0; g < memes.size(); g++) {
			boolean visible = false;
			boolean created = false;
			for( int y = 0; y < user.getMemesCreated().size(); y++) {
				if( user.getMemesCreated().get(y).equals(memes.get(g))) {
					created = true;
				}
			}
			for( int f = 0; f < user.getMemesViewed().size(); f++) {
				if( user.getMemesViewed().get(f).equals(memes.get(g))) {
					visible = true;
				}
			}
			if( created == false && visible == false) {
				return memes.get(g);
			}		
		}
		return null;
	}
	public String toString() {
		String feedString = "";
		if(memes != null) {
			for( Meme meme : memes) {
				feedString = feedString + meme.toString() + "\n" ;
			}
		}
		return feedString;
	}
	public ArrayList<Meme> getMemes() {
		return memes;
	}
	public void setMemes(ArrayList<Meme> memes) {
		this.memes = memes;
	}
	
	
	
}
