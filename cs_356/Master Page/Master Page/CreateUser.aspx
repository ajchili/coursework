<%@ Page Title="" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="CreateUser.aspx.cs" Inherits="Master_Page.CreateUser" %>
<asp:Content ContentPlaceHolderID="headers" runat="server">
</asp:Content>
<asp:Content ContentPlaceHolderID="navbarContent" runat="server">
    <a class="navbar-brand" href="/Main.aspx">Pet A Puppy</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/UserTickets.aspx">My Tickets</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/ViewUsers.aspx">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/CreateTicket.aspx">Create Ticket</a>
            </li>
        </ul>
    </div>
</asp:Content>
<asp:Content ContentPlaceHolderID="main" runat="server">
    <div class="bg-light">
        <div class="container">
            <h1 class="h3 mb-3 font-weight-normal text-center">Create new User</h1>
			<div class="form-group">
                <label for="main_txtUsername">Username</label>
				<asp:TextBox id="txtUsername" runat="server" type="text" class="form-control" placeholder="Username" MaxLength="30"></asp:TextBox>
                <div class="invalid-feedback">Your username must be at least 5 characters short and 30 characters long.</div>
			</div>
			<div class="form-group">
                <label for="main_txtPassword">Password</label>
				<asp:TextBox ID="txtPassword" runat="server" type="password" class="form-control" placeholder="Password" MaxLength="60"></asp:TextBox>
                <div class="invalid-feedback">
                    A password is required!
                    <br />
                    It must be at least 8 characters or longer
                    <br />
                    It must contain at least one number
                    <br />
                    It must contain at least one special character
                </div>
			</div>
            <asp:Button ID="btnSubmit" runat="server" class="btn btn-lg btn-primary btn-block mt-3" Text="Submit" OnClick="submitForm" />
            <asp:Panel ID="pnlError" runat="server" class="alert alert-danger" role="alert" Visible="false">
                Unable to create user.
            </asp:Panel>
        </div>
    </div>
</asp:Content>
<asp:Content ContentPlaceHolderID="footer" runat="server">
</asp:Content>
