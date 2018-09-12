/* balance.c
 *
 *
 * This program may be freely distributed for class use,
 * provided that this copyright notice is retained.
 */

#include <stdio.h>

#define MONTHS_PER_YEAR 12
#define PERCENT 100.0f

int main(void)
{
  FILE *out_fp;
  out_fp = fopen("text.txt", "w");
  char file_line[80];

  float loan_amount, interest_rate, monthly_payment, monthly_interest_rate,
        balance1, balance2, balance3;

  printf("Enter amount of loan: ");
  scanf("%f", &loan_amount);
  printf("Enter interest rate: ");
  scanf("%f", &interest_rate);
  printf("Enter monthly payment: ");
  scanf("%f", &monthly_payment);

  monthly_interest_rate = (interest_rate / PERCENT) / MONTHS_PER_YEAR;

  balance1 = loan_amount - monthly_payment + (loan_amount * monthly_interest_rate);
  balance2 = balance1 - monthly_payment + (balance1 * monthly_interest_rate);
  balance3 = balance2 - monthly_payment + (balance2 * monthly_interest_rate);

  printf("\n");   /* blank line */

  printf("Balance remaining after first payment: $%.2f\n", balance1);
  printf("Balance remaining after second payment: $%.2f\n", balance2);
  printf("Balance remaining after third payment: $%.2f\n", balance3);

  fprintf(out_fp, "Balance remaining after first payment: $%.2f\n", balance1);
  fprintf(out_fp, "Balance remaining after second payment: $%.2f\n", balance2);
  fprintf(out_fp, "Balance remaining after third payment: $%.2f\n", balance3);

  fclose(out_fp);
  return 0;
}
