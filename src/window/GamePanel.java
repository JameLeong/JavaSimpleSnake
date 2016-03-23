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
	 * ��Ϸ��Ϣ������
	 */
	public GameDto dto;
	/**
	 * ��Ϸ������
	 */
	public GameSnake gameService;
	/**
	 * ��Ϸ��λ������
	 */
	public GameAct gameAct;
	/**
	 * ��Ϸ������
	 */
	public GameControl gameControl;

	/**
	 * ��ʼ���������� ���¿�ʼ
	 */
	private void initEverthing() {
		this.dto = new GameDto();
		this.gameService = new GameSnake(this.dto);
		this.gameAct = new GameAct(this.dto,this.gameService);
		this.gameControl = new GameControl(this.dto,this.gameService);

	}
    public GamePanel() {
		// ��ʼ����ͨ��Ϸ 
		this.initEverthing();
		
		// �����¼�����
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				// �ı���ͣ״̬
				if(dto.isStart()){
					if (ke.getKeyCode() == KeyEvent.VK_P) {
					dto.changePause();
				}
				}
				
				if (ke.getKeyCode() == KeyEvent.VK_S) {
					// ������ڿ���״̬���ǾͿ�ʼ��Ϸ
					if (!dto.isStart()) {
						initEverthing();
						dto.setStart(true);
					}

				}
				// �����������Ҽ�ʱ,����
				// ���������ͣ״̬������ܿ���
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
	 * ��������Ϸ���
	 * 
	 * @param g
	 */
	private void playPanel(Graphics g) {
		// ������Ϸ���
		g.drawRect(10, 10, 700, 540);
		// �ж���Ϸ�Ƿ����
		if (this.gameService.isGameOver()) {
			this.dto.setStart(false);
			g.setColor(Color.RED);
			g.setFont(new Font("����",Font.BOLD,80));
			g.drawString("��Ϸ����", 200, 300);
			g.setColor(Color.BLACK);
			
		}
		if(this.dto.isPause()){
			g.setColor(Color.RED);
			g.setFont(new Font("����",Font.BOLD,80));
			g.drawString("��Ϸ��ͣ", 200, 300);
			g.setColor(Color.BLACK);
		}

		// ������
		for (int i = 0; i < this.dto.getSnakeBody(); i++) {
			g.drawRect(this.dto.point.get(i).x, this.dto.point.get(i).y, this.dto.snakeWidth,
					this.dto.snakeHeight);
		}
		// ���ʳ�ﱻ����
		if (this.dto.isEat()) {
			// ����ʳ��
			this.gameAct.creatFood();
			this.dto.setEat(false);;
		}

		// �����Ϸ��ʼ�ſ�ʼ����
		if (this.dto.isStart()) {
			// ����ʳ��
			g.fillRect(this.dto.foodX * 20, this.dto.foodY * 20, 20, 20);

			// �����ͣ�˾Ͳ���ȥ�ж�
			if (!this.dto.isPause()) {
				// �ж���û�г�ʳ��
				if (this.isCanEat()) {
					// ��Ϊʳ�ﱻ���˵�״̬���Է�����������ʳ��
					this.dto.setEat(true); 
					// �ӳ�����
					this.gameAct.creatSnakeRect();
				}
			}

			// ͣ��
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// �ж��Ƿ񵽱߽磬Ȼ�����
			// û�й������������
			if (this.gameService.isOverBoundary()) {
				switch (this.dto.direction) {
				// ���������ͣ״̬�Ϳ�ʼ�˶�
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
	 * �ж���û�гԵ�ʳ��
	 * 
	 * @return ��û�гԵ�
	 */
	public boolean isCanEat() {
		Rectangle food = new Rectangle(this.dto.foodX * 20, this.dto.foodY * 20, 20, 20);
		Rectangle snake = new Rectangle(this.dto.point.get(0).x, this.dto.point.get(0).y, 20, 20);
		return food.intersects(snake);
	}

	/**
	 * ���ƼƷ����
	 * 
	 * @param g
	 */
	private void scorePanel(Graphics g) {
		// ���ƼƷ����
		g.drawRect(560 + 200, 10, 190, 540);

		// �ȼ�
		g.drawRect(760 + 10, 20, 170, 90);
		g.setFont(new Font("����", Font.BOLD, 25));
		g.drawString("�ȼ���", 780, 50);
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD, 40));
		g.drawString(this.dto.getLevel() + "", 840, 90);

		// �÷�
		g.setColor(Color.BLACK);
		g.drawRect(760 + 10, 20 + 90 + 20, 170, 90);
		g.setFont(new Font("����", Font.BOLD, 25));
		g.drawString("�÷֣�", 780, 50 + 90 + 20);
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD, 40));
		g.drawString(this.dto.getScore() + "", 840, 90 + 90 + 20);
		g.setColor(Color.BLACK);

	}

	/**
	 * ��Ϸ˵�����
	 * 
	 * @param g
	 */
	private void explanationPanel(Graphics g) {
		// �÷�
		g.drawRect(770, 240, 170, 90 + 200 + 10);
		g.setFont(new Font("����", Font.BOLD, 25));
		g.drawString("��Ϸ˵����", 780, 50 + 90 + 20 + 90 + 20);
		g.setFont(new Font("����", Font.PLAIN, 15));
		g.drawString("������ٿ�̰����", 780, 350);
		g.drawString("P����ͣ", 780, 400);
		g.drawString("S����ʼ��Ϸ", 780, 450);
		
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
