<%-- 
    Document   : upload
    Created on : 2 Nov, 2018, 4:48:44 PM
    Author     : reganseah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
    <title>Track2Career</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
    <script src="js/jquery.min.js"></script>
    <script src="js/skel.min.js"></script>
    <script src="js/skel-layers.min.js"></script>
    <script src="js/init.js"></script>
    <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-xlarge.css" />
    </noscript>
<link rel="stylesheet" type="text/css" media="all" href="css/styles.css" />

</head>

<body id = "top">
<header id="header" class="skel-layers-fixed">
    <h1><a href="#">Track2Career</a></h1>
    <nav id="nav">
        <ul>
            <li><a href="userhome.html">Home</a></li>
            <li><a href="DBSTrackCourses.html">Track</a></li>
            <li><a href="jobs.html">Jobs</a></li>
            <li><a href="upload.html">Upload</a></li>
            <li><a href="profile.html">Profile</a></li>
        </ul>
    </nav>
</header>

<form id="upload" action="index.html" method="POST" enctype="multipart/form-data">

<fieldset>
<legend>CSV File Upload</legend>

<input type="hidden" id="MAX_FILE_SIZE" name="MAX_FILE_SIZE" value="300000" />

<div>
	<label for="fileselect">Files to upload:</label>
	<input type="file" id="fileselect" name="fileselect[]" multiple="multiple" />
	<div id="filedrag">or drop files here</div>
</div>

<div id="submitbutton">
	<button type="submit">Upload Files</button>
</div>

</fieldset>

</form>

<div id="messages">
<p>Status Messages</p>
</div>


<script src="js/filedrag.js"></script>
</body>
</html>