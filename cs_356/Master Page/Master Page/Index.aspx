<%@ Page Title="Pet a Puppy" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="Master_Page.Index" %>
<asp:Content ContentPlaceHolderID="headers" runat="server">
</asp:Content>

<asp:Content ContentPlaceHolderID="navbarContent" runat="server">
    <a class="navbar-brand" href="/Index.aspx">Pet A Puppy</a>
</asp:Content>

<asp:Content ContentPlaceHolderID="main" runat="server">
    <div class="container content mt-5">
        <h2>Pet a Puppy Site Map</h2>
        <asp:SiteMapDataSource ID="smPAP" runat="server" />
        <asp:TreeView runat="server" DataSourceID="smPAP">
        </asp:TreeView>
    </div>
</asp:Content>

<asp:Content ContentPlaceHolderID="footer" runat="server">
</asp:Content>
