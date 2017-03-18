package modal;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class SearchModal extends AbstractTableModel {

	private List<SearchTask> li;
	private String[] columnNames = { "Date", "Name", "Threshold", "Hours" };

	public SearchModal(List<SearchTask> list) {
		this.li = list;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return li.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	public void update(List li) {
	    // remove a row from your internal data structure
		this.li = li;
	    fireTableDataChanged();
	}

	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SearchTask si = li.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return si.getTaskDate();
		case 1:
			return si.getTaskName();
		case 2:
			return si.getThreshold();
		case 3:
			return si.getTaskHour();		
		}
		return null;
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		li.add((SearchTask) value);		
		fireTableDataChanged();
	}
	
}
