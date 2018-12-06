using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient; //libraries for database connections
using System.Configuration; //libraries for workign with web config
using System.Data;

namespace Master_Page
{
    public partial class Main : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UserName"] != null && Session["Password"] != null)
            {
                Models.User user = new Models.User(Session["UserName"].ToString(), Session["Password"].ToString());
                // Populates grdTicketTable with tickets from database.
                SqlDataReader ticketReader = Models.Ticket.getAllTickets(grdTicketTable);
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
            GridViewRow row = grdTicketTable.SelectedRow;
            Session["ticketId"] = row.Cells[1].Text;
            Response.Redirect("ViewTicket.aspx");
        }
    }
}