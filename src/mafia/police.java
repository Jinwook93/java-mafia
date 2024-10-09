package mafia;

public class police extends citizen {


	public police() {
		super();
		super.isPolice=1;
		// TODO Auto-generated constructor stub
	}

	

	public police(String name, int hp, int isMafia, int isDoctor, int isPolice) {
		super(name,100,0,0,1);
//		super.name=name;
//		super.hp=hp;
//		super.isMafia=0;	//마피아일시 1로 활성화
//		super.isDoctor=0;
//		super.isPolice=1;
	}
	

	public police(String name, int hp) {
		super(name, 100, 0, 0,1);
	}

	public police(String name) {
		super(name);
	}
	
	void kill_police(citizen k) {		//경찰 고유의 스킬
		System.out.println("경찰이 마피아를 찾았습니다");
		((mafia)k).die();
	}
	
	
	void isMafia(citizen k) {		//경찰 고유의 스킬
	
		if(k.hp==100&&(k instanceof mafia)) {
			System.out.println(k.name+"는(은) 마피아입니다");
			kill_police(k);
		}
		
		else if(k.hp==100&&(k instanceof doctor)) {
			System.out.println(k.name+"는(은) 의사입니다");
		}	
		else {
		System.out.println(k.name+"는(은) 시민입니다");
	}
	
		
	}
	

	@Override
	void die() {
		this.hp=0;
		System.out.println("무고한 경찰 " +this.getName()+"이(가) 죽었습니다.");
		
		if(Game.policeCount<0) {
			Game.policeCount=0;
		}
		else {
			Game.policeCount-=1;
		
		}
		Game.ghostCount+=1;
	}
	
	
}
