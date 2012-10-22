package ee.itcollege.i396.ex3;

import java.util.ArrayList;

public class Calculator {

	/**
	 * Ten pin bowling - number of pins
	 */
	final int pinsOnField = 10;
	
	/**
	 * Ten pin bowling - number of pins
	 */
	final int maxIndex = 9;

	//public for testing
	public ArrayList<Frame> frames;
	
	/**
	 * Current Frame Number
	 */
	//public for testing
	public int frameIndex;
	
	public Calculator() {
		frames = new ArrayList<Frame>();
		frames.add(new Frame());
		frameIndex = 0;
	}
	
	public int getScore() {
		int score = 0;
		for (Frame frame : frames) {
			score += frame.getScore();
		}
		return score;
	}
	
	public void hit(int hit) {
		if (frameIndex > maxIndex) {
			addFinalBonus(hit);
			return;
		}
		
		Frame currentFrame = frames.get(frameIndex);
		if (currentFrame.canHitMore()) {
			currentFrame.hit(hit);
		}
		else if (++frameIndex < 10) {
			Frame newFrame = new Frame();
			newFrame.hit(hit);
			frames.add(newFrame);
		}
		
		addSpareBonus(hit);
		addStrikeBonus(hit, 0);
	}
	
	private void addFinalBonus(int hit) {
		int diff = frameIndex - maxIndex;
		if (diff < 2) {
			frames.get(frameIndex - 1).addBonus(hit);
			frameIndex++;
		}
		else throw new IllegalStateException();
	}

	private void addSpareBonus(int hit) {
		if (frameIndex == 0) {
			return;
		}
		Frame frame = frames.get(frameIndex - 1);
		if (frame.isSpare() && frame.getBonus() == 0) {
			frame.addBonus(hit);
		}
		
	}
	
	private void addStrikeBonus(int hit, int offset) {
		if (frameIndex - offset == 0) {
			return;
		}
		if (offset > 1) {
			return;
		}
		Frame frame = frames.get(frameIndex - offset - 1);
		
		if (!frame.isStrike()) {
			return;
		}
		
		if (frame.getBonus() == 0) {
			addStrikeBonus(hit, ++offset);
		}
		frame.addBonus(hit);
	}
	
	//public for testing
	public class Frame {
		
		private int hit1;
		private int hit2;
		private int bonus;
		
		public Frame() {
			hit1 = 0;
			hit2 = 0;
			bonus = 0;
		}
		
		public void hit(int hit) {
			if (hit1 == 0) {
				hit1 = hit;	
			}
			else if (hit <= pinsOnField - hit1) {
				hit2 = hit;
			}
			else {
				throw new IllegalStateException();
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
			if (hit1 == pinsOnField) {
				return true;
			}
			return false;
		}
		
		public boolean isSpare() {
			if (hit2 != 0 && hit1 + hit2 == pinsOnField) {
				return true;
			}
			return false;
		}
		public int getScore() {
			return hit1 + hit2 + bonus;
		}
	}
}