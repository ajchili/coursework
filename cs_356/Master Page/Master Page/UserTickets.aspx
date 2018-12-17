<%@ Page Title="" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="UserTickets.aspx.cs" Inherits="Master_Page.UserTickets" %>
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
                <a class="nav-link active">My Tickets</a>
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
<div class="row no-gutters">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Resolved Tickets</h5>
                <div class="overflow-scroll-x">
                    <asp:GridView ID="grdResolvedTicketTable" class="table bg-light" runat="server" AllowPaging="False" AllowSorting="False" AutoGenerateColumns="False" DataKeyNames="id" EmptyDataText="No data was found." OnSelectedIndexChanged="gridViewSelectedIndexChanged" OnRowDataBound="gridViewDataBound">
                        <Columns>
                            <asp:CommandField ShowSelectButton="True" />
                            <asp:BoundField DataField="id" HeaderText="#" InsertVisible="False" ReadOnly="True" SortExpression="Ticket #" />
                            <asp:BoundField DataField="title" HeaderText="Title" />
                            <asp:BoundField DataField="description" HeaderText="Description" />
                            <asp:BoundField DataField="dateCreated" HeaderText="Created" />
                            <asp:BoundField DataField="dateUpdated" HeaderText="Updated" />
                            <asp:BoundField DataField="dateResolved" HeaderText="Resolved" />
                            <asp:BoundField DataField="submitter" HeaderText="Reporter" />
                            <asp:BoundField DataField="assignedUser" HeaderText="Assignee" />
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
