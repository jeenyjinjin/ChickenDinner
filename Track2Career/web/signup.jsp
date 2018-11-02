<%-- 
    Document   : signup
    Created on : 2 Nov, 2018, 4:46:44 PM
    Author     : reganseah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Material design sign up form</title>



    <link rel="stylesheet" href="css/signup.css">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

</head>

<body>

<div id="login-box">
    <div class="left">
        <h1>Sign up</h1>

        <input type="text" name="username" placeholder="Username" />
        <input type="text" name="email" placeholder="E-mail" />
        <input type="password" name="password" placeholder="Password" />

        <select class="myselect"  data-placeholder="Education">
            <option>SMU</option>
            <option>Other Universities</option>
            <option>JC</option>
            <option>Polytechnic</option>
            <option>International School</option>

        </select>
        <br/>
        <select class="myselect" multiple="multiple" style="width:525px;" data-placeholder = "Your Skills">
            <option>Python</option>
            <option>Consulting</option>
            <option>Management</option>
            <option>Java</option>
            <option>CPA</option>
            <option>Leadership</option>
            <option>Socialogical frameworks</option>
            <option>C++</option>
        </select>
        <input type="submit" name="signup_submit" value="Sign me up" />
    </div>

    <div class="right">
        <span class="loginwith">Sign in with<br />social network</span>

        <button class="social-signin facebook">Log in with facebook</button>

        <button class="social-signin google">Log in with Google+</button>

    </div>
    <div class = "bottomright"><a href = "userhome.html">Skip</a></div>
    <div class="or">OR</div>
    <script type="text/javascript">
        $(".myselect").select2();
    </script>
</div>



</body>

</html>

