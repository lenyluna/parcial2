<#import "common/menu.ftl" as m>
<@m.menu />

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info" >
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
            </div>

            <div style="padding-top:30px" class="panel-body" >
                <form id="loginform" class="form-horizontal" role="form" method="post" action="/signup/guardando">

                    <div class="form-group">
                        <label for="email" class="col-md-3 control-label">Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="name" required/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-md-3 control-label">Username:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastname" class="col-md-3 control-label">Email:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="email" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label"><input type="button" onclick="getGeo()" value="Ubicacion"/></label>
                        <div class="col-md-9">
                        <input type="text" name="dir" disabled>
                        </div>
                    </div>

                    <div style="margin-top:10px" class="form-group">

                        <div class="col-sm-12 controls" align="center">
                            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Sign Up  </button>
                            <a href="/"> <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Cancel</button></a>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script src="/js/geolocalizacion.js" type="text/javascript"></script>
<script src="http://j.maxmind.com/app/geoip.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
</body>
</html>