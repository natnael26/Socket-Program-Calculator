/**
 * Program to calculate integers by postfix.
 * Author : Cho Seo Hyung.
 * Socket program 1.
 * Last changed : October 06, 2015.
 */
import java.util.Stack;
public class Calculator {
	private Stack<String> st = new Stack<String>();
	private Stack<Double> st2 = new Stack<Double>();
	private String[] operator = new String[20];
	private int cnt = 0; //index of operator array.
	public int postfix(String input){ //translate infix to postfix.
		String[] str2 = input.split(" "); //split the equation.
		int a=0,ans=1;
		for(int i=1;i<str2.length-1;i++){
			char ch = input.charAt(i);
			if(str2[i].equals("+")||str2[i].equals("-")||str2[i].equals("*")||str2[i].equals("/")){
				if(str2[i+1].equals("+")||str2[i+1].equals("-")||str2[i+1].equals("*")||str2[i+1].equals("/")){
					ans=0; //check exception that operators come repeatly.
				}
			}
		}
		if(ans==0||str2[0].equals("+")||str2[0].equals("-")||str2[0].equals("*")||str2[0].equals("/")
				||str2[str2.length-1].equals("+")||str2[str2.length-1].equals("-")||str2[str2.length-1].equals("*")||str2[str2.length-1].equals("/"))
			return 0; //check exception that operator comes at first or the last.
		else{
			for (int j = 0; j < str2.length; j++) {
				if (str2[a].equals("(") || str2[a].equals(")")) {
					a++; //jumps ( or ).
					continue;
				} else if (str2[a].equals("*") || str2[a].equals("/")) {
					while (st.isEmpty() == false) // pop the operator stack until it goes empty.
					{
						String temp1 = (String) st.peek();
						st.pop();
						operator[cnt] = temp1;
						cnt++;
					}
					st.push(str2[a]); //then push operator
				}
				else if (str2[a].equals("+") || str2[a].equals("-")){
					if(st.isEmpty() == false){
						if (st.peek() == "+" || st.peek() == "-") {
							while (st.isEmpty() == false) // if operator priority is higher than the operator which is in operator stack,
							{ //pop the operator stack until it goes empty.
								String temp1 = (String) st.peek();
								st.pop();
								operator[cnt] = temp1;
								cnt++;
							}
						}
					}
					st.push(str2[a]); //then push operator
				}
				else {
					operator[cnt] = str2[a];
					cnt++;
				}
				a++;
			}
			while (st.isEmpty() == false){ // at the last of the equation, pop the operator stack until it goes empty.
				String temp = (String) st.peek();
				st.pop();
				operator[cnt] = temp;
				cnt++;
			}
			return 1;
		}
	}
	public double calculate(){ //calculate the postfix equation.
		double op1, op2;
		for (int i = 0; i < cnt; i++)
		{
			if (operator[i].equals("+")) { //operate previous two integer, when "+" appear.
				op1 = st2.peek();
				st2.pop();
				op2 = st2.peek();
				st2.pop();
				double temp = op1 + op2;
				st2.push(temp);
			}
			else if (operator[i].equals("-")) { //operate previous two integer, when "-" appear.
				op1 = st2.peek();
				st2.pop();
				op2 = st2.peek();
				st2.pop();
				double temp = op2 - op1;
				st2.push(temp);
			}
			else if (operator[i].equals("*")) {//operate previous two integer, when "*" appear.
				op1 = st2.peek();
				st2.pop();
				op2 = st2.peek();
				st2.pop();
				double temp = op1 * op2;
				st2.push(temp);
			}
			else if (operator[i].equals("/")) {//operate previous two integer, when "/" appear.
				op1 = st2.peek();
				st2.pop();
				op2 = st2.peek();
				st2.pop();
				if(op1==0){ //check exception when operand divided by 0.
					return 0;
				}
				else{
					double temp = op2 / op1;
					st2.push(temp);
					//a =1;
				}
			}
			else{
				st2.push(Double.parseDouble(operator[i])); //translate String to integer and complete the equation.
			}
		}
		return st2.pop(); //return final result.
	}
	public static int except(String input){ //check exception when we input wrong elements.
		if(input.equals("OK")){ //when input "OK", send quit message.
			System.out.println("\nquit the program.");
			System.exit(1);
		}
		for(int i=0;i<input.length();i++)
		{
			char ch = input.charAt(i);
			if(ch!=' '){
				if((int)ch<48||(int)ch>57){
					if((int)ch!=40&&(int)ch!=41&&(int)ch!=42&&(int)ch!=43&&(int)ch!=45&&(int)ch!=47)
						return 0; //if error catched, return 0.
				}
			}
		}
		return 1; //if error doesn't catched, return 1.
	}
}
