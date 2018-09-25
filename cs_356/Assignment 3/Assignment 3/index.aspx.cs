using System;
using System.Linq;
using System.Text.RegularExpressions;

namespace Assignment_3
{
    public partial class index : System.Web.UI.Page
    {
        // Determines if results should be displayed based on provided information, then, calculates results and displays them.
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

            // Submit form
            if (canSubmit)
            {
                Session["username"] = txtUsername.Text;
                Session["password"] = password;
                Session["firstName"] = txtFirstName.Text;
                Session["lastName"] = txtLastName.Text;
                Session["email"] = txtEmail.Text;
                Session["address"] = txtAddress.Text;
                Session["city"] = txtAddress.Text;
                Session["state"] = drpState.Text;
                Session["zip"] = txtZip.Text;
                Session["phone"] = txtPhone.Text;
                Session["phoneType"] = drpPhoneType.Text;
                Session["receiveTexts"] = rbYesText.Checked;
                Response.Redirect("success.aspx");
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