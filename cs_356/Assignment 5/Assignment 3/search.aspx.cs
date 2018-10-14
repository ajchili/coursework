using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment_3
{
    public partial class search : System.Web.UI.Page
    {
        // Loads all data from database on page load
        protected void Page_Load(object sender, EventArgs e)
        {
            SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);
            conn.Open();

            SqlCommand cmd = new SqlCommand("SearchCustomers", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@strCustomerID", SqlDbType.Int).Value = DBNull.Value;
            cmd.Parameters.Add("@strLastName", SqlDbType.VarChar).Value = DBNull.Value;
            cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = DBNull.Value;
            cmd.Parameters.Add("@strEmaiLAddress", SqlDbType.VarChar).Value = DBNull.Value;
            cmd.Parameters.Add("@strPhone", SqlDbType.Int).Value = DBNull.Value;

            SqlDataReader reader = cmd.ExecuteReader();
            gridTable.DataSource = reader;
            gridTable.DataBind();

            conn.Close();
        }

        // Searches database based on provided inputs
        protected void searchDatabase(object sender, EventArgs e)
        {

            SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);
            conn.Open();

            SqlCommand cmd = new SqlCommand("SearchCustomers", conn);
            cmd.CommandType = CommandType.StoredProcedure;

            // Adds CustomerID to search if required
            if (txtSearchID.Text.Length > 0)
            {
                cmd.Parameters.Add("@strCustomerID", SqlDbType.Int).Value = txtSearchID.Text;
            }
            else
            {
                cmd.Parameters.Add("@strCustomerID", SqlDbType.Int).Value = DBNull.Value;
            }

            // Adds Last Name to search if required
            if (txtSearchLastName.Text.Length > 0)
            {
                cmd.Parameters.Add("@strLastName", SqlDbType.VarChar).Value = txtSearchLastName.Text;
            }
            else
            {
                cmd.Parameters.Add("@strLastName", SqlDbType.VarChar).Value = DBNull.Value;
            }

            // Adds Username to search if required
            if (txtSearchUsername.Text.Length > 0)
            {
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = txtSearchUsername.Text;
            }
            else
            {
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = DBNull.Value;
            }

            // Adds Email to search if required
            if (txtSearchEmail.Text.Length > 0)
            {
                cmd.Parameters.Add("@strEmaiLAddress", SqlDbType.VarChar).Value = txtSearchEmail.Text;
            }
            else
            {
                cmd.Parameters.Add("@strEmaiLAddress", SqlDbType.VarChar).Value = DBNull.Value;
            }

            // Adds Phone to search if required
            if (txtSearchPhone.Text.Length > 0)
            {
                cmd.Parameters.Add("@strPhone", SqlDbType.Int).Value = txtSearchPhone.Text;
            }
            else
            {
                cmd.Parameters.Add("@strPhone", SqlDbType.Int).Value = DBNull.Value;
            }

            SqlDataReader reader = cmd.ExecuteReader();
            gridTable.DataSource = reader;
            gridTable.DataBind();

            conn.Close();
        }
    }
}