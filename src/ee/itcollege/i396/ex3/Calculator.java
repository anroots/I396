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
	 * Ten pin bowling - number of pins
	 */
	public static final int NUMBER_OF_PINS_ON_FIELD = 10;

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

	/**
	 * Number of pins on the field.
	 */
	private int pinsOnField = NUMBER_OF_PINS_ON_FIELD;

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
			// We tried to hit more pins when the game is already over
			throw new GameOverException();
		}

		currentFramePoints.add(i);
		roll += 1;
		pinsOnField -= i;

		if (isFrameOver()) {
			moveToNextFrame();
		}

	}

	private void moveToNextFrame() {
		frame += 1; // Move to next frame
		roll = 0; // Reset current roll number
		score += getFrameScore() + getFrameBonus(); // Add frame score to total
													// score
		framePointsHistory.add(new ArrayList<Integer>(currentFramePoints)); // Save
																			// frame
																			// hits
																			// to
																			// history
		currentFramePoints.clear(); // Reset frame hits for the next frame
		pinsOnField = NUMBER_OF_PINS_ON_FIELD;
	}

	private int getFrameBonus() {

		if (lastFrameWasSpare()) {
			// Spare: next throw after the spare-frame eq the first throw of the
			// current frame
			return currentFramePoints.get(0);
		}

		if (lastFrameWasStrike()) {
			return getCurrentFramePointsSum();
		}

		return 0;
	}

	public int getFinalScore() {
		moveToNextFrame();
		return score;
	}

	private int getFrameScore() {
		return getCurrentFramePointsSum() + getFrameBonus();
	}

	private int getCurrentFramePointsSum() {
		int frameScore = 0;
		for (int i = 0; i < currentFramePoints.size(); i++) {
			frameScore += currentFramePoints.get(i);
		}
		return frameScore;
	}

	private boolean isFrameOver() {
		return (!isGameOver() && !hasMoreRolls()) || isFieldEmpty();
	}

	private boolean hasMoreRolls() {
		return !(roll == MAX_ROLLS && frame < MAX_FRAMES);
	}

	private boolean isFieldEmpty() {
		return pinsOnField == 0;
	}

	/**
	 * The first and second rolls of the last frame managed to get all the pins
	 * on the field
	 * 
	 * @return
	 */
	public boolean lastFrameWasSpare() {
		if (getPrevFrame() == null || getPrevFrame().size() < 2) {
			return false;
		}
		return getPrevFrame().get(0) + getPrevFrame().get(1) == NUMBER_OF_PINS_ON_FIELD;
	}

	public List<Integer> getPrevFrame() {
		// -2: Frames start from 1 (but list starts from 0), + we want the
		// previous frame
		try {
			return framePointsHistory.get(frame - 2);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void resetGame() {
		roll = 0;
		frame = 1;
		score = 0;
		currentFramePoints.clear();
		framePointsHistory.clear();
	}

	private boolean isGameOver() {
		return frame == MAX_FRAMES && roll == MAX_ROLLS_IN_LAST_FRAME;
	}

	public boolean lastFrameWasStrike() {
		if (getPrevFrame() == null || getPrevFrame().size() != 1) {
			return false;
		}
		return getPrevFrame().get(0) == NUMBER_OF_PINS_ON_FIELD;
	}

}

class GameOverException extends Exception {
	private static final long serialVersionUID = 1647989541156621987L;
}