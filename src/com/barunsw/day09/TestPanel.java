package com.barunsw.day09;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.day09.controller.AddressBookInterface;
import com.barunsw.day09.controller.FileAddressBookImpl;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	// 테이블 셀 인덱스 정의
	private int idx = 0;
	private final int TABLE_CELL_INDEX_ID = idx++;
	private final int TABLE_CELL_INDEX_NAME = idx++;
	private final int TABLE_CELL_INDEX_AGE = idx++;

	private JButton jButton_Reload = new JButton("재조회");
	
	private JScrollPane jScrollPane_List = new JScrollPane();
	private JTable jTable_List = new JTable();
	
	private JScrollPane jScrollPane_Word = new JScrollPane();
	private JTree jTree_Word = new JTree();
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("ROOT");
	
	private DefaultTreeModel treeModel;
	private CommonTableModel tableModel;
	
	private AddressBookInterface addressBookInterface;
	
	public TestPanel() {
		try {
			addressBookInterface = new FileAddressBookImpl();
			
			initComponent();
			initTable();
			initTree();
			
			initData(null);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {		
		this.setLayout(new GridBagLayout());

		this.add(jButton_Reload, new GridBagConstraints(0, 0, 2, 1,
				1.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jScrollPane_Word.setMinimumSize(new Dimension(200, 0));
		jScrollPane_Word.setPreferredSize(new Dimension(200, 0));
		
		jScrollPane_Word.getViewport().add(jTree_Word);
		jScrollPane_List.getViewport().add(jTable_List);
				
		this.add(jScrollPane_Word, new GridBagConstraints(0, 1, 1, 1,
				0.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));

		this.add(jScrollPane_List, new GridBagConstraints(1, 1, 1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		jButton_Reload.addActionListener(new TestPanel_jButton_Reload_ActionListener(this));
	}
	
	private void initTable() {
		Vector<String> columnData = new Vector<>();
		columnData.add("이름");
		columnData.add("나이");
		
		tableModel = new CommonTableModel(columnData);
		
		jTable_List.setModel(tableModel);
		
		// row의 높이 지정
		jTable_List.setRowHeight(22);

		// 가운데 정렬 기능을 포함한 DefaultTableCellRenderer
		DefaultTableCellRenderer centerTableCellRenderer = new DefaultTableCellRenderer();
		centerTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		// 컬럼별 width 지정
		int columnCount = tableModel.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			TableColumn tableColumn = jTable_List.getColumnModel().getColumn(i);

			JTableHeader header = jTable_List.getTableHeader();
			// 헤더 정렬
			((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			
			// 데이터 Cell 관련 설정
			if (i == TABLE_CELL_INDEX_ID) {
				tableColumn.setPreferredWidth(50);
				tableColumn.setCellRenderer(centerTableCellRenderer);
			}
			else if (i == TABLE_CELL_INDEX_NAME) {
				tableColumn.setPreferredWidth(50);
				tableColumn.setCellRenderer(centerTableCellRenderer);
			}
			else if (i == TABLE_CELL_INDEX_AGE) {
				tableColumn.setPreferredWidth(200);
				tableColumn.setCellRenderer(centerTableCellRenderer);
			}
		}
	}
	
	private void initTree() {
		treeModel = new DefaultTreeModel(rootNode);
		jTree_Word.setModel(treeModel);
		
		// root 노드는 안 보여준다.
		jTree_Word.setRootVisible(false);

		// tree 선택 이벤트
		
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("ㄱ");
		
		rootNode.add(node1);
		
		treeModel.reload();
	}
	
	private void initData(String filterWord) {
		Vector dataInfo = new Vector();
		
		Vector oneData = new Vector();
		oneData.add("홍길동");
		oneData.add("30");
		
		dataInfo.add(oneData);
		
		tableModel.setData(dataInfo);
		
		// 테이블 내용이 변경되었음을 알린다.
		tableModel.fireTableDataChanged();
	}
	
	void jButton_Test_actionPerformed(ActionEvent e) {

	}
}

class TestPanel_jButton_Reload_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Reload_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Test_actionPerformed(e);
	}
}