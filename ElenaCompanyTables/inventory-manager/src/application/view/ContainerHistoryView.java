package application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import application.controller.ContainerHistoryController;
import application.model.Session;
import application.utils.GridBagLayoutUtils;

public class ContainerHistoryView extends JFrame {

	private static final long serialVersionUID = 1L;

	private ContainerHistoryController controller;
	private JTable tblContainerHistory;
	private JLabel lblSession;
	private JLabel lblContainer;
	private JButton btnAdd;
	private JButton btnDelete;
	private NewContainerHistoryView newContainerHistoryView;

	public ContainerHistoryView(ContainerHistoryController controller) {
		this.controller = controller;
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Container History");
		setPreferredSize(new Dimension(800, 600));

		// buttons
		JButton btnAdd = new JButton("Add data");
		btnAdd.setEnabled(true);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newContainerHistoryView = new NewContainerHistoryView(controller);
			}
		});

		JButton btnDelete = new JButton("Remove selected item");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeHistoryRowController(tblContainerHistory.getSelectedRow());
			}
		});

		// toolbar
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);

		lblContainer = new JLabel();
		lblContainer.setHorizontalAlignment(SwingConstants.RIGHT);

		JToolBar toolbar = new JToolBar();
		toolbar.add(btnAdd);
		toolbar.add(btnDelete);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		toolbar.add(lblContainer);
		add(toolbar, BorderLayout.NORTH);

		tblContainerHistory = new JTable();
		tblContainerHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblContainerHistory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDelete.setEnabled((tblContainerHistory.getSelectedRow() >= 0));
			}
		});
		add(new JScrollPane(tblContainerHistory), BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	public void setTableModel(TableModel model, String containerID) {
		tblContainerHistory.setModel(model);
		lblContainer.setText("<html> Container " + containerID + " status history</i></html>");
	}

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}
}
