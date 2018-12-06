using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Text.RegularExpressions;
using System.Data.SqlClient; //libraries for database connections
using System.Configuration; //libraries for workign with web config
using System.Data;

namespace Master_Page.Models
{

    public class User
    {
        private static SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["SQLServerWebProjectDB"].ConnectionString);

        public String UserName { get; set; }

        public String Password { get; set; }

        public User(string UserName, string Password)
        {
            this.UserName = UserName;
            this.Password = Password;
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
         * Checks to see if object UserName is already taken within the database.
         */
        private Boolean canCreate()
        {
            if (isValidUserName() && isValidPassword())
            {
                conn.Open();
                conn.ChangeDatabase("PetAPuppyKirinPatel");
                SqlCommand cmd = new SqlCommand("GetUser", conn);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add("@strUserName", SqlDbType.VarChar).Value = UserName;
                int count = cmd.ExecuteNonQuery();
                conn.Close();
                return count == 0;
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
            return UserName.Length >= 5 && UserName.Length <= 10;
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
    }
}