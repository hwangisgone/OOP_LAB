package hust.soict.globalict.aims.media;

import hust.soict.globalict.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable, Comparable<Media>{

//	public String getContents() {
//		String buffer = getTitle + "<br/>" + category + "<br/>" + director + "<br/>" + length + "<br/>" + cost + "<br/>";
//		return buffer;
//	}
	
	public String toString() {
		return String.format("DVD - %s - %s - %s - %s: %s $", 
			this.getTitle(), 
			this.getCategory(), 
			this.getDirector(),
			this.getLength(),
			this.getCost()
		);
	}
	
	public boolean isMatch(String keywords) {
		// Split the keywords into an array of individual words
		String[] words = keywords.toLowerCase().split("\\s+");
		
		// Check if any word in the array matches the DVD's title (case-insensitive)
		for (String word : words) {
			if (this.getTitle().toLowerCase().contains(word)) {
				return true;
			}
		}
		   
		// No match found
		return false;
	}

	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Playing DVD: " + this.getTitle());
			System.out.println("DVD length: " + this.getLength());
		} else {
			throw new PlayerException("ERROR: DVD length is non-positive");
		}
	}

	
	public DigitalVideoDisc(String title, String category, float cost) {
		super(title, category, cost);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category, int length, float cost) {
		super(title, category, length, cost);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super(title, category, director, cost);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super(title, category, director, length, cost);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category) {
		super(title, category);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public int compareTo(Media o2) {
        if (o2 instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd2 = (DigitalVideoDisc) o2;
            int titleComparison = this.getTitle().compareTo(dvd2.getTitle());
            if (titleComparison != 0) { return titleComparison; }
            
            int lengthComparison = Integer.compare(dvd2.getLength(), this.getLength());
            if (lengthComparison != 0) { return lengthComparison; }
            
            return Double.compare(dvd2.getCost(), this.getCost());
        }
        return super.compareTo(o2);
    }
}
