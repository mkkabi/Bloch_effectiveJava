/*
package effectivejava;

import sun.misc.*;

// An autocloseable class using a cleaner as a safety net
class Room implements AutoCloseable {

	private static final Cleaner cleaner = Cleaner.create(new Object(), new Runnable() {
		@Override
		public void run() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	});

	// Resource that requires cleaning. Must not refer to Room!
	private static class State implements Runnable {

		int numJunkPiles; // Number of junk piles in this room

		State(int numJunkPiles) {
			this.numJunkPiles = numJunkPiles;
		}

		// Invoked by close method or cleaner
		@Override
		public void run() {
			System.out.println("Cleaning room");
			numJunkPiles = 0;
		}
	}

	// The state of this room, shared with our cleanable
	private final State state;

	// Our cleanable. Cleans the room when it’s eligible for gc
	
	private final Cleaner.Cleanable cleanable;
	Cleaner.

	public Room(int numJunkPiles) {
		state = new State(numJunkPiles);
		cleanable = cleaner.register(this, state);
	}

	@Override
	public void close() {
		cleanable.clean();
	}
}

public class AutoCloseableWithCleaner {

}

*/