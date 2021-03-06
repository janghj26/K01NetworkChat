package chat8;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Scanner;

//클라이언트가 입력한 메세지를 서버로 전송해주는 쓰레드 클래스
public class Sender extends Thread{

	Socket socket;
	PrintWriter out = null;//output스트림이 필요
	String name;
	
	//클라이언트가 접속시 사용했던 Socket객체를 기반으로 출력스트림 생성
	public Sender(Socket socket, String name) {
		this.socket = socket;
		try {
			
			out = new PrintWriter(this.socket.getOutputStream(), true);
			this.name = name;
		}
		catch(Exception e) {
			System.out.println("예외>sender>생성자:" + e);
		}
	}
	
	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		
		try {
			//최초로 보내는 메세지는 이름(대화명)
			name = URLEncoder.encode(name, "UTF-8");
			out.println(name);
			
			//두번째부터는 q를 입력하기전까지는 입력한 메세지를 서버로 전송한다.
			while(out != null) {
				try {
					String s2 = s.nextLine();
					if(s2.equalsIgnoreCase("Q")) {//대소문자 구분없이 q를 사용할경우 탈출
						break;
					}
					else {
						s2 = URLEncoder.encode(s2, "UTF-8");
						out.println(s2);
					}
				}
				catch(Exception e) {
					System.out.println("예외>Sender>run1:" + e);
				}
			}
			out.close();//스트림종료
			socket.close();//소켓종료
		}
		catch(Exception e) {
			System.out.println("예외>Sender>run2:" + e);
		}
	}
}
