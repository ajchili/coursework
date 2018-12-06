﻿<%@ Page Title="" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="Main.aspx.cs" Inherits="Master_Page.Main" %>
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
                <a class="nav-link" href="/CreateTicket.aspx">Create Ticket</a>
            </li>
        </ul>
    </div>
</asp:Content>
<asp:Content ContentPlaceHolderID="main" runat="server">
    <asp:GridView ID="grdTicketTable" class="table bg-light" runat="server" AllowPaging="False" AllowSorting="False" AutoGenerateColumns="False" DataKeyNames="id" EmptyDataText="No data was found." OnSelectedIndexChanged="gridViewSelectedIndexChanged">
        <Columns>
            <asp:CommandField ShowSelectButton="True" />
            <asp:BoundField DataField="id" HeaderText="Ticket #" InsertVisible="False" ReadOnly="True" SortExpression="Ticket #" />
            <asp:BoundField DataField="title" HeaderText="Title" />
            <asp:BoundField DataField="description" HeaderText="Description" />
            <asp:BoundField DataField="dateCreated" HeaderText="Created On" />
            <asp:BoundField DataField="dateUpdated" HeaderText="Last Updated" />
            <asp:BoundField DataField="dateResolved" HeaderText="Resolved On" />
            <asp:BoundField DataField="submitter" HeaderText="Reporter" />
            <asp:BoundField DataField="assignedUser" HeaderText="Assigned To" />
        </Columns>
    </asp:GridView>
</asp:Content>
<asp:Content ContentPlaceHolderID="footer" runat="server">
</asp:Content>
