package entity;

import java.awt.Point;

import service.GameSnake;
import dto.GameDto;

public class GameAct {
	private GameDto dto;
	private GameSnake gameService;
	public GameAct(GameDto dto,GameSnake gameService){
		this.gameService = gameService;
		this.dto = dto;
	}
	
	/**
	 * 产生一个食物
	 */
	public void creatFood() {
		// 返回(1,34)
		this.dto.foodX = 1 + (int) (Math.random() * 34);
		// 返回(1,26)
		this.dto.foodY = 1 + (int) (Math.random() * 26);
	}
	/**
	 * 从蛇头增加蛇身
	 */
	public void creatSnakeRect() {
		int x = 0;
		int y = 0;

		// Y轴方向的直线(向上)
		if (this.dto.direction == 3) {
			x = this.dto.point.get(0).x;
			y = this.dto.point.get(0).y - this.dto.snakeHeight;

		}
		// Y轴方向的直线(向下)
		else if (this.dto.direction == 4) {
			x =this.dto. point.get(0).x;
			y =this.dto. point.get(0).y + this.dto.snakeHeight;
		}
		// X轴方向的直线(向左)
		else if (this.dto.direction == 1) {
			x =this.dto. point.get(0).x - this.dto.snakeWidth;
			y =this.dto. point.get(0).y;
		}
		// X轴方向的直线(向右)
		else if (this.dto.direction == 2) {
			x = this.dto.point.get(0).x + this.dto.snakeWidth;
			y = this.dto.point.get(0).y;
		}

		for (int i = this.dto.getSnakeBody() - 1; i >= 0; i--) {
			this.dto.point.add(new Point(0, 0));
			this.dto.point.set(i + 1, this.dto.point.get(i));
		}
		this.dto.point.set(0, new Point(x, y));
		this.dto.setSnakeBody(this.dto.getSnakeBody()+1);
		// 计分
		this.gameService.calScore();
	}


}
