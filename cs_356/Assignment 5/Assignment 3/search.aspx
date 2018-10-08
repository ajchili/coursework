<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="search.aspx.cs" Inherits="Assignment_3.search" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <link href="css/StyleSheet1.css" rel="stylesheet" />
    <!-- Material Bootstrap CSS components -->
	<link rel="stylesheet"
		  href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
		  integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
		  crossorigin="anonymous" />
	<title>Search Users</title>
</head>
<body>
    <form runat="server">
        <asp:SqlDataSource ID="SQLServerWebProjectSearchDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:SQLServerWebProjectDB %>" SelectCommand="SELECT * FROM [tblCustomers]"></asp:SqlDataSource>
        <asp:GridView ID="gridTable" class="table" runat="server" AllowPaging="True" AllowSorting="True" AutoGenerateColumns="False" DataKeyNames="CustomerID" DataSourceID="SQLServerWebProjectSearchDataSource" EmptyDataText="No data was found.">
            <Columns>
                <asp:CommandField ShowSelectButton="True" />
                <asp:BoundField DataField="CustomerID" HeaderText="CustomerID" InsertVisible="False" ReadOnly="True" SortExpression="CustomerID" />
                <asp:BoundField DataField="LastName" HeaderText="LastName" SortExpression="LastName" />
                <asp:BoundField DataField="Phone" HeaderText="Phone" SortExpression="Phone" />
                <asp:BoundField DataField="EmailAddress" HeaderText="EmailAddress" SortExpression="EmailAddress" />
                <asp:BoundField DataField="UserName" HeaderText="UserName" SortExpression="UserName" />
                <asp:BoundField DataField="Password" HeaderText="Password" SortExpression="Password" />
            </Columns>
        </asp:GridView>
        
        <div class="container">
            <div class="row">
                <div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Customer ID"></asp:Label>
						<asp:TextBox ID="txtSearchID" runat="server" type="text" class="form-control" MaxLength="30"></asp:TextBox>
					</div>
                </div>
                <div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Last Name"></asp:Label>
						<asp:TextBox ID="txtSearchLastName" runat="server" type="text" class="form-control" MaxLength="30"></asp:TextBox>
					</div>
                </div>
                <div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Username"></asp:Label>
						<asp:TextBox ID="txtSearchUsername" runat="server" type="text" class="form-control" MaxLength="30"></asp:TextBox>
					</div>
                </div>
                <div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Email Address"></asp:Label>
						<asp:TextBox ID="txtSearchEmail" runat="server" type="text" class="form-control" MaxLength="100"></asp:TextBox>
					</div>
                </div>
                <div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Phone"></asp:Label>
						<asp:TextBox ID="txtSearchPhone" runat="server" type="number" class="form-control" MaxLength="12"></asp:TextBox>
					</div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <asp:Button ID="btnSearch" runat="server" class="btn btn-primary btn-raised" UseSubmitBehavior="false" Text="Search" OnClick="searchDatabase" />
                    <a href="index.aspx" class="btn btn-secondary btn-outline">Home</a>
                </div>
            </div>
        </div>
     </form>

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
	<script>$(document).ready(function () { $('body').bootstrapMaterialDesign(); });</script>
</body>
</html>
