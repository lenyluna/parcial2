<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">
    <i><h3>#${etiqueta}</h3></i>
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
</div>