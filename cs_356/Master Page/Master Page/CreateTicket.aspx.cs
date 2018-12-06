using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class CreateTicket : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

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
            String title = txtTitle.Text;
            String description = txtDescription.Text;
            String submitter = txtEmail.Text;

            if (title.Length > 0 && submitter.IndexOf('@') > 0 && submitter.IndexOf('.') > 0)
            {
                Models.Ticket ticket = new Models.Ticket();
                ticket.Title = title;
                ticket.Description = description;
                ticket.Submitter = submitter;
                if (ticket.create())
                {
                    Session["submitter"] = submitter;
                    Response.Redirect("ViewMyTickets.aspx");
                }
            }
        }
    }
}