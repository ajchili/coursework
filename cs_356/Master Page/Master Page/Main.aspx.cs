using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class Main : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserName"] != null && Session["Password"] != null)
            {
                Models.User user = new Models.User(Session["UserName"].ToString(), Session["Password"].ToString());
                Response.Write("User is authenticated via a session.");
                Response.Write(user.UserName);
                Response.Write(user.Password);
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
    }
}