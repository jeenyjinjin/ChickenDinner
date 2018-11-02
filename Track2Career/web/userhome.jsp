<%-- 
    Document   : userhome
    Created on : 2 Nov, 2018, 4:50:02 PM
    Author     : reganseah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
		</script>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

		<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">-->
	</head>
	<body id="top">

		<!-- Header -->
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

		<!-- Banner --><!--
			<section id="banner">
				<div class="inner">
					<h2>This is Ion</h2>
					<p>A free responsive template by <a href="http://templated.co">TEMPLATED</a></p>
					<ul class="actions">
						<li><a href="#content" class="button big special">Sign Up</a></li>
						<li><a href="#elements" class="button big alt">Learn More</a></li>
					</ul>
				</div>
			</section>
-->
		<!-- One
			<section id="one" class="wrapper style2">
				<header class="major">
					<h2>Ipsum feugiat consequat</h2>
					<p>Tempus adipiscing commodo ut aliquam blandit</p>
				</header>
				<div class="container">
					<div class="row">
						<div class="4u">
							<section class="special box">
								<i class="icon fa-area-chart major"></i>
								<h3>Justo placerat</h3>
								<p>Eu non col commodo accumsan ante mi. Commodo consectetur sed mi adipiscing accumsan ac nunc tincidunt lobortis.</p>
							</section>
						</div>
						<div class="4u">
							<section class="special box">
								<i class="icon fa-refresh major"></i>
								<h3>Blandit quis curae</h3>
								<p>Eu non col commodo accumsan ante mi. Commodo consectetur sed mi adipiscing accumsan ac nunc tincidunt lobortis.</p>
							</section>
						</div>
						<div class="4u">
							<section class="special box">
								<i class="icon fa-cog major"></i>
								<h3>Amet sed accumsan</h3>
								<p>Eu non col commodo accumsan ante mi. Commodo consectetur sed mi adipiscing accumsan ac nunc tincidunt lobortis.</p>
							</section>
						</div>
					</div>
				</div>
			</section>
			-->
		<!-- Two -->
			<section id="two" class="wrapper style1">
				<!-- reason: save space for more content information
				<header class="major">
					<h2>Commodo accumsan aliquam</h2>
					<p>Amet nisi nunc lorem accumsan</p>
				</header>
				-->
				<div class="container">
					<div class="row">
						<div class="6u">
							<section class="special">
								<a href="#" class="image fit"><img src="images/pic01.jpg" alt="" /></a>
								<h3>Career2Track</h3>
								<p>Fill in your dream job and determine your course!</p>
								<ul class="actions">
									<!--<li><a href="#" class="button alt">Learn More</a></li>-->
									<!--
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
										<input type="text" class="form-control" placeholder="Search for a job..." aria-describedby="basic-addon1" id="search" onfocus="setFocus(1)" onblur="setFocus(0)">
									</div>
									-->
									<div class = "wrap">
										<form action="TrackSearchResults.html" method="get">
											<div class="search">

												<input type="text" class="searchTerm" placeholder="Search Track">
												<button type="submit" class="searchButton">
													<i class="material-icons">search</i>
												</button>
											</div>
										</form>

									</div>
								</ul>
							</section>
						</div>
						<div class="6u">
							<section class="special">
								<a href="#" class="image fit"><img src="images/pic02.jpg" alt="" /></a>
								<h3>Track2Career</h3>
								<p>Ever wonder where a track is going to lead you? Explore your future potential now!</p>

								<ul class="actions">
									<!--<li><a href="#" class="button alt">Learn More</a></li>-->
									<!--
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
										<input type="text" class="form-control" placeholder="Search for a track..." aria-describedby="basic-addon1" id="search" onfocus="setFocus(1)" onblur="setFocus(0)">
									</div>
									-->
									<div class = "wrap">
										<form action="TrackSearchResults.html" method="get">
											<div class="search">

												<input type="text" class="searchTerm" placeholder="Search Track">
												<button type="submit" class="searchButton">
													<i class="material-icons">search</i>
												</button>
											</div>
										</form>

									</div>
								</ul>
							</section>
						</div>
					</div>
				</div>
			</section>

			<!--
			<section id="three" class="wrapper style2">
				<div class="container">
					<div class="row">
						<div class="8u">
							<section>
								<h2>Mollis ut adipiscing</h2>
								<a href="#" class="image fit"><img src="images/pic03.jpg" alt="" /></a>
								<p>Vis accumsan feugiat adipiscing nisl amet adipiscing accumsan blandit accumsan sapien blandit ac amet faucibus aliquet placerat commodo. Interdum ante aliquet commodo accumsan vis phasellus adipiscing. Ornare a in lacinia. Vestibulum accumsan ac metus massa tempor. Accumsan in lacinia ornare massa amet. Ac interdum ac non praesent. Cubilia lacinia interdum massa faucibus blandit nullam. Accumsan phasellus nunc integer. Accumsan euismod nunc adipiscing lacinia erat ut sit. Arcu amet. Id massa aliquet arcu accumsan lorem amet accumsan commodo odio cubilia ac eu interdum placerat placerat arcu commodo lobortis adipiscing semper ornare pellentesque.</p>
							</section>
						</div>
						<div class="4u">
							<section>
								<h3>Magna massa blandit</h3>
								<p>Feugiat amet accumsan ante aliquet feugiat accumsan. Ante blandit accumsan eu amet tortor non lorem felis semper. Interdum adipiscing orci feugiat penatibus adipiscing col cubilia lorem ipsum dolor sit amet feugiat consequat.</p>
								<ul class="actions">
									<li><a href="#" class="button alt">Learn More</a></li>
								</ul>
							</section>
							<hr />
							<section>
								<h3>Ante sed commodo</h3>
								<ul class="alt">
									<li><a href="#">Erat blandit risus vis adipiscing</a></li>
									<li><a href="#">Tempus ultricies faucibus amet</a></li>
									<li><a href="#">Arcu commodo non adipiscing quis</a></li>
									<li><a href="#">Accumsan vis lacinia semper</a></li>
								</ul>
							</section>
						</div>
					</div>
				</div>
			</section>			
			-->
		<!-- Footer -->


	</body>

</html>