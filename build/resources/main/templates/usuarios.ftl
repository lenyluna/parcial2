<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>PicAbu!</title>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">

    <style>
        body {
            padding-top: 75px;
        }

    </style>
    <link href="/css/myCSS.css" rel="stylesheet"/>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
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
                    <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-open"></span> Nueva Imagen</a>
                </p>
            <#if login=="false">
                <li><a href="/signup/">Sign up</a></li>
            </#if>
            <#if login=="true">
                <li style="padding-top: 15px; padding-left: 800px"><span
                        class="glyphicon glyphicon-user"></span> ${username}</li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span
                            class="glyphicon glyphicon-cog"><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <#if tipoUser == "AdministradorGeneral" >
                            <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Lista de usuarios</a>
                            </li>
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
            <form id="signin" class="navbar-form navbar-right" role="form" action="/login/${post.id}" method="post">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="text" class="form-control" name="username" value="" placeholder="Username" required/>
                </div>

                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="password" type="password" class="form-control" name="password" value=""
                           placeholder="Password" required/>
                </div>

                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Login
                </button>
            </form>
        </#if>
        </div>
    </div>
</div>

<div class="container">


    <h1>Usuarios</h1>


    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Username</th>
            <th>Correo</th>
            <th>Privilegio</th>
            <th></th>
        </thead>
        <tfoot>
        <tr>
            <th>Nombre</th>
            <th>Username</th>
            <th>Correo</th>
            <th>Privilegio</th>
            <th>
            </th>

        </tr>
        </tfoot>
        <tbody>
<#list listUser as usuario>
        <tr>
            <#if usuario.privilegio!="AdministradorGeneral">
            <td>${usuario.name}</td>
            <td>${usuario.username}</td>
                <td>${usuario.correo}</td>
            <td>${usuario.privilegio}</td>
            <td>
                <a class="btn btn-warning btn-xs" href="/cambiarPrivi/${usuario.id}/" role="button">
                    <spam class="glyphicon glyphicon-lock"></spam>
                    Admin</a>
                <a class="btn btn-danger btn-xs" href="/eliminar/${usuario.id}" role="button">
                    <spam class="glyphicon glyphicon-remove"></spam>
                    Eliminar</a>
            </td>
            </#if>
        </tr>
</#list>
        </tbody>
    </table>


</div>


<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>

</body>
</html>