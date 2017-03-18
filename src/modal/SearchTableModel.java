package modal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2484130177460220320L;

	private List<SearchTask> li;
	private String[] columnNames = { "Name", "Value", "Hours", "Date" };

	public SearchTableModel(List<SearchTask> list) {
		this.li = list;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		if (li != null)
			return li.size();
		else
			return 0;
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
		String str = "", hrString = "";
		int hr = 0;
		switch (columnIndex) {
		case 3:
			try {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd KK:mm a");
				Date t = format.parse(si.getTaskDate());
				str = timeFormat.format(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str;
		case 0:
			return si.getTaskName();
		case 1:
			return si.getThreshold();
		case 2:
			if (si.getTaskHour() > 12) {
				hr = si.getTaskHour() - 12;
				hrString = String.valueOf(hr) + "PM";
			} else {
				hrString = String.valueOf(si.getTaskHour()) + "AM";
			}
			return hrString;
		}
		return null;
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		li.add((SearchTask) value);
		fireTableDataChanged();
	}
}
