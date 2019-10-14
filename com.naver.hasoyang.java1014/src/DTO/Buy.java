package DTO;

import java.sql.Date;

public class Buy {
	private int buycode; 		// 구매코드
	private String itemName; 	// 상품명
	private String CustomerID; 	// 구매자아이디
	private int count;			// 수량
	private Date buydate;		// 구매일자
	
	
	public int getBuycode() {
		return buycode;
	}
	public void setBuycode(int buycode) {
		this.buycode = buycode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getBuydate() {
		return buydate;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
	
	@Override
	public String toString() {
		return "Buy [buycode=" + buycode + ", itemName=" + itemName + ", CustomerID=" + CustomerID + ", count=" + count
				+ ", buydate=" + buydate + "]";
	}
}