package service;

import java.awt.Rectangle;

import dto.GameDto;

public class GameSnake {
	private GameDto dto;

	public GameSnake(GameDto dto) {
		this.dto = dto;

	}

	/**
	 * 方向：向左
	 */
	public void TurnLeft() {
		if (this.isStraightX()) {
			for (int i = 0; i < this.dto.getSnakeBody(); i++) {
				this.dto.point.get(i).x -= this.dto.getSnakeRate();
			}
		} else {
			int tempx = this.dto.point.get(0).x;
			for (int i = this.dto.getSnakeBody() - 2; i >= 0; i--) {
				this.dto.point.get(i + 1).x = this.dto.point.get(i).x;
				this.dto.point.get(i + 1).y = this.dto.point.get(i).y;
			}
			this.dto.point.get(0).x = tempx - this.dto.getSnakeRate();
		}

	}

	/**
	 * 方向：向右
	 */
	public void TurnRight() {
		if (this.isStraightX()) {
			for (int i = 0; i < this.dto.getSnakeBody(); i++) {
				this.dto.point.get(i).x += this.dto.getSnakeRate();
			}
		} else {
			int tempx = this.dto.point.get(0).x;
			for (int i = this.dto.getSnakeBody() - 2; i >= 0; i--) {
				this.dto.point.get(i + 1).x = this.dto.point.get(i).x;
				this.dto.point.get(i + 1).y = this.dto.point.get(i).y;
			}
			this.dto.point.get(0).x = tempx + this.dto.getSnakeRate();
		}

	}

	/**
	 * 方向：上
	 */
	public void TurnUp() {
		if (this.isStraightY()) {
			for (int i = 0; i < this.dto.getSnakeBody(); i++) {
				this.dto.point.get(i).y -= this.dto.getSnakeRate();
			}
		} else {
			int tempy = this.dto.point.get(0).y;
			for (int i = this.dto.getSnakeBody() - 2; i >= 0; i--) {
				this.dto.point.get(i + 1).x = this.dto.point.get(i).x;
				this.dto.point.get(i + 1).y = this.dto.point.get(i).y;
			}
			this.dto.point.get(0).y = tempy - this.dto.getSnakeRate();
		}

	}

	/**
	 * 方向：向下
	 */
	public void TurnDown() {
		if (this.isStraightY()) {
			for (int i = 0; i < this.dto.getSnakeBody(); i++) {
				this.dto.point.get(i).y += this.dto.getSnakeRate();
			}
		} else {
			int tempy = this.dto.point.get(0).y;
			for (int i = this.dto.getSnakeBody() - 2; i >= 0; i--) {
				this.dto.point.get(i + 1).x = this.dto.point.get(i).x;
				this.dto.point.get(i + 1).y = this.dto.point.get(i).y;
			}
			this.dto.point.get(0).y = tempy + this.dto.getSnakeRate();
		}

	}

	/**
	 * 如果蛇不在一条横向的直线上 如果在就返回true
	 */
	public boolean isStraightX() {
		for (int i = 0; i < this.dto.getSnakeBody(); i++) {
			if (this.dto.point.get(i).y != this.dto.point.get(0).y) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 如果蛇不在一条纵向的直线上 如果在就返回true
	 */
	public boolean isStraightY() {
		for (int i = 0; i < this.dto.getSnakeBody(); i++) {
			if (this.dto.point.get(i).x != this.dto.point.get(0).x) {
				return false;
			}
		}
		return true;
	}

	// 检查游戏是否失败

	/**
	 * 是否过界 如果过界则返回false
	 */
	public boolean isOverBoundary() {
		// 上界
		if (this.dto.point.get(0).y <= 10) {
			return false;
		}
		// 左界
		if (this.dto.point.get(0).x <= 10) {
			return false;
		}
		// 下界
		if (this.dto.point.get(0).y >= 540 + 10 - 20) {
			return false;
		}
		// 右界
		if (this.dto.point.get(0).x >= 700 + 10 - 20) {
			return false;
		}
		return true;
	}

	/**
	 * 判断蛇有没有吃到自己 如果吃到自己就返回true
	 */
	public boolean isEatItself() {
		Rectangle body;
		Rectangle head = new Rectangle(this.dto.point.get(0).x,
				this.dto.point.get(0).y, 20, 20);
		for (int i = 1; i < this.dto.getSnakeBody(); i++) {
			body = new Rectangle(this.dto.point.get(i).x,
					this.dto.point.get(i).y, 20, 20);
			if (head.intersects(body)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 判断游戏是否结束 如果游戏应该结束则返回true
	 */
	public boolean isGameOver() {
		if (!this.isOverBoundary())
			return true;
		if (this.isEatItself())
			return true;
		return false;
	}

	/**
	 * 计分 计算等级
	 */
	public void calScore() {
		this.dto.setScore(this.dto.getScore() + 10);
		this.dto.setLevel(this.dto.getScore() / 30);
	}

	public GameDto getDto() {
		return dto;
	}

	public void setDto(GameDto dto) {
		this.dto = dto;
	}
}
