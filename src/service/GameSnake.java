package service;

import java.awt.Rectangle;

import dto.GameDto;

public class GameSnake {
	private GameDto dto;

	public GameSnake(GameDto dto) {
		this.dto = dto;

	}

	/**
	 * ��������
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
	 * ��������
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
	 * ������
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
	 * ��������
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
	 * ����߲���һ�������ֱ���� ����ھͷ���true
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
	 * ����߲���һ�������ֱ���� ����ھͷ���true
	 */
	public boolean isStraightY() {
		for (int i = 0; i < this.dto.getSnakeBody(); i++) {
			if (this.dto.point.get(i).x != this.dto.point.get(0).x) {
				return false;
			}
		}
		return true;
	}

	// �����Ϸ�Ƿ�ʧ��

	/**
	 * �Ƿ���� ��������򷵻�false
	 */
	public boolean isOverBoundary() {
		// �Ͻ�
		if (this.dto.point.get(0).y <= 10) {
			return false;
		}
		// ���
		if (this.dto.point.get(0).x <= 10) {
			return false;
		}
		// �½�
		if (this.dto.point.get(0).y >= 540 + 10 - 20) {
			return false;
		}
		// �ҽ�
		if (this.dto.point.get(0).x >= 700 + 10 - 20) {
			return false;
		}
		return true;
	}

	/**
	 * �ж�����û�гԵ��Լ� ����Ե��Լ��ͷ���true
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
	 * �ж���Ϸ�Ƿ���� �����ϷӦ�ý����򷵻�true
	 */
	public boolean isGameOver() {
		if (!this.isOverBoundary())
			return true;
		if (this.isEatItself())
			return true;
		return false;
	}

	/**
	 * �Ʒ� ����ȼ�
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
