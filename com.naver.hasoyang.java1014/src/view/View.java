package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.BuyDAO;
import DTO.Buy;
import service.BuyEventHandler;

public class View extends JFrame {
	
	BuyEventHandler event = new BuyEventHandler(this);
	public JTextField txtItemName, txtCustomerID, txtCount, txtBuyDate ;
	public JLabel lblItemName, lblCustomerID, lblCount, lblBuyDate;
	
	//데이터 조회를 위한 컴포넌트
	public JButton btnLast,btnPrev,btnNext,btnFirst;
	public JLabel lblNum;
	
	//데이터 작업을 수행할 컴포넌트
	public JButton btnInsert,btnDelete,btnUpdate,btnSearch,btnClear;
	//데이터 전체를 저장할 List를 인스턴스로 선언
	public List<Buy> list ;
	//현재 보여지는 데이터의 인덱스를 저장할 변수
	public int idx;
	public View()
	{
		//4줄 2칸으로 만들고 여백을 가로 3 세로 3으로 설정
		JPanel p1 = new JPanel(new GridLayout(4,2,3,3));
		lblItemName = new JLabel("아이템이름",JLabel.RIGHT);
		txtItemName = new JTextField();
		
		lblCustomerID = new JLabel("구매자아이디",JLabel.RIGHT);
		txtCustomerID = new JTextField();
		
		lblCount = new JLabel("구매수량",JLabel.RIGHT);
		txtCount = new JTextField();
		txtCount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//0에서 9사이의 키가 아닌 것을 누르면
				if(e.getKeyCode() < KeyEvent.VK_0 || e.getKeyCode() > KeyEvent.VK_9)
				{
					String imsi = "";
					int len = txtCount.getText().trim().length();
					for(int i = 0; i < len-1; i ++ )
					{
						char ch = txtCount.getText().charAt(i);
						imsi = imsi + ch;
					}
					txtCount.setText(imsi);
				}
				
			}
			
		});
		lblBuyDate = new JLabel("구매날짜",JLabel.RIGHT);
		txtBuyDate = new JTextField();
		txtBuyDate.setEditable(false);
		
		p1.add(lblItemName);
		p1.add(txtItemName);
		
		p1.add(lblCustomerID);
		p1.add(txtCustomerID);
		
		p1.add(lblCount);
		p1.add(txtCount);
		
		p1.add(lblBuyDate);
		p1.add(txtBuyDate);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add("Center",p1);
		
		add(centerPanel);
		
		JPanel p2 = new JPanel(new GridLayout(1,5,3,3));

		btnFirst = new JButton("<<");
		btnPrev = new JButton("<");
		btnNext = new JButton(">");
		btnLast = new JButton(">>");
		
		btnFirst.addActionListener(event);
		btnPrev.addActionListener(event);
		btnNext.addActionListener(event);
		btnLast.addActionListener(event);
		
		lblNum = new JLabel("",JLabel.CENTER);
		
		p2.add(btnFirst);
		p2.add(btnPrev);
		p2.add(lblNum);
		p2.add(btnNext);
		p2.add(btnLast);
		
		centerPanel.add("South", p2);
		
		JPanel p3 = new JPanel(new GridLayout(1,5,3,3));
		
		//추가 삭제 수정 지움 조회 
		btnInsert = new JButton("추가");
		btnDelete = new JButton("삭제");
		btnUpdate = new JButton("수정");
		btnClear = new JButton("지움");
		btnSearch = new JButton("조회");
		
		btnClear.addActionListener(event);
		btnInsert.addActionListener(event);
		btnDelete.addActionListener(event);
		btnUpdate.addActionListener(event); 
		btnSearch.addActionListener(event); 
		
		p3.add(btnInsert);
		p3.add(btnDelete);
		p3.add(btnUpdate);
		p3.add(btnClear);
		p3.add(btnSearch);
		
		add("South",p3);
		
		
		BuyDAO dao = new BuyDAO();
		list = dao.getList();
		
		display();
		
		setTitle("구매");
		//위치와 크기를 한꺼번에 설정
		setBounds(200,200,350,200);
		//종료 기능 부여
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//list에서 idx번째 데이터를 가져와서 화면에 출력하는 메소드
	public void display()
	{
		//배열이나 list에서 데이터를 꺼낼 때
		//ArrayIndexOutOfBoundsException에 주의
		if(list == null || list.size() == 0)
		{
			JOptionPane.showMessageDialog(null, "출력할 데이터가 없습니다.");
			return;
		}
		Buy buy = list.get(idx);
		
		this.txtItemName.setText(buy.getItemName());
		this.txtCustomerID.setText(buy.getCustomerID());
		String disp = String.format("%d개", buy.getCount());
		this.txtCount.setText(disp);
		this.txtBuyDate.setText(buy.getBuydate().toString());
		
		this.lblNum.setText(idx+1+"");
		
	}
}
