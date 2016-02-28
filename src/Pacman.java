import java.awt.Color;
import java.awt.event.KeyEvent;

import org.w3c.dom.css.ElementCSSInlineStyle;

public class Pacman extends GameObject {

	private int lives = 3;
	private boolean canPacmanKillGhosts = false;

	public Pacman(int x, int y, Color color, Input input, Board gameBoard, boolean isActive) {
		super(x, y, color, input, gameBoard, isActive);
	}

	@Override
	/**
	 * Update is run every frame and performs what ever actions need to be run
	 * for pacman like moving, eating pellets, checking for ghosts.
	 */
	public void update() {

		if (isActive == true) {
			if (input.getIsKeyPressed() == true) {

				if (input.getCurrentKey().getKeyCode() == KeyEvent.VK_A) {
					if (gameBoard.getGameboard()[yCoord][xCoord - 1].isWall() == false) {
						if(xCoord == 1){
							xCoord = 28;
						}
						else{
							xCoord -= 1;
						}
						System.out.println("Move pacman left");
						System.out.println("Total moves: " + input.getTotalPacmanMoves());
					}
				}
				if (input.getCurrentKey().getKeyCode() == KeyEvent.VK_D) {
					if (gameBoard.getGameboard()[yCoord][xCoord + 1].isWall() == false) {
						if(xCoord == 28){
							xCoord = 1;
						}
						else{
							xCoord += 1;
						}
						
						System.out.println("Move pacman right");
						System.out.println("Total moves: " + input.getTotalPacmanMoves());
					}
				}
				if (input.getCurrentKey().getKeyCode() == KeyEvent.VK_W) {
					if (gameBoard.getGameboard()[yCoord - 1][xCoord].isWall() == false) {
						yCoord -= 1;
						System.out.println("Move pacman up");
						System.out.println("Total moves: " + input.getTotalPacmanMoves());
					}
				}
				if (input.getCurrentKey().getKeyCode() == KeyEvent.VK_S) {
					if (gameBoard.getGameboard()[yCoord + 1][xCoord].isWall() == false) {
						yCoord += 1;
						System.out.println("Move pacman down");
						System.out.println("Total moves: " + input.getTotalPacmanMoves());
					}
				}
				checkForGhost();
				tryToEatPellet();
			}
			else{
				
			}
		}
	}

	/**
	 * Pacman lose a life, and dies if no lives are left.
	 */
	private void loseLife() {
		lives--;
		if (lives == 0) {
			death();
		}
	}

	public int getLives() {
		return lives;
	}

	/**
	 * Sets pacman to no longer be active.
	 */
	public void death() {
		isActive = false;
	}

	/**
	 * Pacman attempts to eat a pellet if one is found it calls remove pellet on
	 * that position.
	 */
	private void tryToEatPellet() {
		if (gameBoard.getGameboard()[yCoord][xCoord].isPellet()) {
			gameBoard.getGameboard()[yCoord][xCoord].removePellet();
		}
	}

	/**
	 * Pacman checks for a ghost in the current position, if one is found pacman
	 * calls lose life or kill ghost.
	 */
	private void checkForGhost() {
		if (gameBoard.getGameboard()[yCoord][xCoord].getIsOccupiedBy() == true) {
			if (canPacmanKillGhosts == false) {
				loseLife();
			} else {
				gameBoard.getGameboard()[yCoord][xCoord].getOccupiedBy().death();
			}
		}
	}
}
