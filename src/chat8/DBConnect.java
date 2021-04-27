package chat8;

import java.sql.DriverManager;

import java.sql.Connection;

public class DBConnect {

	public static void main(String[] args) {
		try {
			/*
			JDBC 프로그래밍 절차
			
			1.오라클용 JDBC 드라이버를 메모리에 로드
			: new를 사용하지 않고 클래스명으로 직접 찾아서 객체를 생성한 후
			메모리에 로드하는 forName() 메소드를 사용한다. 메모리에 로드된
			드라이버가 DriverManager라는 클래스에 등록된다.
			 */
			Class.forName("oracle.jdbc.OracleDriver");
			/*
			2.오라클 연결을 위한 커넥션URL, 계정아이디, 패스워드를 준비한다.
			커넥션URL => jdbc:oracle:thin:@IP주소:포드번호:SID명
			 */
			String ur1 = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "kosmo";
			String pass = "1234";
			/*
			3.DriverManager 클래스의 메소드를 통해 오라클에 연결을
			시도한다. 연결에 성공할 경우 연결된 주소를 반환한다.
			 */
			Connection con = DriverManager.getConnection(ur1,id,pass);
			if(con!=null) {
				System.out.println("Oracle 연결성공");
			}
			else {
				System.out.println("Oracle 연결실패");
			}
		}
		catch(Exception e) {
			System.out.println("Oracle 연결시 예외발생");
			e.printStackTrace();
		}
	}
}
