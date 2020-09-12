
package finalcompilerproject;


import static finalcompilerproject.FinalCompilerProject.lookahead;



public final class parser  {  // delete extends Token
    
    public Token lookAH;
    public int error;
    public int Label;
    
    
    void Labelafterchange()
	{
	    System.out.println("lb " + (++Label));	
            
	}
 
    
   public parser(){
      
   
	error=0;
        Label=0;
	lookAH = lookahead.poll(); // lookAH stands for look Ahead.
     
		MainNFun(); 
	}
    
   public int Lexical(){
       
       
		if (lookahead.isEmpty()||lookahead.size()<1)
			return -1; 
                
		
		lookAH = lookahead.poll();
                
		return 1;
	}
   
   
   
  public void MainNFun(){
        
      
      
	if (lookAH.name.equals("void") ){
	    Lexical();
		if (lookAH.name.equals("main")){
                    Lexical();
			if (lookAH.name.equals("(")){
				Lexical();
				if (lookAH.name.equals(")")){
					Lexical();

					BeginFunction(); 
					if (Lexical() != -1){

		                         
                                            System.out.println("Not Valid");
					
					System.exit(0);
						
					}
                                }
				
				else 
                                  { 
                                    System.out.println( "in line "+lookAH.line + " : Missing right parentheses");
                                    System.out.println("Not Valid");
                                    System.exit(0);
                                    
                                  }
                                }
                                    
			else {
                                System.out.println("in line "+lookAH.line+" : Missing left parentheses");
                                System.out.println("Not Valid");
                                System.exit(0);
                                }  
                                }
                        }
		
		else { 
                    System.out.println("in line "+lookAH.line+" : Missing  void");
                    System.out.println("Not Valid");
                    System.exit(0);

	}
        
        }// end function MainNFun
  
  
  
 public void compoundStFun(){
		if (lookAH.name.equals("{"))
		{
			Lexical();
			moreStmtFun();
			if (lookAH.name.equals("}"))
				Lexical();
			else
			{
			    System.out.println("Line (" + lookAH.line + ") , error : expected a } " );
                            System.out.println("Not Valid");
		            System.exit(0);
			
			}
		}
		else 
		{ 
                    
                    
                     System.out.println("Line (" + lookAH.line + ") , error : expected left brace" );
                     System.out.println("Not Valid");
		     System.exit(0);
			
		}
	}

  
  
  
  
        
 
  
 public void BeginFunction(){

	if (lookAH.name.equals("{"))
          {
	     Lexical();
	     moreStmtFun();
	     if (lookAH.name.equals("}"))

		Lexical();
			else
                     System.out.println("Line (" + lookAH.line + " ) Mising a } ") ;


			
		}
                
		else {
                     System.out.println("Line (" + lookAH.line  + "Mising a left brace");
                     System.out.println("Not Valid");
                     System.exit(1);

                      }
	        } // end function
 
 
 public boolean keycheck(String s)
{
     String keyword[] ={"int","main()","float","if","double","for","static","else","auto","break","case","char","const","continue","void","while","volatile",
						"unsigned","union","typedef","switch","struct","static","sizeof","signed","short","return","long"};
     int d=0;
     int start = 0;
     /*
     if(!(s[start]>=97 && s[start]<=122))
                     return false;
*/
      for(int i=0;i<28;i++)
      {
             if(s.equals(keyword[i]) )
              {     
                     d = 1;
                     break;
              }
      }
     if(d == 1)
             return true;
     else
             return false;
} 
 
 /// update here
 
 
 public	void expFun()
	{
		int flag=-1;
		String id = null;
		if(lookAH.name.equals("break"))
		{
			Lexical();
			return;
		}
		if (keycheck(lookAH.name) || lookAH.type.equals("identifier")|| lookAH.type.equals("number"))
		{
			if(keycheck(lookAH.name))
				Lexical();
			if(lookAH.type.equals("identifier"))
			{
				flag=0;
				id=lookAH.name;
			}
			else
				System.out.println("push" + lookAH.name );

			
		
			if (lookAH.type.equals("identifier")||lookAH.type.equals("number"))
			{
				String tempOfID = lookAH.name;
				Lexical();
				if(flag==0&&(!(lookAH.name.equals("="))&&!(lookAH.name.equals("++"))&&!(lookAH.name.equals("+="))&&!(lookAH.name.equals("*="))&&!(lookAH.name.equals("-="))&&!(lookAH.name.equals("--"))&&!(lookAH.name.equals("/="))))
			        System.out.println("rvalue " + id);	
                                    
				else if(flag==0)
					System.out.println("lvalue " + id);
				if (lookAH.name.equals("+"))
				{
					Lexical();
					expFun();
				       System.out.println("+");
				
				}
				else if (lookAH.name.equals("-"))
				{
					Lexical();
					expFun();
					System.out.println("-");
				}
				else if (lookAH.name.equals("*"))
				{
					Lexical();
					expFun();
					System.out.println("*");
				}
				else if (lookAH.name.equals("/"))
				{
					Lexical();
					expFun();
					System.out.println("/");
				}
				else if (lookAH.name.equals("%"))
				{
					Lexical();
					expFun();
					System.out.println("%");
				}
				else 
				{
					if (lookAH.name.equals("="))
					{
						Lexical();
						expFun();
						System.out.println("=");
									
					}
					else if (lookAH.name.equals("++"))
					{
						Lexical();
                                                System.out.println("rvalue " + tempOfID);
						System.out.print("push 1 \n+\n=\n");
						
					}
					else if (lookAH.name.equals("--"))
					{
						Lexical();
						 System.out.println("rvalue " + tempOfID);
						System.out.print("push 1 \n-\n=\n");
					}

					else if (lookAH.name.equals("*="))
					{
						System.out.println("rvalue " + tempOfID);
						Lexical();
						expFun();
                                                System.out.println("*");
                                                 System.out.println("=");
					
					}
					else if (lookAH.name.equals("/="))
					{					
						System.out.println("rvalue " + tempOfID);
						Lexical();
						expFun();
						System.out.println("/");
                                                 System.out.println("=");
					}
					else if (lookAH.name.equals("+="))
					{				
						System.out.println("rvalue " + tempOfID);
						Lexical();
						expFun();
						System.out.println("+");
                                                 System.out.println("=");
					}
					else if (lookAH.name.equals("-="))
					{
						System.out.println("rvalue " + tempOfID);
						Lexical();
						expFun();
						System.out.println("-");
                                                 System.out.println("=");
					}

				}
				return;
			}
			else
			{
                            System.out.println("Line (" + lookAH.line + ") , error : expression must start with identifier or any atomic data");
			                         System.out.println("Not Valid");
                                                 System.exit(0);
				
			}
			
			if (lookAH.name.equals("+"))
			{
				Lexical();
				expFun();
			}
			else if ( lookAH.type.equals("identifier"))
			{
				Lexical();
				expFun();
			}
			else if (lookAH.name.equals("-"))
			{
				Lexical();
				expFun();
			}
			else if (lookAH.name.equals("*"))
			{
				Lexical();
				expFun();
			}
			else if (lookAH.name.equals("/"))
			{
				Lexical();
				expFun();
			}
			else if (lookAH.name.equals("%"))
			{
				Lexical();
				expFun();
			}
			return;
		}

		if (lookAH.name.equals("("))
		{
			Lexical();
			expFun();
			if (lookAH.name.equals(")"))
				Lexical();
			else 
			{
                            System.out.println("Error: line " + lookAH.line + ", Expected a closing paranthesese" );
                            System.out.println("Not Valid");
				System.exit(0);
				
			}
			if (lookAH.name.equals("+"))
			{
				Lexical();
				expFun();

			}
			else
				if (lookAH.name.equals("-"))
				{
					Lexical();
					expFun();
				}
			else if (lookAH.name.equals("*"))
			{
				Lexical();
				expFun();
			}
			else if (lookAH.name.equals("/"))
			{
				Lexical();
				expFun();
			}
			else if (lookAH.name.equals("%"))
			{
				Lexical();
				expFun();	
			}
			return;
		}

		if (lookAH.type.equals("charecter"))
		{
			Lexical();
			//simply get the integer from the char.
			if (lookAH.name.equals("+"))
			{
				Lexical();
				expFun();
			}
			else
				if (lookAH.name.equals("-"))
				{
					Lexical();
					expFun();
				}
				else if (lookAH.name.equals("*"))
				{
					Lexical();
					expFun();
				}
				else if (lookAH.name.equals("/"))
				{
					Lexical();
					expFun();
				}
				else if (lookAH.name.equals("%"))
				{
					Lexical();
					expFun();
				}
			return;
		}
	}//function
 
 



public void stmtFun(){
    
	if (lookAH.name.equals("if"))
		ifStmtFun();
	else
	     if (lookAH.name.equals("while"))
		whileStmtFun();
	else
	     if (lookAH.name.equals("switch"))
		switchStmtFun();
	else
             if (lookAH.name.equals("do"))
		doStmtFun();
	else
       	     if (lookAH.name.equals("for"))
		forStmtFun();
	else
             if (lookAH.type.equals("identifier"))
		{
		expFun();
                    if (lookAH.name.equals(";"))
			Lexical();
                    
			else
			  {
                              System.out.println("Line (" + lookAH.line + ") , error : expected a ;");
                              System.out.println("Not Valid");
                              System.exit(1);
                          }
									
		}
             
	else
             if (lookAH.name.equals(";"))
		Lexical();
	else
	     if (lookAH.name.equals("{"))
		BeginFunction();
	else{
              System.out.println("Line (" + lookAH.line + ") , error : expected a statement"); 
              System.out.println("Not Valid");
              System.exit(1);
																			
	   }

	} // end function stmtfunc






public void preList(){
		if (lookAH.type.equals("identifier"))
		{
		        System.out.println("lvalue" + lookAH.name);	
                    
			Lexical();
			if (lookAH.name.equals("="))
			{
				Lexical();
				expFun();
	                        System.out.println("=");
				if (lookAH.name.equals(","))
				{
					Lexical();
					if (lookAH.name.equals("int"))
					{
						Lexical();
						preList();
					}
					else
					{
                                            System.out.println("Line (" + lookAH.line + ") , error : for loop must start with int in first statement" );
					    System.out.println("Not Valid"); 
					    System.exit(0);
					}
				}
			}
		}
		else
		{
                           System.out.println("Line (" + lookAH.line + ") , error : expected an identifier" );
    		           System.out.println("Not Valid"); 
			   System.exit(0);
			
		}
	} // end fuction preList








public void postList(){
		expFun();
		if (lookAH.name.equals(",")){
			Lexical();
			postList();

		}
	}  // end of the functio postList



public	void forStmtFun()
{
	if (lookAH.name.equals("for"))
		{
			Lexical();
			if (lookAH.name.equals("("))
			{
				Lexical();
				if (lookAH.name.equals("int"))
				{
					Lexical();
					preList();
				}
				else if (lookAH.type.equals("identifier"))
				{
					preList();
				}
				else
				{
                                    
                           System.out.println("Line (" + lookAH.line + ") , error : for loop must start with int data type in first segement" );
    		           System.out.println("Not Valid"); 
			   System.exit(0);
					
				}
			if (lookAH.name.equals(";"))
			{
		            Lexical();
                            System.out.println("label");
			    Labelafterchange();
			    int topLb = Label;
			    condFunction();
                            System.out.print("gofalse ");
			    Labelafterchange();
			    int falsLb = Label;
			 if (lookAH.name.equals(";"))
			{
			  Lexical();
                          System.out.print("goto ");
                          Labelafterchange();
			  int stmtBegining = Label;
                          System.out.print("label ");
                          Labelafterchange();
			  int postListStarting = Label;
		          postList();
                            System.out.println("goto lb"  + topLb );
                            
                            
			if (lookAH.name.equals(")"))
			  {
			   Lexical();
                           System.out.println("label lb" + stmtBegining );
			   stmtFun();
                              System.out.println("goto lb " +postListStarting );
                              System.out.println("label lb" + falsLb);
								
								
                          }	 			
			else
			  {
                              System.out.println("Line ( " + lookAH.line + ") , error : expected a right parentheses");
                              System.out.println("Not Valid"); 
		               System.exit(0);
								
			  }
			}
			else
			{
                            System.out.println("Line ( " + lookAH.line + ") , error : expected a ;");
                              System.out.println("Not Valid"); 
		               System.exit(0);
                          }
			}
			else
                        {
                            System.out.println("Line ( " + lookAH.line + ") , error : expected a ;");
                              System.out.println("Not Valid"); 
		               System.exit(0);
                        }
						
			}
			else
			{
                             System.out.println("Line ( " + lookAH.line + ") , error : expected a left parentheses");
                              System.out.println("Not Valid"); 
		               System.exit(0);
				
			}
		}
		else
		{
                    
                    System.out.println("Line ( " + lookAH.line + ") , error : expected for in for loop");
                              System.out.println("Not Valid"); 
		               System.exit(0);
			
		}

	}  // end of function for loop 


 
public void doStmtFun(){
    
       System.out.println("label");
       Labelafterchange();
		
       int doLable = Label;
       
       
	if (lookAH.name.equals("do"))
	   {
	    Lexical();
	    stmtFun();
            
	if (lookAH.name.equals("while"))
	   {
	    Lexical();
            
            
	if (lookAH.name.equals("("))
	   {
	   Lexical();
	   condFunction();
           
           
	if (lookAH.name.equals(")"))
	   {
               
               System.out.println("gotrue lb " + doLable );
	       Lexical();
               
               
	if (lookAH.name.equals(";"))
	   Lexical();
        
	else
	  {
              System.out.println("Line (" + lookAH.line + ") , error : expected a ;");
              System.out.println("Not valid");
              System.exit(0);
	}
           }
					
	else
	  {
              
              System.out.println("Line (" + lookAH.line + ") , error : expected a )");
              System.out.println("Not valid");
              System.exit(0);
	}

	}
       else
	{
            System.out.println("Line (" + lookAH.line + ") , error : expected a (");
              System.out.println("Not valid");
              System.exit(0);
					
	}
      }
     else
	 {
             System.out.println("Line (" + lookAH.line + ") , error : do statement must end up with while part");
              System.out.println("Not valid");
              System.exit(0);
	}
	}
        
     else
	{
             System.out.println("Line (" + lookAH.line + ") , error : expected do in do statement");
              System.out.println("Not valid");
              System.exit(0);
			
		}

	}  // end of function dostmtfunc



public void switchStmtFun(){
    
	if (lookAH.name.equals("switch")){
		Lexical();
                
	if (lookAH.name.equals("(")){
		Lexical();
		expFun();
                
	if (lookAH.name.equals(")")){
		Lexical();
		bracedCasesFun();
		}
        
        
	else
		{
                    System.out.println("Line (" +lookAH.line + ") Mising a )" );
                    System.out.println("Not Valid");
                    System.exit(1);
					
		}
        
	}
        
	else
	       {
                   System.out.println("Line (" +lookAH.line + ") Mising a (" );
                   System.out.println("Not Valid");
                   System.exit(1);
                   
				
		}
	}
        
        else
		{
                   System.out.println("Line (" +lookAH.line + ") Mising switch in switch statement" );
                   System.out.println("Not Valid");
                   System.exit(1);
			
		}
	} //end of function switcstmtfinc







public void bracedCasesFun(){
    
  	if (lookAH.name.equals("{")){
	    Lexical();
	    casesFun();
            
	if (lookAH.name.equals("}")){
	    Lexical();
	}
        
	else
	   {
               System.out.println("Line (" +lookAH.line + ") Mising a }" );
               System.out.println("Not Valid");
               System.exit(1);			
	   }
        
        }
		
	else
	    {
               System.out.println("Line (" +lookAH.line + ") Mising a {" );
               System.out.println("Not Valid");
               System.exit(1);
			
	    }
	} // end function 

public void casesFun(){


	if (lookAH.name.equals("case")){
            caseFun();
	    casesFun();
		}
        
	if (lookAH.name.equals("default")){
	    defaultFun();
	    casesFun();
		}
	}  // end function casesFun



public void defaultFun(){

	if (lookAH.name.equals("default")){
	    Lexical();
            
        if (lookAH.name.equals(":")){
	    Lexical();
            
	if (lookAH.name != "}" && lookAH.name != "case")
	    stmtFun();
        }
        
        
	else
	    {
               System.out.println("Line (" +lookAH.line + ") , error : expected a :" );
               System.out.println("Not Valid");
               System.exit(1);
				
	    }
       }
        
	else
	    {
               System.out.println("Line (" +lookAH.line + ") , error" );
               System.out.println("Not Valid");
               System.exit(1); 
                
	    }
	} // end function default function


public void orderedStmtFun(){
    
	if (lookAH.name != "}" && lookAH.name != "case" && lookAH.name != "default"){
	     stmtFun();
	     orderedStmtFun();
		}
	} //end function orderedStmtFun



public	void caseFun(){

	if (lookAH.name.equals("case")){
	     Lexical();
	     expFun();
             System.out.println("==");
             System.out.print("goFalse ");
	     Labelafterchange();
             int nextCaseLabel = Label;
				

	if (lookAH.name.equals(":")){
	    Lexical();
	    orderedStmtFun();
            System.out.print("label lb " + nextCaseLabel);
            
			
	   }
        
        
	else
	   {
               System.out.println("Line (" +lookAH.line + ") , error : expected a :" );
               System.out.println("Not Valid");
               System.exit(1);
				
	    }
     }
        
	else
	    {
               System.out.println("Line (" +lookAH.line + ") , error" );
               System.out.println("Not Valid");
               System.exit(1);
	 
            }
	}  // end function case function



 
public	void whileStmtFun(){
    
    System.out.print("Label ");
    Labelafterchange();
    int beforeWhileLb = Label;
		
	if (lookAH.name.equals("while")){
	    Lexical();
            
	if (lookAH.name.equals("(")){
            Lexical();
	    condFunction();
            
	if (lookAH.name.equals(")")){
            Lexical();
            System.out.print("go false ");
            Labelafterchange();
            int toFalseLabel = Label;
            stmtFun();
            System.out.println("goto  lb" + beforeWhileLb + "label lb" + toFalseLabel);
           
					
	}				
				
	    else
		{
                    System.out.println("Line (" +lookAH.line + ") , error : expected a )" );
                    System.out.println("Not Valid");
                    System.exit(1);
					
		}
	}
        
        
	    else
		{
                    System.out.println("Line (" +lookAH.line + ") , error : expected a (" );
                    System.out.println("Not Valid");
                    System.exit(1);
				
		}
	}
        
	    else
		{
                    System.out.println("Line (" +lookAH.line + ") , error" );
                    System.out.println("Not Valid");
                    System.exit(1);
		}
	} // enf whilestmtfunction



public void ifStmtFun(){
    
    	if (lookAH.name.equals("if")){
      	    Lexical();
            
	if (lookAH.name.equals("(")){
	    Lexical();
	    condFunction();
            
	if (lookAH.name.equals(")")){
	    Lexical();
            System.out.print("go false ");
            Labelafterchange();
            int falseLabel = Label;
	    stmtFun();
            System.out.print("go to ");
            Labelafterchange();
            int afterElseLabel = Label;
            System.out.println("label lb " + falseLabel);
					

	if (lookAH.name.equals("else")){
	    elseFun();				
	}
            System.out.println("label lb " + afterElseLabel);
					
     }
        
   	   else
		{
                    System.out.println("Line (" +lookAH.line + ") Mising a )" );
                    System.out.println("Not Valid");
                    System.exit(1);
					
		}
        
   }
	   else
		{
                    System.out.println("Line (" +lookAH.line + ") Mising a (" );
                    System.out.println("Not Valid");
                    System.exit(1);
				
		}
   }
        
	   else
		{
                    
                    System.out.println("Line (" +lookAH.line + ") , error" );
                    System.out.println("Not Valid");
                    System.exit(1);
			
		}
	} // end ifstmtfunct



public	void elseFun(){
    
    	if (lookAH.name.equals("else")){
            
	    Lexical();
	    stmtFun();
		}

	} // end of function elseFun


public 	void condFunction(){
    
	if (lookAH.type.equals("identifier")  || lookAH.type.equals("number")){
		Token current = lookAH;
                
                if (lookAH.type.equals("identifier"))
                {
                    System.out.println("rvalue " + lookAH.name);
                   
                }
                else
                    System.out.println("push" + lookAH.name);
			
			
		Lexical();
                if(!(lookAH.name.equals(")")))
		smPrFun(current);
                else
		{
                    System.out.println("push 0");
		    System.out.println("!=");
				
		}

	}
        
	   else{
                    System.out.println("Line (" +lookAH.line + ") Mising an identifier or a number." );
                    System.out.println("Not Valid");
                    System.exit(1);
		}
	} // end of function condFunction



public boolean isLogicalOperator(){
    
	String logicalOperator[] = { "<", ">", "==", "<=", ">=", "!=", "||", "&&" };
		for (int i = 0; i < 7; i++)
			if (lookAH.name.equals(logicalOperator[i]))
				return true;
		                return false;
	}






public void smPrFun(Token leftP)
	{
            
	if (isLogicalOperator())
	{
	String logOp = lookAH.name;
        Lexical();
			
	if (lookAH.type.equals("identifier") || lookAH.type.equals("charecter") || lookAH.type.equals("integer") || lookAH.type.equals("number"))
			{

				if (lookAH.type.equals("identifier"))
                                    System.out.println("rvalue" + lookAH.name);
					
				else
                                    System.out.println("push " + lookAH.name);
		                    System.out.println(logOp);
				    Lexical();
			}
			else
			{
                            System.out.println("Line (" +lookAH.line + ") , error : expected an identifier or a number." );
                             System.out.println("Not Valid");
                             System.exit(1);
                       
			}
		}
		else
		{
			System.out.println("Line (" +lookAH.line + ") , error : expected logical operator." );
                        System.out.println("Not Valid");
                        System.exit(0);
		}

	}// end of the function 

        


public	void moreStmtFun(){
    
	if (lookAH.name.equals("}"))//means e
		return;
        
	stmtFun();
	moreStmtFun();

	} // end function moreStmtFun



  
}//end class pars
   

