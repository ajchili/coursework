using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class CreateUser : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserName"] != null && Session["Password"] != null)
            {
                Models.User user = new Models.User(Session["UserName"].ToString(), Session["Password"].ToString());
            }
            else
            {
                // Redirects if no sesion is present
                Response.Redirect("index.aspx");
            }
        }

        void Page_PreInit(object sender, EventArgs e)
        {
            if (DateTime.Now.Month == 12)
            {
                Page.Theme = "HolidayTheme";
            }
            else
            {
                Page.Theme = "DefaultTheme";
            }
        }

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
                if (user.create())
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