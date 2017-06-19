package com.ruanko.uitl;

import java.util.List;

public class PageModel {

	private int allLine;//所以行数
	private int onLine;//上一页
	private int nextLine;//下一页
	
	private List entityList;//数据结果集
	public int getAllLine() {
		return allLine;
	}
	public void setAllLine(int allLine) {
		this.allLine = allLine;
	}
	public int getOnLine() {
		return onLine;
	}
	public void setOnLine(int onLine) {
		this.onLine = onLine;
	}
	public int getNextLine() {
		return nextLine;
	}
	public void setNextLine(int nextLine) {
		this.nextLine = nextLine;
	}
	public List getEntityList() {
		return entityList;
	}
	public void setEntityList(List entityList) {
		this.entityList = entityList;
	}
	
}
