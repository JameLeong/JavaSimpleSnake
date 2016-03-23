package control;

import java.awt.event.KeyEvent;

import service.GameSnake;
import window.GamePanel;
import dto.GameDto;

/**
 * 游戏的控制器
 * 
 * @author James
 *
 */
public class GameControl {
	/**
	 * 游戏信息储存类
	 */
	public GameDto dto;
	/**
	 * 游戏操作类
	 */
	public GameSnake gameService;

	/**
	 * 构造函数
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
