package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Buy;

public class BuyDAO {
	//클래스를 처음 사용할 때 한 번만 수행하는 코드
	static {
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e)
		{
			System.out.printf("클래스 로드 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
	}
	
	//데이터베이스 연동에 필요한 변수
	//데이터베이스 연결 변수
	private Connection con;
	//SQL 실행 변수
	private PreparedStatement pst;
	//select 구문의 결과 사용 변수
	private ResultSet rs;
	
	public int insertBuy(Buy buy)
	{
		int result = -1;
		
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.13:1521:xe",
					"user08","user08");
			pst = con.prepareStatement(
					"insert into buy(buycode, itemname, customerid, count) "
					+ "values(buyseq.nextval,?,?,?)");
			
			//sql에 물음표가 있으면 실제데이터로 바인딩
			pst.setString(1, buy.getItemName());
			pst.setString(2, buy.getCustomerID());
			pst.setInt(3, buy.getCount());
			
			//sql실행
			result = pst.executeUpdate();
			
			//정리
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.printf("데이터 삽입 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateBuy(Buy buy)
	{
		int result = -1;
		
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.13:1521:xe",
					"user08","user08");
			pst = con.prepareStatement(
					"update buy "
					+ "set itemname = ?, customerid = ? , count = ? "
					+ "where buycode = ?");
			
			pst.setString(1, buy.getItemName());
			pst.setString(2, buy.getCustomerID());
			pst.setInt(3, buy.getCount());
			
			pst.setInt(4, buy.getBuycode());
			
			//sql실행
			result = pst.executeUpdate();
			
			//정리
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.printf("데이터 수정 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteBuy(int buycode)
	{
		int result = -1;
		
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.13:1521:xe",
					"user08","user08");
			pst = con.prepareStatement(
					"delete "
					+ "from buy "
					+ "where buycode = ?");
			
			pst.setInt(1, buycode);
			
			//sql실행
			result = pst.executeUpdate();
			
			//정리
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.printf("데이터 삭제 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	//전체 데이터를 조회하는 메소드
	public List<Buy> getList()
	{
		List<Buy> list = new ArrayList<Buy>();
		
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.13:1521:xe",
					"user08","user08");
			pst = con.prepareStatement("select * from buy");
			
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Buy buy = new Buy();
				buy.setBuycode(rs.getInt("buycode"));
				buy.setItemName(rs.getString("itemname"));
				buy.setCustomerID(rs.getString("customerid"));
				buy.setCount(rs.getInt("count"));
				buy.setBuydate(rs.getDate("buydate"));
				
				list.add(buy);
			}
			//정리
			rs.close();
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.printf("데이터 조회 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	//기본키를 가지고 조회하는 메소드
	public Buy getBuy(int buycode)
	{
		Buy buy = null;
		
		try
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.13:1521:xe",
					"user08","user08");
			pst = con.prepareStatement(
					"select * from buy where buycode = ?");
			
			pst.setInt(1, buycode);
			
			rs = pst.executeQuery();

			if(rs.next())
			{
				buy = new Buy();
				
				buy.setBuycode(rs.getInt("buycode"));
				buy.setItemName(rs.getString("itemname"));
				buy.setCustomerID(rs.getString("customerid"));
				buy.setCount(rs.getInt("count"));
				buy.setBuydate(rs.getDate("buydate"));
			}
			//정리
			rs.close();
			pst.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.printf("데이터 조회 예외 : %s\n", e.getMessage());
			e.printStackTrace();
		}
		
		return buy;
	}
	
}
