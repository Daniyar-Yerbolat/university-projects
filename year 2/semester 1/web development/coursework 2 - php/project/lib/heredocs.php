<?php

function login(){
		$login = <<<login
		<form class="form-group" method="POST" action="../controller/sign_in_controller.php">
			<label>Username
			<input type="text" placeholder="username here" name="username" pattern"[A-Za-z0-9\S]{6,25}" required="required"/>
			</label>
			<br/>
			<label>Password
			<input type="password" placeholder="password here" name="password" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required="required"/>
			</label>
			<br/>
			<input type="submit" name="login" value="Log In"/>
		</form>
		<a href="register.php">Register</a>
login;
		return $login;
	}

	function register(){
		$register = <<<register
		<form class="form-group" method="POST" action="../controller/sign_in_controller.php">
			<label>Username
				<input type="text" name="username" pattern="[A-Za-z0-9\S]{6,25}" required="required"/>
			</label>
			<br/>
			<label>Password
				<input type="password" name="password1" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required="required"/>
			</label>
			<br/>
			<label>Retype Password
				<input type="password" name="password2" pattern="[A-Za-z0-9._%+-]{6,20}" title="Password should be at least 6 characters long" required="required"/>
			</label>
			<br/>
			<input type="submit" name="register" value="Register"/>
		</form>
register;
		return $register;
	}

	function ask(){
		$ask = <<<ask
		<form class="ask" method="post" action="../controller/questions_controller.php">
			<input id="word" type="text" name="question" oninput="findQuestions()" placeholder="type question here" required="required"/>
			<br/>
			<textarea rows="4" cols="50" name="description" placeholder="description" required="required"></textarea>
			<br/>
			<input type="submit" name="ask" value="Ask"/>
		</form>
ask;
		return $ask;
	}

	function logout(){
		$logout = <<<logout
		<form class="logout" method="post" action="../controller/sign_in_controller.php">
			<input type="submit" name="logout" value="Log Out"/>
		</form>
logout;
		return $logout;
	}

?>