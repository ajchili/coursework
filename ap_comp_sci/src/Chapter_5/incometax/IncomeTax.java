public class IncomeTax
{
	
	public int money;
	private double tax;
	private int rate;

	public IncomeTax() {
		
	}
	
	public IncomeTax(int money) {
		this.money = money;
	}
	
	public void getIncome(int money) {
		if (money < 50000) {
			tax = money * .01;
			money -= money;
		} else if (money >= 50000) {
			tax = 50000 * .01;
			money -= 50000;
			if (money < 25000) {
				tax += money * .02;
			} else if (money > 25000) {
				tax = 25000 * .02;
				money -= 25000;	
				if (money < 25000) {
					tax += money * .03;
				} else if (money > 25000) {
					tax = 25000 * .03;
					money -= 25000;	
					if (money < 150000) {
						tax += money * .04;
					} else if (money > 150000) {
						tax = 150000 * .04;
						money -= 150000;
						if (money < 150000) {
							tax += money * .05;
						} else if (money > 150000) {
							tax = 150000 * .05;
							money -= 150000;
							if (money > 0) {
								tax += money * .06;
								money -= money;
							}
						}
					}
				}
			}
		}
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public double returnTax() {
		return tax;
	}
	
}
