<#import "common/menu.ftl" as m>
<@m.menu />



<div class="container">


    <h1>Usuarios</h1>


    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Username</th>
            <th>Correo</th>
            <th>Privilegio</th>
            <th></th>
        </thead>
        <tfoot>
        <tr>
            <th>Nombre</th>
            <th>Username</th>
            <th>Correo</th>
            <th>Privilegio</th>
            <th>
            </th>

        </tr>
        </tfoot>
        <tbody>
<#list listUser as usuario>
        <tr>
            <#if usuario.privilegio!="AdministradorGeneral" && usuario.privilegio!="Anonimo" >
            <td>${usuario.name}</td>
            <td>${usuario.username}</td>
                <td>${usuario.correo}</td>
            <td>${usuario.privilegio}</td>
            <td>
                <a class="btn btn-warning btn-xs" href="/cambiarPrivi/${usuario.id}/" role="button">
                    <spam class="glyphicon glyphicon-lock"></spam>
                    Admin</a>
                <a class="btn btn-danger btn-xs" href="/eliminar/${usuario.id}" role="button">
                    <spam class="glyphicon glyphicon-remove"></spam>
                    Eliminar</a>
            </td>
            </#if>
        </tr>
</#list>
        </tbody>
    </table>


</div>


<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>

</body>
</html>