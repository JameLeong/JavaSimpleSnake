package window;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		//创建游戏面板
		GamePanel panel = new GamePanel();
		
		//把面板放在框架上
		panel.setFocusable(true);
		add(panel);
		
		// 创建窗口
		setTitle("贪吃蛇");
		setResizable(false);
		setSize(1000, 590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
