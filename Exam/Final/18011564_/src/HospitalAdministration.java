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
	
	JButton btnInsert1; //의사 입력
	JButton btnInsertNur; //간호사 입력
	JButton btnInsertPat; //환자 입력
	JButton btnInsertTre; //진료 입력
	JButton btnInsertCha; //차트 입력 
	
	JButton btnSubM; //의사입력제출버튼
	JButton btnSubNur; //간호사 입력 제출버튼
	JButton btnSubPat; //환자 입력 제출버튼
	JButton btnSubTre; //진료 입력 제출 버튼
	JButton btnSubCha; //차트 입력 제출 버튼 
	
	JButton btnSelDoc; //의사 출력
	JButton btnSelNur; //간호사 출력
	JButton btnSelPat; //환자 출력
	JButton btnSelTre; //진료 출력
	JButton btnSelCha; //차트 출력
	
	PreparedStatement pstmt;
	
	static Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
		
		  
	public HospitalAdministration() //생성자
	{
		super("18011564/변영화");
		conDB();
		setInit();
		setVisible(true);
		setBounds(200, 200, 1500, 700); //가로위치,세로위치,가로길이,세로길이
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
		
		btnReset = new JButton("DB초기화");
		
		btnInsert1 = new JButton("신규 의사 입력");
		btnInsertNur = new JButton("신규 간호사 입력"); 
		btnInsertPat = new JButton("신규 환자 입력"); 
		btnInsertTre = new JButton("진료 입력"); 
		btnInsertCha = new JButton("차트입력");
		
		btnSelDoc = new JButton("의사 검색"); //의사 출력
		btnSelNur = new JButton("간호사 검색"); //간호사 출력
		btnSelPat = new JButton("환자 검색"); //환자 출력
		btnSelTre = new JButton("진료 검색"); //진료 출력
		btnSelCha = new JButton("차트 검색"); //차트 출력
		
		btnSel2 = new JButton("검색2");
		btnSel3 = new JButton("검색3");
		
		pnButton.add(btnReset);
		pnButton.add(new JLabel("")); //1
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		
		//2 입력
		pnButton.add(btnInsert1);
		pnButton.add(btnInsertNur);
		pnButton.add(btnInsertPat);
		pnButton.add(btnInsertTre);
		pnButton.add(btnInsertCha);
		
		//3 검색1
		pnButton.add(btnSelDoc);
		pnButton.add(btnSelNur);
		pnButton.add(btnSelPat);
		pnButton.add(btnSelTre);
		pnButton.add(btnSelCha);
		
		//4 검색 -2,3
		
		pnButton.add(btnSel2);
		pnButton.add(btnSel3);
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		pnButton.add(new JLabel(""));
		
		btnReset.addActionListener(new ActionListenerDBInit()); //DB초기화 버튼 누르면?
		
	
		
		//입력버튼 1,2,3,4,5 
		
		btnInsert1.addActionListener(new ActionListener() {	//의사 입력 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsert();
			}
		});
		
		btnInsertNur.addActionListener(new ActionListener() {	//간호사 입력 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertNur();
			}
		});
		
		btnInsertPat.addActionListener(new ActionListener() {	//환자 입력 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertPat();
			}
		});
		
		
		btnInsertTre.addActionListener(new ActionListener() {	//진료 입력 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertTre();
			}
		});
		
		
		btnInsertCha.addActionListener(new ActionListener() {	//차트 입력 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				new newwindowInsertCha();
			}
		});
		
		
		//검색버튼 1,2,3,4,5
		
		btnSelDoc.addActionListener(new ActionListener() {	//의사 검색 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("의사ID" + "\t" + "담당진료과목" + "\t" + "성명" + "\t" + "성별" + "\t" + "전화번호" + "\t"+ "이메일" + "\t" +"직급" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Doctors;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
								+ " \t " + rs.getString(5) + "\t" + rs.getString(6) + "\t" +rs.getString(7) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
		
		
		btnSelNur.addActionListener(new ActionListener() {	//간호사 검색 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("간호사ID" + "\t" + "담당업무" + "\t" + "성명" + "\t" + "성별" + "\t" + "전화번호" + "\t"+ "이메일" + "\t" +"직급" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Nurses;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
								+ " \t " + rs.getString(5) + "\t" + rs.getString(6) + "\t" +rs.getString(7) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
	
		
		btnSelPat.addActionListener(new ActionListener() {	//환자 검색 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("환자ID" + "\t" + "환자성명" + "\t" + "환자성별" + "\t" + "주민번호" + "\t" + "주소" + "\t"+ "전화번호" + "\t" +"이메일" + "\t" +"직업"+ "\t" +"의사ID"+ "\t" +"간호사ID"+ "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Patients;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
								+ " \t " + rs.getString(5) + "\t" + rs.getString(6) + "\t" +rs.getString(7) + "\t" +rs.getString(8)+ "\t" +rs.getInt(9)+ "\t" +rs.getInt(10)+ "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
		
		btnSelTre.addActionListener(new ActionListener() {	//진료 검색 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("진료ID" + "\t" + "진료내용" + "\t" + "진료날짜" + "\t" + "환자ID" + "\t" + "의사ID" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Treatments;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDate(3) + "\t" + rs.getInt(4)
								+ " \t " + rs.getInt(5) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
		
		
		btnSelCha.addActionListener(new ActionListener() {	//차트 검색 버튼 누르면?
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					txtResult.setText("차트ID" + "\t" + "차트내용" + "\t" + "진료ID"+ "\t" + "환자ID" + "\t" + "의사ID" + "\t" + "간호사ID" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select * from Charts;";
					rs = stmt.executeQuery(sql2);
					
					while (rs.next()) {
						String res = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
								+ " \t " + rs.getInt(5) + "\t" + rs.getInt(6) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
		
		
		btnSel2.addActionListener(new ActionListener() {	//검색2버튼 - <진료 경험이 있는 의사의 이름과 담당 진료 과목>
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					
					txtResult.setText("\t"+"<진료 경험이 있는 의사의 이름과 담당 진료 과목을 출력>" + "\n"+ "\n");
					txtResult.append("의사이름" + "\t" + "담당진료과목" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select doc_name '의사이름', major_treat '담당진료과목'\r\n" + 
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
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
		
		btnSel3.addActionListener(new ActionListener() {	//검색3버튼 - <여자 의사랑 같이 진료해본 간호사에게 할당된 환자의 이름과 성별 출력 >
			public void actionPerformed(ActionEvent e) {
				try {
					txtResult.setText("");
					
					txtResult.setText("\t"+"<여자 의사랑 같이 진료해본 간호사에게 할당된 환자의 이름과 성별 출력 >" + "\n"+ "\n");
					txtResult.append("환자이름" + "\t" + "환자성별" + "\n");
					
					stmt = con.createStatement();
					
					String sql2 = "select pat_name '환자 이름' ,pat_gen '환자 성별'\r\n" + 
							"from Patients\r\n" + 
							"where Nurses_nur_id IN (select Nurses_nur_id from Charts where Treatments_Doctors_doc_id IN (select doc_id from Doctors where doc_gen='F'))\r\n" + 
							"group by pat_name;";
					rs = stmt.executeQuery(sql2);
					
					
					while (rs.next()) {
						String res = rs.getString(1) + "\t" + rs.getString(2) + "\n";
						txtResult.append(res);
					}
					
				}catch (Exception e1) {
					System.out.println("검색실패이유 : " + e1);
				}
			}
		});
		
		
		txtResult.setText("<출력창>");

		add(pnButton);
		add(pnResult);		
		
		
	}
		  
	
	private class ActionListenerDBInit implements ActionListener { // DB초기화 버튼 눌렀을 때 
		public void actionPerformed(ActionEvent e) {
			try {
				
				System.out.println("DB초기화 시작...");
				
				System.out.println("이미 존재하는 table이면 drop합니다..");
				
				stmt = con.createStatement();
				String query;
				
				//이미 존재하는 table이면 drop
				
				query = "DROP TABLE IF EXISTS Charts";
				stmt.executeUpdate(query);
				System.out.println("Charts 테이블 DROP 완료");

				query = "DROP TABLE IF EXISTS Treatments";
				stmt.executeUpdate(query);
				System.out.println("Treatments 테이블 DROP 완료");

				query = "DROP TABLE IF EXISTS Patients";
				stmt.executeUpdate(query);
				System.out.println("Patients 테이블 DROP 완료");

				query = "DROP TABLE IF EXISTS Doctors";
				stmt.executeUpdate(query);
				System.out.println("Doctors 테이블 DROP 완료");

				query = "DROP TABLE IF EXISTS Nurses";
				stmt.executeUpdate(query);
				System.out.println("Nurses 테이블 DROP 완료");

				System.out.println("DROP 성공!");
				
				//table create
				
				//Doctors
				System.out.println("table CREATE 시작...");
				
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
				System.out.println("Doctors table CREATE 완료");
				
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
				System.out.println("Nurses table CREATE 완료");
				
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
				System.out.println("Patients table CREATE 완료");
				
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
				System.out.println("Treatments table CREATE 완료");
				
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
				System.out.println("Charts table CREATE 완료");
				
				System.out.println("table CREATE 성공!");
				
				
				//각 테이블마다 튜플 insert
				System.out.println("기본 튜플 INSERT 시도...");
				int rowcount = 0;
				
				//insert - Doctors
				query = "INSERT INTO Doctors VALUES(980312,'소아과','이태정', 'M', '010-333-1340', 'itj@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(000601,'내과','안성기', 'M', '011-222-0987', 'ask@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(001208,'외과','김민종', 'M', '010-333-8743', 'kmj@han.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(020403,'피부과','이태서', 'M', '019-777-3764', 'its@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(050900,'소아과','김연아', 'F', '010-555-3746', 'kya@habhn.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(050101,'내과','차태현', 'M', '011-222-7643', 'cth@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(062019,'소아과','전지현', 'F', '010-999-1265', 'jjh@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(070576,'피부과','홍길동', 'M', '016-333-7263', 'hgd@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(080543,'방사선과','유재석', 'M', '010-222-1263', 'yjs@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(091001,'외과','김병만', 'M', '010-555-3542', 'kbm@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(111111,'소아과','아이유', 'F', '010-111-1111', 'iu@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(222222,'내과','여진구', 'M', '010-525-5252', 'yjg@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(333333,'외과','노홍철', 'M', '010-333-3333', '33@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(444444,'방사선과','하동훈', 'M', '010-444-4444', '44h@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(555555,'피부과','김범수', 'M', '010-555-5555', '55@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(666666,'소아과','길성준', 'M', '010-666-6666', '66@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(777777,'내과','이효리', 'F', '010-777-7777', '77@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(888888,'방사선과','정준하', 'M', '010-888-8888', '88@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(999999,'성형외과','박나래', 'F', '010-999-9999', '99@hanbh.com','과장')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Doctors VALUES(101010,'내과','김희민', 'M', '010-101-1010', '10@hanbh.com','전문의')";
				rowcount += stmt.executeUpdate(query);
				
				
				System.out.println("Doctors 테이블에 " + rowcount + "개의 튜플 추가 완료");

				//insert - Nurses
				rowcount = 0;
				
				query = "INSERT INTO Nurses VALUES(050302,'소아과','김은영', 'F', '010-555-8751', 'key@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(050021,'내과','윤성애', 'F', '016-333-8745', 'ysa@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(040089,'피부과','신지원', 'M', '010-666-7646', 'sjw@hanbh.com','주임')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(070605,'방사선과','유정화', 'F', '010-333-4588', 'yjh@hanbh.com','주임')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(070804,'내과','라하나', 'F', '010-222-1340', 'nhn@hanbh.com','주임')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(071018,'소아과','김화경', 'F', '019-888-4116', 'khk@hanbh.com','주임')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(100356,'소아과','이선용', 'M', '010-777-1234', 'isy@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(104145,'외과','김현', 'M', '010-999-8520', 'kh@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(120309,'피부과','박성완', 'M', '010-777-4996', 'psw@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(130211,'외과','이서연', 'F', '010-222-3214', 'isy2@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(901111,'소아과','안정원', 'M', '010-156-6443', '1@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(902222,'외과','김준완', 'M', '010-897-1546', '2@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(903333,'내과','양석형', 'M', '010-1846-9856', '3@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(904444,'외과','이익준', 'M', '010-1323-1567', '4@hanbh.com','주임')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(905555,'외과','채송화', 'F', '010-182-4787', '5@hanbh.com','주임')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(906666,'외과','소이현', 'F', '010-777-1573', '6@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(907777,'피부과','정로사', 'F', '010-999-9897', '7@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(908888,'소아과','주종수', 'M', '010-875-2568', '8@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(909999,'소아과','장겨울', 'F', '010-121-1212', '9@hanbh.com','수간호사')";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Nurses VALUES(901010,'방사선과','김희애', 'F', '010-157-8521', '10@hanbh.com','간호사')";
				rowcount += stmt.executeUpdate(query);
				
				
				
				System.out.println("Nurses 테이블에 " + rowcount + "개의 튜플 추가 완료");
				
				
				//insert - Patients
				//`pat_id` `pat_name` `pat_gen` `pat_jumin`  `pat_addr` `pat_phone` `pat_email`  `pat_job``Doctors_doc_id`  `Nurses_nur_id` 
				rowcount = 0;
				
				query = "INSERT INTO Patients VALUES(2345,'안상건', 'M', 232345, '서울','010-555-7845','ask@ab.com','회사원', 980312, 050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(3545,'김성룡', 'M', 543545, '서울','010-333-7812','ksr@bb.com','자영업', 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(3424,'이종진', 'M', 433424, '부산','019-888-4859','ijj@ab.com','회사원', 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(7675,'최광석', 'M', 677675, '당진','010-222-4847','cks@cc.com','회사원', 050900, 100356)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4533,'정한경', 'M', 744533, '강릉','010-777-9630','jhk@ab.com','교수', 000601, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(5546,'유원현', 'M', 765546, '대구','010-777-0214','ywh@cc.com','자영업', 070576, 120309)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4543,'최재정', 'M', 454543, '부산','010-555-4187','cjj@bb.com','회사원', 050101, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(9768,'이진희', 'F', 119768, '서울','010-888-3675','ijh@ab.com','교수', 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4234,'오나미', 'F', 234234, '속초','010-999-6541','onm@cc.com','학생', 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(7643,'송성묵', 'M', 987643, '서울','010-222-5874','ssm@bb.com','학생', 062019, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(1111,'정형돈', 'M', 981111, '서울','010-741-5555','1@naver.com','무직', 980312, 050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(2222,'조세호', 'M', 982222, '천안','010-742-5552','2@naver.com','자영업', 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(3333,'한소리', 'F', 983333, '천안','010-743-5553','3@naver.com','가수', 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(4444,'아이언', 'M', 984444, '부산','010-744-5554','4@naver.com','회사원', 111111, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(5555,'김아중', 'F', 985555, '서울','010-745-5555','5@naver.com','회사원', 888888,901010)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(6666,'정기오', 'M', 986666, '서울','010-746-6666','6@naver.com','교수', 777777,903333)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(7777,'오마이', 'F', 987777, '일산','010-747-7777','7@naver.com','회사원', 020403,907777)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(8888,'김시광', 'M', 988888, '대전','010-748-8888','8@naver.com','회사원', 222222,070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(9999,'피바다', 'F', 989999, '서울','010-749-9999','9@naver.com','학생', 666666,909999)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Patients VALUES(1010,'김제니', 'F', 981010, '서울','010-750-1010','10@naver.com','자영업', 070576,120309)";
				rowcount += stmt.executeUpdate(query);
				
				
				System.out.println("Patients 테이블에 " + rowcount + "개의 튜플 추가 완료");

				//insert - Treatments
				// `treat_id``treat_contents` `treat_date``Patients_pat_id` `Doctors_doc_id`
				rowcount = 0;
				
				query = "INSERT INTO Treatments VALUES(130516023, '감기,몸살', STR_TO_DATE('2013-05-16','%Y-%m-%d'), 2345, 980312)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(130628100, '피부 트러블 치료', STR_TO_DATE('2013-06-28','%Y-%m-%d'), 3545, 020403)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(131205056, '목 디스크로 MRI 촬영', STR_TO_DATE('2013-12-05','%Y-%m-%d'), 3424, 080543)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(131218024, '중이염', STR_TO_DATE('2013-12-18','%Y-%m-%d'), 7675, 050900)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(131224012, '장염', STR_TO_DATE('2013-12-24','%Y-%m-%d'), 4533, 000601)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140103001, '여드름 치료', STR_TO_DATE('2014-01-03','%Y-%m-%d'), 5546, 070576)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140109026, '위염', STR_TO_DATE('2014-01-09','%Y-%m-%d'), 4543, 050101)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140226102, '화상치료', STR_TO_DATE('2014-02-26','%Y-%m-%d'), 9768, 091001)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140303003, '교통사고 외상치료', STR_TO_DATE('2014-03-03','%Y-%m-%d'), 4234, 091001)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(140308087, '장염', STR_TO_DATE('2014-03-08','%Y-%m-%d'), 7643, 062019)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(151111111, '장염', STR_TO_DATE('2014-04-01','%Y-%m-%d'), 1111, 980312)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(152222222, '염증 치료', STR_TO_DATE('2014-06-12','%Y-%m-%d'),2222, 020403)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(153333333, 'MRI 촬영', STR_TO_DATE('2014-06-25','%Y-%m-%d'),3333, 080543)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(154444444, '감기', STR_TO_DATE('2014-06-28','%Y-%m-%d'),4444, 111111)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(155555555, 'MRI 촬영', STR_TO_DATE('2014-07-01','%Y-%m-%d'),5555, 888888)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(156666666, '위염', STR_TO_DATE('2014-07-12','%Y-%m-%d'),6666, 777777)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(157777777, '여드름 치료', STR_TO_DATE('2014-07-20','%Y-%m-%d'),7777, 020403)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(158888888, '장염', STR_TO_DATE('2014-08-05','%Y-%m-%d'),8888, 222222)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(159999999, '중이염', STR_TO_DATE('2014-08-14','%Y-%m-%d'),9999, 666666)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Treatments VALUES(151010101, '여드름 치료', STR_TO_DATE('2014-08-23','%Y-%m-%d'),1010, 070576)";
				rowcount += stmt.executeUpdate(query);
				
				
				
				System.out.println("Treatments 테이블에 " + rowcount + "개의 튜플 추가 완료");

				//insert - Charts
				//  `chart_id`  `chart_contents`  `Treatments_treat_id`  `Treatments_Patients_pat_id``Treatments_Doctors_doc_id`  `Nurses_nur_id` 
				rowcount = 0;
				
				query = "INSERT INTO Charts VALUES(1, '2차방문', 130516023, 2345, 980312,050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(2, '피부 부작용', 130628100, 3545, 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(3, '목 움직임 없음', 131205056, 3424, 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(4, '엄청난 고통', 131218024, 7675, 050900, 100356)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(5, '복통,구토 있음', 131224012, 4533, 000601, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(6, '7차 치료', 140103001, 5546, 070576, 120309)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(7, '복통,구토 있음', 140109026, 4543, 050101, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(8, '1차 치료', 140226102, 9768, 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(9, '목에 고통호소', 140303003, 4234, 091001, 130211)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(10, '복통 있음', 140308087, 7643, 062019, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(11, '복통 있음', 151111111, 1111, 980312, 050302)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(12, '염증 부어오름', 152222222, 2222, 020403, 040089)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(13, '목에 통증호소', 153333333, 3333, 080543, 070605)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(14, '심한 기침', 154444444, 4444, 111111, 071018)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(15, '목에 통증호소', 155555555, 5555, 888888, 901010)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(16, '복통,구토 있음', 156666666, 6666, 777777, 903333)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(17, '등 여드름', 157777777, 7777, 020403, 907777)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(18, '구토 있음', 158888888, 8888, 222222, 070804)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(19, '귀에서 살짝 피보임', 159999999, 9999, 666666, 909999)";
				rowcount += stmt.executeUpdate(query);
				query = "INSERT INTO Charts VALUES(20, '심한 여드름', 151010101, 1010, 070576, 120309)";
				rowcount += stmt.executeUpdate(query);
				
				
				System.out.println("Charts 테이블에 " + rowcount + "개의 튜플 추가 완료");
				
				System.out.println("DB 초기화 성공!");
				txtResult.setText("DB 초기화 성공!");
				
				
			}catch (Exception e1) {
				System.out.println("초기화 실패이유 : " + e1);
			}
		}
	}
	

	class newwindowInsert extends JFrame{ //새로운 의사 입력창
		
		JTextField txtDocID;
		JTextField txtMajTre;
		JTextField txtDocName;
		JTextField txtDocGen;
		JTextField txtDocPhone;
		JTextField txtDocEma;
		JTextField txtDocpos;
		
		newwindowInsert(){
			
			setTitle("신규 의사 입력창");
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
			
			
			pnIN.add(new JLabel("의사ID : "));
			pnIN.add(txtDocID);
			
			pnIN.add(new JLabel("담당진료과목 : "));
			pnIN.add(txtMajTre);
			
			pnIN.add(new JLabel("성명 : "));
			pnIN.add(txtDocName);
			
			pnIN.add(new JLabel("성별 : "));
			pnIN.add(txtDocGen);
			
			pnIN.add(new JLabel("전화번호 : "));
			pnIN.add(txtDocPhone);
			
			pnIN.add(new JLabel("이메일 : "));
			pnIN.add(txtDocEma);
			
			pnIN.add(new JLabel("직급 : "));
			pnIN.add(txtDocpos);
			
			btnSubM = new JButton("제출");
			pnIN.add(new JLabel(""));
			pnIN.add(btnSubM);
			
			btnSubM.addActionListener(new ActionListenerSubM());
			
		}
		
		private class ActionListenerSubM implements ActionListener { // 새로운 의사 입력 후 제출버튼 눌렀을때
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
					System.out.println("새로운 의사 입력 완료");
					txtResult.setText("새로운 의사 입력 완료");
					
				}catch (Exception e1) {
					System.out.println("입력실패이유 : " + e1);
				}
			}
		}
		
		
	}
	


	class newwindowInsertNur extends JFrame{ //새로운 간호사 입력창
		
		JTextField txtNuID;
		JTextField txtMaJo;
		JTextField txtNuNa;
		JTextField txtNuGe;
		JTextField txtNuph;
		JTextField txtNuEm;
		JTextField txtNuPo;
		
		newwindowInsertNur(){
			
			setTitle("신규 간호사 입력창");
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
			
			
			
			pnINNur.add(new JLabel("간호사ID : "));
			pnINNur.add(txtNuID);
			
			pnINNur.add(new JLabel("담당업무 : "));
			pnINNur.add(txtMaJo);
			
			pnINNur.add(new JLabel("성명 : "));
			pnINNur.add(txtNuNa);
			
			pnINNur.add(new JLabel("성별 : "));
			pnINNur.add(txtNuGe);
			
			pnINNur.add(new JLabel("전화번호 : "));
			pnINNur.add(txtNuph);
			
			pnINNur.add(new JLabel("이메일 : "));
			pnINNur.add(txtNuEm);
			
			pnINNur.add(new JLabel("직급 : "));
			pnINNur.add(txtNuPo);
			
			btnSubNur = new JButton("제출");
			pnINNur.add(new JLabel(""));
			pnINNur.add(btnSubNur);
			
			btnSubNur.addActionListener(new ActionListenerSubNur());
			
		}
		
		private class ActionListenerSubNur implements ActionListener { // 새로운 간호사 입력 후 제출버튼 눌렀을때
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
					System.out.println("새로운 간호사 입력 완료");
					txtResult.setText("새로운 간호사 입력 완료");
					
					
				}catch (Exception e1) {
					System.out.println("입력실패이유 : " + e1);
				}
			}
		}
	}
	
	class newwindowInsertPat extends JFrame{ //새로운 환자 입력창
		
		//10개
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
			
			setTitle("신규 환자 입력창");
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
			
			
			pnINPat.add(new JLabel("환자ID : "));
			pnINPat.add(txtPaID);
			
			pnINPat.add(new JLabel("간호사ID : "));
			pnINPat.add(txtNuuID);
			
			pnINPat.add(new JLabel("의사ID : "));
			pnINPat.add(txtDoccID);
			
			pnINPat.add(new JLabel("환자성명 : "));
			pnINPat.add(txtPaNa);
			
			pnINPat.add(new JLabel("환자성별 : "));
			pnINPat.add(txtPaGe);
			
			pnINPat.add(new JLabel("주민번호 : "));
			pnINPat.add(txtPaJu);
			
			pnINPat.add(new JLabel("주소 : "));
			pnINPat.add(txtPaAd);
			
			pnINPat.add(new JLabel("전화번호 : "));
			pnINPat.add(txtPaPh);
			
			pnINPat.add(new JLabel("이메일 : "));
			pnINPat.add(txtPaEm);
			
			pnINPat.add(new JLabel("직업 : "));
			pnINPat.add(txtPaJo);
			
			
			btnSubPat = new JButton("제출");
			pnINPat.add(new JLabel(""));
			pnINPat.add(btnSubPat);
			
			btnSubPat.addActionListener(new ActionListenerSubPat());
			
		}
		
		private class ActionListenerSubPat implements ActionListener { // 새로운 환자 입력 후 제출버튼 눌렀을때
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
					System.out.println("새로운 환자 입력 완료");
					txtResult.setText("새로운 환자 입력 완료");
					
					
				}catch (Exception e1) {
					System.out.println("입력실패이유 : " + e1);
				}
			}
		}
	}
	
	
	

	class newwindowInsertTre extends JFrame{ //새로운 진료튜플 입력창
		
		JTextField txtTrID;
		JTextField txtPaaID;
		JTextField txtDotIC;
		JTextField txtTrCon;
		JTextField txtTrDa;
		
		newwindowInsertTre(){
			
			setTitle("신규 진료 입력창");
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
			
			
			pnINTre.add(new JLabel("진료ID : "));
			pnINTre.add(txtTrID);
			
			pnINTre.add(new JLabel("환자ID : "));
			pnINTre.add(txtPaaID);
			
			pnINTre.add(new JLabel("의사ID : "));
			pnINTre.add(txtDotIC);
			
			pnINTre.add(new JLabel("진료내용 : "));
			pnINTre.add(txtTrCon);
			
			pnINTre.add(new JLabel("진료날짜 : "));
			pnINTre.add(txtTrDa);
			
			btnSubTre = new JButton("제출");
			pnINTre.add(new JLabel(""));
			pnINTre.add(btnSubTre);
			
			btnSubTre.addActionListener(new ActionListenerSubTre());
			
		}
		
		private class ActionListenerSubTre implements ActionListener { // 신규 진료 입력 후 제출버튼 눌렀을때
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
					System.out.println("신규 진료 입력 완료");
					txtResult.setText("신규 진료 입력 완료");
					
					
				}catch (Exception e1) {
					System.out.println("입력실패이유 : " + e1);
				}
			}
		}
	}
	
	
	


	class newwindowInsertCha extends JFrame{ //새로운 차트 입력창
		
		JTextField txtChID;
		JTextField txtChCo;
		JTextField txtTrrId;
		JTextField txtPaaaID;
		JTextField txtDoctorID;
		JTextField txtNurseID;
		
		newwindowInsertCha(){
			
			setTitle("신규 차트 입력창");
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
			
			
			
			pnINCha.add(new JLabel("차트ID : "));
			pnINCha.add(txtChID);
			
			pnINCha.add(new JLabel("차트내용 : "));
			pnINCha.add(txtChCo);
			
			pnINCha.add(new JLabel("진료ID : "));
			pnINCha.add(txtTrrId);
			
			pnINCha.add(new JLabel("환자ID : "));
			pnINCha.add(txtPaaaID);
			
			pnINCha.add(new JLabel("의사ID : "));
			pnINCha.add(txtDoctorID);
			
			pnINCha.add(new JLabel("간호사ID : "));
			pnINCha.add(txtNurseID);
			
			btnSubCha = new JButton("제출");
			pnINCha.add(new JLabel(""));
			pnINCha.add(btnSubCha);
			
			btnSubCha.addActionListener(new ActionListenerSubCha());
			
		}
		
		private class ActionListenerSubCha implements ActionListener { // 새로운 차트 입력 후 제출버튼 눌렀을때
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
					System.out.println("새로운 차트 입력 완료");
					txtResult.setText("새로운 차트 입력 완료");
					
					
				}catch (Exception e1) {
					System.out.println("입력실패이유 : " + e1);
				}
			}
		}
	}
	
	
	
	
	
	public void conDB() { //데이터베이스 연결 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			      
		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
		  
	public static void main(String[] args) { //메인
		HospitalAdministration HA = new HospitalAdministration();
		
		
		HA.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					con.close();
				} catch (Exception e4) { 	}
				System.out.println("프로그램 완전 종료!");    		
				System.exit(0);
			}
		});
		
	}
		
}
