<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">

        <div class="col-md-8 col-md-offset-2 well">

            <div class="row">
                <h3>${post.titulo}</h3>
                <br>
                <h4>by <a class="button"  <#if login=="true"> href="/mensaje/${post.user.id}" </#if> style="text-decoration:none">${post.user.username}</a> <span class="glyphicon glyphicon-time"></span> <a class="button" style="text-decoration:none">${post.fecha}</a></h4>
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
                            <h4> <span class="glyphicon glyphicon-hdd"></span> <br> ${bw} Mbps</h4>
                        </div>
                        <div class="col-md-6">
                            <h4> <span class="glyphicon glyphicon-link"></span> <br> ${accesada} </h4>
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

    <div class="col-md-8 col-md-offset-2 well">

        <div class="row ">
            <h3>Comentarios</h3>
            <hr>
            <#list listComent as comentario>
            <div class="row">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h5 class="panel-title">
                <#if login=="true"> <a href="/mensaje/${post.user.id}">${comentario.autor.username}</a> </#if>

                            <#if login=="true"><a href="/downVote/${comentario.id}" >
                                <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>${comentario.downVote} votes</small></a>

                            <a href="/upVote/${comentario.id}">
                                <spam class="glyphicon glyphicon-triangle-top"></spam> <small>${comentario.upVote} votes</small></a>
                            <#else>
                                <a  class="button" style="text-decoration:none">
                                    <spam class="glyphicon glyphicon-triangle-bottom"></spam> <small>${comentario.downVote} votes</small></a>

                                    <a class="button" style="text-decoration:none">
                                        <spam class="glyphicon glyphicon-triangle-top"></spam> <small>${comentario.upVote} votes</small></a>
                            </#if>
                            <spam class="glyphicon glyphicon-time"></spam> <small>${comentario.fecha}</small>
                           <#if login=="true"><a href="/eliminar/${post.id}/comentario/${comentario.id}"> <spam class="glyphicon glyphicon-remove"></spam> </a></#if>
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