import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;


public class HospitalAdministration extends JFrame {

	JButton btnReset, btnSel1, btnSel2, btnSel3;
	JTextArea txtResult;
	
	JButton btnInsert1; //�ǻ� �Է�
	JButton btnInsertNur; //��ȣ�� �Է�
	JButton btnInsertPat; //ȯ�� �Է�
	JButton btnInsertTre; //���� �Է�
	JButton btnInsertCha; //��Ʈ �Է� 
	
	JButton btnSubM; //�ǻ��Է������ư
	JButton btnSubNur; //��ȣ�� �Է� �����ư
	JButton btnSubPat; //ȯ�� �Է� �����ư
	JButton btnSubTre; //���� �Է� ���� ��ư
	JButton btnSubCha; //��Ʈ �Է� ���� ��ư 
	
	JButton btnSelDoc; //�ǻ� ���
	JButton btnSelNur; //��ȣ�� ���
	JButton btnSelPat; //ȯ�� ���
	JButton btnSelTre; //���� ���
	JButton btnSelCha; //��Ʈ ���
	
	PreparedStatement pstmt;
	
	static Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
		
		  
	public HospitalAdministration() //������
	{
		super("18011564/����ȭ");
		conDB();
		setInit();
		setVisible(true);
		setBounds(200, 200, 1500, 700); //������ġ,������ġ,���α���,���α���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

		  
	public void setInit() { //gui
		
		GridLayout g1 = new GridLayout(1, 1);
		GridLayout g2 = new GridLayout(4, 5, 10, 10);
		setLayout(g1);
		
		JPanel pnButton;
		txtResult = new JTextArea();
		JScrollPane pnResult = new JScrollPane(txtResult);
		
		pnButton = new JPanel();
		
		pnButton.setLayout(g2);
		
		btnReset = new JButton("DB�ʱ�ȭ");
		
		btnInsert1 = new JButton("�ű� �ǻ� �Է�");
		btnInsertNur = new JButton("�ű� ��ȣ�� �Է�"); 
		btnInsertPat = new JButton("�ű� ȯ�� �Է�"); 
		btnInsertTre = new JButton("���� �Է�"); 
		btnInsertCha = new JButton("��Ʈ�Է�");
		
		btnSelDoc = new JButton("�ǻ� �˻�"); //�ǻ� ���
		btnSelNur = new JButton("��ȣ�� �˻�"); //��ȣ�� ���
		btnSelPat = new JButton("ȯ�� �˻�"); //ȯ�� ���
		btnSelTre = new JButton("���� �˻�"); //���� ���
		btnSelCha = new JButton("��Ʈ �˻�"); //��Ʈ ���
		
		btnSel2 = new JButton("�˻�2");
		btnSel3 = new JButton("�˻�3");
		
		pnButton.add(btnReset);
		pnButton.add(new JLabel("")); //1
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		
		//2 �Է�
		pnButton.add(btnInsert1);
		pnButton.add(btnInsertNur);
		pnButton.add(btnInsertPat);
		pnButton.add(btnInsertTre);
		pnButton.add(btnInsertCha);
		
		//3 �˻�1
		pnButton.add(btnSelDoc);
		pnButton.add(btnSelNur);
		pnButton.add(btnSelPat);
		pnButton.add(btnSelTre);
		pnButton.add(btnSelCha);
		
		//4 �˻� -2,3
		
		pnButton.add(btnSel2);
		pnButton.add(btnSel3);
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		
		btnReset.addActionListener(new ActionListenerDBInit()); //DB�ʱ�ȭ ��ư ������?
		
	
		
		//�Է¹�ư 1,2,3,4,5 
		
		btnInsert1.addActionListener(new ActionListener() {	//�ǻ� �Է� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsert();
			}
		});
		
		btnInsertNur.addActionListener(new ActionListener() {	//��ȣ�� �Է� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertNur();
			}
		});
		
		btnInsertPat.addActionListener(new ActionListener() {	//ȯ�� �Է� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertPat();
			}
		});
		
		
		btnInsertTre.addActionListener(new ActionListener() {	//���� �Է� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertTre();
			}
		});
		
		
		btnInsertCha.addActionListener(new ActionListener() {	//��Ʈ �Է� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertCha();
			}
		});
		
		
		//�˻���ư 1,2,3,4,5
		
		btnSelDoc.addActionListener(new ActionListener() {	//�ǻ� �˻� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("�ǻ�ID" + "\t" + "����������" + "\t" + "����" + "\t" + "����" + "\t" + "��ȭ��ȣ" + "\t"+ "�̸���" + "\t" +"����" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Doctors;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
								+ " \t " + rs.getString(5) + "\t" + rs.getString(6) + "\t" +rs.getString(7) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
		
		
		btnSelNur.addActionListener(new ActionListener() {	//��ȣ�� �˻� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("��ȣ��ID" + "\t" + "������" + "\t" + "����" + "\t" + "����" + "\t" + "��ȭ��ȣ" + "\t"+ "�̸���" + "\t" +"����" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Nurses;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
								+ " \t " + rs.getString(5) + "\t" + rs.getString(6) + "\t" +rs.getString(7) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
	
		
		btnSelPat.addActionListener(new ActionListener() {	//ȯ�� �˻� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("ȯ��ID" + "\t" + "ȯ�ڼ���" + "\t" + "ȯ�ڼ���" + "\t" + "�ֹι�ȣ" + "\t" + "�ּ�" + "\t"+ "��ȭ��ȣ" + "\t" +"�̸���" + "\t" +"����"+ "\t" +"�ǻ�ID"+ "\t" +"��ȣ��ID"+ "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Patients;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
								+ " \t " + rs.getString(5) + "\t" + rs.getString(6) + "\t" +rs.getString(7) + "\t" +rs.getString(8)+ "\t" +rs.getInt(9)+ "\t" +rs.getInt(10)+ "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
		
		btnSelTre.addActionListener(new ActionListener() {	//���� �˻� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("����ID" + "\t" + "���᳻��" + "\t" + "���ᳯ¥" + "\t" + "ȯ��ID" + "\t" + "�ǻ�ID" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Treatments;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDate(3) + "\t" + rs.getInt(4)
								+ " \t " + rs.getInt(5) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
		
		
		btnSelCha.addActionListener(new ActionListener() {	//��Ʈ �˻� ��ư ������?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("��ƮID" + "\t" + "��Ʈ����" + "\t" + "����ID"+ "\t" + "ȯ��ID" + "\t" + "�ǻ�ID" + "\t" + "��ȣ��ID" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Charts;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
								+ " \t " + rs.getInt(5) + "\t" + rs.getInt(6) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
		
		
		btnSel2.addActionListener(new ActionListener() {	//�˻�2��ư - <���� ������ �ִ� �ǻ��� �̸��� ��� ���� ����>
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					
					txtResult.setText("\t"+"<���� ������ �ִ� �ǻ��� �̸��� ��� ���� ������ ���>" + "\n"+ "\n");
					txtResult.append("�ǻ��̸�" + "\t" + "����������" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select doc_name '�ǻ��̸�', major_treat '����������'\r\n" + 
							"from Doctors\r\n" + 
							"where doc_id IN (select Doctors_doc_id\r\n" + 
							"				from Treatments\r\n" + 
							"                group by Doctors_doc_id);";
					rs = stmt.executeQuery(sql2);
					
					
					while (rs.next()) {
						String res = rs.getString(1) + "\t" + rs.getString(2) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
		
		btnSel3.addActionListener(new ActionListener() {	//�˻�3��ư - <���� �ǻ�� ���� �����غ� ��ȣ�翡�� �Ҵ�� ȯ���� �̸��� ���� ��� >
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					
					txtResult.setText("\t"+"<���� �ǻ�� ���� �����غ� ��ȣ�翡�� �Ҵ�� ȯ���� �̸��� ���� ��� >" + "\n"+ "\n");
					txtResult.append("ȯ���̸�" + "\t" + "ȯ�ڼ���" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select pat_name 'ȯ�� �̸�' ,pat_gen 'ȯ�� ����'\r\n" + 
							"from Patients\r\n" + 
							"where Nurses_nur_id IN (select Nurses_nur_id from Charts where Treatments_Doctors_doc_id IN (select doc_id from Doctors where doc_gen='F'))\r\n" + 
							"group by pat_name;";
					rs = stmt.executeQuery(sql2);
					
					
					while (rs.next()) {
						String res = rs.getString(1) + "\t" + rs.getString(2) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("�˻��������� : " + e1);
				}
			}
		});
		
		
		txtResult.setText("<���â>");

		add(pnButton);
		add(pnResult);		
		
		
	}
		  
	
	private class ActionListenerDBInit implements ActionListener { // DB�ʱ�ȭ ��ư ������ �� 
		public void actionPerformed(ActionEvent e) {
			try {
				
				System.out.println("DB�ʱ�ȭ ����...");
				
				System.out.println("�̹� �����ϴ� table�̸� drop�մϴ�..");
				
				stmt = con.createStatement();
				String query;
				
				//�̹� �����ϴ� table�̸� drop
				
				query = "DROP TABLE IF EXISTS Charts";
				stmt.executeUpdate(query);
				System.out.println("Charts ���̺� DROP �Ϸ�");

				query = "DROP TABLE IF EXISTS Treatments";
				stmt.executeUpdate(query);
				System.out.println("Treatments ���̺� DROP �Ϸ�");

				query = "DROP TABLE IF EXISTS Patients";
				stmt.executeUpdate(query);
				System.out.println("Patients ���̺� DROP �Ϸ�");

				query = "DROP TABLE IF EXISTS Doctors";
				stmt.executeUpdate(query);
				System.out.println("Doctors ���̺� DROP �Ϸ�");

				query = "DROP TABLE IF EXISTS Nurses";
				stmt.executeUpdate(query);
				System.out.println("Nurses ���̺� DROP �Ϸ�");

				System.out.println("DROP ����!");
				
				//table create
				
				//Doctors
				System.out.println("table CREATE ����...");
				
				query = "CREATE TABLE IF NOT EXISTS `Doctors` (\r\n" + 
						"  `doc_id` INT NOT NULL,\r\n" + 
						"  `major_treat` VARCHAR(25) NOT NULL,\r\n" + 
						"  `doc_name` VARCHAR(20) NOT NULL,\r\n" + 
						"  `doc_gen` CHAR(1) NOT NULL,\r\n" + 
						"  `doc_phone` VARCHAR(15) NULL,\r\n" + 
						"  `doc_email` VARCHAR(50) NULL,\r\n" + 
						"  `doc_position` VARCHAR(20) NOT NULL,\r\n" + 
						"  PRIMARY KEY (`doc_id`),\r\n" + 
						"  UNIQUE INDEX `doc_email_UNIQUE` (`doc_email` ASC) VISIBLE,\r\n" + 
						"  UNIQUE INDEX `doc_id_UNIQUE` (`doc_id` ASC) VISIBLE)\r\n" + 
						"ENGINE = InnoDB;\r\n";
				stmt.executeUpdate(query);
				System.out.println("Doctors table CREATE �Ϸ�");
				
				//Nurses
				query = "CREATE TABLE IF NOT EXISTS `Nurses` (\r\n" + 
						"  `nur_id` INT NOT NULL,\r\n" + 
						"  `major_job` VARCHAR(25) NOT NULL,\r\n" + 
						"  `nur_name` VARCHAR(20) NOT NULL,\r\n" + 
						"  `nur_gen` CHAR(1) NOT NULL,\r\n" + 
						"  `nur_phone` VARCHAR(15) NULL,\r\n" + 
						"  `nur_email` VARCHAR(50) NULL,\r\n" + 
						"  `nur_position` VARCHAR(20) NOT NULL,\r\n" + 
						"  PRIMARY KEY (`nur_id`),\r\n" + 
						"  UNIQUE INDEX `nur_id_UNIQUE` (`nur_id` ASC) VISIBLE,\r\n" + 
						"  UNIQUE INDEX `nur_email_UNIQUE` (`nur_email` ASC) VISIBLE)\r\n" + 
						"ENGINE = InnoDB;\r\n";
				stmt.executeUpdate(query);
				System.out.println("Nurses table CREATE �Ϸ�");
				
				//patients
				query = "CREATE TABLE IF NOT EXISTS `Patients` (\r\n" + 
						"  `pat_id` INT NOT NULL,\r\n" + 
						"  `pat_name` VARCHAR(20) NOT NULL,\r\n" + 
						"  `pat_gen` CHAR(1) NOT NULL,\r\n" + 
						"  `pat_jumin` VARCHAR(14) NOT NULL,\r\n" + 
						"  `pat_addr` VARCHAR(100) NOT NULL,\r\n" + 
						"  `pat_phone` VARCHAR(15) NULL,\r\n" + 
						"  `pat_email` VARCHAR(50) NULL,\r\n" + 
						"  `pat_job` VARCHAR(20) NOT NULL,\r\n" + 
						"  `Doctors_doc_id` INT NOT NULL,\r\n" + 
						"  `Nurses_nur_id` INT NOT NULL,\r\n" + 
						"  PRIMARY KEY (`pat_id`),\r\n" + 
						"  UNIQUE INDEX `pat_id_UNIQUE` (`pat_id` ASC) VISIBLE,\r\n" + 
						"  UNIQUE INDEX `pat_email_UNIQUE` (`pat_email` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Patients_Doctors1_idx` (`Doctors_doc_id` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Patients_Nurses1_idx` (`Nurses_nur_id` ASC) VISIBLE,\r\n" + 
						"  CONSTRAINT `fk_Patients_Doctors1`\r\n" + 
						"    FOREIGN KEY (`Doctors_doc_id`)\r\n" + 
						"    REFERENCES `Doctors` (`doc_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION,\r\n" + 
						"  CONSTRAINT `fk_Patients_Nurses1`\r\n" + 
						"    FOREIGN KEY (`Nurses_nur_id`)\r\n" + 
						"    REFERENCES `Nurses` (`nur_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION)\r\n" + 
						"ENGINE = InnoDB;\r\n";
				stmt.executeUpdate(query);
				System.out.println("Patients table CREATE �Ϸ�");
				
				//Treatments
				query = "CREATE TABLE IF NOT EXISTS `Treatments` (\r\n" + 
						"  `treat_id` INT NOT NULL,\r\n" + 
						"  `treat_contents` VARCHAR(1000) NOT NULL,\r\n" + 
						"  `treat_date` DATE NOT NULL,\r\n" + 
						"  `Patients_pat_id` INT NOT NULL,\r\n" + 
						"  `Doctors_doc_id` INT NOT NULL,\r\n" + 
						"  PRIMARY KEY (`treat_id`, `Patients_pat_id`, `Doctors_doc_id`),\r\n" + 
						"  INDEX `fk_Treatments_Patients1_idx` (`Patients_pat_id` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Treatments_Doctors1_idx` (`Doctors_doc_id` ASC) VISIBLE,\r\n" + 
						"  UNIQUE INDEX `treat_id_UNIQUE` (`treat_id` ASC) VISIBLE,\r\n" + 
						"  CONSTRAINT `fk_Treatments_Patients1`\r\n" + 
						"    FOREIGN KEY (`Patients_pat_id`)\r\n" + 
						"    REFERENCES `Patients` (`pat_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION,\r\n" + 
						"  CONSTRAINT `fk_Treatments_Doctors1`\r\n" + 
						"    FOREIGN KEY (`Doctors_doc_id`)\r\n" + 
						"    REFERENCES `Doctors` (`doc_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION)\r\n" + 
						"ENGINE = InnoDB;\r\n";
				stmt.executeUpdate(query);
				System.out.println("Treatments table CREATE �Ϸ�");
				
				//Charts
				query = "CREATE TABLE IF NOT EXISTS `Charts` (\r\n" + 
						"  `chart_id` VARCHAR(20) NOT NULL,\r\n" + 
						"  `chart_contents` VARCHAR(1000) NOT NULL,\r\n" + 
						"  `Treatments_treat_id` INT NOT NULL,\r\n" + 
						"  `Treatments_Patients_pat_id` INT NOT NULL,\r\n" + 
						"  `Treatments_Doctors_doc_id` INT NOT NULL,\r\n" + 
						"  `Nurses_nur_id` INT NOT NULL,\r\n" + 
						"  PRIMARY KEY (`chart_id`, `Treatments_treat_id`, `Treatments_Patients_pat_id`, `Treatments_Doctors_doc_id`),\r\n" + 
						"  INDEX `fk_Charts_Treatments1_idx` (`Treatments_treat_id` ASC, `Treatments_Patients_pat_id` ASC, `Treatments_Doctors_doc_id` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Charts_Nurses1_idx` (`Nurses_nur_id` ASC) VISIBLE,\r\n" + 
						"  UNIQUE INDEX `chart_id_UNIQUE` (`chart_id` ASC) VISIBLE,\r\n" + 
						"  CONSTRAINT `fk_Charts_Treatments1`\r\n" + 
						"    FOREIGN KEY (`Treatments_treat_id` , `Treatments_Patients_pat_id` , `Treatments_Doctors_doc_id`)\r\n" + 
						"    REFERENCES `Treatments` (`treat_id` , `Patients_pat_id` , `Doctors_doc_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION,\r\n" + 
						"  CONSTRAINT `fk_Charts_Nurses1`\r\n" + 
						"    FOREIGN KEY (`Nurses_nur_id`)\r\n" + 
						"    REFERENCES `Nurses` (`nur_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION)\r\n" + 
						"ENGINE = InnoDB;\r\n";
				stmt.executeUpdate(query);
				System.out.println("Charts table CREATE �Ϸ�");
				
				System.out.println("table CREATE ����!");
				
				
				//�� ���̺��� Ʃ�� insert
				System.out.println("�⺻ Ʃ�� INSERT �õ�...");
				int rowcount = 0;
				
				//insert - Doctors
				query = "INSERT INTO Doctors VALUES(980312,'�Ҿư�','������', 'M', '010-333-1340', 'itj@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(000601,'����','�ȼ���', 'M', '011-222-0987', 'ask@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(001208,'�ܰ�','�����', 'M', '010-333-8743', 'kmj@han.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(020403,'�Ǻΰ�','���¼�', 'M', '019-777-3764', 'its@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(050900,'�Ҿư�','�迬��', 'F', '010-555-3746', 'kya@habhn.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(050101,'����','������', 'M', '011-222-7643', 'cth@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(062019,'�Ҿư�','������', 'F', '010-999-1265', 'jjh@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(070576,'�Ǻΰ�','ȫ�浿', 'M', '016-333-7263', 'hgd@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(080543,'��缱��','���缮', 'M', '010-222-1263', 'yjs@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(091001,'�ܰ�','�躴��', 'M', '010-555-3542', 'kbm@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(111111,'�Ҿư�','������', 'F', '010-111-1111', 'iu@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(222222,'����','������', 'M', '010-525-5252', 'yjg@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(333333,'�ܰ�','��ȫö', 'M', '010-333-3333', '33@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(444444,'��缱��','�ϵ���', 'M', '010-444-4444', '44h@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(555555,'�Ǻΰ�','�����', 'M', '010-555-5555', '55@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(666666,'�Ҿư�','�漺��', 'M', '010-666-6666', '66@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(777777,'����','��ȿ��', 'F', '010-777-7777', '77@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(888888,'��缱��','������', 'M', '010-888-8888', '88@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(999999,'�����ܰ�','�ڳ���', 'F', '010-999-9999', '99@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(101010,'����','�����', 'M', '010-101-1010', '10@hanbh.com','������')";
				rowcount += stmt.executeUpdate(query);
				
				
				System.out.println("Doctors ���̺� " + rowcount + "���� Ʃ�� �߰� �Ϸ�");

				//insert - Nurses
				rowcount = 0;
				
				query = "INSERT INTO Nurses VALUES(050302,'�Ҿư�','������', 'F', '010-555-8751', 'key@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(050021,'����','������', 'F', '016-333-8745', 'ysa@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(040089,'�Ǻΰ�','������', 'M', '010-666-7646', 'sjw@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(070605,'��缱��','����ȭ', 'F', '010-333-4588', 'yjh@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(070804,'����','���ϳ�', 'F', '010-222-1340', 'nhn@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(071018,'�Ҿư�','��ȭ��', 'F', '019-888-4116', 'khk@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(100356,'�Ҿư�','�̼���', 'M', '010-777-1234', 'isy@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(104145,'�ܰ�','����', 'M', '010-999-8520', 'kh@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(120309,'�Ǻΰ�','�ڼ���', 'M', '010-777-4996', 'psw@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(130211,'�ܰ�','�̼���', 'F', '010-222-3214', 'isy2@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(901111,'�Ҿư�','������', 'M', '010-156-6443', '1@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(902222,'�ܰ�','���ؿ�', 'M', '010-897-1546', '2@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(903333,'����','�缮��', 'M', '010-1846-9856', '3@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(904444,'�ܰ�','������', 'M', '010-1323-1567', '4@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(905555,'�ܰ�','ä��ȭ', 'F', '010-182-4787', '5@hanbh.com','����')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(906666,'�ܰ�','������', 'F', '010-777-1573', '6@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(907777,'�Ǻΰ�','���λ�', 'F', '010-999-9897', '7@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(908888,'�Ҿư�','������', 'M', '010-875-2568', '8@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(909999,'�Ҿư�','��ܿ�', 'F', '010-121-1212', '9@hanbh.com','����ȣ��')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(901010,'��缱��','�����', 'F', '010-157-8521', '10@hanbh.com','��ȣ��')";
				rowcount += stmt.executeUpdate(query);
				
				
				
				System.out.println("Nurses ���̺� " + rowcount + "���� Ʃ�� �߰� �Ϸ�");
				
				
				//insert - Patients
				//`pat_id` `pat_name` `pat_gen` `pat_jumin`  `pat_addr` `pat_phone` `pat_email`  `pat_job``Doctors_doc_id`  `Nurses_nur_id` 
				rowcount = 0;
				
				query = "INSERT INTO Patients VALUES(2345,'�Ȼ��', 'M', 232345, '����','010-555-7845','ask@ab.com','ȸ���', 980312, 050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(3545,'�輺��', 'M', 543545, '����','010-333-7812','ksr@bb.com','�ڿ���', 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(3424,'������', 'M', 433424, '�λ�','019-888-4859','ijj@ab.com','ȸ���', 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(7675,'�ֱ���', 'M', 677675, '����','010-222-4847','cks@cc.com','ȸ���', 050900, 100356)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4533,'���Ѱ�', 'M', 744533, '����','010-777-9630','jhk@ab.com','����', 000601, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(5546,'������', 'M', 765546, '�뱸','010-777-0214','ywh@cc.com','�ڿ���', 070576, 120309)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4543,'������', 'M', 454543, '�λ�','010-555-4187','cjj@bb.com','ȸ���', 050101, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(9768,'������', 'F', 119768, '����','010-888-3675','ijh@ab.com','����', 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4234,'������', 'F', 234234, '����','010-999-6541','onm@cc.com','�л�', 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(7643,'�ۼ���', 'M', 987643, '����','010-222-5874','ssm@bb.com','�л�', 062019, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(1111,'������', 'M', 981111, '����','010-741-5555','1@naver.com','����', 980312, 050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(2222,'����ȣ', 'M', 982222, 'õ��','010-742-5552','2@naver.com','�ڿ���', 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(3333,'�ѼҸ�', 'F', 983333, 'õ��','010-743-5553','3@naver.com','����', 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4444,'���̾�', 'M', 984444, '�λ�','010-744-5554','4@naver.com','ȸ���', 111111, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(5555,'�����', 'F', 985555, '����','010-745-5555','5@naver.com','ȸ���', 888888,901010)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(6666,'�����', 'M', 986666, '����','010-746-6666','6@naver.com','����', 777777,903333)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(7777,'������', 'F', 987777, '�ϻ�','010-747-7777','7@naver.com','ȸ���', 020403,907777)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(8888,'��ñ�', 'M', 988888, '����','010-748-8888','8@naver.com','ȸ���', 222222,070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(9999,'�ǹٴ�', 'F', 989999, '����','010-749-9999','9@naver.com','�л�', 666666,909999)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(1010,'������', 'F', 981010, '����','010-750-1010','10@naver.com','�ڿ���', 070576,120309)";
				rowcount += stmt.executeUpdate(query);
				
				
				System.out.println("Patients ���̺� " + rowcount + "���� Ʃ�� �߰� �Ϸ�");

				//insert - Treatments
				// `treat_id``treat_contents` `treat_date``Patients_pat_id` `Doctors_doc_id`
				rowcount = 0;
				
				query = "INSERT INTO Treatments VALUES(130516023, '����,����', STR_TO_DATE('2013-05-16','%Y-%m-%d'), 2345, 980312)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(130628100, '�Ǻ� Ʈ���� ġ��', STR_TO_DATE('2013-06-28','%Y-%m-%d'), 3545, 020403)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(131205056, '�� ��ũ�� MRI �Կ�', STR_TO_DATE('2013-12-05','%Y-%m-%d'), 3424, 080543)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(131218024, '���̿�', STR_TO_DATE('2013-12-18','%Y-%m-%d'), 7675, 050900)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(131224012, '�忰', STR_TO_DATE('2013-12-24','%Y-%m-%d'), 4533, 000601)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140103001, '���帧 ġ��', STR_TO_DATE('2014-01-03','%Y-%m-%d'), 5546, 070576)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140109026, '����', STR_TO_DATE('2014-01-09','%Y-%m-%d'), 4543, 050101)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140226102, 'ȭ��ġ��', STR_TO_DATE('2014-02-26','%Y-%m-%d'), 9768, 091001)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140303003, '������ �ܻ�ġ��', STR_TO_DATE('2014-03-03','%Y-%m-%d'), 4234, 091001)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140308087, '�忰', STR_TO_DATE('2014-03-08','%Y-%m-%d'), 7643, 062019)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(151111111, '�忰', STR_TO_DATE('2014-04-01','%Y-%m-%d'), 1111, 980312)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(152222222, '���� ġ��', STR_TO_DATE('2014-06-12','%Y-%m-%d'),2222, 020403)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(153333333, 'MRI �Կ�', STR_TO_DATE('2014-06-25','%Y-%m-%d'),3333, 080543)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(154444444, '����', STR_TO_DATE('2014-06-28','%Y-%m-%d'),4444, 111111)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(155555555, 'MRI �Կ�', STR_TO_DATE('2014-07-01','%Y-%m-%d'),5555, 888888)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(156666666, '����', STR_TO_DATE('2014-07-12','%Y-%m-%d'),6666, 777777)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(157777777, '���帧 ġ��', STR_TO_DATE('2014-07-20','%Y-%m-%d'),7777, 020403)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(158888888, '�忰', STR_TO_DATE('2014-08-05','%Y-%m-%d'),8888, 222222)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(159999999, '���̿�', STR_TO_DATE('2014-08-14','%Y-%m-%d'),9999, 666666)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(151010101, '���帧 ġ��', STR_TO_DATE('2014-08-23','%Y-%m-%d'),1010, 070576)";
				rowcount += stmt.executeUpdate(query);
				
				
				
				System.out.println("Treatments ���̺� " + rowcount + "���� Ʃ�� �߰� �Ϸ�");

				//insert - Charts
				//  `chart_id`  `chart_contents`  `Treatments_treat_id`  `Treatments_Patients_pat_id``Treatments_Doctors_doc_id`  `Nurses_nur_id` 
				rowcount = 0;
				
				query = "INSERT INTO Charts VALUES(1, '2���湮', 130516023, 2345, 980312,050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(2, '�Ǻ� ���ۿ�', 130628100, 3545, 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(3, '�� ������ ����', 131205056, 3424, 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(4, '��û�� ����', 131218024, 7675, 050900, 100356)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(5, '����,���� ����', 131224012, 4533, 000601, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(6, '7�� ġ��', 140103001, 5546, 070576, 120309)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(7, '����,���� ����', 140109026, 4543, 050101, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(8, '1�� ġ��', 140226102, 9768, 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(9, '�� ����ȣ��', 140303003, 4234, 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(10, '���� ����', 140308087, 7643, 062019, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(11, '���� ����', 151111111, 1111, 980312, 050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(12, '���� �ξ����', 152222222, 2222, 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(13, '�� ����ȣ��', 153333333, 3333, 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(14, '���� ��ħ', 154444444, 4444, 111111, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(15, '�� ����ȣ��', 155555555, 5555, 888888, 901010)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(16, '����,���� ����', 156666666, 6666, 777777, 903333)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(17, '�� ���帧', 157777777, 7777, 020403, 907777)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(18, '���� ����', 158888888, 8888, 222222, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(19, '�Ϳ��� ��¦ �Ǻ���', 159999999, 9999, 666666, 909999)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(20, '���� ���帧', 151010101, 1010, 070576, 120309)";
				rowcount += stmt.executeUpdate(query);
				
				
				System.out.println("Charts ���̺� " + rowcount + "���� Ʃ�� �߰� �Ϸ�");
				
				System.out.println("DB �ʱ�ȭ ����!");
				txtResult.setText("DB �ʱ�ȭ ����!");
				
				
			}catch (Exception e1) {
				System.out.println("�ʱ�ȭ �������� : " + e1);
			}
		}
	}
	

	class newwindowInsert extends JFrame{ //���ο� �ǻ� �Է�â
		
		JTextField txtDocID;
		JTextField txtMajTre;
		JTextField txtDocName;
		JTextField txtDocGen;
		JTextField txtDocPhone;
		JTextField txtDocEma;
		JTextField txtDocpos;
		
		newwindowInsert(){
			
			setTitle("�ű� �ǻ� �Է�â");
			setBounds(200, 100, 700, 800);
			setResizable(false);
			setVisible(true);
			
			JPanel pnIN = new JPanel();
			
			
			GridLayout g3 = new GridLayout(8, 2);
			pnIN.setLayout(g3);
			
			add(pnIN);
			
			txtDocID = new JTextField(20);
			txtMajTre = new JTextField(20);
			txtDocName = new JTextField(20);
			txtDocGen = new JTextField(20);
			txtDocPhone = new JTextField(20);
			txtDocEma = new JTextField(20);
			txtDocpos = new JTextField(20);
			
			
			pnIN.add(new JLabel("�ǻ�ID : "));
			pnIN.add(txtDocID);
			
			pnIN.add(new JLabel("���������� : "));
			pnIN.add(txtMajTre);
			
			pnIN.add(new JLabel("���� : "));
			pnIN.add(txtDocName);
			
			pnIN.add(new JLabel("���� : "));
			pnIN.add(txtDocGen);
			
			pnIN.add(new JLabel("��ȭ��ȣ : "));
			pnIN.add(txtDocPhone);
			
			pnIN.add(new JLabel("�̸��� : "));
			pnIN.add(txtDocEma);
			
			pnIN.add(new JLabel("���� : "));
			pnIN.add(txtDocpos);
			
			btnSubM = new JButton("����");
			pnIN.add(new JLabel(""));
			pnIN.add(btnSubM);
			
			btnSubM.addActionListener(new ActionListenerSubM());
			
		}
		
		private class ActionListenerSubM implements ActionListener { // ���ο� �ǻ� �Է� �� �����ư ��������
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String doid = txtDocID.getText();
					String matr = txtMajTre.getText();
					String dona = txtDocName.getText();
					String doge = txtDocGen.getText();
					String doph = txtDocPhone.getText();
					String doem = txtDocEma.getText();
					String dopo = txtDocpos.getText();
					
					String sql1 = "INSERT INTO Doctors VALUES(?,?,?,?,?,?,?);";
					pstmt = con.prepareStatement(sql1);
					
					pstmt.setString(1, doid);
					pstmt.setString(2, matr);
					pstmt.setString(3, dona);
					pstmt.setString(4, doge);
					pstmt.setString(5, doph);
					pstmt.setString(6, doem);
					pstmt.setString(7, dopo);
					
					pstmt.executeUpdate();
					System.out.println("���ο� �ǻ� �Է� �Ϸ�");
					txtResult.setText("���ο� �ǻ� �Է� �Ϸ�");
					
				}catch (Exception e1) {
					System.out.println("�Է½������� : " + e1);
				}
			}
		}
		
		
	}
	


	class newwindowInsertNur extends JFrame{ //���ο� ��ȣ�� �Է�â
		
		JTextField txtNuID;
		JTextField txtMaJo;
		JTextField txtNuNa;
		JTextField txtNuGe;
		JTextField txtNuph;
		JTextField txtNuEm;
		JTextField txtNuPo;
		
		newwindowInsertNur(){
			
			setTitle("�ű� ��ȣ�� �Է�â");
			setBounds(200, 100, 700, 800);
			setResizable(false);
			setVisible(true);
			
			JPanel pnINNur = new JPanel();
			
			
			GridLayout g3 = new GridLayout(8, 2);
			pnINNur.setLayout(g3);
			
			add(pnINNur);
			
			txtNuID = new JTextField(20);
			txtMaJo = new JTextField(20);
			txtNuNa = new JTextField(20);
			txtNuGe = new JTextField(20);
			txtNuph = new JTextField(20);
			txtNuEm = new JTextField(20);
			txtNuPo = new JTextField(20);
			
			
			
			pnINNur.add(new JLabel("��ȣ��ID : "));
			pnINNur.add(txtNuID);
			
			pnINNur.add(new JLabel("������ : "));
			pnINNur.add(txtMaJo);
			
			pnINNur.add(new JLabel("���� : "));
			pnINNur.add(txtNuNa);
			
			pnINNur.add(new JLabel("���� : "));
			pnINNur.add(txtNuGe);
			
			pnINNur.add(new JLabel("��ȭ��ȣ : "));
			pnINNur.add(txtNuph);
			
			pnINNur.add(new JLabel("�̸��� : "));
			pnINNur.add(txtNuEm);
			
			pnINNur.add(new JLabel("���� : "));
			pnINNur.add(txtNuPo);
			
			btnSubNur = new JButton("����");
			pnINNur.add(new JLabel(""));
			pnINNur.add(btnSubNur);
			
			btnSubNur.addActionListener(new ActionListenerSubNur());
			
		}
		
		private class ActionListenerSubNur implements ActionListener { // ���ο� ��ȣ�� �Է� �� �����ư ��������
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String nuid = txtNuID.getText();
					String majo = txtMaJo.getText();
					String nuna = txtNuNa.getText();
					String nuge = txtNuGe.getText();
					String nuph = txtNuph.getText();
					String nuem = txtNuEm.getText();
					String nupo = txtNuPo.getText();
					
					String sql1 = "INSERT INTO Nurses VALUES(?,?,?,?,?,?,?);";
					pstmt = con.prepareStatement(sql1);
					
					pstmt.setString(1, nuid);
					pstmt.setString(2, majo);
					pstmt.setString(3, nuna);
					pstmt.setString(4, nuge);
					pstmt.setString(5, nuph);
					pstmt.setString(6, nuem);
					pstmt.setString(7, nupo);
					
					pstmt.executeUpdate();
					System.out.println("���ο� ��ȣ�� �Է� �Ϸ�");
					txtResult.setText("���ο� ��ȣ�� �Է� �Ϸ�");
					
					
				}catch (Exception e1) {
					System.out.println("�Է½������� : " + e1);
				}
			}
		}
	}
	
	class newwindowInsertPat extends JFrame{ //���ο� ȯ�� �Է�â
		
		//10��
		JTextField txtPaID;
		JTextField txtNuuID;
		JTextField txtDoccID;
		JTextField txtPaNa;
		JTextField txtPaGe;
		JTextField txtPaJu;
		JTextField txtPaAd;
		JTextField txtPaPh;
		JTextField txtPaEm;
		JTextField txtPaJo;
		
		newwindowInsertPat(){
			
			setTitle("�ű� ȯ�� �Է�â");
			setBounds(200, 100, 700, 800);
			setResizable(false);
			setVisible(true);
			
			JPanel pnINPat = new JPanel();
			
			
			GridLayout g3 = new GridLayout(11, 2);
			pnINPat.setLayout(g3);
			
			add(pnINPat);
			
			txtPaID = new JTextField(20);
			txtNuuID = new JTextField(20);
			txtDoccID = new JTextField(20);
			txtPaNa = new JTextField(20);
			txtPaGe = new JTextField(20);
			
			txtPaJu = new JTextField(20);
			txtPaAd = new JTextField(20);
			txtPaPh = new JTextField(20);
			txtPaEm = new JTextField(20);
			txtPaJo = new JTextField(20);
			
			
			pnINPat.add(new JLabel("ȯ��ID : "));
			pnINPat.add(txtPaID);
			
			pnINPat.add(new JLabel("��ȣ��ID : "));
			pnINPat.add(txtNuuID);
			
			pnINPat.add(new JLabel("�ǻ�ID : "));
			pnINPat.add(txtDoccID);
			
			pnINPat.add(new JLabel("ȯ�ڼ��� : "));
			pnINPat.add(txtPaNa);
			
			pnINPat.add(new JLabel("ȯ�ڼ��� : "));
			pnINPat.add(txtPaGe);
			
			pnINPat.add(new JLabel("�ֹι�ȣ : "));
			pnINPat.add(txtPaJu);
			
			pnINPat.add(new JLabel("�ּ� : "));
			pnINPat.add(txtPaAd);
			
			pnINPat.add(new JLabel("��ȭ��ȣ : "));
			pnINPat.add(txtPaPh);
			
			pnINPat.add(new JLabel("�̸��� : "));
			pnINPat.add(txtPaEm);
			
			pnINPat.add(new JLabel("���� : "));
			pnINPat.add(txtPaJo);
			
			
			btnSubPat = new JButton("����");
			pnINPat.add(new JLabel(""));
			pnINPat.add(btnSubPat);
			
			btnSubPat.addActionListener(new ActionListenerSubPat());
			
		}
		
		private class ActionListenerSubPat implements ActionListener { // ���ο� ȯ�� �Է� �� �����ư ��������
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String paid = txtPaID.getText();
					String nuuid = txtNuuID.getText();
					String dccid = txtDoccID.getText();
					String pana = txtPaNa.getText();
					String page = txtPaGe.getText();
					
					String paju = txtPaJu.getText();
					String paad = txtPaAd.getText();
					String paph = txtPaPh.getText();
					String paem = txtPaEm.getText();
					String pajo = txtPaJo.getText();
					
					String sql1 = "INSERT INTO Patients VALUES(?,?,?,?,?,?,?,?,?,?);";
					pstmt = con.prepareStatement(sql1);
					
					pstmt.setString(1, paid);
					pstmt.setString(2, pana);
					pstmt.setString(3, page);
					pstmt.setString(4, paju);
					pstmt.setString(5, paad);
					pstmt.setString(6, paph);
					pstmt.setString(7, paem);
					pstmt.setString(8, pajo);
					pstmt.setString(9, dccid);
					pstmt.setString(10, nuuid);
					
					pstmt.executeUpdate();
					System.out.println("���ο� ȯ�� �Է� �Ϸ�");
					txtResult.setText("���ο� ȯ�� �Է� �Ϸ�");
					
					
				}catch (Exception e1) {
					System.out.println("�Է½������� : " + e1);
				}
			}
		}
	}
	
	
	

	class newwindowInsertTre extends JFrame{ //���ο� ����Ʃ�� �Է�â
		
		JTextField txtTrID;
		JTextField txtPaaID;
		JTextField txtDotIC;
		JTextField txtTrCon;
		JTextField txtTrDa;
		
		newwindowInsertTre(){
			
			setTitle("�ű� ���� �Է�â");
			setBounds(200, 100, 700, 800);
			setResizable(false);
			setVisible(true);
			
			JPanel pnINTre = new JPanel();
			
			
			GridLayout g3 = new GridLayout(6, 2);
			pnINTre.setLayout(g3);
			
			add(pnINTre);
			
			txtTrID = new JTextField(20);
			txtPaaID = new JTextField(20);
			txtDotIC = new JTextField(20);
			txtTrCon = new JTextField(20);
			txtTrDa = new JTextField(20);
			
			
			pnINTre.add(new JLabel("����ID : "));
			pnINTre.add(txtTrID);
			
			pnINTre.add(new JLabel("ȯ��ID : "));
			pnINTre.add(txtPaaID);
			
			pnINTre.add(new JLabel("�ǻ�ID : "));
			pnINTre.add(txtDotIC);
			
			pnINTre.add(new JLabel("���᳻�� : "));
			pnINTre.add(txtTrCon);
			
			pnINTre.add(new JLabel("���ᳯ¥ : "));
			pnINTre.add(txtTrDa);
			
			btnSubTre = new JButton("����");
			pnINTre.add(new JLabel(""));
			pnINTre.add(btnSubTre);
			
			btnSubTre.addActionListener(new ActionListenerSubTre());
			
		}
		
		private class ActionListenerSubTre implements ActionListener { // �ű� ���� �Է� �� �����ư ��������
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String trid= txtTrID.getText();
					String paid = txtPaaID.getText();
					String doctid = txtDotIC.getText();
					String trco = txtTrCon.getText();
					String trda = txtTrDa.getText();
					
					
					String sql1 = "INSERT INTO Treatments VALUES(?,?,?,?,?);";
					pstmt = con.prepareStatement(sql1);
					
					pstmt.setString(1, trid);
					pstmt.setString(2, trco);
					pstmt.setString(3, trda);
					pstmt.setString(4, paid);
					pstmt.setString(5, doctid);
					
					pstmt.executeUpdate();
					System.out.println("�ű� ���� �Է� �Ϸ�");
					txtResult.setText("�ű� ���� �Է� �Ϸ�");
					
					
				}catch (Exception e1) {
					System.out.println("�Է½������� : " + e1);
				}
			}
		}
	}
	
	
	


	class newwindowInsertCha extends JFrame{ //���ο� ��Ʈ �Է�â
		
		JTextField txtChID;
		JTextField txtChCo;
		JTextField txtTrrId;
		JTextField txtPaaaID;
		JTextField txtDoctorID;
		JTextField txtNurseID;
		
		newwindowInsertCha(){
			
			setTitle("�ű� ��Ʈ �Է�â");
			setBounds(200, 100, 700, 800);
			setResizable(false);
			setVisible(true);
			
			JPanel pnINCha = new JPanel();
			
			
			GridLayout g3 = new GridLayout(7, 2);
			pnINCha.setLayout(g3);
			
			add(pnINCha);
			
			txtChID = new JTextField(20);
			txtChCo = new JTextField(20);
			txtTrrId = new JTextField(20);
			txtPaaaID = new JTextField(20);
			txtDoctorID = new JTextField(20);
			txtNurseID = new JTextField(20);
			
			
			
			pnINCha.add(new JLabel("��ƮID : "));
			pnINCha.add(txtChID);
			
			pnINCha.add(new JLabel("��Ʈ���� : "));
			pnINCha.add(txtChCo);
			
			pnINCha.add(new JLabel("����ID : "));
			pnINCha.add(txtTrrId);
			
			pnINCha.add(new JLabel("ȯ��ID : "));
			pnINCha.add(txtPaaaID);
			
			pnINCha.add(new JLabel("�ǻ�ID : "));
			pnINCha.add(txtDoctorID);
			
			pnINCha.add(new JLabel("��ȣ��ID : "));
			pnINCha.add(txtNurseID);
			
			btnSubCha = new JButton("����");
			pnINCha.add(new JLabel(""));
			pnINCha.add(btnSubCha);
			
			btnSubCha.addActionListener(new ActionListenerSubCha());
			
		}
		
		private class ActionListenerSubCha implements ActionListener { // ���ο� ��Ʈ �Է� �� �����ư ��������
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String chid = txtChID.getText();
					String chco = txtChCo.getText();
					String trrid = txtTrrId.getText();
					String paaaid = txtPaaaID.getText();
					String doctorid = txtDoctorID.getText();
					String nurseid = txtNurseID.getText();
					
					
					String sql1 = "INSERT INTO Charts VALUES(?,?,?,?,?,?);";
					pstmt = con.prepareStatement(sql1);
					
					pstmt.setString(1, chid);
					pstmt.setString(2, chco);
					pstmt.setString(3, trrid);
					pstmt.setString(4, paaaid);
					pstmt.setString(5, doctorid);
					pstmt.setString(6, nurseid);

					
					pstmt.executeUpdate();
					System.out.println("���ο� ��Ʈ �Է� �Ϸ�");
					txtResult.setText("���ο� ��Ʈ �Է� �Ϸ�");
					
					
				}catch (Exception e1) {
					System.out.println("�Է½������� : " + e1);
				}
			}
		}
	}
	
	
	
	
	
	public void conDB() { //�����ͺ��̽� ���� 
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
	
		  
	public static void main(String[] args) { //����
		HospitalAdministration HA = new HospitalAdministration();
		
		
		HA.addWindowListener(new WindowAdapter() {
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
