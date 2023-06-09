package hust.soict.globalict.test.store;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.store.Store;

public class StoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Store localMediaStore = new Store();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",	"Animation",		"Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",		"Science Fiction",	"George Lucas", 87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",			"Animation", 		18.99f);
		
		localMediaStore.addMedia(dvd1);
		localMediaStore.addMedia(dvd2);
		localMediaStore.print();
		System.out.println();
		
		localMediaStore.removeMedia(dvd3);
		localMediaStore.removeMedia(dvd1);
		localMediaStore.print();
		System.out.println();
		
		localMediaStore.addMedia(dvd1);
		localMediaStore.addMedia(dvd1);
		localMediaStore.addMedia(dvd1);
		localMediaStore.print();
	}

}
