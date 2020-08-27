/*
https://practice.geeksforgeeks.org/problems/longest-valid-parentheses/0
 */

package javaCom;

import java.util.Scanner;
import java.util.Stack;

import static java.lang.Math.max;

public class longestValidPaenthesisSet
{
	final static boolean DEBUG = true;
	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while(t-->0)
		{
			String parString = s.next();
			System.out.println(longestValidParenthesis(parString));
		}
	}

	public static int longestValidParenthesis(String input)
	{
		Stack<Integer> parenStack = new Stack<Integer>();
		//for any (, push in stack
		 //if lastStable  = -1
		 //    set lastStable = thisIndex

		/*
		for any ),
		 check if a corresponding ) exists
		  if yes,
		  	pop stack
		  	if now stack empty, take the possible answer length as thisIndex - lastStable + 1
		  if no,
		  	empty the stack (if any element)
		*/
		int lastStableIndex = -1;
		int answer = -1;
		for(int i=0; i<input.length(); i++)
		{
			char c = input.charAt(i);

			if(c=='(')
			{
				parenStack.push(i);
				lastStableIndex = (lastStableIndex==-1)?i:lastStableIndex;
			}
			else
			{
				if(parenStack.empty())
				{
					//invalid Configuration
					lastStableIndex = -1;
					continue;
				}
				int _PopPosition = parenStack.pop();
				if(parenStack.empty())
				{
					answer = max(answer, i - lastStableIndex + 1);
				}
				else
				{
					answer = max(answer, i - (parenStack.peek()+1)+1);
					//answer = max(answer, i - parenStack.peek());
				}
				answer = max(answer, i - _PopPosition+1);

			}

		}

		return answer;
	}

}
