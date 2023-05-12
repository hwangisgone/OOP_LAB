package aims;

public class DigitalVideoDisc {
	private String title;
	private String category;
	private String director;
	private int length;
	private float cost;
	
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	public float getCost() {
		return cost;
	}
	
	public String getContents() {
		String buffer = title + "<br/>" + category + "<br/>" + director + "<br/>" + length + "<br/>" + cost + "<br/>";
		return buffer;
	}
	
	public String toString() {
		return String.format("DVD - %s - %s - %s - %s: %s $", 
			this.title, 
			this.category, 
			this.director,
			this.length,
			this.cost
		);
	}
	
	// By title
	public DigitalVideoDisc(String title) {
		super();
		this.title = title;
	}
	
	// By title, category, cost
	public DigitalVideoDisc(String title, String category, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	
	// By title, category, director, and cost
	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.cost = cost;
	}
	
	// By all
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
	}
	

	public DigitalVideoDisc(String title, String category, int length, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.length = length;
		this.cost = cost;
	}
	public DigitalVideoDisc(String title, float cost) {
		super();
		this.title = title;
		this.cost = cost;
	}
	
	
}
