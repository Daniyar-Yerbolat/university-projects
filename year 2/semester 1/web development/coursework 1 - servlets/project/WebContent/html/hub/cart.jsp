<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="java.util.*, package1.*, servlets.*, java.io.ByteArrayOutputStream, java.io.ObjectOutputStream, java.util.Base64"%>
<%!float total = 0.0f;
	String url = "";
	String serializedObject = "";%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Main Page</title>
<link href="../../css/common.css" rel="stylesheet" />
<link href="../../css/cart-page.css" rel="stylesheet" />
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
	<!-- <div class="placeholder"></div>-->
	<hr />

	<div class="block">
		<div id="container-content-4">
			<%
				total = 0.0f;
				String url = "";
				if (request.getSession() != null) {
					if (session.getAttribute("items_in_cart") != null) {
						Item[] items = (Item[]) session.getAttribute("items_in_cart");
						int x = 0;
						while (x < items.length) {
							if (items[x] != null) {
								total = total + items[x].getCost();
								url = "../games/" + items[x].getTitle().toLowerCase().trim().replace(" ", "_") + ".jsp";
								try {
									ByteArrayOutputStream bo = new ByteArrayOutputStream();
									ObjectOutputStream so = new ObjectOutputStream(bo);
									so.writeObject(items[x]);
									serializedObject = Base64.getUrlEncoder().encodeToString(bo.toByteArray());
									so.flush();
									so.close();
								} catch (Exception e) {
									System.out.println(e);
								}
			%>
			<div class="purchases_block">
				<h2><%=items[x].getTitle()%></h2>
				<a href="<%=url%>"><img class="main_poster"
					src="<%=items[x].getImage()%>" alt="game poster" /></a>
				<p class="cost"><%="$" + items[x].getCost()%></p>
				<form class="remove"
					action="${pageContext.request.contextPath}/Cart" method="post">
					<input type="hidden" name="delete" value="<%=serializedObject%>" />
					<button type="submit" style="border: 0; background: transparent">
						<img src="../../images/red_x.png" width="40px" height="40px"
							alt="submit" />
					</button>
				</form>
			</div>
			<%
				}
							x++;
						}
					}
				}
			%>
			<form id="buy" method="post"
				action="${pageContext.request.contextPath}/Cart"
				onsubmit="confirm('Confirm?')">
				<table>
					<tr>
						<td><label>First Name</label></td>
						<td><input placeholder="first name" type="text" name="firstname" required="required" /></td>
					</tr>
					<tr>
						<td><label>Last Name</label></td>
						<td><input placeholder="last name" type="text" name="lastname" required="required" /></td>
					</tr>
					<tr>
						<td><label>Email</label></td>

						<td><input placeholder="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
							type="email" name="email" required="required" />
						</td>
					</tr>
					<tr>
						<td><label>Phone Number</label></td>
						<td><input placeholder="phone number" type="text" pattern="[+0-9]{9,}"
							title="format: numbers only and 9 digits or more"
							name="phonenumber" required="required" /></td>
					</tr>
					<tr>
						<td><label>Total</label></td>
						<td><%="$" + total%></td>
					</tr>
				</table>
				<input type="submit" value="Buy" />

			</form>
			<form id="clear_cart" method="post"
				action="${pageContext.request.contextPath}/Cart"
				onsubmit="confirm('Clear Cart?')">
			<input type="hidden" name="clear" value="yes" />
			<input type="submit" value="Clear cart" />		
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
<body>

</body>
</html>