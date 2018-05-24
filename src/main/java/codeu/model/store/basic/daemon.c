#include <stdio.h>

int main(){
	int pdi = fork();
	if (pid == 0)
	{
		pid = fork();
		if (pid == 0)
		{
			fclose(stdout);
			fclose(stdin);
			while(1){
			}
		}
	}
	return 0;
}