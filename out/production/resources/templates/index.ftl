<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">

    <div class="col-md-12 well">
        <div class="row">
        <#list listPost as post>
                <a href="/verpost/${post.id}">
                        <div class="col-md-2 well">
                       <h6>${post.titulo}</h6>
                       <img src="${post.urlimagen}" style="width: 150px; height: 150px;"/>
                       <hr>
                       <h7><span class="glyphicon glyphicon-eye-open"></span> ${post.views}</h7>
                    </div>
                </a>
        </#list>
        </div>

    </div>

</div><!-- /.container -->
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<script src="/js/myJava.js" type="text/javascript"> </script>

</html>