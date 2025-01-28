package mainPackage;

import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfaceDesign {

    private JFrame frmInventaryManagementSystem;
    private JTextField txtPname;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JButton btnAdditem;
    private JTextField textSearchid;
    private JTextField txtSellid;
    private JTextField txtSellQuantity;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfaceDesign window = new InterfaceDesign();
                    window.frmInventaryManagementSystem.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public InterfaceDesign() {
        initialize();
        buildConnection();
        loadTable();
    }
    
    
    Connection con;
    PreparedStatement prestm;
    ResultSet rst;
     public void buildConnection() {
    	 
    	 try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb","root","student");
			System.out.println("Done with the stable connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
     }
     public void loadTable() {
    	 try {
			prestm=con.prepareStatement("select * from inventoryTable");
			
			rst=prestm.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 
     }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmInventaryManagementSystem = new JFrame();
        frmInventaryManagementSystem.getContentPane().setFont(new Font("Arial", Font.BOLD, 21));
        frmInventaryManagementSystem.setTitle("Inventary Management System");
        frmInventaryManagementSystem.setSize(1283, 751); // Set frame size as needed
        frmInventaryManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmInventaryManagementSystem.setLocationRelativeTo(null);
        frmInventaryManagementSystem.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Inventory Management System");
        lblNewLabel.setBounds(353, 22, 571, 56);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        frmInventaryManagementSystem.getContentPane().add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(24, 100, 374, 334);
        panel.setBorder(new TitledBorder(null, "Add Items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        frmInventaryManagementSystem.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Product Name");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(10, 52, 136, 29);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Quantity");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(10, 112, 117, 29);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Price per item");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(10, 166, 136, 29);
        panel.add(lblNewLabel_1_1_1);
        
        txtPname = new JTextField();
        txtPname.setBorder(new LineBorder(new Color(171, 173, 179)));
        txtPname.setBounds(172, 55, 166, 26);
        panel.add(txtPname);
        txtPname.setColumns(10);
        
        txtQuantity = new JTextField();
        txtQuantity.setColumns(10);
        txtQuantity.setBorder(new LineBorder(new Color(171, 173, 179)));
        txtQuantity.setBounds(172, 115, 166, 26);
        panel.add(txtQuantity);
        
        txtPrice = new JTextField();
        txtPrice.setColumns(10);
        txtPrice.setBorder(new LineBorder(new Color(171, 173, 179)));
        txtPrice.setBounds(172, 169, 166, 26);
        panel.add(txtPrice);
        
        btnAdditem = new JButton("Add item\r\n");
        btnAdditem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name, quantity, price;
        		
        		name=txtPname.getText();
        		quantity=txtQuantity.getText();
        		price=txtPrice.getText(); 
        		
        		try {
        			prestm = con.prepareStatement("insert into InventaryTable(ProductName, Quantity, PricePerItem) values (?, ?, ?)");
					prestm.setString(1, name);
					prestm.setString(2, quantity);
					prestm.setString(3, price);
					
					prestm.executeUpdate();
					JOptionPane.showMessageDialog(null,"Item Added !!");
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
				
					txtPname.requestFocus();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	
        		
        		
        	}
        });
        btnAdditem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAdditem.setBounds(10, 236, 158, 29);
        panel.add(btnAdditem);
        
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		txtPname.setText("");
				txtQuantity.setText("");
				txtPrice.setText("");
			
				txtPname.requestFocus();
        	}
        });
        btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClear.setBounds(198, 236, 127, 29);
        panel.add(btnClear);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(24, 445, 374, 131);
        panel_1.setBorder(new TitledBorder(null, "Search item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        frmInventaryManagementSystem.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Enter Product ID\r\n");
        lblNewLabel_1_1_1_1.setBounds(10, 38, 153, 22);
        lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
        panel_1.add(lblNewLabel_1_1_1_1);
        
        textSearchid = new JTextField();
        textSearchid.setColumns(10);
        textSearchid.setBorder(new LineBorder(new Color(171, 173, 179)));
        textSearchid.setBounds(161, 34, 146, 26);
        panel_1.add(textSearchid);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	String searchId = textSearchid.getText();
        	
try {
	prestm=con.prepareStatement("select * from InventaryTable where ID=?");
	
	prestm.setString(1, searchId);
	
	rst=prestm.executeQuery();
	if(rst.next()) {
		
		txtPname.setText(rst.getString(2));
		txtQuantity.setText(rst.getString(3));
		txtPrice.setText(rst.getString(4));
	
		txtPname.requestFocus();
		
	}else {
		txtPname.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");
	
		txtPname.requestFocus();
		
	}
	
} catch (SQLException e1) {
	e1.printStackTrace();
}
			
    
        	}
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSearch.setBounds(82, 79, 136, 29);
        panel_1.add(btnSearch);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(24, 587, 374, 95);
        panel_2.setBorder(new TitledBorder(null, "Modifiy record", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        frmInventaryManagementSystem.getContentPane().add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        String name, quantity, price,id;
        		
        		name=txtPname.getText();
        		quantity=txtQuantity.getText();
        		price=txtPrice.getText(); 
        		
        		id=textSearchid.getText();
        		
        		
        		try {
					prestm=con.prepareStatement("update InventaryTable set ProductName=?,Quantity=?,PricePerItem=? where ID=?");
					prestm.setString(1, name);
					prestm.setString(2, quantity);
					prestm.setString(3, price);
					prestm.setString(4, id);
					
					
					prestm.executeUpdate();
					JOptionPane.showMessageDialog(null,"Item Updated !!");
					loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
				
					txtPname.requestFocus();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        		
        	}
        });
        btnUpdate.setBounds(20, 31, 144, 41);
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel_2.add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id= textSearchid.getText();
        		try {
					prestm=con.prepareStatement("delete from InventaryTable where ID=?");
					prestm.setString(1, id);
					prestm.executeUpdate();
					JOptionPane.showMessageDialog(null,"Item Deleted !!");

                    loadTable();
					
					txtPname.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
        	}
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnDelete.setBounds(174, 31, 141, 41);
        panel_2.add(btnDelete);
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnExit.setBounds(1009, 50, 180, 49);
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frmInventaryManagementSystem.getContentPane().add(btnExit);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(491, 100, 646, 364);
        frmInventaryManagementSystem.getContentPane().add(panel_3);
        panel_3.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 21, 626, 332);
        panel_3.add(scrollPane);
        
        table = new JTable();
        table.setGridColor(new Color(0, 0, 0));
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setViewportView(table);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "Sell items", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBounds(491, 473, 646, 209);
        frmInventaryManagementSystem.getContentPane().add(panel_4);
        panel_4.setLayout(null);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Enter Product ID\r\n");
        lblNewLabel_1_1_1_1_1.setBounds(107, 50, 135, 22);
        lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
        panel_4.add(lblNewLabel_1_1_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Quantity");
        lblNewLabel_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel_1_1_1_1_1_1.setBounds(107, 97, 135, 22);
        panel_4.add(lblNewLabel_1_1_1_1_1_1);
        
        txtSellid = new JTextField();
        txtSellid.setColumns(10);
        txtSellid.setBorder(new LineBorder(new Color(171, 173, 179)));
        txtSellid.setBounds(280, 46, 146, 26);
        panel_4.add(txtSellid);
        
        txtSellQuantity = new JTextField();
        txtSellQuantity.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		
        		String sellid = txtSellid.getText();	
            	String sellQuantity =	txtSellQuantity.getText();
            	
            	try {
            		prestm=con.prepareStatement("select * from InventaryTable where ID=?");
            		
            		prestm.setString(1, sellid);
            		
            		rst=prestm.executeQuery();
            		
            		if(rst.next()) {
            			if(Integer.parseInt(sellQuantity)<=Integer.parseInt(rst.getString(3))) {
                			
                		
            			txtPname.setText(rst.getString(2));
            			txtQuantity.setText(rst.getString(3));
            			txtPrice.setText(rst.getString(4));
            		
            			
            		}else {
            			txtPname.setText("");
            			txtQuantity.setText("");
            			txtPrice.setText("");
            		
            			txtPname.requestFocus();
            		}	
            		}
            		else {
            			txtPname.setText("");
            			txtQuantity.setText("");
            			txtPrice.setText("");
            		
            			txtPname.requestFocus();
            			
            		}
            		
            	} catch (SQLException e1) {
            		e1.printStackTrace();
            	}
        	}
        });
        txtSellQuantity.setColumns(10);
        txtSellQuantity.setBorder(new LineBorder(new Color(171, 173, 179)));
        txtSellQuantity.setBounds(280, 93, 146, 26);
        panel_4.add(txtSellQuantity);
        
        JButton btnSellItem = new JButton("Sell Item");
        btnSellItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name,quantity,price,id;
        		
        		name=txtPname.getText();
        		quantity=txtQuantity.getText();
        		price=txtPrice.getText();
        		id=txtSellid.getText();
        		
        		String Sellid=txtSellid.getText();
        		String SellQuantity=txtSellQuantity.getText();
        		
        		
        		Integer diffQuantity = Integer.parseInt(quantity) - Integer.parseInt(SellQuantity);
        		
        		
        		
        		try {
        			prestm = con.prepareStatement("update InventoryTable set Quantity=? where ID=?");
        			
        			prestm.setString(1, diffQuantity.toString());
        			prestm.setString(2, id);
        			
        			prestm.executeUpdate();
        			
        			JOptionPane.showMessageDialog(null,"Items Sold!!");
        			
        			loadTable();
        			
        			txtPname.setText("");
        			txtQuantity.setText("");
        			txtPrice.setText("");
        			
        			txtPname.requestFocus();
        			
        					
        		
        		} catch (SQLException e1) {
        			e1.printStackTrace();
        		}
        	}
        });
        btnSellItem.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnSellItem.setBounds(132, 142, 123, 40);
        panel_4.add(btnSellItem);
    }
}
