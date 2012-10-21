package ee.itcollege.i396.ex3;

public class Calculator {

	/**
	 * Player's total score
	 */
	private int score = 0;
	
	/**
	 * Current frame number.
	 * Max: 10 frames
	 */
	private int frame = 1;
	
	/**
	 * Current roll within the frame.
	 * Max 2, except for the 10th frame (3).
	 */
	private int roll = 0;
	
	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void hit(int i) {
		score += i;
		roll +=1;
		
		if (roll == 2 && frame != 10){
			frame +=1;
			roll = 0;
		}
		
		// Reset
		if (frame == 10 && roll == 4){
			roll = 0;
			frame = 1;
			score = i;
		}
	}


}
