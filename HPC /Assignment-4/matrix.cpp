#include<iostream>
#include<cstdlib>
#include<omp.h>

using namespace std;
#define num 1000

class matrix{
int **mat1,**mat2,**res;
int *vect1,*vect2,*res2;
public:
	void init1(int n);
	void init2(int n);
	void init3(int n);
	void matMatMul(int n);
	void matVectMul(int n);
	void vectVectAdd(int n);
	void matMatMulPar(int n);
	void matVectMulPar(int n);
	void vectVectAddPar(int n);
};

void matrix::init1(int n){
	mat1=new int*[n];
	mat2=new int*[n];
	
	for(int i=0;i<n;i++){
		mat1[i]=new int[n];
		mat2[i]=new int[n];
	}
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			mat1[i][j]=rand()%num;
			mat2[i][j]=rand()%num;
		}
	}
	res=new int*[n];
	for(int i=0;i<n;i++){
		res[i]=new int[n];
	}
}

void matrix::init2(int n){
	vect1=new int[n];
	mat1=new int*[n];
	for(int i=0;i<n;i++){
		mat1[i]=new int[n];
	}
	for(int i=0;i<n;i++){
		vect1[i]=rand()%num;
		for(int j=0;j<n;j++){
			mat1[i][j]=rand()%num;
		}
	}
	res2=new int[n];
}

void matrix::init3(int n){
	vect1=new int[n];
	vect2=new int[n];
	for(int i=0;i<n;i++){
		vect1[i]=rand()%num;
		vect2[i]=rand()%num;
	}
	res2=new int[n];
}
void matrix::matMatMul(int n){
	
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			for(int k=0;k<n;k++){
				res[i][j]+=mat1[i][k]*mat2[k][j];
			}
		}
	}
}

void matrix::matMatMulPar(int n){
	#pragma omp parallel for
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			for(int k=0;k<n;k++){
				res[i][j]+=mat1[i][k]*mat2[k][j];
			}
		}
	}
}

void matrix::matVectMul(int n){
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			res2[i]+=vect1[j]*mat1[j][i];
		}
	}
}

void matrix::matVectMulPar(int n){
	#pragma omp parallel for
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			res2[i]+=vect1[j]*mat1[j][i];
		}
	}
}

void matrix::vectVectAdd(int n){
	for(int i=0;i<n;i++){
		res2[i]=vect1[i]+vect2[i];
	}
}


void matrix::vectVectAddPar(int n){
	#pragma omp parallel for
	for(int i=0;i<n;i++){
		res2[i]=vect1[i]+vect2[i];
	}
}

int main(){
	int n,choice,ex=1;
	float start,end;
	
	matrix obj;
	

		cout<<"1. mat mat mul"<<endl;
		cout<<"2. mat vect mul"<<endl;
		cout<<"3. vect vect add"<<endl;
		cout<<"4. exit"<<endl;
		cin>>choice;
		
		switch(choice){
			case 1:
				cout<<"Enter value of n :"<<endl;
				cin>>n;
				obj.init1(n);
				start =omp_get_wtime();
				obj.matMatMul(n);
				end=omp_get_wtime();
				cout<<"time taken for serial mat mat mul : "<<end-start<<endl;
				
				start =omp_get_wtime();
				obj.matMatMulPar(n);
				end=omp_get_wtime();
				cout<<"time taken for parallel mat mat mul : "<<end-start<<endl;
				break;
			case 2:
				cout<<"Enter value of n :"<<endl;
				cin>>n;
				obj.init2(n);
				start =omp_get_wtime();
				obj.matVectMul(n);
				end=omp_get_wtime();
				cout<<"time taken for serial mat vect mul : "<<end-start<<endl;
				
				start =omp_get_wtime();
				obj.matVectMulPar(n);
				end=omp_get_wtime();
				cout<<"time taken for parallel mat vect mul : "<<end-start<<endl;
				break;
			case 3:
				cout<<"Enter value of n :"<<endl;
				cin>>n;
				obj.init3(n);
				start =omp_get_wtime();
				obj.vectVectAdd(n);
				end=omp_get_wtime();
				cout<<"time taken for serial vect vect add : "<<end-start<<endl;
				
				start =omp_get_wtime();
				obj.vectVectAddPar(n);
				end=omp_get_wtime();
				cout<<"time taken for parallel vect vect add : "<<end-start<<endl;
				break;
			default:
				ex=-1;
				break;
		}
		
	
}

/*
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
1
Enter value of n :
1000
time taken for serial mat mat mul : 17.2852
time taken for parallel mat mat mul : 11.0586
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
2
Enter value of n :
10000
time taken for serial mat vect mul : 5.73242
time taken for parallel mat vect mul : 4.15234
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
2
Enter value of n :
20000
time taken for serial mat vect mul : 37.6855
time taken for parallel mat vect mul : 27.5957
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
3
Enter value of n :
20000
time taken for serial vect vect add : 0
time taken for parallel vect vect add : 0.00195312
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
3
Enter value of n :
100000 
time taken for serial vect vect add : 0
time taken for parallel vect vect add : 0.00195312
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
3
Enter value of n :
1000000
time taken for serial vect vect add : 0.0136719
time taken for parallel vect vect add : 0.00195312
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
3
Enter value of n :
2000000
time taken for serial vect vect add : 0.0117188
time taken for parallel vect vect add : 0.0175781
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$ ./a.out
1. mat mat mul
2. mat vect mul
3. vect vect add
4. exit
3
Enter value of n :
3000000
time taken for serial vect vect add : 0.015625
time taken for parallel vect vect add : 0.00585938
virus@virus-VirtualBox:~/Desktop/HPC/Assignment-4$
*/
