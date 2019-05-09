package Common;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class QueDisplayPanel extends JPanel {
	private JTable table;
	private String[] columnNames = {"ID","Name","E-mail","Time","Question"};
	private JLabel QueLengthLabel;
	private ModelDataManager modelDataManager;
	private JTextArea banListTextArea ;
	/**
	 * Create the panel.
	 */
	public QueDisplayPanel() {
		
		this.setBounds(2, 5, 790, 546);
		this.setLayout(null);
		
		String[][] data = {{"1","aa","aaa","dsadsafsfasf", "dasfassfsdgds"}};
		
		TableModel queTableModel = new DefaultTableModel(data,columnNames);
		
		table = new JTable(queTableModel);
		table.setFont(new Font("Arial", Font.PLAIN, 18));
//		table.setBounds(2,2, 700, 320);
		table.setEnabled(false);

		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(45, 135, 700, 208);
//		scrollPane.add(table);
		add(scrollPane);
		
		JLabel lblQueueLength = new JLabel("Queue Length :");
		lblQueueLength.setFont(new Font("Arial", Font.PLAIN, 25));
		lblQueueLength.setBounds(80, 50, 200, 50);
		add(lblQueueLength);
		
		QueLengthLabel = new JLabel("5");
		QueLengthLabel.setFont(new Font("Arial", Font.BOLD, 25));
		QueLengthLabel.setBounds(300, 50, 100, 50);
		add(QueLengthLabel);
		
		banListTextArea = new JTextArea();
		banListTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
		banListTextArea.setBounds(45, 402, 700, 80);
		add(banListTextArea);
		
		JLabel lblBanRecord = new JLabel("Ban Record");
		lblBanRecord.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBanRecord.setBounds(45, 366, 125, 21);
		add(lblBanRecord);
		
		JLabel lblQueueContext = new JLabel("Queue Content");
		lblQueueContext.setFont(new Font("Arial", Font.PLAIN, 20));
		lblQueueContext.setBounds(45, 99, 154, 21);
		add(lblQueueContext);
		
	}
	public QueDisplayPanel(ModelDataManager m) {
		this();
		this.modelDataManager = m;		
	}
	public boolean viewUpdate(String[][] data) {
		if(data == null)
		{
			QueLengthLabel.setText("0");
		}else {
			QueLengthLabel.setText(""+data.length);
		}
		TableModel queTableModel = new DefaultTableModel(data,columnNames);		
		table.setModel(queTableModel);
		table.setEnabled(false);
		
		String banListStr =modelDataManager.getBanList();
		if (banListStr!=null)
			banListTextArea.setText(banListStr);
		else 
			banListTextArea.setText("");
		
		return true;
	}
}
