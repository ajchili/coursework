using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment_3
{
    public partial class success : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["firstName"] != null)
            {
                // Setup user information
                User user = new User();
                user.FirstName = Session["firstName"].ToString();
                user.LastName = Session["lastName"].ToString();
                user.Username = Session["username"].ToString();
                user.Email = Session["email"].ToString();

                lblFirstName.Text = user.FirstName;
                lblLastName.Text = user.LastName;
            }
            else
            {
                // Redirect as no information is provided
                Response.Redirect("index.aspx");
            }
        }
    }
}