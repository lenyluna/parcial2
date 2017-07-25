<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">
    <div class="col-md-8 col-md-offset-2 well">
           <p><h3><i>${user.name} </i><small>${user.username}</small></h3></p>
            <hr style="color: #0056b2;"/>
        <div class="row">
            <div class="col-xs-12 col-sm-4 emphasis">
            <h2><strong>${cantArticulo}</strong></h2>
                <p><small><span class="glyphicon glyphicon-file"></span> Mis Posts</small></p>
            </div>
            <div class="col-xs-12 col-sm-4 emphasis">
                <h2><strong>${views}</strong></h2>
                <p><small><span class="glyphicon glyphicon-eye-open"></span> views</small></p>
            </div>
            <div class="col-xs-12 col-sm-4 emphasis">
                <h2><strong>${accesada}</strong></h2>
                <p><small><span class="glyphicon glyphicon-link"></span> Accesados</small></p>
            </div>

        </div>
        <hr/>
        <h3><strong>Mis Posts<strong></h3>
        <div class="row">
            <div class="col-md-12 well">
                <div class="row">
                <#list listPost as post>
                <a href="/verpost/${post.id}">
                    <div class="col-md-2 well" style="width: auto; height: auto;">
                        <h6>${post.titulo}</h6>
                        <img src="${post.urlimagen}" style="width: 150px; height: 150px;"/>
                        <hr>
                        <h7><span class="glyphicon glyphicon-eye-open"></span> ${post.views}</h7>
                    </div>
                </a>
                </#list>
                </div>
                <p align="right">
                    <a href="#">Siguiente>></a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script src="/js/myJava.js" type="text/javascript"> </script>
</html>