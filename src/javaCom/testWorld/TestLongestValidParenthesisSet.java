import javaCom.longestValidPaenthesisSet;

import java.util.Random;

public class TestLongestValidParenthesisSet
{
	static boolean DEBUG = false;
	final static int parLen = 100;
	public static void main(String[] args)
	{
		//repeat 10 times
		//generate a string of length
		//run brute force logic to check
		// run the
		/*
		System.out.println(bruteForceLongestParenthesis("()()()"));

		String input2 = "))((((())))()())()))()(()))((((()))((()())())()(())))(()()))((((()(((()(((((((())(()))(()(()))(())))(((()()()()))(()()()())((()()((()()))())))((()())()(()))))))(((())))()(()))())()()())(()(()()))())(()))))(()(())(()()())))(()()))(()))()()()(())())())()))(()(()(()(((())))()))())))))()))())()()()()(())())()(()())(())())((()()))))(())()((()())()(()(()))(((((()()(())((()))()(())(()(()())))()()(()()))())))(()))()(())()(()(()()))))(()(((()(()(())()()(()()))))))(()((())))()())((()()((((((()))(()))(()((())()(((())()()(()(())))((()(((((()()((()()))((((()(((())))((()))()))())))))()))()()()((((()(((()()())())))))))(()())))()()()()()))))()(()(((())()))))()((()))())()())))((()))))))))((()())()()()))(()()())())())(((()))))(()())))(())(((()(())(((()(()(())(())()()))((()))())((()))()))))))()((())((())((((()(()((((()))))()()(()(()((())()()((()())())()(()()()())(((())(()))((()())((())((()()((()(()()()()()((()))(()))(()((()()())())(())(())())(()(())((((()(()()((()(()(())((()()(()()))))((()())))(())((()))";
		System.out.println(bruteForceLongestParenthesis(input2));

		System.out.println(bruteForceLongestParenthesis(")))((("));
		*/

		for(int i=0;i <10; i++)
		{
			String parSeq = generateRandomParSeq(1000);
			if(DEBUG)System.out.println(parSeq);
			int brute_answer = bruteForceLongestParenthesis(parSeq);
			int my_answer = longestValidPaenthesisSet.longestValidParenthesis(parSeq);
			assert(brute_answer!=my_answer);
			if(brute_answer!=my_answer)
			{
				System.out.println("---failing on-----");
				System.out.println(parSeq);
				DEBUG = true;
				bruteForceLongestParenthesis(parSeq);
				DEBUG = false;
				System.out.println("myAnswer: " + my_answer );
				System.out.println("---end-----");

			}
			System.out.println("result match : " + i);
		}

	}
	static String generateRandomParSeq(int len)
	{
		char myArray[] = new char[len];
		// create instance of Random class
		Random rand = new Random();
		for(int i=0; i<len; i++)
		{
			int randNum = rand.nextInt(2);
			myArray[i] = randNum==1?')':'(';
		}

		return new String(myArray);

	}
	static int bruteForceLongestParenthesis(String input)
	{
		int maxAnswer = -1;
		int answerStart = -1, answerEnd = -1;
		for(int i=0;i<input.length()-1; i++)
		{
			for(int j=i; j<input.length();j++)
			{
				//if valid combo, then mark
				if(isValidParenthesis(input, i, j))
				{
					int _thisLen = j-i+1;
					if(_thisLen>maxAnswer)
					{
						maxAnswer = _thisLen;
						answerStart = i;
						answerEnd = j;
					}
				}
			}
		}


		if(DEBUG)
		{
			if (maxAnswer != -1)
			{
				debugOut(DEBUG, "Start Index : " + answerStart + ", End Index: " + answerEnd);
				debugOut(DEBUG, input.substring(answerStart, 1 + answerEnd));
			}
			debugOut(DEBUG, "answer:" + maxAnswer );
		}
		return maxAnswer;
	}

	static boolean isValidParenthesis(String input, int startIndex, int endIndex)
	{
		if((endIndex-startIndex)%2==0)
			return false;
		int leftCount = 0;

		for(int i=startIndex; i<=endIndex; i++)
		{
			char c = input.charAt(i);
			if(c==')')
			{
				if(leftCount<=0)
					return  false;
				leftCount--;
			}
			else
			{
				leftCount++;
			}
		}
		return (leftCount==0);
	}

	static void debugOut(boolean isEnabled, String msg)
	{
		if(isEnabled)
		{
			System.out.println(msg);
		}
	}

}
