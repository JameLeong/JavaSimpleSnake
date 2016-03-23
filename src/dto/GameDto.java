package dto;

import java.awt.Point;
import java.util.ArrayList;

public class GameDto {
	/**
	 * 蛇的大小
	 */
	public int snakeWidth;
	public int snakeHeight;

	/**
	 * 蛇头的位置
	 */
	public int snakeX;
	public int snakeY;
	/**
	 * 蛇身的坐标
	 */
	public ArrayList<Point> point;
	/**
	 * 蛇方块的个数
	 */
	private int SnakeBody;
	/**
	 * 蛇的方向
	 */
	public int direction;
	/**
	 * 蛇的速度:每一步走的距离
	 */
	private int SnakeRate;

	/**
	 * 食物的坐标
	 */
	public int foodX;
	public int foodY;
	/**
	 * 是否吃到
	 */
	private boolean isEat;
	/**
	 * 是否暂停状态
	 */
	private boolean pause;

	/**
	 * 等级
	 */
	private int level;
	/**
	 * 分数
	 */
	private int score;
	/**
	 * 是否开始
	 */
	private boolean start;

	public GameDto(){
		this.snakeHeight = 20;
		this.snakeWidth = 20;
		this.point = new ArrayList<Point>();
		this.SnakeBody = 1;
		this.snakeX = 300 + 10;
		this.snakeY = 300 + 10;
		this.SnakeRate = 20;
		this.isEat = true;
		this.pause = false;
		this.score = 0;
		this.level = 0;
		this.start = false;
		this.direction = 1;
		// 设置蛇头
		this.point.add(new Point(this.snakeX, this.snakeY));
	}
	
	
	
	// 各种生成器，读取器
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getSnakeBody() {
		return SnakeBody;
	}

	public void setSnakeBody(int snakeBody) {
		SnakeBody = snakeBody;
	}



	public int getSnakeRate() {
		return SnakeRate;
	}

	public void setSnakeRate(int snakeRate) {
		SnakeRate = snakeRate;
	}

	public boolean isEat() {
		return isEat;
	}

	public void setEat(boolean isEat) {
		this.isEat = isEat;
	}
	/**
	 * 改变暂停状态
	 */
	public void changePause() {
		this.setPause(!isPause());
	}
	
	

}
