<#macro menu>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>PicAbu!</title>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">




    <style>
        body {
            padding-top: 75px;
            background-color: #183E75;
            background="/yucaImagenes/fondo-otono.jpg"
        }


        /*
 * Black navbar style
 */
        #navbar-black.navbar-default { /* #3C3C3C - #222222 */
            font-size: 14px;
            background-color: rgba(34, 34, 34, 1);
            background: -webkit-linear-gradient(top, rgba(60, 60, 60, 1) 0%, rgba(34, 34, 34, 1) 100%);
            background: linear-gradient(to bottom, rgba(60, 60, 60, 1) 0%, rgba(34, 34, 34, 1) 100%);
            border: 0px;
            border-radius: 0;
        }
        #navbar-black.navbar-default .navbar-nav>li>a:hover,
        #navbar-black.navbar-default .navbar-nav>li>a:focus,
        #navbar-black.navbar-default .navbar-nav>li>ul>li>a:hover,
        #navbar-black.navbar-default .navbar-nav>li>ul>li>a:focus,
        #navbar-black.navbar-default .navbar-nav>.active>a,
        #navbar-black.navbar-default .navbar-nav>.active>a:hover,
        #navbar-black.navbar-default .navbar-nav>.active>a:focus {
            color: rgba(255, 255, 255, 1);
            background-color: rgba(0, 0, 0, 1);
            background: -webkit-linear-gradient(top, rgba(0, 0, 0, 1) 0%, rgba(0, 0, 0, 1) 100%);
            background: linear-gradient(to bottom, rgba(0, 0, 0, 1) 0%, rgba(0, 0, 0, 1) 100%);
        }
        #sidebar-black, #column-black {
            background-color: #222222;
        }
        #navbar-black.navbar-default .navbar-toggle {
            border-color: #222222;
        }
        #navbar-black.navbar-default .navbar-toggle:hover,
        #navbar-black.navbar-default .navbar-toggle:focus {
            background-color: #3C3C3C;
        }
        #navbar-black.navbar-default .navbar-nav>li>a,
        #navbar-black.navbar-default .navbar-nav>li>ul>li>a,
        #navbar-black.navbar-default .navbar-brand {
            color: #999999;
        }
        #navbar-black.navbar-default .navbar-toggle .icon-bar,
        #navbar-black.navbar-default .navbar-toggle:hover .icon-bar,
        #navbar-black.navbar-default .navbar-toggle:focus .icon-bar {
            background-color: #ffffff;
        }

        /*
         * Red navbar style
         */
        #navbar-red.navbar-default { /* #990033 - #cc0033 */
            font-size: 14px;
            background-color: rgba(153, 0, 51, 1);
            background: -webkit-linear-gradient(top, rgba(204, 0, 51, 1) 0%, rgba(153, 0, 51, 1) 100%);
            background: linear-gradient(to bottom, rgba(204, 0, 51, 1) 0%, rgba(153, 0, 51, 1) 100%);
            border: 0px;
            border-radius: 0;
        }
        #navbar-red.navbar-default .navbar-nav>li>a:hover,
        #navbar-red.navbar-default .navbar-nav>li>a:focus,
        #navbar-red.navbar-default .navbar-nav>li>ul>li>a:hover,
        #navbar-red.navbar-default .navbar-nav>li>ul>li>a:focus,
        #navbar-red.navbar-default .navbar-nav>.active>a,
        #navbar-red.navbar-default .navbar-nav>.active>a:hover,
        #navbar-red.navbar-default .navbar-nav>.active>a:focus {
            color: rgba(51, 51, 51, 1);
            background-color: rgba(255, 255, 255, 1);
            background: -webkit-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
            background: linear-gradient(to bottom, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
        }
        #sidebar-red, #column-red {
            background-color: #990033;
        }
        #navbar-red.navbar-default .navbar-toggle {
            border-color: #990033;
        }
        #navbar-red.navbar-default .navbar-toggle:hover,
        #navbar-red.navbar-default .navbar-toggle:focus {
            background-color: #cc0033;
        }
        #navbar-red.navbar-default .navbar-nav>li>a,
        #navbar-red.navbar-default .navbar-nav>li>ul>li>a,
        #navbar-red.navbar-default .navbar-brand {
            color: #999999;
        }
        #navbar-red.navbar-default .navbar-toggle .icon-bar,
        #navbar-red.navbar-default .navbar-toggle:hover .icon-bar,
        #navbar-red.navbar-default .navbar-toggle:focus .icon-bar {
            background-color: #ffffff;
        }

        /*
         * Darkblue navbar style
         */
        #navbar-darkblue.navbar-default { /* #003399 - #0033cc */
            font-size: 14px;
            background-color: rgba(51, 51, 153, 1);
            background: -webkit-linear-gradient(top, rgba(51, 51, 204, 1) 0%, rgba(51, 51, 153, 1) 100%);
            background: linear-gradient(to bottom, rgba(51, 51, 204, 1) 0%, rgba(51, 51, 153, 1) 100%);
            border: 0px;
            border-radius: 0;
        }
        #navbar-darkblue.navbar-default .navbar-nav>li>a:hover,
        #navbar-darkblue.navbar-default .navbar-nav>li>a:focus,
        #navbar-darkblue.navbar-default .navbar-nav>li>ul>li>a:hover,
        #navbar-darkblue.navbar-default .navbar-nav>li>ul>li>a:focus,
        #navbar-darkblue.navbar-default .navbar-nav>.active>a,
        #navbar-darkblue.navbar-default .navbar-nav>.active>a:hover,
        #navbar-darkblue.navbar-default .navbar-nav>.active>a:focus {
            color: rgba(51, 51, 51, 1);
            background-color: rgba(255, 255, 255, 1);
            background: -webkit-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
            background: linear-gradient(to bottom, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
        }
        #sidebar-darkblue, #column-darkblue {
            background-color: #333399;
        }
        #navbar-darkblue.navbar-default .navbar-toggle {
            border-color: #333399;
        }
        #navbar-darkblue.navbar-default .navbar-toggle:hover,
        #navbar-darkblue.navbar-default .navbar-toggle:focus {
            background-color: #3333cc;
        }
        #navbar-darkblue.navbar-default .navbar-nav>li>a,
        #navbar-darkblue.navbar-default .navbar-nav>li>ul>li>a,
        #navbar-darkblue.navbar-default .navbar-brand {
            color: #999999;
        }
        #navbar-darkblue.navbar-default .navbar-toggle .icon-bar,
        #navbar-darkblue.navbar-default .navbar-toggle:hover .icon-bar,
        #navbar-darkblue.navbar-default .navbar-toggle:focus .icon-bar {
            background-color: #ffffff;
        }

        /*
         * Darkgreen navbar style
         */
        #navbar-darkgreen.navbar-default { /* #006633 - #009933 */
            font-size: 14px;
            background-color: rgba(0, 102, 51, 1);
            background: -webkit-linear-gradient(top, rgba(0, 153, 51, 1) 0%, rgba(0, 102, 51, 1) 100%);
            background: linear-gradient(to bottom, rgba(0, 153, 51, 1) 0%, rgba(0, 102, 51, 1) 100%);
            border: 0px;
            border-radius: 0;
        }
        #navbar-darkgreen.navbar-default .navbar-nav>li>a:hover,
        #navbar-darkgreen.navbar-default .navbar-nav>li>a:focus,
        #navbar-darkgreen.navbar-default .navbar-nav>li>ul>li>a:hover,
        #navbar-darkgreen.navbar-default .navbar-nav>li>ul>li>a:focus,
        #navbar-darkgreen.navbar-default .navbar-nav>.active>a,
        #navbar-darkgreen.navbar-default .navbar-nav>.active>a:hover,
        #navbar-darkgreen.navbar-default .navbar-nav>.active>a:focus {
            color: rgba(51, 51, 51, 1);
            background-color: rgba(255, 255, 255, 1);
            background: -webkit-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
            background: linear-gradient(to bottom, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
        }
        #sidebar-darkgreen, #column-darkgreen {
            background-color: #006633;
        }
        #navbar-darkgreen.navbar-default .navbar-toggle {
            border-color: #006633;
        }
        #navbar-darkgreen.navbar-default .navbar-toggle:hover,
        #navbar-darkgreen.navbar-default .navbar-toggle:focus {
            background-color: #009933;
        }
        #navbar-darkgreen.navbar-default .navbar-nav>li>a,
        #navbar-darkgreen.navbar-default .navbar-nav>li>ul>li>a,
        #navbar-darkgreen.navbar-default .navbar-brand {
            color: #999999;
        }
        #navbar-darkgreen.navbar-default .navbar-toggle .icon-bar,
        #navbar-darkgreen.navbar-default .navbar-toggle:hover .icon-bar,
        #navbar-darkgreen.navbar-default .navbar-toggle:focus .icon-bar {
            background-color: #ffffff;
        }

        /*
         * Yellow navbar style
         */
        #navbar-yellow.navbar-default { /* #99ff00 - #ccff00 */
            font-size: 14px;
            background-color: rgba(153, 255, 0, 1);
            background: -webkit-linear-gradient(top, rgba(204, 255, 0, 1) 0%, rgba(153, 255, 0, 1) 100%);
            background: linear-gradient(to bottom, rgba(204, 255, 0, 1) 0%, rgba(153, 255, 0, 1) 100%);
            border: 0px;
            border-radius: 0;
        }
        #navbar-yellow.navbar-default .navbar-nav>li>a:hover,
        #navbar-yellow.navbar-default .navbar-nav>li>a:focus,
        #navbar-yellow.navbar-default .navbar-nav>li>ul>li>a:hover,
        #navbar-yellow.navbar-default .navbar-nav>li>ul>li>a:focus,
        #navbar-yellow.navbar-default .navbar-nav>.active>a,
        #navbar-yellow.navbar-default .navbar-nav>.active>a:hover,
        #navbar-yellow.navbar-default .navbar-nav>.active>a:focus {
            color: rgba(51, 51, 51, 1);
            background-color: rgba(255, 255, 255, 1);
            background: -webkit-linear-gradient(top, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
            background: linear-gradient(to bottom, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 100%);
        }
        #sidebar-yellow, #column-yellow {
            background-color: #99ff00;
        }
        #navbar-yellow.navbar-default .navbar-toggle {
            border-color: #99ff00;
        }
        #navbar-yellow.navbar-default .navbar-toggle:hover,
        #navbar-yellow.navbar-default .navbar-toggle:focus {
            background-color: #ccff00;
        }
        #navbar-yellow.navbar-default .navbar-nav>li>a,
        #navbar-yellow.navbar-default .navbar-nav>li>ul>li>a,
        #navbar-yellow.navbar-default .navbar-brand {
            color: #999999;
        }
        #navbar-yellow.navbar-default .navbar-toggle .icon-bar,
        #navbar-yellow.navbar-default .navbar-toggle:hover .icon-bar,
        #navbar-yellow.navbar-default .navbar-toggle:focus .icon-bar {
            background-color: #ffffff;
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
                                <li><a href="/listUsuario/"><span class="glyphicon glyphicon-list-alt"></span> Lista de usuarios</a></li>
                                <li class="divider"></li>
                            </#if>
                            <#if tipoUser !="Anonimo">
                            <li><a href="/perfil"><span class="glyphicon glyphicon-info-sign"></span> Mis informaciones</a></li>
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
</#macro>