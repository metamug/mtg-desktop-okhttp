package View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class ViewChecklist extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JTree tree;
	JPanel windowPane;

	public ViewChecklist(String title) {
		super(title);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Checklist");
		DefaultMutableTreeNode form = new DefaultMutableTreeNode("Form");
		DefaultMutableTreeNode search = new DefaultMutableTreeNode("Search");

		root.add(form);
		root.add(search);
		tree = new JTree(root);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ViewChecklist.this.setVisible(false);
				ViewChecklist.this.dispose();
			}
		});
		setResizable(false);
		createView();
		setLocation(300, 100);
		pack();
	}

	private void createView() {

		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null) {
					return;
				}
				Object nodeInfo = node.getUserObject();
				if (node.isLeaf()) {
					switchView(nodeInfo.toString());
				}
			}
		});

		windowPane = new JPanel();
		windowPane.add(new TaskScreenPanel());
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tree, windowPane);
		getContentPane().add(pane);
	}

	public void switchView(String view) {
		windowPane.removeAll();
		if (view.equals("Search")) {
			windowPane.add(new SearchScreenPanel());
		} else if (view.equals("Form")) {
			windowPane.add(new TaskScreenPanel());
		}
		windowPane.revalidate();
		windowPane.repaint();
	}

}
