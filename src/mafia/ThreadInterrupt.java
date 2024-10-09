package mafia;

public class ThreadInterrupt extends Thread{
	

	public void run() {
		
		try {
			for(int i=10;i>=0;i--) {
				Thread.sleep(1000);
				if(i==0) {
					System.out.println("***!!!발언 시간 : "+(i)+"초 남았습니다***!!!!");
					System.out.println("발언 시간이 종료 되었습니다");
					break;
				}
				else if (i<=5) {	
				System.out.println("***!!!발언 시간 : "+(i)+"초 남았습니다***!!!");
				}
			}
			}catch(InterruptedException e) {
			}
			
		
		
			}
		
	}

