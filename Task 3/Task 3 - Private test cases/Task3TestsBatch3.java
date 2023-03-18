package csen1002.tests.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task3.FallbackDfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task3TestsBatch3 {

	@Test
	public void testFallbackDfa1String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#h;p;u;w;x#0,h,4;0,p,3;0,u,9;0,w,7;0,x,6;1,h,5;1,p,8;1,u,2;1,w,13;1,x,4;2,h,9;2,p,9;2,u,5;2,w,4;2,x,5;3,h,0;3,p,12;3,u,2;3,w,3;3,x,10;4,h,2;4,p,11;4,u,10;4,w,13;4,x,5;5,h,1;5,p,7;5,u,3;5,w,10;5,x,10;6,h,10;6,p,4;6,u,7;6,w,10;6,x,5;7,h,12;7,p,6;7,u,6;7,w,1;7,x,12;8,h,7;8,p,7;8,u,0;8,w,12;8,x,12;9,h,8;9,p,11;9,u,9;9,w,6;9,x,5;10,h,4;10,p,5;10,u,8;10,w,11;10,x,1;11,h,1;11,p,10;11,u,8;11,w,0;11,x,13;12,h,0;12,p,2;12,u,13;12,w,12;12,x,2;13,h,7;13,p,0;13,u,12;13,w,3;13,x,11#4#8;11");
		assertEquals("uuuhxxhp,11;ppxp,8;p,11;p,11;p,11", fallbackDfa.run("uuuhxxhpppxpppp"));
	}

	@Test
	public void testFallbackDfa1String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#h;p;u;w;x#0,h,4;0,p,3;0,u,9;0,w,7;0,x,6;1,h,5;1,p,8;1,u,2;1,w,13;1,x,4;2,h,9;2,p,9;2,u,5;2,w,4;2,x,5;3,h,0;3,p,12;3,u,2;3,w,3;3,x,10;4,h,2;4,p,11;4,u,10;4,w,13;4,x,5;5,h,1;5,p,7;5,u,3;5,w,10;5,x,10;6,h,10;6,p,4;6,u,7;6,w,10;6,x,5;7,h,12;7,p,6;7,u,6;7,w,1;7,x,12;8,h,7;8,p,7;8,u,0;8,w,12;8,x,12;9,h,8;9,p,11;9,u,9;9,w,6;9,x,5;10,h,4;10,p,5;10,u,8;10,w,11;10,x,1;11,h,1;11,p,10;11,u,8;11,w,0;11,x,13;12,h,0;12,p,2;12,u,13;12,w,12;12,x,2;13,h,7;13,p,0;13,u,12;13,w,3;13,x,11#4#8;11");
		assertEquals("wwhpwhxhhwuhxuppwx,11;p,11;hhh,8;u,10", fallbackDfa.run("wwhpwhxhhwuhxuppwxphhhu"));
	}

	@Test
	public void testFallbackDfa1String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#h;p;u;w;x#0,h,4;0,p,3;0,u,9;0,w,7;0,x,6;1,h,5;1,p,8;1,u,2;1,w,13;1,x,4;2,h,9;2,p,9;2,u,5;2,w,4;2,x,5;3,h,0;3,p,12;3,u,2;3,w,3;3,x,10;4,h,2;4,p,11;4,u,10;4,w,13;4,x,5;5,h,1;5,p,7;5,u,3;5,w,10;5,x,10;6,h,10;6,p,4;6,u,7;6,w,10;6,x,5;7,h,12;7,p,6;7,u,6;7,w,1;7,x,12;8,h,7;8,p,7;8,u,0;8,w,12;8,x,12;9,h,8;9,p,11;9,u,9;9,w,6;9,x,5;10,h,4;10,p,5;10,u,8;10,w,11;10,x,1;11,h,1;11,p,10;11,u,8;11,w,0;11,x,13;12,h,0;12,p,2;12,u,13;12,w,12;12,x,2;13,h,7;13,p,0;13,u,12;13,w,3;13,x,11#4#8;11");
		assertEquals("hphuhuu,8;pu,8;wx,11;wwp,12", fallbackDfa.run("hphuhuupuwxwwp"));
	}

	@Test
	public void testFallbackDfa1String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#h;p;u;w;x#0,h,4;0,p,3;0,u,9;0,w,7;0,x,6;1,h,5;1,p,8;1,u,2;1,w,13;1,x,4;2,h,9;2,p,9;2,u,5;2,w,4;2,x,5;3,h,0;3,p,12;3,u,2;3,w,3;3,x,10;4,h,2;4,p,11;4,u,10;4,w,13;4,x,5;5,h,1;5,p,7;5,u,3;5,w,10;5,x,10;6,h,10;6,p,4;6,u,7;6,w,10;6,x,5;7,h,12;7,p,6;7,u,6;7,w,1;7,x,12;8,h,7;8,p,7;8,u,0;8,w,12;8,x,12;9,h,8;9,p,11;9,u,9;9,w,6;9,x,5;10,h,4;10,p,5;10,u,8;10,w,11;10,x,1;11,h,1;11,p,10;11,u,8;11,w,0;11,x,13;12,h,0;12,p,2;12,u,13;12,w,12;12,x,2;13,h,7;13,p,0;13,u,12;13,w,3;13,x,11#4#8;11");
		assertEquals("xwhwppxwpxp,8;uw,11;xwu,8;w,13", fallbackDfa.run("xwhwppxwpxpuwxwuw"));
	}

	@Test
	public void testFallbackDfa1String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13#h;p;u;w;x#0,h,4;0,p,3;0,u,9;0,w,7;0,x,6;1,h,5;1,p,8;1,u,2;1,w,13;1,x,4;2,h,9;2,p,9;2,u,5;2,w,4;2,x,5;3,h,0;3,p,12;3,u,2;3,w,3;3,x,10;4,h,2;4,p,11;4,u,10;4,w,13;4,x,5;5,h,1;5,p,7;5,u,3;5,w,10;5,x,10;6,h,10;6,p,4;6,u,7;6,w,10;6,x,5;7,h,12;7,p,6;7,u,6;7,w,1;7,x,12;8,h,7;8,p,7;8,u,0;8,w,12;8,x,12;9,h,8;9,p,11;9,u,9;9,w,6;9,x,5;10,h,4;10,p,5;10,u,8;10,w,11;10,x,1;11,h,1;11,p,10;11,u,8;11,w,0;11,x,13;12,h,0;12,p,2;12,u,13;12,w,12;12,x,2;13,h,7;13,p,0;13,u,12;13,w,3;13,x,11#4#8;11");
		assertEquals("huppww,11;xhp,8;uwu,8;w,13", fallbackDfa.run("huppwwxhpuwuw"));
	}

	@Test
	public void testFallbackDfa2String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#b;o;w;y#0,b,11;0,o,5;0,w,10;0,y,9;1,b,5;1,o,11;1,w,4;1,y,3;2,b,1;2,o,4;2,w,3;2,y,10;3,b,11;3,o,3;3,w,1;3,y,10;4,b,8;4,o,6;4,w,2;4,y,5;5,b,3;5,o,1;5,w,2;5,y,3;6,b,5;6,o,11;6,w,9;6,y,9;7,b,2;7,o,7;7,w,12;7,y,0;8,b,2;8,o,9;8,w,12;8,y,10;9,b,7;9,o,4;9,w,1;9,y,11;10,b,9;10,o,6;10,w,3;10,y,6;11,b,8;11,o,1;11,w,7;11,y,12;12,b,4;12,o,11;12,w,4;12,y,8#9#4;8;11");
		assertEquals("oyoo,11;o,4;y,11;w,1", fallbackDfa.run("oyoooyw"));
	}

	@Test
	public void testFallbackDfa2String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#b;o;w;y#0,b,11;0,o,5;0,w,10;0,y,9;1,b,5;1,o,11;1,w,4;1,y,3;2,b,1;2,o,4;2,w,3;2,y,10;3,b,11;3,o,3;3,w,1;3,y,10;4,b,8;4,o,6;4,w,2;4,y,5;5,b,3;5,o,1;5,w,2;5,y,3;6,b,5;6,o,11;6,w,9;6,y,9;7,b,2;7,o,7;7,w,12;7,y,0;8,b,2;8,o,9;8,w,12;8,y,10;9,b,7;9,o,4;9,w,1;9,y,11;10,b,9;10,o,6;10,w,3;10,y,6;11,b,8;11,o,1;11,w,7;11,y,12;12,b,4;12,o,11;12,w,4;12,y,8#9#4;8;11");
		assertEquals("ywwyoy,11;oboyyoyb,4;ywwoyob,8;y,11", fallbackDfa.run("ywwyoyoboyyoybywwoyoby"));
	}

	@Test
	public void testFallbackDfa2String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#b;o;w;y#0,b,11;0,o,5;0,w,10;0,y,9;1,b,5;1,o,11;1,w,4;1,y,3;2,b,1;2,o,4;2,w,3;2,y,10;3,b,11;3,o,3;3,w,1;3,y,10;4,b,8;4,o,6;4,w,2;4,y,5;5,b,3;5,o,1;5,w,2;5,y,3;6,b,5;6,o,11;6,w,9;6,y,9;7,b,2;7,o,7;7,w,12;7,y,0;8,b,2;8,o,9;8,w,12;8,y,10;9,b,7;9,o,4;9,w,1;9,y,11;10,b,9;10,o,6;10,w,3;10,y,6;11,b,8;11,o,1;11,w,7;11,y,12;12,b,4;12,o,11;12,w,4;12,y,8#9#4;8;11");
		assertEquals("owbwooyw,4;y,11;obwbb,8;ob,8;b,7", fallbackDfa.run("owbwooywyobwbbobb"));
	}

	@Test
	public void testFallbackDfa2String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#b;o;w;y#0,b,11;0,o,5;0,w,10;0,y,9;1,b,5;1,o,11;1,w,4;1,y,3;2,b,1;2,o,4;2,w,3;2,y,10;3,b,11;3,o,3;3,w,1;3,y,10;4,b,8;4,o,6;4,w,2;4,y,5;5,b,3;5,o,1;5,w,2;5,y,3;6,b,5;6,o,11;6,w,9;6,y,9;7,b,2;7,o,7;7,w,12;7,y,0;8,b,2;8,o,9;8,w,12;8,y,10;9,b,7;9,o,4;9,w,1;9,y,11;10,b,9;10,o,6;10,w,3;10,y,6;11,b,8;11,o,1;11,w,7;11,y,12;12,b,4;12,o,11;12,w,4;12,y,8#9#4;8;11");
		assertEquals("bbowbo,11;wbwo,4;o,4;y,11", fallbackDfa.run("bbowbowbwooy"));
	}

	@Test
	public void testFallbackDfa2String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#b;o;w;y#0,b,11;0,o,5;0,w,10;0,y,9;1,b,5;1,o,11;1,w,4;1,y,3;2,b,1;2,o,4;2,w,3;2,y,10;3,b,11;3,o,3;3,w,1;3,y,10;4,b,8;4,o,6;4,w,2;4,y,5;5,b,3;5,o,1;5,w,2;5,y,3;6,b,5;6,o,11;6,w,9;6,y,9;7,b,2;7,o,7;7,w,12;7,y,0;8,b,2;8,o,9;8,w,12;8,y,10;9,b,7;9,o,4;9,w,1;9,y,11;10,b,9;10,o,6;10,w,3;10,y,6;11,b,8;11,o,1;11,w,7;11,y,12;12,b,4;12,o,11;12,w,4;12,y,8#9#4;8;11");
		assertEquals("oowwobobwybo,4;y,11;ob,8;by,0", fallbackDfa.run("oowwobobwyboyobby"));
	}

}