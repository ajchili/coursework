// Name: Kirin Patel
// Purpose: Assignment 2 (Zodiac Signs)
// Date: 9/12/2018

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Assignment_2
{
    public partial class index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        // Hides pnlResult and pnlError panels when called.
        private void resetView()
        {
            pnlResult.Visible = false;
            pnlError.Visible = false;
        }

        // Returns a string of the corresponding Zodiac animal for the given year.
        private string getZodiacAnimal(int year)
        {
            switch (year)
            {
                case 1:
                    return "Rooster";
                case 2:
                    return "Dog";
                case 3:
                    return "Pig";
                case 4:
                    return "Rat";
                case 5:
                    return "Ox";
                case 6:
                    return "Tiger";
                case 7:
                    return "Rabbit";
                case 8:
                    return "Dragon";
                case 9:
                    return "Snake";
                case 10:
                    return "Horse";
                case 11:
                    return "Sheep";
            }

            return "Monkey";
        }

        // Returns a string of the corresponding Zodiac lucky numbers for the given year.
        private string getLuckyNumbers(int year)
        {
            switch (year)
            {
                case 1:
                    return "5, 7 and 8";
                case 2:
                    return "3, 4 and 9";
                case 3:
                    return "2, 5 and 8";
                case 4:
                    return "2 and 3";
                case 5:
                    return "9 and 1";
                case 6:
                    return "1, 3 and 4";
                case 7:
                    return "3, 4 and 9";
                case 8:
                    return "1, 6 and 7";
                case 9:
                    return "2, 8 and 9";
                case 10:
                    return "2, 3 and 7";
                case 11:
                    return "3, 4 and 9";
            }
            return "1, 7 and 8";
        }

        // Returns a string of the corresponding Zodiac lucky colors for the given year.
        private string getLuckyColors(int year)
        {
            switch (year)
            {
                case 1:
                    return "orange, cyan and pink";
                case 2:
                    return "pink, beige and blue";
                case 3:
                    return "yellow, red and white";
                case 4:
                    return "pink, blue and white";
                case 5:
                    return "yellow, green and red";
                case 6:
                    return "red, white and beige";
                case 7:
                    return "yellow, orange and green";
                case 8:
                    return "pink, white and blue";
                case 9:
                    return "red, green and brown";
                case 10:
                    return "pink, white and red";
                case 11:
                    return "blue, red and yellow";
            }
            return "yellow, pink and white";
        }

        // Returns a string of the corresponding Zodiac lucky flowers for the given year.
        private string getLuckyFlowers(int year)
        {
            switch (year)
            {
                case 1:
                    return "gladiola, impatiens and cockscomb";
                case 2:
                    return "rose, oncidium and cymbidium orchids";
                case 3:
                    return "hydrangea, pitcher plant and marguerite";
                case 4:
                    return "lily and african violet";
                case 5:
                    return "tulip, evergreen and peach blossom";
                case 6:
                    return "cineraia and anthurium";
                case 7:
                    return "snapdragon, plantain lily and nerve plant";
                case 8:
                    return "bleeding-heart vine, larkspur and hyacinth";
                case 9:
                    return "orchid and cactus";
                case 10:
                    return "calla lily, jasmine and marigold";
                case 11:
                    return "carnation, primrose and alice flower";
            }

            return "chrysant-hemum and alliums";
        }

        // Determines if results should be displayed based on provided information, then, calculates results and displays them.
        protected void getResult(object sender, EventArgs e)
        {
            resetView();

            // Get provided information
            string name = txtName.Text;
            string dob = txtDateOfBirth.Text;

            // Verify provided information
            if (name.Length > 0 && dob.Length > 0)
            {
                // Get year of birth from provided information
                int year = Convert.ToInt32(dob.Substring(0, 4)) % 12;

                string zodiacAnimal = getZodiacAnimal(year);

                // Sets label text to results
                lblName.Text = name;
                // Calculate age
                int age = DateTime.Now.Year - Convert.ToInt32(dob.Substring(0, 4));
                if (Convert.ToInt32(dob.Substring(5, 2)) >= DateTime.Now.Month)
                {
                    if (Convert.ToInt32(dob.Substring(8, 2)) > DateTime.Now.Day)
                    {
                        // Removes one year from age if the birthday of the current year has not yet occurred
                        age--;
                    }
                }
                lblAge.Text = age.ToString();
                lblZodiacAnimal.Text = zodiacAnimal;
                lblLuckyNumbers.Text = getLuckyNumbers(year);
                lblLuckyColors.Text = getLuckyColors(year);
                lblLuckyFlowers.Text = getLuckyFlowers(year);
                imgZodiacAnimal.ImageUrl = "~/images/" + zodiacAnimal.ToLower() + ".PNG";

                // Displays results
                pnlResult.Visible = true;
            }
            else
            {
                // Displays error
                pnlError.Visible = true;
            }
        }

        // Resets information fields and views
        protected void resetUserInput(object sender, EventArgs e)
        {
            txtName.Text = "";
            txtDateOfBirth.Text = "";
            resetView();
        }
    }
}