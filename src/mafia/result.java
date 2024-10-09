package mafia;

public class result {

	
	private static result results;
	
	
	static result getInstance() {				//싱글톤 사용
		
		if(results == null) {
			
			result results = new result();
		}
		return results;
			
	}
	
	static void freeInstance() {
		
		results = null;
	}
		
	static void who_live_death(citizen citi[]) {
		System.out.println("=========================== 참가자 명단 ============================");
		for(citizen k:citi) {
			if(k.hp==0&&(k instanceof police)) {
				System.out.println("참가자 이름:"+ k  +" >>> 유령(경찰)");
			}
			else if(k.hp==0&&(k instanceof mafia)) {
				System.out.println("참가자 이름:"+ k  +" >>> 유령(마피아)");
			}
			else if(k.hp==0&&(k instanceof doctor)) {
				System.out.println("참가자 이름:"+ k  +" >>> 유령(의사)");
			}
			
			else if(k.hp==0) {
				System.out.println("참가자 이름:" + k+"  >>> 유령(시민)");
			}
			
			else {	
				System.out.println("참가자 이름:"  + k);
				}
		}
		System.out.println("시민의 수 : "+Game.citiCount);
		System.out.println("마피아의 수 : "+Game.mafiaCount);
		System.out.println("의사의 수 : "+Game.doctorCount);
		System.out.println("경찰의 수 : "+Game.policeCount);
		System.out.println("유령의 수 : "+Game.ghostCount);
	
		
	}
	
	
	 static void endResult(citizen citi[]) {				//게임 결과
		System.out.println("=========================== 게임 결과 ============================");
		for(citizen k:citi) {
			
			if(k.hp==0) {
			
					if(k instanceof police) {
						System.out.println("참가자 이름:"+ k  +" >>> 유령(경찰)");
					}
					else if(k instanceof mafia) {
						System.out.println("참가자 이름:"+ k  +" >>> 유령(마피아)");
					}
					else if(k instanceof doctor) {
						System.out.println("참가자 이름:"+ k  +" >>> 유령(의사)");
					}
					
					else {
						System.out.println("참가자 이름:" + k+"  >>> 유령(시민)");
					}
			  }
			
			
			else {	//hp =100
				
				if(k instanceof police) {
					System.out.println("참가자 이름:"+ k  +" >>> 경찰");
				}
				else if(k instanceof mafia) {
					System.out.println("참가자 이름:"+ k  +" >>> 마피아");
				}
				else if(k instanceof doctor) {
					System.out.println("참가자 이름:"+ k  +" >>> 의사");
				}
				
				else {
					System.out.println("참가자 이름:" + k+"  >>> 시민");
				}	
				
			}
		}
		System.out.println("시민의 수 : "+Game.citiCount);
		System.out.println("마피아의 수 : "+Game.mafiaCount);
		System.out.println("의사의 수 : "+Game.doctorCount);
		System.out.println("경찰의 수 : "+Game.policeCount);
		System.out.println("유령의 수 : "+Game.ghostCount);
	
		
	}
	
	
	
	
	
	
	
	static void win_lose(int citiCount, int doctorCount,int mafiaCount,int ghostCount,int policeCount) {
		
		
				if(citiCount==0&&doctorCount==0&&mafiaCount==1&&policeCount==1) {
					System.out.println("!!!경찰의 수가 마피아의 수와 같습니다!!!");//경찰1명,마피아1명만 생존한경우
					System.out.println("!!!마피아는 경찰에 대응할 수단이 없습니다!!!");
					System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ 시민 승리 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
						//2번째 조건, 마피아가 시민,의사,경찰의 합보다 같을 경우의 조건과 겹치므로 우선순위로 따로 만들었다.
					
					System.out.println();
				}
				
				else if (mafiaCount>=(citiCount+doctorCount+policeCount)) {
					System.out.println("!!!마피아가 시민,의사,경찰의 합보다 같거나 많습니다!!!!");
					System.out.println("!!!시민이 마피아에 대응할 수단이 없습니다!!!");
					System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ 마피아 승리 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
		
				//	System.out.println("게임을 종료합니다");
					System.out.println();
				}
				
				else if(mafiaCount==0) {
					System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ 시민 승리 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
				//	System.out.println("게임을 종료합니다");
					
					
				}
				else if(citiCount==0&&doctorCount==0&&policeCount==0) {
					System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆ 마피아 승리 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
				//	System.out.println("게임을 종료합니다");
					System.out.println();
					
				}
	}
		
	
	
}
