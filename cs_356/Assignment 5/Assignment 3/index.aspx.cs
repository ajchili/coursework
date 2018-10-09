using System;
using System.Linq;
using System.Text.RegularExpressions;
using System.Data.SqlClient; //libraries for database connections
using System.Configuration; //libraries for workign with web config

namespace Assignment_3
{
    public partial class index : System.Web.UI.Page
    {
        /*
         * Checks to see if there are existing records at a provided field with the specified value.
         * The function returns true if no records exist and false if there are one or more existing
         * records.
         */
        protected Boolean checkAgainstDatabase(String field, String value)
        {
            SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);
            conn.Open();
            string sqlCommand = "SELECT count(*) FROM tblCustomers WHERE " + field + "='" + value + "';";
            SqlCommand cmd = new SqlCommand(sqlCommand, conn);
            Boolean doesExist = Convert.ToInt32(cmd.ExecuteScalar().ToString()) <= 0;
            conn.Close();
            return doesExist;
        }

        /*
         * Takes provided information and inserts it into the SQLServerWebProjectDB.
         * The function returns the number of rows affected.
         */
        protected int insertNewUser(String firstName, String lastName, String address, String city, String state, String zip, String phone, String phoneType, String emailAddress, String username, String password)
        {
            SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);
            conn.Open();
            string sqlInsertNewCustomer = "insert into tblCustomers(FirstName," +
                "LastName," +
                "Address," +
                "City," +
                "State," +
                "ZipCode," +
                "Phone," +
                "PhoneType," +
                "EmailAddress," +
                "UserName," +
                "Password)" +
                "values('" + firstName + "'," +
                "'" + lastName + "'," +
                "'" + address + "'," +
                "'" + city + "'," +
                "'" + state + "'," +
                "'" + zip + "'," +
                "'" + phone + "'," +
                "'" + phoneType + "'," +
                "'" + emailAddress + "'," +
                "'" + username + "'," +
                "'" + password + "')";
            SqlCommand cmd = new SqlCommand(sqlInsertNewCustomer, conn);
            int count = cmd.ExecuteNonQuery();
            conn.Close();
            return count;
        }

        // Determin if results should be displayed based on provided information, then, calculates results and displays them.
        protected void submitForm(object sender, EventArgs e)
        {
            // Resets password field for security reasons
            string password = txtPassword.Text;
            txtPassword.Text = "";

            // Regex to match symbols in password
            Regex regex = new Regex("[!@#$%^&*(),.?\":{}|<>]");

            bool canSubmit = true;

            // Validate password requiremenets
            if (password.Length >= 8 && password.Any(char.IsDigit) && regex.IsMatch(password))
            {
                // Password is validated
                txtPassword.CssClass = "form-control is-valid";
            }
            else
            {
                canSubmit = false;
                // Password is not validated
                txtPassword.CssClass = "form-control is-invalid";
            }

            if (txtPhone.Text.Length >= 10)
            {
                // Phone Number is validated
                txtPhone.CssClass = "form-control is-valid";
            }
            else
            {
                canSubmit = false;
                // Phone Number is not validated
                txtPhone.CssClass = "form-control is-invalid";
            }

            if (checkAgainstDatabase("UserName", txtUsername.Text))
            {
                lblUsernameError.Visible = false;
            }
            else
            {
                canSubmit = false;
                lblUsernameError.Visible = true;
            }

            if (checkAgainstDatabase("EmailAddress", txtEmail.Text))
            {
                lblEmailErrror.Visible = false;
            }
            else
            {
                canSubmit = false;
                lblEmailErrror.Visible = true;
            }

            // Submit form
            if (canSubmit)
            {
                lblInsertError.Visible = false;

                Session["username"] = txtUsername.Text;
                Session["password"] = password;
                Session["firstName"] = txtFirstName.Text;
                Session["lastName"] = txtLastName.Text;
                Session["email"] = txtEmail.Text;
                Session["address"] = txtAddress.Text;
                Session["city"] = txtCity.Text;
                Session["state"] = drpState.Text;
                Session["zip"] = txtZip.Text;
                Session["phone"] = txtPhone.Text;
                Session["phoneType"] = drpPhoneType.Text;
                Session["receiveTexts"] = rbYesText.Checked;

                // Inserts new user data
                if (insertNewUser(txtFirstName.Text,
                    txtLastName.Text,
                    txtAddress.Text,
                    txtCity.Text,
                    drpState.Text,
                    txtZip.Text,
                    txtPhone.Text,
                    drpPhoneType.Text,
                    txtEmail.Text,
                    txtUsername.Text,
                    password) == 1)
                {
                    // Data inserted succesfully
                    Response.Redirect("success.aspx");
                }
                else
                {
                    // Error inserting data
                    lblInsertError.Visible = true;
                }
            }
        }

        // Resets information fields and views
        protected void resetUserInput(object sender, EventArgs e)
        {
            txtUsername.Text = "";
            txtPassword.Text = "";
            txtFirstName.Text = "";
            txtLastName.Text = "";
            txtEmail.Text = "";
            txtAddress.Text = "";
            txtCity.Text = "";
            txtZip.Text = "";
            txtPhone.Text = "";
            drpState.Text = "AL";
            drpPhoneType.Text = "Home";
            rbYesText.Checked = true;
            rbNoText.Checked = false;
        }
    }
}