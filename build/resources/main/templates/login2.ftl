<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>PicAbu!</title>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 75px;
        }

    </style>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">PicAbu!</a>
        </div>


        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <p class="navbar-btn">
                        <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-open" ></span>   Nueva Imagen</a>
                    </p>
                <li><a href="#">Sing up</a></li>
            <#if login=="true">
                <li style="padding-top: 15px; padding-left: 800px"><span class="glyphicon glyphicon-user"></span> ${username}</li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-cog"><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <#if tipoUser == "AdministradorGeneral" >
                            <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Lista de usuarios</a></li>
                            <li class="divider"></li>
                        </#if>
                        <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                        <!--<li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>-->
                    </ul>
                </li>
                </p>
            </#if>
            </ul>
       <!-- <#if login=="false">
            <form id="signin" class="navbar-form navbar-right" role="form" action="/login/-1" method="post">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input  type="text" class="form-control" name="username" value="" placeholder="Username" required />
                </div>

                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" ></i></span>
                    <input id="password" type="password" class="form-control" name="password" value="" placeholder="Password" required/>
                </div>

                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </#if>-->
        </div>
    </div>
</div>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info" >
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
            </div>

            <div style="padding-top:30px" class="panel-body" >

                <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                <div class="alert alert-danger">El usuario o contraseña es incorrecta</div>
                <form id="loginform" class="form-horizontal" role="form" method="post" action="/login/-1">

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username" required>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password" required>
                    </div>

                    <div style="margin-top:10px" class="form-group">

                        <div class="col-sm-12 controls">
                            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Login  </button>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                No tienes una cuenta!
                                <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                    Sign Up aqu&iacute!
                                </a>
                            </div>
                        </div>
                    </div>
                </form>



            </div>
        </div>
    </div>
    <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
            </div>
            <div class="panel-body" >
                <form id="signupform" class="form-horizontal" role="form" action="/signup/" method="post">

                    <div class="form-group">
                        <label for="email" class="col-md-3 control-label">Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="name" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-md-3 control-label">Username:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-md-3 control-label">Email:</label>
                        <div class="col-md-9">
                            <input type="Email" class="form-control" name="email" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="password" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn-signup" type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Send</button>
                            <button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>