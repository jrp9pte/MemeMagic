
public class BackgroundImage implements Comparable<BackgroundImage>{
	// Variables
	String imageFileName;
	String title;
	String description;
	//Methods
	public BackgroundImage() {
		imageFileName = "";
		title = "";
		description = "";
	}
	public BackgroundImage( String imageFileName, String title, String Description ) {
		 this.imageFileName = imageFileName;
		 this.title = title;
		 description = Description;
				 
	 }
	@Override public String toString() {
		return title + " " + "<" + description + ">";
	}
	@Override public boolean equals(Object object) {
		if ( object instanceof BackgroundImage ) {
			BackgroundImage image = (BackgroundImage) object;
			if (image.getImageFileName() != null && image.getTitle() != null && image.getDescription() != null) {
				

				if( (image.getImageFileName()).equals(imageFileName) && (image.getTitle()).equals(title) && (image.getDescription()).equals( description)) {
				return true;
				}
				else {
				return false;
				}
			}
		}
			return false;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName( String imageFileName) {
		this.imageFileName = imageFileName;
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descriptionName) {
		description = descriptionName;
	}
	@Override
	public int compareTo(BackgroundImage o) {
		int firstCompare= (this.getImageFileName()).compareTo(o.getImageFileName());
		if ( firstCompare != 0) {
			return firstCompare;
		}
		int secondCompare = (this.getTitle()).compareTo(o.getTitle());
		if ( secondCompare != 0) {
			return secondCompare;
		}
		int thirdCompare = (this.getDescription()).compareTo(o.getDescription());
			return thirdCompare;
		
		
	}
	 
	
}
