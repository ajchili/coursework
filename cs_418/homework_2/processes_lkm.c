#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/sched.h>

//int init_module(void){
static int hello_init(void){

		  struct task_struct *task=&init_task;
		  do{
					 printk(KERN_INFO "cs418 *** %s [%d] parent %s \n", task->comm, task->pid, task->parent->comm);
#if 0					 
					 printk(KERN_INFO "cs418 *** current process name: %s \n", current->comm);
					 printk(KERN_INFO "cs418 *** current PID %i \n", current->pid);
					 printk(KERN_INFO "cs418 *** parent process name  %s\n", current->parent->comm);
					 printk(KERN_INFO "cs418 *** parent process PID  %i\n", current->parent->pid);
#endif 
					 
		  }while((task = next_task(task)) != &init_task);

		  return 0;

}// end init_module

//void cleanup_module(void){
static void hello_exit(void){
		  printk(KERN_INFO "cs418 *** removed LKM, goodbye!\n");
		//  printk(KERN_INFO "cs418 *** removed LKM current process is %s (PID %i), goodbye!\n", current->comm, current->pid);
		  return;
}

module_init(hello_init);
module_exit(hello_exit);
