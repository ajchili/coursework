obj-m += hello_lkm.o

KDIR = //usr/src/linux-headers-3.16.0-4-amd64

all:
	$(MAKE) -C $(KDIR) SUBDIRS=$(PWD) modules

clean:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean
