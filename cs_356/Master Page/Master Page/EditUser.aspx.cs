using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class EditUser : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["user"] != null && Session["UserName"] != null && Session["Password"] != null)
                {
                    Models.User[] users = Models.User.getAllUsers();
                    for (int i = 0; i < users.Length; i++)
                    {
                        if (users[i].Id == Convert.ToInt32(Session["user"].ToString()))
                        {
                            txtUsername.Text = users[i].UserName;
                            txtPassword.Text = users[i].Password;
                            txtFirstName.Text = users[i].FirstName;
                            txtLastName.Text = users[i].LastName;
                            txtEmail.Text = users[i].email;
                            break;
                        }
                    }
                }
                else
                {
                    // Redirects if no sesion or user is present
                    Response.Redirect("index.aspx");
                }
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
            String password = txtPassword.Text.Replace("&nbsp;", "");
            String firstName = txtFirstName.Text.Replace("&nbsp;", "");
            String lastName = txtLastName.Text.Replace("&nbsp;", "");
            String email = txtEmail.Text.Replace("&nbsp;", "");

            Models.User user = new Models.User(username, password);
            user.Id = Convert.ToInt32(Session["user"].ToString());
            user.FirstName = firstName;
            user.LastName = lastName;
            user.email = email;
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
                if (user.update())
                {
                    Response.Redirect("ViewUsers.aspx");
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