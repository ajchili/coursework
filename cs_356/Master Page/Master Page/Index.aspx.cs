using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Master_Page
{
    public partial class Index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        void Page_PreInit(object sender, EventArgs e)
        {
            DateTime date = DateTime.Now;

            if ((date.Month <= 12 && date.Day <= 30) || (date.Month == 9 && date.Day == 31))
            {
                Page.Theme = "HolidayTheme";
            }
            else
            {
                Page.Theme = "DefaultTheme";
            }
        }
    }
}