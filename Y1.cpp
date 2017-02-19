#include <iostream>
#include <string>
 
using namespace std;
 

class mono
{
private:
	short stopien;
	long long wsp;
	mono* next;
	mono* prev;
 
public:
	mono( short new_stopien=16000, long long new_wsp=1 )
	{
		stopien=new_stopien;
		wsp=new_wsp;
		next=NULL;
		prev=NULL;
	}
 
	friend class list;
	
	void wypisz_mono()
	{
		if( stopien == 0 )
				cout<<wsp;
		else if( wsp == 1 )
			cout<<"+x^"<<stopien;
		else if( wsp == -1 )
			cout<<"-x^"<<stopien;
		else
			cout<<wsp<<"x^"<<stopien;
		return;
	}
};

class list	 
{
private:
	mono* wart;
	mono** start;
 
public:
	list()
	{
		wart = new mono;
		wart -> next = wart;
		wart -> prev = wart;
		start = &wart;
	}
 
	bool empty()
	{
		if(wart -> next == wart)
			return 1;
		else
			return 0;
	}
	void print_asc()
	{
		if( empty() )
		{
			cout<<"EMPTY"<<endl;
			return;
		}
		mono* temp = wart -> next;
		(*temp).wypisz_mono();
		while( temp -> next != wart )
		{
				temp = temp -> next;		//tu+
				(*temp).wypisz_mono();
		}
		cout<<endl;
	}
	void print_desc()
	{
		if( empty() )
		{
			cout<<"EMPTY"<<endl;
			return;
		}
		mono* temp = wart -> prev;
		(*temp).wypisz_mono();
		while( temp -> prev != wart )
		{
			temp = temp -> prev;		//tu+
			(*temp).wypisz_mono();
		}
		cout<<endl;
	}
	void min()
	{
		if( empty() )
		{
			cout<<"ERROR"<<endl;
			return;
		}
		cout<<( wart -> next ) -> wsp<<endl;
	}
	void max()
	{
		if( empty() )
		{
			cout<<"ERROR"<<endl;
			return;
		}
		cout<<( wart -> prev ) -> wsp<<endl;
	}
	void derivative()
	{
		if( empty() )
			return;
		if( ( wart -> next ) -> next == wart && ( wart -> next ) -> stopien == 0 ) 
		{
			delete ( wart -> next );
			wart -> next = wart -> prev = wart;
			return;
		}
		mono* temp = wart -> next;
		while( true )
		{
			if( temp -> stopien == 0 )
			{
				wart -> next = temp -> next;
				( temp -> next ) -> prev = wart;
				delete temp;
				temp = wart -> next;
			}
			else
			{
				temp -> wsp *= temp -> stopien;
				(temp -> stopien)--;
				if( temp -> next == wart )
					return;
				temp = temp -> next;
			}
		}
	}
	void add_mono( short& stopien, long long& new_wsp, bool row = false )
	{
		if( new_wsp == 0 )
			return;
		mono* temp = *start;
		while( true )
		{
			if( temp -> stopien == stopien )
			{
				if( row )
					start = &( temp -> prev );
				temp -> wsp += new_wsp;
				if( temp -> wsp == 0 )
				{
					( temp -> prev ) -> next = temp -> next;
					( temp -> next ) -> prev = temp -> prev;
					delete temp;
				}
				return;
			}
 
			if( ( temp -> next ) -> stopien > stopien )
			{
				if( row )
					start = &temp;
				mono* nowy = new mono( stopien, new_wsp );
				mono* temp_2 = temp -> next;
				temp -> next = nowy;
				nowy -> prev = temp;
				temp_2 -> prev = nowy;
				nowy -> next = temp_2;
				return;
			}
			temp = temp -> next;
		}
	}
	void add_wiel( list& B )
	{
		if( B.empty() )
			return;
		mono* B_iter = B.wart -> next;
		while( B_iter != B.wart )
		{
			add_mono( B_iter -> stopien, B_iter -> wsp, true );
			B_iter = B_iter -> next;
		}
		start = &wart;
	}
	void clean()
	{
		while( !empty() )
		{
			mono* temp = wart -> next;
			wart -> next = temp -> next;
			delete temp;
		}
		wart -> prev = wart;
	}
	~list()
	{
		clean();
		delete wart;
	}
};
 
 
 
 
int main()
{
	ios_base::sync_with_stdio(0);
	int Z;
	cin>>Z;
	while(Z--)
	{
		int n; cin>>n;
		list tab[26];
		while(n--)
		{
			string S;
			cin>>S;
			switch ( S[0] )
			{
				case 'P':
				{
					if(S=="PRINT_ASC")
					{
						char c; cin>>c;
						tab[ c - 'A' ].print_asc();	
					}
					else
					{
						char c; cin>>c;
						tab[ c - 'A' ].print_desc();
					}
					break;
				}
				case 'M':
				{
					if(S=="MIN")
					{
						char c; cin>>c;
						tab[ c - 'A' ].min();
					}
					else
					{
						char c; cin>>c;
						tab[ c - 'A' ].max();
					}
					break;
				}
				case 'D':
				{
					char c; cin>>c;
					tab[ c - 'A' ].derivative();
					break;
				}
				case 'A':
				{
					if(S=="ADD")
					{
						char c1, c2; cin>>c1>>c2;
						tab[ c1 - 'A' ].add_wiel( tab[ c2 - 'A' ] );
						cout<<"ADD OK"<<endl;
					}
					else
					{
						char c; cin>>c;
						short stopien;
						long long new_wsp;
						cin>>stopien>>new_wsp;
						tab[ c - 'A' ].add_mono( stopien, new_wsp );
						cout<<"ADD OK"<<endl;
					}
					break;
				}
				case 'C':
				{
					char c; cin>>c;
					if( tab[ c - 'A' ].empty() ) cout<<"EMPTY"<<endl;
					else { tab[ c - 'A' ].clean(); cout<<"CLEAN OK"<<endl; }
					break;
				}
				default:
				{}
				
			}
		}
	}
	return 0;
}
