package teluguNLG.phrase;

import java.util.*;
import java.util.regex.Pattern;

/*
 * Servlet imports are kept here for reference from the old web version.
 *
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
*/

public class TeluguUnicode
{
 String output="";
 //File file;
 //PrintWriter out;
/*public TeluguUnicode()
{
 try{
 //file=new File("telugu.txt");
//out=new BufferedWriter(new FileWriter(file));
}catch(IOException ioe)
{
ioe.printStackTrace();
}

}*/

 public String convert(String wxSentence)
{
 output="";
 if(wxSentence == null)
 return "";
 return setOutput(wxSentence);
}

 public  String getUnicode(String unicode)
{
 
HashMap<String,String> unicodeValue=new HashMap<String,String>();
 /**************symbol mappings***********************/

	unicodeValue.put(";",";");	//;
	unicodeValue.put(":",":");	//:
	unicodeValue.put("\"","34");	//
	unicodeValue.put("'","39");	//'
	unicodeValue.put(",","44");	//,
	unicodeValue.put(".","46");	//.
	unicodeValue.put("/","47");	///.
	unicodeValue.put("?","63");	//?
	unicodeValue.put("<","<");	//<
	unicodeValue.put(">",">");	//>
	unicodeValue.put("[","91");	//[
	unicodeValue.put("]","93");	//]
	unicodeValue.put("{","123");	//{
	unicodeValue.put("}","125");	//}
	
	unicodeValue.put("(","40");	//(
	unicodeValue.put(")","41");	//)
	unicodeValue.put("@","64");	//@
	unicodeValue.put("#","35");	//#
	
	unicodeValue.put("^","^");	//^
	unicodeValue.put("*","42");	//*
	
	unicodeValue.put("=","61");	//=
	unicodeValue.put("+","43");	//+
	unicodeValue.put("|","|");	//|
	unicodeValue.put("~","~");	//|
	unicodeValue.put("`","`");	//|
	unicodeValue.put("\\","\\");	//|


	/***************numbers mapping ***********************/

	unicodeValue.put("0","\u0C66");	//0
	unicodeValue.put("1","\u0C67");	//1
	unicodeValue.put("2","\u0C68");	//2
	unicodeValue.put("3","\u0C69");	//3
	unicodeValue.put("4","\u0C6A");	//4
	unicodeValue.put("5","\u0C6B");	//5
	unicodeValue.put("6","\u0C6C");	//6
	unicodeValue.put("7","\u0C6D");	//7
	unicodeValue.put("8","\u0C6E");	//8
	unicodeValue.put("9","\u0C6F");	//9

	/*****************a-z mappings***************************/

	unicodeValue.put("a","\u0C05");	//a
	unicodeValue.put("b","\u0C2C");	//b
	unicodeValue.put("c","\u0C1A");	//c
	unicodeValue.put("d","\u0C21");	//d
	unicodeValue.put("e","\u0C47");	//e
	unicodeValue.put("f","\u0C91");	//f
	unicodeValue.put("g","\u0C17");	//g
	unicodeValue.put("h","\u0C39");	//h
	unicodeValue.put("i","\u0C3F");	//i
	unicodeValue.put("j","\u0C1C");	//j
	unicodeValue.put("k","\u0C15");	//k
	unicodeValue.put("l","\u0C32");	//l
	unicodeValue.put("m","\u0C2E");	//m
	unicodeValue.put("n","\u0C28");	//n
	unicodeValue.put("o","\u0C4B");	//o
	unicodeValue.put("p","\u0C2A");	//p
	unicodeValue.put("q","\u0C43");	//q
	unicodeValue.put("r","\u0C30");	//r
	unicodeValue.put("s","\u0C38");	//s
	unicodeValue.put("t","\u0C1F");	//t
	unicodeValue.put("u","\u0C41");	//u
	unicodeValue.put("v","\u0C35");	//v
	unicodeValue.put("w","\u0C24");	//w
	unicodeValue.put("x","\u0C26");	//x
	unicodeValue.put("y","\u0C2F");	//y
	unicodeValue.put("z","\u0C01");	//z
        unicodeValue.put("_","\u0C4D");

	/*******************A-Z mappings*****************************/

	unicodeValue.put("A","\u0C3E");	//A
	unicodeValue.put("B","\u0C2D");	//B
	unicodeValue.put("C","\u0C1D");	//C
	unicodeValue.put("D","\u0C22");	//D
	unicodeValue.put("E","\u0C48");	//E
	unicodeValue.put("F","\u0C1E");	//F
	unicodeValue.put("G","\u0C18");	//G
	unicodeValue.put("H","\u0C03");	//H
	unicodeValue.put("I","\u0C40");	//I
	unicodeValue.put("J","\u0C1D");	//J
	unicodeValue.put("K","\u0C16");	//K
	
	unicodeValue.put("M","\u0C02");	//M
	unicodeValue.put("N","\u0C23");	//N
	unicodeValue.put("O","\u0C4C");	//O
	unicodeValue.put("P","\u0C2B");	//P
	unicodeValue.put("Q","\u0C44");	//Q
	unicodeValue.put("R","\u0C37");	//R
	unicodeValue.put("S","\u0C36");	//S
	unicodeValue.put("T","\u0C20");	//T
	unicodeValue.put("U","\u0C42");	//U
	
	unicodeValue.put("W","\u0C25");	//W
	unicodeValue.put("X","\u0C27");	//X
	

	/**************Two Character length words************/

	unicodeValue.put("aA","\u0C06");
	unicodeValue.put("ai","\u0C07");
	unicodeValue.put("aI","\u0C08");
	unicodeValue.put("au","\u0C09");
	unicodeValue.put("aU","\u0C0A");
	unicodeValue.put("aq","\u0C0B");
	unicodeValue.put("lq","\u0C0C");
	unicodeValue.put("ae","\u0C0F");
	unicodeValue.put("aE","\u0C10");
	unicodeValue.put("ao","\u0C13");
	unicodeValue.put("aO","\u0C14");
	

	unicodeValue.put("eV","\u0C46");
	
	unicodeValue.put("oV","\u0C4A");
        unicodeValue.put("lY","\u0C33");

	/***********Three Character length words**************/
	
	unicodeValue.put("aeV","\u0C0E");
	unicodeValue.put("aoV","\u0C12");

  String textUnicode=unicodeValue.get(unicode);
     return textUnicode;

}

 public String setOutput(String inputString)
{
 String singleLetChar="",twoLetChar="",singleNextChar="",twoNextChar="",threeLetChar="";
boolean z=true;
for(int i=0;i<inputString.length();i++)
{
 boolean x=true,y=true;
 
 singleLetChar=inputString.substring(i,i+1);
//System.out.println(singleLetChar+"first");
if(i<inputString.length()-1 || singleLetChar.equals(" "))

{
 if(inputString.length()-i>=2)
{
 singleNextChar=inputString.substring(i+1,i+2);
twoLetChar=inputString.substring(i,i+2);
//System.out.println(twoLetChar+"two letters");
}
if(inputString.length()-i>=4)
twoNextChar=inputString.substring(i+2,i+4);
if(inputString.length()-i>=3)
threeLetChar=inputString.substring(i,i+3);
//System.out.println(threeLetChar+"three letters");




if( threeLetChar.equals("aeV") || threeLetChar.equals("aoV"))
{
 if(i<inputString.length()-2)
{
 threeLetChar=getUnicode(threeLetChar);
  output=output+threeLetChar;
 //out.write(threeLetChar);

 i=i+2;
threeLetChar="";
continue;
}

}
  
 if(twoLetChar.equals("lY") || twoLetChar.equals("eV") || twoLetChar.equals("aA") || twoLetChar.equals("ai") || twoLetChar.equals("aI") || twoLetChar.equals("au") || twoLetChar.equals("aU") || twoLetChar.equals("aq") || twoLetChar.equals("lq") || twoLetChar.equals("ae") || twoLetChar.equals("aE") || twoLetChar.equals("ao") || twoLetChar.equals("aO") || twoLetChar.equals("oV"))
 {
  y=false;
if(twoLetChar.equals(twoNextChar) && i<inputString.length()-4)
{
 twoLetChar=getUnicode(twoLetChar);
output=output+twoLetChar;
// out.write(twoLetChar);
 singleLetChar=getUnicode("_");
  //out.write(singleLetChar);
 output=output+singleLetChar;
 twoLetChar=getUnicode(twoNextChar);
 //out.write(twoLetChar);
output=output+twoLetChar;
i=i+3;
x=false;
twoLetChar="";
twoNextChar="";
continue;
}
else
{
twoLetChar=getUnicode(twoLetChar);
//out.write(twoLetChar);
output=output+twoLetChar;
 i=i+1;
twoLetChar="";
continue;
}
}
//System.out.println(singleLetChar+"hello");
if(singleLetChar.equals(" ")) 
{
 //out.write(" ");
output=output+" ";
z=false;
continue;

}
 if(Pattern.matches("[^aAiIuUeEoOMV ]",singleLetChar) && Pattern.matches("[^aAiIuUeEoOMV ]",singleNextChar) && i!=0 && y==true)
{
  singleLetChar=getUnicode(singleLetChar);
  //out.write(singleLetChar);
  output=output+singleLetChar;
  singleLetChar=getUnicode("_");
  //out.write(singleLetChar);
  output=output+singleLetChar;
  singleLetChar=getUnicode(singleNextChar);
  //System.out.println(singleLetChar);
 // out.write(singleLetChar);
 output=output+singleLetChar;
  x=false;
  i=i+1;

singleNextChar="";
continue;
}



 
if(z==true && singleLetChar.equals("a") && i!=0) 
{
 
continue;

}


}
 if(x==true) 
{
 //System.out.println(singleLetChar+"hai");
 singleLetChar=getUnicode(singleLetChar);
//out.write(singleLetChar);
output=output+singleLetChar;
//System.out.println(singleLetChar);
singleLetChar="";
z=true;
}



}
 //out.close();
String convertedOutput=output;
output="";
return convertedOutput;
}


/* public void printOutput()
{


try
{ 
Runtime runtime=Runtime.getRuntime();
 
if(System.getProperty("os.name").equals("Linux"))
{
String[] cmd={"gedit","telugu.txt"};
Process process=runtime.exec(cmd);
}
else
{
String[] cmd={"notepad","telugu.txt"};
Process process=runtime.exec(cmd);
}

}catch(IOException ioe)
{
ioe.printStackTrace();
}
}*/
/*
 * Old servlet entry point. The plain Java utility now uses convert(String).
 *
public void service(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException
{
 System.out.println("in telugu unicode");
output=req.getParameter("wxSentence");
output=setOutput(output);
out=res.getWriter();
out.println(output);
 

}
*/

}

