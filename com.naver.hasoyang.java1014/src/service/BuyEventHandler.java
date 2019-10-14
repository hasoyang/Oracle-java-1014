package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.BuyDAO;
import DTO.Buy;
import view.View;

public class BuyEventHandler implements ActionListener {

	View view;
	
	public BuyEventHandler(View view) {
		super();
		//변수는 아무런 기호가 붙지 않으면 가까운 곳에서 만들어진 것을 사용
		//this.을 붙이면 메소드 외부에서 찾아서 사용
		//super.을 붙이면 사우이 클래스에서 찾아서 사용
		this.view = view;
	}

	//버튼을 누르거나 텍스트 필드에서 enter키를 누를 때 처리를 위한 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		Buy buy ;
		BuyDAO dao;
		String itemname;
		String customerID;
		String count;
		int len;
		String imsi;
		
		switch(e.getActionCommand())
		{
		case "<<":
			if(view.idx == 0)
			{
				JOptionPane.showMessageDialog(null, "첫번째 데이터입니다.");
				return;
			}
			view.idx = 0;
			view.display();
			break;
		case "<":
			//첫번째 데이터라면 마지막으로 변경
			if(view.idx == 0)
				view.idx = view.list.size()-1;
			else
				view.idx -= 1;
			
			view.display();
			break;
		case ">":
			if(view.idx == view.list.size()-1)
				view.idx = 0;
			else
				view.idx += 1;
			
			view.display();
			break;
		case ">>":
			if(view.idx == view.list.size()-1)
			{
				JOptionPane.showMessageDialog(null, "마지막 데이터입니다.");
				return;
			}
			else
			{
				view.idx = view.list.size()-1;
				view.display();
			}
			break;
		case "추가":
			buy = new Buy();
			//현재 데이터의 buycode를 가져와서 설정
			buy.setBuycode(view.list.get(view.idx).getBuycode());
			itemname = view.txtItemName.getText().trim();
			if(itemname.length() < 1)
			{
				JOptionPane.showMessageDialog(null, "아이템 이름은 필수 입력");
				return;
			}
			buy.setItemName(itemname);
			customerID = view.txtCustomerID.getText().trim();
			if(customerID.length() < 1)
			{
				JOptionPane.showMessageDialog(null, "구매자 아이디은 필수 입력");
				return;
			}
			buy.setCustomerID(customerID);
			
			count = view.txtCount.getText().trim();
			len = count.length();
			imsi = "";
			//문자열에서 각각의 글자를 가져와서
			//숫자이면 imsi에 더해주고 숫자가 아니면 중단
			for(int i = 0; i < len; i ++)
			{
				char ch = count.charAt(i);
				if(ch>='0' && ch <='9')
				{
					imsi = imsi + ch;
				}
				else
				{
					break;
				}
				
			}
			if(imsi.length() == 0)
			{
				imsi = "0";
			}
			buy.setCount(Integer.parseInt(imsi));
			
			dao = new BuyDAO();
			dao.insertBuy(buy);
			view.list = dao.getList();
			view.display();
			break;
		case "삭제":
			if(view.list.size() == 0)
			{
				JOptionPane.showMessageDialog(null, "지울 데이터가 없습니다!!");
				return;
			}
			else
			{
				int result = JOptionPane.showConfirmDialog(null, "정말로 삭제?", "삭제", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
				{
				
					buy = view.list.get(view.idx);
					//데이터베이스에서 삭제
					dao = new BuyDAO();
					dao.deleteBuy(buy.getBuycode());
					
					//메모리에서 삭제
					view.list.remove(view.idx);
					if(view.list.size() == 0)
					{
						view.idx = 0;
						view.txtBuyDate.setText("");
						view.txtCount.setText("");
						view.txtCustomerID.setText("");
						view.txtItemName.setText("");
					}
					else
					{
						view.idx = 0;
						view.display();
					}
				}
			}
			break;
		case "수정":
			buy = new Buy();
			//현재 데이터의 buycode를 가져와서 설정
			buy.setBuycode(view.list.get(view.idx).getBuycode());
			itemname = view.txtItemName.getText().trim();
			if(itemname.length() < 1)
			{
				JOptionPane.showMessageDialog(null, "아이템 이름은 필수 입력");
				return;
			}
			buy.setItemName(itemname);
			customerID = view.txtCustomerID.getText().trim();
			if(customerID.length() < 1)
			{
				JOptionPane.showMessageDialog(null, "구매자 아이디은 필수 입력");
				return;
			}
			buy.setCustomerID(customerID);
			
			count = view.txtCount.getText().trim();
			len = count.length();
			imsi = "";
			//문자열에서 각각의 글자를 가져와서
			//숫자이면 imsi에 더해주고 숫자가 아니면 중단
			for(int i = 0; i < len; i ++)
			{
				char ch = count.charAt(i);
				if(ch>='0' && ch <='9')
				{
					imsi = imsi + ch;
				}
				else
				{
					break;
				}
				
			}
			if(imsi.length() == 0)
			{
				imsi = "0";
			}
			buy.setCount(Integer.parseInt(imsi));
			
			int r = JOptionPane.showConfirmDialog(null,"정말로 수정?","수정",JOptionPane.YES_NO_OPTION);
			
			if( r == JOptionPane.YES_OPTION)
			{
				dao = new BuyDAO();
				dao.updateBuy(buy);
				view.list = dao.getList();
				view.display();
			}
			break;
		case "지움":
			view.txtBuyDate.setText("");
			view.txtCount.setText("");
			view.txtCustomerID.setText("");
			view.txtItemName.setText("");
			
			view.btnClear.setText("취소");
			view.btnDelete.setEnabled(false);
			view.btnFirst.setEnabled(false);
			view.btnLast.setEnabled(false);
			view.btnNext.setEnabled(false);
			view.btnPrev.setEnabled(false);
			view.btnSearch.setEnabled(false);
			view.btnUpdate.setEnabled(false);
			break;
		case "취소":
			view.idx = 0;
			view.display();
			
			view.btnClear.setText("지움");
			view.btnDelete.setEnabled(true);
			view.btnFirst.setEnabled(true);
			view.btnLast.setEnabled(true);
			view.btnNext.setEnabled(true);
			view.btnPrev.setEnabled(true);
			view.btnSearch.setEnabled(true);
			view.btnUpdate.setEnabled(true);
			break;
		case "조회":
			
			break;
		}

	}

}
