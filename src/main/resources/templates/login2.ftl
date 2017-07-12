<#import "common/menu.ftl" as m>
<@m.menu />
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info" >
            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
            </div>

            <div style="padding-top:30px" class="panel-body" >

                <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                <div class="alert alert-danger">El usuario o contraseña es incorrecta</div>
                <form id="loginform" class="form-horizontal" role="form" method="post" action="/login/-1">

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username" required>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password" required>
                    </div>

                    <div style="margin-top:10px" class="form-group">

                        <div class="col-sm-12 controls">
                            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Login  </button>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                No tienes una cuenta!
                                <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                    Sign Up aqu&iacute!
                                </a>
                            </div>
                        </div>
                    </div>
                </form>



            </div>
        </div>
    </div>
    <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
            </div>
            <div class="panel-body" >
                <form id="signupform" class="form-horizontal" role="form" action="/signup/" method="post">

                    <div class="form-group">
                        <label for="email" class="col-md-3 control-label">Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="name" required>
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
                            <input type="Email" class="form-control" name="email" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-3 control-label">Password</label>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="password" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <button id="btn-signup" type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> Send</button>
                            <button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>