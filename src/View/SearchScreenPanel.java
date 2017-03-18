package View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Service.CheckListService;
import modal.SearchTableModel;

public class SearchScreenPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	AbstractTableModel model;
	JPanel form;
	CheckListService cls = new CheckListService();
	JTable resultTable = null;

	public SearchScreenPanel() {
		model = getTaskTableModel();
		resultTable = new JTable(model);
		resultTable.scrollRectToVisible(resultTable.getCellRect(resultTable.getRowCount() - 1, 0, true));
		JScrollPane scrollPane = new JScrollPane(resultTable);
		form = createTaskForm();
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, form, scrollPane);
		add(splitPane);
	}

	public JPanel createTaskForm() {
		return new SearchPanel(this);
	}

	public AbstractTableModel getTaskTableModel() {

		AbstractTableModel model = new SearchTableModel(cls.getSearch());
		return model;
	}

}
