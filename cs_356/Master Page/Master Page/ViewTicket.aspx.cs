using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class ViewTicket : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["ticketId"] != null)
                {
                    Models.Ticket.get(grdTicketTable, int.Parse(Session["ticketId"].ToString()));
                    if (Session["UserName"] != null && Session["Password"] != null)
                    {
                        grdTicketTable.Visible = false;
                        editPanel.Visible = true;
                        GridViewRow row = grdTicketTable.Rows[0];

                        txtTitle.Text = row.Cells[2].Text;
                        txtDescription.Text = row.Cells[3].Text;
                        txtState.Text = row.Cells[4].Text;
                        txtAssignedTo.Text = row.Cells[9].Text;
                    }
                }
                else
                {
                    // Redirects if no ticket id is present
                    Response.Redirect("Main.aspx");
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

        private void handleTicketAction(Boolean shouldResolve)
        {
            GridViewRow row = grdTicketTable.Rows[0];
            Boolean canSubmit = true;
            String title = txtTitle.Text;
            String description = txtDescription.Text;
            int state;
            int assignedTo;

            txtTitle.CssClass = "form-control is-valid";
            txtState.CssClass = "form-control is-valid";
            txtAssignedTo.CssClass = "form-control is-valid";

            if (title.Length == 0)
            {
                canSubmit = false;
                txtTitle.CssClass = "form-control is-invalid";
            }

            if (!Int32.TryParse(txtState.Text, out state))
            {
                canSubmit = false;
                txtState.CssClass = "form-control is-invalid";
            }


            Int32.TryParse(txtAssignedTo.Text, out assignedTo);

            if (canSubmit)
            {
                Models.Ticket ticket = new Models.Ticket();
                ticket.ID = int.Parse(row.Cells[1].Text);
                ticket.Title = title;
                ticket.Description = description;
                ticket.Status = state;
                ticket.Submitter = row.Cells[8].Text;
                ticket.AssignedUser = assignedTo;
                Boolean result;
                if (shouldResolve)
                {
                    result = ticket.resolve();
                }
                else
                {
                    result = ticket.edit();
                }

                if (result)
                {
                    Response.Redirect("Main.aspx");
                }
            }
        }

        protected void submitForm(object sender, EventArgs e)
        {
            handleTicketAction(false);
        }

        protected void resolveForm(object sender, EventArgs e)
        {
            handleTicketAction(true);
        }
    }
}