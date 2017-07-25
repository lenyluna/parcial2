<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">

        <div class="col-md-8 col-md-offset-2 well">
            <div class="row">
                <#if login =="true">
                <p align="right"><#if username == post.user.username || tipoUser == "AdministradorGeneral"><a href="/post/modificar/${post.id}"><span class="glyphicon glyphicon-edit"></span> Modificar</a> </#if> <#if tipoUser == "AdministradorGeneral"><a href=""><span class="glyphicon glyphicon-remove"></span> Eliminar </a> </#if></p>
                </#if>
                    <h3>${post.titulo}</h3>
                <br>
                <h4>by <a class="button"  <#if login=="true"> href="/mensaje/${username}/${post.user.username}" </#if> style="text-decoration:none">${post.user.username}</a> <span class="glyphicon glyphicon-time"></span> <a class="button" style="text-decoration:none">${post.fecha}</a></h4>
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
                <p style="padding-left: 8px;"><b>Descripci&oacuten:</b> <br/> ${post.descripcion}</p>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-eye-open"></span> <br> ${post.views} Views </h4>
                        </div>
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-hdd"></span> <br> ${bw} Mbps</h4>
                        </div>
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-link"></span> <br> ${accesada} </h4>
                        </div>

                    </div>
                </div>
                <div class="col-md-6">
                    <#list post.listaEtiqueta as etiqueta>
                    <a href="/postByEtiqueta/${etiqueta.id}/"><span class="badge">${etiqueta.name}</span></a>
                    </#list>
                </div>
            </div>

        </div>

        <div class="row">

            <div class="col-md-8 col-md-offset-2">
                <div class="widget-area no-padding blank">
                    <div class="status-upload">
                      <#if login=="true"> <form role="form" method="post" action="/verpost/${post.id}/comentario">
                            <textarea name="comentario" placeholder="Comenta Algo!!!"></textarea>

                            <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Comentar</button>
                        </form>
                      </#if>
                    </div>
                    <!-- Status Upload  -->
                </div>
                <!-- Widget Area -->
            </div>

        </div>
    </div>

    <br>

    <#if listComent?size!=0>
    <div class="col-md-8 col-md-offset-2 well">

        <div class="row ">
            <h3>Comentarios</h3>
            <hr>
            <#list listComent as comentario>
            <div class="row">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h5 class="panel-title">
                            <#if login=="true"> <a href="/mensaje/${username}/${post.user.username}">${comentario.autor.username}</a> <#else> ${comentario.autor.username}</#if>
                            <#assign x="false"/>
                            <#assign y="false"/>
                            <#if login=="true">
                                <#list comentario.listLikes as like>
                                    <#if like.user.username == "${username}" && like.opcion == "like">
                                        <#assign x="true"/>
                                    <#elseif like.user.username == "${username}" && like.opcion == "unlike">
                                        <#assign y="true">
                                    </#if>
                                 </#list>
                                <#if y=="false" && x=="false">
                                    <a href="/downVote/${comentario.id}" >
                                        <spam class="glyphicon glyphicon-triangle-bottom"></spam></a> <small>${comentario.downVote} votes</small>
                                <#elseif y=="true" && x=="false">
                                    <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small style="color: #0000FF">${comentario.downVote} votes</small>
                                <#elseif y=="false" && x == "true">
                                    <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>${comentario.downVote} votes</small>
                                </#if>
                                <#if x=="false" && y=="false">
                                    <a href="/upVote/${comentario.id}">
                                        <spam class="glyphicon glyphicon-triangle-top"></spam></a> <small>${comentario.upVote} votes</small>
                                <#elseif x=="true" && y=="false">
                                    <spam class="glyphicon glyphicon-triangle-top"></spam></a>  <small style="color: #0000FF">${comentario.upVote} votes</small>
                                <#elseif y=="true" && x == "false">
                                    <spam class="glyphicon glyphicon-triangle-top"></spam></a>  <small>${comentario.upVote} votes</small>
                                </#if>
                            <#else>
                                <a  class="button" style="text-decoration:none">
                                    <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>${comentario.downVote} votes</small></a>

                                <a class="button" style="text-decoration:none">
                                    <spam class="glyphicon glyphicon-triangle-top"></spam> <small>${comentario.upVote} votes</small></a>
                            </#if>
                            <spam class="glyphicon glyphicon-time"></spam> <small>${comentario.fecha}</small>
                            <#if login=="true" && tipoUser=="AdministradorGeneral"><a href="/eliminar/${post.id}/comentario/${comentario.id}"> <spam class="glyphicon glyphicon-remove"></spam> </a></#if>
                        </h5>
                    </div>
                    <div class="panel-body">${comentario.contenido} </div>
                </div>
            </div>
            </#list>
        </div>

    </div>
    <!-- /.container -->
    </#if>
</div>
</body>
</html>