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

        private void resetView()
        {
            pnlResult.Visible = false;
            pnlError.Visible = false;
        }

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

        protected void getResult(object sender, EventArgs e)
        {
            resetView();

            string name = txtName.Text;
            string dob = txtDateOfBirth.Text;
            if (name.Length > 0 && dob.Length > 0)
            {
                int year = Convert.ToInt32(dob.Substring(0, 4)) % 12;
                string zodiacAnimal = getZodiacAnimal(year);
                lblName.Text = name;
                int age = DateTime.Now.Year - Convert.ToInt32(dob.Substring(0, 4));
                if (Convert.ToInt32(dob.Substring(5, 2)) >= DateTime.Now.Month)
                {
                    if (Convert.ToInt32(dob.Substring(8, 2)) > DateTime.Now.Day)
                    {
                        age--;
                    }
                }
                lblAge.Text = age.ToString();
                lblZodiacAnimal.Text = zodiacAnimal;
                lblLuckyNumbers.Text = getLuckyNumbers(year);
                lblLuckyColors.Text = getLuckyColors(year);
                lblLuckyFlowers.Text = getLuckyFlowers(year);
                imgZodiacAnimal.ImageUrl = "~/images/" + zodiacAnimal.ToLower() + ".PNG";
                pnlResult.Visible = true;
            }
            else
            {
                pnlError.Visible = true;
            }
        }

        protected void resetUserInput(object sender, EventArgs e)
        {
            txtName.Text = "";
            txtDateOfBirth.Text = "";
            resetView();
        }
    }
}