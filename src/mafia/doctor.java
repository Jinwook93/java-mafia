package mafia;

public class doctor extends citizen {

	
	
	doctor(){				
		super();
		super.isDoctor=1;	
	}
	

	doctor(String name){
		
		super(name,0);
//		this.name=name;
//		this.hp=0;
	}
	


	doctor(String name,int hp,int isMafia,int isDoctor,int isPolice){
		super(name,100,0,1,0);
//		super.name=name;
//		super.hp=hp;
//		super.isCitizen=0;
//		super.isMafia=0;	
//		super.isDoctor=1;
//		super.isPolice=0;
	}
	
	
	void heal(citizen citizen) {		//의사 고유의 스킬
		citizen.hp=100;
		System.out.println("의사가 마피아로부터 시민의 목숨을 살렸습니다");
	//	System.out.println("의사가 시민 "+citizen.getName()+"을(를) 살렸습니다");    //시민의 정체가 드러나므로 이름을 공백처리했음
		
	}
	
	@Override
	void die() {
		this.hp=0;
		System.out.println("무고한 의사 " +this.getName()+"이(가) 죽었습니다.");
		if(Game.doctorCount<0) {
			Game.doctorCount=0;
		}
		else {
		  Game.doctorCount-=1;
		}
		Game.ghostCount+=1;
	}
	
	
}
