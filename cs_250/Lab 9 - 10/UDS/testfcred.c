#include	<sys/param.h>
#include	<sys/ucred.h>

int main()
{
	printf("sizeof(struct ucred) = %d\n", sizeof(struct ucred));
	printf("sizeof(struct cmsghdr) = %d\n", sizeof(struct cmsghdr));
	exit(0);
}
