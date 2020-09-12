/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalcompilerproject;


import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;



public class FinalCompilerProject {
    
    
public static Queue<Token>lookahead = new LinkedList();
    

   

   
public static String logicalOp[]= new String[]{"!","==",">","<",">=","<=","||","&&","|","&"};
public static String operators[] =new String[]{"+","*","/","-","%","=","++","--","-=","--","+="};
public  static String op[]=new String[]{"{","}",";",",","(",")","<<",">>",":"};
    
    
  public static  boolean contrain ( String s[],char ch, int size)
{
	String c =Character.toString(ch);
	
	String search=c;
	for ( int i=0;i<size;i++)
		if(s[i].equals(search))
			return true;
	return false;
}
    
    
    
    

    
public static boolean ischar(char c)
{
    return (c>=65 && c<=90) || (c>=97 && c<=122);

}              
    
 public static boolean keycheck(String s)  
{
     String keyword[] ={"int","main()","float","if","double","for","static","else","auto","break","case","char","const","continue","void","while","volatile",
						"unsigned","union","typedef","switch","struct","static","sizeof","signed","short","return","long"};
     int d=0;
     if(!(s.charAt(0)>=97 && s.charAt(0)<=122))
                     return false;
      for(int i=0;i<28;i++)
      {
             if(s.equals(keyword[i]))
              {     
                     d = 1;
                     break;
              }
      }
      
      return d==1;
      
  
} 

 
 
 
 
 
 public static boolean isnums(String str)
{
    int flag=0;
    for(int i = 0;i<str.length();i++)
    {
         if(!isnum(str.charAt(i)))
         {
                           if(str.charAt(i) != 46) 
                           {
                                     flag=1;
                                     break;
                           }
         }
    }
    
   if(flag == 1)
            return false;
    else
            return true;
}
 
 
 
 public static boolean isnum(char c)
{
    
    
    if(c>=48 && c<=57)
             return true;
    else
             return false;


}
    
    
    
  public static  int isidentifier(String s)
{
    int f =0;
     for(int i=1;i<s.length();i++)
     {
      if(!ischar(s.charAt(i)))
      {
       if(!isnum(s.charAt(i)))
       {
        if(s.charAt(i) != 95)
        {
           if(s.charAt(i) == 91)
           {
                     i++;
                     for(;s.charAt(i)!= 93;i++)
                     {
                      if(!isnum(s.charAt(i)))
                      {
                      f =1;   
		      break;
		  }
                     }
           }
           else{
           f = 1;
           }
           if(f ==1)
                   break;
		}} } }
      
   return f;
}
  
  
  
 public static String opcheck(String s)
{
    
     int f1=0,f2=0,f=0;
    for(int i=0;i<9;i++)
    { 
		if(s.equals(op[i]))
            {  f = 1;
              break; }
    }
    if(f == 1)
    { 
		if(s.equals(op[0])||s.equals(op[1]))
			return "prace Op";
		if(s.equals(op[2]))
			return "semicolone";
		if(s.equals(op[3]))
			return "common";
		if(s.equals(op[4]))
			return "left paran";
		if(s.equals(op[5]))
			return "right paran";
		if(s.equals(op[6]))
			return "out operator";
		if(s.equals(op[7]))
			return "in operator";
		if(s.equals(op[8]))
			return "colone Op";
	
	}
    else
    { for(int i=0;i<10;i++)
         {       
             if(s.equals(logicalOp[i]))
             {
                    f1 = 1;
                    break;
             }
         }
         if(f1 == 1)
         {return "logicalOperator"; }
   else
         {
          for(int i=0;i<11;i++)
          {       
             if(s.equals(operators[i]) )
             {
                    f2 =1;
                    break;  }
          }
          
          if(f2 == 1)  return "mathOperator";
          else  return "error";
     }
    }
    return null;
    
}
    
    
    
    
    
public static  int lexical() throws IOException 
{ 
    

    File ifs=new File("test-in.txt");
   
   
    
    int q=0,f=0;
    boolean check;
    String str="";
    String strch;
    String strline="";
    

    
  
    System.out.println("Token\t\t\tToken Type\t\t\tstart\t\tlength\t\t\tLine");
 
    System.out.println("----------------------------------------------------------------------------------------------------------------");
    
    
    Scanner in=new Scanner(ifs);

    while(in.hasNextLine())
    {
        
        
     strline=in.nextLine();
    
      
        
    q++;
 
	int start;
    strline = strline + " ";

    strline = strline + " ";
	
    for(int j=0;j<strline.length();j++)
    {       
            if(strline.charAt(j) ==' ' || strline.charAt(j)=='\t'||strline.charAt(j)=='\"')
            {     
		 if(strline.charAt(j)=='\"')
	            {
			  str+=strline.charAt(j++);
			 while(strline.charAt(j++)!='\"')
	                  str+=strline.charAt(j);
                         
                         
                            
	             System.out.println(str+"\t\t\tstring\t\t\t" + (int)(Math.abs((int)str.length()-j)+1) + "\t\t\t" + str.length() +"\t\t\t" + q + "\n" );		 
	             str="";
		     Token t=new Token(str);
		     t.line=q;

		      lookahead.add(t);
                                  
                                  
                                 

					  }
                     if(!str.equals(""))
                     {
					  

					 
                      if(ischar(str.charAt(0)))
                      {
                                    check = keycheck(str);
                                    
                                    if(check)
                                    {
                                        
                                  
                                    System.out.println(str+"\t\t\tkey word\t\t\t"+(int)(Math.abs((int)str.length()-j)+1)+"\t\t\t"+str.length()+"\t\t\t"+q+"\n");
                                    Token t=new Token(str);
				    t.line=q;				 
				    t.type="identifier";
			            lookahead.add(t);						  
										 
                                    }        
                                    else
                                    {
                                      
                                        f = isidentifier(str);
                                        
                                        if(f == 1)
                                       {
                                         
                                           System.out.println(str+"\t\t\terror\t\t\t"+(int)(Math.abs((int)str.length()-j)+1) + "\t\t\t" + str.length() + "\t\t\t"+q+"\n");
                                          f=0;
                                        
                                        }
                                        else
                                        {
                                            
                                    
                                         System.out.println(str+"\t\t\tidentifier\t\t\t"+(int)(Math.abs((int)str.length()-j)+1) + "\t\t\t" + str.length() + "\t\t\t"+q+"\n");
                                         Token t=new Token(str); 
                                         t.line=q;
                                         t.type="identifier";
                                         lookahead.add(t);
											  
											  
											  
											  
                                        }
                                    }
                                                                                             
                      
                                           
                      }
                      else
                      {
                      if(isnum(str.charAt(0)))
                        {
                         if(isnums(str))
		  {
                                                        
                   
                      System.out.println(str+"\t\t\tnumber\t\t\t\t" + (int)(Math.abs((int)str.length()-j)+1) + "\t\t\t" + str.length() + "\t\t\t" + q + "\n");
                    Token t=new Token(str);
                    t.line=q;
		    t.type="number";                 
		    lookahead.add(t);											     
													
													  
		 }
                         else  
                     
                                System.out.println(str+"\t\t\terror\t\t\t" + (int)(Math.abs((int)str.length()-j)+1) + "\t\t\t" + str.length() + "\t\t\t" + q + "\n");
                                                           
                 }
          else
                          {
                              strch = opcheck(str);
                              if(strch.equals("error"))
                               
                                  System.out.println(str+"\t\t\t"+strch+"\t\t\t"+(int)(Math.abs((int)str.length()-j)+1)+"\t\t\t"+str.length()+"\t\t\t"+q+"\n");
                                     
          else
		         {
                                    
                         
                                  System.out.println(str+"\t\t\t"+strch+"\t\t\t"+(int)(Math.abs((int)str.length()-j)+1)+"\t\t\t"+str.length()+"\t\t\t"+q+"\n");
                             Token t=new Token(str);							    
			     t.line=q;
			      t.type="";
			     lookahead.add(t);
							  }
                          }
                      } 
                      
                     }
                      
                     str="";  
            }
           else
            {
               str=str+strline.charAt(j);    
            }    
     }       
    }
    
    return 0;
}
    

    
    
    
    public static void main(String[] args) throws IOException  {
        
    
       Scanner in =new Scanner(System.in); 
       
        System.out.println("the lexical operation is done here ---> ");
      lexical();
      
        System.out.println("--------------------------------------------------------------------");
        
        System.out.println("Now we Translate a code to Abstract stack Machine ");
        System.out.println("-------------");
        
       
        
     parser p = new parser();
     
        System.out.println("-----------------------");
        System.out.println("code is valid");
      
    
       
       
    
  
	
	
    
}
}
