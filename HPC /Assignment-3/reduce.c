#include<stdio.h>
#include<mpi.h>
#include<stdlib.h>

#define num 1000

int main(int argc,char** argv){
	int arr[5]={20,34,2,11,10};

	int my_rank,size;
	int x;
	int min,max,sum;
	MPI_Init(&argc,&argv);
	MPI_Comm_rank(MPI_COMM_WORLD,&my_rank);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	
	printf("Rank : %d size : %d\n",my_rank,size);

	
	switch(my_rank){
		case 0:
			x=arr[0];
			break;
		case 1:
			x=arr[1];
			break;
		case 2:
			x=arr[2];
			break;
		case 3:
			x=arr[3];
			break;
		default:
			x=arr[4];
			break;
	}
	
	
	MPI_Reduce(&x,&sum,1,MPI_INT,MPI_SUM,0,MPI_COMM_WORLD);
	MPI_Reduce(&x,&min,1,MPI_INT,MPI_MIN,0,MPI_COMM_WORLD);
	MPI_Reduce(&x,&max,1,MPI_INT,MPI_MAX,0,MPI_COMM_WORLD);
	
	if(my_rank==0){
		float avg=sum/size;
		printf("sum :%d\nmin :%d\nmax :%d\navg :%f\n",sum,min,max,avg);
	}
	MPI_Finalize();
	exit(0);
}
