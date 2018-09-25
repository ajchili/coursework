<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="success.aspx.cs" Inherits="Assignment_3.success" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <!-- Material Bootstrap CSS components -->
	<link rel="stylesheet"
		  href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
		  integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
		  crossorigin="anonymous" />
	<title>Account Created!</title>
</head>
<body>
    <div class="jumbotron jumbotron-fluid">
		<div class="container">
            <div class="row">
                <div class="col-8">
			        <h1 class="display-4">Thank you for registering!</h1>
			        <p class="lead">
                        <asp:Label ID="lblFirstName" runat="server"></asp:Label>
                        <asp:Label ID="lblLastName" runat="server"></asp:Label>, your first random dog fact is below this!
			        </p>
			        <hr class="my-4" />
			        <h4>The image to the right is a picture of a dog! (It can also be clicked to get more information about the image!)</h4>
                </div>
                <div class="col-4">
                    <a href="https://dog.ceo/" target="_blank">
                        <img id="imgDog" src="https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif" alt="Random Dog Image" style="width: 100%"/>
                    </a>
                </div>
            </div>
		</div>
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
</body>
</html>
