#include <stdio.h>
#include <assert.h>

int main()
{
	int i, num, j;
	printf ("Enter the number: ");
	scanf ("%d", &num );

        assert(num>0);  // poor coding style - since this should be checked other ways

	for (i=1; i<num; i++) {
		j=j*i;    
        }

	printf("The factorial of %d is %d\n",num,j);
return 0;
}
