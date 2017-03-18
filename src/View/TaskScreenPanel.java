package View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import modal.FormTableModel;
import Service.CheckListService;

public class TaskScreenPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractTableModel model;
	JPanel form;
	CheckListService cls = new CheckListService();
	JTable resultTable = null;

	public TaskScreenPanel() {
		model = getTaskTableModel();
		resultTable = new JTable(model);
		resultTable.scrollRectToVisible(resultTable.getCellRect(resultTable.getRowCount() - 1, 0, true));
		JScrollPane scrollPane = new JScrollPane(resultTable);
		form = new FormPanel(this);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, form, scrollPane);
		add(splitPane);
	}

	public AbstractTableModel getTaskTableModel() {

		AbstractTableModel model = new FormTableModel(cls.getTasks());
		return model;
	}

	public void setDeleteData() {

	}

}
