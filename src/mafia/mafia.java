package mafia;

public class mafia extends citizen {

	
	
	mafia(){				//마피아의 정보를 가져옴.
		super();
		super.isMafia=1;
	}
	

	mafia(String name){
		
		super(name,0);
//		this.name=name;
//		this.hp=0;
	}
	
	
	
	mafia(String name,int hp,int isMafia,int isDoctor, int isPolice){
		super(name,100,1,0,0);
//		super.name=name;
//		super.hp=hp;
//		super.isMafia=1;	//마피아일시 1로 활성화
//		super.isDoctor=0;
//		super.isPolice=0;
	}
	
	
	void kill_Mafia(citizen citizen) { //마피아 고유의 기술 시민을 죽임.
		
		if(Game.mafiaCount>0) {
		citizen.hp=0;
		
		if(citizen instanceof doctor) {
			System.out.println("의사 "+citizen.getName()+"이(가) 마피아에 의해 살해되었습니다");	
		}
		else if(citizen instanceof police) {
			System.out.println("경찰 "+citizen.getName()+"이(가) 마피아에 의해 살해되었습니다");	
		}
		else {
		System.out.println("시민 "+citizen.getName()+"이(가) 마피아에 의해 살해되었습니다");
		}
	   }
	}
	
	
	@Override
	void die() {
		this.hp=0;
		System.out.println("마피아가 검거되었습니다.");
		
		if(Game.mafiaCount<0) {
			Game.mafiaCount=0;
		}
		else {
			Game.mafiaCount-=1;
		
		}
		Game.ghostCount+=1;
	}
	
	
	
}
