public class IncomeTaxTester
{

	public static void main(String[] args) {
		IncomeTax tax = new IncomeTax();
		tax.setMoney(51000);
		tax.getIncome(tax.money);
		System.out.println(tax.returnTax());
	}
	
}
