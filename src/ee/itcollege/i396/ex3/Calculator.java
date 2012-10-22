package ee.itcollege.i396.ex3;

import java.util.ArrayList;

public class Calculator {

	/**
	 * Ten pin bowling - number of pins
	 */
	public static final int PINS_ON_FIELD = 10;

	/**
	 * Ten pin bowling - number of pins
	 */
	public static final int NUMBER_OF_FRAMES_IN_GAME = 10;

	/**
	 * Holds all the frames (10)
	 */
	private ArrayList<Frame> frames = new ArrayList<Frame>();

	private boolean gameIsOver = false;

	/**
	 * Current Frame Number
	 */
	private int frameIndex = 0;

	public ArrayList<Frame> getFrames() {
		return frames;
	}

	public int getFrameIndex() {
		return frameIndex;
	}

	public int setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
		return frameIndex;
	}

	private boolean isGameOver() {
		return gameIsOver;
	}

	public Calculator() {
		frames.add(new Frame());
	}

	public int getScore() {
		int score = 0;
		for (Frame frame : frames) {
			score += frame.getScore();
		}
		return score;
	}

	public void hit(int hit) throws GameOverException {

		if (isGameOver()) {
			throw new GameOverException();
		}

		if (currentFrameIsLastFrame()) {
			addFinalBonus(hit);
			gameIsOver = true;
			return;
		}

		Frame currentFrame = frames.get(getFrameIndex());

		if (currentFrame.canHitMore()) {
			currentFrame.hit(hit);
		} else if (setFrameIndex(getFrameIndex() + 1) < NUMBER_OF_FRAMES_IN_GAME) {
			Frame newFrame = new Frame();
			newFrame.hit(hit);
			frames.add(newFrame);
		}

		addSpareBonus(hit);
		addStrikeBonus(hit, 0);
	}

	public boolean currentFrameIsLastFrame() {
		return getFrameIndex() >= NUMBER_OF_FRAMES_IN_GAME;
	}

	private void addFinalBonus(int hit) throws GameOverException {
		frames.get(getFrameIndex() - 1).addBonus(hit);
		setFrameIndex(getFrameIndex() + 1);
	}

	private void addSpareBonus(int hit) {
		if (getFrameIndex() == 0) {
			return;
		}

		Frame frame = frames.get(getFrameIndex() - 1);

		if (frame.isSpare() && frame.getBonus() == 0) {
			frame.addBonus(hit);
		}
	}

	private void addStrikeBonus(int hit, int offset) {

		if (getFrameIndex() - offset == 0) {
			return;
		}

		if (offset > 1) {
			return;
		}

		Frame frame = frames.get(getFrameIndex() - offset - 1);

		if (!frame.isStrike()) {
			return;
		}

		if (frame.getBonus() == 0) {
			addStrikeBonus(hit, ++offset);
		}

		frame.addBonus(hit);
	}

	// public for testing
	public class Frame {

		private int hit1 = 0;
		private int hit2 = 0;
		private int bonus = 0;

		public void hit(int hit) {
			if (hit1 == 0) {
				if (hit > PINS_ON_FIELD) {
					throw new IllegalStateException();
				}
				hit1 = hit;
			} else {
				if (hit > PINS_ON_FIELD - hit1) {
					throw new IllegalStateException();
				}
				hit2 = hit;
			}
		}

		public void addBonus(int bonus) {
			this.bonus += bonus;
		}

		public int getBonus() {
			return bonus;
		}

		public boolean canHitMore() {
			if (hit1 != 0 && hit2 != 0) {
				return false;
			}
			if (isStrike() || isSpare()) {
				return false;
			}
			return true;
		}

		public boolean isStrike() {
			return hit1 == PINS_ON_FIELD;
		}

		public boolean isSpare() {
			return hit2 != 0 && hit1 + hit2 == PINS_ON_FIELD;
		}

		public int getScore() {
			return hit1 + hit2 + bonus;
		}
	}

	/**
	 * 
	 * GameOverException is a good idea, because this allows the user of the
	 * class to detect when the game is over. More generic exceptions, like
	 * IllegalStateException would be fine, too, but GameOverException adds more
	 * specifics as to why this happened.
	 * 
	 * New game can be started by creating a new instance or using a reset
	 * method.
	 * 
	 */
	public class GameOverException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6155891011832751159L;
	}
}