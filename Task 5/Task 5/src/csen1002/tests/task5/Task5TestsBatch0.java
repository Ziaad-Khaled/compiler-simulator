package csen1002.tests.task5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task5.CfgLeftRecElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task5TestsBatch0 {


	@Test
	public void testCfgLeftRecursionElimination0() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;T;L#a;b;c;d;i#S/ScT,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;T;L;S';L'#a;b;c;d;i#S/LaS',TiS',bS';T/aSb,LabS,i;L/aSbiS'dLL',iiS'dLL',bS'dLL',aSbiS'iL',iiS'iL',bS'iL';S'/cTS',e;L'/aS'dLL',abSiS'dLL',aS'iL',abSiS'iL',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination1() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;D;G;L;T;R#b;f;i;y;z#S/DyRTT,TDRS,TzLR,f;D/TSTDG,fGTT;G/GSDLb,GyLR,fDf,yLT;L/DGDL,GSbS,LLf,LS,f;T/LLSy,RbGG,TfTTi,bSLL;R/DD,DLTLi,fTDfL");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;D;G;L;T;R;G';L';T';R'#b;f;i;y;z#S/DyRTT,TDRS,TzLR,f;D/TSTDG,fGTT;G/fDfG',yLTG';L/TSTDGGDLL',fGTTGDLL',fDfG'SbSL',yLTG'SbSL',fL';T/fGTTGDLL'LSyT',fDfG'SbSL'LSyT',yLTG'SbSL'LSyT',fL'LSyT',RbGGT',bSLLT';R/fGTTGDLL'LSyT'STDGDR',fDfG'SbSL'LSyT'STDGDR',yLTG'SbSL'LSyT'STDGDR',fL'LSyT'STDGDR',bSLLT'STDGDR',fGTTDR',fGTTGDLL'LSyT'STDGLTLiR',fDfG'SbSL'LSyT'STDGLTLiR',yLTG'SbSL'LSyT'STDGLTLiR',fL'LSyT'STDGLTLiR',bSLLT'STDGLTLiR',fGTTLTLiR',fTDfLR';G'/SDLbG',yLRG',e;L'/LfL',SL',e;T'/STDGGDLL'LSyT',fTTiT',e;R'/bGGT'STDGDR',bGGT'STDGLTLiR',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination2() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;D;R;E;B;F#l;o;v;y;z#S/BFoRo,RB,RSvFR,SlFz;D/Sl,lFv,oRzF,yR,z,zEFvS;R/RlR,RoRlS,SBSz,SRRyB,l,zEEF;E/EBD,EDoF,lRS;B/Dy,oBlE,oDE,v;F/BS,Bz,vRRR");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;D;R;E;B;F;S';R';E';B'#l;o;v;y;z#S/BFoRoS',RBS',RSvFRS';D/BFoRoS'l,RBS'l,RSvFRS'l,lFv,oRzF,yR,z,zEFvS;R/BFoRoS'BSzR',BFoRoS'RRyBR',lR',zEEFR';E/lRSE';B/lR'BS'lyB',zEEFR'BS'lyB',lR'SvFRS'lyB',zEEFR'SvFRS'lyB',lFvyB',oRzFyB',yRyB',zyB',zEFvSyB',oBlEB',oDEB',vB';F/lR'BS'lyB'S,zEEFR'BS'lyB'S,lR'SvFRS'lyB'S,zEEFR'SvFRS'lyB'S,lFvyB'S,oRzFyB'S,yRyB'S,zyB'S,zEFvSyB'S,oBlEB'S,oDEB'S,vB'S,lR'BS'lyB'z,zEEFR'BS'lyB'z,lR'SvFRS'lyB'z,zEEFR'SvFRS'lyB'z,lFvyB'z,oRzFyB'z,yRyB'z,zyB'z,zEFvSyB'z,oBlEB'z,oDEB'z,vB'z,vRRR;S'/lFzS',e;R'/lRR',oRlSR',BS'BSzR',SvFRS'BSzR',BS'RRyBR',SvFRS'RRyBR',e;E'/BDE',DoFE',e;B'/FoRoS'lyB',FoRoS'BSzR'BS'lyB',FoRoS'RRyBR'BS'lyB',FoRoS'BSzR'SvFRS'lyB',FoRoS'RRyBR'SvFRS'lyB',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination3() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;R;W;A;V#l;n;t;x;z#S/RzA,SVR,StSS,lRnA,t;R/AnVV,SASRz,SWV,l,lA,lASAz;W/RSVlS,RtRSx,WWSV,Wn,t,tVAlR;A/RV,Rz,lV;V/ARA,VlVlS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;R;W;A;V;S';R';W';A';V'#l;n;t;x;z#S/RzAS',lRnAS',tS';R/AnVVR',lRnAS'ASRzR',tS'ASRzR',lRnAS'WVR',tS'WVR',lR',lAR',lASAzR';W/AnVVR'SVlSW',lRnAS'ASRzR'SVlSW',tS'ASRzR'SVlSW',lRnAS'WVR'SVlSW',tS'WVR'SVlSW',lR'SVlSW',lAR'SVlSW',lASAzR'SVlSW',AnVVR'tRSxW',lRnAS'ASRzR'tRSxW',tS'ASRzR'tRSxW',lRnAS'WVR'tRSxW',tS'WVR'tRSxW',lR'tRSxW',lAR'tRSxW',lASAzR'tRSxW',tW',tVAlRW';A/lRnAS'ASRzR'VA',tS'ASRzR'VA',lRnAS'WVR'VA',tS'WVR'VA',lR'VA',lAR'VA',lASAzR'VA',lRnAS'ASRzR'zA',tS'ASRzR'zA',lRnAS'WVR'zA',tS'WVR'zA',lR'zA',lAR'zA',lASAzR'zA',lVA';V/lRnAS'ASRzR'VA'RAV',tS'ASRzR'VA'RAV',lRnAS'WVR'VA'RAV',tS'WVR'VA'RAV',lR'VA'RAV',lAR'VA'RAV',lASAzR'VA'RAV',lRnAS'ASRzR'zA'RAV',tS'ASRzR'zA'RAV',lRnAS'WVR'zA'RAV',tS'WVR'zA'RAV',lR'zA'RAV',lAR'zA'RAV',lASAzR'zA'RAV',lVA'RAV';S'/VRS',tSSS',e;R'/zAS'ASRzR',zAS'WVR',e;W'/WSVW',nW',e;A'/nVVR'VA',nVVR'zA',e;V'/lVlSV',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination4() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;U;F;E;L;W;V#b;j;m;r#S/SVr,Sr,Um,jFU,jV,jWm;U/SVmUb,bUVUE;F/FE,Fb,bV,bW,j;E/b,m;L/WLEE,mWWEj,rWU;W/EErF,EFLWE,jWjF,mSmV,mV,mVWSj;V/EW,FUWV,SLm,UVW,WVEjL,m");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;U;F;E;L;W;V;S';U';F'#b;j;m;r#S/UmS',jFUS',jVS',jWmS';U/jFUS'VmUbU',jVS'VmUbU',jWmS'VmUbU',bUVUEU';F/bVF',bWF',jF';E/b,m;L/WLEE,mWWEj,rWU;W/bErF,mErF,bFLWE,mFLWE,jWjF,mSmV,mV,mVWSj;V/bW,mW,bVF'UWV,bWF'UWV,jF'UWV,jFUS'VmUbU'mS'Lm,jVS'VmUbU'mS'Lm,jWmS'VmUbU'mS'Lm,bUVUEU'mS'Lm,jFUS'Lm,jVS'Lm,jWmS'Lm,jFUS'VmUbU'VW,jVS'VmUbU'VW,jWmS'VmUbU'VW,bUVUEU'VW,bErFVEjL,mErFVEjL,bFLWEVEjL,mFLWEVEjL,jWjFVEjL,mSmVVEjL,mVVEjL,mVWSjVEjL,m;S'/VrS',rS',e;U'/mS'VmUbU',e;F'/EF',bF',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination5() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;F;U;B;X;P#b;l;p#S/BP,BpUSP,SPb,lBBbB;F/FSS,FUbB,SFPp,lXbUX;U/pSb,pXBB;B/SPB,SS,bUp;X/FPU,FlSFp,PFSp,UlBB,XP,XPPBS;P/FPPbS,UbXSB");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;F;U;B;X;P;S';F';B';X'#b;l;p#S/BPS',BpUSPS',lBBbBS';F/BPS'FPpF',BpUSPS'FPpF',lBBbBS'FPpF',lXbUXF';U/pSb,pXBB;B/lBBbBS'PBB',lBBbBS'SB',bUpB';X/lBBbBS'PBB'PS'FPpF'PUX',lBBbBS'SB'PS'FPpF'PUX',bUpB'PS'FPpF'PUX',lBBbBS'PBB'pUSPS'FPpF'PUX',lBBbBS'SB'pUSPS'FPpF'PUX',bUpB'pUSPS'FPpF'PUX',lBBbBS'FPpF'PUX',lXbUXF'PUX',lBBbBS'PBB'PS'FPpF'lSFpX',lBBbBS'SB'PS'FPpF'lSFpX',bUpB'PS'FPpF'lSFpX',lBBbBS'PBB'pUSPS'FPpF'lSFpX',lBBbBS'SB'pUSPS'FPpF'lSFpX',bUpB'pUSPS'FPpF'lSFpX',lBBbBS'FPpF'lSFpX',lXbUXF'lSFpX',PFSpX',pSblBBX',pXBBlBBX';P/lBBbBS'PBB'PS'FPpF'PPbS,lBBbBS'SB'PS'FPpF'PPbS,bUpB'PS'FPpF'PPbS,lBBbBS'PBB'pUSPS'FPpF'PPbS,lBBbBS'SB'pUSPS'FPpF'PPbS,bUpB'pUSPS'FPpF'PPbS,lBBbBS'FPpF'PPbS,lXbUXF'PPbS,pSbbXSB,pXBBbXSB;S'/PbS',e;F'/SSF',UbBF',e;B'/PS'PBB',pUSPS'PBB',PS'SB',pUSPS'SB',e;X'/PX',PPBSX',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination6() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;L;Q;K;X;G;R#b;d;l;n#S/GSG,dSdQX;L/b,lGG;Q/GlS,QRnX,QXdGb,RlLbX,SXb,SnLR;K/KG,Kn,lGnX,nG;X/LK,QdXS,SbXRl,nR;G/QnRX,lK;R/LKXK,Xd,dG,lXR");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;L;Q;K;X;G;R;Q';K';G';R'#b;d;l;n#S/GSG,dSdQX;L/b,lGG;Q/GlSQ',RlLbXQ',GSGXbQ',dSdQXXbQ',GSGnLRQ',dSdQXnLRQ';K/lGnXK',nGK';X/bK,lGGK,GlSQ'dXS,RlLbXQ'dXS,GSGXbQ'dXS,dSdQXXbQ'dXS,GSGnLRQ'dXS,dSdQXnLRQ'dXS,GSGbXRl,dSdQXbXRl,nR;G/RlLbXQ'nRXG',dSdQXXbQ'nRXG',dSdQXnLRQ'nRXG',lKG';R/bKXKR',lGGKXKR',bKdR',lGGKdR',dSdQXXbQ'nRXG'lSQ'dXSdR',dSdQXnLRQ'nRXG'lSQ'dXSdR',lKG'lSQ'dXSdR',dSdQXXbQ'nRXG'SGXbQ'dXSdR',dSdQXnLRQ'nRXG'SGXbQ'dXSdR',lKG'SGXbQ'dXSdR',dSdQXXbQ'dXSdR',dSdQXXbQ'nRXG'SGnLRQ'dXSdR',dSdQXnLRQ'nRXG'SGnLRQ'dXSdR',lKG'SGnLRQ'dXSdR',dSdQXnLRQ'dXSdR',dSdQXXbQ'nRXG'SGbXRldR',dSdQXnLRQ'nRXG'SGbXRldR',lKG'SGbXRldR',dSdQXbXRldR',nRdR',dGR',lXRR';Q'/RnXQ',XdGbQ',e;K'/GK',nK',e;G'/lSQ'nRXG',SGXbQ'nRXG',SGnLRQ'nRXG',e;R'/lLbXQ'nRXG'lSQ'dXSdR',lLbXQ'dXSdR',lLbXQ'nRXG'SGXbQ'dXSdR',lLbXQ'nRXG'SGnLRQ'dXSdR',lLbXQ'nRXG'SGbXRldR',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination7() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;J;E;L;R#b;d;f;g;w#S/EELJ,SERJS,SLEEL,SLS,SRwR,dJE;J/EJf,gEg,gLE,wSJL;E/EdLbS,EwS,RfJE,SS;L/EL,JdE,Sd,dRJL,dRgRw;R/EgEE,JdRdJ,LREL,LS,w,wEJE");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;J;E;L;R;S';E';R'#b;d;f;g;w#S/EELJS',dJES';J/EJf,gEg,gLE,wSJL;E/RfJEE',dJES'SE';L/RfJEE'L,dJES'SE'L,RfJEE'JfdE,dJES'SE'JfdE,gEgdE,gLEdE,wSJLdE,RfJEE'ELJS'd,dJES'SE'ELJS'd,dJES'd,dRJL,dRgRw;R/dJES'SE'gEER',dJES'SE'JfdRdJR',gEgdRdJR',gLEdRdJR',wSJLdRdJR',dJES'SE'LRELR',dJES'SE'JfdERELR',gEgdERELR',gLEdERELR',wSJLdERELR',dJES'SE'ELJS'dRELR',dJES'dRELR',dRJLRELR',dRgRwRELR',dJES'SE'LSR',dJES'SE'JfdESR',gEgdESR',gLEdESR',wSJLdESR',dJES'SE'ELJS'dSR',dJES'dSR',dRJLSR',dRgRwSR',wR',wEJER';S'/ERJSS',LEELS',LSS',RwRS',e;E'/dLbSE',wSE',ELJS'SE',e;R'/fJEE'gEER',fJEE'JfdRdJR',fJEE'LRELR',fJEE'JfdERELR',fJEE'ELJS'dRELR',fJEE'LSR',fJEE'JfdESR',fJEE'ELJS'dSR',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination8() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;B;X;R;D#d;q;s#S/DX,DdDSq,dS,sBR;B/BSsXS,RdBD,sR;X/BdX,SqS,XS,XdSsB;R/SsBBX,dRSR,dXRsD,s;D/DRB,DqDdR,RDXs,RsS,XR,qDRD");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;B;X;R;D;B';X';D'#d;q;s#S/DX,DdDSq,dS,sBR;B/RdBDB',sRB';X/RdBDB'dXX',sRB'dXX',DXqSX',DdDSqqSX',dSqSX',sBRqSX';R/DXsBBX,DdDSqsBBX,dSsBBX,sBRsBBX,dRSR,dXRsD,s;D/dSsBBXDXsD',sBRsBBXDXsD',dRSRDXsD',dXRsDDXsD',sDXsD',dSsBBXsSD',sBRsBBXsSD',dRSRsSD',dXRsDsSD',ssSD',dSsBBXdBDB'dXX'RD',sBRsBBXdBDB'dXX'RD',dRSRdBDB'dXX'RD',dXRsDdBDB'dXX'RD',sdBDB'dXX'RD',sRB'dXX'RD',dSqSX'RD',sBRqSX'RD',qDRDD';B'/SsXSB',e;X'/SX',dSsBX',e;D'/RBD',qDdRD',XsBBXDXsD',dDSqsBBXDXsD',XsBBXsSD',dDSqsBBXsSD',XsBBXdBDB'dXX'RD',dDSqsBBXdBDB'dXX'RD',XqSX'RD',dDSqqSX'RD',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination9() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;K;R;B;D#j;n;q;r#S/RB,RjKS,n;K/BDqS,SKB,SRqS,rRBK;R/DDSBB,DKj,nKRq,nRSRD,qD,qRDrS;B/BBnK,BDrK,DnDD,Rn,SnD,r;D/DDSD,DnB");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;K;R;B;D;B';D'#j;n;q;r#S/RB,RjKS,n;K/BDqS,RBKB,RjKSKB,nKB,RBRqS,RjKSRqS,nRqS,rRBK;R/DDSBB,DKj,nKRq,nRSRD,qD,qRDrS;B/DnDDB',DDSBBnB',DKjnB',nKRqnB',nRSRDnB',qDnB',qRDrSnB',DDSBBBnDB',DKjBnDB',nKRqBnDB',nRSRDBnDB',qDBnDB',qRDrSBnDB',DDSBBjKSnDB',DKjjKSnDB',nKRqjKSnDB',nRSRDjKSnDB',qDjKSnDB',qRDrSjKSnDB',nnDB',rB';D/;B'/BnKB',DrKB',e;D'/DSDD',nBD',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination10() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;J;A;G;T#b;i;k;s#S/ASiJ,SA,ST,bJ;J/GsJGb,JsT,SGA,Sb,bAs,kA;A/AS,AbJ,kJGS;G/Ak,bSA,iJsAS;T/JS,JTTb,SAk,i,sJbSb");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;J;A;G;T;S';J';A'#b;i;k;s#S/ASiJS',bJS';J/GsJGbJ',ASiJS'GAJ',bJS'GAJ',ASiJS'bJ',bJS'bJ',bAsJ',kAJ';A/kJGSA';G/kJGSA'k,bSA,iJsAS;T/kJGSA'ksJGbJ'S,bSAsJGbJ'S,iJsASsJGbJ'S,kJGSA'SiJS'GAJ'S,bJS'GAJ'S,kJGSA'SiJS'bJ'S,bJS'bJ'S,bAsJ'S,kAJ'S,kJGSA'ksJGbJ'TTb,bSAsJGbJ'TTb,iJsASsJGbJ'TTb,kJGSA'SiJS'GAJ'TTb,bJS'GAJ'TTb,kJGSA'SiJS'bJ'TTb,bJS'bJ'TTb,bAsJ'TTb,kAJ'TTb,kJGSA'SiJS'Ak,bJS'Ak,i,sJbSb;S'/AS',TS',e;J'/sTJ',e;A'/SA',bJA',e", cfgLeftRecElim.toString());
	}

}