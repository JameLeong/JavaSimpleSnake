package control;

import java.awt.event.KeyEvent;

import service.GameSnake;
import window.GamePanel;
import dto.GameDto;

/**
 * ��Ϸ�Ŀ�����
 * 
 * @author James
 *
 */
public class GameControl {
	/**
	 * ��Ϸ��Ϣ������
	 */
	public GameDto dto;
	/**
	 * ��Ϸ������
	 */
	public GameSnake gameService;

	/**
	 * ���캯��
	 * 
	 * @param dto
	 * @param gameService
	 */
	public GameControl(GameDto dto, GameSnake gameService) {
		this.dto = new GameDto();
		this.gameService = new GameSnake(this.dto);

	}

	public void controlSnake(KeyEvent ke) {

	}

}
