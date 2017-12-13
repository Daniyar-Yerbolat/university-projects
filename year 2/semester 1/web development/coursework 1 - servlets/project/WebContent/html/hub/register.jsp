<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Main Page</title>
<link href="../../css/common.css" rel="stylesheet" />
<link href="../../css/register.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8" />
</head>

<body>
	<header>
		<nav>
			<ul>
				<li id="home"><a href="index.jsp">&#160;</a></li>
				<li><a href="">Developers</a></li>
				<li><a href="">Country</a></li>
				<li id="current"><a href="">Genres<span id="icon"></span></a></li>
				<li><a href="">Engine</a></li>
				<li><a href="">Year</a></li>
				<li><a href="">Publishers</a></li>
				<li><a href="">Franchises</a></li>
				<li><a href="">Reviews</a></li>
				<li><a href="">Credits</a></li>
				<li id="vaultboy"></li>
			</ul>

		</nav>
	</header>

	<hr />

	<div class="block">
		<div id="container-content-4">
			<form class="register" method="POST">
				<section>
					Username: <br /> Password: <br /> Retype password: <br /> Email: <br />
					Retype email: <br />
				</section>

				<section>
					<input type="text" name="username" required="required" /> <br /> <input
						type="password" name="password1" pattern="[A-Za-z0-9._%+-]{6,20}"
						title="Password should be at least 6 characters long"
						required="required" /><br /> <input type="password"
						name="password2" pattern="[A-Za-z0-9._%+-]{6,20}"
						title="Password should be at least 6 characters long"
						required="required" /><br /> <input type="email" name="email1"
						pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
						required="required" /> <br /> <input type="email" name="email2"
						pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
						required="required" /> <br /> <select size="1"
						style="padding-left: 25px;">
						<option value="">-</option>
					</select>
				</section>

				<section>
					<br /> <br />
				</section>
				<br class="clear" /> <input type="submit" name="submit" />
			</form>
		</div>
		<br class="clear" />
	</div>

	<footer>
		<img class="avatar" src="../../images/avatar.jpg" alt="Avatar" />
		<ul>
			<li>Created by Daniel Potter.</li>
			<li>Contact through <a
				href="https://steamcommunity.com/id/daniel_faraday"><img
					src="../../images/steam.png" alt="Steam" /></a></li>
			<li>or by <a href="mailto:">email</a>.
			</li>
		</ul>

	</footer>

</body>
</html>