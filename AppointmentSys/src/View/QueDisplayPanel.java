package View;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Model.ModelDataManager;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

/**
 * @author wyj19
 * Display the queue status and the ban list content
 */
public class QueDisplayPanel extends JPanel {
	private JTable table;
	private String[] columnNames = {"ID","   Name   ","   E-mail   ","   Time   ","   Question   "};
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
		QueLengthLabel.setBounds(300, 50, 305, 50);
		add(QueLengthLabel);
		
		banListTextArea = new JTextArea();
		banListTextArea.setEditable(false);
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
			QueLengthLabel.setText("0(Empty Queue)");
		}else {
			QueLengthLabel.setText(""+data.length);
		}
		TableModel queTableModel = new DefaultTableModel(data,columnNames);		
		table.setModel(queTableModel);
		table.setEnabled(false);
		FitTableColumns(table);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
		String banListStr =modelDataManager.getBanList();
		if (banListStr!=null)
			banListTextArea.setText(banListStr);
		else 
			banListTextArea.setText("");
		
		return true;
	}
	//cite from https://blog.csdn.net/tototuzuoquan/article/details/8982618
	public void FitTableColumns(JTable myTable) {             
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        Enumeration columns = myTable.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(
                    column.getIdentifier());

            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable,
                            column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++){
                int preferedWidth = (int) myTable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(myTable,
                                myTable.getValueAt(row, col), false, false,
                                row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width);

        }

    }
}
