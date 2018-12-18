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
                        ddlState.SelectedIndex = Convert.ToInt32(row.Cells[4].Text) + 1;
                        ddlPriority.SelectedIndex = Convert.ToInt32(row.Cells[5].Text);
                        Models.User[] users = Models.User.getAllUsers();
                        ddlAssignedTo.SelectedIndex = -1;
                        for (int i = 0; i < users.Length; i++)
                        {
                            ddlAssignedTo.Items.Add(users[i].UserName);
                            if (row.Cells[10].Text != "&nbsp;" && Convert.ToInt32(row.Cells[10].Text) == users[i].Id)
                            {
                                ddlAssignedTo.SelectedIndex = i;
                            }
                        }
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
            String resolutionDetails = txtResolutionDetails.Text;
            int state = Convert.ToInt32(ddlState.SelectedValue);
            int priority = Convert.ToInt32(ddlPriority.SelectedValue);

            txtTitle.CssClass = "form-control is-valid";
            txtResolutionDetails.CssClass = "form-control is-valid";

            if (title.Length == 0)
            {
                canSubmit = false;
                txtTitle.CssClass = "form-control is-invalid";
            }


            if (resolutionDetails.Length == 0)
            {
                canSubmit = false;
                txtResolutionDetails.CssClass = "form-control is-invalid";
            }

            Models.User[] users = Models.User.getAllUsers();

            if (canSubmit)
            {
                Models.Ticket ticket = new Models.Ticket();
                ticket.ID = int.Parse(row.Cells[1].Text);
                ticket.Title = title;
                ticket.Description = description;
                ticket.Status = state;
                ticket.Priority = priority;
                ticket.Submitter = row.Cells[9].Text;
                if (users[ddlAssignedTo.SelectedIndex] != null)
                {
                    ticket.AssignedUser = users[ddlAssignedTo.SelectedIndex].Id;
                }
                Boolean result;
                if (shouldResolve)
                {
                    result = ticket.resolve(resolutionDetails);
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