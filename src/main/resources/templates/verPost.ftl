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

    <link href="/css/myCSS.css" rel="stylesheet">
    <link href="/css/bootstrap.css" rel="stylesheet">
</head>
<#if cargando=="false">
<body>
<#else>
<body onload="document.getElementById('id01').style.display='block'">
</#if>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">PicAbu!</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <p class="navbar-btn">
                            <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-open" ></span>   Nueva Imagen</a>
                        </p>
                    </li>
                    <#if login=="false">
                    <li>
                        <a onclick="document.getElementById('id01').style.display='block'" style="width:auto; padding-top: 15px; padding-left: 580px">Log in</a>
                        <div id="id01" class="modal">
                            <form class="modal-content animate" method="post" action="/login/${post.id}">
                                <div class="imgcontainer">
                                    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                                    <img src="/img/img_avatar2.png" alt="Avatar" class="avatar">
                                </div>
                                <div class="container2">
                                    <#if cargando == "true">
                                        <div class="alert alert-danger">El usuario o contraseña es incorrecta</div>
                                        <label><span class="glyphicon glyphicon-user"></span><b> Username:</b></label>
                                        <input type="text" name="username" value="${username}" required/>
                                        <label><span class="glyphicon glyphicon-eye-open"></span><b> Password:</b></label>
                                        <input type="password" name="password"  required/>
                                    <#else>
                                        <label><span class="glyphicon glyphicon-user"></span><b> Username:</b></label>
                                        <input type="text" name="username" required/>

                                        <label><span class="glyphicon glyphicon-eye-open"></span><b> Password:</b></label>
                                        <input type="password" name="password" required/>
                                    </#if>

                                </div>
                                <div class="container2" style="background-color:#f1f1f1">
                                    <p align="center">
                                        <button type="submit" class="btn btn-primary"><span
                                                class="glyphicon glyphicon-off"></span> Log In
                                        </button>
                                        <button type="button" onclick="document.getElementById('id01').style.display='none'"
                                                class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>
                                            Cancel
                                        </button>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </li>
                    <li>
                        <a onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Sign up</a>
                        <div id="id02" class="modal">
                            <form class="modal-content animate" method="post" action="/signup/">
                            <span onclick="document.getElementById('id02').style.display='none'" class="close"
                                  title="Close Modal">&times;</span>
                                <div class="container2">
                                    <label><b>Name:</b> </label>
                                    <input type="text" name="nombre" required/>
                                    <label><span class="glyphicon glyphicon-user"></span><b> Username: </b></label>
                                    <input type="text" name="username" required/>
                                    <label><span class="glyphicon glyphicon-envelope"></span><b> Email: </b></label>
                                    <input type="text" name="email" required/>
                                    <label><span class="glyphicon glyphicon-eye-open"></span><b> Password: </b></label>
                                    <input type="password" name="password" required/>

                                </div>
                                <div class="container2" style="background-color:#f1f1f1">
                                    <p align="center">
                                        <button type="submit" class="btn btn-primary"><span
                                                class="glyphicon glyphicon-send"></span> Send
                                        </button>
                                        <button type="button" onclick="document.getElementById('id02').style.display='none'"
                                                class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>
                                            Cancel
                                        </button>
                                    </p>
                                </div>
                            </form>

                        </div>
                    </li>
                    </#if>
                    <#if login=="true">
                    <li>
                        <p  style="padding-top: 15px; padding-left: 650px"><span class="glyphicon glyphicon-user"></span> ${username}
                          <!--Cuando pasen el mause por esta parte deben de salir las opciones de abajo -->
                              <ul><a href="#"><span class="glyphicon glyphicon-cog"></span><span class="caret icon-arrow-down6"></span></a>
                                  <ul>
                                      <!-- <li><a href="#"> <span class="icon-dot"></span></a></li>-->
                                      <#if tipoUser == "AdministradorGeneral" >
                                      <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Lista de usuarios</a></li>
                                      </#if>
                                      <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                                  </ul>
                             </ul>
                        </p>
                    </li>
                    </#if>
                </ul>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>
<div class="container">

        <div class="col-md-8 col-md-offset-2 well">

            <div class="row">
                <h3>${post.titulo}</h3>
                <br>
                <h4>by <a class="button" style="text-decoration:none">${post.user.username}</a> <span class="glyphicon glyphicon-time"></span> <a class="button" style="text-decoration:none">${post.fecha}</a></h4>
                <div class="input-group-addon"><span class="glyphicon glyphicon-link"></span> </div>
                <input type="text" class="form-control" id="exampleInputAmount" value="${link}"  align="right" readonly="readonly"/>
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
                        <form>
                            <textarea placeholder="Comenta Algo!!!"></textarea>

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

            <div class="row">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h5 class="panel-title">
                            Nombre_del_usuario1
                            <a href="">
                                <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>1254 votes</small></a>

                            <a href="">
                                <spam class="glyphicon glyphicon-triangle-top"></spam> <small>1254 votes</small></a>

                            <spam class="glyphicon glyphicon-time"></spam> <small>06/07/2017</small>
                        </h5>
                    </div>
                    <div class="panel-body">Panel content </div>
                </div>
            </div>

            <div class="row">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h5 class="panel-title">
                            Nombre_del_usuario2
                            <a href="">
                                <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>1254 votes</small></a>

                            <a href="">
                                <spam class="glyphicon glyphicon-triangle-top"></spam> <small>1254 votes</small></a>

                            <spam class="glyphicon glyphicon-time"></spam> <small>06/07/2017</small>
                        </h5>
                    </div>
                    <div class="panel-body">Panel content </div>
                </div>
            </div>

        </div>




    </div>
    <!-- /.container -->

</div>
<!-- /.container -->
<script src="/js/jquery.js"></script>
<script src="/js/myJquery.js"></script>
</body>

</html>