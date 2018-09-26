<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="Assignment_3.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <link href="StyleSheet1.css" rel="stylesheet" />
    <!-- Material Bootstrap CSS components -->
	<link rel="stylesheet"
		  href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
		  integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
		  crossorigin="anonymous" />
	<title>Random Dog Fact Newsletter</title>
</head>
<body>
    <!-- Header, displays welcome to user -->
    <div class="jumbotron jumbotron-fluid">
		<div class="container">
            <div class="row">
                <div class="col-10">
			        <h1 class="display-4">Random Dog Fact Newsletter</h1>
			        <p class="lead">Get random dog facts texted or emailed to you daily.</p>
			        <hr class="my-4" />
			        <p>To sign up, fill out all the information below.</p>
                </div>
                <div class="col-2">
                    <a href="https://dog.ceo/" target="_blank">
                        <img id="imgDog" src="https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif" alt="Random Dog Image" class="fillDiv"/>
                    </a>
                </div>
            </div>
		</div>
	</div>

	<div class="container mt-5">
        <!--User creation form -->
		<form runat="server" class="needs-validation" novalidate>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="First Name"></asp:Label>
						<asp:TextBox ID="txtFirstName" runat="server" type="text" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">Your first name is required!</div>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Last Name"></asp:Label>
						<asp:TextBox ID="txtLastName" runat="server" type="text" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">Your last name is required!</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Username"></asp:Label>
						<asp:TextBox ID="txtUsername" runat="server" type="text" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">A username is required!</div>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Password"></asp:Label>
						<asp:TextBox ID="txtPassword" runat="server" type="password" class="form-control" required></asp:TextBox>
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
				</div>
			</div>
			<div class="form-group">
				<asp:Label runat="server" class="bmd-label-floating" Text="Email Address"></asp:Label>
				<asp:TextBox ID="txtEmail" runat="server" type="email" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">Your email address is required!</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Address"></asp:Label>
						<asp:TextBox ID="txtAddress" runat="server" type="text" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">Your home address is required!</div>
					</div>
				</div>
				<div class="col">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="City"></asp:Label>
						<asp:TextBox ID="txtCity" runat="server" type="text" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">The city you live in is required!</div>
					</div>
				</div>
            </div>
            <div class="row">
				<div class="col">
                    <label>State</label>
                    <asp:dropdownlist ID="drpState" runat="server" class="form-control">
                        <asp:ListItem value="AL">Alabama</asp:ListItem>
	                    <asp:ListItem value="AK">Alaska</asp:ListItem>
	                    <asp:ListItem value="AZ">Arizona</asp:ListItem>
	                    <asp:ListItem value="AR">Arkansas</asp:ListItem>
	                    <asp:ListItem value="CA">California</asp:ListItem>
	                    <asp:ListItem value="CO">Colorado</asp:ListItem>
	                    <asp:ListItem value="CT">Connecticut</asp:ListItem>
	                    <asp:ListItem value="DE">Delaware</asp:ListItem>
	                    <asp:ListItem value="DC">District Of Columbia</asp:ListItem>
	                    <asp:ListItem value="FL">Florida</asp:ListItem>
	                    <asp:ListItem value="GA">Georgia</asp:ListItem>
	                    <asp:ListItem value="HI">Hawaii</asp:ListItem>
	                    <asp:ListItem value="ID">Idaho</asp:ListItem>
	                    <asp:ListItem value="IL">Illinois</asp:ListItem>
	                    <asp:ListItem value="IN">Indiana</asp:ListItem>
	                    <asp:ListItem value="IA">Iowa</asp:ListItem>
	                    <asp:ListItem value="KS">Kansas</asp:ListItem>
	                    <asp:ListItem value="KY">Kentucky</asp:ListItem>
	                    <asp:ListItem value="LA">Louisiana</asp:ListItem>
	                    <asp:ListItem value="ME">Maine</asp:ListItem>
	                    <asp:ListItem value="MD">Maryland</asp:ListItem>
	                    <asp:ListItem value="MA">Massachusetts</asp:ListItem>
	                    <asp:ListItem value="MI">Michigan</asp:ListItem>
	                    <asp:ListItem value="MN">Minnesota</asp:ListItem>
	                    <asp:ListItem value="MS">Mississippi</asp:ListItem>
	                    <asp:ListItem value="MO">Missouri</asp:ListItem>
	                    <asp:ListItem value="MT">Montana</asp:ListItem>
	                    <asp:ListItem value="NE">Nebraska</asp:ListItem>
	                    <asp:ListItem value="NV">Nevada</asp:ListItem>
	                    <asp:ListItem value="NH">New Hampshire</asp:ListItem>
	                    <asp:ListItem value="NJ">New Jersey</asp:ListItem>
	                    <asp:ListItem value="NM">New Mexico</asp:ListItem>
	                    <asp:ListItem value="NY">New York</asp:ListItem>
	                    <asp:ListItem value="NC">North Carolina</asp:ListItem>
	                    <asp:ListItem value="ND">North Dakota</asp:ListItem>
	                    <asp:ListItem value="OH">Ohio</asp:ListItem>
	                    <asp:ListItem value="OK">Oklahoma</asp:ListItem>
	                    <asp:ListItem value="OR">Oregon</asp:ListItem>
	                    <asp:ListItem value="PA">Pennsylvania</asp:ListItem>
	                    <asp:ListItem value="RI">Rhode Island</asp:ListItem>
	                    <asp:ListItem value="SC">South Carolina</asp:ListItem>
	                    <asp:ListItem value="SD">South Dakota</asp:ListItem>
	                    <asp:ListItem value="TN">Tennessee</asp:ListItem>
	                    <asp:ListItem value="TX">Texas</asp:ListItem>
	                    <asp:ListItem value="UT">Utah</asp:ListItem>
	                    <asp:ListItem value="VT">Vermont</asp:ListItem>
	                    <asp:ListItem value="VA">Virginia</asp:ListItem>
	                    <asp:ListItem value="WA">Washington</asp:ListItem>
	                    <asp:ListItem value="WV">West Virginia</asp:ListItem>
	                    <asp:ListItem value="WI">Wisconsin</asp:ListItem>
	                    <asp:ListItem value="WY">Wyoming</asp:ListItem>
                    </asp:dropdownlist>
				</div>
				<div class="col mt-2">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Zip Code"></asp:Label>
						<asp:TextBox ID="txtZip" runat="server" type="number" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">Your zip code is required!</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col mt-2">
					<div class="form-group">
						<asp:Label runat="server" class="bmd-label-floating" Text="Phone Number"></asp:Label>
						<asp:TextBox ID="txtPhone" runat="server" type="number" class="form-control" required></asp:TextBox>
                        <div class="invalid-feedback">A valid phone number is required!</div>
					</div>
				</div>
				<div class="col">
                    <label>Phone Type</label>
                    <asp:dropdownlist ID="drpPhoneType" runat="server" class="form-control">
                        <asp:ListItem>Home</asp:ListItem>
                        <asp:ListItem>Mobile</asp:ListItem>
                        <asp:ListItem>Work</asp:ListItem>
                    </asp:dropdownlist>
				</div>
			</div>
            <div>
                <asp:RadioButton ID="rbYesText" runat="server" GroupName="groupGetTexts" Checked="true" />
				<asp:Label runat="server" Text="Send me daily texts about dogs!"></asp:Label>
            </div>
            <div>
                <asp:RadioButton ID="rbNoText" runat="server" GroupName="groupGetTexts" />
				<asp:Label runat="server" Text="Do not send me daily texts, I do not want to know about these cute creatures."></asp:Label>
            </div>
            <div class="row mt-3">
                <div class="col">
                    <asp:Button ID="btnReset" runat="server" class="btn btn-danger btn-raised" UseSubmitBehavior="false" Text="Reset" OnClick="resetUserInput" />
                    <asp:Button ID="btnSubmit" runat="server" class="btn btn-primary btn-raised" Text="Submit" OnClick="submitForm" />
                </div>
            </div>
		</form>
	</div>
    
	<!-- axios Javascript components -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.min.js"></script>
    <script>
        // Get random dog picture
        axios.get('https://dog.ceo/api/breeds/image/random').then(function (res) {
            if (res.status === 200) {
                document.getElementById('imgDog').src = res.data.message;
            }
        });
    </script>

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

    <!-- Material Bootstrap Front-End Form Validator -->
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
          'use strict';
          window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            Array.prototype.filter.call(forms, function(form) {
              form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                  event.preventDefault();
                  event.stopPropagation();
                }
                form.classList.add('was-validated');
              }, false);
            });
          }, false);
        })();
    </script>
</body>
</html>
