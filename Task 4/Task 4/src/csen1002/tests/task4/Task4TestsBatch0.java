package csen1002.tests.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task4.CfgEpsUnitElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task4TestsBatch0 {

	@Test
	public void testCfgEpsilonRuleElimination0() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;A;B;C#a;b;c;d;x#S/aAb,ab,x,xB;A/Bc,C,c,d;B/A,AA,AC,ACA,C,CA,CAA,CAC,CACA,CC,CCA;C/A,b", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;H;R;V;N;G#b;u;v;z#S/V,uNbSu,vNz;H/R,VS,e,uSV;R/SuNv,Sz,e,v,vSb;V/H,b,vGSbS;N/H,S,e,zHH,zHRvV,zNSGH;G/S,uVuS");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;H;R;V;N;G#b;u;v;z#S/V,uNbSu,uNbu,ubSu,ubu,vNz,vz;H/R,S,V,VS,u,uS,uSV,uV;R/SuNv,Suv,Sz,uNv,uv,v,vSb,vb,z;V/H,b,vGSb,vGSbS,vGb,vGbS,vSb,vSbS,vb,vbS;N/H,S,z,zG,zGH,zH,zHH,zHRv,zHRvV,zHv,zHvV,zN,zNG,zNGH,zNH,zNS,zNSG,zNSGH,zNSH,zRv,zRvV,zS,zSG,zSGH,zSH,zv,zvV;G/S,uVu,uVuS,uu,uuS", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;N;O;X;A#l;m;u;w;z#S/AAXz,NlO,OOuNX,SzO;N/S,SAmAu,X,e,lX;O/N,OSO,S,XOlNN,e,zS;X/X,XOuA,e,wS;A/AlS,Oz,uNlAm");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;N;O;X;A#l;m;u;w;z#S/AAXz,AAz,Nl,NlO,OOu,OOuN,OOuNX,OOuX,Ou,OuN,OuNX,OuX,Sz,SzO,l,lO,u,uN,uNX,uX;N/S,SAmAu,X,l,lX;O/N,OS,OSO,Ol,OlN,OlNN,S,SO,XOl,XOlN,XOlNN,Xl,XlN,XlNN,l,lN,lNN,zS;X/OuA,X,XOuA,XuA,uA,wS;A/AlS,Oz,uNlAm,ulAm,z", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;Z;T;K;N;L;W#i;k;p;q;w#S/LZ,ZkKiS,kSS;Z/KLW,N,wKSZK;T/TNZ,p;K/L,WWw,e,pZqWN;N/S,SKk,Z,kZ,p;L/SLL,TKLw,WZWq,e,wZ;W/SKLpW,wTL");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;Z;T;K;N;L;W#i;k;p;q;w#S/LZ,Z,ZkKiS,ZkiS,kSS;Z/KLW,KW,LW,N,W,wKSZ,wKSZK,wSZ,wSZK;T/TNZ,p;K/L,WWw,pZqWN;N/S,SKk,Sk,Z,kZ,p;L/S,SL,SLL,TKLw,TKw,TLw,Tw,WZWq,wZ;W/SKLpW,SKpW,SLpW,SpW,wT,wTL", cfgEpsUnitElim.toString());
	}


	@Test
	public void testCfgUnitRuleElimination0() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,b,c,d,e;B/CACA,e;C/Bc,b,c,d,e", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;V;Y;R;P;N#g;p;q;x;z#S/NRS,S,pSzR,xV;V/P,PYg,VYSx;Y/NNqNY,P,VVYYx,qRxS,qVx;R/P,PPNY,gRN,z;P/NVp,PzVN,RNVp,Y;N/NSp,Y");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;V;Y;R;P;N#g;p;q;x;z#S/NRS,pSzR,xV;V/NNqNY,NVp,PYg,PzVN,RNVp,VVYYx,VYSx,qRxS,qVx;Y/NNqNY,NVp,PzVN,RNVp,VVYYx,qRxS,qVx;R/NNqNY,NVp,PPNY,PzVN,RNVp,VVYYx,gRN,qRxS,qVx,z;P/NNqNY,NVp,PzVN,RNVp,VVYYx,qRxS,qVx;N/NNqNY,NSp,NVp,PzVN,RNVp,VVYYx,qRxS,qVx", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;X;P;C;Q#a;b;k;t#S/CaPP,P,tXSP;X/Pb,QSa,XX;P/CPbPS,P,PQa,Q,S;C/Q,S,b;Q/SXXkC,SaPk,kXSk,t");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;X;P;C;Q#a;b;k;t#S/CPbPS,CaPP,PQa,SXXkC,SaPk,kXSk,t,tXSP;X/Pb,QSa,XX;P/CPbPS,CaPP,PQa,SXXkC,SaPk,kXSk,t,tXSP;C/CPbPS,CaPP,PQa,SXXkC,SaPk,b,kXSk,t,tXSP;Q/SXXkC,SaPk,kXSk,t", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;M;P;G;B;I;X#b;j;o;p#S/oPBbM,pMXBp;M/GjMX,I,oMB;P/B,G,ISIbI,M,XPo,jIbI;G/SSBI,o,pISM;B/GGIS,MMIBb,P,SGjG,j;I/BpPo,M;X/GX,MPP");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;M;P;G;B;I;X#b;j;o;p#S/oPBbM,pMXBp;M/BpPo,GjMX,oMB;P/BpPo,GGIS,GjMX,ISIbI,MMIBb,SGjG,SSBI,XPo,j,jIbI,o,oMB,pISM;G/SSBI,o,pISM;B/BpPo,GGIS,GjMX,ISIbI,MMIBb,SGjG,SSBI,XPo,j,jIbI,o,oMB,pISM;I/BpPo,GjMX,oMB;X/GX,MPP", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;E;Q;Y;I;H;C#a;i;n;x#S/E,HSxI,Q,n;E/C,Q,Y,YQICC,i;Q/SCnYx,i,n,xI;Y/E,QiYCY,SxHS,xHHHQ;I/Q,Y,aCEY,e,xCnYS;H/I,IxSx,aS,e;C/H,Q,aE");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;E;Q;Y;I;H;C#a;i;n;x#S/CnYx,Cnx,HSx,HSxI,Hx,HxI,IxSx,Ixx,QC,QCC,QI,QIC,QICC,Qi,QiC,QiCY,QiY,QiYC,QiYCY,QiYY,SCnYx,SCnx,SnYx,Snx,Sx,SxH,SxHS,SxI,SxS,YQ,YQC,YQCC,YQI,YQIC,YQICC,a,aC,aCE,aCEY,aCY,aE,aEY,aS,aY,i,n,nYx,nx,x,xCn,xCnS,xCnY,xCnYS,xH,xHHHQ,xHHQ,xHQ,xHS,xI,xQ,xS,xSx,xn,xnS,xnY,xnYS,xx;E/CnYx,Cnx,IxSx,Ixx,QC,QCC,QI,QIC,QICC,Qi,QiC,QiCY,QiY,QiYC,QiYCY,QiYY,SCnYx,SCnx,SnYx,Snx,Sx,SxH,SxHS,SxS,YQ,YQC,YQCC,YQI,YQIC,YQICC,a,aC,aCE,aCEY,aCY,aE,aEY,aS,aY,i,n,nYx,nx,x,xCn,xCnS,xCnY,xCnYS,xH,xHHHQ,xHHQ,xHQ,xHS,xI,xQ,xS,xSx,xn,xnS,xnY,xnYS,xx;Q/CnYx,Cnx,SCnYx,SCnx,SnYx,Snx,i,n,nYx,nx,x,xI;Y/CnYx,Cnx,IxSx,Ixx,QC,QCC,QI,QIC,QICC,Qi,QiC,QiCY,QiY,QiYC,QiYCY,QiYY,SCnYx,SCnx,SnYx,Snx,Sx,SxH,SxHS,SxS,YQ,YQC,YQCC,YQI,YQIC,YQICC,a,aC,aCE,aCEY,aCY,aE,aEY,aS,aY,i,n,nYx,nx,x,xCn,xCnS,xCnY,xCnYS,xH,xHHHQ,xHHQ,xHQ,xHS,xI,xQ,xS,xSx,xn,xnS,xnY,xnYS,xx;I/CnYx,Cnx,IxSx,Ixx,QC,QCC,QI,QIC,QICC,Qi,QiC,QiCY,QiY,QiYC,QiYCY,QiYY,SCnYx,SCnx,SnYx,Snx,Sx,SxH,SxHS,SxS,YQ,YQC,YQCC,YQI,YQIC,YQICC,a,aC,aCE,aCEY,aCY,aE,aEY,aS,aY,i,n,nYx,nx,x,xCn,xCnS,xCnY,xCnYS,xH,xHHHQ,xHHQ,xHQ,xHS,xI,xQ,xS,xSx,xn,xnS,xnY,xnYS,xx;H/CnYx,Cnx,IxSx,Ixx,QC,QCC,QI,QIC,QICC,Qi,QiC,QiCY,QiY,QiYC,QiYCY,QiYY,SCnYx,SCnx,SnYx,Snx,Sx,SxH,SxHS,SxS,YQ,YQC,YQCC,YQI,YQIC,YQICC,a,aC,aCE,aCEY,aCY,aE,aEY,aS,aY,i,n,nYx,nx,x,xCn,xCnS,xCnY,xCnYS,xH,xHHHQ,xHHQ,xHQ,xHS,xI,xQ,xS,xSx,xn,xnS,xnY,xnYS,xx;C/CnYx,Cnx,IxSx,Ixx,QC,QCC,QI,QIC,QICC,Qi,QiC,QiCY,QiY,QiYC,QiYCY,QiYY,SCnYx,SCnx,SnYx,Snx,Sx,SxH,SxHS,SxS,YQ,YQC,YQCC,YQI,YQIC,YQICC,a,aC,aCE,aCEY,aCY,aE,aEY,aS,aY,i,n,nYx,nx,x,xCn,xCnS,xCnY,xCnYS,xH,xHHHQ,xHHQ,xHQ,xHS,xI,xQ,xS,xSx,xn,xnS,xnY,xnYS,xx", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;N;G;O;D;X#a;d;w#S/DS,GdOSO,aDd;N/Gw,NGODw,O,S;G/O,dXSO,e,wX;O/NG,NwDd,OSG,SDwO,e;D/G,S,XwOaS;X/SaG,aNNaO,dOD");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;N;G;O;D;X#a;d;w#S/DS,GdOS,GdOSO,GdS,GdSO,aDd,ad,dOS,dOSO,dS,dSO;N/DS,Dw,GDw,GODw,GOw,GdOS,GdOSO,GdS,GdSO,Gw,NDw,NG,NGDw,NGODw,NGOw,NGw,NODw,NOw,Nw,NwDd,Nwd,ODw,OS,OSG,Ow,SDw,SDwO,SG,Sw,SwO,aDd,ad,dOS,dOSO,dS,dSO,dXS,dXSO,w,wDd,wX,wd;G/DS,Dw,GDw,GODw,GOw,GdOS,GdOSO,GdS,GdSO,Gw,NDw,NG,NGDw,NGODw,NGOw,NGw,NODw,NOw,Nw,NwDd,Nwd,ODw,OS,OSG,Ow,SDw,SDwO,SG,Sw,SwO,aDd,ad,dOS,dOSO,dS,dSO,dXS,dXSO,w,wDd,wX,wd;O/DS,Dw,GDw,GODw,GOw,GdOS,GdOSO,GdS,GdSO,Gw,NDw,NG,NGDw,NGODw,NGOw,NGw,NODw,NOw,Nw,NwDd,Nwd,ODw,OS,OSG,Ow,SDw,SDwO,SG,Sw,SwO,aDd,ad,dOS,dOSO,dS,dSO,dXS,dXSO,w,wDd,wX,wd;D/DS,Dw,GDw,GODw,GOw,GdOS,GdOSO,GdS,GdSO,Gw,NDw,NG,NGDw,NGODw,NGOw,NGw,NODw,NOw,Nw,NwDd,Nwd,ODw,OS,OSG,Ow,SDw,SDwO,SG,Sw,SwO,XwOaS,XwaS,aDd,ad,dOS,dOSO,dS,dSO,dXS,dXSO,w,wDd,wX,wd;X/Sa,SaG,aNNa,aNNaO,aNa,aNaO,aa,aaO,d,dD,dO,dOD", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;H;W;J;Q;L#h;q;y#S/Ly,QQhQ,S,W,WWyJ,qJL;H/QLLJW,e,q;W/SSJq,SyJhL,W;J/JHqHJ,JyLW,W,e;Q/Q,S,ShL,Wq,e,qW;L/HJHJy,SS,Sy,yJHyS");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;H;W;J;Q;L#h;q;y#S/Ly,QQh,QQhQ,Qh,QhQ,SSJq,SSq,SyJhL,SyhL,WWy,WWyJ,h,hQ,qJL,qL;H/LLJW,LLW,QLLJW,QLLW,q;W/SSJq,SSq,SyJhL,SyhL;J/Hq,HqH,HqHJ,HqJ,JHq,JHqH,JHqHJ,JHqJ,Jq,JqH,JqHJ,JqJ,JyLW,SSJq,SSq,SyJhL,SyhL,q,qH,qHJ,qJ,yLW;Q/Ly,QQh,QQhQ,Qh,QhQ,SSJq,SSq,ShL,SyJhL,SyhL,WWy,WWyJ,Wq,h,hQ,qJL,qL,qW;L/HHJy,HHy,HJHJy,HJHy,HJJy,HJy,Hy,JHJy,JHy,JJy,Jy,SS,Sy,y,yHyS,yJHyS,yJyS,yyS", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination4() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;W;T;X;F#a;c;d;m;p#S/FcW,XpS,d;W/T,WdX,aW,e,mSWS;T/S,STc,T,X,XmXp,e;X/WWFc,X,mXWT;F/W,p");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;W;T;X;F#a;c;d;m;p#S/Fc,FcW,XpS,c,cW,d;W/Fc,FcW,STc,Sc,WFc,WWFc,WWc,Wc,WdX,XmXp,XpS,a,aW,c,cW,d,dX,mSS,mSWS,mX,mXT,mXW,mXWT;T/Fc,FcW,STc,Sc,WFc,WWFc,WWc,Wc,XmXp,XpS,c,cW,d,mX,mXT,mXW,mXWT;X/Fc,WFc,WWFc,WWc,Wc,c,mX,mXT,mXW,mXWT;F/Fc,FcW,STc,Sc,WFc,WWFc,WWc,Wc,WdX,XmXp,XpS,a,aW,c,cW,d,dX,mSS,mSWS,mX,mXT,mXW,mXWT,p", cfgEpsUnitElim.toString());
	}

}