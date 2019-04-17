/*--------------------------------------------------

Derek Popowski
derek.a.popowski@und.edu
CSci 364 Concurrent and distributed programming
hw 5
Matrix multiplication with openMP

--------------------------------------------------*/
#include <iostream>
#include <random>

int main(int argc, char* argv[]){

  int print = 0;
  
  if(argc > 2 && (*argv[2] == 'y' || *argv[2] == 'Y' || *argv[2] == '1')){
    print = 1;
  }
  int size = 0;
  sscanf(argv[1],"%d",&size);
  //printf("matrix size = %d\nprint : %d\n",size,print);//debug for print set and matrix size

  //init the matrix to the correct size and set all values to 1
  int **M;
  int **N;
  M = (int**)malloc(size*sizeof(int*));
  for(int i = 0; i < size; i++)
    M[i]=(int*)malloc(size*sizeof(int));
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++)
      M[i][j]=1;
  
  //verify the matrix init
  /*
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++)
      printf("%d ",M[i][j]);
    printf("\n");
  }
  */
  N = (int**)malloc(size*sizeof(int*));
  for(int i = 0; i < size; i++)
    N[i]=(int*)malloc(size*sizeof(int));
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++)
      N[i][j]=1
	;
  //verify the matrix init
  /*
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++)
      printf("%d ",N[i][j]);
    printf("\n");
  }
  */
  //init the output matrix
  int **S;
  S = (int**)malloc(size*sizeof(int*));
  for(int i = 0; i < size; i++)
    S[i]=(int*)malloc(size*sizeof(int));
  for(int i = 0; i < size; i++)
    for(int j = 0; j < size; j++)
      S[i][j]=0;

  int rSum;
  for(int i = 0; i < size; i++){
    for(int j = 0; j < size; j++){
      rSum = 0;
      for(int k = 0;k < size; k++)
	rSum+=(M[i][k])*(N[k][j]);
	  
    S[i][j]=rSum;
    }
  }
  if(print==1)
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
	printf("%d ",S[i][j]);
      }
      printf("\n");
    }
}

  

