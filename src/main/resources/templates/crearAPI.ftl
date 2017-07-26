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

            background="/yucaImagenes/fondo-otono.jpg"
        }


    </style>
</head>
<body>

<div class="container">
    <div class="modal-header">
        <h4 class="modal-title" id="myModalLabell">Nuevo post</h4>
    </div>
    <div class="modal-body">
        <form action="/api/post/new" method="post" role="form" enctype="multipart/form-data">

            <div class="form-group">
                <label for="exampleInputEmail1">Titulo</label>
                <input  type="text" name="titulo"  class="form-control" id="exampleInputEmail1" required />
            </div>
            <div id="gallery"></div>

            <div class="form-group">
                <label for="exampleInputEmail1">Descripci&oacuten</label>

                <textarea  name="descripcion" class="form-control"  rows="3" required ></textarea>
            </div>

            <div class="form-group">
                <label>Etiquetas</label>
                <input type="text" name="eti" class="form-control" id="eti" placeholder="se debe dividir por ','"/>
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