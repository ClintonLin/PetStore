package com.ruanko.uitl;

import java.util.List;

public class PageModel {

	private int allLine;//��������
	private int onLine;//��һҳ
	private int nextLine;//��һҳ
	
	private List entityList;//���ݽ����
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
