using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class Login : System.Web.UI.Page
    {
        // Validates user login
        protected void submitForm(object sender, EventArgs e)
        {
            Boolean canSubmit = true;
            String username = txtUsername.Text;
            String password = txtPassword.Text;

            Models.User user = new Models.User(username, password);
            txtUsername.CssClass = "form-control is-valid";
            txtPassword.CssClass = "form-control is-valid";
            pnlError.Visible = false;

            if (!user.isValidUserName())
            {
                // Username is not valid
                canSubmit = false;
                txtUsername.CssClass = "form-control is-invalid";
            }

            if (!user.isValidPassword())
            {
                // Password is not valid
                canSubmit = false;
                txtPassword.CssClass = "form-control is-invalid";
            }

            if (canSubmit)
            {
                if (user.login())
                {
                    // User is logged in
                    Session["UserName"] = user.UserName;
                    Session["Password"] = user.Password;
                    Response.Redirect("main.aspx");
                }
                else
                {
                    // Some other error such as no user exists or username/password are incorrect
                    pnlError.Visible = true;
                }
            }
        }
    }
}