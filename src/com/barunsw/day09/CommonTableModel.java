package com.barunsw.day09;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class CommonTableModel extends AbstractTableModel {
	private Vector<String> columnInfo = new Vector<>();
	private Vector dataInfo = new Vector();

	public CommonTableModel() {
	}
	
	public CommonTableModel(Vector<String> columnInfo) {
		this.columnInfo = columnInfo;
	}
	
	public void setData(Vector dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	public void addData(Vector oneData) {
		this.dataInfo.add(oneData);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dataInfo.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnInfo.size();
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columnInfo.get(columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object o = dataInfo.get(rowIndex);
		if (o instanceof Vector) {
			Vector oneData = (Vector)o;
			return oneData.get(columnIndex);
		}
		
		return null;
	}

}
