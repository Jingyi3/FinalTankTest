#include<iostream>
#include <string>
#include <sstream>
using namespace std;

string& trim(string &);
string& lower(string &);
string getToken();        

template <class T>         
void convertFromString(T &, const std::string &);
bool Isnum(string &);      
void MatchToken(string);  


string s1[9] = { "=","<","<=",">",">=",":=",",",";","." };
string line;              
string lookahead;          
char *p = NULL;            

void ParseS();
float ParseE();
float ParseR(float);
float ParseT();
float ParseP(float);
float ParseF();

int main() {

	while (1) {
		cout << "biaodashi:" << endl;
		getline(cin, line);
		trim(line);                
		lower(line);               
		line = line + "#";
		if (line[0] == '+' || line[0] == '-')          
			line = "0" + line;
		p = &line[0];

		lookahead = getToken();
		ParseS();
		cout << endl;
	}

	return 0;
}

void MatchToken(string a) {
	if (lookahead != a) {
		cout << "Error1" << endl;
		exit(0);
	}
	else
		lookahead = getToken();
}

void ParseS() {
	cout << "S1" << endl;
	float Ev = ParseE();
	cout << "result is:"<<Ev << endl;
}

float ParseE() {
	float Ev;
	if (Isnum(lookahead)||lookahead.compare("(")==0) {
		float Tv=ParseT();
		float Ri = Tv;
		float Rs=ParseR(Ri);
		Ev = Rs;
	}
	else {
		cout << "Error2" << endl;
		exit(0);
	}
	
	return Ev;
}
float ParseR(float i) {
	float Rs;
	if (lookahead.compare("+") == 0) {
		MatchToken(lookahead);
		float Tv=ParseT();
		float R1i = i + Tv;
		float R1s = ParseR(R1i);
		Rs = R1s;
		cout << "Rs=" << Rs << endl;
	}
	else if (lookahead.compare("-") == 0) {
		MatchToken(lookahead);
		float Tv = ParseT();
		float R1i = i - Tv;
		float R1s = ParseR(R1i);
		Rs = R1s;
		cout << "Rs=" << Rs << endl;
	}
	else if (lookahead.compare(")") == 0 || lookahead.compare("#") == 0) {
		Rs = i;
	}
	else {	
		cout << "Error3" << endl;
		exit(0);
	}
	return Rs;
}

float ParseT() {
	float Tv;
	if (Isnum(lookahead) || lookahead.compare("(")==0) {
		float Fv=ParseF();
		float Pi = Fv;
		float Ps=ParseP(Pi);
		Tv = Ps;
		cout << "Tv=" << Tv << endl;
	}
	else {
		cout << "Error4" << endl;
		exit(0);
	}
	return Tv;
}

float ParseP(float i) {
	cout << "P" << endl;
	float Ps;
	if (lookahead.compare("*") == 0) {
		MatchToken(lookahead);
		float Fv=ParseF();
		float P1i = i * Fv;
		float P1s=ParseP(P1i);
		Ps = P1s;
		cout << "Ps=" << Ps << endl;
	}
	else if (lookahead.compare("/") == 0) {
		MatchToken(lookahead);
		float Fv = ParseF();
		if(Fv==0){
			cout << "chushu is 0"<<endl;
		}
			
		float P1i = i / Fv;
		float P1s = ParseP(P1i);
		Ps = P1s;
		cout << "Ps=" << Ps << endl;
	}
	else if (lookahead.compare(")") == 0 || lookahead.compare("#") == 0 || lookahead.compare("+") == 0 ||lookahead.compare("-")==0 ) {
		Ps = i;
	}
	else {
		cout << "Error4" << endl;
		exit(0);
	}
	return Ps;
}

float ParseF() {
	float Fv;
	if (Isnum(lookahead)) {
		convertFromString(Fv, lookahead);
		lookahead = getToken();
	}
	else if (lookahead.compare("(") == 0) {
		MatchToken("(");
		float Ev=ParseE();
		Fv = Ev;
		if (lookahead.compare(")")==0)
			MatchToken(")");
	}
	else {
		cout << "Error5" << endl;
		exit(0);
	}
	cout << "Fv=" << Fv << endl;
	return Fv;
}


string getToken() {
	char word[20];   
	string finish;
	while (*p!='\0') {      
		if (*p == ' ') {       
			p++; continue;
		}
		
		else if (*p == '+' || *p == '-' || *p == '*' || *p == '/' || *p == '=' ||
			*p == '#' || *p == '(' || *p == ')' || *p == ',' || *p == ';' || *p == '.') {
			finish = *p++;
		}
		
		else if (*p == '<' || *p == '>' || *p == ':') {
			if (*p == '<'&&*(p+1) == '=') {
				finish = "<=";
			}
			else if (*p == '>'&&*(p + 1) == '=') {
				finish = ">=";
			}
			else if (*p == ':'&&*(p + 1) == '=') {
				finish = ":=";
			}
			else if (*p == ':') { cout << ": must has" << endl; }
			else
			{
				finish = *p;
			}
			p++;
		}
		else if (*p >= '0'&&*p <= '9'|| *p == '.')
		{
			int j = 0;
			int count = 0;
			
			for (j = 0; *p != '\0' && (*p >= '0'&&*p <= '9'||*p=='.');) {
				if (*p == '.')
					count++;
				word[j++] = *p++;
			}
			if (count > 1) {
				cout << "no two :" << endl;
				exit(0);
			}
			word[j] = '\0';
			finish = word;
		}
		else if (*p >= 'a'&&*p <= 'z')
		{
			int j = 0;
			
			for (j = 0; *p != '\0' && ((*p >= 'a'&&*p <= 'z') || (*p >= '0'&&*p <= '9'));) {
				word[j++] = *p++;
			}
			word[j] = '\0';
			finish = word;
		}
		else
		{
			cout << "\"" << *p << "\"unavaiable" << endl;
			exit(0);
		}
		cout << "zhongjiefu:" << finish << endl;
		return finish;
	}
}

string& trim(std::string &s)
{
	if (s.empty())
	{
		return s;
	}

	s.erase(0, s.find_first_not_of(" "));
	s.erase(s.find_last_not_of(" ") + 1);
	return s;
}

string& lower(string & s) {
	for (int i = 0; i < s.length(); i++) {
		if (s[i] >= 'A' && s[i] <= 'Z')
			s[i] += -'A' + 'a';
	}
	return s;
}

template <class T>
void convertFromString(T &value, const std::string &s) {
	std::stringstream ss(s);
	ss >> value;
}

bool Isnum(string& a) {
	if (a[0] >= '0' && a[0] <= '9') 
		return 1;
	else
		return 0;
}