package MainScreen;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ComputerScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	public static Vector<Computer> computerList = new Vector<Computer>();
	int x = 12;
	int y = 10;
	int row = 0;
	int computerNumber = 0;

	//������
	public ComputerScreen(int numberOfRow) {
		Dimension size = new Dimension();	//����� �����ϱ� ���� ��ü ����
		size.setSize(500, 800);			//��ü�� ����� ����
		this.setPreferredSize(size);		//������ ������ ������ �ִ� ��ü�� �̿��� �г��� ������ ����
		this.mountEventHandler();
		setLayout(null);
		//�� ��
		while(row < numberOfRow){
			//6���� ��ġ
			for(int i=0; i<=6; i++){
				if(i != 0){
					x += 95;
				}
				if(x > 580){
					x = 12;
					y += 136;
					row++;
					break;
				}
				Computer computer = new Computer(++computerNumber);
				computer.setLocation(x, y);
				add(computer);
				computerList.add(computer);
			}
		}
		System.out.println("������ : " + this.getSize().height);
	}
	
	public void mountEventHandler(){
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				//���콺 ���� ��ư
				if(e.getButton() == MouseEvent.BUTTON1){
					Vector<Computer> list = ComputerScreen.computerList;
					for(int i=0; i<list.size(); i++){
						list.get(i).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					}
				}
			}
		});
	}
}