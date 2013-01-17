package controller;

public class Welcome {
	
	public Welcome() {
		
	}
	
	private String story, title;
	private String pictureURL; // TODO: investigate turning this into an actual URL
	
	public void generateRandomStory() {
		// TODO this must get a random story from the database
		// HACK FOR NOW
		story = "Something, something...\n A whole lot of nothing.\n Lost in stupidity,\n Like the end of a bad book.\n" +
				"A parroting parody;\n All I am is a ferret ferreting.";
		title = "A whole lot of nothing.";
		pictureURL = "src/main/resources/new_hat.jpg";
				
		
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public String getStory() {
		return story;
	}

	public String getTitle() {
		return title;
	}
	
}
