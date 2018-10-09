using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment_3
{
    public partial class search : System.Web.UI.Page
    {

        // Searches database based on provided inputs
        protected void searchDatabase(object sender, EventArgs e)
        {
            List<String> whereClauses = new List<String>();

            // Adds CustomerID to search if required
            if (txtSearchID.Text.Length > 0)
            {
                whereClauses.Add("CustomerID=" + txtSearchID.Text);
            }

            // Adds Last Name to search if required
            if (txtSearchLastName.Text.Length > 0)
            {
                whereClauses.Add("LastName=" + txtSearchLastName.Text);
            }

            // Adds Username to search if required
            if (txtSearchUsername.Text.Length > 0)
            {
                whereClauses.Add("UserName=" + txtSearchUsername.Text);
            }

            // Adds Email to search if required
            if (txtSearchEmail.Text.Length > 0)
            {
                whereClauses.Add("EmailAddress=" + txtSearchEmail.Text);
            }

            // Adds Phone to search if required
            if (txtSearchPhone.Text.Length > 0)
            {
                whereClauses.Add("Phone=" + txtSearchPhone.Text);
            }

            // Combines all where clauses
            String whereClause = (whereClauses.ToArray().Length > 0 ? "WHERE " : " ") + String.Join(" AND ", whereClauses.ToArray());
            // Executes search
            SQLServerWebProjectSearchDataSource.SelectCommand = "SELECT * FROM tblCustomers " + whereClause;
        }
    }
}