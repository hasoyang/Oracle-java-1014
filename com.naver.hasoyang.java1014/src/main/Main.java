package main;

import java.util.List;

import DAO.BuyDAO;
import DTO.Buy;
import view.View;

public class Main {

	public static void main(String[] args) {
		BuyDAO dao = new BuyDAO();
		
		new View();
		//데이터 삽입
		/*
		Buy buy = new Buy();
		buy.setItemName("커피");
		buy.setCustomerID("hasoyang");
		buy.setCount(3);
		
		int r = dao.insertBuy(buy);
		
		if(r > 0)
		{
			System.out.printf("삽입성공\n");
		}
		else
		{
			System.out.printf("삽입실패\n");	
		}
		*/
		
		//데이터 수정
		/*
		Buy buy = new Buy();
		
		buy.setBuycode(1);
		buy.setItemName("과일");
		buy.setCustomerID("haso");
		buy.setCount(7);
		
		int r = dao.updateBuy(buy);
		
		if(r > 0)
		{
			System.out.printf("수정성공\n");
		}
		else
		{
			System.out.printf("수정실패\n");	
		}
		*/
		
		//데이터 삭제
		/*
		int r = dao.deleteBuy(1);
		
		if(r > 0)
		{
			System.out.printf("삭제성공\n");
		}
	
		else
		{
			System.out.printf("삭제실패\n");	
		}
		*/
		
		//System.out.printf("%s\n", dao.getBuy(2));
		
	}

}
