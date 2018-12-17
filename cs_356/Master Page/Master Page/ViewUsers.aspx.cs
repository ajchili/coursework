using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient; //libraries for database connections

namespace Master_Page
{
    public partial class ViewUsers : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserName"] != null && Session["Password"] != null)
            {
                Models.User user = new Models.User(Session["UserName"].ToString(), Session["Password"].ToString());
                SqlDataReader reader = Models.User.getAllUsers(grdUsersTable);
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

        protected void gridViewSelectedIndexChanged(object sender, EventArgs e)
        {
            GridView gridView = (GridView)sender;
            GridViewRow row = gridView.SelectedRow;

            String action = ((LinkButton)row.Cells[0].Controls[0]).Text.ToLower();
            if (action == "activate")
            {
                Models.User.activateUser(Convert.ToInt32(row.Cells[2].Text), "SuperSecretPasswordThatMustBeUpdated123!@#");
                Response.Redirect("ViewUsers.aspx");
            }
            else if (action == "deactivate")
            {
                Models.User.deactiavteUser(Convert.ToInt32(row.Cells[2].Text));
                Response.Redirect("ViewUsers.aspx");
            }
        }

        protected void gridViewEditing(object sender, GridViewEditEventArgs e)
        {
            GridView gridView = (GridView)sender;
            GridViewRow row = gridView.Rows[e.NewEditIndex];
            Session["user"] = row.Cells[2].Text;
            Response.Redirect("EditUser.aspx");
        }

        protected void gridViewDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                String password = e.Row.Cells[7].Text;
                if (password != "&nbsp;")
                {
                    ((LinkButton)e.Row.Cells[0].Controls[0]).Text = "Deactivate";
                }
                else
                {
                    ((LinkButton)e.Row.Cells[0].Controls[0]).Text = "Activate";
                    ((LinkButton)e.Row.Cells[1].Controls[0]).Enabled = false;
                    e.Row.Attributes.Add("class", "bg-danger");
                }
            }
        }
    }
}