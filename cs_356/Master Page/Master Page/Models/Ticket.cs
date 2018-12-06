using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient; //libraries for database connections
using System.Configuration; //libraries for workign with web config
using System.Data;

namespace Master_Page.Models
{
    public class Ticket
    {
        private static SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);

        public int ID { get; set; }

        public String Title { get; set; }

        public String Description { get; set; }

        public int Status { get; set; }

        public DateTime DateCreated { get; set; }

        public DateTime DateUpdated { get; set; }

        public DateTime DateResolved { get; set; }

        public String Submitter { get; set; }

        public int AssignedUser { get; set; }

        /**
         * Gets all PAP Tickets.
         */
        public static SqlDataReader getAllTickets(GridView gridView)
        {
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("GetAllTickets", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            SqlDataReader reader = cmd.ExecuteReader();
            gridView.DataSource = reader;
            gridView.DataBind();
            conn.Close();
            return reader;
        }

        /**
         * Gets all PAP Tickets that are submitted by specified submitter.
         */
        public static SqlDataReader getTickets(GridView gridView, String submitter)
        {
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("GetTickets", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@strSubmitter", SqlDbType.VarChar).Value = submitter;
            SqlDataReader reader = cmd.ExecuteReader();
            gridView.DataSource = reader;
            gridView.DataBind();
            conn.Close();
            return reader;
        }

        /**
         * Gets all PAP Ticket with specified ID.
         */
        public static SqlDataReader get(GridView gridView, int id)
        {
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("GetTicket", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@intID", SqlDbType.Int).Value = id;
            SqlDataReader reader = cmd.ExecuteReader();
            gridView.DataSource = reader;
            gridView.DataBind();
            conn.Close();
            return reader;
        }

        /**
         * Creates a new ticket based on properties of Ticket object.
         */
        public Boolean create()
        {
            if (Title != null && Submitter != null)
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("CreateTicket", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@strTitle", SqlDbType.VarChar).Value = Title;
                cmd.Parameters.Add("@strDescription", SqlDbType.VarChar).Value = Description;
                cmd.Parameters.Add("@intCurrentState", SqlDbType.Int).Value = 1;
                cmd.Parameters.Add("@dateResolved", SqlDbType.DateTime).Value = DBNull.Value;
                cmd.Parameters.Add("@strSubmitter", SqlDbType.VarChar).Value = Submitter;
                cmd.Parameters.Add("@intAssignedUser", SqlDbType.Int).Value = DBNull.Value;
                int created = cmd.ExecuteNonQuery();
                conn.Close();
                return created == 1;
            } else
            {
                return false;
            }
        }

        public Boolean edit()
        {
            if (Title != null && Submitter != null)
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("EditTicket", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@intId", SqlDbType.Int).Value = ID;
                cmd.Parameters.Add("@strTitle", SqlDbType.VarChar).Value = Title;
                cmd.Parameters.Add("@strDescription", SqlDbType.VarChar).Value = Description;
                cmd.Parameters.Add("@intCurrentState", SqlDbType.Int).Value = Status;
                cmd.Parameters.Add("@dateResolved", SqlDbType.DateTime).Value = DBNull.Value;
                cmd.Parameters.Add("@strSubmitter", SqlDbType.VarChar).Value = Submitter;
                if (AssignedUser != 0)
                {
                    cmd.Parameters.Add("@intAssignedUser", SqlDbType.Int).Value = AssignedUser;
                }
                else
                {
                    cmd.Parameters.Add("@intAssignedUser", SqlDbType.Int).Value = DBNull.Value;
                }
                int edited = cmd.ExecuteNonQuery();
                conn.Close();
                return edited == 1;
            }
            else
            {
                return false;
            }
        }

        public Boolean resolve()
        {
            if (Title != null && Submitter != null)
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("EditTicket", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@intId", SqlDbType.Int).Value = ID;
                cmd.Parameters.Add("@strTitle", SqlDbType.VarChar).Value = Title;
                cmd.Parameters.Add("@strDescription", SqlDbType.VarChar).Value = Description;
                cmd.Parameters.Add("@intCurrentState", SqlDbType.Int).Value = Status;
                cmd.Parameters.Add("@dateResolved", SqlDbType.DateTime).Value = DateTime.Now;
                cmd.Parameters.Add("@strSubmitter", SqlDbType.VarChar).Value = Submitter;
                if (AssignedUser != 0)
                {
                    cmd.Parameters.Add("@intAssignedUser", SqlDbType.Int).Value = AssignedUser;
                }
                else
                {
                    cmd.Parameters.Add("@intAssignedUser", SqlDbType.Int).Value = DBNull.Value;
                }
                int resolved = cmd.ExecuteNonQuery();
                conn.Close();
                return resolved == 1;
            }
            else
            {
                return false;
            }
        }
    }
}