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
                    <a href="/CrearPost/" class="btn btn-success" ><span class="glyphicon glyphicon-open" ></span> Nueva Imgen</a>
                </p>
            </li>
            <#if login=="false">
            <li><a href="/signup/">Sign up</a></li>
            </#if>
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
        <#if login=="false">
        <form id="signin" class="navbar-form navbar-right" role="form" action="/login/-1" method="post">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input  type="text" class="form-control" name="username" value="" placeholder="Username" required />
            </div>

            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock" ></i></span>
                <input id="password" type="password" class="form-control" name="password" value="" placeholder="Password" required/>
            </div>

            <button type="submit" class="btn btn-primary"> <span class="glyphicon glyphicon-send"></span> Login</button>
        </form>
        </#if>
    </div>
</div>
</div>

<div class="container">

    <div class="col-md-12 well">
        <div class="row">
        <#list listPost as post>
                <a href="/verpost/${post.id}">
                        <div class="col-md-2 well">
                       <h6>${post.titulo}</h6>
                       <img src="${post.urlimagen}" style="width: 150px; height: 150px;"/>
                       <hr>
                       <h7><span class="glyphicon glyphicon-eye-open"></span> ${post.views}</h7>
                    </div>
                </a>
        </#list>
        </div>

    </div>

</div><!-- /.container -->
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script src="/js/myJava.js" type="text/javascript"> </script>

</html>