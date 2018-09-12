<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="Assignment_2.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
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
