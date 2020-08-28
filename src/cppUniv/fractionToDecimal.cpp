/*


File : fractionToDecimal.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/a-simple-fraction/0


To run the file, run below command:
g++ fractionToDecimal.cpp && ./a.out <../TestInput/fractionToDecimal.txt


*/


#include <bits/stdc++.h>
#define DEBUG 0
using namespace std;

string decimalRep(const int Numerator,const int Denominator) {
	//count number of 5 and 2 in denom
	int numberCopy = Denominator;
	
	assert(Denominator>0);

	//perform division
	const int Remainder = Numerator%Denominator;
	const int Quotient  = Numerator/Denominator; 

	if(Remainder==0) {
		assert(false && "never enter here");
		return to_string(Quotient);
	}
	
	int count_decimal_nonrepetitive=0;
	
	while(numberCopy>1) {
		bool is5Multi = (numberCopy%5==0);
		bool is2Multi = (numberCopy%2==0);

		if(!is5Multi && !is2Multi) {
			break;
		}
		
		if(is5Multi) {
			numberCopy /= 5;
		}
		
		if(is2Multi) {
			numberCopy /= 2;
		}

		count_decimal_nonrepetitive++;
	}

	if(DEBUG) {cout<<"Number of 2s and 5s in Denon" << count_decimal_nonrepetitive<<endl;}

	//obtain result of remainder/Denominator
	//result is quotient+"decimal representation with right brackets"

	/*
	 normal division process:
	 	we have a remainder, Denominator
	 	until remainder < denom
	  	multiply remainder by 10 append a 0 in decimal
	*/

	/*
	  for a repetitive remainder, first divide the remainder count_decimal_nonrepetitive times
	  the remainder obtained now will be repeated when the decimal is about to be repeated
	  but if the remainder is 0, then no further division is required
	*/
	//string nonRepeativeFraction = "";
	int _rem = Remainder;
	int decimalPlaces = 0;
	char decimalValue[50001];
	int _savedRemainder;
	bool _isDivisible = false;
	while(true) {
		
		decimalPlaces++;
		
		if(_rem==0) 
		{
			//exit no repeating remainder
			_isDivisible = true;
			break;
		}


		if(decimalPlaces==1+count_decimal_nonrepetitive) 
		{
			//save the remainder
			//as it is supposed to repeat
			
			_savedRemainder = _rem;

		}
		else if (decimalPlaces>count_decimal_nonrepetitive) 
		{
			//check if remainder repeated 
			if(_rem==_savedRemainder) {
				//we have the repeated decimal
				//exit
				if(DEBUG) {cout<<"saved remainder"<<_rem<<':'<<endl;}
				break;
			}
		}
		if(DEBUG) {cout<<"new remainder"<<_rem<<endl;}
		//execution here means, no exit criteria is met
		//continue the division and calculate the decimal
		_rem *= 10;
		
		if(_rem < Denominator) 
		{
			decimalValue[decimalPlaces] = 0+'0';
		}
		else 
		{
			int _quotient = _rem/Denominator;
			_rem = _rem % Denominator;
			assert((_quotient>0 && _quotient<10) && "quotient incorrect");
			decimalValue[decimalPlaces] = _quotient+'0';	
		}



	}//end of loop, for division

	//the decimal values found are final
	//1...count_decimal_nonrepetitive are non repetitive
	//count_decimal_nonrepetitive..decimalPlaces) non inclusive are repetitive

	//form the non repetitive part of the answer
	string myDecimal = to_string(Quotient) + ".";

	for(int i =1;i<=count_decimal_nonrepetitive;i++) {
		//if(i==decimalPlaces) break; //eg: 2/4, it will break at first (handled by gcd)
		myDecimal = myDecimal + decimalValue[i];
	}
	if(_isDivisible) {
		return myDecimal;
	}
	myDecimal += "(";
	for(int i= count_decimal_nonrepetitive+1;i<decimalPlaces;i++) 
	{
		myDecimal = myDecimal + decimalValue[i];
	}
	myDecimal += ")";
	return myDecimal;

}

string decimalRepUtil(const int Numerator,const int Denominator) {
	assert(Denominator>0);
	int myGcd = __gcd(Numerator, Denominator);
	if(myGcd==Denominator) {
		return to_string(Numerator/Denominator);
	}
	return decimalRep(Numerator/myGcd, Denominator/myGcd);
}

int main(void) {
	int t, a, b;
	scanf("%d", &t);
	while(t--) {
		scanf("%d%d", &a, &b);
		cout<<decimalRepUtil(a, b)<<endl;
	}
}