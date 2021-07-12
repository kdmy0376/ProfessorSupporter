package FileTransfer;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/* ���� �ޱ� �� 
 * 
 */

public class ReceiveFile extends JDialog implements ActionListener{

	private JProgressBar 	jb;
	private JPanel	jp1, jp2, jp3;	
	private BufferedWriter	bufWriter;	
	private Socket socket;
	private long fileLength;
	private String fileName;
	private JButton b, b1, b2;
	
	public ReceiveFile (JFrame frame, String addr){// �����Ӱ� ����� �������ּҸ� ��� ��
		//�θ�������â, ����, Modal���� ����
		super(frame, "���� �ޱ�â", false);
				
		jp1	= new JPanel(new GridLayout(0, 1));
		jp2	= new JPanel(new FlowLayout());
		jp3	= new JPanel(new GridLayout(2, 0));
		
		jb = new JProgressBar(0, 100);
		
		jb.setStringPainted(true);
		jb.setString("�����... 0%");
		b1 = new JButton("�ޱ�");
		b1.addActionListener(this);
		b = new JButton("���");
		b.addActionListener(this);
		b2 = new JButton("�ݱ�");
		b2.addActionListener(this);
		
		jp1.add(jb);
		jp2.add(b1);
		jp2.add(b);
		jp2.add(b2);
		jp3.add(jp1);	
		jp3.add(jp2);
		
		Container con = getContentPane();
		con.add(jp3);
		
		try{
			//���� ���� �ϰ� �������Ͽ� ����
			socket = new Socket(addr, 7777);
			
			BufferedReader	bufReader	= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String msg = bufReader.readLine();
			
			String tmp[] = msg.split(",");
			
			fileName = tmp[0];	
			fileLength = Long.parseLong(tmp[1]);
			
			if(fileName.length() >= 20)
				
				jb.setString(fileName.substring(0, 20) + "...");
			
			else
				
				jb.setString(fileName);
			
		}
		
		catch(Exception e){ 
			
			System.out.println(e);
			e.printStackTrace();
			
		}
		
		this.addWindowListener(new WindowAdapter(){
			
     		public void windowClosing(WindowEvent we){		// ���� �ݱ�� ���� ���� ����
     		
    			try{	
    				
    				socket.close();
    				socket = null;
    				
    			}catch(Exception e){ 
    				
    				System.out.println(e);
    				e.printStackTrace();
    				
    			}
    			
    			jb.setEnabled(false);
    			
     		}
		});
					
		this.setSize(300, 100);				
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension di = tk.getScreenSize();
	    Dimension di1 = this.getSize();
	    //ȭ���� ���߾ӿ� ��ġ
	    this.setLocation((int)(di.getWidth()/2 - di1.getWidth()/2), (int)(di.getHeight()/2 - di1.getHeight()/2));
	    this.setVisible(true);
	    
	}
	
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource() == b1){
			
			((JButton)ae.getSource()).setEnabled(false);
			
			try{
				
				bufWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));			
				bufWriter.write("Accept" + "\n");
				bufWriter.flush();
			
				Thread t = new Thread(){
					
					public void run(){
						
						getFile();
						
					}
				};
				
				t.start();
			
			}catch(Exception e){ 
				
				System.out.println(e);
				e.printStackTrace();
				
			}
			
		}
		
		else if(ae.getSource() == b){

			JOptionPane.showMessageDialog(this,	"���Ϻ����Ⱑ ��ҵǾ����ϴ�!", "���â", JOptionPane.ERROR_MESSAGE);
			
			try{
				
				jb.setString("�������!!!");
				jb.setEnabled(false);
				b2.setEnabled(true);
				b.setEnabled(false);
				b1.setEnabled(false);
				socket.close();
				socket = null;
				
			}catch(Exception e){ 
				
				System.out.println(e);
				e.printStackTrace();
				
			}	
			
		}else if(ae.getSource() == b2){
			
			int cc = JOptionPane.showConfirmDialog(this, "���Ϲޱ�â�� �ݰڽ��ϱ�?", "Ȯ��â", JOptionPane.YES_NO_OPTION);
			
			if(cc == 0)
				
				this.dispose();
			
		}
		
	}
	
	void getFile(){	// ���� �ޱ� �޼ҵ�
	
		try {
			
			File f = new File("c:/", fileName);	// ���� ��η� ������ c:/�� ����
			
			FileOutputStream out = new FileOutputStream(f);
			InputStream is = socket.getInputStream();
			
			int	i = 0;	// ��Ʈ������ ���� �о� ���°� �ӽ÷� ��� ���� ����
			long cnt = 0;	// ���۷� üũ�� ���� ����
						
			while((i = is.read()) != -1){ // ���� ���϶����� �ϱ� �۾��� ��� �մϴ�.
			
				out.write((char)i);
				
				cnt ++;
				
				int result = (int)(cnt / fileLength) ;
				
				if(result > jb.getValue()){
					jb.setValue(result);
					jb.setString("������...\t " + result + "%");
					b1.setEnabled(false);
					b2.setEnabled(false);
				}
			}
			
			b1.setEnabled(false);
			b.setEnabled(false);
			
			if((cnt / fileLength) < 100){	// 100 ���� ���� �ÿ� �������ᰡ �ƴϹǷ� ���и� �׷��������� ����.
				jb.setString("�������!!!");
				jb.setEnabled(false);
				b.setEnabled(false);
				b1.setEnabled(false);
				b2.setEnabled(true);
			}
			else{	
				jb.setValue(100);
				jb.setString("���� �Ϸ�!!! 100%");
				out.close();				
				JOptionPane.showMessageDialog(this,	"���������� �Ϸ�ƽ��ϴ�!", "�˸�â", JOptionPane.INFORMATION_MESSAGE);				
				this.dispose();
			}
		}catch(Exception e){ 
			System.out.println(e);
			e.printStackTrace();	
		}	
	}
}