<%@ Page Title="" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="ViewUsers.aspx.cs" Inherits="Master_Page.ViewUsers" %>
<asp:Content ContentPlaceHolderID="headers" runat="server">
    <link href="CSS/Main.css" rel="stylesheet" />
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
                <a class="nav-link active" href="/ViewUsers.aspx">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/CreateTicket.aspx">Create Ticket</a>
            </li>
        </ul>
    </div>
</asp:Content>
<asp:Content ContentPlaceHolderID="main" runat="server">
    <div class="row bg-light">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="CreateUser.aspx">Create New User</a>
            </li>
        </ul>
    </div>
<div class="row no-gutters">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">High Priority</h5>
                <div class="overflow-scroll-x">
                    <asp:GridView ID="grdUsersTable" class="table bg-light" runat="server" AllowPaging="False" AllowSorting="False" AutoGenerateColumns="False" DataKeyNames="id" EmptyDataText="No data was found." OnSelectedIndexChanged="gridViewSelectedIndexChanged" OnRowDataBound="gridViewDataBound" OnRowEditing="gridViewEditing">
                        <Columns>
                            <asp:CommandField ShowSelectButton="True" />
                            <asp:CommandField ShowEditButton="True" />
                            <asp:BoundField DataField="id" HeaderText="#" InsertVisible="False" ReadOnly="True" SortExpression="Ticket #" />
                            <asp:BoundField DataField="username" HeaderText="Username" />
                            <asp:BoundField DataField="email" HeaderText="Email Address" />
                            <asp:BoundField DataField="firstName" HeaderText="First Name" />
                            <asp:BoundField DataField="lastName" HeaderText="Last Name" />
                            <asp:BoundField DataField="password" HeaderText="password" Visible="True" />
                        </Columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
    </div>
</div>
</asp:Content>
<asp:Content ContentPlaceHolderID="footer" runat="server">
</asp:Content>
