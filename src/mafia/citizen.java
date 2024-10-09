package mafia;

import java.util.Scanner;



public class citizen {

	String name;			//이름
	int hp;					//체력	
	int isMafia=0;		//직업 식별 변수 , 모두 0일경우 시민으로 간주. hp=0일경우에는 직업 상관없이 유령으로 간주
	int isDoctor=0;
	int isPolice=0;
	ThreadInterrupt th = new ThreadInterrupt();
	String yn;			//발언 선택 변수
	

	@Override
	public String toString() {
		return name + ", hp=" + hp + "";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	citizen(){				//시민의 정보를 가져옴. //파라미터에 따른 오버로딩 사용
		this.name="익명"+String.valueOf(Game.anonymous);
		this.hp=100;
		this.isMafia=0;
		this.isDoctor=0;
		this.isPolice=0;	
	}
	

	citizen(String name){
		
		this(name,100);
//		this.name=name;
//		this.hp=100;
	}
	
	
	citizen(String name,int hp){
		this.name=name;
		this.hp=hp;			
	}
	
	citizen(String name,int hp,int isMafia,int isDoctor,int isPolice){
		this.name=name;
		this.hp=hp;	
		this.isMafia=isMafia;
		this.isDoctor=isDoctor;
		this.isPolice=isPolice;
		
	}
	
	
	int random(int rand,int randnum_val1) {
			
	rand=(int)((Math.random()*randnum_val1)+1);		// 1과 지정값 사이의 범위를 추출한 랜덤함수 추출	
	return rand;

	
	}


	void talk() {
		System.out.println("----------------------발언 선택-----------------------");
		System.out.print(this.getName()+": ");
		String citi_talking="";
		if(hp<=0) {
			System.out.println("!!!!유령은 말할 수 없습니다!!!!!!");
		}
		
		else {
			Scanner sc3=new Scanner(System.in);
			System.out.println("발언를 희망합니까 [희망: Y,y 아님: N,n]  (대소문자,공백 상관안함)");
			yn=sc3.nextLine();
			
			String yn_trim=yn.trim();
							
				switch(yn_trim) {
			
					case "n" :{
					System.out.println("발언를 미희망하셨습니다." );
					break;
					}
					case "y" :{
						
						Scanner sc2=new Scanner(System.in);
						System.out.println("발언를 희망하셨습니다. 발언권은 10초가 주어집니다" );
						th.run();
						while(true) {
							citi_talking=sc2.nextLine();
							
							if(citi_talking.contains("")) {
								th.interrupt();
								break;
							}
							
						}
						th.interrupt();		
						break;
					}
					
					
					case "N" :{
						System.out.println("발언를 미희망하셨습니다." );
						break;
					}
					
					
					case "Y" :{
						
						Scanner sc2=new Scanner(System.in);
						System.out.println("발언를 희망하셨습니다.발언권은 10초가 주어집니다" );
						th.run();
						while(true) {
							
							citi_talking=sc2.nextLine();
							
							if(citi_talking.contains("")) {
								th.interrupt();
								break;
							}
							
						}
						th.interrupt();			
						
						break;
						}
				
					default:
						System.out.println("!!!!!!!!범위값이 아닙니다!!!!!!!!!");
						this.talk();
					}
				
		}
	
	}
	
			void die() {
				this.hp=0;
				System.out.println("무고한 시민 " +this.getName()+"이(가) 죽었습니다.");
				if(Game.citiCount<0) {
					Game.citiCount=0;
				}
				else {
					Game.citiCount-=1; 
				}
				 Game.ghostCount+=1;
			}
	
	
	
	
	}
