using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class ViewMyTickets : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["submitter"] != null)
            {
                // Obtains all tickets from submitter.
                Models.Ticket.getTickets(grdTicketTable, Session["submitter"].ToString());
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
            String email = txtEmail.Text;
            txtEmail.CssClass = "form-control is-valid";

            if (email.IndexOf('@') < 0 || email.IndexOf('.') < 0)
            {
                // Username is not valid
                canSubmit = false;
                txtEmail.CssClass = "form-control is-invalid";
            }

            if (canSubmit)
            {
                Session["submitter"] = email;
                Response.Redirect("ViewMyTickets.aspx");
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