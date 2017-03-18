package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Service.CheckListService;
import modal.SearchTableModel;

public class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	final JTextField fromDate = new JTextField(6);
	final JTextField toDate = new JTextField(6);
	final JButton searchBtn = new JButton("Search");
	java.sql.Date curDate;

	final SearchTableModel model;
	CheckListService cls = new CheckListService();

	public SearchPanel(SearchScreenPanel parent) {
		model = (SearchTableModel) parent.resultTable.getModel();
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(new JLabel("From Date :"), c);

		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;

		this.add(fromDate, c);

		c.gridx++;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(new JLabel("To Date"), c);

		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;

		this.add(toDate, c);

		c.gridx++;
		this.add(searchBtn, c);

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fromDate.getText() != null && fromDate.getText().trim().length() > 0
						|| toDate.getText() != null && toDate.getText().trim().length() > 0) {

					model.update(cls.getSearch(fromDate.getText(), toDate.getText()));

					model.update(cls.getSearch(fromDate.getText(), toDate.getText()));
				} else {
					model.update(cls.getSearch());
				}
			}

		});
	}

}
