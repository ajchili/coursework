<%@ Page Title="Login" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Master_Page.Login" %>
<asp:Content ContentPlaceHolderID="headers" Runat="Server">
    <link href="CSS/Login.css" rel="stylesheet" />
</asp:Content>

<asp:Content ContentPlaceHolderID="navbarContent" Runat="Server">
    <a class="navbar-brand" href="/Index.aspx">Pet A Puppy</a>
</asp:Content>

<asp:Content ContentPlaceHolderID="main" Runat="Server">
    <div class="bg-light">
        <div class="login-form">
            <img class="login-image" src="Assets/logo.png" alt="Pet A Puppy">
            <h1 class="h3 mb-3 font-weight-normal text-center">Please login</h1>
			<div class="form-group">
                <label for="main_txtUsername">Username</label>
				<asp:TextBox id="txtUsername" runat="server" type="text" class="form-control" placeholder="Username" MaxLength="30"></asp:TextBox>
                <div class="invalid-feedback">Your username must be at least 5 characters short and 10 characters long.</div>
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
            <asp:Button ID="btnSubmit" runat="server" class="btn btn-lg btn-primary btn-block mt-3" Text="Login" OnClick="submitForm" />
            <asp:Panel ID="pnlError" runat="server" class="alert alert-danger" role="alert" Visible="false">
                There was an error logging in, please ensure you entered your username and password correctly.
            </asp:Panel>
            <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
        </div>
    </div>
</asp:Content>

<asp:Content ContentPlaceHolderID="footer" Runat="Server">
</asp:Content>