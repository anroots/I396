package ee.itcollege.i396.ex3;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	/**
	 * One game consists of this many frames
	 */
	public static final int MAX_FRAMES = 10;

	/**
	 * One frame (except last) can have at most this many rolls
	 */
	public static final int MAX_ROLLS = 2;

	/**
	 * The last frame within a game can have at most this many rolls
	 */
	public static final int MAX_ROLLS_IN_LAST_FRAME = 3;

	/**
	 * How many bats must be hit in first and second roll for the frame to be
	 * called a "Spare"
	 */
	private static final Integer SPARE_HIT_COUNT = 5;

	/**
	 * Player's total score
	 */
	private int score = 0;

	/**
	 * Current frame number. Max: 10 frames
	 */
	private int frame = 1;

	/**
	 * Holds frame point history Each is a List that represents one frame. Each
	 * frame is represented by numeric hit records.
	 */
	private List<List<Integer>> framePointsHistory = new ArrayList<List<Integer>>();

	/**
	 * Points for the current frame. Will be added to FramePointsHistory and
	 * emptied with each frame switch.
	 */
	private List<Integer> currentFramePoints = new ArrayList<Integer>();

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

	/**
	 * Total score is calculated after the frame is over. Running score queries
	 * take scores from previous frames and add the score of the running frame.
	 * 
	 * @return
	 */
	public int getScore() {
		return score + getFrameScore();
	}

	public void hit(int i) throws GameOverException {

		if (isGameOver()) {
			// We tried to hit more bats when the game is already over
			throw new GameOverException();
		}

		currentFramePoints.add(i);
		roll += 1;

		if (isFrameOver()) {
			moveToNextFrame();
		}

	}

	private void moveToNextFrame() {
		frame += 1; // Move to next frame
		roll = 0; // Reset current roll number
		score += getFrameScore(); // Add frame score to total score
		framePointsHistory.add(new ArrayList<Integer>(currentFramePoints)); // Save
																			// frame
																			// hits
																			// to
																			// history
		currentFramePoints.clear(); // Reset frame hits for the next frame
	}

	private int getFrameScore() {
		int frameScore = 0;
		for (int i = 0; i < currentFramePoints.size(); i++) {
			frameScore += currentFramePoints.get(i);
		}
		return frameScore;
	}

	private boolean isFrameOver() {
		return !isGameOver() && roll == MAX_ROLLS && frame < MAX_FRAMES;
	}

	public boolean lastFrameWasSpare() {
		// -2: Frames start from 1 (but list starts from 0), + we want the
		// previous frame
		return framePointsHistory.get(frame - 2).get(0) == SPARE_HIT_COUNT
				&& framePointsHistory.get(frame - 2).get(1) == SPARE_HIT_COUNT;
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