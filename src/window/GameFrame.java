package window;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		//������Ϸ���
		GamePanel panel = new GamePanel();
		
		//�������ڿ����
		panel.setFocusable(true);
		add(panel);
		
		// ��������
		setTitle("̰����");
		setResizable(false);
		setSize(1000, 590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
