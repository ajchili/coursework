using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI.WebControls;
using System.Text.RegularExpressions;
using System.Data.SqlClient; //libraries for database connections
using System.Configuration; //libraries for workign with web config
using System.Data;

namespace Master_Page.Models
{

    public class User
    {
        private static SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);

        public int Id { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public String UserName { get; set; }

        public String Password { get; set; }

        public String email { get; set; }

        public User(string UserName, string Password)
        {
            this.UserName = UserName;
            this.Password = Password;
        }

        public static Boolean deactiavteUser(int id)
        {
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("DeactivateUser", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@intId", SqlDbType.Int).Value = id;
            int count = cmd.ExecuteNonQuery();
            conn.Close();
            return count == 1;
        }

        public static Boolean activateUser(int id, String password)
        {
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("ActivateUser", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@intId", SqlDbType.Int).Value = id;
            cmd.Parameters.Add("@strPassword", SqlDbType.VarChar).Value = password;
            int count = cmd.ExecuteNonQuery();
            conn.Close();
            return count == 1;
        }

        public static User[] getAllUsers()
        {
            List<User> list = new List<User>();
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("GetAllUsers", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            SqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                User user = new User(reader["username"].ToString(), reader["password"].ToString());
                user.Id = Convert.ToInt32(reader["id"].ToString());
                user.FirstName = reader["firstName"].ToString();
                user.LastName = reader["lastName"].ToString();
                user.email = reader["email"].ToString();
                list.Add(user);
            }
            conn.Close();
            return list.ToArray();
        }

        /**
         * Gets all PAP Users.
         */
        public static SqlDataReader getAllUsers(GridView gridView)
        {
            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("GetAllUsers", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            SqlDataReader reader = cmd.ExecuteReader();
            gridView.DataSource = reader;
            gridView.DataBind();
            conn.Close();
            return reader;
        }

        /**
         * Attempts to login current user.
         */
        public Boolean login()
        {
            if (isValidUserName() && isValidPassword())
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("LoginUser", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = UserName;
                cmd.Parameters.Add("@strPassword", SqlDbType.VarChar).Value = Password;
                int count = cmd.ExecuteNonQuery();
                conn.Close();
                return count == 1;
            }
            else
            {
                return false;
            }
        }

        /**
         * Returns id of current user.
         */
        public int getId()
        {
            if (UserName != null && Password != null)
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("DoesUserExist", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = UserName;
                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    int id = Convert.ToInt32(reader["id"].ToString());
                    conn.Close();
                    return id;
                }
                return -1;
            }
            else
            {
                return -1;
            }
        }

        /**
         * Checks to see if object UserName is already taken within the database.
         */
        private Boolean canCreate()
        {
            if (isValidUserName() && isValidPassword())
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("DoesUserExist", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = UserName;
                int count = cmd.ExecuteNonQuery();
                conn.Close();
                return count < 1;
            }
            else
            {
                return false;
            }
        }

        /**
         * Checks to see if object UserName meets provided requirements.
         */
        public Boolean isValidUserName()
        {
            return UserName.Length >= 5 && UserName.Length <= 30;
        }

        /**
         * Checks to see if object Password meets provided requirements.
         */
        public Boolean isValidPassword()
        {
            Regex regex = new Regex("[!@#$%^&*(),.?\":{}|<>]");
            return Password.Length >= 8 && Password.Any(char.IsDigit) && regex.IsMatch(Password);
        }

        /**
         * Creates a user within the database with provided User object.
         */
        public Boolean create()
        {
            if (canCreate())
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("CreateUser", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = UserName;
                cmd.Parameters.Add("@strPassword", SqlDbType.VarChar).Value = Password;
                int created = cmd.ExecuteNonQuery();
                conn.Close();
                return created == 1;
            }
            else
            {
                return false;
            }
        }

        public Boolean update()
        {
            if (Password.Length > 0)
            {
                if (!isValidPassword())
                {
                    return false;
                }
            }

            if (!isValidUserName())
            {
                return false;
            }
            
            if (Id < 0)
            {
                return false;
            }

            conn.Open();
            conn.ChangeDatabase("PetAPuppyKirinPatel");
            SqlCommand cmd = new SqlCommand("EditUser", conn);
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Add("@intId", SqlDbType.Int).Value = Id;
            cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = UserName;
            cmd.Parameters.Add("@strFirstName", SqlDbType.VarChar).Value = FirstName;
            cmd.Parameters.Add("@strLastName", SqlDbType.VarChar).Value = LastName;
            cmd.Parameters.Add("@strEmail", SqlDbType.VarChar).Value = email;
            cmd.Parameters.Add("@strPassword", SqlDbType.VarChar).Value = Password;
            int updated = cmd.ExecuteNonQuery();
            conn.Close();
            return updated == 1;
        }
    }
}