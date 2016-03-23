package window;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import control.GameControl;
import service.GameSnake;
import dto.GameDto;
import entity.GameAct;

public class GamePanel extends JPanel {
	/**
	 * 游戏信息储存类
	 */
	public GameDto dto;
	/**
	 * 游戏操作类
	 */
	public GameSnake gameService;
	/**
	 * 游戏单位生成类
	 */
	public GameAct gameAct;
	/**
	 * 游戏控制类
	 */
	public GameControl gameControl;

	/**
	 * 初始化所有数据 重新开始
	 */
	private void initEverthing() {
		this.dto = new GameDto();
		this.gameService = new GameSnake(this.dto);
		this.gameAct = new GameAct(this.dto,this.gameService);
		this.gameControl = new GameControl(this.dto,this.gameService);

	}
    public GamePanel() {
		// 初始化普通游戏 
		this.initEverthing();
		
		// 设置事件监听
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				// 改变暂停状态
				if(dto.isStart()){
					if (ke.getKeyCode() == KeyEvent.VK_P) {
					dto.changePause();
				}
				}
				
				if (ke.getKeyCode() == KeyEvent.VK_S) {
					// 如果不在开启状态，那就开始游戏
					if (!dto.isStart()) {
						initEverthing();
						dto.setStart(true);
					}

				}
				// 按下向左、向右键时,方向
				// 如果不是暂停状态，则接受控制
				if (!dto.isPause() && dto.isStart()) {
					if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
						dto.direction = 1;

					} else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
						dto.direction = 2;

					} else if (ke.getKeyCode() == KeyEvent.VK_UP) {
						dto.direction = 3;

					} else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
						dto.direction = 4;

					} else if (ke.getKeyCode() == KeyEvent.VK_W) {
						gameAct.creatSnakeRect();
					}
				}

			}

		});

	}

	/**
	 * 绘制玩游戏面板
	 * 
	 * @param g
	 */
	private void playPanel(Graphics g) {
		// 绘制游戏面板
		g.drawRect(10, 10, 700, 540);
		// 判断游戏是否结束
		if (this.gameService.isGameOver()) {
			this.dto.setStart(false);
			g.setColor(Color.RED);
			g.setFont(new Font("黑体",Font.BOLD,80));
			g.drawString("游戏结束", 200, 300);
			g.setColor(Color.BLACK);
			
		}
		if(this.dto.isPause()){
			g.setColor(Color.RED);
			g.setFont(new Font("黑体",Font.BOLD,80));
			g.drawString("游戏暂停", 200, 300);
			g.setColor(Color.BLACK);
		}

		// 画蛇身
		for (int i = 0; i < this.dto.getSnakeBody(); i++) {
			g.drawRect(this.dto.point.get(i).x, this.dto.point.get(i).y, this.dto.snakeWidth,
					this.dto.snakeHeight);
		}
		// 如果食物被吃了
		if (this.dto.isEat()) {
			// 产生食物
			this.gameAct.creatFood();
			this.dto.setEat(false);;
		}

		// 如果游戏开始才开始绘制
		if (this.dto.isStart()) {
			// 画出食物
			g.fillRect(this.dto.foodX * 20, this.dto.foodY * 20, 20, 20);

			// 如果暂停了就不再去判断
			if (!this.dto.isPause()) {
				// 判断有没有吃食物
				if (this.isCanEat()) {
					// 设为食物被吃了的状态，以方便重新生成食物
					this.dto.setEat(true); 
					// 延长蛇身
					this.gameAct.creatSnakeRect();
				}
			}

			// 停顿
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 判断是否到边界，然后操作
			// 没有过界则继续运行
			if (this.gameService.isOverBoundary()) {
				switch (this.dto.direction) {
				// 如果不是暂停状态就开始运动
				case 1:
					if (!this.dto.isPause()) {
						this.gameService.TurnLeft();
					}
					break;
				case 2:
					if (!this.dto.isPause()) {
						this.gameService.TurnRight();
					}

					break;
				case 3:
					if (!this.dto.isPause()) {
						this.gameService.TurnUp();
					}

					break;
				case 4:
					if (!this.dto.isPause()) {
						this.gameService.TurnDown();
					}

					break;
				}
			}
			
		}

	}

	/**
	 * 判断有没有吃到食物
	 * 
	 * @return 有没有吃到
	 */
	public boolean isCanEat() {
		Rectangle food = new Rectangle(this.dto.foodX * 20, this.dto.foodY * 20, 20, 20);
		Rectangle snake = new Rectangle(this.dto.point.get(0).x, this.dto.point.get(0).y, 20, 20);
		return food.intersects(snake);
	}

	/**
	 * 绘制计分面板
	 * 
	 * @param g
	 */
	private void scorePanel(Graphics g) {
		// 绘制计分面板
		g.drawRect(560 + 200, 10, 190, 540);

		// 等级
		g.drawRect(760 + 10, 20, 170, 90);
		g.setFont(new Font("黑体", Font.BOLD, 25));
		g.drawString("等级：", 780, 50);
		g.setColor(Color.RED);
		g.setFont(new Font("黑体", Font.BOLD, 40));
		g.drawString(this.dto.getLevel() + "", 840, 90);

		// 得分
		g.setColor(Color.BLACK);
		g.drawRect(760 + 10, 20 + 90 + 20, 170, 90);
		g.setFont(new Font("黑体", Font.BOLD, 25));
		g.drawString("得分：", 780, 50 + 90 + 20);
		g.setColor(Color.RED);
		g.setFont(new Font("黑体", Font.BOLD, 40));
		g.drawString(this.dto.getScore() + "", 840, 90 + 90 + 20);
		g.setColor(Color.BLACK);

	}

	/**
	 * 游戏说明面板
	 * 
	 * @param g
	 */
	private void explanationPanel(Graphics g) {
		// 得分
		g.drawRect(770, 240, 170, 90 + 200 + 10);
		g.setFont(new Font("黑体", Font.BOLD, 25));
		g.drawString("游戏说明：", 780, 50 + 90 + 20 + 90 + 20);
		g.setFont(new Font("宋体", Font.PLAIN, 15));
		g.drawString("方向键操控贪吃蛇", 780, 350);
		g.drawString("P：暂停", 780, 400);
		g.drawString("S：开始游戏", 780, 450);
		
		g.setColor(Color.RED);
		g.drawString("By James", 860, 530);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.playPanel(g);
		this.scorePanel(g);
		this.explanationPanel(g);
		repaint();
	}

	
}
