<%@ Page Title="" Language="C#" MasterPageFile="~/PetAPuppy.Master" AutoEventWireup="true" CodeBehind="ViewTicket.aspx.cs" Inherits="Master_Page.ViewTicket" %>
<asp:Content ContentPlaceHolderID="headers" runat="server">
</asp:Content>
<asp:Content ContentPlaceHolderID="navbarContent" runat="server">
    <a class="navbar-brand" href="/Main.aspx">Pet A Puppy</a>
</asp:Content>
<asp:Content ContentPlaceHolderID="main" runat="server">
    <asp:GridView ID="grdTicketTable" class="table bg-light" runat="server" AllowPaging="False" AllowSorting="False" AutoGenerateColumns="False" DataKeyNames="id" EmptyDataText="No data was found.">
        <Columns>
            <asp:CommandField ShowSelectButton="True" />
            <asp:BoundField DataField="id" HeaderText="Ticket #" InsertVisible="False" ReadOnly="True" SortExpression="Ticket #" />
            <asp:BoundField DataField="title" HeaderText="Title" />
            <asp:BoundField DataField="description" HeaderText="Description" />
            <asp:BoundField DataField="currentState" HeaderText="Status" />
            <asp:BoundField DataField="ticketPriority" HeaderText="Priority" />
            <asp:BoundField DataField="dateCreated" HeaderText="Created On" />
            <asp:BoundField DataField="dateUpdated" HeaderText="Last Updated" />
            <asp:BoundField DataField="dateResolved" HeaderText="Resolved On" />
            <asp:BoundField DataField="submitter" HeaderText="Reporter" />
            <asp:BoundField DataField="assignedUser" HeaderText="Assigned To" />
        </Columns>
    </asp:GridView>
    <asp:Panel ID="editPanel" class="bg-light" runat="server" Visible="false">
        <div class="container">
            <h1 class="h3 mb-3 font-weight-normal text-center">Edit Ticket</h1>
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
                <label for="main_ddlState">State</label>
                <asp:DropDownList ID="ddlState" runat="server" class="form-control">
                    <asp:ListItem Value="-1">Backlog</asp:ListItem>
                    <asp:ListItem Value="0">Blocked</asp:ListItem>
                    <asp:ListItem Value="1">In Progress</asp:ListItem>
                    <asp:ListItem Value="2">Awaiting Testing</asp:ListItem>
                    <asp:ListItem Value="3">Done</asp:ListItem>
                </asp:DropDownList>
			</div>
			<div class="form-group">
                <label for="main_ddlPriority">Priority</label>
                <asp:DropDownList ID="ddlPriority" runat="server" class="form-control">
                    <asp:ListItem Value="0">Low</asp:ListItem>
                    <asp:ListItem Value="1">Medium</asp:ListItem>
                    <asp:ListItem Value="2">High</asp:ListItem>
                </asp:DropDownList>
			</div>
			<div class="form-group">
                <label for="main_ddlAssignedTo">Assigned To</label>
                <asp:DropDownList ID="ddlAssignedTo" runat="server" class="form-control">
                </asp:DropDownList>
                <div class="invalid-feedback">In.</div>
			</div>
            <asp:Button ID="btnSubmit" runat="server" class="btn btn-lg btn-primary btn-block mt-3" Text="Save" OnClick="submitForm" />
			<div class="form-group">
                <label for="main_txtResolutionDetails">Resolution Details</label>
				<asp:TextBox id="txtResolutionDetails" runat="server" type="text" class="form-control" placeholder="Resolution Details" MaxLength="255"></asp:TextBox>
                <div class="invalid-feedback">Details are required.</div>
			</div>
            <asp:Button ID="btnResolve" runat="server" class="btn btn-lg btn-primary btn-block mt-3" Text="Resolve" OnClick="resolveForm" />
            <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
        </div>
    </asp:Panel>
</asp:Content>
<asp:Content ContentPlaceHolderID="footer" runat="server">
</asp:Content>
