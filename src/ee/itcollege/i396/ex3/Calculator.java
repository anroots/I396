package ee.itcollege.i396.ex3;

public class Calculator {

	public static final int MAX_FRAMES = 10;
	public static final int MAX_ROLLS = 2;
	public static final int MAX_ROLLS_IN_LAST_FRAME = 3;

	/**
	 * Player's total score
	 */
	private int score = 0;

	/**
	 * Current frame number. Max: 10 frames
	 */
	private int frame = 1;

	/**
	 * Current roll within the frame. Max 2, except for the 10th frame (3).
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

		if (isGameOver()) {
			// We tried to hit more bats when the game is already over
			resetGame();
		}

		score += i;
		roll += 1;

		if (isFrameOver()) {
			moveToNextFrame();
		}

	}

	private void moveToNextFrame() {
		frame += 1;
		roll = 0;
	}

	private boolean isFrameOver() {
		return roll == MAX_ROLLS && frame < MAX_FRAMES;
	}

	private void resetGame() {
		roll = 0;
		frame = 1;
		score = 0;
	}

	private boolean isGameOver() {
		return frame == MAX_FRAMES && roll == MAX_ROLLS_IN_LAST_FRAME;
	}

}
