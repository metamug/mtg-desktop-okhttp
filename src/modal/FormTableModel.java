package modal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FormTableModel extends AbstractTableModel {

	private List<Task> li;
	private String[] columnNames = { "Slot", "Name", "Threshold", "Type" };

	public FormTableModel(List<Task> list) {
		this.li = list;
	}

	public boolean contains(Task t) {
		return li.contains(t);
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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Task task = li.get(rowIndex);
		switch (columnIndex) {
		case 1:
			return task.getTaskName();
		case 2:
			return task.getTaskThreshold();
		case 3:
			return task.getTaskType();
		case 0:
			return task.getSlot();
		case -1: //since this is not visible in table
			return task.getTaskId(); 
		}
		return null;
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		Task task = (Task) value;
		li.add(row, task);
		//Since this is pushed to next row.
		li.remove(row+1);
		
		// Sort it on slot before display.
		Collections.sort(li, new Comparator<Task>() {
			@Override
			public int compare(Task a, Task b) {
				return a.getSlot() - b.getSlot();
			}
		});
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		// remove a row from your internal data structure
		fireTableRowsDeleted(row, row);
		li.remove(row);

	}

}
