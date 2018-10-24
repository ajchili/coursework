<%@ Page Title="Login" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Master_Page.Login" %>
<asp:Content ContentPlaceHolderID="headers" Runat="Server">
    <link href="CSS/Login.css" rel="stylesheet" />
</asp:Content>

<asp:Content ContentPlaceHolderID="navbarContent" Runat="Server">
    <a class="navbar-brand" href="/Login.aspx">Pet A Puppy</a>
</asp:Content>

<asp:Content ContentPlaceHolderID="main" Runat="Server">
    <div class="login-container">
        <div class="login-form">
            <form runat="server">
                <img class="login-image" src="Assets/logo.png" alt="Pet A Puppy">
                <h1 class="h3 mb-3 font-weight-normal text-center">Please login</h1>
				<div class="form-group">
                    <label for="main_txtEmailAddress">Email address</label>
					<asp:TextBox id="txtEmailAddress" runat="server" type="text" class="form-control" placeholder="Email Address" MaxLength="100"></asp:TextBox>
				</div>
				<div class="form-group">
                    <label for="main_txtPassword">Password</label>
					<asp:TextBox ID="txtPassword" runat="server" type="text" class="form-control" placeholder="Password" MaxLength="50"></asp:TextBox>
				</div>
                <button class="btn btn-lg btn-primary btn-block mt-3">Login</button>
                <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
            </form>
        </div>
    </div>
</asp:Content>

<asp:Content ContentPlaceHolderID="footer" Runat="Server">
</asp:Content>