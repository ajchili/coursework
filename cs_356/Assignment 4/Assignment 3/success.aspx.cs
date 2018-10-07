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
            // Checks for session information
            if (Session["firstName"] != null)
            {
                // Setup user information
                User user = new User();
                user.FirstName = Session["firstName"].ToString();
                user.LastName = Session["lastName"].ToString();
                user.Username = Session["username"].ToString();
                user.Email = Session["email"].ToString();
                
                // Displays session and user information
                lblFirstName.Text = user.FirstName;
                lblLastName.Text = user.LastName;
                lblUsername.Text = user.Username;
                lblEmail.Text = user.Email;
                lblPassword.Text = Session["password"].ToString();
                lblAddress.Text = Session["address"].ToString();
                lblCity.Text = Session["city"].ToString();
                lblState.Text = Session["state"].ToString();
                lblZip.Text = Session["zip"].ToString();
                lblPhone.Text = Session["phone"].ToString();
                lblPhoneType.Text = Session["phoneType"].ToString();
                lblReceiveNotifications.Visible = Session["receiveTexts"].Equals(false);
            }
            else
            {
                // Redirect as no information is provided
                Response.Redirect("index.aspx");
            }
        }
    }
}