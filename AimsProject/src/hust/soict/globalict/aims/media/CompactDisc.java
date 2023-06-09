package hust.soict.globalict.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.globalict.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private List<Track> tracks = new ArrayList<Track>();

	public String getArtist() {
		return artist;
	}
	
	public void addTrack(Track oneTrack) {
		if (this.tracks.contains(oneTrack)) {
			System.out.println("Track already added.");
		} else {
			this.tracks.add(oneTrack);
			System.out.println("Added " + oneTrack);
		}
	}
	
	public void removeTrack(Track oneTrack) {
		if (this.tracks.remove(oneTrack)) {
			System.out.println("Track does not exist.");
		} else {
			System.out.println("Removed " + oneTrack);
		}	
	}
	
	public String toString() {
		return String.format("CD - %s - %s - %s - %s: %s $", 
			this.getTitle(), 
			this.getCategory(), 
			this.getArtist(),
			this.getLength(),
			this.getCost()
		);
	}
	
	public int getLength() {
		int len = 0;
        for (Track track : tracks) {
            len += track.getLength();
        }
		return len;
	}
	
	public void play() throws PlayerException {
		if(this.getLength() > 0) {
			System.out.println("Disc Artist: " + this.getArtist());
			System.out.println("Disc length: " + this.getLength());
			
			int index = 0;
	        for (Track track : tracks) {
	        	try {
	        		track.play();
	        	} catch(PlayerException e) {
	        		throw e;
	        	}
	        	index += 1;
	        	System.out.println("Track #" + index);
	        }
		} else {
			throw new PlayerException("ERROR: CD length is non-positive");
		}
	}
	
	public CompactDisc(String title, String category, float cost, String artist) {
		super(title, category, cost);
		this.artist = artist;
	}

	public CompactDisc(String title, String category, String artist) {
		super(title, category);
		this.artist = artist;
	}

	public CompactDisc(String title, String artist) {
		super(title);
		this.artist = artist;
	}

	public CompactDisc(String title, String category, float cost) {
		super(title, category, cost);
		// TODO Auto-generated constructor stub
	}

	public CompactDisc(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
}
