import java.awt.GridLayout;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;


public class BookListSwing extends JFrame implements ActionListener {
   JButton btnOk, btnReset,btnInsert1, btnSel1, btnSel2, btnReset2 ;
   JTextArea txtResult;
   JPanel pn1, pn2;
   JTextField txtOid, txtCid, txtBid,txtSale, txtDate;
   JButton btngo;
   PreparedStatement pstmt;
   
   static Connection con;
   Statement stmt;
   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";

   public BookListSwing() {
      super("18011564 ����ȭ");
      layInit();
      conDB();
      setVisible(true);
      setBounds(200, 200, 1000, 400); //������ġ,������ġ,���α���,���α���
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void layInit() {
      btnOk = new JButton("�˻� 1 : select * from book");
      btnSel1 = new JButton("�˻� 2 : select * from orders");
      btnSel2 = new JButton("�˻� 3 : select * from customer");
      btnReset = new JButton("ȭ���ʱ�ȭ");
      btnInsert1 = new JButton("�Է� 1 : orders �߰�"); //
      btnReset2 = new JButton("DB �ʱ�ȭ");
      
      
      txtResult = new JTextArea();
      pn1 = new JPanel();
      
      pn1.add(btnOk);
      pn1.add(btnSel1);
      pn1.add(btnSel2);
      pn1.add(btnReset);
      pn1.add(btnReset2);
      pn1.add(btnInsert1);//
      
      txtResult.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(txtResult);
      add("North", pn1);
      add("Center", scrollPane);
      
      
      btnOk.addActionListener(this);
      btnReset.addActionListener(this);
      btnSel1.addActionListener(this);
      btnSel2.addActionListener(this);
      
      //inserâ �����..?
      btnInsert1.addActionListener(new ActionListener() {
    	  
    	  public void actionPerformed(ActionEvent e) {
              new newWindow(); // Ŭ���� newWindow�� ���� ������
          }
      });
      
      //inserâ �����..?
      
      btnReset2.addActionListener(new ActionListenerInit());
   }

   
   /* 5�� 2�� insert..*/
   
   public void insertMyDB() {
	  
	   try {
		   stmt = con.createStatement();
		   
		   //book
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (1,'�౸�� ����','�½�����', 7000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (2, '�౸�ƴ� ����', '������', 13000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (3, '�౸�� ����', '���ѹ̵��', 22000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (4, '���� ���̺�', '���ѹ̵��', 35000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (5, '�ǰ� ����', '�½�����', 8000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (6, '���� �ܰ躰���', '�½�����', 6000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (7, '�߱��� �߾�', '�̻�̵��', 20000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (8, '�߱��� ��Ź��', '�̻�̵��', 13000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (9, '�ø��� �̾߱�', '�Ｚ��', 7500);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (10, 'Olympic Champions', 'Pearson', 13000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (11, '������ ��Ź��', '�ŵ��ϺϽ�', 93000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (12, '�ü��', '���¼��Ͻ�', 26000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (13, '���������Ƿ�', '�����ۺϽ�', 15000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (14, '�ѱ� �ѱ���', '������ �Ͻ�', 21000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (15, 'â���� ����', '���θ��׿콺', 20000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (16, '����Ҽ��� ������� �͵�', '��������', 18000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (17, '����� ����', '��н�����', 11700);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (18, '������ ��', '����', 11000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (19, '�̱��� ������', '������ȭ��', 7000);");
		   stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (20, '���� �߿�����', '��Ʈ', 9000);");
	   
		   //customer
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (1,'������','���� ��ü��Ÿ', '000-5000-0001');");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (2,'�迬��','���ѹα� ����', '000-6000-0001');");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (3, '��̶�', '���ѹα� ������', '000-7000-0001');");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (4, '�߽ż�', '�̱� Ŭ������', '000-8000-0001');");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (5, '�ڼ���', '���ѹα� ����',  NULL);");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (6, '����ȭ', '���ѹα� õ��',  '000-1234-1212');");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (7, '�ڹο�', '���ѹα� ����',  NULL);");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (8, '¯��', '�Ϻ�',  NULL);");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (9, '������', '���ѹα� ����',  '000-5480-5482');");
		   stmt.executeUpdate("insert into customer (custid, name, address, phone) values (10, '�̹�ȣ', '���ѹα� ����',  '000-0005-1000');");

		   //orders
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (1, 1, 1, 6000, STR_TO_DATE('2014-07-01','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (2, 1, 3, 21000, STR_TO_DATE('2014-07-03','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (3, 2, 5, 8000, STR_TO_DATE('2014-07-03','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (4, 3, 6, 6000, STR_TO_DATE('2014-07-04','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (5, 4, 7, 20000, STR_TO_DATE('2014-07-05','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (6, 1, 2, 12000, STR_TO_DATE('2014-07-07','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (7, 4, 8, 13000, STR_TO_DATE( '2014-07-07','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (8, 3, 10, 12000, STR_TO_DATE('2014-07-08','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (9, 2, 10, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (10, 3, 8, 13000, STR_TO_DATE('2014-07-10','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (11, 6, 11, 93000, STR_TO_DATE('2014-07-11','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (12, 6, 12, 26000, STR_TO_DATE('2014-07-12','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (13, 8, 19, 7000, STR_TO_DATE('2014-07-12','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (14, 7, 16, 18000, STR_TO_DATE('2014-07-14','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (15, 9, 20, 9000, STR_TO_DATE('2014-07-15','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (16, 10, 18, 11000, STR_TO_DATE('2014-07-16','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (17, 7, 12, 26000, STR_TO_DATE('2014-07-17','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (18, 8, 12, 26000, STR_TO_DATE('2014-07-19','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (19, 1, 11, 93000, STR_TO_DATE('2014-07-20','%Y-%m-%d'));");
		   stmt.executeUpdate("insert into orders (orderid, custid, bookid, saleprice, orderdate) values (20, 9, 14, 21000, STR_TO_DATE('2014-07-25','%Y-%m-%d'));");

	   
	  
	   }catch (Exception e4) {
		   System.out.println("�ʱ� ���̺� �߰� ����");
	   }
	   
   }
   
   /* insert�� �����ٶ�*/
   
   public void conDB() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("����̹� �ε� ����");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try { /* �����ͺ��̽��� �����ϴ� ���� */
          System.out.println("�����ͺ��̽� ���� �غ�...");
          con = DriverManager.getConnection(url, userid, pwd);
          System.out.println("�����ͺ��̽� ���� ����");
       } catch (SQLException e1) {
          e1.printStackTrace();
       }
   }
   
   /*���⼭���� 5�� 2�� delete*/
   
   private class ActionListenerInit implements ActionListener {
	   public void actionPerformed (ActionEvent e) {
		   int rowcount;
		   
		   System.out.println("DB �ʱ�ȭ����");
		   
		   try {
			   stmt = con.createStatement();
			   
			   String query = "delete FROM orders";
			   rowcount = stmt.executeUpdate(query);
			   System.out.println("orders ���̺��� " + rowcount + "�� Ʃ�� ����");
			   
			   query = "delete FROM book";
			   rowcount = stmt.executeUpdate(query);
			   System.out.println("book ���̺��� " + rowcount + "�� Ʃ�� ����");
			   
			   query = "delete FROM customer";
			   rowcount = stmt.executeUpdate(query);
			   System.out.println("customer ���̺��� " + rowcount + "�� Ʃ�� ����");
			   
			   insertMyDB();//
			   
		   } catch (Exception e4) {
			   System.out.println("DB �ʱ�ȭ ����" + e4);
		   }
	   }
	   
   }
   
   
   
   /*������� 5�� 2��*/
   
   

   @Override
   public void actionPerformed(ActionEvent e) {
    
      try {
         stmt = con.createStatement();
         String query = "SELECT * FROM Book ";
         if (e.getSource() == btnOk) {
            txtResult.setText("");
            txtResult.setText("BOOKNO           BOOK NAME       PUBLISHER      PRICE\n");
            rs = stmt.executeQuery(query);
            while (rs.next()) {
               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4)
                     + "\n";
               txtResult.append(str);
            }
         } else if (e.getSource() == btnReset) {
            txtResult.setText("");
         } else if (e.getSource() == btnSel1)  {
             query = "SELECT * FROM orders ";
             
        	 txtResult.setText("");
             txtResult.setText("ORDERID           CUSTID	BOOKID	SALEPRICE	ORDERDATE\n");
             rs = stmt.executeQuery(query);
             while (rs.next()) {
                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\t" + rs.getDate(5)
                      + "\n";
                txtResult.append(str);
             }

         } else if (e.getSource() == btnSel2)  {
             query = "SELECT * FROM customer ";
             
        	 txtResult.setText("");
             txtResult.setText("CUSTID	NAME	ADDRESS	PHONE\n");
             rs = stmt.executeQuery(query);
             while (rs.next()) {
                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                      + "\n";
                txtResult.append(str);
             }

         } 
         
      } catch (Exception e2) {
         System.out.println("���� �б� ���� :" + e2);

      }

   }
   

class newWindow extends JFrame {
    // ��ư�� �������� ��������� �� â�� ������ Ŭ����
    newWindow() {
        setTitle("insertâ");
        
        JPanel jp1 = new JPanel();
        setContentPane(jp1);
        
        
        GridLayout grid = new GridLayout(6,2);
       
        
        btngo = new JButton("�Է� �Ϸ�");
        txtOid = new JTextField(20);
        txtCid = new JTextField(20);
   	 	txtBid = new JTextField(20);
   	 	txtSale = new JTextField(20);
   	 	txtDate = new JTextField(20);
   	 	
   	 	jp1.setLayout(grid);
   	 	
   	 	jp1.add(new JLabel("  orderid : "));
   	 	jp1.add(txtOid);
   	 	
   	 	
   	 	jp1.add(new JLabel("  custid : "));
   	 	jp1.add(txtCid);
   	 	
   	 	jp1.add(new JLabel("  bookid : "));
   	 	jp1.add(txtBid);
   	 	
   	 	jp1.add(new JLabel("  saleprice : "));
   	 	jp1.add(txtSale);
   	 	
   	 	jp1.add(new JLabel("  orderdate : "));
   	 	jp1.add(txtDate);
        
   	 	jp1.add(new JLabel(""));
   	 	jp1.add(btngo);
   	 	
       
        
        setBounds(450,400,400,400);
        setResizable(false);
        setVisible(true);
       
        btngo.addActionListener(new ActionListenerGo());//
       
        
    }
    
    
    
    public class ActionListenerGo implements ActionListener {
    	
  	   public void actionPerformed (ActionEvent e) {
  		   try  {
  			   
  			   String orid = txtOid.getText();
  			   String cuid = txtCid.getText();
  			   String boid = txtBid.getText();
  			   String sapr = txtSale.getText();
  			   String orda = txtDate.getText();
  			   
  			   String sql1 ="insert into orders (orderid, custid, bookid, saleprice, orderdate) values (?,?,?,?,?);";
  			   
  			   
  			   pstmt = con.prepareStatement(sql1);
  			   pstmt.setString(1, orid);
  			   pstmt.setString(2, cuid);
  			   pstmt.setString(3, boid);
  			   pstmt.setString(4, sapr);
  			   pstmt.setString(5, orda);
  			   pstmt.executeUpdate();
  			   
  			   System.out.println("order�߰� ����");
  			   
  		   } catch (Exception e4) {
  			 System.out.println("���� ����:");
  			 System.out.println(e4.getMessage());
  		   }
  	   }
  		   
  		   
  	   
    }
    
    
    
}


   public static void main(String[] args) {
      BookListSwing BLS = new BookListSwing();
      
      //BLS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      //BLS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      BLS.addWindowListener(new WindowAdapter() {
    	  public void windowClosing(WindowEvent we) {
    		try {
    			con.close();
    		} catch (Exception e4) { 	}
    		System.out.println("���α׷� ���� ����!");    		
    	    System.exit(0);
    	  }
    	});
   }
}



