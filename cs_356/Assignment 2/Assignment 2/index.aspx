<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="Assignment_2.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <link href="css/StyleSheet1.css" rel="stylesheet" />
    <!-- Material Bootstrap CSS components -->
    <link rel="stylesheet"
        href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
        integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
        crossorigin="anonymous">
    <title>Zodiac Sign</title>
</head>
<body>
    <div class="container">
        <h1>Welcome!</h1>
        <p>Enter the information below to get information about your Zodia sign.</p>
        <!-- User input -->
        <form runat="server">
            <div class="form-group">
                <asp:Label runat="server" class="bmd-label-floating" Text="Name"></asp:Label>
                <asp:TextBox ID="txtName" runat="server" type="text" class="form-control"></asp:TextBox>
            </div>
            <div class="form-group">
                <asp:Label runat="server" class="bmd-label-static" Text="Date of Birth"></asp:Label>
                <asp:TextBox ID="txtDateOfBirth" runat="server" type="date" class="form-control"></asp:TextBox>
            </div>
            <asp:Button ID="btnSubmit" runat="server" class="btn btn-primary btn-raised" Text="Submit" />
            <asp:Button ID="btnReset" runat="server" class="btn btn-danger btn-raised" Text="Reset" />
        </form>
        <!-- Error alert -->
        <asp:Panel ID="pnlError" runat="server" Visible="false">
            <div class="alert alert-danger" role="alert">
                Please ensure that all information is provided in the fields above!
            </div>
        </asp:Panel>
        <!-- Result -->
        <asp:Panel ID="pnlResult" runat="server" Visible="false">
            <div class="row">
                <div class="col-4">
                    <asp:Image ID="imgZodiacAnimal" runat="server" />
                </div>
                <div class="col-8">
                    <h5>
                        <asp:Label runat="server" Text="Your Zodiac Animal is:"></asp:Label>
                        <asp:Label ID="lblZodiacAnimal" runat="server" class="badge  badge-primary" Text=""></asp:Label>
                    </h5>
                    <hr />
                    <p>
                        <asp:Label runat="server" Text="Your Lucky Numbers are:"></asp:Label>
                        <asp:Label ID="lblLuckyNumbers" runat="server" class="badge badge-secondary" Text=""></asp:Label>
                        <br />
                        <asp:Label runat="server" Text="Your Lucky Colors are:"></asp:Label>
                        <asp:Label ID="lblLuckyColors" runat="server" class="badge badge-secondary" Text=""></asp:Label>
                        <br />
                        <asp:Label runat="server" Text="Your Lucky Flowers are:"></asp:Label>
                        <asp:Label ID="lblLuckyFlowers" runat="server" class="badge badge-secondary" Text=""></asp:Label>
                    </p>
                </div>
            </div>
        </asp:Panel>
    </div>

    <!-- Material Bootstrap Javascript components -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
        integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
        crossorigin="anonymous"></script>
    <script src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
        integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
        crossorigin="anonymous"></script>
    <script>$(document).ready(function() { $('body').bootstrapMaterialDesign(); });</script>
</body>
</html>
