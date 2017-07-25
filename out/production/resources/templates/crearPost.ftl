<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">
            <div class="modal-header">
                <#if modificar=="false">
                    <h4 class="modal-title" id="myModalLabell">Nuevo post</h4>
                <#elseif modificar=="true">
                    <h4 class="modal-title" id="myModalLabell">Modificar post</h4>
                </#if>
            </div>
            <div class="modal-body">
                <#if modificar=="false">
                <form action="/CrearPost/guardando" method="post" role="form" enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="exampleInputEmail1">Titulo</label>
                        <input  type="text" name="titulo"  class="form-control" id="exampleInputEmail1" required/>
                    </div>

                    <div id="gallery"></div>

                    <div class="form-group">
                        <label for="exampleInputEmail1">Descripci&oacuten</label>

                        <textarea  name="descripcion" class="form-control"  rows="3" required ></textarea>
                    </div>

                    <div class="form-group">
                        <label>Etiquetas</label>
                        <input type="text" name="eti" class="form-control" id="eti"/>
                    </div>


                    <div class="form-group">
                        <label for="exampleInputEmail1">Foto</label>
                        <input required type="file" id="fileinput" accept="image/*" name="img"/>

                    </div>

                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary  btn-lg">Publicar</button>
                    </div>
                </form>
            <#elseif modificar=="true">
                <form action="/post/modificar/${id}/guardando" method="post" role="form">

                    <div class="form-group">
                        <label for="exampleInputEmail1">Titulo</label>
                        <input  type="text" name="titulo"  class="form-control" id="exampleInputEmail1" value="${titulo}" required/>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputEmail1">Descripci&oacuten</label>

                        <textarea  name="descripcion" class="form-control"  rows="3" required >${descripcion}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="exampleInputEmail1">Foto</label>
                        <img  src="${url}"/>
                    </div>

                    <div class="form-group">
                        <label>Etiquetas</label>
                        <input type="text" name="eti" class="form-control" id="eti" value="${etiquetas}" disabled/>
                    </div>
                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary  btn-lg">Guardar</button>
                    </div>
                </form>
            </#if>

            </div>

        </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script src="/js/myJava.js" type="text/javascript"> </script>
</body>
</html>