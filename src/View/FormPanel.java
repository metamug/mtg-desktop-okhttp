package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Service.CheckListService;
import modal.FormTableModel;
import modal.Task;

public class FormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	final JTextField taskName = new JTextField(20);
	final JTextField txtTaskValue = new JTextField(6);
	final JTextField slot = new JTextField(6);
	final JComboBox<String> comboType = new JComboBox<String>();
	final JTable table;
	String taskType = "0";
	double taskValue;
	CheckListService cls = new CheckListService();

	public FormPanel(TaskScreenPanel parent) {

		txtTaskValue.setText("0.0");

		table = parent.resultTable;
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(new JLabel("Slot :"), c);

		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;

		this.add(slot, c);

		c.gridx--;
		c.gridy++;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(new JLabel("Task Name :"), c);

		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;

		this.add(taskName, c);

		// c.gridx--;
		c.gridx++;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(new JLabel("Value :"), c);

		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;

		this.add(txtTaskValue, c);

		c.gridx = 0;
		c.gridy++;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(new JLabel("Type :"), c);

		comboType.addItem("Numeric");
		comboType.addItem("Alpha-Numeric");
		comboType.addItem("Text");
		comboType.setSelectedIndex(0);
		c.gridx++;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(comboType, c);
		comboType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if ("Numeric".equalsIgnoreCase(comboType.getSelectedItem().toString())) {
					taskType = "0";
					txtTaskValue.setEnabled(true);
				} else if ("Alpha-Numeric".equalsIgnoreCase(comboType.getSelectedItem().toString())) {
					taskType = "1";
					txtTaskValue.setEnabled(false);
				} else if ("Text".equalsIgnoreCase(comboType.getSelectedItem().toString())) {
					taskType = "2";
					txtTaskValue.setEnabled(false);
				}

			}
		});

		c.gridx = 0;
		c.gridy++;
		JButton addButton = new JButton("Add");
		this.add(addButton, c);

		c.gridx++;
		JButton deleteBtn = new JButton("Delete");
		this.add(deleteBtn, c);

		c.gridx++;
		JButton editBtn = new JButton("Edit");
		this.add(editBtn, c);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterRecord(true);
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row != -1) {
					cls.deleteTask((int) table.getValueAt(row, -1));
					((FormTableModel) table.getModel()).removeRow(row);
				} else {
					JOptionPane.showMessageDialog(FormPanel.this, "Please select a single row to delete");
				}

			}

		});

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int row = table.getSelectedRow();
				if (row != -1) {

					slot.setText("" + table.getValueAt(row, 0));
					taskName.setText((String) table.getValueAt(row, 1));
					txtTaskValue.setText("" + table.getValueAt(row, 2));
					comboType.setSelectedItem((String) table.getValueAt(row, 3));
				}
			}
		});

		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) // if row is selected
					alterRecord(false);
				else {
					JOptionPane.showMessageDialog(FormPanel.this,
							"Please select a single row to edit.");
				}
			}
		});
	}

	void alterRecord(boolean create) {
		// Check whether entered text isn't empty
		if (taskName.getText().length() == 0 || txtTaskValue.getText().length() == 0 || slot.getText().length() == 0) {
			JOptionPane.showMessageDialog(FormPanel.this, "Please fill all fields.");

		} else if (!slot.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(FormPanel.this, "Slot should be an integer.");
			slot.requestFocus();
		} else {

			if (taskType == "0") {
				try {
					taskValue = Double.parseDouble(txtTaskValue.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(FormPanel.this, "Invalid Input. Please insert valid task value");
					txtTaskValue.requestFocus();
				}
			}
			Task t = new Task(taskName.getText(), taskValue, taskType, Integer.parseInt(slot.getText()));

			FormTableModel model = (FormTableModel) table.getModel();

			if (create) {
				if (!model.contains(t)) {
					cls.postTask(t);
					// update table
					model.setValueAt(t, 0, 0);
				} else {
					JOptionPane.showMessageDialog(FormPanel.this, "Duplicate Slot Number");
					return;
				}
			} else {
				t.setTaskId((int) model.getValueAt(table.getSelectedRow(), -1));
				cls.updateTask(t);
				// update table
				model.setValueAt(t, table.getSelectedRow(), 0);
			}

		}
	}
}
