<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>PicAbu!</title>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
            <form id="signin" class="navbar-form navbar-right" role="form" action="/login/${post.id}" method="post">
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

        <div class="col-md-8 col-md-offset-2 well">

            <div class="row">
                <h3>${post.titulo}</h3>
                <br>
                <h4>by <a class="button" style="text-decoration:none">${post.user.username}</a> <span class="glyphicon glyphicon-time"></span> <a class="button" style="text-decoration:none">${post.fecha}</a></h4>
                <div style="width: 250px;">
                    <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-link"></i></span>
                    <input  type="text" class="form-control" name="username" value="${link}"  readonly="readonly" />
                     </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <img class="img-responsive" alt="Responsive image" src="${post.urlimagen}" style="width: 1024px; height: 700px"/>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-eye-open"></span> <br> ${post.views} Views </h4>
                        </div>
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-star"></span> <br> 122456 Stars </h4>
                        </div>
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-hdd"></span> <br> ${bw} Mbps</h4>
                        </div>

                    </div>
                </div>
                <div class="col-md-6">
                    <#list post.listaEtiqueta as etiqueta>
                    <a href="#"><span class="badge">${etiqueta.name}</span></a>
                    </#list>
                </div>
            </div>

        </div>

        <div class="row">

            <div class="col-md-8 col-md-offset-2">
                <div class="widget-area no-padding blank">
                    <div class="status-upload">
                        <form role="form" method="post" action="/verpost/${post.id}/comentario">
                            <textarea name="comentario" placeholder="Comenta Algo!!!"></textarea>

                            <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Comentar</button>
                        </form>
                    </div>
                    <!-- Status Upload  -->
                </div>
                <!-- Widget Area -->
            </div>

        </div>
    </div>

    <br>

    <div class="col-md-8 col-md-offset-2 well">

        <div class="row ">
            <h3>Comentarios</h3>
            <hr>
            <#list listComent as comentario>
            <div class="row">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h5 class="panel-title">
                            ${comentario.autor.username}
                            <a href="/downVote/${comentario.id}">
                                <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>${comentario.downVote} votes</small></a>

                            <a href="/upVote/${comentario.id}">
                                <spam class="glyphicon glyphicon-triangle-top"></spam> <small>${comentario.upVote} votes</small></a>

                            <spam class="glyphicon glyphicon-time"></spam> <small>${comentario.fecha}</small>
                        </h5>
                    </div>
                    <div class="panel-body">${comentario.contenido} </div>
                </div>
            </div>
            </#list>

        </div>

    </div>
    <!-- /.container -->

</div>
</body>
</html>