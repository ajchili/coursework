using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient; //libraries for database connections

namespace Master_Page
{
    public partial class UserTickets : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserName"] != null && Session["Password"] != null)
            {
                Models.User user = new Models.User(Session["UserName"].ToString(), Session["Password"].ToString());
                SqlDataReader resolvedReader = Models.Ticket.getResolvedTicketsForUser(grdResolvedTicketTable, user.getId());
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
            Session["ticketId"] = row.Cells[1].Text;
            Response.Redirect("ViewTicket.aspx");
        }

        protected void gridViewDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                DateTime createDate = DateTime.Parse(e.Row.Cells[4].Text.Split(' ')[0]);
                DateTime resolveDate = DateTime.Parse(e.Row.Cells[4].Text.Split(' ')[0]);
                if ((resolveDate - createDate).TotalDays >= 7)
                {
                    e.Row.Attributes.Add("class", "bg-danger");
                }
                else if ((resolveDate - createDate).TotalDays > 3)
                {
                    e.Row.Attributes.Add("class", "bg-warning");
                }
            }
        }
    }
}