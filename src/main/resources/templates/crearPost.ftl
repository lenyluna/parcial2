<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabell">Nuevo post</h4>
            </div>
            <div class="modal-body">
                <form action="/CrearPost/guardando" method="post" role="form" enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="exampleInputEmail1">Titulo</label>
                        <input type="text" required class="form-control" id="exampleInputEmail1" name="titulo">
                    </div>

                    <div id="gallery"></div>

                    <div class="form-group">
                        <label for="exampleInputEmail1">Descripci&oacuten</label>

                        <textarea class="form-control" required rows="3" name="descripcion"></textarea>
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


            </div>

        </div>

</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script src="/js/myJava.js" type="text/javascript"> </script>
</body>
</html>