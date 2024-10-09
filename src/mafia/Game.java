package mafia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
static int mafiaCount;			//마피아의 수
static int ghostCount;			//유령의 수
static int citiCount;				//시민의 수
static int doctorCount;			//의사의 수	(의사도 시민에 포함될 수 있으나, 여기에서는 시민과 의사,경찰은 별개로 지정함)
static int policeCount;			//경찰의 수	(시민의 정체를 확인할 수 있는 기능, 마피아일시 체포 기능 추가.)


static int anonymous;				//이름을 입력하지 않을 경우 익명이름의 수


	public static void main(String[] args) {
		String name="";
		result r1= new result();		//생존자,유령 결과 출력 용도 객체. 싱글톤으로 구현.
		int join;							//참가자 수
		 String randnum_val;             //랜덤함수범위 설정		
		 int randnum_intval;			//String을 int로 변환시킬 변수
		 int samenamecount=0;			//중복된 이름이 있을 경우 식별변수 		
		 
		 
	while(true) {
		
		mafiaCount=0;			//마피아의 수 (초기화 목적)
		ghostCount=0;			//유령의 수 (초기화 목적)
		citiCount=0;				//시민의 수 (초기화 목적)
		doctorCount=0;			//의사의 수 (초기화 목적)
		policeCount=0;			//경찰의 수 (초기화 목적)
		
		anonymous=1;			//익명 이름 수 (익명 존재할 시 해당 값 출력 후 증가 ,초기화 목적)
		samenamecount=0;		//동일 이름 수  (초기화 목적)		
		
		System.out.println("=========================== 마피아 게임 ============================");
		System.out.println();
		System.out.println("==== 게임에 참가할 인원을 입력하세요 (5~10명까지 가능합니다). 게임나가기 '-1' 입력 ====");
		
		Scanner sc= new Scanner(System.in);
		
		String input_join=sc.nextLine();
		
		
		try {
		join=Integer.parseInt(input_join);				//참가자 수
		}
		catch (NumberFormatException e) {
			System.out.println("================= 입력 범위가 아닙니다(숫자만 입력 가능) ===================");
			System.out.println();
			continue;
		}
		
		if(input_join.equals("-1")) {
				System.out.println("게임 종료");
				break;
			}
		
		if (join<5||join>10) {				//참가자 수가 범위에 맞지 않을 경우
			System.out.println("====================== 입력 범위가 아닙니다(5~10명까지 가능) ==============");
			System.out.println();
		}
		
		else {
			
			Scanner sc1= new Scanner(System.in);
			citizen citi[]=new citizen[join];			//사람 생성
			String nametmp[]=new String[join];			//이름 임시 보관. 기존 시민 이름를 의사,마피아,경찰 이름에 덮어씌우는 역할
			
			for(int i=0;i<citi.length;i++) {			
				System.out.println("참가자 이름 입력"+"("+(i+1)+")");
				name=sc1.nextLine();				//참가자 이름
				name=name.trim();
				name=name.replace(" ", "");			//이름이 공백제거
				
				
				if(name=="") {
				System.out.println("!!! 이름이 없어 이름이 '익명"+anonymous+"'으로 처리됩니다 !!!");
				}
				
				citi[i] = new citizen(name,100,0,0,0);
				if (citi[i].name=="") {
					citi[i]=new citizen();
					anonymous++;
				}
				
				nametmp[i]= citi[i].name;
				
			
			}
			
			
			
			for(int i=0;i<citi.length;i++) {				//참가자 이름 중 동일 이름이 존재할 경우
			for(int j=i+1;j<=citi.length-1;j++) {
				if((citi[i].name).equals(citi[j].name)){
					samenamecount++;
					citi[j].name= citi[j].name+"("+ String.valueOf(samenamecount)+")";
					
					nametmp[j]= citi[j].name;
				}
				
			  }
				samenamecount=0;
			}
			
		
			
			Scanner sc_random=new Scanner(System.in);

			
			
			System.out.println();
			System.out.println("===================== 랜덤함수 범위의 최대값을 지정합니다 ======================");
			System.out.println();
			System.out.println("=================== 랜덤함수 범위는 1과 지정한 값 사이를 기준으로 합니다 ================");
			System.out.println();
			System.out.println("========================= 입력값은 4 이상이어야 합니다 =============================");
			System.out.println();
			System.out.println("================ 랜덤함수의 범위가 적을수록 직업이 중복될 확률이 높아집니다 ================");
			System.out.println();
			
			
			while(true) {
			
			System.out.println("======================= 랜덤함수의 최대값 범위를 입력해주세요 =========================");
			randnum_val= sc_random.nextLine();
			
			
			try {
				randnum_intval=Integer.parseInt(randnum_val);				
				}
				catch (NumberFormatException e) {
					System.out.println("!!!!!!!!!!!!!!!!! 값 입력 범위가 아닙니다(숫자만 입력 가능) !!!!!!!!!!!!!!!!!");
					System.out.println();
					continue;
				}
			
			if(randnum_intval<4) {
				System.out.println("!!!!!!!!!!! 값의 범위가 아닙니다. 값을 다시 입력해 주세요 !!!!!!!!!!!!!");
				System.out.println();
				System.out.println("!!!!!!!!!!!!!! 최소 4 이상의 값을 입력해 주세요 !!!!!!!!!!!!!!!!!!!");
				System.out.println();
				continue;
			}
			
			
			break;
			
			}
		
			
			while(true) {   //루프 테스트
						
			System.out.println("======= 참가자에게 임의의 수를 지정합니다. 가장 큰 수가 걸릴 시 마피아로 지정됩니다 ==========");
			int max=citi[0].random(1,randnum_intval);
			int rand[]=new int[citi.length];	
			int min=citi[citi.length-1].random(1,randnum_intval);
			
			for(int i=0;i<citi.length;i++) {
				rand[i]=(int)(citi[i].random(1,randnum_intval));		//어차피 임의의 수 출력이므로, 고정값 1을 넣었음.	
				}
			
		
			for(int i=0;i<rand.length-1;i++) {				//rand[i] 순서 배열
			
				if(rand[i]>=max) {				//max일시 마피아
				max=rand[i];
				}
				
				if(rand[i]<=min&&rand[i]<max) {		//min일시 의사
					min=rand[i];
				}
				
					
			}
												
			int arrtmp=0;							
			int randtmp[]=new int [join];			//랜덤함수값 임시 복사용도 배열
					
			for(int i=0;i<citi.length;i++) {
				
				randtmp[i]=rand[i];
			}
			
			
			for(int i=0;i<citi.length;i++) {			//임시보관배열로 배열의 순서를 지정
				
				for(int j=i+1;j<citi.length;j++) {
					
					if(randtmp[i]<randtmp[j]) {
						arrtmp=randtmp[i];
						randtmp[i]=randtmp[j];
						randtmp[j]=arrtmp;
						
					}
			}
				
				
			
			}
			int randtmp_2ndmax;				//경찰은 2번쨰로 큰 랜덤함수로 추출
			if(randtmp[0]==randtmp[1]) {
				randtmp_2ndmax=randtmp[2]; //1번쨰,2번쨰 둘다 max일 경우 3번째로 큰 랜덤함수를 경찰로 지정
			}
			else {
			randtmp_2ndmax=randtmp[1];		//2번쨰로 큰 랜덤함수를 경찰로 지정
			}
			
			
		
			for(int i=0;i<citi.length;i++) {
				
				
				if(rand[i]==randtmp_2ndmax) {
					citi[i]= new police (nametmp[i],100,0,0,1);			//기존 객체에 의사 생성자 새로 재정의
					policeCount++;
					System.out.println(citi[i].name+":"+randtmp_2ndmax +"<<<경찰");	//!!!!경찰 결과값을 알려주는 """테스트용 출력 코드"""이니 주석처리할것
					
				}
				
				else if(rand[i]<=min) {
					citi[i]= new doctor(nametmp[i],100,0,1,0);			//기존 객체에 의사 생성자 새로 재정의
					doctorCount++;
					System.out.println(citi[i].name+":"+min +"<<<의사");	//!!!!의사 결과값을 알려주는 """테스트용 출력 코드"""이니 주석처리할것
					
				}
				else if(rand[i]<randtmp_2ndmax) {
				citi[i]= new citizen(nametmp[i],100,0,0,0);			//기존 객체에 시민 생성자 새로 재정의
				System.out.println(citi[i].name+":"+rand[i]);		//!!!!!시민 결과값을 알려주는 """테스트용 출력 코드"""이니 주석처리할것
				citiCount++;
			}
				
			
				else {
				citi[i]= new mafia(nametmp[i],100,1,0,0);				//기존 객체에 마피아 생성자 새로 재정의
				mafiaCount++;
				System.out.println(citi[i].name+":"+max +"<<<마피아");		//!!!!마피아 결과값을 알려주는 """테스트용 출력 코드"""이니 주석처리할것
				
				}
				
				
			}
			
			

				r1.who_live_death(citi);

			
		
			
			if(doctorCount==0) {		//
				System.out.println("!!!! 시민을 살릴 의사가 존재하지 않습니다 !!!!");
				System.out.println();
			}  
			

			if(policeCount==0) {		//
				System.out.println("!!!! 마피아를 수색할 경찰이 존재하지 않습니다 !!!!");
				System.out.println();
			 
			}
			
			if(citiCount==0||mafiaCount==0||mafiaCount>=(citiCount+doctorCount+policeCount)) {							
				System.out.println("======= 시민,마피아가 지정되지 않거나 마피아가 시민,의사,경찰의 합보다 많을 경우 다시 지정합니다 =======");
				System.out.println();     //랜덤함수에 마피아,시민이 가끔 지정되지 않는 경우가 있어서 다시 지정하는 기능을 추가하였다.
				citiCount=0;
				mafiaCount=0;
				doctorCount=0;
				ghostCount=0;
				policeCount=0;
				continue;
			}
			
			else 
				break;
			} //루프 끝
			
	
			
			System.out.println("============================ 게임을 시작합니다 ==================================");
			System.out.println();
			System.out.println();
			
			day_night d1 = new day_night();			//낮,밤 출력 객체
			
			System.out.println("==================== "+join+"명이 게임에 참가했습니다 ===========================");

			
//		    -------is변수 활성화 테스트----------
			
//			for(int i=0;i<join;i++) {
//				System.out.println(citi[i].name+"은 마피아입니까? =>"+citi[i].isMafia);
//			System.out.println(citi[i].name+"은 경찰입니까? =>"+citi[i].isPolice);
//			System.out.println(citi[i].name+"은 의사입니까? =>"+citi[i].isDoctor);
//			System.out.println("");
//			}
			
			
			
		while(true) {
				
			d1.day();					//낮 출력
		
			if(doctorCount<=0) {
				System.out.println("!!!! 시민을 살릴 의사가 존재하지 않습니다 !!!!!");
			}
			if(policeCount<=0) {
				System.out.println("!!!! 마피아를 수색할 경찰이 존재하지 않습니다 !!!!!");
			}
			
			for(citizen i:citi) {			
				i.talk();				// 채팅기능으로 누가 마피아인지를 추리하는 과정. 동시 채팅은 구현하기 어려워서 한명씩 대화하는 방향으로 구현하였다.
			}
			
			Scanner sc3=new Scanner(System.in); 
			int target=0;			//임시보관 변수
			String who_kill="";
			String vote[]=new String[join];
			int vote_count[]=new int[join];  //시민이 투표한 수		
			int vote_max=0;			//최다 득표
			
			System.out.println();
			System.out.println("------------------ 투표 --------------------");
			System.out.println("대화를 마치고 시민들이 투표를 진행합니다");
			

			while(true) {
			
			
				for(int k=0;k<citi.length;k++) { //추가
				
				
					if(citi[k].hp==0) {
						System.out.println("(시민) ("+citi[k].name+")누굴 죽일 건가요");	
						System.out.println("!!!유령은 투표할 수 없습니다 !!!");
						System.out.println();
						continue;
					}
			while(true)	{	//추가
			System.out.println("(시민) ("+citi[k].name+")누굴 죽일 건가요");	
			int citi_who_count=0;
			who_kill=sc3.nextLine();
			
			
			for(int i=0;i<citi.length;i++) {		//유효성검사확인			
				  if(!who_kill.equals(citi[i].name)) {
					  citi_who_count++;
				  }	  
		      }
			//System.out.println("citi_who_count"+citi_who_count); //유효성검사확인
			  if(who_kill.equals("")||(citi_who_count==citi.length)) {    //유효성검사확인
				System.out.println("!!! 이름을 다시 입력하세요 !!!!");
				continue;
			   }
			  else if(who_kill.equals(citi[k].name)) {
				  System.out.println("!!!!!!!! 시민은 자기 자신을 죽일 수 없습니다 !!!!!!!!!");
				  System.out.println();
					continue;
				  
			  }
			  
		 
		
								
			for(int i=0;i<citi.length;i++) {		//배열 위치 검색
				if(who_kill.equals(citi[i].name)) {
					target=i;
					vote_count[target]+=1;
				}
			}
			
			if(citi[target].hp==0) {
				System.out.println("!!! 시민은 유령를 죽일 수 없습니다 !!!");
				 System.out.println();
				continue;
				}		
			else {			
	
				vote[k]=citi[target].name;		
				if(citi[k].hp==0) {
					System.out.println("유령은 투표할 수 없습니다");
					System.out.println();
					continue;
				}
				else if(vote[k]!=null) {
				System.out.println(citi[k].name+"은(는) "+vote[k]+"에게 투표하였습니다");
				System.out.println();
				System.out.println("******************** "+vote[k]+"의 득표현황 *************************");
				System.out.println(vote[k] +":"+(vote_count[target])+"표");
				System.out.println("***************************************************");
				System.out.println();
				}
			
				break;		
			}	//추가
			}  //누굴 죽일건지 개각각의 while 탈출
			

				}//for문 시민 1명당 투표구문 끝
			
				int maxCount=0;			//최다득표자 중복을 확인 하는 변수
				
				System.out.println("---------------------- 득표 결과 -----------------------");
				int target_max=0;	//배열위치
			for(int i=0;i<citi.length;i++) {
				System.out.println(citi[i].name+":"+vote_count[i]+"표");
				if(vote_count[i]>=vote_max) {		
					vote_max=vote_count[i];			//최대득표수
					target_max=i;
				}
	
			  }
			
			for(int i=0;i<citi.length;i++) {
				if(vote_max==vote_count[i]) {
				maxCount++;
			}
				
				if(maxCount>1) {
					System.out.println();
					System.out.println("------------ 최다 득표자를 추출할 수 없어서 다시 투표를 진행합니다 ------------");		
					break;
				}
			}
			
			if(maxCount>1) {
				 for(int v=0;v<vote_count.length;v++) {		//변수값 초기화, 재투표 진행
					 vote_count[v]=0;
					 vote_max=0;	
					 maxCount=0;
				 }
			continue;
			}
			else {
			System.out.println("최다 득표자:"+citi[target_max].name);
			System.out.println("시민 "+citi[target_max].name+"이(가) 투표에 의해 죽게 됩니다" );
			if(citi[target_max].isMafia==1&& who_kill.equals(citi[target_max].name)) {
				((mafia) citi[target_max]).die();
				  break;
				}
			else {
				((citizen)citi[target_max]).die();	 
				 break;
			}
			}
				}  //시민투표 끝
			
			
			
			
			
				
			if (mafiaCount>=(citiCount+doctorCount+policeCount)) {
				r1.win_lose(citiCount, doctorCount,mafiaCount,ghostCount,policeCount);
				r1.endResult(citi);
				System.out.println("게임을 종료합니다");
				break;	
			}
			else if(mafiaCount==0||(citiCount==0&&doctorCount==0&&mafiaCount==1&&policeCount==1)) {
				r1.win_lose(citiCount, doctorCount,mafiaCount,ghostCount,policeCount);
				r1.endResult(citi);
				System.out.println("게임을 종료합니다");
				break;
			}
			else if(citiCount==0&&doctorCount==0&policeCount==0) {
				r1.win_lose(citiCount, doctorCount,mafiaCount,ghostCount,policeCount);
				r1.endResult(citi);
				System.out.println("게임을 종료합니다");
				break;
			}
			else {
				r1.who_live_death(citi);
				
			}
				
				
				
				
			
	//	}
			d1.night();						//밤
			
			if(doctorCount<=0) {
				System.out.println("!!!! 시민을 살릴 의사가 존재하지 않습니다 !!!!!");
			}
					
			String who_kill_mafia;
			int target_mafia=0;
			String whoisMafia="";
			int target_police=0;
			int police_who_count;
			
			Scanner sc4=new Scanner(System.in);			//마피아가 죽일 시민
			Scanner sc5=new Scanner(System.in);			//의사가 살릴 시민
			Scanner sc6=new Scanner(System.in);			//경찰이 확인할 시민
			
			if(policeCount<=0) {
				System.out.println("!!! 마피아를 수색할 경찰이 존재하지 않습니다 !!!");
				}
			else {	
			System.out.println("경찰이 의심이 되는 시민의 신분을 확인합니다");
			System.out.println("신분은 하루 기준 1회씩 확인 가능하며, 시민이 경찰, 유령일 경우에는 확인할 기회가 다시 주어집니다");
			
			 while(true) {
			police_who_count=0;
			System.out.println("(경찰) 시민의 이름을 입력하세요");
			whoisMafia=sc6.nextLine();
				
			for(int i=0;i<citi.length;i++) {			//객체 배열 citi를 하나하나 조회해서 who_kill과 같은 값을 가지는지 확인을 한다.
				  if(!whoisMafia.equals(nametmp[i])) {	//같은 값을 가지지 않을 경우 mafia_count를 하나씩 증가하도록 했는데
					  police_who_count++;				//만약 police_who_count가 객체 배열 길이와 같다면 그 외의 값을 입력했다는 의미가 된다.
				  }	  
		      }
		//	System.out.println("police-_who-count"+mafia_who_count);   유효성 검사
			if(whoisMafia.equals("")||police_who_count==citi.length) {
				System.out.println("!!! 이름을 다시 입력하세요 !!!!");
				continue;
			}
			else { 		
			for(int i=0;i<citi.length;i++) {			//시민 배열 위치 검색
				if(whoisMafia.equals(citi[i].name)) {
					target_police=i;					
				}	
			}
			
			
			if(citi[target_police].hp==0) {
				System.out.println("!!!!! 경찰은 유령을 수색할 수 없습니다 !!!!!");
				System.out.println(citi[target_police].name+"은(는) 유령입니다");
				System.out.println();
				continue;
				}
				
			else if(citi[target_police] instanceof police) {
				System.out.println("!!!!! 경찰은 같은 경찰을 수색할 수 없습니다 !!!!!");
				System.out.println(citi[target_police].name+"은(는) 경찰입니다");
				System.out.println();
				continue;
				}
	
			else {
				for(citizen k:citi){
					if(k instanceof police) {			//수색
								((police)k).isMafia(citi[target_police]);
								System.out.println();
								break;
							}
				    	} //for문
			    	}		//else문
			
			}   //else문
			
			 			break;
			
			
		} //while 끝
			 	
			}	//else끝
			
			
			if(mafiaCount<=0) {
				if (mafiaCount>=(citiCount+doctorCount+policeCount)||(mafiaCount==0)
						||(citiCount==0&&doctorCount==0&&policeCount==0)
						||(citiCount==0&&doctorCount==0&&mafiaCount==1&&policeCount==1)) {
					r1.win_lose(citiCount, doctorCount,mafiaCount,ghostCount,policeCount);
					r1.endResult(citi);
					System.out.println("게임을 종료합니다");
					break;	
				}
				
				else {
					r1.who_live_death(citi);
					
				}	
				
			}
				
		else {	
			
			System.out.println("-----------------------------");
			
			System.out.println("마피아가 죽일 시민을 고르고 있습니다");
			
				
			
			while(true) {	
			System.out.println("(마피아) 누굴 죽일 건가요");
			int mafia_who_count =0;						//죽일 사람 이외의 값을 입력할 경우의 횟수에 대한 변수를 만들없다. 
			who_kill_mafia=sc4.nextLine();
			int target_doctor=0;
			
			
			for(int i=0;i<citi.length;i++) {			//객체 배열 citi를 하나하나 조회해서 who_kill과 같은 값을 가지는지 확인을 한다.
				  if(!who_kill_mafia.equals(nametmp[i])) {	//같은 값을 가지지 않을 경우 mafia_count를 하나씩 증가하도록 했는데
					  mafia_who_count++;				//만약 mafia_count가 객체 배열 길이와 같다면 그 외의 값을 입력했다는 의미가 된다.
				  }	  
		      }
		//	System.out.println("mafia-_who-count"+mafia_who_count);   유효성 검사
			if(who_kill_mafia.equals("")||mafia_who_count==citi.length) {
				System.out.println("!!!! 이름을 다시 입력하세요 !!!!");
				continue;
			}
							
			for(int i=0;i<citi.length;i++) {			//마피아 배열 위치 검색
				if(who_kill_mafia.equals(citi[i].name)) {
					target_mafia=i;	
							
				}
			}
					
			if(citi[target_mafia].hp==0) {
				System.out.println("!!!! 마피아는 유령을 죽일 수 없습니다 !!!!!");
				System.out.println(citi[target_mafia].name+"은(는) 유령입니다");
				continue;
				}
			
			else if(citi[target_mafia].isMafia==1) {
				System.out.println("!!!! 마피아는 같은 마피아를 죽일 수 없습니다 !!!!!");
				System.out.println(citi[target_mafia].name+"은(는) 마피아입니다");
				continue;
				}
			
			else if (citi[target_mafia].isMafia==0&& who_kill_mafia.equals(citi[target_mafia].name)) {
						//마피아가 지목한 사람이 시민일 경우
				if(doctorCount>0) {   //랜덤함수 추출값에서 의사가 존재할 경우
					while(true) {
					String who_heal_doctor;
				
				
			       System.out.println("(의사) 누굴 살릴 건가요 ");
			       int doctor_who_count=0;				//유효값인지 검사하는 변수
			       who_heal_doctor=sc5.nextLine();
				
				for(int i=0;i<citi.length;i++) {					
					  if(!who_heal_doctor.equals(nametmp[i])) {
						  doctor_who_count++;
						  
					  }	
					  else {
						  target_doctor=i;
					  }
			      }
			//	System.out.println("who_healdoctor"+doctor_who_count);
				if(who_heal_doctor.equals("")||doctor_who_count==citi.length) {
					System.out.println("!!! 이름을 다시 입력하세요 !!!!");
					continue;
				}	
				
				else if(citi[target_doctor].hp==0) {
					System.out.println("!!! 유령은 살릴 수 없습니다 !!!!");
					System.out.println(citi[target_doctor].name+"은(는) 유령입니다");
					continue;
				}
				
					
				if(who_heal_doctor.equals(citi[target_mafia].name)) {
						//마피아가 지목한 사람과 의사가 지목한 사람이 일치할 경우 시민은 회복
					
					for(citizen k:citi) {	
					  if(k instanceof doctor) {
					    ((doctor)k).heal(citi[target_mafia]);
					    break;
					   }
					}
					System.out.println(" 아무도 죽지 않았습니다 ");
						break;
				}
				else {  //마피아가 지목한 사람과 의사가 지목한 사람이 일치하지 않을 경우 시민은 죽음
				 citi[target_mafia].die();
				   for(citizen k:citi) {	
					  if(k instanceof mafia) {
					    ((mafia)k).kill_Mafia(citi[target_mafia]);
					    break;
					   }
					}
				 break;
			
				}
				}  //while문
				}//if 문
				
				else {
					System.out.println("!!!! 시민을 살릴 의사가 존재하지 않습니다 !!!!!");
					citi[target_mafia].die();	
					for(citizen k:citi) {	
						  if(k instanceof mafia) {
						    ((mafia)k).kill_Mafia(citi[target_mafia]);
						    break;
						   }
						}
					 break;
				}
				break;
			}	//else if 끝
				
				
			//break;
				
	
			}// 마피아 while문 끝
		} //마피아 if문
		
		
		
	   //결과 출력

	
		
				if (mafiaCount>=(citiCount+doctorCount+policeCount)||(mafiaCount==0)
						||(citiCount==0&&doctorCount==0&&policeCount==0)
						||(citiCount==0&&doctorCount==0&&mafiaCount==1&&policeCount==1)) {
					r1.win_lose(citiCount, doctorCount,mafiaCount,ghostCount,policeCount);
					r1.endResult(citi);
					System.out.println(" 게임을 종료합니다 ");
					break;	
				}
				
				else {
					r1.who_live_death(citi);
					
				}	
				
			
	
				
			}//while 루프 반복
	
			continue;
	
		}	
			

	}
	
	}
	
}
