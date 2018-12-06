<%@ Page Title="" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="CreateTicket.aspx.cs" Inherits="Master_Page.CreateTicket" %>
<asp:Content ID="Content1" ContentPlaceHolderID="headers" runat="server">
</asp:Content>
<asp:Content ContentPlaceHolderID="navbarContent" runat="server">
    <a class="navbar-brand" href="/Index.aspx">Pet A Puppy</a>
</asp:Content>
<asp:Content ContentPlaceHolderID="main" runat="server">
    <div class="bg-light">
        <div class="container">
            <h1 class="h3 mb-3 font-weight-normal text-center">Create new Ticket</h1>
			<div class="form-group">
                <label for="main_txtTitle">Title</label>
				<asp:TextBox id="txtTitle" runat="server" type="text" class="form-control" placeholder="Title" MaxLength="100"></asp:TextBox>
                <div class="invalid-feedback">A title is required.</div>
			</div>
			<div class="form-group">
                <label for="main_txtDescription">Description</label>
				<asp:TextBox id="txtDescription" runat="server" type="text" class="form-control" placeholder="Description" MaxLength="255"></asp:TextBox>
			</div>
	        <div class="form-group">
                <label for="main_txtEmail">Email Addres</label>
		        <asp:TextBox id="txtEmail" runat="server" type="email" class="form-control" placeholder="Email Address" MaxLength="100"></asp:TextBox>
                <div class="invalid-feedback">A valid email address is required.</div>
	        </div>
            <asp:Button ID="btnSubmit" runat="server" class="btn btn-lg btn-primary btn-block mt-3" Text="Submit" OnClick="submitForm" />
            <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
        </div>
    </div>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="footer" runat="server">
</asp:Content>
