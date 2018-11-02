<%-- 
    Document   : profile
    Created on : 2 Nov, 2018, 4:46:04 PM
    Author     : reganseah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha256-3dkvEK0WLHRJ7/Csr0BZjAWxERc5WH7bdeUya2aXxdU= sha512-+L4yy6FRcDGbXJ9mPG8MT/3UCDzwR9gPeyFNMCtInsol++5m3bk2bXWKdZjvybmohrAsn3Ua5x8gfLnbE1YkOg==" crossorigin="anonymous">
    <!-- Bootstrap Core CSS -->
    <!--     <link href="css/bootstrap.min.css" rel="stylesheet"> -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" crossorigin="anonymous">
    <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-xlarge.css" />
    </noscript>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
    </script>
    <style>
        body {
            padding-top: 70px;
            /* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
        }

        .othertop{margin-top:10px;}
    </style>
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
</body>
<body>



<body>

<div class="container">
    <div class="row">
        <div class="col-md-10 ">
            <form class="form-horizontal">
                <fieldset>


                    <legend>User profile form requirement</legend>



                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Name (Full name)">Name (Full name)</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-user">
                                    </i>
                                </div>
                                <input id="Name (Full name)" name="Name (Full name)" type="text" placeholder="Name (Full name)" class="form-control input-md">
                            </div>


                        </div>


                    </div>

                    <!-- File Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Upload photo">Upload photo</label>
                        <div class="col-md-4">
                            <input id="Upload photo" name="Upload photo" class="input-file" type="file">
                        </div>
                    </div>


                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Primary Occupation">Primary Occupation</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-briefcase"></i>

                                </div>
                                <input id="Primary Occupation" name="Primary Occupation" type="text" placeholder="Primary Occupation" class="form-control input-md">
                            </div>


                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Secondary Occupation (if any)">Secondary Occupation (if any)</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-briefcase"></i>

                                </div>
                                <input id="Secondary Occupation (if any)" name="Secondary Occupation (if any)" type="text" placeholder="Secondary Occupation (if any)" class="form-control input-md">
                            </div>


                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Skills">Skills</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-graduation-cap"></i>

                                </div>

                                <!--
                                <input id="Skills" name="Skills" type="text" placeholder="Skills" class="form-control input-md">
                                -->
                                <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
                                <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
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
                                <script type="text/javascript">
                                    $(".myselect").select2();
                                </script>


                            </div>


                        </div>
                    </div>




                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Phone number ">Phone number </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-phone"></i>

                                </div>
                                <input id="Phone number " name="Phone number " type="text" placeholder="Primary Phone number " class="form-control input-md">

                            </div>


                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Email Address">Email Address</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-envelope-o"></i>

                                </div>
                                <input id="Email Address" name="Email Address" type="text" placeholder="Email Address" class="form-control input-md">

                            </div>

                        </div>
                    </div>






                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="Working Experience (time period)">Working Experience (time period)</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-clock-o"></i>

                                </div>
                                <input id="Working Experience (time period)" name="Working Experience" type="text" placeholder="Enter time period " class="form-control input-md">


                            </div>

                        </div>
                    </div>



                    <div class="form-group">
                        <label class="col-md-4 control-label" ></label>
                        <div class="col-md-4">
                            <a href="userhome.html" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> Submit</a>
                            <a href="profile.html" class="btn btn-danger" value=""><span class="glyphicon glyphicon-remove-sign"></span> Clear</a>

                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
        <div class="col-md-2 hidden-xs">
            <img src="images/regandemo.jpg" class="img-responsive img-thumbnail ">
        </div>


    </div>
</div>
<!-- jQuery Version 1.11.1 -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>





</body>
</html>