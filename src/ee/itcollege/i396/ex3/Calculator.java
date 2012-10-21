package ee.itcollege.i396.ex3;

import java.util.ArrayList;
import java.util.List;

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
	 * Holds a list of points the player got for the current frame. Each roll
	 * adds a new entry.
	 */
	private List<Integer> framePoints = new ArrayList<Integer>();

	/**
	 * Current roll within the frame. Max 2, except for the 10th frame (3).
	 */
	private int roll = 0;

	public Calculator() {

	}

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
		return score + getFrameScore();
	}

	public void hit(int i) throws GameOverException {

		if (isGameOver()) {
			// We tried to hit more bats when the game is already over
			throw new GameOverException();
		}

		framePoints.add(i);
		// System.out.print("Add "+i+" points, score is now "+score+"\n");
		roll += 1;

		if (isFrameOver()) {
			moveToNextFrame();
		}

	}

	private void moveToNextFrame() {
		frame += 1;
		roll = 0;
		score += getFrameScore();
		framePoints.clear();
	}

	private int getFrameScore() {
		int frameScore = 0;
		for (int i = 0; i < framePoints.size(); i++) {
			frameScore += framePoints.get(i);
		}
		return frameScore;
	}

	private boolean isFrameOver() {
		return !isGameOver() && roll == MAX_ROLLS && frame < MAX_FRAMES;
	}

	public void resetGame() {
		roll = 0;
		frame = 1;
		score = 0;
	}

	private boolean isGameOver() {
		return frame == MAX_FRAMES && roll == MAX_ROLLS_IN_LAST_FRAME;
	}

}

class GameOverException extends Exception {
	private static final long serialVersionUID = 1647989541156621987L;
}