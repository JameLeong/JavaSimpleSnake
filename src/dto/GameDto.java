package dto;

import java.awt.Point;
import java.util.ArrayList;

public class GameDto {
	/**
	 * �ߵĴ�С
	 */
	public int snakeWidth;
	public int snakeHeight;

	/**
	 * ��ͷ��λ��
	 */
	public int snakeX;
	public int snakeY;
	/**
	 * ���������
	 */
	public ArrayList<Point> point;
	/**
	 * �߷���ĸ���
	 */
	private int SnakeBody;
	/**
	 * �ߵķ���
	 */
	public int direction;
	/**
	 * �ߵ��ٶ�:ÿһ���ߵľ���
	 */
	private int SnakeRate;

	/**
	 * ʳ�������
	 */
	public int foodX;
	public int foodY;
	/**
	 * �Ƿ�Ե�
	 */
	private boolean isEat;
	/**
	 * �Ƿ���ͣ״̬
	 */
	private boolean pause;

	/**
	 * �ȼ�
	 */
	private int level;
	/**
	 * ����
	 */
	private int score;
	/**
	 * �Ƿ�ʼ
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
		// ������ͷ
		this.point.add(new Point(this.snakeX, this.snakeY));
	}
	
	
	
	// ��������������ȡ��
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
	 * �ı���ͣ״̬
	 */
	public void changePause() {
		this.setPause(!isPause());
	}
	
	

}
