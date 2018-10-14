using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Assignment_3
{
    public class User
    {
        // First name parameter with C# get/set functionality
        private string firstName;
        public string FirstName
        {
            get
            {
                return this.firstName;
            }
            set
            {
                this.firstName = value;
            }
        }

        // Last name parameter with C# get/set functionality
        private string lastName;
        public string LastName
        {
            get
            {
                return this.lastName;
            }
            set
            {
                this.lastName = value;
            }
        }

        // Username parameter with C# get/set functionality
        private string username;
        public string Username
        {
            get
            {
                return this.username;
            }
            set
            {
                this.username = value;
            }
        }

        // Email parameter with C# get/set functionality
        private string email;
        public string Email
        {
            get
            {
                return this.email;
            }
            set
            {
                this.email = value;
            }
        }
    }
}