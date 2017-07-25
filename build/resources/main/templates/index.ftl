<#import "common/menu.ftl" as m>
<@m.menu />
<#if veri=="ok">
<div class="alert alert-success" align="center"> ${mensaje}  <#if creado=="true"><a href="/post/${link}">Haz clic</a> para ir al Post </#if> <a href="/msjRemove/inicio/-1"><span class="glyphicon glyphicon-remove"></span> </a></div>
<#elseif veri=="error">
<div class="alert alert-danger" align="center">Ha ocurrido un error realizando la operaci&oacute..! <a href="/msjRemove/inicio/-1"><span class="glyphicon glyphicon-remove"></span> </a></div>
</#if>
<div class="container">
    <div class="col-md-12 well">
        <div class="row">
        <#list listPost as post>
               <a href="/verpost/${post.id}/true">
                    <div class="col-md-2 well">
                       <h6>${post.titulo}</h6>
                       <img style="width: 150px; height: 150px;" src="${post.urlimagen}" />
                       <hr>
                       <h7><span class="glyphicon glyphicon-eye-open"></span> ${post.views}</h7>
                    </div>
               </a>
        </#list>
        </div>
    </div>
<!--<a href="/">Siguiente.>> </a>-->
</div><!-- /.container -->
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script src="/js/myJava.js" type="text/javascript"> </script>

</html>